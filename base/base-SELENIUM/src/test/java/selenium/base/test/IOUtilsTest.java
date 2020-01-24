package selenium.base.test;

import selenium.base.utils.IOUtils;

public class IOUtilsTest {
	
	public static void main(String...strings) {
		IOUtils.creatEmptyFile("d://test", "test.pdf");
		System.out.println(IOUtils.isFileExist("d://test//test2.pdf"));
	}
}
