package base.crawler.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.google.common.base.Strings;

public class HtmlParser {

	public static List<String> getTagContent(String html, String charset, String tagName) throws ParserException {
		List<String> list = new ArrayList<>();
		Parser parser = Parser.createParser(html, charset);
		parser.setEncoding(parser.getEncoding());
		NodeList nodes = parser.parse(new TagNameFilter(tagName));
		Node[] nodeArray = new Node[nodes.size()];
		nodes.copyToNodeArray(nodeArray);
		Arrays.asList(nodeArray).forEach(node -> {
			list.add(node.toHtml());
		});
		return list;
	}

	public static List<String> getTagContent(String URL, String tagName) throws ParserException {

		List<String> list = new ArrayList<>();
		Parser parser = new Parser();
		parser.setURL(URL);
		parser.setEncoding(parser.getEncoding());
		NodeList nodes = parser.parse(new TagNameFilter(tagName));
		Node[] nodeArray = new Node[nodes.size()];
		nodes.copyToNodeArray(nodeArray);
		Arrays.asList(nodeArray).forEach(node -> {
			list.add(node.toHtml());
		});
		return list;
	}

	public static Date parseDate(String dateStr) throws ParseException {
		if (Strings.isNullOrEmpty(dateStr)) {
			return new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.parse(dateStr);
	}

	public static String getImageAttr(String element) {
		return getAttr(element, "img", "src");
	}

	public static String getAttr(String element, String tag, String attr) {
		String reg = "<" + tag + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s*?>";
		Matcher m = Pattern.compile(reg).matcher(element);
		while (m.find()) {
			String r = m.group(1);
			if(r.contains("\"")) {
				return r.subSequence(0, r.indexOf('"')).toString();
			}
			return r;
		}
		return "";
	}
	
	public static void main(String... strings) {
		try {
			String url = "https://www.jianshu.com/p/a499b56456ed";
			List<String> list = getTagContent(url, "img");

			list.forEach(s -> {
				System.out.println(s);
				System.out.println(getImageAttr(s));
				//System.out.println(getImageAttr(s).substring(1, getImageAttr(s).indexOf('"')));
			});
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		String ss = "<img class=\"aligncenter j-lazy\" alt=\"江疏影变身酷我畅听声音陪伴官 深挖新时代女性热议话题\" src=\"http://www.jjg630.com/uploads/temfile/5f1a86023bab6.jpeg\" data-original=\"http://www.jjg630.com/uploads/tem_file/5f1a86023bab6.jpeg\" style=\"display: block;\">";
//		ss = "<img class=\"aligncenter\" src=\"http://www.jjg630.com/uploads/tem_file/5f1a86023bab6.jpeg\">";
//		System.out.println(getImageAttr(ss));
//		System.out.println("ss");

	}
}
