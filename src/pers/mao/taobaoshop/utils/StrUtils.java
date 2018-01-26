package pers.mao.taobaoshop.utils;

import java.util.regex.Pattern;

public class StrUtils {

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
