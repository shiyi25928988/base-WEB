package org.yshi.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatchServletModule extends com.google.inject.servlet.ServletModule{

	@Override
	public void configureServlets() {
		log.info("DispatchServletModule configureServlets...");
		
		serve("/*").with(net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet.class);
	}
}
