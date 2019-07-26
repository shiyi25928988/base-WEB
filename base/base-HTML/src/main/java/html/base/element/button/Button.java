package html.base.element.button;

import html.base.element.HtmlElement;
import j2html.attributes.Attribute;
import j2html.tags.ContainerTag;

/**
 * @author yshi
 *
 */
public class Button extends ContainerTag implements HtmlElement<Button>{

	private Button(String tagName) {
		super(tagName);
	}
	
	public Button() {
		this("button");
	}
	
	@Override
	public Button setClasses(String...classes) {
		this.withClasses(classes);
		return this;
	}
	
	@Override
	public Button setName(String name) {
		this.withName(name);
		return this;
	}

	@Override
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
	
	public Button addAttribute(String name, String value) {
		this.attr(new Attribute(name, value));
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

	@Override
	public Button hide(boolean condition) {
		if(condition) {
			this.isHidden();
		}
		return this;
	}

	

}
