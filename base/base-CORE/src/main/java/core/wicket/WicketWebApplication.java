package core.wicket;

import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.csp.CSPRequestCycleListener;
import org.apache.wicket.csp.ContentSecurityPolicySettings;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.pages.BrowserInfoPage;
import org.apache.wicket.request.cycle.IRequestCycleListener;

import core.module.ModuleRegister;
import core.wicket.pages.homepage.HomePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WicketWebApplication extends org.apache.wicket.protocol.http.WebApplication{
	
	/**
	 * @see org.apache.wicket.Application#getHomePage() 
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		log.info("WicketRootApplication init...");
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, ModuleRegister.getModulesAsArray()));
		
		ContentSecurityPolicySettings contentSecurityPolicySettings = this.getCspSettings();
		contentSecurityPolicySettings.blocking().clear()
			.add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF);
		
		
		
	}
}
