package base.jetty;

import com.google.inject.Guice;
import com.google.inject.Injector;

import base.jetty.guice.JettyModule;
import base.jetty.service.JettyBootService;

/**
 * @author shiyi
 *
 */
public class Main {

	public static void main(String... strings) throws Exception {
		Injector injector = Guice.createInjector(new JettyModule());
		JettyBootService service = injector.getInstance(JettyBootService.class);
		service.start(8080);
		
	}
}
