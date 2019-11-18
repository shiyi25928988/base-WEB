package base.crawler.crawler;

import java.io.File;
import java.util.Queue;
import java.util.UUID;
import java.util.regex.Pattern;

import base.crawler.CrawlResults;
import base.crawler.CrawlResults.ResultType;
import base.crawler.config.QueueHolder;
import base.io.IOUtils;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public abstract class AbstractCrawler extends WebCrawler {
	
	private static final String domainSuffixReg = ".(com|net|org|cn|edu|gov|top|info|xxx|idv|name|coop|museum|aero|pro|biz|int|xyz|co|mobi|club|rec|asia|travel|vip|win|fun).";

	Queue<CrawlResults> queue = QueueHolder.getQuene();

	/**
	 *
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String link = url.getURL().toLowerCase();
		return getPattern().matcher(link).matches();
	}

	/**
	*
	*/
	@Override
	public void visit(Page page) {
		var url = page.getWebURL().getURL();
		var name = "";
		if(url.contains("/") && url.contains(".")) {
			if(url.lastIndexOf('/') < url.lastIndexOf('.')) {
				name = url.substring(url.lastIndexOf('/'), url.lastIndexOf('.'));
			}
		}
		if(url.lastIndexOf('/') == (url.length() - 1)) {
			name = UUID.randomUUID().toString();
		}
		
		var extension = "";
		if(url.contains(".")) {
			extension = url.substring(url.lastIndexOf('.'));
			if(Pattern.compile(domainSuffixReg).matcher(extension).find()) {
				extension = ".html";
			}
			log.error("extension : " + extension);
		}
		
		if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.HtmlParseData) {
			var htmlParseData = (HtmlParseData) page.getParseData();
			var content = htmlParseData.getHtml();
			queue.offer(new CrawlResults(url, name ,content.getBytes(), extension, CrawlResults.ResultType.Html));
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.TextParseData) {
			var textParseData = (TextParseData) page.getParseData();
			var content = textParseData.getTextContent();
			queue.offer(new CrawlResults(url, name, content.getBytes(), extension, CrawlResults.ResultType.Text));
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.BinaryParseData) {
			//var binaryParseData = (BinaryParseData) page.getParseData();
			//var content = binaryParseData.getHtml();
			var content = page.getContentData();
			queue.offer(new CrawlResults(url, name, content, extension, CrawlResults.ResultType.Binary));
		}
	}

	abstract protected Pattern getPattern();

}
