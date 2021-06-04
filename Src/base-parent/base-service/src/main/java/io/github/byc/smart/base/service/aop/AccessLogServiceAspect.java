package io.github.byc.smart.base.service.aop;

import io.github.byc.smart.base.core.aop.BaseAspect;
import io.github.byc.smart.base.core.support.OperatorService;
import io.github.byc.smart.base.service.support.ServiceAccessLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author : bamboo
 */
public class AccessLogServiceAspect extends BaseAspect {

    private static int index = 0;

    public static final ThreadLocal<Integer> SERVICE_ACCESS_INDEX = new ThreadLocal<>();

    public static final ThreadLocal<String> SERVICE_ACCESS_INDEX_UUID = new ThreadLocal<>();

    @Autowired(required = false)
    private ServiceAccessLogger serviceAccessLogger;

    @Autowired(required = false)
    private OperatorService operatorService;

    @Around("execution(* *..service.*Manager.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        int currentIndex = index++;
        Integer preServiceAccessIndex = SERVICE_ACCESS_INDEX.get();
        SERVICE_ACCESS_INDEX.set(currentIndex);
        String preServiceAccessIndexUUID = SERVICE_ACCESS_INDEX_UUID.get();
        SERVICE_ACCESS_INDEX_UUID.set(UUID.randomUUID().toString());

        String userName = this.operatorService == null ? "N/A" : this.operatorService.getOperatorName();

        this.logger.debug(String.format("[%s] [userName: %s]%s"), currentIndex, userName, proceedingJoinPoint.getSignature().toLongString());

        Long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
            return result;
        } catch (Exception ex) {
            result = ExceptionUtils.getRootCause(ex);
            if (result == null) {
                result = ex;
            }
            throw ex;
        } finally {
            try {
                Long endTime = System.currentTimeMillis();
                Long timeUsed = endTime - startTime;
                this.logger.debug("[%s]%sms", currentIndex, timeUsed);
                if (serviceAccessLogger != null) {
                    String serviceName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
                    String method = proceedingJoinPoint.getSignature().getName();
                    Object[] args = proceedingJoinPoint.getArgs();
                    this.serviceAccessLogger.logAccess(serviceName, method, args, result, timeUsed);
                }
            } finally {
                SERVICE_ACCESS_INDEX.set(preServiceAccessIndex);
                SERVICE_ACCESS_INDEX_UUID.set(preServiceAccessIndexUUID);
            }
        }
    }
}
