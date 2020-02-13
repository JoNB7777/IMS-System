package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in order items for the CRUD functionality
 * @author Admin
 *
 */
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

	/**
	 * Reads all OrderItems to the logger
	 */
	@Override
	public List<OrderItem> readAll() {
		List<OrderItem> orderItems = orderItemService.readAll();
		for(OrderItem orderItem: orderItems) {
			LOGGER.info(orderItem.toString());
		}
		return orderItems;
	}

	/**
	 * Creates an OrderItem by taking in user input
	 */
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

	/**
	 * Updates an existing OrderItem by taking in user input
	 */
	@Override
	public OrderItem update() {
		LOGGER.info("Please enter the order id of the order-item you would like to update");
		Long orderId = getLongInput();
		LOGGER.info("Please enter an item id");
		Long itemId = getLongInput();
		LOGGER.info("Please enter a quantity");
		int quantity = getIntInput();
		OrderItem orderItem = orderItemService.update(new OrderItem(orderId, itemId, quantity));
		LOGGER.info("Order Item Updated");
		return orderItem;
	}

	/**
	 * Deletes an existing OrderItem by its id
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order-item you would like to delete");
		Long id = getLongInput();
		orderItemService.delete(id);
	}

}
