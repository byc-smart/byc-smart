package io.github.byc.smart.boot.core;

import io.github.byc.smart.base.core.util.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : bamboo(kan.zhang-cn@hotmail.com)
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextUtils.setApplicationContext(applicationContext);
        logger.debug("applicationContext registered");
        if (logger.isTraceEnabled()) {
            for (String beanName : applicationContext.getBeanDefinitionNames()) {
                logger.trace(beanName);
            }
        }
    }
}
