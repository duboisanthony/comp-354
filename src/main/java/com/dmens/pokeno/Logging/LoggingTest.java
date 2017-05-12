package com.dmens.pokeno.Logging;

/**
 * Created by devin on 2017-05-12.
 */


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingTest {

    private static final Logger LOG = LogManager.getLogger(LoggingTest.class);

    public static void main(String... args){
        LOG.debug("This will be printed on debug");
        LOG.info("This will be printed on info");
        LOG.warn("This will be printed on warn");
        LOG.error("This will be printed on error");
        LOG.fatal("This will be printed on fatal");

        LOG.info("Appending string: {}.", "Hello, World");
    }
}
