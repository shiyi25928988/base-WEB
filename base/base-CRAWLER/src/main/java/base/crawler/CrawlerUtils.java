package base.crawler;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author yshi
 *
 */
public class CrawlerUtils {
	
	private static final int CONNECTION_TIMEOUT = 500;
	
	/**
	 * @param addr
	 * @return
	 * @throws IOException
	 */
	public static boolean isAddressReacheable(String addr) throws IOException {
		java.net.URL url = new java.net.URL(addr);
		String host = url.getHost();
		InetAddress address = InetAddress.getByName(host);
		return address.isReachable(CONNECTION_TIMEOUT);
	}

}
