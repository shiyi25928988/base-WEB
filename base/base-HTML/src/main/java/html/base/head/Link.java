package html.base.head;

import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

/**
 * @author yshi
 *
 */
public class Link extends EmptyTag{

	public Link() {
		super("link");
	}
	
	public Link withRel(String rel) {
		this.withRel(rel);
		return this;
	}
	
	public Link withHref(String href) {
		this.withHref(href);
		return this;
	}
	
	public Link attr(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

}
