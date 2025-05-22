package org.chain_of_responsibility_logger.loggers;

public final class LoggerChainFactory {
    private LoggerChainFactory() {}

    public static Logger createLoggerChain() {
        Logger errorLogger = new ErrorLogger(null);
        Logger warnLogger = new WarnLogger(errorLogger);
        Logger debugLogger = new DebugLogger(warnLogger);
        return new InfoLogger(debugLogger);
    }
}
