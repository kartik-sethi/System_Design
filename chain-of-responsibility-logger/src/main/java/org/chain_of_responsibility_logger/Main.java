package org.chain_of_responsibility_logger;

import org.chain_of_responsibility_logger.loggers.LogManager;

public class Main {
    public static void main(String[] args) {
        LogManager log = LogManager.getInstance();
        log.info("This is info log.");
        log.debug("This is debug log");
        log.warn("This is warn log");
        log.error("This is error log.");
    }
}