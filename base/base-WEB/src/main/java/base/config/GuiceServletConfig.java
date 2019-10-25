package base.config;

import java.util.Objects;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import base.module.Modules;
import lombok.extern.slf4j.Slf4j;

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
			injector = Guice.createInjector(Modules.getAllModules());
		}
		return injector;
	}

	/**
	 * @return
	 * @throws NullPointerException
	 */
	public static Injector getInjectorInstance() throws NullPointerException {
		if (Objects.isNull(injector)) {
			log.error("Injector is null...");
			throw new NullPointerException();
		}
		return injector;
	}
}
