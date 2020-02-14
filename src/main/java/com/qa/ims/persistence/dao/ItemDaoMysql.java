package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;


public class ItemDaoMysql implements Dao<Item>{
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);
	
	public void handleException(Exception e) {
		Utils.handleException(e, LOGGER);
	}
	
	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public ItemDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://34.89.15.11:3306/ims";
		this.username = username;
		this.password = password;
	}

	public ItemDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	/**
	 * Turns data about an item from the database into an item object with these as its attributes
	 * @param resultSet
	 * @return item object
	 * @throws SQLException
	 */
	Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String itemName = resultSet.getString("item_name");
		Double value = resultSet.getDouble("item_value");
		return new Item(id, itemName, value);
	}
	
	/**
	 * Reads the item that has been added to the database last (as ids are auto-incrementing)
	 * @return
	 */
	public Item readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM items ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}
	
	/**
	 * Creates an item in the database
	 * 
	 * @param - takes in an item object. id will be ignored
	 */
	@Override
	public Item create(Item item) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into items(item_name, item_value) values('" + item.getItemName()
					+ "','" + item.getItemValue() + "')");
			return readLatest();
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}
	
	/**
	 * Reads out all the available data about an item from the item table
	 * @param id - the id of the item
	 * @return
	 */
	public Item readItem(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM items where id = " + id);) {
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}
	
	/**
	 * Updates an item in the database
	 * 
	 * @param item - takes in an item object, the id field will be used to update that item in the database
	 */
	@Override
	public Item update(Item item) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update items set item_name ='" + item.getItemName() + "', item_value ='"
					+ item.getItemValue() + "' where id =" + item.getId());
			return readItem(item.getId());
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}
	
	/**
	 * Deletes an item in the database
	 * 
	 * @param id - id of the item
	 */
	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from items where id = " + id);
		} catch (Exception e) {
			handleException(e);
		}
		
	}
	
	/**
	 * reads all info about all items from the items table
	 */
	@Override
	public ArrayList<Item> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items");) {
			ArrayList<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(itemFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			handleException(e);
		}
		return new ArrayList<>();
	}

}
