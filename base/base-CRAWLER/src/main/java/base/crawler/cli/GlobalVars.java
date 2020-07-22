package base.crawler.cli;

import java.io.File;

public abstract class GlobalVars {
	
	public static final String CURRENT_PATH = System.getProperty("user.dir") + File.separator + "crawl" ; 

	public static boolean isRobotTxtEnabled = false;
	
	public static String robotUserAgent = "ToutiaoSpider";
	
	public static int crawlerNumber = Runtime.getRuntime().availableProcessors();
	
	public static String targetFileType = "";
	
}
