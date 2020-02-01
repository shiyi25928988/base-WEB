package selenium.base.cli;

import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.openqa.selenium.WebDriver;

import lombok.extern.slf4j.Slf4j;
import selenium.base.browser.BrowserType;
import selenium.base.browser.WebDriverFactory;
import selenium.base.exception.ScriptFileNotExistException;
import selenium.base.script.DefaultScriptParser;
import selenium.base.script.IScriptParser;
import selenium.base.script.Script;
import selenium.base.script.ScriptObjGenerator;
import selenium.base.utils.IOUtils;

/**
 * @author shiyi
 *
 */
@Slf4j
public class CliArgsProcessor {
	
	private IScriptParser scriptParser = new DefaultScriptParser();

	private CommandLineParser parser = new DefaultParser();

	private HelpFormatter formatter = new HelpFormatter();

	public void process(String...args) throws Exception {
		
		CommandLine cmd = parser.parse(CommandLineOptions.getOptions(), args);
		
		/** --help */
		if (cmd.hasOption(CommandLineOptions.OPT_HELP)) {
			formatter.printHelp("selenium", CommandLineOptions.getOptions(), true);
		}
		
		/** --browser information */
		if (cmd.hasOption(CommandLineOptions.OPT_BROWSER)) {
			//GlobleVargs.browserType = cmd.getOptionValue(CommandLineOptions.OPT_BROWSER);
			String browserType = cmd.getOptionValue(CommandLineOptions.OPT_BROWSER);
			Optional<WebDriver> opt = WebDriverFactory.getWebDriver(BrowserType.getBrowserType(browserType));
			if(opt.isEmpty()) {
				throw new RuntimeException();
			}
		}
		
		/** --script */
		if (cmd.hasOption(CommandLineOptions.OPT_SCRIPT)) {
			String scriptFile = cmd.getOptionValue(CommandLineOptions.OPT_SCRIPT);
			
			if(!IOUtils.isFileExist(scriptFile)) {
				throw new ScriptFileNotExistException();
			} else {
				log.debug("script file exists!!");
			}
			
			Script script = ScriptObjGenerator.gen(scriptFile);
			scriptParser.parse(script);
		}
		
		
	}
	
	

}
