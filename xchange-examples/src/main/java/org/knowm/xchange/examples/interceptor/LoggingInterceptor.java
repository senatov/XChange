package org.knowm.xchange.examples.interceptor;

import lombok.extern.slf4j.Slf4j;
import si.mazi.rescu.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class LoggingInterceptor implements Interceptor {

	@Override
	public Object aroundInvoke(
			InvocationHandler invocationHandler, Object proxy, Method method, Object[] args)
			throws Throwable {
		long start = System.currentTimeMillis();
		Object result = invocationHandler.invoke(proxy, method, args);
		log.info(
				"{}.{} took {} ms.",
				method.getDeclaringClass().getName(),
				method.getName(),
				System.currentTimeMillis() - start);
		return result;
	}
}