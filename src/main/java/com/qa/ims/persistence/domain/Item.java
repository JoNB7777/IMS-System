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
	
	public String toString() {
		return "id:" + id + " item name:" + itemName + " item value:" + itemValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemValue == null) ? 0 : itemValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemValue == null) {
			if (other.itemValue != null)
				return false;
		} else if (!itemValue.equals(other.itemValue))
			return false;
		return true;
	}

}
