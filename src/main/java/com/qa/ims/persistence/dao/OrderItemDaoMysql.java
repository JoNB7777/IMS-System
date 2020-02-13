package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItem;

public class OrderItemDaoMysql implements Dao<OrderItem> {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderItemDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderItemDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}
	
	OrderItem orderItemFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long orderId = resultSet.getLong("order_id");
		Long itemId = resultSet.getLong("item_id");
		int quantity = resultSet.getInt("quantity");
		return new OrderItem(id, orderId, itemId, quantity);
	}
	
	public OrderItem readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM order_items ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return orderItemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItem create(OrderItem orderItem) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into order_items(order_id, item_id, quantity) values('" + orderItem.getItemId()
					+ "','" + orderItem.getOrderId() + "', ' " + orderItem.getQuantity() + " ')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public OrderItem readOrderItem(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT FROM order_items where id = " + id);) {
			resultSet.next();
			return orderItemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItem update(OrderItem orderItem) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update order_items set quantity ='" + orderItem.getQuantity() +  "' where id =" + orderItem.getId());
			return readOrderItem(orderItem.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from order_items where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		
	}
	
	@Override
	public ArrayList<OrderItem> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from order_items");) {
			ArrayList<OrderItem> orderItems = new ArrayList<>();
			while (resultSet.next()) {
				orderItems.add(orderItemFromResultSet(resultSet));
			}
			return orderItems;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	

}
