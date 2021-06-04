package io.github.byc.smart.base.service.support;

/**
 * @author : bamboo
 */
public interface ServiceAccessLogger {
    /**
     * 记录访问日志
     * @param serviceName 服务名
     * @param methodName 方法名
     * @param args 参数
     * @param result 结果
     * @param timeUsed 用时
     */
    void logAccess(String serviceName, String methodName, Object[] args, Object result, Long timeUsed);
}
