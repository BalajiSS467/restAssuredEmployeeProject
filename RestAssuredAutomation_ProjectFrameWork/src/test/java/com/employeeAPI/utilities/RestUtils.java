package com.employeeAPI.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empEmail() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("John" +generatedString+ "@gmail.com");
	}
	
	public static String empFirstName() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Bala"+generatedString);
	}
	
	public static String empLastName() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Post"+generatedString);
	}


}
