package base.crawler;

import java.util.UUID;
import java.util.regex.Pattern;

import base.crawler.config.CrawlerConstants;
import base.io.IOUtils;

/**
 * @author yshi
 *
 */
public abstract class WriteFile {
	
	private static Pattern filePattern = Pattern.compile("[\\\\/:*?\"<>|]");
	
	/**
	 * @param result
	 */
	public static void writeResult(CrawlResults result){
		var content = result.getContent();
		var name = result.getName();
		var extension = filePattern.matcher(result.getExtension()).replaceAll("_");

		//var extension = result.getExtension().replace("/", "_");
		IOUtils.writeFile(content, 
				CrawlerConstants.CURRENT_PATH, name + extension);
	}
	
}
