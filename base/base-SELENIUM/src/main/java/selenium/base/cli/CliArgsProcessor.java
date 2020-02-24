package selenium.base.cli;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.openqa.selenium.WebDriver;

import lombok.extern.slf4j.Slf4j;
import selenium.base.browser.BrowserType;
import selenium.base.browser.WebDriverFactory;
import selenium.base.command.CommandExecutor;
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
			//TODO ADD browser version support
			
			//Get browser type.
			String browserType = cmd.getOptionValue(CommandLineOptions.OPT_BROWSER);
			//Build web driver object instance.
			//Once after building a web driver instance , 
			//can directly use  WebDriverFactory#getWebDriverInstance() 
			//to get an existing web driver instance.
			WebDriver webDriver = WebDriverFactory.buildWebDriver(BrowserType.getBrowserType(browserType));
			if(Objects.isNull(webDriver)) {
				throw new RuntimeException("WebDriver not been found!");
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
			//TODO 
			
			CommandExecutor exec = new CommandExecutor(scriptParser.parse(script));
			
			exec.run();
			
		}
		
		
	}
	
	

}
