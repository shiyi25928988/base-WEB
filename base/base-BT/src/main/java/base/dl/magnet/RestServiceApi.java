package base.dl.magnet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import core.annotation.Controller;
import core.http.result.JSON;

@Controller
public class RestServiceApi {

	
	@GET
	@Path(value = "/download")
	public JSON<String> download(@PathParam(value = "magnet") String name) {
		return new JSON<String>("Hello " + name);
	}
}
