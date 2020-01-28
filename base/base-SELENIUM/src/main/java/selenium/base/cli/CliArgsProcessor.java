package selenium.base.cli;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import selenium.base.GlobleVargs;
import selenium.base.script.IScript;

public class CliArgsProcessor {

	private static CommandLineParser parser = new DefaultParser();

	private static HelpFormatter formatter = new HelpFormatter();

	public void process(String...args) throws ParseException {
		CommandLine cmd = parser.parse(CommandLineOptions.getOptions(), args);
		
		/** --help */
		if (cmd.hasOption(CommandLineOptions.OPT_HELP)) {
			formatter.printHelp("selenium", CommandLineOptions.getOptions(), true);
		}
		
		if (cmd.hasOption(CommandLineOptions.OPT_BROWSER)) {
			GlobleVargs.browserType = cmd.getOptionValue(CommandLineOptions.OPT_BROWSER);
		}
		
		if (cmd.hasOption(CommandLineOptions.OPT_SCRIPT)) {
			
		}
		
		
	}
	
	private List<IScript> searchScriptFiles(){
		
		return null;
	}
}
