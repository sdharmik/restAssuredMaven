package com.test.restAssured.satc;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;

public  class Resttest {

	@Before
	public void setup() {
		RestAssured.baseURI="http://10.49.65.11:8080";
		RestAssured.basePath="/clientServices/api/v1/";
	}
	
	@Test
	public void testPutAssetByGetProfileInfo(){
		Header Apikey=new Header("SupportAssist-API-Key","A4B600D628A24C868561FFE70E963100");
		RestAssured.
			given().header(Apikey).pathParam("clientID", 1234).
			param("idmProfileId" ,"OYjSnJ/oxdUafgjruY7CFTxM03RaaTVlZXyEmV4fLyQ=").param("type","SAAGENT")
			.when().request(Method.GET,"clients/{clientID}")
			.then().statusCode(HttpStatus.SC_BAD_REQUEST);
	}
	
}
