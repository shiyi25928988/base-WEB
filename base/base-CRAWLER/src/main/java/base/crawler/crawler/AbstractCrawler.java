package base.crawler.crawler;

import java.net.MalformedURLException;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

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
		var rootFolder = "";
		var fileName = "";
		var protocol = "";
		try {
			java.net.URL urlObj = new java.net.URL(url);
			rootFolder = urlObj.getHost();
			fileName = urlObj.getFile();
			protocol = urlObj.getProtocol();
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}
		
		if(fileName.endsWith("/")) {
			fileName = fileName + "index";
		}
		
		var extension = "";
		var charSet = "UTF-8";
		
		try {
			extension = ContentType.getExtend(page.getContentType().trim());
		} catch (ExtendTypeNotFoundException e1) {
			log.error(e1.getMessage());
			extension = ".txt";
		}
		charSet = ContentType.getCharSet(page.getContentType());
		
		if(fileName.endsWith(extension)) {
			fileName = fileName.replaceFirst(extension, "");
		}
		
		log.info("visiting : " + url);
		log.info("page type : " + page.getContentType());
		if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.HtmlParseData) {
			var htmlParseData = (HtmlParseData) page.getParseData();
			var content = htmlParseData.getHtml();
			try {
				queue.put(new CrawlResults(url, fileName ,content.getBytes(), extension, CrawlResults.ResultType.Html, rootFolder, protocol, charSet));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.TextParseData) {
			var textParseData = (TextParseData) page.getParseData();
			var content = textParseData.getTextContent();
			try {
				queue.put(new CrawlResults(url, fileName, content.getBytes(), extension, CrawlResults.ResultType.Text, rootFolder, protocol, charSet));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (page.getParseData() instanceof edu.uci.ics.crawler4j.parser.BinaryParseData) {
			var content = page.getContentData();
			try {
				queue.put(new CrawlResults(url, fileName, content, extension, CrawlResults.ResultType.Binary, rootFolder, protocol, charSet));
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
