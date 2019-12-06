package base.crawler;

import java.io.File;
import java.util.regex.Pattern;

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
	public static void writeResult(CrawlResults result) {

		switch (result.getType()) {
		case Html:
		case Text:
			break;
		case Binary:
			break;
			default:
		}

		var extension = result.getExtension();

		var realPath = CrawlerConstants.CURRENT_PATH + File.separator + result.getRootFolder()
				+ result.getFileName().substring(0, result.getFileName().lastIndexOf("/")).replace("/", File.separator);

		var fileName = result.getFileName().substring(result.getFileName().lastIndexOf("/") + 1);

		fileName = filePattern.matcher(fileName).replaceAll("_");

		var content = result.getContent();

		IOUtils.writeBinaryFile(content, realPath, fileName + extension);
	}

}
