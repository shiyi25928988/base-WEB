package html.base.page;

import static j2html.TagCreator.*;

import j2html.tags.DomContent;

/**
 * @author yshi
 *
 */
public class IndexHtml extends Html {

	@Override
	public DomContent renderBody() {
		return iframe();
	}

}
