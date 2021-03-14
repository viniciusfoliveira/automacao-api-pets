package br.com.itau.rest.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.itau.rest.model.Users;
import br.com.itau.rest.utils.Utils;
import io.restassured.response.Response;

public class UserTest {

	private final String URL = "https://petstore.swagger.io/v2/user";

	private void sendPost(Users user) {

		Gson gson = new Gson();

		String body = gson.toJson(user);

		Response r = Utils.responsePost(body, URL);
		assertEquals(r.getStatusCode(), 200);

		System.out.println(r.asString());
	}
	// 1) Crie a usuaria Maria Assunção
	// 6) A partir de uma massa de teste elaborada por você, crie 5 usuários e 10

	@Test
	public void createUser() throws IllegalAccessException, IOException {

		for (int i = 0; i < Utils.readJson("src/test/java/resources/json/users.json").length(); i++) {

			JSONObject ob = Utils.readJson("src/test/java/resources/json/users.json").getJSONObject(i);

			sendPost(new Users(ob.getInt("id"), ob.getString("username"), ob.getString("firstName"),
					ob.getString("lastName"), ob.getString("email"), ob.getString("password"), ob.getString("phone"),
					ob.getInt("userStatus")));

		}
	}
}
