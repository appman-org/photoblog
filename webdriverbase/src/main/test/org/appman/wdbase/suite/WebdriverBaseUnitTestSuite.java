package org.appman.wdbase.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.appman.wdbase.page.AbstractPageTest;

/**
 * Created by Pieter on 10/25/2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AbstractPageTest.class
})
public class WebdriverBaseUnitTestSuite {
}
