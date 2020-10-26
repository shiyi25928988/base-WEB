package http.proxy.core.servlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PathMapRegister {

	private static Map<String, String> pathMap = new ConcurrentHashMap<>();
	
	public static void registerFor(String path, String remoteURL) {
		pathMap.put(path, remoteURL);
	}
	
	public static String getMapedRemoteURL(String path) {
		return pathMap.get(path);
	}
}
