package util;

public class InputConstraints {

    //Must at least have 5 - 16 characters, must not contain symbols
    public static boolean validateUsername(String username){
        if (username == null || username.isEmpty()) {
            return false;
        }

        // Check the length of the username
        int usernameLength = username.length();
        if (usernameLength < 5 || usernameLength > 16) {
            return false;
        }

        // Check if the username contains only alphanumeric characters and underscores
        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }

        // Check if the username starts with an alphabetic character
        char firstChar = username.charAt(0);
        if (!Character.isLetter(firstChar) ) {
            return false;
        }

        // Check if the username starts or ends with an underscore
        char lastChar = username.charAt(usernameLength - 1);
        if ( lastChar == '_') {
            return false;
        }


        return true;
    }

    public static boolean validateEmail(String email){
        if (email == null || email.isEmpty()) {
            return false;
        }


        // Check if the email starts with an alphabetic character
        char firstChar = email.charAt(0);
        if (!Character.isLetter(firstChar) ) {
            return false;
        }

        // Split the email into local part and domain
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false; // Missing '@' symbol
        }


        // Check for the presence of a dot '.' after '@'
        if (email.contains("@.") || email.contains(".@") ) {
            return false;
        }

        String localPart = parts[0];
        String domain = parts[1];

        // Check for missing local part or top-level domain
        if (localPart.isEmpty() || domain.isEmpty()) {
            return false;
        }

        // Check for invalid symbols
        String specialCharacters = "!#$%^&*()=+[]{}|;:'\",<>?/";
        for (char c : email.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(c))) {
                return false;
            }
        }

        if (!domain.contains("."))
            return false;

        specialCharacters = "-_";
        for (char c : domain.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(c)) || Character.isDigit(c)) {
                return false;
            }
        }


        // Check for consecutive dots in the domain
        if (domain.contains("..")) {
            return false;
        }

        // Check for space in the domain or local part
        if (domain.contains(" ") || localPart.contains(" ")) {
            return false;
        }

        // Check for multiple '@' symbols
        if (email.indexOf('@', email.indexOf('@') + 1) != -1) {
            return false;
        }





        return true;
    }

    public static boolean validatePassword(String password){
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Check for minimum length (adjust as needed)
        if (password.length() < 8 ) {
            return false;
        }

        // Check for at least one uppercase letter
        if (!containsUppercase(password)) {
            return false;
        }

        // Check for at least one lowercase letter
        if (!containsLowercase(password)) {
            return false;
        }

        // Check for at least one digit
        if (!containsDigit(password)) {
            return false;
        }

        // Check for at least one special character (adjust as needed)
        if (!containsSpecialCharacter(password)) {
            return false;
        }

        return true;
    }

    private static boolean containsUppercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsLowercase(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDigit(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsSpecialCharacter(String password) {
        // Define your set of special characters (adjust as needed)
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";
        for (char c : password.toCharArray()) {
            if (specialCharacters.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }


}
