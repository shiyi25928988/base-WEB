package core.wicket.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

public class BootstrapBasePage extends WebPage
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BootstrapBasePage() 
	{
		super();
	}

	public BootstrapBasePage(IModel<?> model) 
	{
		super(model);
	}

	public BootstrapBasePage(PageParameters parameters) 
	{
		super(parameters);
	}

	@Override
	protected void onInitialize() 
	{
		super.onInitialize();
		
		BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>("sources",
			SourcesPage.class, SourcesPage.generatePageParameters(this));
		add(link);

		PopupSettings settings = new PopupSettings("sources", PopupSettings.RESIZABLE);
		settings.setWidth(800);
		settings.setHeight(600);
		link.setPopupSettings(settings);
		
		final String packageName = getClass().getPackage().getName();
		add(new Label("mainNavigation", Strings.afterLast(packageName, '.')));
	}
}
