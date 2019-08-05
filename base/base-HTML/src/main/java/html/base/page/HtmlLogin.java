package html.base.page;

import static j2html.TagCreator.*;

import html.base.element.button.Button;
import html.base.element.button.ButtonType;
import html.base.element.input.PasswordInput;
import html.base.head.Head;
import html.base.head.Link;
import html.base.head.Meta;
import html.base.head.Style;
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
		Head head = new Head()
				.addDomContent(new Meta().attr("charset", "UTF-8"))
				.addDomContent(new Meta().attr("http-equiv", "X-UA-Compatible").attr("content", "IE=edge"))
				.addDomContent(new Meta().attr("name", "viewport").attr("content", "width=device-width, initial-scale=1, shrink-to-fit=no"))
				.addDomContent(new Link().withRel("stylesheet")
					  .withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css")
					  .attr(new Attribute("integrity", "sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"))
					  .attr(new Attribute("crossorigin", "anonymous")))
				.addDomContent(new Style().addStyle(
						"      .bd-placeholder-img {\r\n" + 
						"        font-size: 1.125rem;\r\n" + 
						"        text-anchor: middle;\r\n" + 
						"        -webkit-user-select: none;\r\n" + 
						"        -moz-user-select: none;\r\n" + 
						"        -ms-user-select: none;\r\n" + 
						"        user-select: none;\r\n" + 
						"      }\r\n" + 
						"      @media (min-width: 768px) {\r\n" + 
						"        .bd-placeholder-img-lg {\r\n" + 
						"          font-size: 3.5rem;\r\n" + 
						"        }}" ))
				.addDomContent(new Link().withRel("stylesheet")
					  .withHref("https://getbootstrap.com/docs/4.3/examples/sign-in/signin.css"));
			return head;
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
				new PasswordInput()
					.setClasses("form-control")
					.setPlaceHolder("Password")
					.setId("inputPassword")
					.setName("PASS_WORD"),
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
				
				new html.base.element.input.Button().setButtonText("login").setType(ButtonType.submit).setClasses("btn btn-lg btn-primary btn-block"),

				p()
					.withClass("mt-5 mb-3 text-muted")
					.withText("© 2017-2019")
				).withClass("form-signin").withAction(".." + contexPath + "/login").withMethod("post");
	}
	
	public ContainerTag javaScript() {
		return script();
	}

}
