package com.zhu.advice;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserServiceAround implements MethodInterceptor{
	private final static Logger LOG = LogManager.getLogger(UserServiceAround.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		LOG.info("start proxy");
		Object object = invocation.proceed();
		LOG.info("end proxy");
		return object;
	}
	
	public static void run1() {
		//获取工具
		ProxyFactory proxyFactory = new ProxyFactory();
		//获取要代理的类
		UserServiceImpl uServiceImpl = new UserServiceImpl();
		//设置代理目标
		proxyFactory.setTarget(uServiceImpl);
		//获取增强代码
		Advice advice = new UserServiceAround();
		//添加增强
		proxyFactory.addAdvice(advice);
		//获取代理类
		UserService userService = (UserService) proxyFactory.getProxy();
		userService.test("zhuzhu", "12");
	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = ac.getBean("proxy",UserService.class);
		userService.test("zhuzhu", "18");
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
