package html.base.element;

import java.util.Arrays;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

public class Div extends ContainerTag implements HtmlElement<Div> {

	public Div() {
		super("div");
	}
	
	public Div(DomContent...dom) {
		super("div");
		Arrays.stream(dom).forEach(e->{
				this.with((DomContent)e);
		});
	}
	
	public Div addContent(DomContent...dom) {
		Arrays.stream(dom).forEach(e->{
			this.with((DomContent)e);
		});
		return this;
	}

	@Override
	public Div setClasses(String... classes) {
		this.withClasses(classes);
		return this;
	}

	@Override
	public Div setName(String name) {
		this.withName(name);
		return this;
	}

	@Override
	public Div setId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Div addAttribute(String name, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Div hide(boolean condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
