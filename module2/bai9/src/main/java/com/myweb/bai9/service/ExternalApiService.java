package com.myweb.bai9.service;

import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ExternalApiService {

    private final HttpClient httpClient;
    public ExternalApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    //send GET requests to another url
    public JsonNode fetchDataFromExternalApi(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.body());
            return jsonNode;
        } catch (Exception e) {
            e.printStackTrace(); // Handle potential JSON parsing errors
            return null;
        }
    }
}
