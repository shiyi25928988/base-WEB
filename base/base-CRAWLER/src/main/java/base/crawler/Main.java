package base.crawler;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import base.crawler.cli.CommandLineOptions;
import base.crawler.crawler.EveryThingCrawler;

public class Main {

	private static CommandLineParser parser = new DefaultParser();
	
	private static HelpFormatter formatter = new HelpFormatter();
	
	public static void main(String... args) {
		
		try {
			CommandLine cmd = parser.parse(CommandLineOptions.getOptions(), args);
			
			if( cmd.hasOption(CommandLineOptions.OptionType.help.get()) ) {
				formatter.printHelp("crawl", CommandLineOptions.getOptions(), true);
			}
			
			if( cmd.hasOption(CommandLineOptions.OptionType.address.get()) ) {
				
				Arrays.stream(cmd.getOptionValues(CommandLineOptions.OptionType.address.get())).forEach(arg -> {
					try {
						CrawlerLauncher.start(EveryThingCrawler.class, arg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	}
}
