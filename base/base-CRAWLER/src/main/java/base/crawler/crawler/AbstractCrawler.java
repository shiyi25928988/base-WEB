package base.crawler.crawler;

import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

import base.crawler.CrawlResults;
import base.crawler.config.ContentType;
import base.crawler.config.QueueHolder;
import base.crawler.exceptions.ExtendTypeNotFoundException;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
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

	BlockingQueue<CrawlResults> queue = QueueHolder.getQuene();

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
		
		if(page.getStatusCode() != 200) return; // return if http status code is not 200 OK
		
		var url = page.getWebURL().getURL();
		var name = url.substring(url.lastIndexOf('/') + 1);
		if(Strings.isNullOrEmpty(name)) {
			name = "index";
		}
		
		var extension = "";

		log.error("page type : " + page.getContentType());
		try {
			extension = ContentType.getExtend(page.getContentType().trim());
		} catch (ExtendTypeNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(name.endsWith(extension)) {
			name = name.replaceFirst(extension, "");
		}
		
		if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.HtmlParseData) {
			var htmlParseData = (HtmlParseData) page.getParseData();
			var content = htmlParseData.getHtml();
			try {
				queue.put(new CrawlResults(url, name ,content.getBytes(), extension, CrawlResults.ResultType.Html));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.TextParseData) {
			var textParseData = (TextParseData) page.getParseData();
			var content = textParseData.getTextContent();
			try {
				queue.put(new CrawlResults(url, name, content.getBytes(), extension, CrawlResults.ResultType.Text));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.BinaryParseData) {
			var content = page.getContentData();
			try {
				queue.put(new CrawlResults(url, name, content, extension, CrawlResults.ResultType.Binary));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Customized url filter pattern.
	 * @return
	 */
	abstract protected Pattern getPattern();

}
