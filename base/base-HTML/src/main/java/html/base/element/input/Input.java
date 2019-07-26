package html.base.element.input;

import html.base.element.HtmlElement;
import j2html.tags.EmptyTag;

public abstract class Input extends EmptyTag implements HtmlElement<Input>{

	private Input(String tagName) {
		super(tagName);
	}
	
	public Input() {
		this("input");
	}

}
