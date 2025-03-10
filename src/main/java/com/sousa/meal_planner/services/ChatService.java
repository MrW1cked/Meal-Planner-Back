package com.sousa.meal_planner.services;

import com.sousa.meal_planner.models.dto.chatGPT.ChatRequest;
import com.sousa.meal_planner.models.dto.chatGPT.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ChatService {

    @Qualifier("openaiRestTemplate")
    private final RestTemplate restTemplate;
private final OCRService ocrService;
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api-key}")
    private String apiKey;

    private final String apiUrlCompletions = "https://api.openai.com/v1/chat/completions";
    private final String apiUrlImages = "https://api.openai.com/v1/files";


    public String chat(String prompt) {

        ChatRequest request = new ChatRequest(model, prompt);
        ChatResponse response = restTemplate.postForObject(apiUrlCompletions, request, ChatResponse.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        return response.getChoices().get(0).getMessage().getContent();
    }

    public String analyzeImage(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("talão", ".jpg");
        file.transferTo(tempFile);
        String textFromImage = ocrService.extractTextFromImage(tempFile.getAbsolutePath());

        ChatRequest request = new ChatRequest(model, "This is a Ticket" , textFromImage);
        ChatResponse response = restTemplate.postForObject(apiUrlCompletions, request, ChatResponse.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        return response.getChoices().get(0).getMessage().getContent();
    }

}
