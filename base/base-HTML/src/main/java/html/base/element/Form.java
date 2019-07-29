package html.base.element;

import html.base.exceptions.HttpMethodNotSupportException;
import j2html.attributes.Attribute;
import j2html.tags.ContainerTag;

public class Form extends ContainerTag {
	
	public Form() {
		super("form");
		this.attr(new Attribute("enctype","text/plain"));
	}

	private String formName;
	private String actionUrl;
	private String method;
	private String enctype="text/plain";
	private boolean novalidate = false;
	private FormTarget target;
	
	
	public Form setName(String formName) {
		this.formName = formName;
		this.withName(formName);
		return this;
	}
	
	public Form setAction(String actionUrl) {
		this.withAction(actionUrl);
		return this;
	}
	
	public Form setHttpMethod(HttpMethod method) {
		switch (method){
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
	
	
	
	public enum FormTarget{
		blank("_blank"),
		self("_self"),
		parent("_parent"),
		top("_top");
		
		public final String target;
		
		FormTarget(String target) {
			this.target = target;
		}
	}



	
}
