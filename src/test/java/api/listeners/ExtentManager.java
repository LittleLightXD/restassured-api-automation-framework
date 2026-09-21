package api.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            sparkReporter.config().setDocumentTitle("API Automation Report");
            sparkReporter.config().setReportName("ShopperStack API Automation");

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "ShopperStack API Automation");
            extent.setSystemInfo("Framework", "REST Assured + TestNG");
            extent.setSystemInfo("Java", "17");
        }

        return extent;
    }
}