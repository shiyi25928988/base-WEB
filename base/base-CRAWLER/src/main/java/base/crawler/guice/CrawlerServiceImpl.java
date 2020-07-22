package base.crawler.guice;

import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import com.google.inject.Inject;

import base.crawler.cli.CommandLineOptions;
import base.crawler.cli.GlobalVars;
import base.crawler.config.CrawlerConfig;
import base.crawler.crawlerType.AbstractCrawler;
import base.crawler.crawlerType.EveryThingCrawler;
import base.crawler.exceptions.AuthInfoInvalidException;
import base.crawler.guice.service.CrawlerLauncher;
import base.crawler.guice.service.CrawlerLauncherService;
import base.crawler.utils.CrawlerUtils;
import db.base.entity.NewsEntity;
import db.base.service.NewsService;
import edu.uci.ics.crawler4j.crawler.authentication.AuthInfo;
import edu.uci.ics.crawler4j.crawler.authentication.FormAuthInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class CrawlerServiceImpl implements CrawlerService {

	private static CommandLineParser parser = new DefaultParser();

	private static HelpFormatter formatter = new HelpFormatter();
	
	private static String fileType = "";
	
	private CrawlerLauncherService crawlerLauncher;
	
	private Class<? extends AbstractCrawler> crawlerClass;
	
	
	
	public CrawlerServiceImpl(CrawlerLauncherService crawlerLauncher, Class<? extends AbstractCrawler> crawlerClass) {
		this.crawlerLauncher = crawlerLauncher;
		this.crawlerClass = crawlerClass;
	}

	@Override
	public void start(String... args) throws Exception {
		
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
				CrawlerConfig.politenessDelay = Integer
						.parseInt(cmd.getOptionValue(CommandLineOptions.OPT_TIME_INTERVAL));
			}
			
			/** form authentication */
			if (cmd.hasOption(CommandLineOptions.OPT_AUTHURL)) {
				String url = cmd.getOptionValue(CommandLineOptions.OPT_AUTHURL);
				AuthInfo authInfo;
				//FormAuthInfo 
				
				if (CrawlerUtils.isAddressReacheable(url)) {
					
					if (cmd.hasOption(CommandLineOptions.OPT_USERNAME)) {//get user name
						String userName = cmd.getOptionValue(CommandLineOptions.OPT_USERNAME);
						
						if (cmd.hasOption(CommandLineOptions.OPT_PASSWD)) {//get password
							String password = cmd.getOptionValue(CommandLineOptions.OPT_PASSWD);
							
							if(cmd.hasOption(CommandLineOptions.OPT_USERNAME_ELEMENT)) {
								String userNameEle = cmd.getOptionValue(CommandLineOptions.OPT_USERNAME_ELEMENT);
								
								if (cmd.hasOption(CommandLineOptions.OPT_PASSWD_ELEMENT)) {//get password
									String passwordEle = cmd.getOptionValue(CommandLineOptions.OPT_PASSWD_ELEMENT);
									
									authInfo = new FormAuthInfo(userName, password, url, userNameEle, passwordEle);
									CrawlerConfig.setAuth(authInfo);
								}
							}
							
						}
					}
					
				} else {
					throw new AuthInfoInvalidException("Invalid authentication parameter.");
				}
			}
			
			/** --file filter */
			if (cmd.hasOption(CommandLineOptions.OPT_FILE_FILTER)) {
				//TODO
				GlobalVars.targetFileType = cmd.getOptionValue(CommandLineOptions.OPT_FILE_FILTER);
			}

			/** --address */
			if (cmd.hasOption(CommandLineOptions.OPT_ADDRESS)) {
				String[] addresses = cmd.getOptionValues(CommandLineOptions.OPT_ADDRESS);

				if (Objects.nonNull(addresses) && addresses.length > 0) {
					Arrays.stream(addresses).forEach(addr -> {
						try {
							if (CrawlerUtils.isAddressReacheable(addr)) {
								crawlerLauncher.start(crawlerClass, addr);
							} else {
								log.error(addr + "is unReacheable.");
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
}
