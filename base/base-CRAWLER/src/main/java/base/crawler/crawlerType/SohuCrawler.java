package base.crawler.crawlerType;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import org.htmlparser.util.ParserException;

import base.crawler.CrawlResults;
import base.crawler.utils.HtmlParser;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SohuCrawler extends AbstractCrawler{

	@Override
	protected Pattern getPattern() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		return true;
	}
	
	/**
	*
	*/
	@Override
	public void visit(Page page) {
		log.info("visit -> " + page.getWebURL().getURL());
		log.info("crawling by " + this.getClass().getCanonicalName());
		log.info("response status : " + page.getStatusCode());
		if(page.getStatusCode() != 200) return; // return if http status code is not 200 OK
		
		if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.HtmlParseData) {
			var htmlParseData = (HtmlParseData) page.getParseData();
			
			if(!page.getWebURL().getURL().trim().startsWith("https://www.sohu.com/a")) {
				return;
			}
			
			try {
				if("GB2312".contentEquals(htmlParseData.getContentCharset())) {
					return;
				}
				String description = htmlParseData.getMetaTagValue("og:description");
				String imageUrl = getImageUrl(htmlParseData.getHtml(), htmlParseData.getContentCharset(),1);
				
				Date releaseDate = new Date();
				try {
					releaseDate = HtmlParser.parseDate(htmlParseData.getMetaTagValue("og:release_date"));
				} catch (ParseException e1) {
					releaseDate = new Date();
				}
				CrawlResults result = new CrawlResults();
				result.setTitle(htmlParseData.getTitle());
				result.setUrl(page.getWebURL().getURL());
				result.setCharSet(htmlParseData.getContentCharset());
				result.setImageUrl(imageUrl);
				result.setContent(description.getBytes());
				result.setReleaseDate(releaseDate);
				result.setSource(page.getWebURL().getDomain());
				queue.put(result);
			} catch (InterruptedException | ParserException e) {
				log.info(e.getLocalizedMessage());
			}
		}
	}

}
