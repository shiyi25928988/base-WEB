package base.crawler.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import org.apache.http.message.BasicHeader;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class HttpHeader {

	private static Properties headerProperties = new Properties();
	private static HashSet<BasicHeader> headers = new HashSet<BasicHeader>();
	static {
		try {
			headerProperties
					.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("http-header.properties"));
			headerProperties.forEach((k, v) -> {
				log.info("http header " + k + ":" + v);
				headers.add(new BasicHeader(k.toString(), v.toString()));
			});
		} catch (IOException e) {
			log.error(e.toString());
			System.exit(1);
		}
	}

	@SuppressWarnings("unused")
	private static HttpHeader instance = new HttpHeader();

	private HttpHeader() {

//		headers.add(new BasicHeader(":authority", "www.toutiao.com"));
//		headers.add(new BasicHeader(":method", "GET"));
//		headers.add(new BasicHeader(":path", "/"));
//		headers.add(new BasicHeader(":scheme", "https"));

	}

	public static HashSet<BasicHeader> getHeaders() {
		return headers;
	}

}
