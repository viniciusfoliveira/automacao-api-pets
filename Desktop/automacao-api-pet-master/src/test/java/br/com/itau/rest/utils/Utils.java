package br.com.itau.rest.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Utils {

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
	
	public static JSONArray readJson(String path) throws IllegalAccessException, IOException {

		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb = sb.append(line);
		}

		br.close();
		fr.close();

		JSONArray ja = new JSONArray(sb.toString());

		return ja;

	}

}
