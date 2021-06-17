package com.employeeAPI.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_DEL_Employee_Record extends TestBase{
	
	@BeforeClass
	void deleteEmployee() throws Exception
	{
		logger.info("********** Started TC005_Delete_Employees_Record *************");
		
		RestAssured.baseURI = "https://reqres.in/api";		
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET,"/users");
		
		//First get the JsonPath object instance from the response interface
		JsonPath jsonpathEvaluator = response.jsonPath();
		
		//Capture id
		String empID = jsonpathEvaluator.get("[0].id");
		response =  httpRequest.request(Method.DELETE,"/users/"+empID); //pass id to delete the record
		
		Thread.sleep(3000);		
	}	
	@Test
	void checkResponseBody()
	{
		logger.info("********** Checking Response Body *************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body===>"+responseBody);		
		Assert.assertEquals(responseBody, "");		
	}
	@Test
	void checkStatusCode()
	{
		logger.info("********** Checking Status code *************");	
		
		int statusCode = response.getStatusCode();//Getting Status Code
		logger.info("Status Code is===>"+statusCode);//204
		Assert.assertEquals(statusCode, 204);		
	}
	@Test
	void checkStausLine() 
	{
		logger.info("********** Checking Status Line *************");
		
		String statusLine = response.getStatusLine();//Getting Status Line
		logger.info("Status Line is===>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
	}
	@Test
	void checkServerType()
	{
		logger.info("********** Checking Server Type *************");
		
		String ServerType = response.header("Server");
		logger.info("Server Type is===>"+ServerType);
		Assert.assertEquals(ServerType, "cloudflare");	
	}
	@AfterClass
	void tearDown()
	{
		logger.info("********** Finished TC005_DEL_Employees_Record *************");	
	}



}
