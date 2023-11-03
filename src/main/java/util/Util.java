package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Date stringToDate(String date) {
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(date);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Date();
    }

}
