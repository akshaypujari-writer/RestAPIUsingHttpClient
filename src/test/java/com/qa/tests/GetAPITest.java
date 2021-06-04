package com.qa.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.MyRestClient;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String url;
	String apiurl;
	MyRestClient myrestclient;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		url = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
	}

	@Test
	public void getAPITest() {
		myrestclient = new MyRestClient();
		myrestclient.get(url + apiurl);
	}
}
