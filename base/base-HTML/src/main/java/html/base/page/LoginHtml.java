package html.base.page;

import static j2html.TagCreator.*;

import j2html.attributes.Attribute;
import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

/**
 * @author yshi
 *
 */
public class LoginHtml extends Html{
	
	/**
	 * @param contexPath
	 */
	public LoginHtml(String contexPath) {
		Html.contexPath = contexPath;
	}

	@Override
	public DomContent renderBody() {
		return body(renderForm()).withClass("text-center");
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
						input().withType("checkbox").withValue("remember-me"),
						text(" Remember me")
						)
					).withClass("checkbox mb-3"),
				button()
					.withClass("btn btn-lg btn-primary btn-block")
					.withType("submit")
					.withText("Sign in"),
				p()
					.withClass("mt-5 mb-3 text-muted")
					.withText("© 2017-2019")
				).withClass("form-signin").withAction(".." + contexPath + "/login").withMethod("post");
	}
	
	public ContainerTag javaScript() {
		return script();
	}

}
