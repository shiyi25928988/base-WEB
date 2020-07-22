package base.module;

import java.io.IOException;
import java.util.Properties;

import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import lombok.extern.slf4j.Slf4j;
import mq.base.module.MessageQueueModule;

@Slf4j
public abstract class ModulesConfig {

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
	
	private static DataSourceModule dataSourceModule = new DataSourceModule();
	private static MessageQueueModule messageQueueModule = new MessageQueueModule(messageQueueProp);
	private static IocModule iocModule = new IocModule();
	private static ServletModule servletModule = new ServletModule();
	private static CacheModule cacheModule = new CacheModule();
	private static EventBusModule eventBusModule = new EventBusModule();
	
	private static com.google.inject.Module[] modules = {
			dataSourceModule,
			messageQueueModule,
			iocModule,
			cacheModule,
			eventBusModule
	} ;
	
	private static com.google.inject.Module[] allModules = {
			servletModule,
			dataSourceModule,
			messageQueueModule,
			iocModule,
			cacheModule,
			eventBusModule
	} ;
	
	/**
	 * @return Module[]
	 */
	public static com.google.inject.Module[] getModules() {
		return modules;
	}
	
	public static com.google.inject.Module[] getAllModules() {
		return allModules;
	}
}
