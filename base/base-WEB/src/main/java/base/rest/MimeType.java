package base.rest;

/**
 * @author yshi
 *
 */
public enum MimeType {
	/**
	 * 
	 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
	 * */
	
	ALL("*/*"),
	
	TEXT_PLAIN("text/plain"),
	TEXT_HTML("text/html"),
	TEXT_CSS("text/css"),
	
	APPLICATION("application/*"),
	APPLICATION_JAVASCRIPT("application/javascript"),
	APPLICATION_ECMASCRIPT("application/ecmascript"),
	APPLICATION_JSON("application/json"),
	APPLICATION_XML("application/xml"),
	APPLICATION_OCTET_STREAM("application/octet-stream"),
	APPLICATION_PDF("application/pdf"),
	
	IMAGE_JPEG("image/jpeg"),
	IMAGE_PNG("image/png"),
	IMAGE_GIF("image/gif"),
	
	VIDEO_MP4("video/mp4"),
	AUDIO_MP3("audio/mpeg");
	
	String type;
	
	private MimeType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
