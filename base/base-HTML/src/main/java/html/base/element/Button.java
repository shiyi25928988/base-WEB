package html.base.element;

import j2html.tags.ContainerTag;

public class Button extends ContainerTag{

	private Button(String tagName) {
		super(tagName);
	}
	
	public Button() {
		this("button");
	}
	
	public Button setClasses(String...classes) {
		this.withClasses(classes);
		return this;
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
	
	public Button setAutoFocus() {
		this.isAutoFocus();
		return this;
	}
	
	public Button setTitle(String title) {
		this.withTitle(title);
		return this;
	}

	public Button setType(ButtonType type) {
		switch(type) {
		case submit:
			this.withType("submit");
			break;
		case button:
			this.withType("button");
			break;
		case reset:
			this.withType("reset");
			break;
		}
		return this;
	}
	
	public enum ButtonType{
		submit,
		button,
		reset
	}
	

}
