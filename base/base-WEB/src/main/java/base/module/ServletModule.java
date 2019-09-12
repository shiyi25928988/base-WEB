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
import base.wicket.WicketApplication;

/**
 * @author yshi
 *
 */
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
		
		filter("/*").through(WicketFilter.class, createWicketFilterInitParams());
		bind(WebApplication.class).to(WicketApplication.class);
		bind(WicketFilter.class).to(CustomWicketFilter.class).in(Scopes.SINGLETON);

		/** DISPATCH */
		serve("/*").with(DispatcherServlet.class);
		bind(DispatcherServlet.class).in(Scopes.SINGLETON);
	}

	@Singleton
	private static class CustomWicketFilter extends WicketFilter {

		@Inject
		private Provider<WebApplication> webApplicationProvider;

		@Override
		protected IWebApplicationFactory getApplicationFactory() {
			return new IWebApplicationFactory() {
				@Override
				public WebApplication createApplication(WicketFilter filter) {
					return webApplicationProvider.get();
				}

				@Override
				public void destroy(WicketFilter filter) {
				}
			};
		}
	}

	private Map<String, String> createWicketFilterInitParams() {
		Map<String, String> wicketFilterParams = new HashMap<String, String>();
		wicketFilterParams.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
		wicketFilterParams.put("applicationClassName", "base.wicket.WicketApplication");
		return wicketFilterParams;
	}
}
