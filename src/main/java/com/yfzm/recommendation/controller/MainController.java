package com.yfzm.recommendation.controller;

import com.yfzm.recommendation.entity.ClothColor;
import com.yfzm.recommendation.entity.CollocationEntity;
import com.yfzm.recommendation.service.RecommendService;
import com.yfzm.recommendation.service.ScoreService;
import com.yfzm.recommendation.util.Constant;
import dto.score.ClothAttributes;
import dto.score.GetScoreForm;
import org.apache.mahout.clustering.UncommonDistributions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private final RecommendService recommendService;
    private final ScoreService scoreService;

    @Autowired
    public MainController(RecommendService recommendService, ScoreService scoreService) {
        this.recommendService = recommendService;
        this.scoreService = scoreService;
    }

    @GetMapping("/recommend")
    public List<CollocationEntity> getRecommendation() {
//        List<Double> attrs = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            attrs.add(UncommonDistributions.rNorm(0, 1));
//        }
//        double[] a = new double[] {UncommonDistributions.rNorm(1, 1)};
        return recommendService.getRecommendation(getRandomAttrs());
    }

    @GetMapping("/score")
    public double getScore() {

        GetScoreForm form = new GetScoreForm();
        ClothAttributes upper = new ClothAttributes();
        ClothAttributes lower = new ClothAttributes();

        upper.setColor(new ClothColor(0, 0, 0));
        upper.setType(Constant.UPPER_CLOTH);
        upper.setAttrs(getRandomAttrs());
        upper.setTags(getRandomTags(3));

        lower.setColor(new ClothColor(0, 0, 0));
        lower.setType(Constant.LOWER_CLOTH);
        lower.setAttrs(getRandomAttrs());
        lower.setTags(getRandomTags(4));

        form.setUpper(upper);
        form.setLower(lower);

        return scoreService.getScore(form);
    }

    public static List<Double> getRandomAttrs() {
        List<Double> attrs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            attrs.add(UncommonDistributions.rNorm(0, 1));
        }
        return attrs;
    }

    public static List<String> getRandomTags(int n) {
        List<String> tags = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tags.add(String.valueOf(Math.random()));
        }
        return tags;
    }

}
