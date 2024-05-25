// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Main {
    // delEdges method to remove the first and last characters of a string
    public static String delEdges(String str) {
        return str.substring(1, str.length() - 1);
    }

    public static void main(String[] args) {
        // Create a JFrame object
        Stream.of("horse", "goat", "monkey", "rooster", "dog", "pig")
                .filter(s -> s.length() > 3)
                .map(s -> delEdges(s))
                .limit(2)
                .sorted()
                .forEachOrdered(System.out::print);
    }
}