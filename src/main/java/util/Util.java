package util;


import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static LocalDate stringToLocalDate(String date) {
            return LocalDate.parse(date);
    }

    //Must at least have 5 - 16 characters, must not contain symbols
    public static boolean validateUsername(String username){
        String USERNAME_PATTERN = "^[a-zA-Z]{5,16}$";
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean validateEmail(String email){
        String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$"; // REGEX
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



}
