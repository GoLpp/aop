package com.zhu.advisor;
import java.security.Provider.Service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.advice.UserServiceAround;
import com.zhu.dao.UserDao;
import com.zhu.dao.impl.UserDaoImpl;
import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserComposablePointCutFactory {
	private final static Logger LOG = LogManager.getLogger(UserComposablePointCutFactory.class);
	
	public Pointcut getPointcut() {
		ControlFlowPointcut pointcut = new ControlFlowPointcut(UserServiceImpl.class, "test");
		NameMatchMethodPointcut matchPointcut = new NameMatchMethodPointcut();
		matchPointcut.addMethodName("findAllUser");
		
		ComposablePointcut c = new ComposablePointcut();
		return c.intersection((Pointcut)pointcut).intersection((MethodMatcher)matchPointcut);
	}
	
	public static void run1() {
		ProxyFactory pf = new ProxyFactory();
		
		UserDao dao = new UserDaoImpl();
		
		pf.setTarget(dao);
		
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		
		advisor.setAdvice(new UserServiceAround());
		
		//advisor.setPointcut(UserComposablePointCutFactory.getPointcut());
		
		pf.addAdvisor(advisor);
		
		UserDao userDao = (UserDao) pf.getProxy();
		
		UserService service = new UserServiceImpl(userDao);
		service.test("zz", "12");
	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("compsable-pointcut.xml");
		UserService service = ac.getBean("userService",UserServiceImpl.class);
		service.test("oo", "123");
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
