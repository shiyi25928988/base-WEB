package html.base.element.input;

import html.base.element.HtmlElement;
import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

/**
 * @author yshi
 *
 */
public class Input extends EmptyTag implements HtmlElement<Input>{

	private Input(String tagName) {
		super(tagName);
	}
	
	public Input() {
		this("input");
	}
	
	public Input setInputType(InputType type) {
		this.withType(type.type);
		return this;
	}

	@Override
	public Input setClasses(String... classes) {
		this.withClasses(classes);
		return this;
	}

	@Override
	public Input setName(String name) {
		this.withName(name);
		return this;
	}

	@Override
	public Input setId(String id) {
		this.withId(id);
		return this;
	}

	@Override
	public Input addAttribute(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

	@Override
	public Input hide(boolean condition) {
		if(condition) {
			this.isHidden();
		}
		return this;
	}

}
