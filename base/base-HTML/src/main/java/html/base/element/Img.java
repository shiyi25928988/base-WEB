package html.base.element;

import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

/**
 * @author yshi
 *
 */
public class Img extends EmptyTag implements HtmlElement<Img>{

	public Img() {
		super("img");
	}
	
	public Img setSrc(String srcUrl) {
		this.withSrc(srcUrl);
		return this;
	}
	
	public Img setAlt(String alt) {
		this.withAlt(alt);
		return this;
	}

	@Override
	public Img setClasses(String... classes) {
		this.withClasses(classes);
		return this;
	}

	@Override
	public Img setName(String name) {
		this.withName(name);
		return this;
	}

	@Override
	public Img setId(String id) {
		this.withId(id);
		return this;
	}

	@Override
	public Img addAttribute(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

	@Override
	public Img hide(boolean condition) {
		this.isHidden();
		return this;
	}

}
