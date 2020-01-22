package selenium.base.cli;


/**
 * @author shiyi
 *
 */
public class CommandLineOptions {
	
	private static final org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
	private static final boolean hasArgs = true;
	private static final boolean noArgs = false;
	
	public static final String OPT_HELP = "help";
	public static final String OPT_BROWSER = "browser";
	public static final String OPT_BROWSER_VERSION = "browserVersion";
	
	public static final String OPT_SCRIPT = "script";
	
	
	static {
		
		options.addOption("h", OPT_HELP, noArgs, "Helper.");
		options.addOption("b", OPT_BROWSER, hasArgs, "Specified which kind of web browser. e.g. chrome");
		options.addOption("bv", OPT_BROWSER_VERSION, hasArgs, "Browser's version.");
		
	}
	
	
	/**
	 * @return
	 */
	public static org.apache.commons.cli.Options getOptions(){
		return CommandLineOptions.options;
	}
}
