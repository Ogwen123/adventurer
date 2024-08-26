package utils;

import java.util.Arrays;

public class Utils {
    public static void logger(String string) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        StringBuilder buffer = new StringBuilder();

        int i = 0;
        while (i < string.length()) {
            char c = string.charAt(i);

            if (c == '*') {
                char nextC = string.charAt(i + 1);

                String code = switch (nextC) {
                    case 'x' -> ANSI_RESET;
                    case 'b' -> ANSI_BLUE;
                    case 'r' -> ANSI_RED;
                    case 'g' -> ANSI_GREEN;
                    case 'y' -> ANSI_YELLOW;
                    case 'p' -> ANSI_PURPLE;
                    case 'c' -> ANSI_CYAN;
                    case 'w' -> ANSI_WHITE;
                    default -> "";
                };

                if (code != ""){
                    buffer.append(code);
                    i += 2;
                } else {
                    buffer.append(c);
                    i++;
                }
            } else {
                buffer.append(c);
                i++;
            }
        }
        System.out.println(buffer);
    }
}
