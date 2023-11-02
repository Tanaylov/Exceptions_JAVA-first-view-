package Homeworks.HW3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Check {
    private final String birthdayFormat = "dd-mm-yyyy";
    public boolean checkData(String[] data) {
        if (!checkDataCompleteness(data)) {
            System.out.println("You enter not enough information");
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            switch (i) {
                case 0, 1, 2:
                    if (!checkSNP(data[i])) {
                        System.out.println("You enter wrong name or surname or patronymic");
                        return false;
                    }
                    break;
                case 3:
                    if (!checkDate(data[i])) {
                        System.out.println("You enter wrong birthday");
                        return false;
                    }
                    break;
                case 4:
                    if (!checkPhoneNumber(data[i])) {
                        System.out.println("You enter wrong phone number");
                        return false;
                    }
                    break;
                case 5:
                    if (!checkSex(data[i])) {
                        System.out.println("You enter wrong sex");
                        return false;
                    }
            }
        }
        return true;
    }

    private boolean checkDataCompleteness(String[] data) {
        if (data.length == UserData.getDataWeWant().length) return true;
        return false;
    }

    private boolean checkSNP(String str) {
        if (str.equals("")) return false;
        for (int i = 0; i < str.length(); i++) {
            int letter = str.toLowerCase().charAt(i);
            if (letter < 97 || letter > 122) return false;
        }
        return true;
    }

    private boolean checkBirthday(String str) {
        if (birthdayFormat.length() != str.length()) return false;
        else if (!str.contains(".")) return false;
        else if (str.indexOf('.') != birthdayFormat.indexOf('.')
                && str.lastIndexOf('.') != birthdayFormat.lastIndexOf('.')) return false;
        String[] birthday = str.split("."); // TODO: check correct number for day, month and year.
        for (String s : birthday) {
            if (!checkNum(s)) return false;
        }
        return true;
    }
    private boolean checkDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat(birthdayFormat);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }

    private boolean checkPhoneNumber(String pNumber) {
        if (!checkNum(pNumber)) return false;
        return true;
    }

    private boolean checkSex(String str) {
        if (!str.toLowerCase().equals("f") && !str.toLowerCase().equals("m")) return false;
        return true;
    }

    private boolean checkNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
