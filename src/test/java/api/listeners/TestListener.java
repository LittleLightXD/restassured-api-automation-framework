package api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.IExecutionListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener
        implements ITestListener, IExecutionListener {

    private static final Logger logger =
            LogManager.getLogger(TestListener.class);

    private static final ExtentReports extent =
            ExtentManager.getExtentReports();

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        ExtentTest test = extent.createTest(testName);

        ExtentTestManager.setTest(test);

        logger.info("TEST STARTED: {}", testName);

        ExtentTest currentTest = ExtentTestManager.getTest();

        if (currentTest != null) {
            currentTest.info("Test started");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        logger.info("TEST PASSED: {}", testName);

        ExtentTest currentTest = ExtentTestManager.getTest();

        if (currentTest != null) {
            currentTest.pass("Test passed successfully");
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        logger.error("TEST FAILED: {}", testName);

        if (result.getThrowable() != null) {

            logger.error(
                    "Failure reason: {}",
                    result.getThrowable().getMessage()
            );

            ExtentTest currentTest = ExtentTestManager.getTest();

            if (currentTest != null) {
                currentTest.fail(result.getThrowable());
            }
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        logger.warn("TEST SKIPPED: {}", testName);

        ExtentTest currentTest = ExtentTestManager.getTest();

        if (currentTest != null) {
            currentTest.skip("Test skipped");
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onExecutionFinish() {

        extent.flush();

        logger.info("Extent report generated successfully");
    }
}