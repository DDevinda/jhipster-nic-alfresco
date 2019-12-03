package com.chainanalytica.nic.http.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpGetClient {

	// get node content by using given nodeId, attachment will be download to tmp folder in the computer
	public void getNodeContent(String urlString) throws IOException {
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		try {
			CloseableHttpResponse response = client.execute(new HttpGet(urlString));
			try {
				// create input stream 
				BufferedInputStream bis = new BufferedInputStream(response.getEntity().getContent());
				String filePath = "src/main/resources/public/samplex.pdf";
				// create output stream 
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
				int inByte;
				while((inByte = bis.read()) != -1) bos.write(inByte);
				bis.close();
				bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    client.close();
		}
		
	}
	
}
