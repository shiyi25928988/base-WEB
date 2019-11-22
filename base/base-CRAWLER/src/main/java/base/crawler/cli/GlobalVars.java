package base.crawler.cli;

public abstract class GlobalVars {

	public static boolean isRobotTxtEnabled = false;
	
	public static String robotUserAgent = "Baiduspider";
	
	public static int crawlerNumber = Runtime.getRuntime().availableProcessors();
	
}
