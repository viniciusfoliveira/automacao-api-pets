package br.com.itau.rest.model;

import java.util.Collection;
import java.util.Map;

public class Pets {

	private int id;
	
	private Category category;
	
	private String name;
	
	private String [] photoUrls;
	
	private Collection<Map<String, Object>> tags;
	
	private String status;

	public Pets(int id, Category category, String name, String [] photoUrls, Collection<Map<String, Object>> tags, String status) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}

	public Collection<Map<String, Object>> getTags() {
		return tags;
	}

	public void setTags(Collection<Map<String, Object>> tags) {
		this.tags = tags;
	}
	
}
