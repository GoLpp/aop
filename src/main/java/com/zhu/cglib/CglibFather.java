package com.zhu.cglib;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CglibFather implements Cglib{
	private final static Logger LOG = LogManager.getLogger(CglibFather.class);
	
	private String  name;
	private String age;
	
	public CglibFather() {
	}
	
	public CglibFather(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public void test(String name, String age) {
		LOG.info("test:" + name + age);
	}
}
