package com.example.team.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class RestTestController {

	@GetMapping("/apitest")
	public String callApiWithXml() {
		StringBuffer result = new StringBuffer();
		try {
			String apiUrl = "https://seoul.openapi.redtable.global/api/rstr?"
					+ "serviceKey=DLqEcgtBqy4lMOUw1BMi8NL1H5XiTbNKGuQtPM3epTpiPJZa6jcwQo1DDRmsGstF";
			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

			String returnLine;
			result.append("<xmp>");
			while ((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine + "\n");
			}
			urlConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result + "</xmp>";
	}
}