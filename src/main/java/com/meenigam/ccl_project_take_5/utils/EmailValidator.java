package com.meenigam.ccl_project_take_5.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator {

    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static boolean isValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static void main(String[] args) {
        String username = "johndoe123";
        boolean isValid = isValid(username);
        System.out.println(isValid); // true
    }
}
