package selenium.base.guice;

import com.google.inject.AbstractModule;

/**
 * @author shiyi
 *
 */
public class SeleniumModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SeleniumExecuteService.class).to(SeleniumExecuteServiceImpl.class);
	}
}
