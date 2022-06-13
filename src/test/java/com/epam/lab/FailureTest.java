package com.epam.lab;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FailureTest {

    private static final Logger logger = LogManager.getLogger(FailureTest.class);

    @Test
    public void failureTest() {
        logger.info("Run failure test.");
        Assert.assertTrue(false);
    }
}
