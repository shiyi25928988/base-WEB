package html.base.page;

import static j2html.TagCreator.*;

import html.base.element.Button;
import j2html.attributes.Attribute;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

public class HtmlLogin {
	
	private final String contexPath;
	
	public HtmlLogin(String contexPath) {
		this.contexPath = contexPath;
	}

	public String render() {
	
		return html(
				renderHead(),
				renderBody()
				).render();
	}
	
	public DomContent renderHead() {
		return head(
				meta().attr(new Attribute("charset", "UTF-8")),
				meta().attr(new Attribute("http-equiv", "X-UA-Compatible"))
					  .attr(new Attribute("content", "IE=edge")),
				meta().attr(new Attribute("name", "viewport"))
					  .attr(new Attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no")),
				link().withRel("stylesheet")
					  .withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css")
					  .attr(new Attribute("integrity", "sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"))
					  .attr(new Attribute("crossorigin", "anonymous")),
				style("\r\n" + 
						"      .bd-placeholder-img {\r\n" + 
						"        font-size: 1.125rem;\r\n" + 
						"        text-anchor: middle;\r\n" + 
						"        -webkit-user-select: none;\r\n" + 
						"        -moz-user-select: none;\r\n" + 
						"        -ms-user-select: none;\r\n" + 
						"        user-select: none;\r\n" + 
						"      }\r\n" + 
						"\r\n" + 
						"      @media (min-width: 768px) {\r\n" + 
						"        .bd-placeholder-img-lg {\r\n" + 
						"          font-size: 3.5rem;\r\n" + 
						"        }\r\n" + 
						"      }\r\n" + 
						"    "),
				link().withRel("stylesheet")
					  .withHref("https://getbootstrap.com/docs/4.3/examples/sign-in/signin.css")
			);
	}
	
	public DomContent renderBody() {
		return body(
				renderForm()
				).withClass("text-center");
		
	}
	
	public DomContent renderForm() {
		return form(
				img()
					.withClass("mb-4")
					.withSrc(".."+ contexPath +"/image/pussy.png")
					.withAlt("login")
					.attr(new Attribute("width","72"))
					.attr(new Attribute("height", "72")),
				h1()
					.withClass("h3 mb-3 font-weight-normal")
					.withText("Please sign in"),
				label()
					.withClass("sr-only")
					.withText("Email address")
					.attr(new Attribute("for", "inputEmail")),
				input()
					.withType("text")
					.withName("USER_NAME")
					.withId("inputUserName")
					.withClass("form-control")
					.withPlaceholder("User Name")
					.isRequired()
					.isAutoFocus().withValue(""),
				label()
					.withClass("sr-only")
					.withText("Password")
					.attr(new Attribute("for", "inputPassword")),
				input()
					.withType("password")
					.withName("PASS_WORD")
					.withId("inputPassword")
					.withClass("form-control")
					.withPlaceholder("Password")
					.isRequired().withValue(""),
				div(
					label(
						input()
							.withType("checkbox")
							.withValue("remember-me"),
						text(
								" Remember me"
							)
						)
					).withClass("checkbox mb-3"),
				
				new Button().setClass("btn btn-lg btn-primary btn-block").setText("Sign in").setType(Button.ButtonType.submit).setTitle("log in.."),

				p()
					.withClass("mt-5 mb-3 text-muted")
					.withText("© 2017-2019")
				).withClass("form-signin").withAction(".." + contexPath + "/login").withMethod("post");
	}
	
	public ContainerTag javaScript() {
		return script();
	}

}
