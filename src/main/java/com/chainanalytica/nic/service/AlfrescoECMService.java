package com.chainanalytica.nic.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.chainanalytica.nic.http.client.HttpGetClient;
import com.chainanalytica.nic.http.client.HttpPostClient;

@Service
public class AlfrescoECMService {

	public void getNodeContent() throws IOException {
		String urlString = "http://localhost:8082/alfresco/api/-default-/public/alfresco/versions/1/nodes/9a84df0a-a86e-4cd1-9ff4-4b7a778c1a14/content?attachment=true&alf_ticket=TICKET_2b2902a651480671d1e07c8219ee11f0c8526c20";
		HttpGetClient hgc = new HttpGetClient();
		hgc.getNodeContent(urlString);
	}
	
	public String getAdminToken() throws UnsupportedEncodingException, JSONException {
		System.out.println("hi");
		HttpPostClient httpPostClient = new HttpPostClient();
		return httpPostClient.getAdminToken();
	}
	
	public String createFolder() {
		HttpPostClient httpPostClient = new HttpPostClient();
		return httpPostClient.createFolder("raaatta");
	}
}
