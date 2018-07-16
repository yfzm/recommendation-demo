package com.yfzm.recommendation.controller;

import com.yfzm.recommendation.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private final RecommendService recommendService;

    @Autowired
    public MainController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("/")
    public List<String> getRecommendation() {
        List<Double> attrs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            attrs.add(0.0);
        }
        return recommendService.getRecommendation(attrs);
    }
}
