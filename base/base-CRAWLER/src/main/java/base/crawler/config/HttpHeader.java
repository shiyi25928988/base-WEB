package base.crawler.config;

import java.util.HashSet;

import org.apache.http.message.BasicHeader;

/**
 * @author yshi
 *
 */
public class HttpHeader {

	public static final String KEY_USER_AGENT = "User-Agent";
	public static final String VALUE_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36";

	public static final String KEY_ACCEPT = "Accept";
	public static final String VALUE_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3";

	public static final String KEY_ACCEPT_CHARSET = "Accept-Charset";
	public static final String VALUE_ACCEPT_CHARSET = "utf-8, iso-8859-1;q=0.5, *;q=0.1";

	public static final String KEY_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String VALUE_ACCEPT_ENCODING = "gzip, compress, deflate, br, identity";

	public static final String KEY_ACCEPT_LANGUAGE = "Accept-Language";
	public static final String VALUE_ACCEPT_LANGUAGE = "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7";

	public static final String KEY_CACHE_CONTROL = "Cache-Control";
	public static final String VALUE_CACHE_CONTROL = "no-cache";

	public static final String KEY_CONNECTION = "Connection";
	public static final String VALUE_CONNECTION = "keep-alive";

	public static final String KEY_CONTENT_TYPE = "Content-Type";
	public static final String VALUE_CONTENT_TYPE = "application/x-www-form-urlencoded";

	private static HashSet<BasicHeader> headers = new HashSet<BasicHeader>();

	@SuppressWarnings("unused")
	private static HttpHeader instance = new HttpHeader();

	private HttpHeader() {
		headers.add(new BasicHeader(KEY_USER_AGENT, VALUE_USER_AGENT));
		headers.add(new BasicHeader(KEY_ACCEPT, VALUE_ACCEPT));
		headers.add(new BasicHeader(KEY_ACCEPT_CHARSET, VALUE_ACCEPT_CHARSET));
		headers.add(new BasicHeader(KEY_ACCEPT_ENCODING, VALUE_ACCEPT_ENCODING));
		headers.add(new BasicHeader(KEY_ACCEPT_LANGUAGE, VALUE_ACCEPT_LANGUAGE));
		headers.add(new BasicHeader(KEY_CACHE_CONTROL, VALUE_CACHE_CONTROL));
		headers.add(new BasicHeader(KEY_CONNECTION, VALUE_CONNECTION));
		headers.add(new BasicHeader(KEY_CONTENT_TYPE, VALUE_CONTENT_TYPE));
	}
	
	public static HashSet<BasicHeader> getHeaders(){
		return headers;
	}

}
