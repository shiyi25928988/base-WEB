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
import dl.magnet.async.DownLoadExcutors;
import dl.magnet.async.DownLoadTask;

public class DownLoadServiceImpl implements DownLoadService{
	
	private static Map<String, DownLoadStatus> statusMap = new ConcurrentHashMap<>();

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
	public void download(String magnetUrl) {
		
		updateStatus(magnetUrl, null);
		DownLoadExcutors.submit(new DownLoadTask(magnetUrl, storagePath, config, dhtModule));
		return;
		
	}
	
	@Override
	public DownLoadStatus getDownLoadStatus(String magnetUrl) {
		// TODO Auto-generated method stub
		return statusMap.get(magnetUrl);
	}
	
	public static void updateStatus(String magnetUrl, TorrentSessionState torrentSessionState) {
		
		if(Objects.isNull(torrentSessionState)) {
			if(statusMap.containsKey(magnetUrl)) {
				return;
			}
			DownLoadStatus status = new DownLoadStatus();
			statusMap.put(magnetUrl, status);
		}
		
		DownLoadStatus status = statusMap.getOrDefault(magnetUrl, new DownLoadStatus());
		status.setCompletePieces(torrentSessionState.getPiecesComplete())
			.setDownloaded(torrentSessionState.getDownloaded())
			.setIncompletePieces(torrentSessionState.getPiecesIncomplete())
			.setSkippedPieces(torrentSessionState.getPiecesSkipped())
			.setRemainingPieces(torrentSessionState.getPiecesRemaining())
			.setNotSkippedPieces(torrentSessionState.getPiecesNotSkipped())
			.setUploaded(torrentSessionState.getUploaded())
			.setTotalPieces(torrentSessionState.getPiecesTotal());
			
		statusMap.put(magnetUrl, status);
		
	}

}
