package base.crawler.config;

import base.crawler.crawlerType.AbstractCrawler;
import base.crawler.crawlerType.DefaultCrawler;
import base.crawler.crawlerType.JianshuCrawler;
import base.crawler.crawlerType.Netease163Crawler;
import base.crawler.crawlerType.PK52Crawler;
import base.crawler.crawlerType.SinaCrawler;
import base.crawler.crawlerType.SohuCrawler;
import base.crawler.crawlerType.SootooCrawler;

/**
 * @author shiyi
 *
 */
public enum WebSiteType {
	SOHU("sohu"), 
	SOOTOO("sootoo"), 
	PK52("52pk"),
	SINA("sina"),
	NE163("163"),
	JIANSHU("jianshu"),
	DEFAULT("");

	private String domain;

	WebSiteType(String domain) {
		this.domain = domain;
	}

	/**
	 * @return WebSiteType
	 */
	private String getDomainName() {
		return this.domain;
	}

	/**
	 * @param domain
	 * @return WebSiteType
	 */
	public static WebSiteType getWebSiteType(String domain) {

		WebSiteType type = DEFAULT;

		for (WebSiteType webSiteType : WebSiteType.values()) {
			if (domain.trim().toLowerCase().contains(webSiteType.getDomainName())) {
				type = webSiteType;
				break;
			}
		}
		return type;
	}

	public static AbstractCrawler getWebSiteCrawler(WebSiteType type) {
		switch (type) {
		case SOHU:
			return new SohuCrawler();
		case SOOTOO:
			return new SootooCrawler();
		case PK52:
			return new PK52Crawler();
		case SINA:
			return new SinaCrawler();
		case NE163:
			return new Netease163Crawler();
		case JIANSHU:
			return new JianshuCrawler();
		default:
			return new DefaultCrawler();
		}
	}

}
