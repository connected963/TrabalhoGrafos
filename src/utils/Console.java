package utils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by connected on 10/21/16.
 */
public class Console {

    public static String readString(String textDescription) {
        return readString(textDescription, false);
    }

    public static String readString(String textDescription, Boolean acceptEmpty) {
        String userText = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(textDescription);
            userText = scanner.nextLine();
        } while (userText.isEmpty() && !acceptEmpty);

        return userText;
    }

    public static Integer readInteger(String textDescription, String errorMessage) {
        Integer userInteger = null;
        Scanner scanner = new Scanner(System.in);
        do {

            try {
                System.out.println(textDescription);
                userInteger = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                scanner.nextLine();
            }

        } while (Objects.isNull(userInteger));

        return userInteger;
    }


    public static Double readDouble(String textDescription, String errorMessage) {
        Double userDouble = null;
        Scanner scanner = new Scanner(System.in);
        do {

            try {
                System.out.println(textDescription);
                userDouble = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                scanner.nextLine();
            }

        } while (Objects.isNull(userDouble));

        return userDouble;
    }

    public static void clear() throws IOException {
        if(SystemUtils.isLinux()) {
            Runtime.getRuntime().exec("clear");
        }
    }
}
