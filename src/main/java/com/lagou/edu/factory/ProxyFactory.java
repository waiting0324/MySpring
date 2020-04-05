package com.lagou.edu.factory;

import com.lagou.edu.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 6550
 * @date 2020/1/6 上午 08:46
 * @description
 */
public class ProxyFactory {

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Object result;
                try {
                    transactionManager.beginTransaction();

                    result = method.invoke(obj, objects);

                    transactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback();
                    throw e;
                }
                return result;
            }
        });
    }

    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result;
                try {
                    transactionManager.beginTransaction();

                    result = method.invoke(obj, objects);

                    transactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback();
                    throw e;
                }
                return result;
            }
        });
    }
}
