package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.services.OrderItemServices;

@Ignore
public class OrderItemControllerTest {
	
	@Mock
	private OrderItemServices orderItemServices;
	
	@Spy
	@InjectMocks
	private OrderItemController orderItemController;
	
	@Test
	public void readAllTest() {
		OrderItemController orderItemController = new OrderItemController(orderItemServices);
		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(new OrderItem(1L, 1L, 5));
		orderItems.add(new OrderItem(2L, 3L, 1));
		orderItems.add(new OrderItem(2L, 2L, 2));
		Mockito.when(orderItemServices.readAll()).thenReturn(orderItems);
		assertEquals(orderItems, orderItemController.readAll());
	}

	@Test
	public void createTest() {
		Long orderId = 1L;
		Long itemId = 1L;
		int quantity = 5;
		Mockito.doReturn(orderId, itemId).when(orderItemController).getLongInput();
		Mockito.doReturn(quantity).when(orderItemController).getIntInput();
		OrderItem orderItem = new OrderItem(orderId, itemId, quantity);
		OrderItem savedOrderItem = new OrderItem(1L, 1L, 1L, 5);
		Mockito.when(orderItemServices.create(orderItem)).thenReturn(savedOrderItem);
		assertEquals(savedOrderItem, orderItemController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		Long id = 1L;
		Long orderId = 1L;
		Long itemId = 1L;
		int quantity = 5;
		Mockito.doReturn(id, orderId, itemId).when(orderItemController).getLongInput();
		Mockito.doReturn(quantity).when(orderItemController).getIntInput();
		OrderItem orderItem = new OrderItem(1L, orderId, itemId, quantity);
		Mockito.when(orderItemServices.update(orderItem)).thenReturn(orderItem);
		assertEquals(orderItem, orderItemController.update());
	}
	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the delete method
	 */
	@Test
	public void deleteTest() {
		Long id = 1L;
		Mockito.doReturn(id).when(orderItemController).getLongInput();
		orderItemController.delete();
		Mockito.verify(orderItemServices, Mockito.times(1)).delete(1L);
	}

}
