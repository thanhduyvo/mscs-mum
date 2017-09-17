package mpp.ssa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {

    private static Pattern regexPattern;
    private static Matcher regMatcher;

    public static boolean validateEmailAddress(String emailAddress) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);
        return regMatcher.matches();
    }

    public static boolean validateMobileNumber(String mobileNumber) {
        regexPattern = Pattern.compile("^\\+[0-9]{2,3}+-[0-9]{10}$");
        regMatcher   = regexPattern.matcher(mobileNumber);
        return regMatcher.matches();
    }
}