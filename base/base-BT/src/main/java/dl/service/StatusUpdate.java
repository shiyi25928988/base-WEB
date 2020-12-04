package dl.service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import bt.torrent.TorrentSessionState;

public class StatusUpdate {
	
	static Map<String, DownLoadStatus> statusMap = new ConcurrentHashMap<>();
	
	public static void updateStatus(String magnetUrl, TorrentSessionState torrentSessionState) {

		if (Objects.isNull(torrentSessionState)) {
			if (statusMap.containsKey(magnetUrl)) {
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
				.setUploaded(torrentSessionState.getUploaded()).setTotalPieces(torrentSessionState.getPiecesTotal());

		statusMap.put(magnetUrl, status);
	}
	
	public static DownLoadStatus getStatus(String magent) {
		return statusMap.getOrDefault(magent, new DownLoadStatus());
	}

}
