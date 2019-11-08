package base.crawler;

import java.util.Queue;
import java.util.regex.Pattern;

import base.crawler.config.QueueHolder;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author yshi
 *
 */
public abstract class AbstractCrawler extends WebCrawler {

	Queue<CrawlResults> queue = QueueHolder.getQuene();

	/**
	 *
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String link = url.getURL().toLowerCase();
		return !getPattern().matcher(link).matches();
	}

	/**
	*
	*/
	@Override
	public void visit(Page page) {
		var url = page.getWebURL().getURL();
		var extension = "";
		if(url.contains(".")) {
			extension = url.substring(url.lastIndexOf('.'));
		}
		
		if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.HtmlParseData) {
			var htmlParseData = (HtmlParseData) page.getParseData();
			var content = htmlParseData.getHtml();
			queue.offer(new CrawlResults(url, content.getBytes(), extension, CrawlResults.ResultType.Html));
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.TextParseData) {
			var textParseData = (TextParseData) page.getParseData();
			var content = textParseData.getTextContent();
			queue.offer(new CrawlResults(url, content.getBytes(), extension, CrawlResults.ResultType.Text));
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.BinaryParseData) {
			var binaryParseData = (BinaryParseData) page.getParseData();
			var content = binaryParseData.getHtml();
			queue.offer(new CrawlResults(url, content.getBytes(), extension, CrawlResults.ResultType.Binary));
		}
	}

	abstract protected Pattern getPattern();

}
