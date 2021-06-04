package io.github.byc.smart.base.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author : bamboo
 */
public class ContextUtils {
    private static ApplicationContext applicationContext;
    private static Logger logger = LoggerFactory.getLogger(ContextUtils.class);

    public static void setApplicationContext(ApplicationContext applicationContext){
        synchronized (ContextUtils.class){
            logger.debug("set applicationContext and notify all");
            ContextUtils.applicationContext = applicationContext;
            ContextUtils.class.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext(){
        synchronized (ContextUtils.class){
            while (applicationContext == null){
                try {
                    logger.debug("get applicationContext, while wait 1 minute");
                    if (applicationContext ==null){
                        logger.warn("have been waiting for applicationContext to be set for 1 minute");
                        throw new InterruptedException();
                    }
                }catch (InterruptedException interruptedException){
                    logger.debug("get applicationContext, wait interrupted");
                }
            }
            return applicationContext;
        }
    }
}
