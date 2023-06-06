import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class LoggingSingleton {
    private static LoggingSingleton instance;
    private Logger logger;

    private LoggingSingleton() {
        // Initialize the logger
        logger = Logger.getLogger(LoggingSingleton.class.getName());
        configureLogger();
    }

    public static LoggingSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggingSingleton.class) {
                if (instance == null) {
                    instance = new LoggingSingleton();
                }
            }
        }
        return instance;
    }

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logWarning(String message) {
        logger.warning(message);
    }

    public void logError(String message) {
        logger.severe(message);
    }
    private void configureLogger() {
        try {
            // Create a FileHandler to log messages into a file
            FileHandler fileHandler = new FileHandler("access.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Set the logging level and add the FileHandler to the logger
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
