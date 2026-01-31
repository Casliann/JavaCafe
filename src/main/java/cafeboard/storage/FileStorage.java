package cafeboard.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStorage {

    public static void save(String path, String content) {
        try {
            Files.writeString(Path.of(path), content);

        } catch (IOException e) {
            System.err.println("Save failed for file: " + path + " (" + e.getMessage() + ")");
        }
    }

    public static String load(String path) {
        try {
            return Files.readString(Path.of(path));

        } catch (IOException e) {
            System.err.println("Load failed for file: " + path + " (" + e.getMessage() + ")");
            return null;
        }
    }
}
