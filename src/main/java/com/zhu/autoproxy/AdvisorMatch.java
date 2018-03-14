package com.zhu.autoproxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.dao.UserDao;
import com.zhu.service.UserService;

public class AdvisorMatch {
	private final static Logger LOG = LogManager.getLogger(AdvisorMatch.class);
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-autoproxy2.xml");
		
		UserService userService = ctx.getBean("userService",UserService.class);
		userService.test("admin", "123");
		LOG.info(userService.getClass().getName());
		
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		userDao.findAllUser("admin");
		LOG.info(userDao.getClass().getName());

	}
}
