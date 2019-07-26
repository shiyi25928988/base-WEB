package html.base.element.input;

import html.base.element.HtmlElement;
import j2html.tags.EmptyTag;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Input setId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Input addAttribute(String name, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Input hide() {
		// TODO Auto-generated method stub
		return null;
	}

}
