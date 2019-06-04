package base.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import base.module.RESTfulModule;
import base.module.ServletModule;
import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import lombok.extern.java.Log;
import mq.base.module.MessageQueueModule;

@Log
public class GuiceServletConfig extends GuiceServletContextListener {
	private static Properties properties = new Properties();
	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("env.properties"));
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
					new DataSourceModule(),
					new MessageQueueModule(properties), 
					new RESTfulModule(),
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
