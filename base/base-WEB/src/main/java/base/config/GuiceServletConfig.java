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
import lombok.extern.java.Log;
import mq.base.module.MessageQueueModule;

/**
 * @author yshi
 *
 */
@Log
public class GuiceServletConfig extends GuiceServletContextListener {
	private static Properties messageQueueProp = new Properties();
	private static Properties jdbcProp = new Properties();
	static {
		try {
			messageQueueProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mq.properties"));
			jdbcProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			log.severe("env.properties load failed");
			System.exit(1);
		}
	}

	private static volatile Injector injector;

	@Override
	protected Injector getInjector() {
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

	public static Injector getInjectorInstance() throws NullPointerException {
		if (Objects.isNull(injector))
			throw new NullPointerException();
		return injector;
	}
}
