package com.sousa.meal_planner.controllers;

import com.sousa.meal_planner.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String prompt) {

        String response = chatService.chat(prompt);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/chat/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String response = chatService.analyzeImage(file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem");
        }
    }
}