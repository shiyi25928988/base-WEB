package core.module;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.Scopes;

import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import core.filter.SecureFilter;
import core.servlet.DispatcherServlet;
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
	
	private static DataSourceModule dataSourceModule = new DataSourceModule();
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
		
		install(dataSourceModule);
		install(messageQueueModule);
		install(iocModule);
		install(cacheModule);
		
		log.info(this.getServletContext().getClass().getCanonicalName());
		
		install(new SecurityModule(this.getServletContext()));

		filter("/sec/*").through(SecureFilter.class);
		bind(SecureFilter.class).in(Scopes.SINGLETON);
		
		/** DISPATCH */
		serve("/*").with(DispatcherServlet.class);
		bind(DispatcherServlet.class).in(Scopes.SINGLETON);
	}
	
}
