package base.wicket;

import java.io.IOException;
import java.util.Properties;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import base.module.IocModule;
import base.module.ModulesConfig;
import base.module.ServletModule;
import base.wicket.page.HomePage;
import base.wicket.page.template.JugTemplate;
import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import lombok.extern.slf4j.Slf4j;

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
		return JugTemplate.class;
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
