package com.zhu.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JdkProxy implements InvocationHandler{
	private final static Logger LOG = LogManager.getLogger(JdkProxy.class);
	
	private Object obj;
	
	public JdkProxy() {
	}
	
	public JdkProxy(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		LOG.info("jdkStart");
		Object object = method.invoke(obj, args);
		LOG.info("jdkEnd");
		return object;
	}
	
	public static void main(String[] args) {
		ProxyImpl proxyImpl = new ProxyImpl();
		JdkProxy jdkProxy = new JdkProxy(proxyImpl);
		InterfaceProxy proxy = (InterfaceProxy) Proxy.newProxyInstance(proxyImpl.getClass().getClassLoader(),
				proxyImpl.getClass().getInterfaces(), jdkProxy);
		proxy.testProxy("xx", "yy");
	}
}
