package com.epam.lab;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener ;
import org.testng.ITestResult;

import static com.epam.lab.SmokeTest.driver;


public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);
    public TestListener() {
        BasicConfigurator.configure();
    }

    public void onTestStart(ITestResult iTestResult) {
        logger.info(String.format("I am in %s method %s start", getTestClassName(iTestResult), getTestMethodName(iTestResult)));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(String.format("I am in %s method %s succeed", getTestClassName(iTestResult), getTestMethodName(iTestResult)));
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.info(String.format("I am in %s method %s failed", getTestClassName(iTestResult), getTestMethodName(iTestResult)));
    }

    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(String.format("I am in %s method %s skipped", getTestClassName(iTestResult), getTestMethodName(iTestResult)));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.info(String.format("Test failed but it is in defined success ratio %s in %s", getTestMethodName(iTestResult), getTestClassName(iTestResult)));
    }

    public void onStart(ITestContext iTestContext) {
        logger.info(String.format("I am in onStart method %s on %s", iTestContext.getName(), iTestContext.getStartDate()));
    }

    public void onFinish(ITestContext iTestContext) {
        logger.info(String.format("I am in onFinish method %s on %s", iTestContext.getName(), iTestContext.getEndDate()));
        logger.info("------------------------------------------------------------------------------------------");
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestClassName(ITestResult iTestResult) {
        return iTestResult.getTestClass().getName();
    }

}
