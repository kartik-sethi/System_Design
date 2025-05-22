package org.chain_of_responsibility_logger.loggers;

import org.chain_of_responsibility_logger.constants.enums.LogLevel;

public final class ErrorLogger extends Logger {
    public ErrorLogger(Logger nextLogger){
        super(LogLevel.ERROR, nextLogger);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if(this.level == logLevel){
            System.out.println("Error log : "+message);
        } else {
            super.log(logLevel, message);
        }
    }
}
