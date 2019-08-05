package html.base.head;

import java.util.Arrays;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

public class Head extends ContainerTag{

	public Head() {
		super("head");
	}
	
	public Head addDomContent(DomContent child) {
		this.with(child);
		return this;
	}
	
	public Head addDomContents(DomContent...child) {
		Arrays.stream(child).forEach(dc->{
			this.with(dc);
		});
		return this;
	}

}
