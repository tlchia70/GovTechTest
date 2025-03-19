package com.ahq.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

public class StringManipulation {

    public static String cleanBDDActions(String description) {
        Pattern pattern = Pattern.compile("\"\\$\\{(.*?)}\"");
        Matcher matcher = pattern.matcher(description);
        while (matcher.find()){
//            System.out.println("========"+matcher.group(1)+"============>" + getBundle().getPropertyValue(matcher.group(1)));
            if ( getBundle().getPropertyValue(matcher.group(1)).toLowerCase().startsWith("pwd.")) {
                description = description.replaceAll("\\$\\{"+matcher.group(1)+"}", "*****");
//                System.out.println("========PWD============>" );
            } else {
                description = description.replaceAll("\\$\\{"+matcher.group(1)+"}", getBundle().getPropertyValue(matcher.group(1)));
            }

        }
//        System.out.println("========REPLACED============>" + description);
//        if (matcher.find())
//        {
//            System.out.println("???????? 1:"+matcher.group(1));
//            System.out.println("???????? 2:"+matcher.group(2));
//        }
        String removedActions = description.split("-action/s:")[0].trim();
//        String removedQuotes = removedActions.replaceAll("\"", "");
//        String removedKeywords = removedActions.replaceAll("Step:", "").replaceAll("And:", "").replaceAll("Result:", "");
//        String removedVariables = removedKeywords.replaceAll("\\$\\{.*?\\}", "").trim();
//        String removedEndColon = removedVariables.replaceAll(":$", "");
//        return removedEndColon.trim();
        return description;
    }
}
