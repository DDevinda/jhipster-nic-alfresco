package com.chainanalytica.nic.web.rest;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainanalytica.nic.service.HttpClientService;
import com.chainanalytica.nic.service.ReportService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
	@Autowired
	ReportService reportService;
	@Autowired
	HttpClientService httpClientService;

	
	@GetMapping("/citizens")
	public String getAllCitizens() {
		String file = reportService.GenarateAllUsersReport();
		return file;
	}
	
	@GetMapping("/vehicles")
	public String getAllVehicles() throws ClientProtocolException, IOException {
		String file = reportService.GenerateAllVehiclesReport();
//		httpClientService.samplePost3(file);
		return file;
	}

	@GetMapping("/check")
	public void makeCall() throws ClientProtocolException, IOException {
		httpClientService.samplePost();
	}
	
	@PostMapping("/checkPost")
	public void makeCall2() throws ClientProtocolException, IOException, JSONException {
		httpClientService.samplePost2();
	}
	
	@PostMapping("/checkFilePost")
	public void makeCall3() throws ClientProtocolException, IOException {
//		httpClientService.samplePost3();
	}

}
