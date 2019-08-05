package base.module;

import com.google.inject.Scopes;

import base.filter.SecureFilter;
import base.servlet.DispatcherServlet;

/**
 * @author yshi
 *
 */
public class ServletModule extends com.google.inject.servlet.ServletModule{
	
	/* (non-Javadoc)
	 * @see com.google.inject.servlet.ServletModule#configureServlets()
	 */
	@Override
	protected void configureServlets() {
		
		install(new SecurityModule(this.getServletContext()));
		
		filter("/*").through(SecureFilter.class);
		bind(SecureFilter.class).in(Scopes.SINGLETON);
		
		/**DISPATCH*/
		serve("/*").with(DispatcherServlet.class);
		bind(DispatcherServlet.class).in(Scopes.SINGLETON);
	}
}
