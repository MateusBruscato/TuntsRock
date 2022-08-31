package com.tuntsProject.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetJsonFromUrl {

	public static List<Country> getCountriesFromApi(String host){
		//Setting US locale for number format
		Locale.setDefault(Locale.US);

		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(host).asJson();
		} catch (UnirestException e) {
			e.getMessage();
			System.out.println("Not able to access the API. Please try again later.");
		}

		if(response.getStatus() != 200) {
			System.out.println("Failed to get connection with API.");
		}

		JsonParser jp = new JsonParser();
		JsonArray ja = (JsonArray) jp.parse(response.getBody().toString());

		List<Country> countries = new ArrayList<>();

		for (int i = 0; i < ja.size(); i++) {
			JsonObject jo = (JsonObject) ja.get(i);
			JsonElement je = jo.get("name");
			String countryName = je.toString();

			je = jo.get("capital");
			String countryCapital = "-";
			if (je != null) {
				countryCapital = je.toString();
			}

			je = jo.get("area");
			String countryArea = "-";
			if (je != null) {
				countryArea = String.format("%.2f", Double.parseDouble(je.toString()));
			}

			je = jo.get("currencies");
			String currencies = "-";
			if (je != null) {
				JsonArray jArrayCurrencies = je.getAsJsonArray();
				if (jArrayCurrencies != null) {
					for (int j = 0; j < jArrayCurrencies.size(); j++) {
						JsonElement jCurrencies = jArrayCurrencies.get(j).getAsJsonObject().get("code");
						currencies += jCurrencies.toString();
					}
				}
			}
			Country country = new Country(countryName, countryCapital, countryArea, currencies);
			countries.add(country);
		}

		return countries;
	}
}
