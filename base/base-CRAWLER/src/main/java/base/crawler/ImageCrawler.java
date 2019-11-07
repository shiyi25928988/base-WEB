package base.crawler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

import com.google.common.io.Files;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class ImageCrawler extends AbstractCrawler {

	@Override
	protected Pattern getPattern() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final Pattern filters = Pattern.compile(
			".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/*
	 * 正则匹配图片文件
	 */
	private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|png|tiff?))$");

	private static File storageFolder; // 爬取的图片本地存储地址
	private static String[] crawlDomains; // 指定要爬取的域名

	/**
	 * 配置方法 指定域名和本地存储文件
	 * 
	 * @param domain
	 * @param storageFolderName
	 */
	public static void configure(String[] domain, String storageFolderName) {
		crawlDomains = domain;

		storageFolder = new File(storageFolderName); // 实例化
		if (!storageFolder.exists()) { // 假如文件不存在
			storageFolder.mkdirs(); // 我们创建一个
		}
	}

	/**
	 * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
	 * 第一个参数referringPage封装了当前爬取的页面信息 第二个参数url封装了当前爬取的页面url信息
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase(); // 得到小写的url
		if (filters.matcher(href).matches()) { // 过滤指定后缀url
			return false;
		}

		if (imgPatterns.matcher(href).matches()) { // 匹配指定图片后缀文件
			return true;
		}

		for (String domain : crawlDomains) { // 匹配指定域名
			if (href.startsWith(domain)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 当我们爬到我们需要的页面，这个方法会被调用，我们可以尽情的处理这个页面 page参数封装了所有页面信息
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL(); // 获取url

		// 只爬取大于等于10kB的图片文件
		if (!imgPatterns.matcher(url).matches() || !((page.getParseData() instanceof BinaryParseData)
				|| (page.getContentData().length < (10 * 1024)))) {
			return;
		}

		// 获取图片后缀
		String extension = url.substring(url.lastIndexOf('.'));
		String hashedName = UUID.randomUUID() + extension; // 通过uuid 拼接成唯一图片名称

		// 定义存储文件
		String filename = storageFolder.getAbsolutePath() + "/" + hashedName;
		try {
			Files.write(page.getContentData(), new File(filename)); // 把爬取到的文件存储到指定文件
			System.out.println("爬取图片的url:" + url);
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

}
