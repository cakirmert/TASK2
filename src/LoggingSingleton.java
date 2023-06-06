import java.util.logging.Logger;

public class LoggingSingleton {
    private static LoggingSingleton instance;
    private Logger logger;

    private LoggingSingleton() {
        // Initialize the logger
        logger = Logger.getLogger(LoggingSingleton.class.getName());
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
}
