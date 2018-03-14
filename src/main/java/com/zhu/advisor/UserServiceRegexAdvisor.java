package com.zhu.advisor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.advice.UserServiceAround;
import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserServiceRegexAdvisor{
	private final static Logger LOG = LogManager.getLogger(UserServiceRegexAdvisor.class);
	
	public static void run1() {
		ProxyFactory proxyFactory = new ProxyFactory();
		UserService userService = new UserServiceImpl();
		proxyFactory.setTarget(userService);
		
		RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
		UserServiceAround advice = new UserServiceAround();
		advisor.setPattern(".*tes.*");
		advisor.setAdvice(advice);
		
		proxyFactory.addAdvisor(advisor);
		UserService proxy = (UserService) proxyFactory.getProxy();
		proxy.test("zhuzhu", "8");
		LOG.info(proxy.getClass().getName());
	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("regex-advisor.xml");
		UserService userService = ac.getBean("proxy", UserService.class);
		userService.test("zhuzzhu", "18");
		LOG.info(userService.getClass().getName());
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
