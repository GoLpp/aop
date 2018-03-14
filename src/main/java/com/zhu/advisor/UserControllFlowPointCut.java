package com.zhu.advisor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.advice.UserServiceAround;
import com.zhu.dao.UserDao;
import com.zhu.dao.impl.UserDaoImpl;
import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class UserControllFlowPointCut {
	private final static Logger LOG = LogManager.getLogger(UserControllFlowPointCut.class);
	
	public static void run1() {
		ProxyFactory pf = new ProxyFactory();
		UserDao userDao = new UserDaoImpl();
		pf.setTarget(userDao);
		
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		
		advisor.setAdvice(new UserServiceAround());
		advisor.setPointcut(new ControlFlowPointcut(UserServiceImpl.class, "test"));
		
		pf.addAdvisor(advisor);
		
		UserDao proxy = (UserDao) pf.getProxy();
		UserService service = new UserServiceImpl(proxy);
		service.test("zhuzu", "12");	
	}
	
	public static void run2() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("controlflow-pointcut.xml");
		UserService service = ac.getBean("userService",UserService.class);
		service.test("zhuzhu", "123");
	}
	
	public static void main(String[] args) {
		//run1();
		run2();
	}
}
