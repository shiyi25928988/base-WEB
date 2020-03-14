package lego.jetty.service;

import org.eclipse.jetty.server.Server;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Singleton
public class JettyBootServiceImpl implements JettyBootService {

	@Inject
	private Server server;

	@Override
	public void start() {
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
