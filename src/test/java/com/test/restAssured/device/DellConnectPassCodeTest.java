package com.test.restAssured.device;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.test.restAssured.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class DellConnectPassCodeTest extends BaseTest{

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://10.49.65.12:8080";
		RestAssured.basePath = "/deviceServices/api/v1";
	}
	
	@Test
	public void testDellConnectPasscode_Success(){
		String serviceTag = "CEHY104";
		Header apiKey = new Header("SupportAssist-API-Key","65D3F3B736924DF1B609F27D660B3109");
		RestAssured.given().header(apiKey).param("serviceTag", serviceTag)
		.when().get("/dellConnectPasscodes")
		.then().log().body().contentType(ContentType.JSON);
	}
	
	@AfterClass
	public static void tearDown() {
		RestAssured.baseURI = null;
		RestAssured.basePath = null;
	}
	
}
