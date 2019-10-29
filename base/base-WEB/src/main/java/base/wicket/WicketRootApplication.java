package base.wicket;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import base.module.ModulesConfig;
import lombok.extern.slf4j.Slf4j;
import wicket.base.bootstrap.layout.BootstrapBasePage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see org.yshi.Start#main(String[])
 */
@Slf4j
public class WicketRootApplication extends WebApplication{
	
	/**
	 * @see org.apache.wicket.Application#getHomePage() 
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return BootstrapBasePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		log.info("WicketRootApplication init...");
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, ModulesConfig.getModules()));
	}
}
