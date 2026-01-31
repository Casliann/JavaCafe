package cafeboard.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {

    public static void log(String action) {
        File file = new File("demo/src/main/resources/data/log.txt");

        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String line = timestamp + " - " + action;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            System.err.println("FileLogger failed: " + e.getMessage());
        }
    }
}

