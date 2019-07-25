package html.base.element;

import j2html.tags.ContainerTag;

public class Button extends ContainerTag{

	private Button(String tagName) {
		super(tagName);
	}
	
	public Button() {
		this("button");
	}
	
	public Button setClass(String className) {
		this.withClass(className);
		return this;
	}

	public Button setName(String name) {
		this.withName(name);
		return this;
	}

	public Button setId(String id) {
		this.withId(id);
		return this;
	}

	public Button setText(String text) {
		this.withText(text);
		return this;
	}

	public Button setType(String type) {
		this.withType(type);
		return this;
	}

}
