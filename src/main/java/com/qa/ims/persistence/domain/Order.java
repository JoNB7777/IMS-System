package com.qa.ims.persistence.domain;

public class Order {
	private Long id;
	private Long customerId;
	private double cost;
	
	public Order(Long id, Long customerId, Double cost) {
		this.id = id;
		this.customerId = customerId;
		this.cost = cost;
	}
	public Order(Long customerId, Double cost) {
		this.customerId = id;
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
	
	public String toString() {
		return "id:" + id + " customer id:" + customerId + " cost:" + cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (((Double) cost == null) ? 0 : ((Double) cost).hashCode());
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
		Order other = (Order) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if ((Double) cost == null) {
			if (other.cost != (Double) null)
				return false;
		} else if (cost != other.cost)
			return false;
		return true;
	}

}
