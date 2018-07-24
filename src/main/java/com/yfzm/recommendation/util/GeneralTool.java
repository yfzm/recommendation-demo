package com.yfzm.recommendation.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;

public class GeneralTool {

    public static void main(String[] args) {
        System.out.println(convertStringToDoubleList("[1,2.0]"));
    }

    public static List<Double> convertStringToDoubleList(String line) {
        JsonParser parser = new JsonParser();
        JsonArray array = new JsonArray();
        array.addAll(parser.parse(line).getAsJsonArray());

        List<Double> attrs = new ArrayList<>();
        for (JsonElement attr : array) {
            attrs.add(attr.getAsDouble());
        }
        return attrs;
    }

    public static List<Integer> convertStringToIntegerList(String line) {
        JsonParser parser = new JsonParser();
        JsonArray array = new JsonArray();
        array.addAll(parser.parse(line).getAsJsonArray());

        List<Integer> list = new ArrayList<>();
        for (JsonElement attr : array) {
            list.add(attr.getAsInt());
        }
        return list;
    }

    public static List<Double> generateUserPreference() {
        Random random = new Random();
        List<Double> userPrefArr = new ArrayList<Double>();
        List<Integer> userViewCount = new ArrayList<Integer>();
        Double sum = 0.0;
        for (int i = 0; i < 50; ++i) {
            Integer randInt = random.nextInt(100) + 1;
            userViewCount.add(randInt);
            sum += pow(randInt, 2);
        }
        for (int i = 0; i < 50; ++i) {
            userPrefArr.add(userViewCount.get(i) / sum);
        }
        return userPrefArr;
    }

    public static String generateImageName() {
        Random random = new Random();
        return String.valueOf(random.nextInt(2000) + 1) + ".jpg";
    }

    public static List<Integer> generateTags() {
        Random random = new Random();
        List<Integer> tags = new ArrayList<Integer>();
        int count = random.nextInt(10) + 1;
        for (int i = 0; i < count; ++i) {
            tags.add(random.nextInt(50));
        }
        return tags;
    }
}
