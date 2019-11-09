package base.crawler.crawler;

import java.util.regex.Pattern;

public class TextCrawler extends AbstractCrawler{

	@Override
	protected Pattern getPattern() {
		return  Pattern.compile(".*(\\.(html|xml))$");
	}

}
