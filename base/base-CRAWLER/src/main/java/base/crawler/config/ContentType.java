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
		
		typeMap.put("application/x-freearc", ".arc");
		
		typeMap.put("video/x-msvideo", ".avi");
		
		typeMap.put("application/vnd.amazon.ebook", ".azw");
		  
		typeMap.put("application/octet-stream", ".bin");
		
		typeMap.put("image/bmp", ".bmp");
		
		typeMap.put("application/x-bzip", ".bz");
		
		typeMap.put("application/x-bzip2", ".bz2");
		
		typeMap.put("application/x-csh", ".csh");
		
		typeMap.put("text/css", ".css"); 
		
		typeMap.put("text/csv", ".csv"); 
		
		typeMap.put("application/msword", ".doc"); 
		
		typeMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx"); 
		
		typeMap.put("application/vnd.ms-fontobject", ".eot");
		
		typeMap.put("application/epub+zip", ".epub");
		
		typeMap.put("image/gif", ".gif");
		
		typeMap.put("text/html", ".html");
		
		typeMap.put("image/vnd.microsoft.icon", ".ico");
		
		typeMap.put("text/calendar", ".ics");
		
	}

	

}
