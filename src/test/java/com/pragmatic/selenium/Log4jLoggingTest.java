package com.pragmatic.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Log4jLoggingTest {
    private static final Logger logger = LogManager.getLogger(Log4jLoggingTest.class);

    @Test
    public void testLog4jLogging(){
        logger.trace("I am a trace log message");
        logger.debug("I am a debug log message");
        logger.info("I am an info level log message");
        logger.error("I am an error level log message ");
        logger.fatal("I am a fatal level log message");

    }
}
