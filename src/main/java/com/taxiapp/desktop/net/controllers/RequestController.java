package com.taxiapp.desktop.net.controllers;

import com.taxiapp.desktop.security.AccessTokenManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class RequestController {
    public static HttpResponse<String> postRequest(String url,String body){
        String token = AccessTokenManager.getInstance().getAccessToken();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type","application/json")
                .POST(body==null?HttpRequest.BodyPublishers.noBody():HttpRequest.BodyPublishers.ofString(body))
                .build();

            return sendRequest(request);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static HttpResponse<String> patchRequest(String url,String body){
        String token = AccessTokenManager.getInstance().getAccessToken();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

            return sendRequest(request);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static HttpResponse<String> getRequest(String url, Map<String, String> parameters) {
        String token = AccessTokenManager.getInstance().getAccessToken();

        StringBuilder queryStringBuilder = new StringBuilder(url);
        if (parameters != null && !parameters.isEmpty()) {
            queryStringBuilder.append("?");
            parameters.forEach((key, value) -> {
                value = value.replace(" ", "+");
                queryStringBuilder.append(key).append("=").append(value).append("&");
            });
            queryStringBuilder.deleteCharAt(queryStringBuilder.length() - 1);
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(queryStringBuilder.toString()))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .GET()
                .build();
            return sendRequest(request);

        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static HttpResponse<String> getEntityById(String url,String id){
        Map<String,String> parameters = new HashMap<>();
        parameters.put("id",id);
        return getRequest(url,parameters);
    }

    protected static HttpResponse<String> sendRequest(HttpRequest request){
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Status code: " + statusCode);
            System.out.println("Response body: " + responseBody);

            return response;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
