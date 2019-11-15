package base.crawler.config;

import java.util.Collection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.conn.DnsResolver;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.authentication.AuthInfo;

/**
 * @author yshi
 *
 */
public class CrawlerConfig extends CrawlConfig{
	
	private static final DnsResolver dnsResolver = new SystemDefaultDnsResolver();
	
	private static String crawlStorageFolder = System.getProperty("user.dir"); 
	
	private static Boolean resumableCrawling = false;
	
	private static Integer maxDepthOfCrawling = -1;
	
	private static Integer maxPagesToFetch = -1;
	
	private static String userAgentString = HttpHeader.VALUE_USER_AGENT;
	
	private static Collection<? extends Header> defaultHeaders = HttpHeader.getHeaders();
	
	private static Integer politenessDelay = 1000; // milliseconds delay between request 
	
	private static Boolean includeHttpsPages = true;
	
	private static Boolean includeBinaryContentInCrawling = true; //fetch binary content e.g image sound
	
	private static Boolean processBinaryContentInCrawling = false; //using Apache TIKA to parse the binary content
	
	private static Integer maxConnectionsPerHost = 16;
	
	private static Integer maxTotalConnections = 1024;
	
	private static Integer socketTimeout = 10 * 1000; //10 seconds
	
	private static Integer connectionTimeout = 60 * 1000; //60 second
	
	private static Integer maxOutgoingLinksToFollow = 16;
	
	private static Integer maxDownloadSize = 1024 * 1024 * 100; //100M
	
	private static Boolean followRedirects = true; 
	
	private static Boolean shutdownOnEmptyQueue = true;
	
	private static Boolean onlineTldListUpdate = false; //update Top-level Domain
	
	private static String ProxyHost; //if crawler need behind a proxy
	private static Integer proxyPort;
	private static String proxyUsername;
	private static String proxyPassword;
	
	private static List<AuthInfo> authInfos;
	
	private static volatile CrawlConfig config;
	
	static{
		config = new CrawlerConfig();
		initConfig(config);
	}
	
	/**
	 * 
	 */
	private CrawlerConfig() {
	}
	
	/**
	 * Init crawler configuration.
	 * @param config
	 */
	private static void initConfig(final CrawlConfig config){
		config.setDnsResolver(dnsResolver);
		config.setDefaultHeaders(defaultHeaders);
		config.setConnectionTimeout(connectionTimeout);
		config.setSocketTimeout(socketTimeout);
		config.setFollowRedirects(followRedirects);
		config.setIncludeBinaryContentInCrawling(includeBinaryContentInCrawling);
		config.setIncludeHttpsPages(includeHttpsPages);
		config.setMaxConnectionsPerHost(maxConnectionsPerHost);
		config.setMaxDepthOfCrawling(maxDepthOfCrawling);
		config.setMaxDownloadSize(maxDownloadSize);
		config.setOnlineTldListUpdate(onlineTldListUpdate);
		config.setMaxOutgoingLinksToFollow(maxOutgoingLinksToFollow);
		config.setPolitenessDelay(politenessDelay);
		config.setProcessBinaryContentInCrawling(processBinaryContentInCrawling);
		config.setShutdownOnEmptyQueue(shutdownOnEmptyQueue);
		config.setResumableCrawling(resumableCrawling);
		config.setUserAgentString(userAgentString);
		config.setMaxPagesToFetch(maxPagesToFetch);
		config.setMaxTotalConnections(maxTotalConnections);
		config.setCrawlStorageFolder(crawlStorageFolder);
	}
	

	
	public static CrawlConfig getConfig() {
		return config;
	}
}
