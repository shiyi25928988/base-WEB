package html.base.head;

import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

/**
 * @author yshi
 *
 */
public class Meta extends EmptyTag{

	public Meta() {
		super("meta");
	}
	
	/**
	 * @param name
	 * @param value
	 * @return
	 */
	public Meta attr(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

}
