package br.com.itau.rest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.Gson;

import br.com.itau.rest.model.Category;
import br.com.itau.rest.model.Pets;
import br.com.itau.rest.utils.ResponseUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetTest {

	private final String URL = "https://petstore.swagger.io/v2/pet";

	private final String URL_GET = "https://petstore.swagger.io/v2/pet/1";

	private void sendPost(int id, String name, Pets pet, Collection<Map<String, Object>> maps) {

		Map<String, Object> tags = new HashMap<String, Object>();
		tags.put("id", id);
		tags.put("name", name);

		maps.add(tags);

		Gson gson = new Gson();

		String body = gson.toJson(pet);

		Response r = ResponseUtils.responsePost(body, URL);

		assertEquals(r.getStatusCode(), 200);

		JsonPath js = new JsonPath(r.asString());

		// 5) Consulte a ordem gerada e valide se está correta
		// 9) Consulte as ordens geradas e valide se estão corretas

		assertTrue((js.get("status").toString().equals(pet.getStatus())));

	}

	// 2) Crie o pet Brutus (dog)
	@Test
	public void createPetPost2() {

		String[] photoUrls = { "string" };
		Collection<Map<String, Object>> maps = new HashSet<Map<String, Object>>();

		Pets pet = new Pets(1, new Category(1, "string"), "Brutus", photoUrls, maps, "available");
		sendPost(1, "string", pet, maps);
	}

	
	// valida campos nulos
	@Test
	public void validNotNull() {

		Response r = ResponseUtils.responseGet(URL_GET);

		System.out.println(r.asString());

		JsonPath js = new JsonPath(r.asString());

		assertTrue(js.get("id").toString() != null);
		assertTrue(js.get("category.id").toString() != null);
		assertTrue(js.get("category.name").toString() != null);
		assertTrue(js.get("name").toString() != null);
	}

	//valida os tipos do contrato
	@Test
	public void validType() {

		Response r = ResponseUtils.responseGet(URL_GET);

		JsonPath js = new JsonPath(r.asString());

		assertTrue(js.get("id").getClass().getSimpleName().equals("Integer"));
		assertTrue(js.get("category.id").getClass().getSimpleName().equals("Integer"));
		assertTrue(js.get("name").getClass().getSimpleName().equals("String"));
	}

	// 4) Mude o status da ordem de venda do Brutus para "delivered"

	@Test
	public void updatePet1() {

		String[] photoUrls = { "string" };

		Collection<Map<String, Object>> maps = new HashSet<Map<String, Object>>();

		Pets pet = new Pets(1, new Category(1, "string"), "Brutus", photoUrls, maps, "delivered");

		sendPost(1, "string", pet, maps);
	}

	// 6) A partir de uma massa de teste elaborada por você, crie 5 usuários e 10
	// pets (5 dogs e 5 cats)
	// 8) Mude o status das ordens de venda de dogs para "delivered" e de cats para
	// "approved"
	// 9) Consulte as ordens geradas e valide se estão corretas

	@Test
	public void generateMass() throws IllegalAccessException, JSONException, IOException {

		for (int i = 0; i < pets().length(); i++) {

			JSONObject ob = pets().getJSONObject(i);

			Map<String, Object> tags = new HashMap<String, Object>();

			JSONArray photosJson = ob.getJSONArray("photoUrls");

			String[] photos = new String[photosJson.length()];

			for (int k = 0; k < photosJson.length(); k++) {

				photos[k] = photosJson.getString(k);
			}

			JSONArray tagsArray = ob.getJSONArray("tags");

			int id = 0;
			String name = null;

			for (int j = 0; j < tagsArray.length(); j++) {

				JSONObject valor = tagsArray.getJSONObject(j);

				id = valor.getInt("id");
				name = valor.getString("name");
			}

			tags.put("id", id);
			tags.put("name", name);

			Collection<Map<String, Object>> maps = new HashSet<Map<String, Object>>();

			maps.add(tags);

			sendPost(1, "String",
					new Pets(ob.getInt("id"),
							new Category(ob.getJSONObject("category").getInt("id"),
									ob.getJSONObject("category").getString("name")),
							ob.getString("name"), photos, maps, ob.getString("status")),

					maps);
		}
	}

	private JSONArray pets() throws IllegalAccessException, IOException {

		FileReader fr = new FileReader("src/test/java/resources/json/massa.json");
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
