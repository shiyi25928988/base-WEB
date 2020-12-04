package dl.magnet.async;

import java.nio.file.Path;
import java.nio.file.Paths;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;
import dl.service.DownLoadServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DownLoadTask implements Runnable{

	private String magnetUrl;
	private String storagePath;
	private Config config;
	private DHTModule dhtModule;
	
	public DownLoadTask(String magnetUrl, String storagePath, Config config, DHTModule dhtModule) {
		this.config = config;
		this.dhtModule = dhtModule;
		this.magnetUrl = magnetUrl;
		this.storagePath =storagePath;
	}
	
	@Override
	public void run() {
		
		log.info("DownLoadTask submit");
		// TODO Auto-generated method stub
//		Path targetDirectory = Paths.get(storagePath);
//		Storage storage = new FileSystemStorage(targetDirectory);
//		
//		BtClient client = Bt.client()
//		        .config(config)
//		        .storage(storage)
//		        .magnet(magnetUrl)
//		        .autoLoadModules()
//		        .module(dhtModule)
//		        .stopWhenDownloaded()
//		        .build();
//		client.startAsync(torrentSessionState ->{
//			DownLoadServiceImpl.updateStatus(magnetUrl, torrentSessionState);
//			
//		}, 1000L).whenCompleteAsync((a,b) ->{
//			
//			
//			
//		});
	}

}
