package base.crawler.config;

import java.util.Collection;
import java.util.HashSet;

import org.apache.http.Header;
import org.apache.http.conn.DnsResolver;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.message.BasicHeader;

import base.crawler.CrawlerConstants;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;

/**
 * @author yshi
 *
 */
public class CrawlerConfig extends CrawlConfig{
	
	private static final DnsResolver dnsResolver = new SystemDefaultDnsResolver();
	
	private static String crawlStorageFolder;
	private static Boolean resumableCrawling = false;
	private static Long dbLockTimeout = 500L;
	private static Integer maxDepthOfCrawling = -1;
	private static Integer maxPagesToFetch = -1;
	private static String userAgentString = HttpHeader.VALUE_USER_AGENT;
	private static Collection<? extends Header> defaultHeaders = HttpHeader.getHeaders();
	private static Integer politenessDelay = 1000; // milliseconds delay between request 
	private static Boolean includeHttpsPages = true;
	private static Boolean includeBinaryContentInCrawling = true; //fetch binary content e.g image sound
	private static Boolean processBinaryContentInCrawling = true; //using Apache TIKA to parse the binary content
	
	private static Integer maxConnectionsPerHost = 16;
	private static Integer maxTotalConnections = 1024;
	
	private static Integer socketTimeout = 10000; //10 seconds
	
	private static Integer maxOutgoingLinksToFollow = 16;
	
	private static Integer maxDownloadSize = 1024 * 1024 * 100; //100M
	
	private static Boolean followRedirects = true; 
	
	private static Boolean shutdownOnEmptyQueue = true;
	
	
	private static CrawlConfig config;
	
	static{
		config = new CrawlerConfig();
		initConfig(config);
	}
	
	/**
	 * 
	 */
	private CrawlerConfig() {
		throw new RuntimeException();
	}
	
	/**
	 * Init crawler configuration.
	 * @param config
	 */
	private static void initConfig(final CrawlConfig config){
		config.setCrawlStorageFolder(CrawlerConstants.CURRENT_PATH);
		config.setConnectionTimeout(50*1000);
		config.setIncludeHttpsPages(true);
		config.setFollowRedirects(false);
		setHeader(config);
	}
	
	private static void setHeader(final CrawlConfig config){
		HashSet<BasicHeader> collections = new HashSet<BasicHeader>();
        collections.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36"));
        collections.add(new BasicHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));
        collections.add(new BasicHeader("Accept-Encoding", "gzip, deflate, br"));
        collections.add(new BasicHeader("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7"));
        collections.add(new BasicHeader("Cache-Control", "no-cache"));
        collections.add(new BasicHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"));
        collections.add(new BasicHeader("Connection", "keep-alive"));
        //collections.add(new BasicHeader("Cookie", "bid=fp-BlwmyeTY; __yadk_uid=dLpMqMsIGD1N38NzhbcG3E6QA33NQ9bE; ps=y; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1506515077%2C%22https%3A%2F%2Faccounts.douban.com%2Flogin%3Falias%3D793890838%2540qq.com%26redir%3Dhttps%253A%252F%252Fwww.douban.com%26source%3DNone%26error%3D1013%22%5D; ll=\"108296\"; ue=\"793890838@qq.com\"; __utmt=1; _ga=GA1.2.388925103.1505404043; _gid=GA1.2.1409223546.1506515083; dbcl2=\"161927939:ZDwWtUnYaH4\"; ck=rMaO; ap=1; push_noty_num=0; push_doumail_num=0; __utma=30149280.388925103.1505404043.1506510959.1506515077.8; __utmb=30149280.22.9.1506516374528; __utmc=30149280; __utmz=30149280.1506510959.7.5.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=30149280.16192; _pk_id.100001.8cb4=1df4f52fdf296b72.1505404042.8.1506516380.1506512502.; _pk_ses.100001.8cb4=*"));
        config.setDefaultHeaders(collections);
	}
	
	public static CrawlConfig getConfig() {
		return config;
	}
}
