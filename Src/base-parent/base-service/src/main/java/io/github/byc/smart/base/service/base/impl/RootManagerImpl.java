package io.github.byc.smart.base.service.base.impl;

import io.github.byc.smart.base.service.base.RootManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author : bamboo
 */
public class RootManagerImpl implements RootManager {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected void setRollbackOnly(){
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
