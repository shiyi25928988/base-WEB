package base.crawler.cli;

import org.apache.commons.cli.Option;

/**
 * @author yshi
 *
 */
public class CommandLineOptions {
	private static org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
	private static final boolean hasArg = true;
	private static final boolean notHasArgs = false;
	
	static {
		options.addOption(new Option("h", "help", notHasArgs, "Helper."));
		options.addOption(new Option("ta", "address", hasArg, "The target web sit address."));
		options.addOption(new Option("ft", "filetype", hasArg, "The target file type you want keep."));
		options.addOption(new Option("fp", "folderpath", hasArg, "The crawled files store folder path."));
		//..... add options here
	}
	
	public static org.apache.commons.cli.Options getOptions(){
		return CommandLineOptions.options;
	}
	
	public enum OptionType{
		help("help"),
		address("address");
		
		
		OptionType(String opt){
			this.opt = opt;
		}
		
		String opt;
		
		public String get() {
			return this.opt;
		}
		
	}
}
