package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order order;
	private Order other;
	
	@Before
	public void setUp() {
		order = new Order(1L, 1L, 59.99);
		other = new Order(1L, 1L, 59.99);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomerId());
		assertNotNull(order.getCost());
		
		order.setId(null);
		assertNull(order.getId());
		order.setCustomerId(null);
		assertNull(order.getCustomerId());
		order.setCost((Double) null);;
		assertNull(order.getCost());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}
	
	@Test
	public void createOrderWithId() {
		assertEquals(1L, order.getId(), 0);
		assertEquals(1L, order.getCustomerId(), 0);
		assertEquals(59.99, order.getCost(), 0);
	}
	
	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}
	
	@Test
	public void orderCustomerIdNullButOtherCustomerIdNotNull() {
		order.setCost((Double) null);;
		assertFalse(order.equals(other));
	}
	
	@Test
	public void customerIdsNotEqual() {
		other.setCustomerId(2L);;
		assertFalse(order.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullCustomerIds() {
		order.setCost((Double) null);;
		other.setCost((Double) null);;
		assertTrue(order.equals(other));
	}
	
	@Test
	public void nullId() {
		order.setId(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		order.setId(null);
		other.setId(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullCost() {
		order.setCost((Double) null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void nullCostOnBoth() {
		order.setCost((Double) null);
		other.setCost((Double) null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void otherCostDifferent() {
		other.setCost(70.0);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Order order = new Order(1L, 4.99);
		assertNull(order.getId());
		assertNotNull(order.getCustomerId());
		assertNotNull(order.getCost());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Order order = new Order(null, null);
		Customer other = new Customer(null, null);
		assertEquals(order.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "id:1 first name:1 surname:59.99";
		assertEquals(toString, other.toString());
	}

}
