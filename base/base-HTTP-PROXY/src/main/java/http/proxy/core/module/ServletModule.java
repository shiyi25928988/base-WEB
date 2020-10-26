package http.proxy.core.module;

import com.google.inject.Scopes;

import cache.base.module.CacheModule;
import http.proxy.core.servlet.DispatcherServlet;
import http.proxy.core.servlet.HttpProxyServlet;
import lombok.extern.slf4j.Slf4j;
import mq.base.module.MessageQueueModule;

/**
 * @author yshi
 *
 */
@Slf4j
public class ServletModule extends com.google.inject.servlet.ServletModule {
	
	
	/*
	 * Load the property files here, which stored in the folder '/base-WEB/src/main/resources' 
	 * */
	
	private static MessageQueueModule messageQueueModule = new MessageQueueModule(System.getProperties());
	private static IocModule iocModule = new IocModule();
	private static CacheModule cacheModule = new CacheModule();
	/*
	 * (non-Javadoc) 
	 * 
	 * @see com.google.inject.servlet.ServletModule#configureServlets()
	 */
	@Override
	protected void configureServlets() {
		
		install(messageQueueModule);
		install(iocModule);
		install(cacheModule);
		
		log.info(this.getServletContext().getClass().getCanonicalName());
		
		/** DISPATCH */
		serve("/*").with(HttpProxyServlet.class);
		bind(HttpProxyServlet.class).in(Scopes.SINGLETON);
	}
	
}
