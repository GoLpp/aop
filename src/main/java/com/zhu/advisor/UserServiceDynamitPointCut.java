package com.zhu.advisor;
import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.advice.UserServiceAround;
import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserServiceDynamitPointCut extends DynamicMethodMatcherPointcut{
	private final static Logger LOG = LogManager.getLogger(UserServiceDynamitPointCut.class);

	@Override
	public boolean matches(Method method, Class<?> targetClass, Object... args) {
		if (args!=null && args.length > 0 && "zhuzhu".equals(args[0])) {
			return true;
		}
		return false;
	}
	
	public static void run1() {
		ProxyFactory pf = new ProxyFactory();
		UserService service = new UserServiceImpl();
		pf.setTarget(service);
		DefaultPointcutAdvisor dpa = new DefaultPointcutAdvisor();
		UserServiceAround advice = new UserServiceAround();
		UserServiceDynamitPointCut pointcut = new UserServiceDynamitPointCut();
		dpa.setAdvice(advice);
		dpa.setPointcut(pointcut);
		pf.addAdvisor(dpa);
		UserService service2 = (UserService) pf.getProxy();
		service2.test("zhuzhu", "18");
		LOG.info(service2.getClass().getName());
	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("dynamic-advisor.xml");
		UserService service = ac.getBean("proxy",UserService.class);
		service.test("zhuzhu", "18");
		LOG.info(service.getClass().getName());
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
