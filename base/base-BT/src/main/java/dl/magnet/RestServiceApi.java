package dl.magnet;

import java.nio.file.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.inject.Inject;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;
import core.annotation.Controller;
import core.annotation.RequestBody;
import core.http.result.JSON;
import dl.service.DownLoadServiceImpl;
import dl.service.DownLoadStatus;
import dl.service.StatusUpdate;
import dl.service.DLService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RestServiceApi {
	
	@Inject
	DLService dlService;

	@POST
	@Path(value = "/download")
	public JSON<String> download(@RequestBody DownLoadRequest req) {
		dlService.download(req.getMagnet());
		
		return new JSON<String>("Start downloading... " + req.getMagnet());
	}
	
	@POST
	@Path(value = "/status")
	public JSON<DownLoadStatus> getStatus(@RequestBody DownLoadRequest req) {
		return new JSON<DownLoadStatus>(StatusUpdate.getStatus(req.getMagnet()));
		
	}
}
