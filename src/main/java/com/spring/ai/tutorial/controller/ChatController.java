package com.spring.ai.tutorial.controller;

import com.spring.ai.tutorial.dto.ChatRequest;
import com.spring.ai.tutorial.dto.ApiResponse;
import com.spring.ai.tutorial.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;


    @Operation(
            summary = "Send a chat message to AI",
            description = "Receives a chat request and returns the AI's response."
    )
    @PostMapping("/query")
    public ResponseEntity<ApiResponse<Map<String, Object>>> sendMessage(@RequestBody ChatRequest chatRequest) {

        String systemMessage = "You are a helpful AI assistant.";

        ChatResponse response = chatService.openAiChat(chatRequest.query(), systemMessage);

        return ResponseEntity.ok()
                .body(new ApiResponse<>(
                        true,
                        Map.of("answer", response.getResult().getOutput().getText()),
                        null
                ));


    }

}
