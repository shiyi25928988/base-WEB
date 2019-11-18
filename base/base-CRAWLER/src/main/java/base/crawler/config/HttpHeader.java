package base.crawler.config;

import java.util.HashSet;

import org.apache.http.message.BasicHeader;

/**
 * @author yshi
 *
 */
public class HttpHeader {

	public static final String KEY_USER_AGENT = "user-agent";
	public static final String VALUE_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";

	public static final String KEY_ACCEPT = "Accept";
	public static final String VALUE_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3";

	public static final String KEY_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String VALUE_ACCEPT_ENCODING = "gzip, deflate, br";

	public static final String KEY_ACCEPT_LANGUAGE = "Accept-Language";
	public static final String VALUE_ACCEPT_LANGUAGE = "zh,zh-CN;q=0.9,en-US;q=0.8,en;q=0.7";

	public static final String KEY_CONNECTION = "Connection";
	public static final String VALUE_CONNECTION = "keep-alive";
	
	public static final String KEY_CONTENT_TYPE = "Content-Type";
	public static final String VALUE_CONTENT_TYPE = "text/html; charset=UTF-8";

	private static HashSet<BasicHeader> headers = new HashSet<BasicHeader>();

	@SuppressWarnings("unused")
	private static HttpHeader instance = new HttpHeader();

	private HttpHeader() {
		headers.add(new BasicHeader(KEY_USER_AGENT, VALUE_USER_AGENT));
		headers.add(new BasicHeader(KEY_ACCEPT, VALUE_ACCEPT));
		headers.add(new BasicHeader(KEY_ACCEPT_ENCODING, VALUE_ACCEPT_ENCODING));
		headers.add(new BasicHeader(KEY_ACCEPT_LANGUAGE, VALUE_ACCEPT_LANGUAGE));
		headers.add(new BasicHeader(KEY_CONNECTION, VALUE_CONNECTION));
		headers.add(new BasicHeader(KEY_CONTENT_TYPE, VALUE_CONTENT_TYPE));
		
		headers.add(new BasicHeader("sec-fetch-mode", "navigate"));
		headers.add(new BasicHeader("sec-fetch-site", "none"));
		headers.add(new BasicHeader("sec-fetch-user", "?1"));
	}
	
	public static HashSet<BasicHeader> getHeaders(){
		return headers;
	}

}
