package html.base.head;

import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

public class Meta extends EmptyTag{

	public Meta() {
		super("meta");
	}
	
	public void attr(String name, String value) {
		this.attr(new Attribute(name, value));
	}

}
