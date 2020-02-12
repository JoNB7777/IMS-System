package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item(1L, "Strawberries", 4.99);
		other = new Item(1L, "Strawberries", 4.99);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getItemValue());
		
		item.setId(null);
		assertNull(item.getId());
		item.setItemName(null);;
		assertNull(item.getItemName());
		item.setItemValue(null);;
		assertNull(item.getItemValue());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}
	
	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getId(), 0);
		assertEquals("Strawberries", item.getItemName());
		assertEquals(4.99, item.getItemValue(), 0);
		
	}
	
	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}
	
	@Test
	public void itemNameNullButOtherNameNotNull() {
		item.setItemName(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void itemNamesNotEqual() {
		other.setItemName("Blackberries");
		assertFalse(item.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setItemName(null);
		other.setItemName(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void nullId() {
		item.setId(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		item.setId(null);
		item.setId(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullItemValue() {
		item.setItemValue(null);;
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullItemValueOnBoth() {
		item.setItemValue(null);
		other.setItemValue(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void otherItemValueDifferent() {
		other.setItemValue(3.29);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Item item = new Item("Strawberries", 4.99);
		assertNull(item.getId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getItemValue());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), item.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Item item = new Item(null, null);
		Item other = new Item(null, null);
		assertEquals(item.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "id:1 item name:Strawberries item value:4.99";
		assertEquals(toString, item.toString());
	}
	
	

}
