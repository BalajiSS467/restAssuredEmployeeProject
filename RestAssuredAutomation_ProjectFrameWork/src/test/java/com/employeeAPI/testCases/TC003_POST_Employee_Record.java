package com.employeeAPI.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;
import com.employeeAPI.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_POST_Employee_Record extends TestBase{
	
	String empEmail = RestUtils.empEmail();
	String empFirstName = RestUtils.empFirstName();
	String empLastName = RestUtils.empLastName();
	
	@BeforeClass
	void createEmployee() throws Exception
	{
		logger.info("********** Started TC003_POST_Employees_Record *************");
		
		RestAssured.baseURI = "https://reqres.in/api";		
		httpRequest = RestAssured.given();
		
		JSONObject requestparams = new JSONObject();
		
		requestparams.put("email",empEmail);
		requestparams.put("first_name",empFirstName);	
		requestparams.put("last_name", empLastName);		
		
		//Add a header stating the request body is json
		httpRequest.header("Content-Type","application/json");
		
		//Add the json to the body of the request
		httpRequest.body(requestparams.toJSONString());
		
		//Response Object
		response = httpRequest.request(Method.POST,"/users");		
		
		Thread.sleep(4000);		
	}
	@Test
	void checkResponseBody()
	{
		logger.info("********** Checking Response Body *************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body===>"+responseBody);
		
		Assert.assertEquals(responseBody.contains(empEmail), true);
		Assert.assertEquals(responseBody.contains(empFirstName), true);
		Assert.assertEquals(responseBody.contains(empLastName), true);
	}
	@Test
	void checkStatusCode()
	{
		logger.info("********** Checking Status code *************");	
		
		int statusCode = response.getStatusCode();//Getting Status Code
		logger.info("Status Code is===>"+statusCode);//201
		Assert.assertEquals(statusCode, 201);		
	}
	@Test
	void checkStausLine() 
	{
		logger.info("********** Checking Status Line *************");
		
		String statusLine = response.getStatusLine();//Getting Status Line
		logger.info("Status Line is===>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
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
	@AfterClass
	void tearDown()
	{
		logger.info("********** Finished TC003_POST_Employees_Record *************");	
	}
}
