package com.taxiapp.desktop.net.controllers;


import static com.taxiapp.desktop.net.ApiEndpoints.AUTH_URL;
import static com.taxiapp.desktop.net.ApiEndpoints.REGISTER_USER_URL;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class AuthenticationRequestController extends RequestController{
    public static String authenticate(String email, String password) {
        try {

            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json.toString()))
                .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String responseBody = response.body();
            HttpHeaders headers = response.headers();

            System.out.println("Response Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);
            System.out.println("Response Headers: " + headers);


            Map<String,String> tokens = extractTokens(responseBody);
            return tokens.get("access_token");


        } catch (Exception e) {
            System.err.println("505. Error: " + e.getMessage());
            return null;
        }

    }
    private static Map<String, String> extractTokens(String responseBody) {
        Map<String, String> tokens = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(responseBody);

            String accessToken = jsonObject.getString("access_token");
            String refreshToken = jsonObject.getString("refresh_token");

            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
        return tokens;
    }

    public static HttpResponse<String> registerUser(String registerRequestAsJson){
        return postRequest(REGISTER_USER_URL,registerRequestAsJson);
    }
}
