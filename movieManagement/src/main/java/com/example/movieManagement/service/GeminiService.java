package com.example.movieManagement.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final Client client;

    public GeminiService() {
        this.client = new Client(); // Initialize Gemini client
    }

    public String generateMovieDescription(String movieTitle) {
        String prompt = "Write two sections for the movie \"" + movieTitle + "\"." +
                "1. Description: A concise overview of the movie's plot and mood." +
                "2. Review: A short personal-style opinion of the movie." +
                "Format as: Description: <text> Review: <text>";
        try {
            GenerateContentResponse response =
                    client.models.generateContent("gemini-2.0-flash-001", prompt, null);
            return response.text();
        } catch (Exception e) {
            return "Unable to generate description at this time.";
        }
    }
}
