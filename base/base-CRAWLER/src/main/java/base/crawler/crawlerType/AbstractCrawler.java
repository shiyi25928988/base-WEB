package base.crawler.crawlerType;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

import org.htmlparser.util.ParserException;

import base.crawler.CrawlResults;
import base.crawler.config.QueueHolder;
import base.crawler.utils.HtmlParser;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author yshi
 *
 */
public abstract class AbstractCrawler extends WebCrawler {

	BlockingQueue<CrawlResults> queue = QueueHolder.getQuene();

	/**
	 *
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String link = url.getURL().toLowerCase();
		boolean should = getPattern().matcher(link).matches();
		return should;
	}

	/**
	*
	*/
	@Override
	public void visit(Page page) {
	}

	/**
	 * Customized url filter pattern.
	 * 
	 * @return
	 */
	abstract protected Pattern getPattern();

	/**
	 * @param html
	 * @param charset
	 * @param sequence
	 * @return
	 * @throws ParserException
	 */
	protected String getImageUrl(String html, String charset, int sequence) throws ParserException {
		List<String> list = HtmlParser.getTagContent(html, charset, "img");
		if (list.isEmpty()) {
			return "";
		}
		if (list.size() > sequence) {
			return HtmlParser.getImageAttr(list.get(sequence));
		} else {
			return HtmlParser.getImageAttr(list.get(list.size() - 1));
		}
	}

}
