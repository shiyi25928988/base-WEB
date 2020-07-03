package custom.business;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import db.base.entity.User;
import lego.annotation.Controller;
import lego.rest.result.HTML;
import lego.rest.result.JSON;

@Controller
public class ApiTest {

	@GET
	@Path(value = "/test")
	public JSON<String> getUserByID() {
		return new JSON<String>("test");
	}
}
