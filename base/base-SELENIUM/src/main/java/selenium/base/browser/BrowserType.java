package selenium.base.browser;

import selenium.base.exception.UnsupportedBrowserException;

/**
 * @author shiyi
 *
 */
public enum BrowserType {
	Chrome, Edge, Firefox, IE, Opera, Safari;

	/**
	 * @param browserType
	 * @return
	 * @throws UnsupportedBrowserException
	 */
	public static BrowserType getBrowserType(String browserType) throws UnsupportedBrowserException {
		if (browserType.contains("chrome")) {
			return BrowserType.Chrome;
		} else if (browserType.contains("edge")) {
			return BrowserType.Edge;
		} else if (browserType.contains("firefox")) {
			return BrowserType.Firefox;
		} else if (browserType.contains("ie")) {
			return BrowserType.IE;
		}else if(browserType.contains("opera")) {
			return BrowserType.Opera;
		}else if(browserType.contains("safari")) {
			return BrowserType.Safari;
		}
		throw new UnsupportedBrowserException(browserType + " is not supported!");
	}
}
