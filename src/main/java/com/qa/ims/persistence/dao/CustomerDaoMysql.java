package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

public class CustomerDaoMysql implements Dao<Customer> {

	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);
	
	public void handleException(Exception e) {
		Utils.handleException(e, LOGGER);
	}

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public CustomerDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://34.89.15.11:3306/imsdb";
		this.username = username;
		this.password = password;
	}

	public CustomerDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	/**
	 * Uses the individual databse entries about a given customer to build a customer object from them with these as his attributes
	 * @param resultSet
	 * @return the customer object
	 * @throws SQLException
	 */
	Customer customerFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		return new Customer(id, firstName, surname);
	}


	/**
	 * Reads the customer that has been last added to the database (as ids are auto-incrementing)
	 * @return
	 */
	public Customer readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Customer create(Customer customer) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into customers(first_name, surname) values('" + customer.getFirstName()
					+ "','" + customer.getSurname() + "')");
			return readLatest();
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * Reads data about a customer from the database
	 * 
	 * @param id - the customer's id
	 */
	public Customer readCustomer(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers where id = " + id);) {
			resultSet.next();
			return customerFromResultSet(resultSet);
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Customer update(Customer customer) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update customers set first_name ='" + customer.getFirstName() + "', surname ='"
					+ customer.getSurname() + "' where id =" + customer.getId());
			return readCustomer(customer.getId());
		} catch (Exception e) {
			handleException(e);
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete * from customers where id = " + id);
		} catch (Exception e) {
			handleException(e);
		}
	}


	@Override
	public ArrayList<Customer> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from customers");) {
			ArrayList<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(customerFromResultSet(resultSet));
			}
			return customers;
		} catch (SQLException e) {
			handleException(e);
		}
		return new ArrayList<>();
	}

}
