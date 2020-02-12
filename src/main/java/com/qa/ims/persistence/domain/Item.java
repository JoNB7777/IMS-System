package com.qa.ims.persistence.domain;

public class Item {
	private Long id;
	private String itemName;
	private Double itemValue;
	
	public Item(Long id, String itemName, Double value) {
		this.setId(id);
		this.setItemName(itemName);
		this.setItemValue(value);
	}
	public Item(String itemName, Double value) {
		this.setItemName(itemName);
		this.setItemValue(value);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getItemValue() {
		return itemValue;
	}
	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

}
