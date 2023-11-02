package Homeworks.HW3;

import java.io.IOException;
import java.util.*;

public class UserData {
    private ArrayList<TreeMap<String, String>> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final static String[] dataWeWant = {"1surname", "2name", "3patronymic", "4birthday (dd-mm-yyyy)",
            "5phone number (without spaces and special symbols)", "6sex (f or m)"};

    {
        boolean flag = true;
        while (flag) {
            System.out.println("If you want:\n" +
                    "- add new user enter '1'\n" +
                    "- write user in file enter '2'\n" +
                    "- get userInfo enter '3'\n" +
                    "- to close enter another number");
            int choice = 9;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    users.add(saveData());
                    break;
                case 2:
                    for (TreeMap<String, String> user : users) {
                        try {
                            SaveToFile saveToFile = new SaveToFile();
                            saveToFile.save(user.get(dataWeWant[0]), getDataForFile(user));
                        } catch (IOException e) {
                            throw new RuntimeException(e.getCause());
                        }
                    }
                    break;
                case 3:
                    print();
                    break;
                case 9:
                    System.out.println("You made incorrect choice, try again.");
                    break;
                default:
                    flag = false;
            }
        }
    }

    public TreeMap<String, String> saveData() {
        String[] data = getData();
        int i = 0;
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (String el : dataWeWant) {
            treeMap.put(el, data[i++]);
        }
        return treeMap;
    }

    private String getDataForFile(TreeMap<String, String> treeMap) {
        StringBuilder result = new StringBuilder();
        treeMap.forEach((k, v) -> result.append(treeMap.get(k) + " "));
        return result.toString();
    }

    private String[] getData() {
        System.out.print("Enter your  ");
        for (String s : dataWeWant) System.out.print(s.substring(1) + ", ");
        System.out.println("\b\b:");
        while (true) {
            String[] date = scanner.nextLine().split(" ");
            if ((new Check()).checkData(date)) return date;
            System.out.println("Enter correct data");
        }
    }
    public void print() {
        System.out.println("-".repeat(55));
        for (TreeMap<String, String> user : users) {
            user.forEach((k, v) -> System.out.println(k.substring(1) + ": " + v));
            System.out.println();
        }
        System.out.println("-".repeat(55));
    }
    public static String[] getDataWeWant() {
        return dataWeWant;
    }
}
