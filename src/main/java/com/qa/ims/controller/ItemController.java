package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {
	
public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<Item> itemService;
	
	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
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

	@Override
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for(Item item: items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter a name for the item");
		String itemName = getInput();
		LOGGER.info("Please enter a price for the item");
		Double itemPrice = getDoubleInput();
		Item item = itemService.create(new Item(itemName, itemPrice));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = getLongInput();
		LOGGER.info("Please enter an item name");
		String itemName = getInput();
		LOGGER.info("Please enter a price");
		Double itemPrice = getDoubleInput();
		Item item = itemService.update(new Item(id, itemName, itemPrice));
		LOGGER.info("Item Updated");
		return item;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = getLongInput();
		itemService.delete(id);
		
	}

}
