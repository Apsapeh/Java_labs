package Utilities;

import java.util.Scanner;

public class InputUtil {
    public static String Input(String str) {
        System.out.print(str);
        return new Scanner(System.in).nextLine();
    }
}
