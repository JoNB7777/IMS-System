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

}
