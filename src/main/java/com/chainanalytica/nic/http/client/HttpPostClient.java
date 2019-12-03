package com.chainanalytica.nic.http.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

public class HttpPostClient {

	// upload a file to admin account
	public void uploadToAlfECM(String generatedReportLocation, String reportName) throws ClientProtocolException, IOException, JSONException {
		String alfTicket = getAdminToken();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8082/alfresco/api/-default-/public/alfresco/versions/1/nodes/-root-/children?alf_ticket=TICKET_f81f733cf69b0aaf53efb38066c06546292f35c5");
		File file = new File(generatedReportLocation);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody("filedata", file, ContentType.DEFAULT_BINARY, reportName);
		builder.addTextBody("uploaddirectory", "simple");
		
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		
		try {
            client.execute(post);
            System.out.println(client.execute(post));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	client.close();
        }

	}
	
	// return the alfresco token of the admin
	public String getAdminToken() throws UnsupportedEncodingException, JSONException {
		String alfTocken = null;
		System.out.println("inside");
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://localhost:8082/alfresco/s/api/login");
		
		String json = "{username:admin,password:admin}";
		StringEntity entity = new StringEntity(json);
		
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
		try {
			CloseableHttpResponse response = client.execute(httpPost);
			String responseJSON = EntityUtils.toString(response.getEntity());
			System.out.println(response.getStatusLine().getStatusCode());
			JSONObject jsonObj = new JSONObject(responseJSON);
			alfTocken = jsonObj.getJSONObject("data").getString("ticket");
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return alfTocken;
		
	}
	
	// create a folder named to the given parameter value
	public String createFolder(String folderName) {
		String responseJSON = null;
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://localhost:8082/alfresco/api/-default-/public/alfresco/versions/1/nodes/-root-/children?alf_ticket=TICKET_6915545ad884b6382120a673d95579b11e20c815");
		
		String json = "{\r\n  \"name\":\""+folderName+"\",\r\n  \"nodeType\":\"cm:folder\"\r\n}";
		
		try {
			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			
			try {
				CloseableHttpResponse response = client.execute(httpPost);
				System.out.println(response.getStatusLine().getStatusCode());
				responseJSON = EntityUtils.toString(response.getEntity());
				JSONObject jobj = new JSONObject(responseJSON);
				System.out.println(jobj);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return responseJSON;
		
	}
	
}
