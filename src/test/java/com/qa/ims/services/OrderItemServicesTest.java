package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderItem;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemServicesTest {
	
	@Mock
	private Dao<OrderItem> orderItemDao;
	
	@InjectMocks
	private OrderItemServices orderItemServices;
	
	@Test
	public void orderItemServicesCreate() {
		OrderItem orderItem = new OrderItem(1L, 1L, 5);
		orderItemServices.create(orderItem);
		Mockito.verify(orderItemDao, Mockito.times(1)).create(orderItem);
	}
	
	@Test
	public void orderItemServicesRead() {
		orderItemServices.readAll();
		Mockito.verify(orderItemDao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void orderItemServicesUpdate() {
		OrderItem orderItem = new OrderItem(1L, 1L, 5);
		orderItemServices.update(orderItem);
		Mockito.verify(orderItemDao, Mockito.times(1)).update(orderItem);
	}
	
	@Test
	public void customerServicesDelete() {
		orderItemServices.delete(1L);;
		Mockito.verify(orderItemDao, Mockito.times(1)).delete(1L);
	}

}
