package org.yshi.server;

public class DispatchServletModule extends com.google.inject.servlet.ServletModule{

	@Override
	public void configureServlets() {
		
		serve("/*").with(net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet.class);
	}
}
