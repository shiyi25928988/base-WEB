package dl.magnet;

import java.nio.file.Path;
import java.nio.file.Paths;

import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.Config;

public class MagnetDownloadService {

	
	
	public static void main(String...strings) {
		
		// enable multithreaded verification of torrent data
		Config config = new Config() {
		    @Override
		    public int getNumOfHashingThreads() {
		        return Runtime.getRuntime().availableProcessors() * 2;
		    }
		};

		// enable bootstrapping from public routers
		com.google.inject.Module dhtModule = new DHTModule(new DHTConfig() {
		    @Override
		    public boolean shouldUseRouterBootstrap() {
		        return true;
		    }
		});
		
		
		Path targetDirectory = Paths.get("D:\\Downloads");
				//Paths.get(System.getProperty("user.home"), "Downloads");
		
		// create file system based backend for torrent data
		Storage storage = new FileSystemStorage(targetDirectory);

		// create client with a private runtime
		BtClient client = Bt.client()
		        .config(config)
		        .storage(storage)
		        .magnet("magnet:?xt=urn:btih:54729b8fd70a8575233f0567d935fdf77140226a&tr=http://tr.cili001.com:8070/announce&tr=udp://p4p.arenabg.com:1337&tr=udp://tracker.opentrackr.org:1337/announce&tr=udp://open.demonii.com:1337")
		        .autoLoadModules()
		        .module(dhtModule)
		        .stopWhenDownloaded()
		        .build();

		// launch
		client.startAsync(torrentSessionState ->{
			
			torrentSessionState.getPiecesTotal();
			
		}, 1000L).join();
	}
}
