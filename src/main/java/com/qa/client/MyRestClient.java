package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MyRestClient {

	// 1. GET Method
	public void get(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault(); //Creates connection
		HttpGet httpget = new HttpGet(url); //This is a request
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpget); //Hits the api with the request and captures response
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("Status Code----> "+statusCode);
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			JSONParser jsonParser = new JSONParser();
			JSONObject responseJsonObj = (JSONObject)jsonParser.parse(responseString);
			System.out.println("Response JSON from API---> "+responseJsonObj);
			Header[] headerArr = httpResponse.getAllHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			for(Header h : headerArr) {
				allHeaders.put(h.getName(), h.getValue());
			}
			System.out.println(allHeaders);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
