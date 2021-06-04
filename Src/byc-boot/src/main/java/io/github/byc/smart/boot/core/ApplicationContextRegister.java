package io.github.byc.smart.boot.core;

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
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // todo wait
    }
}
