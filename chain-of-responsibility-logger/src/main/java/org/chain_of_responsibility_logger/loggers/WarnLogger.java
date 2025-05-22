package org.chain_of_responsibility_logger.loggers;

import org.chain_of_responsibility_logger.constants.enums.LogLevel;

public final class WarnLogger extends Logger {
    public WarnLogger(Logger nextLogger){
        super(LogLevel.WARN, nextLogger);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if(this.level == logLevel){
            System.out.println("WARN : "+message);
        } else {
            super.log(logLevel, message);
        }
    }
}
