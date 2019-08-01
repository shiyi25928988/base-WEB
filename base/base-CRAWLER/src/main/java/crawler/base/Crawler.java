package crawler.base;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.extern.java.Log;

/**
 * @author yshi
 *
 */
@Log
public class Crawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");
	
	Queue<CrawlResults> queue = QueueHolder.getQuene();

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String link = url.getURL().toLowerCase();
		return !FILTERS.matcher(link).matches();
	}
	
	 @Override
     public void visit(Page page) {

         if (page.getParseData() instanceof HtmlParseData) {
        	 String url = page.getWebURL().getURL();
        	 log.info("crawler visting : " + url);
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String text = htmlParseData.getText();
             log.info("html title : " + htmlParseData.getTitle());
             log.info("html contents : " + text);
             queue.offer(new CrawlResults(url, text));
         }
	 }

}
