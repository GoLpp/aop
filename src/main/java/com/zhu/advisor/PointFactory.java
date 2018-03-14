package com.zhu.advisor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.dao.UserDao;
import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

public class PointFactory {
	private final static Logger LOG = LogManager.getLogger(PointFactory.class);
	
	public ControlFlowPointcut getPointcut() {
		return new ControlFlowPointcut(UserServiceImpl.class, "test");
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("controlpointcut-factory.xml");
		UserService service = ac.getBean("userService",UserService.class);
		service.test("zhuzhu", "123");
	}
}
