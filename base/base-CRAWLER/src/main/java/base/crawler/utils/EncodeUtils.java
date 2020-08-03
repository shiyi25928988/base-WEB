package base.crawler.utils;

import java.io.UnsupportedEncodingException;

public class EncodeUtils {

	public static String ConvertFromGBKtoUTF8(String str) {
		try {
			return new String(str.getBytes("GBK"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String...strings) {
		String str = "缃戞槗鏂伴椈涓績鍥剧墖棰戦亾锛�24灏忔椂鍥剧墖鏂伴椈瀹炴椂鎺ㄩ�併��";
		System.out.println(ConvertFromGBKtoUTF8(str));
	}
}
