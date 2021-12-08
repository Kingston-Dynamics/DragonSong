package com.kdyncs.dragonsong.api.util.misc;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Utility {
    
    // Get a Safe List
    public static <T> List<T> getSafeList(List<T> other) {
        return other == null ? Collections.emptyList() : other;
    }
    
    // Substitute a Single Valkue
    public static String substitute(String message, String substitution) {
        // Any message with a substituion parameter shoud have this in it.
        String target = "!%";
        
        // Substitute the target value with a message
        if (message.contains(target)) {
            return substitute(message, substitution, target);
        }
        
        // Return original if we can't substitute anything.
        return message;
    }
    
    // Substitute a String Value
    public static String substitute(String message, String substitution, String target) {
        
        if (message.contains(target)) {
            return message.replaceFirst(Pattern.quote(target), substitution);
        }
        
        return message;
    }
    
    public static String multiSubstitute(String message, String... substitutions) {
        String substituted = message;
        
        for (String substitution : substitutions) {
            substituted = substitute(substituted, substitution);
        }
        
        return substituted;
    }
    
    // Multi Subsitute using Map
    public static String multiSubstitute(String message, Map<String, String> substitutions) {
        
        String substituted = message;
        
        for (Entry<String, String> entry : substitutions.entrySet()) {
            substituted = substitute(substituted, entry.getValue(), entry.getKey());
        }
        
        return substituted;
    }
}
