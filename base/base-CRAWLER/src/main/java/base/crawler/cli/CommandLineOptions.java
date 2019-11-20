package base.crawler.cli;

import org.apache.commons.cli.Option;

/**
 * @author yshi
 *
 */
public class CommandLineOptions {
	
	public static final String OPT_HELP = "help";
	public static final String OPT_ADDRESS = "address";
	public static final String OPT_FOLDER = "folder";
	
	
	private static final org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
	private static final boolean hasArg = true;
	private static final boolean notHasArgs = false;
	
	static {
		options.addOption(new Option("h", OPT_HELP, notHasArgs, "Helper."));
		options.addOption(new Option("a", OPT_ADDRESS, hasArg, "The target web sit host address."));
		options.addOption(new Option("f", OPT_FOLDER, hasArg, "The crawled files store folder path."));
		//..... add options here
	}
	
	public static org.apache.commons.cli.Options getOptions(){
		return CommandLineOptions.options;
	}
	
}
