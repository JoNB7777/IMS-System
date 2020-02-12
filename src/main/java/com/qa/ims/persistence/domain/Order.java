package com.qa.ims.persistence.domain;

public class Order {
	private Long id;
	private Long customerId;
	private double cost;
	
	public Order(Long id, Long customerId, Double cost) {
		this.customerId = id;
		this.customerId = customerId;
		this.cost = cost;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

}
