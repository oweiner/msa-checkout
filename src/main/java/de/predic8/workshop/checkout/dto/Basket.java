package de.predic8.workshop.checkout.dto;

import java.util.List;

public class Basket {
	private String uuid;
	private String customer;
	private List<Article> items;

	public Basket() {
	}

	public String getUuid() {
		return this.uuid;
	}

	public String getCustomer() {
		return this.customer;
	}

	public List<Article> getItems() {
		return this.items;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setItems(List<Article> items) {
		this.items = items;
	}
}