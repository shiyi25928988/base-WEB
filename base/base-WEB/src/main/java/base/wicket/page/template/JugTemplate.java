package base.wicket.page.template;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import base.wicket.panel.FooterPanel;
import base.wicket.panel.HeaderPanel;
import base.wicket.panel.MenuPanel;

/**
 * @author yshi
 *
 */
public class JugTemplate extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String CONTENT_ID = "contentComponent";
	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;

	public JugTemplate() {
		add(headerPanel = new HeaderPanel("headerPanel"));
		add(menuPanel = new MenuPanel("menuPanel"));
		add(footerPanel = new FooterPanel("footerPanel"));
		add(new Label(CONTENT_ID, "Put your content here"));
	}

}
