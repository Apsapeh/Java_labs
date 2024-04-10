package General.Utilities;

import java.io.*;

/**
 * This class provides utility methods for reading from and writing to files.
 */
public class File {
    /**
     * Reads the content of a file at the given path and returns it as a string.
     * @param path The path of the file to read.
     * @return The content of the file as a string.
     */
    public static String read(String path) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    /**
     * Writes the given content to a file at the given path.
     * @param path The path of the file to write to.
     * @param content The content to write to the file.
     */
    public static void write(String path, String content) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path))) {
            byte[] bytes = content.getBytes();
            bos.write(bytes);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}