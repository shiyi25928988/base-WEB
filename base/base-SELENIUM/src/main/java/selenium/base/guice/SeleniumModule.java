package selenium.base.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import selenium.base.script.DefaultScriptParser;
import selenium.base.script.IScriptParser;

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
