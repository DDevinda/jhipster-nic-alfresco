package com.chainanalytica.nic.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.chainanalytica.nic.http.client.HttpGetClient;

@Service
public class HttpClientService {

	public void samplePost() throws ClientProtocolException, IOException {
		
		String url = "https://jsonplaceholder.typicode.com/posts";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		
		request.addHeader("header-name" , "header-value");
		HttpResponse response = client.execute(request);
		
		System.out.println("entity");
		System.out.println(response.getEntity());
		
		System.out.println("getcontent");
		System.out.println(response.getEntity().getContent());
		
		System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		System.out.println(result.toString());
	}
	
	public void samplePost2() throws ClientProtocolException, IOException, JSONException {
		
		JSONObject json = new JSONObject();
		json.put("userId", "12345");
		json.put("id", "12345");
		json.put("title", "someValue");
		json.put("body", "someValue");

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		try {
		    HttpPost request = new HttpPost("https://jsonplaceholder.typicode.com/posts");
		    StringEntity params = new StringEntity(json.toString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    httpClient.execute(request);
		// handle response here...
		} catch (Exception ex) {
		    // handle exception here
		} finally {
		    httpClient.close();
		}
		
	}
	
	public void samplePost3(String generatedReportName) throws ClientProtocolException, IOException {
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8082/alfresco/api/-default-/public/alfresco/versions/1/nodes/-root-/children?alf_ticket=TICKET_da0dcc3c1f32a57474551bb6970e6444b12ae226");
		File file = new File("src/main/resources/public/"+generatedReportName);
//		FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addBinaryBody("filedata", file, ContentType.DEFAULT_BINARY, "somename.pdf");
//		builder.addPart("filedata", fileBody);
//		builder.addBinaryBody("filedata", file, ContentType.DEFAULT_BINARY, "somefile.pdf");
		
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		
		try {
            client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
}
