package com.qa.tests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.MyRestClient;

public class GetAPITest extends TestBase {
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
	public void getAPITest() throws ParseException, IOException, org.json.simple.parser.ParseException {
		myrestclient = new MyRestClient();
		myrestclient.get(url + apiurl);
		
	}
	
	@Test
	public void getAPITest2() throws Exception {
		myrestclient = new MyRestClient();
		httpResponse = myrestclient.get2(url + apiurl);
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONParser jsonParser = new JSONParser();
		JSONObject responseJsonObj = (JSONObject)jsonParser.parse(responseString);
		Assert.assertEquals(RESPONSE_STATUS_CODE_200, httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(responseJsonObj.get("per_page").toString(), "6");
	//	Assert.assertEquals(responseJsonObj.get("data[0].first_name"), "George");
		JSONArray jsonArr = (JSONArray)responseJsonObj.get("data");
		JSONObject o = (JSONObject)jsonArr.get(0);
		Assert.assertEquals(o.get("first_name"), "George");
	}
	
	
}
