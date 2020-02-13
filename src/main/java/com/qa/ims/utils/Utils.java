package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Utils {
	
	private Utils () {
		
	}

	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	public static Double getDoubleInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return Double.valueOf(scanner.nextLine());
	}
	
	public static Long getLongInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return Long.valueOf(scanner.nextLine());
	}
	
	public static Integer getIntInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return Integer.valueOf(scanner.nextLine());
	}
	
	public void handleException(Exception e, Logger LOGGER) {
		LOGGER.debug(e.getStackTrace());
		LOGGER.error(e.getMessage());
	}

}
