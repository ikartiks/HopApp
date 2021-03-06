package com.hopapp.utility;

import java.util.regex.Pattern;

/**
 * Created by kartikshah on 28/06/15.
 */
public class StringUtil {

    public static boolean isValidEmailAddress(String email) {

        Pattern p = Pattern
                .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (!(p.matcher(email).matches()))
            return false;
        return true;
    }

    public static String removeSplCharsExceptSpace(String x) {
        // .replaceAll("\\\\","")
        return x.replaceAll("[^a-zA-Z0-9 ]", ""); // space after 0-9 to allow
        // single blank space i.e
        // %20
    }

    public static boolean hasNumber(String x) {
        // .replaceAll("\\\\","")
        Pattern p = Pattern.compile("[0-9]");
        return p.matcher(x).find();
    }

    public static boolean hasSpecialCharExceptSpace(String x) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9 ]");
        return p.matcher(x).find();
    }
}
