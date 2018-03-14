package com.zhu.advisor;
import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.advice.UserServiceAround;
import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserServiceStaticAdvisor extends StaticMethodMatcherPointcutAdvisor{
	private final static Logger LOG = LogManager.getLogger(UserServiceStaticAdvisor.class);

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return UserService.class.isAssignableFrom(targetClass) && 
					"test".equals(method.getName());
	}
	
	public static void run1() {
		ProxyFactory pf = new ProxyFactory();
		UserService service = new UserServiceImpl();
		pf.setTarget(service);
		
		UserServiceStaticAdvisor usta = new UserServiceStaticAdvisor();
		usta.setAdvice(new UserServiceAround());
		pf.addAdvisor(usta);
		
		UserService userService = (UserService) pf.getProxy();
		userService.test("ggbone", "8");
	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("static-advisor.xml");
		UserService userService = ac.getBean("proxy", UserService.class);
		userService.test("ggbone", "8");
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
