package base.crawler.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yshi
 * https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
 */
public abstract class ContentType {
	
	private static Map<String, String> typeMap = new HashMap<>();
	
	static {
		typeMap.put("audio/aac", ".aac");
		
		typeMap.put("application/x-abiword", ".abw");
	}

	

}
