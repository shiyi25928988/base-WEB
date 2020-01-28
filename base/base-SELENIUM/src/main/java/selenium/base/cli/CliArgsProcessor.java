package selenium.base.cli;

import java.util.List;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.openqa.selenium.WebDriver;

import selenium.base.GlobleVargs;
import selenium.base.browser.BrowserType;
import selenium.base.browser.WebDriverFactory;
import selenium.base.script.IScript;

public class CliArgsProcessor {

	private static CommandLineParser parser = new DefaultParser();

	private static HelpFormatter formatter = new HelpFormatter();

	public void process(String...args) throws Exception {
		CommandLine cmd = parser.parse(CommandLineOptions.getOptions(), args);
		
		/** --help */
		if (cmd.hasOption(CommandLineOptions.OPT_HELP)) {
			formatter.printHelp("selenium", CommandLineOptions.getOptions(), true);
		}
		
		if (cmd.hasOption(CommandLineOptions.OPT_BROWSER)) {
			//GlobleVargs.browserType = cmd.getOptionValue(CommandLineOptions.OPT_BROWSER);
			String browserType = cmd.getOptionValue(CommandLineOptions.OPT_BROWSER);
			Optional<WebDriver> opt = WebDriverFactory.getWebDriver(BrowserType.getBrowserType(browserType));
			if(opt.isEmpty()) {
				throw new RuntimeException();
			}
		}
		
		if (cmd.hasOption(CommandLineOptions.OPT_SCRIPT)) {
			
		}
		
		
	}
	
	private List<IScript> searchScriptFiles(){
		
		return null;
	}
}
