package com.ahq.globals.utilities;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class UtilBase64 {

    public static List<String> SECRET_STRINGS = new ArrayList<>();

    public static String decrypt(String base64encodedString) {
        try {
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            return new String(base64decodedBytes, "utf-8");
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            return "Base64 Decryption Failed";
        }
    }

    public static String encrypt(String str) {
        return "pwd." + new String(Base64.getEncoder().encode(Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8))));
    }

    public static String check(String str) {
        if (str.startsWith("pwd.")) {
            try {
                byte[] base64decodedBytes = Base64.getDecoder().decode(Base64.getDecoder().decode(str.substring(4)));
                String decodedString = new String(base64decodedBytes, "utf-8");
                SECRET_STRINGS.add(decodedString);
                return decodedString;
            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
                return "Password Decryption Failed";
            }
        } else {
            return str;
        }
    }
}