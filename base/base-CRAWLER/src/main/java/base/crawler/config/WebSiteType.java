package base.crawler.config;

import base.crawler.crawlerType.AbstractCrawler;
import base.crawler.crawlerType.DefaultCrawler;
import base.crawler.crawlerType.SohuCrawler;
import base.crawler.crawlerType.SootooCrawler;

/**
 * @author shiyi
 *
 */
public enum WebSiteType {
	SOHU("sohu"), 
	SOOTOO("sootoo"), 
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
		default:
			return new DefaultCrawler();
		}
	}

}
