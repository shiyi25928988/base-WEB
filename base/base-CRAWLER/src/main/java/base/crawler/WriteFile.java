package base.crawler;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

import base.crawler.config.CrawlerConstants;
import base.io.IOUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public abstract class WriteFile {
	
	private static Pattern filePattern = Pattern.compile("[\\\\/:*?\"<>|]");
	
	/**
	 * @param result
	 */
	public static void writeResult(CrawlResults result){
		var content = result.getContent();
		var name = result.getName();
		var extension = result.getExtension();
		if(Strings.isNullOrEmpty(extension)) extension = ".html";
		
		
		name = filePattern.matcher(name).replaceAll("_");
		log.error(name);log.error(extension);
		IOUtils.writeFile(content, 
				CrawlerConstants.CURRENT_PATH, name + extension);
	}
	
}
