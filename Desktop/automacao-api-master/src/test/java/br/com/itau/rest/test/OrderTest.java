package br.com.itau.rest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.gson.Gson;

import br.com.itau.rest.model.Orders;
import br.com.itau.rest.utils.ResponseUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OrderTest {

	private final String URL = "https://petstore.swagger.io/v2/store/order";

	private final String URL_GET = "https://petstore.swagger.io/v2/store/order/4";
	
	private void sendPost(Orders order) {

		Gson gson = new Gson();
		String body = gson.toJson(order);

		Response r = ResponseUtils.responsePost(body, URL);

		assertEquals(r.getStatusCode(), 200);

	}
	
	@Test
	public void validNotNull(){
	
		Response r = ResponseUtils.responseGet(URL_GET);
		
		JsonPath js = new JsonPath(r.asString());

		assertTrue(js.get("id").toString() != null);
		assertTrue(js.get("petId").toString() != null);
		assertTrue(js.get("quantity").toString() != null);
		assertTrue(js.get("shipDate").toString() != null);
		assertTrue(js.get("status").toString() != null);
		assertTrue(js.get("complete").toString() != null);
	}
	
	@Test
	public void validType() {

		Response r = ResponseUtils.responseGet(URL_GET);
		
		JsonPath js = new JsonPath(r.asString());

		assertTrue(js.get("id").getClass().getSimpleName().equals("Integer"));
		assertTrue(js.get("petId").getClass().getSimpleName().equals("Integer"));
		assertTrue(js.get("quantity").getClass().getSimpleName().equals("Integer"));
		assertTrue(js.get("shipDate").getClass().getSimpleName().equals("String"));
		assertTrue(js.get("status").getClass().getSimpleName().equals("String"));
		assertTrue(js.get("complete").getClass().getSimpleName().equals("Boolean"));
	}
	
	// 3) Faça a venda do Brutus para a Maria Assunção
	// 7) Faça a venda de 1 dog e 1 cat para cada usuário

	@Test
	public void sellAnimal() {

		Orders order = new Orders(1, 1, 1, "2020-12-12T21:09:42.437Z", "placed", true);
		sendPost(order);

		int userId = 2;

		int count = 1;

		int quantity = 1;

		for (int petId = 2; petId <= 11; petId++) {

			while (userId < 5) {

				Orders orders = new Orders(userId, petId, userId, "2020-12-12T21:09:42.437Z", "placed", true);
				sendPost(orders);

				if (count == 2) {

					count = 1;
					userId++;
					break;
				}

				count++;
				break;
			}
		}
	}

}
