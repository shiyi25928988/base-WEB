package crawler.base.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	static	String  content = ".com/album/";
	private static final String domainSuffixReg = ".(com|net|org|cn|edu|gov|top|info|xxx|idv|name|coop|museum|aero|pro|biz|int|xyz|co|mobi|club|rec|asia|travel|vip|win).";
	
	
	
	public static void main(String...strings) {
//		Pattern p = Pattern.compile(domainSuffixReg);
//		
//		Matcher m = p.matcher(content);
//		
//		System.out.println(m.find());
		
//		if(Pattern.matches(domainSuffixReg, content)) {
//			System.out.println("match");
//		}
		
		System.out.println(content.lastIndexOf('/'));
		System.out.println(content.length());
		
	}
}
