package base.crawler.cli;

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
	public static final String OPT_TIME_INTERVAL = "time-interval";
	
	public static final String OPT_AUTHURL = "auth-url";
	public static final String OPT_USERNAME = "user-name";
	public static final String OPT_PASSWD = "password";
	public static final String OPT_USERNAME_ELEMENT = "user-name-element";
	public static final String OPT_PASSWD_ELEMENT = "password-element";
	
	
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
		options.addOption("ti", OPT_TIME_INTERVAL, hasArg, "Time interval(ms) between each http request .");
		
		options.addOption("al", OPT_AUTHURL, hasArg, "Login page url.");
		options.addOption("au", OPT_USERNAME, hasArg, "Login user name.");
		options.addOption("ap", OPT_PASSWD, hasArg, "Login password.");
		options.addOption("aue", OPT_USERNAME_ELEMENT, hasArg, "Login user name input element's name attr");
		options.addOption("ape", OPT_PASSWD_ELEMENT, hasArg, "Login password element's name attr");
		//..... add options here
	}
	
	public static org.apache.commons.cli.Options getOptions(){
		return CommandLineOptions.options;
	}
	
}
