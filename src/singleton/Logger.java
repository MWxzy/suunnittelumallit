package singleton;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance = null;

    private PrintWriter writer;
    private String fileName;
    private boolean isOpen;

    private Logger() {
        this.fileName = "application.log";
        this.isOpen = false;
        openFile();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private void openFile() {
        try {
            if (writer != null) {
                writer.close();
            }
            writer = new PrintWriter(new FileWriter(fileName, true)); // Append mode
            isOpen = true;
            System.out.println("Logger opened file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error opening log file: " + e.getMessage());
            isOpen = false;
        }
    }

    public void setFileName(String newFileName) {
        if (newFileName == null || newFileName.trim().isEmpty()) {
            System.err.println("Invalid file name. Keeping current file.");
            return;
        }

        this.fileName = newFileName;
        openFile();
        write("--- Log file changed to: " + fileName + " ---");
    }

    public void write(String message) {
        if (!isOpen) {
            System.err.println("Logger is not open. Attempting to reopen...");
            openFile();
            if (!isOpen) {
                System.err.println("Failed to reopen logger. Message not logged.");
                return;
            }
        }

        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println("[" + timestamp + "] " + message);
            writer.flush();
        } catch (Exception e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public void close() {
        if (writer != null) {
            write("--- Logger closed ---");
            writer.close();
            isOpen = false;
            System.out.println("Logger closed.");
        }
    }

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.write("Simulation started");
        logger.write("Processing data...");
        logger.write("Data processed successfully");

        logger.setFileName("new_log.txt");
        logger.write("This message goes to the new file");

        logger.write("Simulation finished");

        logger.close();

        Logger anotherLogger = Logger.getInstance();
        System.out.println("Are both logger references the same? " + (logger == anotherLogger));

        anotherLogger.write("Attempting to write after close...");
        anotherLogger.close();
    }
}