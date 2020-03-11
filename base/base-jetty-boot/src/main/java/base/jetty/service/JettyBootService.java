package base.jetty.service;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestListener;

import com.google.inject.ImplementedBy;

/**
 * @author shiyi
 *
 */
@ImplementedBy(value=JettyBootServiceImpl.class)
public interface JettyBootService {
	
	void start();
	void stop();
}
