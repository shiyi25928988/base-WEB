package base.crawler.utils;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author yshi
 *
 */
public class CrawlerUtils {
	
	/**
	 * CONNECTION_TIMEOUT
	 */
	private static final int CONNECTION_TIMEOUT = 5000;
	
	/**
	 * @param addr
	 * @return
	 * @throws IOException
	 */
	public static boolean isAddressReacheable(String addr) throws IOException {
//		java.net.URL url = new java.net.URL(addr);
//		String host = url.getHost();
//		InetAddress address = InetAddress.getByName(host);
//		return address.isReachable(CONNECTION_TIMEOUT);
		return true;
	}

}
