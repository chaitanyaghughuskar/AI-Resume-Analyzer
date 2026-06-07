package com.chaitanya.airesumeanalyzer.service;

import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    public String analyzeResume(String resumeText) throws Exception {

        WebClient webClient = WebClient.builder().build();

        String prompt = """
                Analyze this resume and provide:
                1. ATS Score out of 100
                2. Strengths
                3. Missing Skills
                4. Suggestions

                Resume:
                """ + resumeText;

        Map<String, Object> requestBody = Map.of(
                "model", "llama-3.1-8b-instant",
                "messages", new Object[] {
                        Map.of(
                                "role", "user",
                                "content", prompt)
                });

        String response = webClient.post()
                .uri("https://api.groq.com/openai/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response);

        return root.path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();
    }
    public String matchJobDescription(String resumeText,
            String jobDescription) throws Exception {

WebClient webClient = WebClient.builder().build();

String prompt = """
Compare the resume with the job description and provide:

1. Match Percentage out of 100
2. Matching Skills
3. Missing Skills
4. Suggestions to improve matching

Resume:
""" + resumeText + """

Job Description:
""" + jobDescription;

Map<String, Object> requestBody = Map.of(
"model", "llama-3.1-8b-instant",
"messages", new Object[] {
Map.of(
      "role", "user",
      "content", prompt)
});

String response = webClient.post()
.uri("https://api.groq.com/openai/v1/chat/completions")
.header("Authorization", "Bearer " + apiKey)
.contentType(MediaType.APPLICATION_JSON)
.bodyValue(requestBody)
.retrieve()
.bodyToMono(String.class)
.block();

ObjectMapper mapper = new ObjectMapper();

JsonNode root = mapper.readTree(response);

return root.path("choices")
.get(0)
.path("message")
.path("content")
.asText();
}
}