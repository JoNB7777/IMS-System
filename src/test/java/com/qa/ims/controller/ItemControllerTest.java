package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	@Mock
	private ItemServices itemServices;
	
	@Spy
	@InjectMocks
	private ItemController itemController;
	
	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> items = new ArrayList<>();
		items.add(new Item("Strawberries", 3.99));
		items.add(new Item("Raspberries", 4.99));
		items.add(new Item("Blackberries", 5.49));
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

	@Test
	public void createTest() {
		String itemName = "Strawberries";
		Double price = 3.99;
		Mockito.doReturn(itemName).when(itemController).getInput();
		Mockito.doReturn(price).when(itemController).getDoubleInput();
		Item item = new Item(itemName, price);
		Item savedItem = new Item(1L, "Strawberries", 3.99);
		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		Long id = 1L;
		String itemName = "Strawberries";
		Double price = 3.29;
		Mockito.doReturn(id).when(itemController).getLongInput();
		Mockito.doReturn(itemName).when(itemController).getInput();
		Mockito.doReturn(price).when(itemController).getDoubleInput();
		Item item = new Item(1L, itemName, price);
		Mockito.when(itemServices.update(item)).thenReturn(item);
		assertEquals(item, itemController.update());
	}
	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the delete method
	 */
	@Test
	public void deleteTest() {
		Long id = 1L;
		Mockito.doReturn(id).when(itemController).getLongInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}

}
