package com.test.restAssured.satc;

import static org.hamcrest.CoreMatchers.containsString;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public abstract class BaseTest {

	public void setUp(){
		RestAssured.baseURI = "http://10.49.65.12:8080";
	}
	
	
	public ValidatableResponse testResponseHeaders(ValidatableResponse resp){
		return resp.and().header("Cache-control", containsString("no-cache"));
	}
}
