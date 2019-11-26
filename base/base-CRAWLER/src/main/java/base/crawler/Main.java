package base.crawler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;

import javax.print.DocFlavor.URL;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import base.crawler.cli.CommandLineOptions;
import base.crawler.cli.GlobalVars;
import base.crawler.config.CrawlerConfig;
import base.crawler.crawler.EveryThingCrawler;
import base.crawler.guice.CrawlerService;
import lombok.extern.slf4j.Slf4j;

/**
 * Entry class
 * 
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
		
		Injector injector = Guice.createInjector(new CrawlerModule());
		CrawlerService service = injector.getInstance(CrawlerService.class);
		try {
			CommandLine cmd = parser.parse(CommandLineOptions.getOptions(), args);
			
			/** --help */
			if (cmd.hasOption(CommandLineOptions.OPT_HELP)) {
				formatter.printHelp("crawl", CommandLineOptions.getOptions(), true);
			}
			
			/** --depth */
			if (cmd.hasOption(CommandLineOptions.OPT_DEPTH)) {
				CrawlerConfig.maxDepthOfCrawling = Integer.parseInt(cmd.getOptionValue(CommandLineOptions.OPT_DEPTH));
			}
			
			/** --robot */
			if (cmd.hasOption(CommandLineOptions.OPT_ROBOT)) {
				GlobalVars.isRobotTxtEnabled = true;
				GlobalVars.robotUserAgent = cmd.getOptionValue(CommandLineOptions.OPT_ROBOT);
			}
			
			/** --crawler number */
			if (cmd.hasOption(CommandLineOptions.OPT_CRAWLER_NUM)) {
				GlobalVars.crawlerNumber = Integer.parseInt(cmd.getOptionValue(CommandLineOptions.OPT_CRAWLER_NUM));
			}
			
			/** --time-interval */
			if (cmd.hasOption(CommandLineOptions.OPT_TIME_INTERVAL)) {
				CrawlerConfig.politenessDelay = Integer.parseInt(cmd.getOptionValue(CommandLineOptions.OPT_TIME_INTERVAL));
			}
			
			/** --address */
			if (cmd.hasOption(CommandLineOptions.OPT_ADDRESS)) {
				String[] addresses = cmd.getOptionValues(CommandLineOptions.OPT_ADDRESS);

				if (Objects.nonNull(addresses) && addresses.length > 0) {
					Arrays.stream(addresses).forEach(a -> {
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
	private static boolean isAddressReacheable(String addr) throws IOException {
		java.net.URL url = new java.net.URL(addr);
		String host = url.getHost();
		InetAddress address = InetAddress.getByName(host);
		return address.isReachable(500);// 500ms
	}
}
