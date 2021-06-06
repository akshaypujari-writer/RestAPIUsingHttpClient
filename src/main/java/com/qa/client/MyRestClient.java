package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MyRestClient {

	// 1. GET Method
	public void get(String url) {
		CloseableHttpResponse httpResponse = null;
		CloseableHttpClient httpClient = HttpClients.createDefault(); // Creates connection
		HttpGet httpget = new HttpGet(url); // This is a request
		try {
			httpResponse = httpClient.execute(httpget); // Hits the api with the request and captures response
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("Status Code----> " + statusCode);
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

			JSONParser jsonParser = new JSONParser();
			JSONObject responseJsonObj = (JSONObject) jsonParser.parse(responseString);
			System.out.println("Response JSON from API---> " + responseJsonObj);

			Header[] headerArr = httpResponse.getAllHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			for (Header h : headerArr) {
				allHeaders.put(h.getName(), h.getValue());
			}
			System.out.println(allHeaders);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public CloseableHttpResponse get2(String url) throws Exception {
		
		CloseableHttpResponse httpResponse = null;
		CloseableHttpClient httpClient = HttpClients.createDefault(); // Creates connection
		HttpGet httpget = new HttpGet(url); // This is a request
		httpResponse = httpClient.execute(httpget); // Hits the api with the request and captures response
		return httpResponse;
		
	}

	//2. POST Method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault(); //open connection
		HttpPost httpPost = new HttpPost(url); //create request object
		httpPost.setEntity(new StringEntity(entityString)); //add request payload to request object
		
		Iterator<Entry<String, String>> it = headerMap.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<String, String> m1 = (Map.Entry<String, String>)it.next();
			httpPost.addHeader(m1.getKey(), m1.getValue()); //add headers to request object
			
		}
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse;
		
	}
	
}
