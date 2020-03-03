package base.jetty.service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import com.google.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
@Singleton
public class JettyBootServiceImpl implements JettyBootService{
	
	private Server server;
	
	/**
	 *
	 */
	@Override
	public void start(int port) {
		log.info("start");
		try {
			server = ServerFactory.buildServer(port, new ServletHandler());
			server.start();
			server.dumpStdErr();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	@Override
	public void stop() {
		log.info("stop");
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setContext(String path) {
		// TODO Auto-generated method stub
		
	}

}
