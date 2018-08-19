package com.test.restAssured.satc;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SATCTest {

	@Test
	public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
		RestAssured.baseURI = "http://satc.dell.com";
		RequestSpecification reqSpec = RestAssured.given().when();
		Response resp = reqSpec.get();
		int statusCode = resp.getStatusCode();
		assertEquals(HttpStatus.SC_OK, statusCode);
	}
}
