package base.wicket;

import java.io.IOException;
import java.util.Properties;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import base.wicket.page.HomePage;
import db.base.module.DataSourceModule;
import lombok.extern.slf4j.Slf4j;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see org.yshi.Start#main(String[])
 */
@Slf4j
public class WicketApplication extends WebApplication
{
	
	//private static Properties messageQueueProp = new Properties();
	private static Properties jdbcProp = new Properties();
	static {
		try {
			//messageQueueProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mq.properties"));
			jdbcProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			log.error(e.getMessage());
			System.exit(1);
		}
	}
	
	
	/**
	 * @see org.apache.wicket.Application#getHomePage() 
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, new DataSourceModule(jdbcProp)));
		// add your configuration here
	}
}
