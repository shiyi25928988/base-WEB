package crawler.base.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import base.crawler.config.WebSiteType;

public class DomainNameTest {

	@Test
	public void test() {
		assertEquals(WebSiteType.getWebSiteType("sohu.com"), WebSiteType.SOHU);	
	}
	
	@Test
	public void test1() {
		assertEquals(WebSiteType.getWebSiteType("sootoo.com"), WebSiteType.SOOTOO);	
	}
}
