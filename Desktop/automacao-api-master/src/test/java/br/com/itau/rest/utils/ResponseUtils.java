package br.com.itau.rest.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ResponseUtils {

	public static Response responsePost (String body, String url) {
		Response r =  RestAssured.given().log().all()
				.header("Content-Type", "application/json")
				.accept("application/json")
				.body(body)
		.when().post(url);
		return r;
	}
	
	public static Response responseGet(String url) {
		Response r =  RestAssured.given().log().all()
				.header("Content-Type", "application/json")
				.accept("application/json")
		.when().get(url);
		return r;
		
	}
}
