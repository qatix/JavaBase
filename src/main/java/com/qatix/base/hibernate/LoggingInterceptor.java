package com.qatix.base.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/3 9:16 AM
 */
@Slf4j
public class LoggingInterceptor extends EmptyInterceptor {

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.info("onLoad is called");
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.info("onSave is called");
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.info("onDelete is called");
        super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        log.info("onFlushDirty is called");
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public void postFlush(Iterator entities) {
        log.info("postFlush is called");
        super.postFlush(entities);
    }

    @Override
    public void preFlush(Iterator entities) {
        log.info("preFlush is called");
        super.preFlush(entities);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        log.info("afterTransactionBegin is called");
        super.afterTransactionBegin(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        log.info("afterTransactionCompletion is called");
        super.afterTransactionCompletion(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        log.info("beforeTransactionCompletion is called");
        super.beforeTransactionCompletion(tx);
    }

    // Logging SQL statement
    @Override
    public String onPrepareStatement(String sql) {
        log.info("onPrepareStatement is called");
        log.info(sql);
        return super.onPrepareStatement(sql);
    }
}
