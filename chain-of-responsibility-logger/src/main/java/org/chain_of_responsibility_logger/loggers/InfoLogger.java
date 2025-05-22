package org.chain_of_responsibility_logger.loggers;

import org.chain_of_responsibility_logger.constants.enums.LogLevel;

public final class InfoLogger extends Logger {
    public InfoLogger(Logger nextLogger){
        super(LogLevel.INFO, nextLogger);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if(this.level == logLevel){
            System.out.println("INFO log : "+message);
        } else {
            super.log(logLevel, message);
        }
    }
}
