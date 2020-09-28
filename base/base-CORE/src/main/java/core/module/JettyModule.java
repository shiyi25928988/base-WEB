package core.module;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;

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

	private static class ServletContextProvider implements Provider<ServletContext> {
		@Inject
		ServletContextHandler servletContextHandler;

		@Override
		public ServletContext get() {
			return servletContextHandler.getServletContext();
		}
	}

	private static class ServerProvider implements Provider<Server> {
		
		private int port;

		@Inject
		ServletContextHandler servletContextHandler;

		@Override
		public Server get() {

			HandlerList handlerList = new HandlerList();
			{
				servletContextHandler.setContextPath("/");
				servletContextHandler.addServlet(DispatcherServlet.class, "/*");
				servletContextHandler.addFilter(com.google.inject.servlet.GuiceFilter.class, "/*",
						EnumSet.of(DispatcherType.REQUEST));
				servletContextHandler.getServletHandler()
						.addListener(new ListenerHolder(GuiceServletCustomContextListener.class));
				handlerList.addHandler(servletContextHandler);
			}
			{
				ServletContextHandler resourceHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
				resourceHandler.setContextPath("/static/");
				resourceHandler.insertHandler(getResourceHandler());
				handlerList.prependHandler(resourceHandler);
			}

			port = Integer.parseInt(System.getProperty("application.port", "8080"));
			
			Server server = new Server(port);
			server.setStopAtShutdown(true);
			server.setHandler(handlerList);
			return server;
		}

		private ResourceHandler getResourceHandler() {

			String dir = System.getProperty("user.dir");
			try {
				Resource res = Resource.newResource(dir + "\\META-INF\\resources", false);
				ResourceHandler resourceHandler = new ResourceHandler();
				resourceHandler.setDirectoriesListed(true);
				resourceHandler.setBaseResource(res);
				// resourceHandler.setDirAllowed(true);
				return resourceHandler;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}
	}

}
