package org.yshi;

import org.yshi.client.baseGWTTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class baseGWTSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for baseGWT");
    suite.addTestSuite(baseGWTTest.class);
    return suite;
  }
}
