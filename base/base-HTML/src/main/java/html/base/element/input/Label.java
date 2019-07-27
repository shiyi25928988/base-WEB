package html.base.element.input;

import html.base.element.HtmlElement;
import j2html.tags.ContainerTag;

public class Label extends ContainerTag implements HtmlElement<Label>{

	public Label(Input input) {
		super("label");
		this.addAttribute("for", input.getId());
	}

	@Override
	public Label setClasses(String... classes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label setName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label setId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label addAttribute(String name, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label hide(boolean condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
