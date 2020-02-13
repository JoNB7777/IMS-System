package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class OrderItemTest {
	
	private OrderItem orderItem;
	private OrderItem other;
	
	@Before
	public void setUp() {
		orderItem = new OrderItem(1L, 1L, 1L, 5);
		other = new OrderItem(1L, 1L, 1L, 5);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(orderItem.getId());
		assertNotNull(orderItem.getOrderId());
		assertNotNull(orderItem.getItemId());
		assertNotNull(orderItem.getQuantity());
		
		orderItem.setId(null);
		assertNull(orderItem.getId());
		orderItem.setOrderId(null);
		assertNull(orderItem.getOrderId());
		orderItem.setItemId(null);
		assertNull(orderItem.getItemId());
		orderItem.setQuantity((Integer) null);
		assertNull(orderItem.getQuantity());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(orderItem.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(orderItem.equals(new Object()));
	}
	
	@Test
	public void createOrderItemWithId() {
		assertEquals(1L, orderItem.getId(), 0);
		assertEquals(1L, orderItem.getOrderId(), 0);
		assertEquals(1L, orderItem.getItemId(), 0);
		assertEquals(5, orderItem.getQuantity());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(orderItem.equals(orderItem));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void orderItemOrderIdNameNullButOtherOrderIdNotNull() {
		orderItem.setOrderId(null);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void orderItemOrderIdNotEqual() {
		other.setOrderId(null);;
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullOrderId() {
		orderItem.setOrderId(null);;
		other.setOrderId(null);
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void nullId() {
		orderItem.setId(null);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		orderItem.setId(null);
		other.setId(null);
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void nullQuantity() {
		orderItem.setQuantity((Integer) null);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void nullQuantityOnBoth() {
		orderItem.setQuantity((Integer) null);
		other.setQuantity((Integer) null);
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void otherQuantityDifferent() {
		other.setQuantity(7);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		OrderItem orderItem = new OrderItem(1L, 1L, 5);
		assertNull(orderItem.getId());
		assertNotNull(orderItem.getOrderId());
		assertNotNull(orderItem.getItemId());
		assertNotNull(orderItem.getQuantity());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(orderItem.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		OrderItem orderItem = new OrderItem(null, null, (Integer) null);
		OrderItem other = new OrderItem(null, null, (Integer) null);
		assertEquals(orderItem.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "id:1 order id: 1 item id: 1 quantity: 5";
		assertEquals(toString, orderItem.toString());
	}

}
