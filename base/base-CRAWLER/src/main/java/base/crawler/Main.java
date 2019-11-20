package base.crawler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import base.crawler.cli.CommandLineOptions;
import base.crawler.crawler.EveryThingCrawler;
import lombok.extern.slf4j.Slf4j;

/**
 * Entry class
 * @author yshi
 *
 */
@Slf4j
public class Main {

	private static CommandLineParser parser = new DefaultParser();
	
	private static HelpFormatter formatter = new HelpFormatter();
	
	/**
	 * @param args
	 */
	public static void main(String... args) {
		
		try {
			CommandLine cmd = parser.parse(CommandLineOptions.getOptions(), args);
			
			if( cmd.hasOption(CommandLineOptions.OPT_HELP) ) {
				formatter.printHelp("crawl", CommandLineOptions.getOptions(), true);
			}
			
			String[] addresses = cmd.getOptionValues(CommandLineOptions.OPT_ADDRESS);
			
			if(Objects.nonNull(addresses)) {
				Arrays.stream(addresses).forEach(a ->{
					try {
						if (isAddressReacheable(a)) {
							CrawlerLauncher.start(EveryThingCrawler.class, a);
						} else {
							log.error(a + "is unReacheable in 500ms");
						}
					} catch (Exception e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				});
			}
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * @param host
	 * @return
	 * @throws IOException
	 */
	private static boolean isAddressReacheable(String host) throws IOException {
		String addr = host;
		if(host.startsWith("http")) {
			addr = host.substring(host.indexOf("//") + 2);
		}
		if(addr.contains("/")) {
			addr = addr.substring(addr.indexOf("/") + 1);
		}
		InetAddress address = InetAddress.getByName(addr);
		return address.isReachable(500);
	}
}
