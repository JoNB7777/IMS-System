package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderItemController implements CrudController<OrderItem> {
	
public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<OrderItem> orderItemService;
	
	public OrderItemController(CrudServices<OrderItem> orderItemService) {
		this.orderItemService = orderItemService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	
	Double getDoubleInput() {
		return Utils.getDoubleInput();
	}
	
	Long getLongInput() {
		return Utils.getLongInput();
	}
	
	Integer getIntInput() {
		return Utils.getIntInput();
	}

	@Override
	public List<OrderItem> readAll() {
		List<OrderItem> orderItems = orderItemService.readAll();
		for(OrderItem orderItem: orderItems) {
			LOGGER.info(orderItem.toString());
		}
		return orderItems;
	}

	@Override
	public OrderItem create() {
		LOGGER.info("Please enter an order id");
		Long orderId = getLongInput();
		LOGGER.info("Please enter an item id");
		Long itemId = getLongInput();
		LOGGER.info("Please enter a quantity");
		int quantity = getIntInput();
		OrderItem orderItem = orderItemService.create(new OrderItem(orderId, itemId, quantity));
		LOGGER.info("orderItem created");
		return orderItem;
	}

	@Override
	public OrderItem update() {
		LOGGER.info("Please enter the id of the order-item you would like to update");
		Long id = getLongInput();
		LOGGER.info("Please enter an item id");
		Long itemId = getLongInput();
		LOGGER.info("Please enter a quantity");
		int quantity = getIntInput();
		OrderItem orderItem = orderItemService.update(new OrderItem(id, itemId, quantity));
		LOGGER.info("Order Item Updated");
		return orderItem;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order-item you would like to delete");
		Long id = getLongInput();
		orderItemService.delete(id);
	}

}
