package html.base.element;

import j2html.attributes.Attribute;
import j2html.tags.EmptyTag;

/**
 * @author yshi
 *
 */
public class Img extends EmptyTag implements HtmlElement<Img>{
	
	private String id;
	private String name;

	/**
	 * 
	 */
	public Img() {
		super("img");
	}
	
	public Img setSrc(String srcUrl) {
		this.withSrc(srcUrl);
		return this;
	}
	
	/**
	 * The alt attribute provides alternative information for an image if a user for some reason cannot view it.
	 * @param alt
	 * @return
	 */
	public Img setAlt(String alt) {
		this.withAlt(alt);
		return this;
	}

	@Override
	public Img setClasses(String... classes) {
		this.withClasses(classes);
		return this;
	}

	@Override
	public Img setName(String name) {
		this.withName(name);
		this.name = name;
		return this;
	}

	@Override
	public Img setId(String id) {
		this.withId(id);
		this.id = id;
		return this;
	}

	@Override
	public Img addAttribute(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

	@Override
	public Img hide(boolean condition) {
		this.isHidden();
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
