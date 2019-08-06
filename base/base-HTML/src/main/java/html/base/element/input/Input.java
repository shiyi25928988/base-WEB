package html.base.element.input;

import html.base.element.HtmlElement;
import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

/**
 * @author yshi
 *
 */
public abstract class Input extends EmptyTag implements HtmlElement<Input>{
	
	private String id;
	private String name;
	
	/**
	 * 
	 */
	public Input() {
		super("input");
	}
	
	Input setInputType(InputType type) {
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
		this.name = name;
		return this;
	}

	@Override
	public Input setId(String id) {
		this.withId(id);
		this.id = id;
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
	
	public Input setPlaceHolder(String placeholder) {
		this.withPlaceholder(placeholder);
		return this;
	}
	
	public Input setText(String text) {
		this.withValue(text);
		return this;
	}
	
	public Input required() {
		this.isRequired();
		return this;
	}
	
	public Input hide() {
		this.isHidden();
		return this;
	}
	
	public Input autoFocus() {
		this.isAutoFocus();
		return this;
	}
	
	public Input autoComplete() {
		this.isAutoComplete();
		return this;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
