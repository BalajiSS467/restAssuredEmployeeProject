package com.employeeAPI.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_ALL_Employees extends TestBase {
	
	@BeforeClass
	void getAllEmployees() throws Exception
	{
		logger.info("********** Started TC001_GET_ALL_Employees *************");
		
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/users");
		
		Thread.sleep(3);
	}
	@Test
	void checkResponseBody()
	{
		logger.info("********** Checking Response Body *************");	
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body===>"+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	@Test
	void checkStatusCode()
	{
		logger.info("********** Checking Status code *************");	
		
		int statusCode = response.getStatusCode();//Getting Status Code
		logger.info("Status Code is===>"+statusCode);//200
		Assert.assertEquals(statusCode, 200);		
	}
	@Test
	void checkResponseTime()
	{
		logger.info("********** Checking Response Time *************");
		
		long responseTime = response.getTime(); 
		logger.info("Response Time is===>"+responseTime);
		
		if (responseTime>2000)
			logger.warn("Response Time is Greater than 2000");
		
		Assert.assertTrue(responseTime<2000);		
	}
	@Test
	void checkStausLine() 
	{
		logger.info("********** Checking Status Line *************");
		
		String statusLine = response.getStatusLine();//Getting Status Line
		logger.info("Status Line is===>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	void checkContentType()
	{
		logger.info("********** Checking Content Type *************");
		
		String ContentType = response.header("Content-Type");
		logger.info("Content Type is===>"+ContentType);
		Assert.assertEquals(ContentType, "application/json; charset=utf-8");		
	}
	@Test
	void checkServerType()
	{
		logger.info("********** Checking Server Type *************");
		
		String ServerType = response.header("Server");
		logger.info("Server Type is===>"+ServerType);
		Assert.assertEquals(ServerType, "cloudflare");		
	}
	@Test
	void checkContentEncoding()
	{
		logger.info("********** Checking Content Encoding *************");
		
		String ContentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is===>"+ContentEncoding);
		Assert.assertEquals(ContentEncoding, "gzip");		
	}
	@Test
	void checkContentLength()
	{
		logger.info("********** Checking Age *************");
		
		String Age = response.header("Age");
		logger.info("Age is===>"+Age);
		
		if(Integer.parseInt(Age)<100)
			logger.warn("Age is less than 100");
		Assert.assertTrue(Integer.parseInt(Age)>100);	
	}
	@Test
	void checkCookies()
	{
		logger.info("********** Checking Cookies *************");
		String Cookies = response.getCookie("asdfg");
	}
	@AfterClass
	void tearDown()
	{
		logger.info("********** Finished TC001_GET_ALL_Employees *************");	
	}



}
