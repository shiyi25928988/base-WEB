package base.crawler.config;

import java.util.Hashtable;
import java.util.Map;

import base.crawler.exceptions.ExtendTypeNotFoundException;

/**
 * @author yshi
 * https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
 */
public abstract class ContentType {
	
	private static Map<String, String> typeMap = new Hashtable<>();
	
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
		
		typeMap.put("text/html; charset=utf-8", ".html");
		
		typeMap.put("image/vnd.microsoft.icon", ".ico");
		
		typeMap.put("image/x-icon", ".ico");
		
		typeMap.put("text/calendar", ".ics");
		
		typeMap.put("application/java-archive", ".jar");
		
		typeMap.put("image/jpeg", ".jpg");
		
		typeMap.put("text/javascript", ".js");
		
		typeMap.put("application/javascript", ".js");
		
		typeMap.put("application/json", ".json");
		
		typeMap.put("application/ld+json", ".jsonld");
		
		typeMap.put("audio/midi", ".mid");
		
		typeMap.put("audio/x-midi", ".midi");
		
		typeMap.put("audio/mpeg", ".mp3");
		
		typeMap.put("video/mpeg", ".mpeg");
		
		typeMap.put("application/vnd.apple.installer+xml", ".mpkg");
		
		typeMap.put("application/vnd.oasis.opendocument.presentation", ".odp");
		
		typeMap.put("application/vnd.oasis.opendocument.spreadsheet", ".ods");
		
		typeMap.put("application/vnd.oasis.opendocument.text", ".odt");
		
		typeMap.put("audio/ogg", ".oga");
		
		typeMap.put("video/ogg", ".ogv");
		
		typeMap.put("application/ogg", ".ogx");
		
		typeMap.put("font/otf", ".otf");
		
		typeMap.put("image/png", ".png");
		
		typeMap.put("application/pdf", ".pdf");
		
		typeMap.put("application/vnd.ms-powerpoint", ".ppt");
		
		typeMap.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", ".pptx");
		
		typeMap.put("application/x-rar-compressed", ".rar");
		
		typeMap.put("application/rtf", ".rtf");
		
		typeMap.put("application/x-sh", ".sh");
		
		typeMap.put("image/svg+xml", ".svg");
		
		typeMap.put("application/x-shockwave-flash", ".swf");
		
		typeMap.put("application/x-tar", ".tar");
		
		typeMap.put("image/tiff", ".tiff");
		
		typeMap.put("font/ttf", ".ttf");

		typeMap.put("text/plain", ".txt");
		
		typeMap.put("application/vnd.visio", ".vsd");
		
		typeMap.put("audio/wav", ".wav");
		
		typeMap.put("audio/webm", ".weba");
		
		typeMap.put("video/webm", ".webm");
		
		typeMap.put("image/webp", ".webp");
		
		typeMap.put("font/woff", ".woff");
		
		typeMap.put("font/woff2", ".woff2");
		
		typeMap.put("application/xhtml+xml", ".xhtml");
		
		typeMap.put("application/vnd.ms-excel", ".xls");
		
		typeMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
		
		typeMap.put("application/xml", ".xml");
		
		typeMap.put("text/xml", ".xml");
		
		typeMap.put("application/vnd.mozilla.xul+xml", ".xul");
		
		typeMap.put("application/zip", ".zip");
		
		typeMap.put("video/3gpp", ".3gp");
		
		typeMap.put("audio/3gpp", ".3gp");
		
		typeMap.put("video/3gpp2", ".3g2");
		
		typeMap.put("audio/3gpp2", ".3g2");
		
		typeMap.put("application/x-7z-compressed", ".7z");
		
	}
	
	/**
	 * @param contentType
	 * @return
	 * @throws ExtendTypeNotFoundException 
	 */
	public static String getExtend(String contentType) throws ExtendTypeNotFoundException {
		if(!typeMap.containsKey(contentType))
			throw new ExtendTypeNotFoundException(contentType + " not specified the extend type.");
		return typeMap.get(contentType);
	}

}
