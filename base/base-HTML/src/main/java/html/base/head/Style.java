package html.base.head;

import j2html.tags.ContainerTag;

/**
 * @author yshi
 *
 */
public class Style extends ContainerTag{

	public Style() {
		super("style");
		this.withType("	text/css");
	}
	
	/**
	 * @param text
	 * @return
	 */
	public Style addStyle(String text) {
		this.withText(text + "\r\n");
		return this;
	}

}
