package dl.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;
import bt.torrent.TorrentSessionState;
import core.annotation.Controller;
import dl.magnet.async.DownLoadExcutors;
import dl.magnet.async.DownLoadTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DownLoadServiceImpl implements DownLoadService{
	
	private static Map<String, DownLoadStatus> statusMap = new ConcurrentHashMap<>();

//	@Inject
//	@Named("application.fileStoragePath")
//	String storagePath;
//	
//	@Inject
//	Config config;
//	
//	@Inject
//	DHTConfig dhtConfig;
//	
//	@Inject
//	DHTModule dhtModule;
	
	@Override
	public void download(String magnetUrl) {
		
		log.info("DownLoadServiceImpl");
		log.info(magnetUrl);
		
		log.info("updateStatus");
		//DownLoadExcutors.submit(new DownLoadTask(magnetUrl, storagePath, config, dhtModule));
		log.info("after submit");
		return;
		
	}
	
	@Override
	public DownLoadStatus getDownLoadStatus(String magnetUrl) {
		// TODO Auto-generated method stub
		return statusMap.get(magnetUrl);
	}
	
	

}
