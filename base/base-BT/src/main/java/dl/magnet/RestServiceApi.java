package dl.magnet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.inject.Inject;

import core.annotation.Controller;
import core.annotation.RequestBody;
import core.http.result.JSON;
import dl.service.DownLoadServiceImpl;

@Controller
public class RestServiceApi {
	
	@Inject
	DownLoadServiceImpl downLoadService;

	@POST
	@Path(value = "/download")
	public JSON<String> download(@RequestBody DownLoadRequest req) {
		downLoadService.download(req.getMagnet());
		return new JSON<String>("Start downloading... " + req.getMagnet());
	}
}
