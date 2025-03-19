package com.ahq.globals.utilities;

public class UtilNricSingapore {

    public static String generate(String doc_type, Integer age_input) {
//        String first =  doc_type;

        String first = "S";
        if (doc_type.contains("FIN")) {
            first = "G";
        }
        int age = (int) (Math.random() * ((50 - 1) + 1)) + 10;
        if (!first.equalsIgnoreCase("S") && !first.equalsIgnoreCase("T") && !first.equalsIgnoreCase("F")
                && !first.equalsIgnoreCase("G"))
            first = "S";
        if (!(age >= -1 && age <= 9))
            age = -1;
        String num = "0000000" + Long.valueOf((long) Math.floor(Math.random() * 9999999));
        num = num.substring(num.length() - 7);
        String[] chars = num.split("");
        if (age != -1)
            chars[0] = age + "";
        String output = first + String.join("", chars);
        chars[0] = String.valueOf(Integer.valueOf(chars[0]) * 2);
        chars[1] = String.valueOf(Integer.valueOf(chars[1]) * 7);
        chars[2] = String.valueOf(Integer.valueOf(chars[2]) * 6);
        chars[3] = String.valueOf(Integer.valueOf(chars[3]) * 5);
        chars[4] = String.valueOf(Integer.valueOf(chars[4]) * 4);
        chars[5] = String.valueOf(Integer.valueOf(chars[5]) * 3);
        chars[6] = String.valueOf(Integer.valueOf(chars[6]) * 2);
        Integer sum = 0;
        for (int i = 0; i <= 6; i++) {
            sum += Integer.valueOf(chars[i]);
        }
        Integer offset = (first == "T" || first == "G") ? 4 : 0;
        Integer temp = (offset + sum) % 11;
        String[] st = { "J", "Z", "I", "H", "G", "F", "E", "D", "C", "B", "A" };
        String[] fg = { "X", "W", "U", "T", "R", "Q", "P", "N", "M", "L", "K" };
        String theAlpha;
        if (first.equals("S") || first.equals("T")) {
            theAlpha = st[temp];
        } else if (first.equals("F") || first.equals("G")) {
            theAlpha = fg[temp];
        } else
            theAlpha = "?";
        return output + theAlpha;



//
//        String first = type;
//        //        String first = "T";
////        if (doc_type.contains("FIN")) {
////            first = "G";
////        }
////        int age = (int) (Math.random() * ((50 - 1) + 1)) + 10;
////        int age = argAge;
////        if (!first.equalsIgnoreCase("S") && !first.equalsIgnoreCase("T") && !first.equalsIgnoreCase("F")
////                && !first.equalsIgnoreCase("G"))
////            first = "S";
//        if (!(age >= -1 && age <= 9))
//            age = -1;
//        String num = "0000000" + Long.valueOf((long) Math.floor(Math.random() * 9999999));
//        num = num.substring(num.length() - 7);
//        String[] chars = num.split("");
//        if (age != -1)
//            chars[0] = age + "";
//        String output = first + String.join("", chars);
//        chars[0] = String.valueOf(Integer.valueOf(chars[0]) * 2);
//        chars[1] = String.valueOf(Integer.valueOf(chars[1]) * 7);
//        chars[2] = String.valueOf(Integer.valueOf(chars[2]) * 6);
//        chars[3] = String.valueOf(Integer.valueOf(chars[3]) * 5);
//        chars[4] = String.valueOf(Integer.valueOf(chars[4]) * 4);
//        chars[5] = String.valueOf(Integer.valueOf(chars[5]) * 3);
//        chars[6] = String.valueOf(Integer.valueOf(chars[6]) * 2);
//        Integer sum = 0;
//        for (int i = 0; i <= 6; i++) {
//            sum += Integer.valueOf(chars[i]);
//        }
//        Integer offset = (first == "T" || first == "G") ? 4 : 0;
//        Integer temp = (offset + sum) % 11;
//        String[] st = { "J", "Z", "I", "H", "G", "F", "E", "D", "C", "B", "A" };
//        String[] fg = { "X", "W", "U", "T", "R", "Q", "P", "N", "M", "L", "K" };
//        String theAlpha;
//        if (first == "S" || first == "T") {
//            theAlpha = st[temp];
//        } else if (first == "F" || first == "G") {
//            theAlpha = fg[temp];
//        } else
//            theAlpha = "?";
//        return output + theAlpha;
    }

    public static Boolean validate(String nric_code) {
        // TO BE DEVELOPED
        return true;
    }

}
