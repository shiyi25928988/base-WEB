package core.module;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import core.servlet.DispatcherServlet;
import core.servlet.GuiceServletCustomContextListener;

/**
 * @author shiyi
 *
 */
public class JettyModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ServletContextHandler.class).toProvider(ServletContextHandlerProvider.class).in(Singleton.class);
		bind(Server.class).toProvider(ServerProvider.class).in(Singleton.class);
		bind(ServletContext.class).toProvider(ServletContextProvider.class);
	}
	

	private static class ServletContextHandlerProvider implements Provider<ServletContextHandler> {
		@Override
		public ServletContextHandler get() {
			// TODO Auto-generated method stub
			return new ServletContextHandler(ServletContextHandler.SESSIONS);
		}
	}
	
	private static class ServletContextProvider implements Provider<ServletContext>{
		@Inject
		ServletContextHandler servletContextHandler;
		
		@Override
		public ServletContext get() {
			return servletContextHandler.getServletContext();
		}
	}
	
	private static class ServerProvider implements Provider<Server> {
		
		@Inject
		ServletContextHandler servletContextHandler;
		
		@Override
		public Server get() {
			servletContextHandler.setContextPath("/");
			servletContextHandler.addServlet(DispatcherServlet.class, "/*");
			servletContextHandler.addFilter(com.google.inject.servlet.GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
			servletContextHandler.getServletHandler().addListener(new ListenerHolder(GuiceServletCustomContextListener.class));
			Server server = new Server(8080);
			server.setStopAtShutdown(true);
			server.setHandler(servletContextHandler);
			//servletContextHandler.getServletContext();
			return server;
		}
	}
	

}
