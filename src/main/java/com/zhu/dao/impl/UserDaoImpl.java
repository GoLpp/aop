package com.zhu.dao.impl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.zhu.dao.UserDao;

public class UserDaoImpl implements UserDao {
	private final static Logger LOG = LogManager.getLogger(UserDaoImpl.class);

	@Override
	public void findAllUser(String name) {
		LOG.info("----------"+name);
	}
}
