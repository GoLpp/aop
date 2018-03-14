package com.zhu.advice;
import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserServiceAfter implements AfterReturningAdvice{
	private final static Logger LOG = LogManager.getLogger(UserServiceAfter.class);

	@Override
	public void afterReturning(Object obj, Method method, Object[] args, Object arg3) throws Throwable {
		LOG.info("after method");
	}
	
	public static void run1() {
		ProxyFactory proxyFactory = new ProxyFactory();
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		proxyFactory.setTarget(userServiceImpl);
		Advice advice = new UserServiceAfter();
		proxyFactory.addAdvice(advice);
		UserService proxy = (UserService) proxyFactory.getProxy();
		proxy.test("zhzhu", "18");
		LOG.info(proxy.getClass().getName());
	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = ac.getBean("proxyAfter",UserService.class);
		userService.test("zhuzhu", "18");
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
