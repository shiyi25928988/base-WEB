package base.dl.magnet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.inject.Inject;

import base.dl.service.DownLoadService;
import core.annotation.Controller;
import core.http.result.JSON;

@Controller
public class RestServiceApi {

	@Inject
	DownLoadService downLoadService;
	
	@GET
	@Path(value = "/download")
	public JSON<String> download(@PathParam(value = "magnet") String name) {
		downLoadService.download(name);
		return new JSON<String>("Hello " + name);
	}
}
