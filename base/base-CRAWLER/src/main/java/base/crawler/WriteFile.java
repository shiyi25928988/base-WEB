package base.crawler;

import java.util.UUID;

import base.crawler.config.CrawlerConstants;
import base.io.IOUtils;

/**
 * @author yshi
 *
 */
public abstract class WriteFile {
	
	/**
	 * @param result
	 */
	public static void writeResult(CrawlResults result){
		var content = result.getContent();
		var extension = result.getExtension();
		IOUtils.writeFile(content, 
				CrawlerConstants.CURRENT_PATH, UUID.randomUUID() + extension);
	}
	
}
