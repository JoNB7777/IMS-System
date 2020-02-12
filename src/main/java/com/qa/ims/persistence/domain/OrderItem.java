package com.qa.ims.persistence.domain;

public class OrderItem {
	private Long id;
	private Long orderId;
	private Long itemId;
	private int quantity;
	
	public OrderItem(Long id, Long orderId, Long itemId, int quantity) {
		this.setId(id);
		this.setOrderId(orderId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}
	
	public OrderItem(Long orderId, Long itemId, int quantity) {
		this.setOrderId(orderId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return "id:" + id + " order id:" + orderId + " itemId:" + itemId + "quantity:" + quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((quantity == (Integer) null) ? 0 : ((Integer) quantity).hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (quantity == (Integer) null) {
			if (other.quantity != (Integer) null)
				return false;
		} else if (quantity != other.quantity){
			return false;
		}
		return true;
	}

}
