package org.yshi.server;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;
import net.customware.gwt.dispatch.server.guice.ServerDispatchModule;

/**
 * @author yshi
 *
 */
@Slf4j
public class BootstrapListener extends com.google.inject.servlet.GuiceServletContextListener{
	
	/* (non-Javadoc)
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 */
	@Override
	protected Injector getInjector() {
		log.info("BootstrapListener getInjector...");
		Injector injector = Guice.createInjector(new ServerDispatchModule(), new ActionHandlerModule(), new DispatchServletModule());
		return injector;
	}

}
