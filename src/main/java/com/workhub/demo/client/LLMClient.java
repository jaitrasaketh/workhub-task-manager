package com.workhub.demo.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class LLMClient {

    private final RestTemplate restTemplate;
    private final String LLM_SERVICE_URL = "http://localhost:5001/generate";

    public LLMClient() {
        this.restTemplate = new RestTemplate();
    }

    public String generateSuggestion(String title, String description) {
        Map<String, String> request = new HashMap<>();
        request.put("title", title);
        request.put("description", description);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(LLM_SERVICE_URL, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (String) response.getBody().get("suggestion");
        } else {
            throw new RuntimeException("Failed to get LLM suggestion");
        }
    }
}
