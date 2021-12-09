/*
 * Copyright (C) 2021 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kdyncs.dragonsong.api.util.misc;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Utility {

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
