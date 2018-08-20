package com.test.restAssured.device;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import io.restassured.RestAssured;

@RunWith(Suite.class)
@SuiteClasses(value={DeviceTest.class, AssetTest.class, DellConnectPassCodeTest.class})
public class DeviceTestSuite {

	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://10.49.65.12:8080";
		RestAssured.basePath = "/deviceServices/api/v1";
	}
	
	@AfterClass
	public static void tearDown(){
		RestAssured.baseURI = null;
		RestAssured.basePath = null;
	}
}
