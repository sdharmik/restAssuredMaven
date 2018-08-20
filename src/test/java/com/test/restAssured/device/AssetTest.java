package com.test.restAssured.device;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.test.restAssured.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class AssetTest extends BaseTest{

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://10.49.65.12:8080";
		RestAssured.basePath = "/deviceServices/api/v1";
	}

	@AfterClass
	public static void tearDown() {
		RestAssured.basePath = "";
		RestAssured.baseURI = null;
	}


	
	@Test
	public void testGetAssetByServiceTag(){
		String serviceTag = "9JTY104";
		Header apiKey = new Header("SupportAssist-API-Key", "65D3F3B736924DF1B609F27D660B3109");
		RestAssured.
				given().
				header(apiKey).
				param("serviceTag", serviceTag).param("retailInfo", "false")
				.when().request("GET", "/assets")
				.then().log().body().statusCode(HttpStatus.SC_OK).and().contentType(ContentType.JSON).and().
				body("find {it.serviceTag == '"+serviceTag+"'}.retailInfo",  nullValue())
				.body("find {it.serviceTag == '"+serviceTag+"'}.localChannel",  notNullValue())
				.body("find {it.serviceTag == '"+serviceTag+"'}.companyNumber",  notNullValue())
				.body("find {it.serviceTag == '"+serviceTag+"'}.productLOB",  notNullValue())
//				.body("find {it.serviceTag == '"+serviceTag+"'}.shippedDate",  isA(Date.class) )
				.and().header("Cache-control", containsString("no-cache"))
				.header("Pragma", containsString("no-cache"));
				
	}

	@Test
	public void testGetAssetByServiceTag_withRetailInfo(){
		String serviceTag = "9JTY104";
		Header apiKey = new Header("SupportAssist-API-Key", "65D3F3B736924DF1B609F27D660B3109");
		RestAssured.
				given().
				header(apiKey).
				param("serviceTag", serviceTag).param("retailInfo", "true")
				.when().request("GET", "/assets")
				.then().statusCode(HttpStatus.SC_OK).and().contentType(ContentType.JSON).and().
				body("find {it.serviceTag == '"+serviceTag+"'}.retailInfo",  notNullValue())
				.body("find {it.serviceTag == '"+serviceTag+"'}.localChannel",  notNullValue())
				.body("find {it.serviceTag == '"+serviceTag+"'}.companyNumber",  notNullValue())
				.body("find {it.serviceTag == '"+serviceTag+"'}.productLOB",  notNullValue())
				.body("find {it.serviceTag == '"+serviceTag+"'}.shippedDate",  notNullValue());
//				.bo
				//.and().header("Cache-control", "no-cache");
				
	}

	@Test
	public void testGetAssetByServiceTag_noHeader(){
		RestAssured.
				given().
				param("serviceTag", "125PTF1").param("retailInfo", "true")
				.when().request("GET", "/assets")
				.then().statusCode(HttpStatus.SC_UNAUTHORIZED).and().contentType(ContentType.JSON);
	}

	@Test
	public void testAssetByServiceTag_Success() {
		String serviceTag = "9JtY104";
		String localChannelAndCompanyNumber = "08";
		Header apiKey = new Header("SupportAssist-API-Key", "65D3F3B736924DF1B609F27D660B3109");
		RestAssured.given().header(apiKey).param("serviceTag", serviceTag)
		.param("retailInfo", "true")
		.when().get("/assets")
		.then().contentType(ContentType.JSON).log().body().statusCode(HttpStatus.SC_OK)
		.body("[0].serviceTag", equalTo(serviceTag.toUpperCase()))
		.body("[0].localChannel", equalTo(localChannelAndCompanyNumber))
		.body("[0].companyNumber", equalTo(localChannelAndCompanyNumber))
		.body("[0].productLOB", equalTo("4PD"))
		.body("[0].shippedDate", equalTo("2016-05-01T05:00:00+0000"))
		.body("[0].retailInfo", notNullValue())
		.and().body("[0].retailInfo.retailTag", equalTo(Boolean.FALSE))
		.body("[0].retailInfo.retailTagRegistration", equalTo(Boolean.FALSE));
	}

	@Test
	public void testAssetByServiceTag_invalidServiceTag_Failure() {
		String serviceTag = "ABCDEF";
		Header apiKey = new Header("SupportAssist-API-Key", "65D3F3B736924DF1B609F27D660B3109");
		RestAssured.given().header(apiKey).param("serviceTag", serviceTag)
		//.param("retailInfo", "true")
		.when().get("/assets")
		.then().contentType(ContentType.JSON).log().body().statusCode(HttpStatus.SC_OK)
		.body("[0].serviceTag", equalTo(serviceTag))
		.body("[0].localChannel", nullValue())
		.body("[0].companyNumber", nullValue())
		.body("[0].productLOB", nullValue())
		.body("[0].shippedDate", nullValue())
		.body("[0].retailInfo", nullValue());
//		.and().body("$1.serviceTag",equalTo(serviceTag.toUpperCase()));
	}

}
