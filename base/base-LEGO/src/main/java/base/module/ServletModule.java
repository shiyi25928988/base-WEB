package base.module;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

import base.filter.SecureFilter;
import base.servlet.DispatcherServlet;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class ServletModule extends com.google.inject.servlet.ServletModule {

	/*
	 * (non-Javadoc) 
	 * 
	 * @see com.google.inject.servlet.ServletModule#configureServlets()
	 */
	@Override
	protected void configureServlets() {

		install(new SecurityModule(this.getServletContext()));

		filter("/*").through(SecureFilter.class);
		bind(SecureFilter.class).in(Scopes.SINGLETON);
		
		/** DISPATCH */
		serve("/*").with(DispatcherServlet.class);
		bind(DispatcherServlet.class).in(Scopes.SINGLETON);
	}

	/**
	 * WicketFilter....
	 * @author yshi
	 *
	 */
	@Singleton
	private static class CustomWicketFilter extends WicketFilter {

		@Inject
		private Provider<WebApplication> webApplicationProvider;

		/**
		 *
		 */
		@Override
		protected IWebApplicationFactory getApplicationFactory() {
			return new IWebApplicationFactory() {
				@Override
				public WebApplication createApplication(WicketFilter filter) {
					return webApplicationProvider.get();
				}

				@Override
				public void destroy(WicketFilter filter) {
					log.info("WicketFilter destroy...");
					filter.destroy();
				}
			};
		}
	}

	/**
	 * Replaced the WEB.xml configuration
	 * 
	 * <web-app>
	 * 		<display-name>Wicket Test</display-name>
	 * 		<filter>
	 * 			<filter-name>TestApplication</filter-name>
	 * 			<filter-class>org.apache.wicket.protocol.http.WicketFilter</filter-class>
	 * 			<init-param>
	 * 				<param-name>applicationClassName</param-name>
	 * 				<param-value>org.wicketTutorial.WicketApplication</param-value>
	 * 			</init-param>
	 * 		</filter>
	 * 		<filter-mapping>
	 * 			<filter-name>TestApplication</filter-name>
	 * 			<url-pattern>/*</url-pattern>
	 * 		</filter-mapping>
	 * </web-app>
	 * 
	 * @return Map
	 */
	private Map<String, String> createWicketFilterInitParams() {
		Map<String, String> wicketFilterParams = new HashMap<String, String>();
		wicketFilterParams.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
		wicketFilterParams.put("applicationClassName", "base.wicket.WicketRootApplication");
		return wicketFilterParams;
	}
}
