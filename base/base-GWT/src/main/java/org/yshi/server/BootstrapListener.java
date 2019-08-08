package org.yshi.server;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.customware.gwt.dispatch.server.guice.ServerDispatchModule;

/**
 * @author yshi
 *
 */
public class BootstrapListener extends com.google.inject.servlet.GuiceServletContextListener{
	
	private static Injector injector = Guice.createInjector(new ServerDispatchModule(), new ActionHandlerModule(), new DispatchServletModule());

	/* (non-Javadoc)
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 */
	@Override
	protected Injector getInjector() {
		return injector;
	}

}
