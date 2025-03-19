package com.ahq.addons;

public class stringManipulation {
    
    public static String cleanBDDActions(String description) {
        String removedActions = description.split("-action/s:")[0].trim();
        String removedQuotes = removedActions.replaceAll("\"", "");
        String removedKeywords = removedQuotes.replaceAll("Step:", "").replaceAll("And:", "").replaceAll("Result:", "");
        String removedVariables = removedKeywords.replaceAll("\\$\\{.*?\\}", "").trim();
        String removedEndColon = removedVariables.replaceAll(":$", "");
        return removedEndColon.trim();
    }
}
