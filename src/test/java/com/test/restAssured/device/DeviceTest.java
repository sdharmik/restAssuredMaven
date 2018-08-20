package com.test.restAssured.device;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.test.restAssured.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Header;

public class DeviceTest extends BaseTest{

	@BeforeClass
	public static void init(){
		RestAssured.baseURI = "http://10.49.65.12:8080";
		RestAssured.basePath = "/deviceServices/api/v1";
	}
	
	@Test
	public void testPutAssetByServiceTag(){

		Header apiKey = new Header("SupportAssist-API-Key", "65D3F3B736924DF1B609F27D660B3109");
		RestAssured.
				given().
				header(apiKey).
				param("serviceTag", "125PTF1").param("retailInfo", "true")
				.when().request("PUT", "/assets")
				.then().log().body()
				.assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).and()
				.assertThat().body("code", equalTo("1110"),"message", containsString("not supported"));
	}

	@AfterClass
	public static void cleanUp(){
		RestAssured.basePath = null;
		RestAssured.baseURI = null;
	}
	
}
