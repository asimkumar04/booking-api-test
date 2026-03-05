package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utility.ReportUtility;

public class Hooks {

    public static ExtentReports extent = ReportUtility.getInstance();
    public static ExtentTest test;

    //create test before each scenario
    @Before
    public void beforeScenario(io.cucumber.java.Scenario scenario) {
        test = extent.createTest(scenario.getName());
    }

    @After
    public void afterScenario(io.cucumber.java.Scenario scenario) {
        // You can log scenario status here
        if (scenario.isFailed()) {
            test.fail("Scenario Failed");
        } else {
            test.pass("Scenario Passed");
        }
        extent.flush(); 
    }

}
