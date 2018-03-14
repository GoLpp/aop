package com.zhu.service.impl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.support.DaoSupport;

import com.zhu.dao.UserDao;
import com.zhu.dao.impl.UserDaoImpl;
import com.zhu.service.UserService;

public class UserServiceImpl implements UserService{
	private final static Logger LOG = LogManager.getLogger(UserServiceImpl.class);
	
	private UserDao dao = new UserDaoImpl();
	
	public UserServiceImpl(UserDao dao) {
		this.dao = dao;
	}
	
	public UserServiceImpl() {
	
	}
	
	@Override
	public void test(String name, String age) {
		//LOG.info(name + "=====" + age);
		dao.findAllUser(name);
	}

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}
}
