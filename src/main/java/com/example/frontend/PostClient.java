package com.example.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostClient {
	public static void main(String [] args) {
		//http://localhost:8080/users

		try {
			URL url = new URL(null, "http://localhost:8080/users", new sun.net.www.protocol.http.Handler());
			HttpURLConnection conn = 
					(HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/json");
			
			/*
			 * {
	"id" : 3,
	"userName":"raghu.palakodety",
	"vorName": "Raghu",
	"nachName": "Palakodety",
	"emailId":"raghu.palakodety@gmail.com"
}
			 */
			String input = "{\"id\":3,\"userName\":\"raghu.palakodety\",\"vorName\":\"Raghu\",\"nachName\":\"Palakodety\",\"emailId\":\"raghu.palakodety87@gmx.de\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "+conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
		}
}
