package com.example.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import javax.net.ssl.HttpsURLConnection;

public class GetClient {
	//http://localhost:8080/users
	
	public static void main(String [] args) {
		try {
			URL url = new URL(null, "http://localhost:8080/users", new sun.net.www.protocol.http.Handler());
			HttpURLConnection conn = 
					(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed: HTTP Error code : " + conn.getResponseCode());							
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output;
			
			System.out.println("Output from Server ....\n");
			
			while((output = br.readLine()) != null) {
				System.out.println(output);
			}
			
			conn.disconnect();
			
		}
		catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
