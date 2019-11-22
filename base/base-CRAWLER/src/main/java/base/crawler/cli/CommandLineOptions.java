package base.crawler.cli;

import java.io.File;

import org.apache.commons.cli.Option;

/**
 * @author yshi
 *
 */
public class CommandLineOptions {
	
	public static final String OPT_HELP = "help";
	public static final String OPT_ADDRESS = "address";
	public static final String OPT_ROBOT = "robot";
	public static final String OPT_DEPTH = "depth";
	public static final String OPT_CRAWLER_NUM = "number";
	
	public static final String OPT_AUTHTYPE = "auth";
	public static final String OPT_USER = "user";
	public static final String OPT_PASSWD = "password";
	
	
	
	private static final org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
	private static final boolean hasArg = true;
	private static final boolean notHasArgs = false;
	
	static {
		options.addOption("h", OPT_HELP, notHasArgs, "Helper.");
		options.addOption("a", OPT_ADDRESS, hasArg, "The target web sit host address.");
		options.addOption("d", OPT_DEPTH, hasArg, "The max depth of crawling. Default no limit.");
		options.addOption("r", OPT_ROBOT, hasArg, 
				  "If enable robot.txt protocol with specified User-Agent.  " + System.lineSeparator()
				+ "e.g. 'Googlebot' 'Baiduspider' 'Bingbot' 'Yahoo! Slurp' 'Pinterestbot'");
		
		options.addOption("n", OPT_CRAWLER_NUM, hasArg, "The number of crawlers.");
		
//		options.addOption("au", OPT_AUTHTYPE, hasArg, "Web site auth type : " + System.lineSeparator()
//													+ "1.Basic Auth " + System.lineSeparator()
//				                                    + "2.Form Auth " + System.lineSeparator()
//				                                    + "3.Windows Domain Auth");
		//..... add options here
	}
	
	public static org.apache.commons.cli.Options getOptions(){
		return CommandLineOptions.options;
	}
	
}
