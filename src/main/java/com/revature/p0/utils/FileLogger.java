package com.revature.p0.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This factory class creates a singleton which handles logging important information
 * about thrown exceptions to a series of log files.
 */
public class FileLogger {
    private static FileLogger fileLogger;
    private static String logFilePath;
    private static boolean consoleOut;

    /**
     * Private constructor, sets default values for logger object
     */
    private FileLogger() {
        logFilePath = "src/main/resources/logs/";
        consoleOut = false;
    }

    /**
     * Factory method returns reference to singleton. Invokes constructor if necessary.
     * @return a reference to this singleton
     */
    public static FileLogger getFileLogger() {
        if (fileLogger == null) {
            fileLogger = new FileLogger();
        }
        return fileLogger;
    }

    /**
     * Writes exception info to a file
     * @param e caught Exception to be logged
     */
    public void writeExceptionToFile(Exception e) {
        try (Writer fileWriter = new FileWriter(getLogFileName(), true)) {
            String logEntry = formatExceptionLogEntry(e.toString());
            fileWriter.write(logEntry + "\n");

            if(consoleOut) {
                System.out.println(logEntry);
            }

        } catch (IOException ex) {
            System.out.println("Warning! Unable to write exception log entry to file.");
        }
    }

    /**
     * Generates a log file name based on path and current date.
     * @return String relative path to log file
     */
    private String getLogFileName() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return logFilePath + today + ".log";
    }

    /**
     * formats a log entry with the exception message as well as
     * the top relevant stack trace element and current timestamp.
     * @param message the Exception message to be written into log file
     * @return String Fully formatted message which includes top relevant calling stack
     * trace element and timestamp
     */
    private String formatExceptionLogEntry(String message) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String stackInfo = stackTraceElements[3].toString();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("%s   [%s]   %s", timestamp, stackInfo, message);
    }

}
