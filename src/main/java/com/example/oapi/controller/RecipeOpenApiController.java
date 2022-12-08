package com.example.oapi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeOpenApiController {
	final String serviceKey = "9bf066dd90674a4e8702";
	
	@GetMapping("/recipe")
	public String recipe() {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api/");
		try {
			urlBuilder.append( URLEncoder.encode("9bf066dd90674a4e8702", "UTF-8")); /*Service Key*/
			urlBuilder.append("/" + URLEncoder.encode("COOKRCP01", "UTF-8")); 
	        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); 
	        
	        urlBuilder.append("/" + URLEncoder.encode("1000", "UTF-8"));
	        urlBuilder.append("/" + URLEncoder.encode("1058", "UTF-8"));
	         
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        Connection conn1 = Jsoup.connect(urlBuilder.toString());
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        
	        if(conn.getResponseCode() == 200) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
	            sb.append(line);
				}
	        rd.close();
	        conn.disconnect();
	        return sb.toString();
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
}
		
