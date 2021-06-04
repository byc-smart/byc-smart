package io.github.byc.smart.base.core.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

/**
 * <p>base class for all aspects</p>
 * @author : bamboo
 */
public class BaseAspect implements Ordered {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private int order;

    @Override
    public int getOrder() {
        return 0;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
