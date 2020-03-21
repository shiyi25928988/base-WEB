package base.wicket.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import cache.base.service.CacheService;
import db.base.service.UserService;

import org.apache.wicket.markup.html.basic.Label;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	@Inject
	UserService userService;
	
	@Inject
	CacheService cache;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		//add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
		
		cache.put("002", userService.getUser("002").getUserName());
		
		add(new Label("version", cache.get("002").get().toString()));

		
		// TODO Add your page's components here

	}
}
