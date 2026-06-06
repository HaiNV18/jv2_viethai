package com.myweb.mongo_anime.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ExternalApiService externalApiService; //tham khảo lại bài giảng ở module 2

    final String GEMINI_API_URI = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    JsonNode buildGeminiRequestFormat(String query) throws Exception{
        // text
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode textNode = mapper.createObjectNode();
        textNode.put("text", query);

        // parts
        ArrayNode partsArray = mapper.createArrayNode();
        partsArray.add(textNode);
        ObjectNode contentsNode = mapper.createObjectNode();
        contentsNode.set("parts", partsArray);

        // contents
        ObjectNode root = mapper.createObjectNode();
        root.set("contents", contentsNode);

        String jsonBody = mapper.writeValueAsString(root); // request
        String data = externalApiService.sendPostRequest(GEMINI_API_URI + apiKey, jsonBody);
        JsonNode jsonData = mapper.readTree(data);
        return jsonData;
    }

    public String sendRequest2Gemini(String query){
        try {
            // Ask
            JsonNode jsonData = buildGeminiRequestFormat(query);
            // Path: candidates[0].content.parts[0].text
            String resultText = jsonData.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
            return resultText;
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }











    public String askGemini(String question) {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                        + apiKey;

        String body = """
        {
          "contents":[
            {
              "parts":[
                {
                  "text":"%s"
                }
              ]
            }
          ]
        }
        """.formatted(question);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        return response.getBody();
    }
}
