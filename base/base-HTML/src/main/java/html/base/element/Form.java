package html.base.element;

import java.util.Arrays;

import html.base.exceptions.HttpMethodNotSupportException;
import j2html.attributes.Attribute;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

/**
 * @author yshi
 *
 */
public class Form extends ContainerTag implements HtmlElement<Form>{

	public Form() {
		super("form");
		this.attr(new Attribute("enctype", "text/plain"));
	}

	public Form addDomContent(DomContent child) {
		this.with(child);
		return this;
	}

	public Form addDomContents(DomContent... child) {
		Arrays.stream(child).forEach(dc -> {
			this.with(dc);
		});
		return this;
	}

	public Form setAction(String actionUrl) {
		this.withAction(actionUrl);
		return this;
	}

	public Form setHttpMethod(HttpMethod method) {
		switch (method) {
		case GET:
			this.withMethod(method.methodName);
			break;
		case POST:
			this.withMethod(method.methodName);
			break;
		default:
			try {
				throw new HttpMethodNotSupportException("Form element not support " + method.methodName);
			} catch (HttpMethodNotSupportException e) {
				e.printStackTrace();
			}
		}
		return this;
	}

	public enum FormTarget {
		blank("_blank"), self("_self"), parent("_parent"), top("_top");

		public final String target;

		FormTarget(String target) {
			this.target = target;
		}
	}
	
	@Override
	public Form setName(String formName) {
		this.withName(formName);
		return this;
	}

	@Override
	public Form setClasses(String... classes) {
		this.withClasses(classes);
		return this;
	}

	@Override
	public Form setId(String id) {
		this.withId(id);
		return this;
	}

	@Override
	public Form addAttribute(String name, String value) {
		this.attr(new Attribute(name, value));
		return this;
	}

	@Override
	public Form hide(boolean condition) {
		this.isHidden();
		return this;
	}

}
