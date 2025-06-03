package com.movie.review;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@Service
public class SentimentAnalysisService {

    public String analyzeSentiment(String text) {
        try {
            // Escape special characters in the text
            String escapedText = text.replace("\"", "\\\"");
            
            // Build the command to run the Python script
            String[] command = {
                "python",
                "src/main/resources/sentiment_analyzer.py",
                escapedText
            };

            // Execute the Python script
            Process process = Runtime.getRuntime().exec(command);
            
            // Read the output
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            
            // Parse the JSON response
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> result = mapper.readValue(output.toString(), Map.class);
            
            return result.get("sentiment");
            
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
} 