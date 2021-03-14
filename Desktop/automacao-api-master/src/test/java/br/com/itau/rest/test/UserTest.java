package br.com.itau.rest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;

import br.com.itau.rest.model.Users;
import br.com.itau.rest.utils.ResponseUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserTest {

	private final String URL = "https://petstore.swagger.io/v2/user";
	
	private void sendPost(Users user) {
		
		Gson gson = new Gson ();

		String body = gson.toJson(user);

		Response r = ResponseUtils.responsePost(body, URL);
        assertEquals(r.getStatusCode(), 200);    
	}
	//1) Crie a usuaria Maria Assunção
	//6) A partir de uma massa de teste elaborada por você, crie 5 usuários e 10 pets (5 dogs e 5 cats)
	
	@Test
	public void createUser() {
		Users user = new Users(1, "Maria Assunção", "Maria", "Assunção", "maria@gmail.com", "123456", "20523374", 1);
		sendPost(user);
		
		Users user1 = new Users(2, "Leonardo menezes", "leonardo", "menezes", "leo@gmail.com", "123456", "20523374", 1);
		sendPost(user);
		
		Users user2 = new Users(3, "Vinicius Ferreira", "Vinicius", "Ferreira", "vini@gmail.com", "123456", "20523374", 1);
		sendPost(user);
		
		Users user3 = new Users(4, "Jose Correia", "Jose", "Correira", "jose@gmail.com", "123456", "20523374", 1);
		sendPost(user);
		
		Users user4 = new Users(5, "Fellipe geraldi", "felipe", "geraldi", "geraldi@gmail.com", "123456", "20523374", 1);
		sendPost(user);
		
		Users use5 = new Users(6, "Fabio Ferreira", "Fabio", "Ferreira", "fabio@gmail.com", "123456", "20523374", 1);
		sendPost(user);
	}
}
