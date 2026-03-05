package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtility {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports();
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String reportPath = System.getProperty("user.dir") + "/reports/Run_" + timestamp + "/index.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent.attachReporter(spark);

			
		}
		return extent;
	}

}
