package base.jetty.service;

import com.google.inject.ImplementedBy;

/**
 * @author shiyi
 *
 */
@ImplementedBy(value=JettyBootServiceImpl.class)
public interface JettyBootService {
	void setContext(String path);
	void start(int port);
	void stop();
}
