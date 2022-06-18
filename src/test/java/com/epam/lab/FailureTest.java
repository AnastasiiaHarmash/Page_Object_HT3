package com.epam.lab;

import io.qameta.allure.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
@Epic("Failure Tests")
@Feature("Failure tests")
public class FailureTest {

    private static final Logger logger = LogManager.getLogger(FailureTest.class);

    @Test(description = "This test always fails")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: This test failed by default")
    @Story("Fails Test")
    @Step("Failed Test")
    public void failureTest() {
        logger.info("Run failure test.");
        Assert.assertTrue(false);
    }
}
