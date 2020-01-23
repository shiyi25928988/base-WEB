package selenium.base;

import com.google.inject.Guice;
import com.google.inject.Injector;

import selenium.base.guice.SeleniumExecuteService;
import selenium.base.guice.SeleniumModule;

/**
 * @author shiyi
 *
 */
public final class Main {
	/**
	 * @param strings
	 */
	public static void main(String...strings) {
		Injector injector = Guice.createInjector(new SeleniumModule());
		SeleniumExecuteService service =  injector.getInstance(SeleniumExecuteService.class);
		service.run(strings);
	}
}
