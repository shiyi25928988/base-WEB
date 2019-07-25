package html.base.element;

import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

public class Meta extends EmptyTag{

	private Meta(String tagName) {
		super(tagName);
	}
	
	public Meta() {
		this("meta");
	}
	
	public void attr(String name, String value) {
		this.attr(new Attribute(name, value));
	}

}
