package com.movie.review;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieReviewController {

    private final SentimentAnalysisService sentimentAnalysisService;

    public MovieReviewController(SentimentAnalysisService sentimentAnalysisService) {
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    @GetMapping("/")
    public String showForm() {
        return "review-form";
    }

    @PostMapping("/analyze")
    public String analyzeReview(@RequestParam String movieName, 
                              @RequestParam String review, 
                              Model model) {
        String sentiment = sentimentAnalysisService.analyzeSentiment(review);
        
        model.addAttribute("movieName", movieName);
        model.addAttribute("review", review);
        model.addAttribute("sentiment", sentiment);
        
        return "result";
    }
} 