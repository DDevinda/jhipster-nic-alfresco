package com.chainanalytica.nic.web.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainanalytica.nic.service.AlfrescoECMService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
	
	@Autowired
	AlfrescoECMService alfrescoECMService;

	@GetMapping("/report")
	public void getNodeContent() throws IOException {
		alfrescoECMService.getNodeContent();
	}
	
	@PostMapping("/auth")
	public String getAdminToken() throws UnsupportedEncodingException, JSONException {
		System.out.println("weda");
		return alfrescoECMService.getAdminToken();
	}
	
	@PostMapping("/folder")
	public String createFolder() {
		return alfrescoECMService.createFolder();
	}
	
}
