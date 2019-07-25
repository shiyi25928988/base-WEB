package html.base.element;

import j2html.tags.ContainerTag;

public class Button extends ContainerTag implements Element{

	private Button(String tagName) {
		super(tagName);
	}
	
	public Button() {
		this("button");
	}
	
	@Override
	public Button setClass(String className) {
		this.withClass(className);
		return this;
	}

	@Override
	public Button setName(String name) {
		this.withName(name);
		return this;
	}

	@Override
	public Element setId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element setText(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element setType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
