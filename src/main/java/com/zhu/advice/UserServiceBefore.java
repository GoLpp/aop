package com.zhu.advice;
import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserServiceBefore implements MethodBeforeAdvice{
	private final static Logger LOG = LogManager.getLogger(UserServiceBefore.class);

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		LOG.info("Before method");
	}
	
	public static void run1() {
		ProxyFactory pf = new ProxyFactory();
		UserServiceImpl userService = new UserServiceImpl();
		pf.setTarget(userService);
		pf.setOptimize(false);
		pf.setInterfaces(userService.getClass().getInterfaces());
		Advice advice = new UserServiceBefore();
		pf.addAdvice(advice);
		UserService iUserService = (UserService) pf.getProxy();
		iUserService.test("zhuzhu", "19");
 	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = ac.getBean("proxyBefore", UserService.class);
		userService.test("zhuzhu", "18");
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
