package base.wicket.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import db.base.service.UserService;

import org.apache.wicket.markup.html.basic.Label;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	@Inject
	UserService userService;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		//add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
		
		add(new Label("version", userService.getUser("002").get().getUserName()));

		// TODO Add your page's components here

	}
}
