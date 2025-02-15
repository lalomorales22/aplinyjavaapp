package aplinyjavaapp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * FileLoader
 *
 * Simple helper for reading text from a file (e.g., .mkd).
 */
public class FileLoader {

    public static String loadFileContent(File file) {
        try {
            String text = Files.readString(Path.of(file.getAbsolutePath()), StandardCharsets.UTF_8);
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file: " + file.getName() + "\n\n" + e.getMessage();
        }
    }
}