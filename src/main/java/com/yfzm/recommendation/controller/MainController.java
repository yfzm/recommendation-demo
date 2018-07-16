//package com.yfzm.recommendation.controller;
//
//import com.yfzm.recommendation.entity.CollocationEntity;
//import com.yfzm.recommendation.service.RecommendService;
//import org.apache.mahout.clustering.UncommonDistributions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class MainController {
//    private final RecommendService recommendService;
//
//    @Autowired
//    public MainController(RecommendService recommendService) {
//        this.recommendService = recommendService;
//    }
//
//    @GetMapping("/")
//    public List<CollocationEntity> getRecommendation() {
//        List<Double> attrs = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            attrs.add(UncommonDistributions.rNorm(0, 1));
//        }
////        double[] a = new double[] {UncommonDistributions.rNorm(1, 1)};
//        return recommendService.getRecommendation(attrs);
//    }
//}
