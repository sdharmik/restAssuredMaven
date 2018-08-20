package com.test.restAssured.satc;

import org.junit.Before;

import com.test.restAssured.BaseTest;

import io.restassured.RestAssured;

public class ClientTest extends BaseTest{
	@Before
	public void setUp(){
		setUp();
		RestAssured.basePath = "/deviceServices/api/v1/assets";
	}

}
