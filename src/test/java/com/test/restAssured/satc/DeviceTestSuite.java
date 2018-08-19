package com.test.restAssured.satc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value={DeviceTest.class, SATCTest.class})
public class DeviceTestSuite {

}
