package com.qa.tests;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.MyRestClient;

public class PostAPITest extends TestBase {

	TestBase testBase;
	String url;
	String apiurl;
	MyRestClient myrestclient;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		url = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
	}
	
	@Test
	public void postAPITest() throws Exception {
		myrestclient = new MyRestClient();
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		JSONObject jsonreq = new JSONObject();
		jsonreq.put("name", "Naveen");
		jsonreq.put("job", "Creator");
		httpResponse = myrestclient.post(url+apiurl, jsonreq.toJSONString(), header);
		System.out.println("StatusCode---->"+httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_201);
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		System.out.println(responseString);
	}
	
}
