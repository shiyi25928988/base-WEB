package base.crawler;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import base.crawler.crawler.EveryThingCrawler;

public class Main {

	private static org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();
	
	private static CommandLineParser parser = new DefaultParser();
	
	private static HelpFormatter formatter = new HelpFormatter();
	
	private static final boolean hasArg = true;
	private static final boolean notHasArgs = false;

	static {
		options.addOption(new Option("a", "address", hasArg, "The target web sit address."));
		options.addOption(new Option("t", "type", hasArg, "The target file type you want keep."));
		options.addOption(new Option("p", "path", hasArg, "The file store folder path."));
		
	}

	public static void main(String... args) {
		
		try {
			CommandLine cmd = parser.parse(options, args);
			
			if( cmd.hasOption( "address" ) ) {
			    // initialise the member variable
			    System.out.println(cmd.getOptionValue( "address" ));
			}
			
			formatter.printHelp("crawl", options);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		if (args.length < 1) {
//			System.out.println("Invalid arguments");
//		} else {
//
//			Arrays.stream(args).forEach(s -> {
//				try {
//					CrawlerLauncher.start(EveryThingCrawler.class, s);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
//		}
	}
}
