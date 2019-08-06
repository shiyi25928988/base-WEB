package html.base.element.h;

import html.base.element.HtmlElement;
import j2html.attributes.Attribute;
import j2html.tags.ContainerTag;

/**
 * @author yshi
 *
 */
public abstract class H extends ContainerTag implements HtmlElement<H>{

	/**
	 * @param tagName
	 */
	public H(String tagName) {
		super(tagName);
	}
	
	public H setText(String text) {
		this.withText(text);
		return this;
	}
	
	public H setDir(Direction dir) {
		switch(dir) {
		case left:
			this.withDir("ltr");
			break;
		case right:
			this.withDir("rtl");
			break;
			default:
		}
		return this;
	}

	@Override
	public H setClasses(String... classes) {
		this.withClasses(classes);
		return this;
	}

	@Override
	public H addAttribute(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

	@Override
	public H hide(boolean condition) {
		if(condition) {
			this.isHidden();
		}
		return this;
	}
	
	public enum Direction{
		left,
		right
	}

}
