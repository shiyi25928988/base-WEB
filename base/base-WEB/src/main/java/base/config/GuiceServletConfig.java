package base.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import base.module.IocModule;
import base.module.ServletModule;
import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import lombok.extern.slf4j.Slf4j;
import mq.base.module.MessageQueueModule;

/**
 * @author yshi
 *
 */
@Slf4j
public class GuiceServletConfig extends GuiceServletContextListener {
	/*
	 * The properties loading 
	 * */
	private static Properties messageQueueProp = new Properties();
	private static Properties jdbcProp = new Properties();
	static {
		try {
			messageQueueProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mq.properties"));
			jdbcProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			log.error(e.getMessage());
			System.exit(1);
		}
	}

	
	/**
	 * Injector
	 */
	private static volatile Injector injector = null;

	/* (non-Javadoc)
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 */
	@Override
	protected synchronized Injector getInjector() {
		if (Objects.isNull(injector)) {
			injector = Guice.createInjector(
					new DataSourceModule(jdbcProp),
					new MessageQueueModule(messageQueueProp), 
					new IocModule(),
					new ServletModule(),
					new CacheModule());
		}
		return injector;
	}

	/**
	 * @return
	 * @throws NullPointerException
	 */
	public static Injector getInjectorInstance() throws NullPointerException {
		if (Objects.isNull(injector))
			throw new NullPointerException();
		return injector;
	}
}
