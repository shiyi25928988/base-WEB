package base.module;

import java.io.IOException;
import java.util.Properties;

import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import lombok.extern.slf4j.Slf4j;
import mq.base.module.MessageQueueModule;

@Slf4j
public abstract class Modules {

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
	
	private static com.google.inject.Module[] modules = {
			new DataSourceModule(jdbcProp),
			new MessageQueueModule(messageQueueProp), 
			new IocModule(),
			new ServletModule(),
			new CacheModule(),
			new EventBusModule()
	} ;
	
	public static com.google.inject.Module[] getModules() {
		return modules;
	}
}
