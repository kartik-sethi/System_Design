package org.chain_of_responsibility_logger.loggers;

import org.chain_of_responsibility_logger.constants.enums.LogLevel;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@code LogManager} is the central entry point for logging messages in the application.
 * It follows the Singleton pattern to ensure a single, thread-safe instance is used across the system.
 *
 * <p>
 * Internally, it uses the Chain of Responsibility pattern to delegate logging responsibilities
 * to different loggers (DebugLogger, InfoLogger, etc.), each handling specific {@link LogLevel}s.
 * </p>
 *
 * Example usage:
 * <pre><code>
 *   LogManager logger = LogManager.getInstance();
 *   logger.debug("Debugging message");
 *   logger.error("An error occurred");
 *</code></pre>
 *
 * @author Kartik Sethi
 * @since 1.0
 */
public final class LogManager {
    private final Logger logger;
    private static final ReentrantLock lock = new ReentrantLock();
    private static volatile LogManager INSTANCE;

    /**
     * Private constructor that initializes the logger chain using {@link LoggerChainFactory}.
     */
    private LogManager() {
        logger = LoggerChainFactory.createLoggerChain();
    }

    /**
     * Provides the singleton instance of {@code LogManager}.
     * Uses double-checked locking with a {@link ReentrantLock} for thread safety.
     *
     * @return the singleton instance of {@code LogManager}
     */
    public static LogManager getInstance() {
        if (Objects.isNull(INSTANCE)) {
            lock.lock();
            try {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new LogManager();
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }

    /**
     * Centralized logging method that applies a timestamp to the message and delegates to the logger chain.
     *
     * <p><strong>DRY Principle:</strong></p>
     * This method encapsulates shared logging behavior such as timestamp formatting and delegation.
     * Specific level methods like {@code debug()}, {@code info()}, etc., reuse this method to avoid repetition.
     * Any changes to logging behavior (e.g., adding thread names, log IDs, formatting) only need to be made here.
     *
     * @param logLevel the level of the log (DEBUG, INFO, WARN, ERROR)
     * @param message  the log message to be logged
     */
    public void log(LogLevel logLevel, String message) {
        String finalMessage = String.format("%s %s", LocalDateTime.now(), message);
        logger.log(logLevel, finalMessage);
    }


    /**
     * Logs a DEBUG level message.
     *
     * @param message the debug message
     */
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Logs an INFO level message.
     *
     * @param message the informational message
     */
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a WARN level message.
     *
     * @param message the warning message
     */
    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    /**
     * Logs an ERROR level message.
     *
     * @param message the error message
     */
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
