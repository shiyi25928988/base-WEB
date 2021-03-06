package base.crawler.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.conn.DnsResolver;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.authentication.AuthInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class CrawlerConfig extends CrawlConfig{
	
	private static final DnsResolver dnsResolver = new SystemDefaultDnsResolver();
	
	public static String crawlStorageFolder = System.getProperty("user.dir"); 
	
	public static Boolean resumableCrawling = false;
	
	public static Integer maxDepthOfCrawling = -1;
	
	public static Integer maxPagesToFetch = -1;
	
	public static String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36";
	
	public static Collection<? extends Header> defaultHeaders = HttpHeader.getHeaders();
	
	public static Integer politenessDelay = 100; // milliseconds delay between request 
	
	public static Boolean includeHttpsPages = true;
	
	public static Boolean includeBinaryContentInCrawling = true; //fetch binary content e.g image sound
	
	public static Boolean processBinaryContentInCrawling = false; //using Apache TIKA to parse the binary content
	
	public static Integer maxConnectionsPerHost = 16;
	
	public static Integer maxTotalConnections = 1024;
	
	public static Integer socketTimeout = 100 * 1000; //10 seconds
	
	public static Integer connectionTimeout = 600 * 1000; //60 second
	
	public static Integer maxOutgoingLinksToFollow = Integer.MAX_VALUE;//-1;
	
	public static Integer maxDownloadSize = 1024 * 1024 * 100; //100M
	
	public static Boolean followRedirects = true; 
	
	public static Boolean shutdownOnEmptyQueue = true;
	
	public static Boolean onlineTldListUpdate = false; //update Top-level Domain
	
	public static String ProxyHost; //if crawler need behind a proxy
	public static Integer proxyPort;
	public static String proxyUsername;
	public static String proxyPassword;
	
	public static List<AuthInfo> authInfos = new ArrayList<>();
	
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
		
		log.info("init crawler config");
		
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
	
	public static void setAuth(AuthInfo authInfo) {
		authInfos.add(authInfo);
		config.setAuthInfos(authInfos);
	}
}
