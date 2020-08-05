package custom.business;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import core.annotation.Controller;
import core.http.result.JSON;
import db.base.entity.User;

@Controller
public class ApiTest {

	@GET
	@Path(value = "/api/test")
	public JSON<String> getUserByID() {
		return new JSON<String>("test");
	}
}
