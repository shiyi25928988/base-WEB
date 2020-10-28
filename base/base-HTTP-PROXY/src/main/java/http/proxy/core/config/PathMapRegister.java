package http.proxy.core.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import base.common.json.JsonUtils;

public class PathMapRegister {
	
	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + File.separator + "proxy.json";
	
	private static ProxyConfigs proxyConfigs;
	
	static {
		
		try {
			byte[] b = new byte[1024];
			PathMapRegister.class.getClassLoader().getResourceAsStream("proxy.json").read(b);
			
			
			System.out.println(CONFIG_FILE_PATH);
			
			proxyConfigs = JsonUtils.fromJson(b, ProxyConfigs.class);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static Map<String, String> pathMap = new ConcurrentHashMap<>();

	public static void register() {
		for(ProxyConfig config : proxyConfigs.getProxyConfigList()) {
			pathMap.put(config.getLocation(), config.getProxyPass());
		}
	}

	public static String getMapedRemoteURL(String path) {
		register();
		return pathMap.get(path);
	}
	
	public static void main(String...strings) {
		System.out.println(getMapedRemoteURL("1"));
	}

	
}
