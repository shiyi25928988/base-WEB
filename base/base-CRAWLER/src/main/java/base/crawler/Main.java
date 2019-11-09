package base.crawler;

import java.util.Arrays;

import base.crawler.crawler.EveryThingCrawler;

public class Main {
	
	public static void main(String...strings) {
		if(strings.length < 1) {
			System.out.println("Invalid arguments");
		}else {
			
			Arrays.stream(strings).forEach(s ->{
				try {
					CrawlerLauncher.start(EveryThingCrawler.class, s);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
}
