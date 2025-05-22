package org.chain_of_responsibility_logger.loggers;

import org.chain_of_responsibility_logger.constants.enums.LogLevel;

/**
 * {@code Logger} is an abstract class in the Chain of Responsibility pattern.
 * It represents a base logger that either handles a specific log level or delegates
 * to the next logger in the chain.
 *
 * <p>
 * Each logger (such as {@link DebugLogger}, {@link InfoLogger}, etc.) is responsible for
 * handling specific {@link LogLevel}s. If the logger cannot handle the given log level,
 * it delegates the logging request to the next logger in the chain.
 * </p>
 *
 * <p><strong>Chain of Responsibility:</strong></p>
 * <p>
 * This class forms the core of the Chain of Responsibility pattern, where each concrete
 * subclass (e.g., {@link DebugLogger}, {@link InfoLogger}) processes specific log levels
 * (DEBUG, INFO, etc.). If the current logger cannot handle the request, it forwards
 * the request to the next logger in the chain. If no logger can handle the log level,
 * an {@link IllegalArgumentException} is thrown.
 * </p>
 *
 * <p><strong>Usage:</strong></p>
 * <pre><code>
 *     Logger loggerChain = new DebugLogger(new InfoLogger(new ErrorLogger(null)));
 *     loggerChain.log(LogLevel.INFO, "This is an informational message");
 * </code></pre>
 *
 * @author Kartik Sethi
 * @since 1.0
 */
public sealed abstract class Logger permits DebugLogger, InfoLogger, WarnLogger, ErrorLogger {
    private final Logger nextLogger;
    protected LogLevel level = null;

    /**
     * Constructs a logger with the specified log level and next logger in the chain.
     *
     * @param level the log level this logger is responsible for (e.g., DEBUG, INFO)
     * @param nextLogger the next logger in the chain that will handle log levels it cannot process
     */
    public Logger(LogLevel level, Logger nextLogger) {
        this.level = level;
        this.nextLogger = nextLogger;
    }

    /**
     * Logs a message at the given log level.
     * <p>
     * If the current logger is responsible for the specified log level, it processes the message.
     * If not, the request is passed to the next logger in the chain.
     * </p>
     *
     * @param logLevel the log level of the message (DEBUG, INFO, etc.)
     * @param message the message to be logged
     * @throws IllegalArgumentException if no logger can handle the log level
     */
    protected void log(LogLevel logLevel, String message) {
        if (nextLogger != null) {
            nextLogger.log(logLevel, message);
        } else {
            throw new IllegalArgumentException(String.format("Invalid log level: %s", logLevel.name()));
        }
    }
}
