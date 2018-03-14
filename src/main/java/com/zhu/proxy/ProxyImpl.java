package com.zhu.proxy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProxyImpl implements InterfaceProxy{
	private final static Logger LOG = LogManager.getLogger(ProxyImpl.class);

	@Override
	public void testProxy(String a, String b) {
		LOG.info("testProxy");
	}
}
