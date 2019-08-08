package base.mvc.controller;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import base.annotation.Controller;
import base.rest.HtmlHelper;

/**
 * @author yshi
 *
 */
@Controller
public class CssController {
	
	@GET
	@Path(value = "/css/bootstrap.css")
	public void getBootStrapCss() throws IOException {
		HtmlHelper.sendCss("bootstrap.css");
	}
	
	@GET
	@Path(value = "/css/bootstrap.min.css")
	public void getBootStrapMinCss() throws IOException {
		HtmlHelper.sendCss("bootstrap.min.css");
	}
	
	@GET
	@Path(value = "/css/signin.css")
	public void getSigninCss() throws IOException {
		HtmlHelper.sendCss("signin.css");
	}

}
