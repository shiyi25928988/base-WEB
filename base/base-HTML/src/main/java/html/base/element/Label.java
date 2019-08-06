package html.base.element;

import j2html.attributes.Attribute;
import j2html.tags.ContainerTag;

/**
 * @author yshi
 *
 */
public class Label extends ContainerTag implements HtmlElement<Label>{
	
	private String id;
	private String name;

	public Label() {
		super("label");
	}
	
	public Label(HtmlElement<?> element) {
		super("label");
		this.addAttribute("for", element.getId());
	}
	
	public Label setText(String text) {
		this.withText(text);
		return this;
	}

	@Override
	public Label setClasses(String... classes) {
		this.withClasses(classes);
		return null;
	}

	@Override
	public Label setName(String name) {
		this.withName(name);
		this.name = name;
		return this;
	}

	@Override
	public Label setId(String id) {
		this.withId(id);
		this.id = id;
		return this;
	}

	@Override
	public Label addAttribute(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

	@Override
	public Label hide(boolean condition) {
		if(condition) {
			this.isHidden();
		}
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
