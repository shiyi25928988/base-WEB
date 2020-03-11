package base.jetty.guice;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.common.collect.Streams;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import base.module.ModulesConfig;
import base.servlet.DispatcherServlet;
import base.servlet.GuiceServletCustomContextListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class JettyModule extends AbstractModule {

	protected void configure() {
		Arrays.asList(ModulesConfig.getAllModules()).forEach(module->{
			log.info(module.getClass().toString());
			install(module);
		});
		bind(Server.class).toProvider(ServerProvider.class).in(Singleton.class);
	}

	private static class ServerProvider implements Provider<Server> {

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		@Override
		public Server get() {
			context.setContextPath("/");
			context.addServlet(DispatcherServlet.class, "/*");
			context.addFilter(com.google.inject.servlet.GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
			context.getServletHandler().addListener(new ListenerHolder(GuiceServletCustomContextListener.class));
			Server server = new Server(8080);
			server.setStopAtShutdown(true);
			server.setHandler(context);
			return server;
		}
	}

}
