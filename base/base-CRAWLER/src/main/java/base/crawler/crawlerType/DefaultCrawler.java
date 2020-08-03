package base.crawler.crawlerType;

import java.util.Date;
import java.util.regex.Pattern;

import org.htmlparser.util.ParserException;

import com.google.common.base.Strings;

import base.crawler.CrawlResults;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class DefaultCrawler extends AbstractCrawler{

	@Override
	protected Pattern getPattern() {
		return null;
	}
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		return true;
	}
	
	@Override
	public void visit(Page page) {
		log.info("visit -> " + page.getWebURL().getURL());
		log.info("crawling by " + this.getClass().getCanonicalName());
		log.info("response status : " + page.getStatusCode());
		if(page.getStatusCode() != 200) return; // return if http status code is not 200 OK
		
		if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.HtmlParseData) {
			var htmlParseData = (HtmlParseData) page.getParseData();
			
			try {
				if("GB2312".contentEquals(htmlParseData.getContentCharset())) {
					return;
				}
				if("GBK".contentEquals(htmlParseData.getContentCharset())) {
					return;
				}
				if("ISO-8859-1".contentEquals(htmlParseData.getContentCharset())) {
					return;
				}
				String description = htmlParseData.getMetaTagValue("description");
				if(Strings.isNullOrEmpty(description.trim())) {
					return;
				}
				String contentType = "news";
				String imageUrl = getImageUrl(htmlParseData.getHtml(), htmlParseData.getContentCharset(),1);
				
				if(!imageUrl.startsWith("htt")) {
					imageUrl = "";
				}
				
				Date releaseDate = new Date();
				
				CrawlResults result = new CrawlResults();
				result.setTitle(htmlParseData.getTitle());
				result.setUrl(page.getWebURL().getURL());
				result.setCharSet(htmlParseData.getContentCharset());
				result.setImageUrl(imageUrl);
				result.setContent(description.getBytes());
				result.setReleaseDate(releaseDate);
				result.setSource(page.getWebURL().getDomain());
				result.setContentType(contentType);
				queue.put(result);
			} catch (InterruptedException | ParserException e) {
				log.info(e.getLocalizedMessage());
			}
		}
	
	}
}
