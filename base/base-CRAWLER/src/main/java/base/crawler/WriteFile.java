package base.crawler;

import java.io.File;
import java.util.regex.Pattern;

import com.google.common.net.UrlEscapers;

import base.crawler.cli.GlobalVars;
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
	public static void writeResult(CrawlResults result) {

		var extension = result.getExtension();

		var realPath = GlobalVars.CURRENT_PATH + File.separator + result.getRootFolder()
				+ result.getFileName().substring(0, result.getFileName().lastIndexOf("/")).replace("/", File.separator);

		var fileName = result.getFileName().substring(result.getFileName().lastIndexOf("/") + 1);

		fileName = UrlEscapers.urlPathSegmentEscaper().escape(fileName);
		
		fileName = filePattern.matcher(fileName).replaceAll("_");

		var content = result.getContent();

		IOUtils.writeBinaryFile(content, realPath, fileName + extension);
	}

}
