package dl.service;

import java.nio.file.Paths;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;

public class DLServiceImpl implements DLService{
	@Inject
	@Named("application.fileStoragePath")
	String storagePath;
	
	@Inject
	Config config;
	
	@Inject
	DHTConfig dhtConfig;
	
	@Inject
	DHTModule dhtModule;
	@Override
	public void download(String magnet) {
		System.out.println(storagePath);
		System.out.println(config.toString());
		System.out.println(dhtConfig.toString());
		System.out.println(dhtModule.toString());
		
		
		Storage storage = new FileSystemStorage(Paths.get(storagePath));
		
		BtClient client = Bt.client()
		        .config(config)
		        .storage(storage)
		        .magnet(magnet)
		        .autoLoadModules()
		        .module(dhtModule)
		        .stopWhenDownloaded()
		        .build();
		client.startAsync(torrentSessionState ->{
			StatusUpdate.updateStatus(magnet, torrentSessionState);
			
		}, 1000L).whenCompleteAsync((a,b) ->{
			
			
			
		});
		
	}

}
