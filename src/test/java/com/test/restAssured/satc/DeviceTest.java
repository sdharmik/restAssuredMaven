package com.test.restAssured.satc;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Date;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class DeviceTest extends BaseTest{

	@Before
	public void setUp(){
		super.setUp();
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
				.then().log().body().statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
	}

	
	@Test
	public void testGetAssetByServiceTag(){

		Header apiKey = new Header("SupportAssist-API-Key", "65D3F3B736924DF1B609F27D660B3109");
		RestAssured.
				given().
				header(apiKey).
				param("serviceTag", "9JtY104").param("retailInfo", "false")
				.when().request("GET", "/assets")
				.then().log().body().statusCode(HttpStatus.SC_OK).and().contentType(ContentType.JSON).and().
				body("find {it.serviceTag == '9JTY104'}.retailInfo",  nullValue())
				.body("find {it.serviceTag == '9JTY104'}.localChannel",  notNullValue())
				.body("find {it.serviceTag == '9JTY104'}.companyNumber",  notNullValue())
				.body("find {it.serviceTag == '9JTY104'}.productLOB",  notNullValue())
				.body("find {it.serviceTag == '9JTY104'}.shippedDate",  isA(Date.class) )
//				.bo
				.and().header("Cache-control", containsString("no-cache"))
				.header("Pragma", containsString("no-cache"));
				
	}

	@Test
	public void testGetAssetByServiceTag_withRetailInfo(){

		Header apiKey = new Header("SupportAssist-API-Key", "65D3F3B736924DF1B609F27D660B3109");
		RestAssured.
				given().
				header(apiKey).
				param("serviceTag", "9JtY104").param("retailInfo", "true")
				.when().request("GET", "/assets")
				.then().statusCode(HttpStatus.SC_OK).and().contentType(ContentType.JSON).and().
				body("find {it.serviceTag == '9JTY104'}.retailInfo",  notNullValue())
				.body("find {it.serviceTag == '9JTY104'}.localChannel",  notNullValue())
				.body("find {it.serviceTag == '9JTY104'}.companyNumber",  notNullValue())
				.body("find {it.serviceTag == '9JTY104'}.productLOB",  notNullValue())
				.body("find {it.serviceTag == '9JTY104'}.shippedDate",  notNullValue());
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

}
