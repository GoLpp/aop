package com.zhu.autoproxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhu.service.UserService;

public class BeanNameMatch {
	private final static Logger LOG = LogManager.getLogger(BeanNameMatch.class);
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext ("applicationContext-autoproxy.xml");
		
		UserService userService = ctx.getBean("userService", UserService.class);
		userService.test("admin", "123");
		LOG.info(userService.getClass().getName());
		
		UserService user2Service = ctx.getBean("user2Service", UserService.class);
		user2Service.test("admin", "123");
		LOG.info(user2Service.getClass().getName());

	}
}
