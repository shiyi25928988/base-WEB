package html.base.page;

import static j2html.TagCreator.html;

import j2html.tags.DomContent;

/**
 * @author yshi
 *
 */
public abstract class Html {

	public abstract DomContent renderHead();
	public abstract DomContent renderBody();
	
	public String render() {
		return html(renderHead(), renderBody()).render();
	}
}
