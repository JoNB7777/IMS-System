package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderDaoMysql implements Dao<Order>{
	
	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);
	
	public void handleException(Exception e) {
		Utils.handleException(e, LOGGER);
	}
	
	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://34.89.15.11:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	/**
	 * creates an order in the database
	 * 
	 * @param order - an order object
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orders(customer_id, cost) values('" + order.getCustomerId()
					+ "','" + order.getCost() + "')");
			return readLatest();
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * reads out the order that has been added to the orders table last (as ids are auto-incrementing)
	 * @return
	 */
	private Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * Turns info about an order into an order object with that info as its attributes
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerId = resultSet.getLong("customer_id");
		Double cost = resultSet.getDouble("cost");
		return new Order(id, customerId, cost);
	}

	/**
	 * Reads all data about all orders from the database
	 */
	@Override
	public ArrayList<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			handleException(e);
		}
		return new ArrayList<>();
	}

	/**
	 * Updates an order in the database
	 * 
	 * @param order - an order object
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set cost ='" + order.getCost() + "' where id =" + order.getId());
			return readOrder(order.getId());
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * reads data about an order from the database by its id
	 * @param id - the id of the order
	 * @return
	 */
	private Order readOrder(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where id = " + id);) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * deletes an order from the system by its id
	 * 
	 * param id - the id of the order
	 */
	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete * from orders where id = " + id);
		} catch (Exception e) {
			handleException(e);
		}
		
	}

}
