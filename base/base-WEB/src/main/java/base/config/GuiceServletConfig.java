package base.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import base.module.IocModule;
import base.module.Modules;
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
			injector = Guice.createInjector(Modules.getModules());
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
