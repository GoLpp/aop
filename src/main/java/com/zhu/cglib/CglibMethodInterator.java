package com.zhu.cglib;
import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibMethodInterator implements MethodInterceptor{
	private final static Logger LOG = LogManager.getLogger(CglibMethodInterator.class);
	
	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		LOG.info("start");
		Object object = methodProxy.invokeSuper(target, args);
		LOG.info("end");
		return object;
	}
	
	public static void main(String[] args) {
		Enhancer eh = new Enhancer();
		eh.setSuperclass(CglibFather.class);
		eh.setCallback(new CglibMethodInterator());
		CglibFather proxy = (CglibFather) eh.create();
		proxy.test("zhuzhu", "123");
	}
}
