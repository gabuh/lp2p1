package util;


import java.time.LocalDate;



public class Util {
    public static LocalDate stringToLocalDate(String date) {
            return LocalDate.parse(date);
    }



}
