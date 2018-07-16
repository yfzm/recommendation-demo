package com.yfzm.recommendation.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class GeneralTool {

    public static void main(String[] args) {
        System.out.println(convertStringToDoubleList("[1,2.0]"));
    }

    public static List<Double> convertStringToDoubleList(String line) {
        JsonParser parser = new JsonParser();
        JsonArray array = new JsonArray();
        array.addAll(parser.parse(line).getAsJsonArray());

        List<Double> attrs = new ArrayList<>();
        for (JsonElement attr: array) {
            attrs.add(attr.getAsDouble());
        }
        return attrs;
    }
}
