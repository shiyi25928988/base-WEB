package lego.controller;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lego.annotation.Controller;
import lego.rest.utils.RestHelper;

/**
 * @author yshi
 *
 */
@Controller
public class CssController {
	
	@GET
	@Path(value = "/css/style.css")
	public void getStyleCss() throws IOException {
		RestHelper.sendCss("style.css");
	}
	
	@GET
	@Path(value = "/css/bootstrap.css")
	public void getBootStrapCss() throws IOException {
		RestHelper.sendCss("bootstrap.css");
	}
	
	@GET
	@Path(value = "/css/bootstrap.min.css")
	public void getBootStrapMinCss() throws IOException {
		RestHelper.sendCss("bootstrap.min.css");
	}
	
	@GET
	@Path(value = "/css/signin.css")
	public void getSigninCss() throws IOException {
		RestHelper.sendCss("signin.css");
	}

}
