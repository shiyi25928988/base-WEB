package lego.module;

import java.io.IOException;
import java.util.Properties;

import org.apache.shiro.web.servlet.ShiroFilter;

import com.google.inject.Scopes;

import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import lego.filter.SecureFilter;
import lego.servlet.DispatcherServlet;
import lombok.extern.slf4j.Slf4j;
import mq.base.module.MessageQueueModule;

/**
 * @author yshi
 *
 */
@Slf4j
public class ServletModule extends com.google.inject.servlet.ServletModule {
	
	private static Properties messageQueueProp = new Properties();
	private static Properties jdbcProp = new Properties();
	
	/*
	 * Load the property files here, which stored in the folder '/base-WEB/src/main/resources' 
	 * */
	static {
		try {
			messageQueueProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mq.properties"));
			jdbcProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			log.error(e.getMessage());
			System.exit(1);
		}
	}
	
	private static DataSourceModule dataSourceModule = new DataSourceModule(jdbcProp);
	private static MessageQueueModule messageQueueModule = new MessageQueueModule(messageQueueProp);
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
