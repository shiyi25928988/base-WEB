package lego;

import com.google.inject.Guice;
import com.google.inject.Injector;

import lego.jetty.service.JettyBootService;
import lego.module.JettyModule;

/**
 * @author shiyi
 *
 */
public class Main {

	public static void main(String... strings) throws Exception {
		Injector injector = Guice.createInjector(new JettyModule());
		JettyBootService service = injector.getInstance(JettyBootService.class);
		service.start();
	}
}
