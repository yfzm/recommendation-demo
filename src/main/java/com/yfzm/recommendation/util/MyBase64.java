package com.kiwi.backend.util.tool;

import java.util.Base64;

public class MyBase64 {

    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);

    }

    public static byte[] decode(String imgString) {
        imgString = imgString.replace("\n", "");
        return Base64.getDecoder().decode(imgString);
    }
}
