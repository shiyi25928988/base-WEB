package base.crawler.config;

import java.util.regex.Pattern;

/**
 * @author yshi
 *
 */
public class ExtendTypeFilter {
	private static boolean enabled = false;
	private static String extendType;

	public static void setType(final String type) {
		ExtendTypeFilter.extendType = type;
		ExtendTypeFilter.enabled = true;
	}

	public static boolean isMatch(final String type) {
		if (ExtendTypeFilter.enabled) {
			if(Pattern.matches(ExtendTypeFilter.extendType, type)) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isNotMatch(final String type) {
		if (ExtendTypeFilter.enabled) {
			if(Pattern.matches(ExtendTypeFilter.extendType, type)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

}
