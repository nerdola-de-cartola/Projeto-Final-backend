package br.gov.ufg.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexUtils {
    static public boolean  validaStringPorRegex(String regex, String text) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    
    int matches = 0;
    while (matcher.find()) {
        matches++;
    }

    return matches == 1;
}
}
