package core.filter;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;

import com.google.inject.Inject;
import com.google.inject.Provider;

import core.wicket.WicketWebApplication;

public class WebApplicationFilter extends org.apache.wicket.protocol.http.WicketFilter {

//	@Inject
//	private Provider<WebApplication> webApplicationProvider;


	
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
	public static Map<String, String> createWicketFilterInitParams() {
		Map<String, String> wicketFilterParams = new HashMap<String, String>();
		wicketFilterParams.put(WebApplicationFilter.FILTER_MAPPING_PARAM, "/*");
		wicketFilterParams.put("applicationClassName", WicketWebApplication.class.getCanonicalName());
		return wicketFilterParams;
	}
}
