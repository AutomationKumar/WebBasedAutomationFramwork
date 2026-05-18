package com.listeners;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.drivermanager.DriverManager;
import com.utilities.PropertyUtils;
import com.utilities.ScreenshotCompressionUtil;

public class Reporting extends TestListenerAdapter implements IAnnotationTransformer {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// timestamp
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName)
				.viewConfigurer().viewOrder()
				.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY }).apply();

		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		} catch (IOException e) {

			System.out.println("Extent Config file not found");
			e.printStackTrace();

		}

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Enviornment", "QA");
		extent.setSystemInfo("user", "Ritesh");

		htmlReporter.config().setDocumentTitle("CRMNEXT Testing");
		// htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		// htmlReporter.config().setTestViewChartLocation(ChartLocation.Top);

		// Add Auto-Healed styling JavaScript
		String autoHealedJS = com.reports.ExtentReportEnhancer.getAutoHealedStylingJS();
		htmlReporter.config().setJs(autoHealedJS);

	}

	public void onTestStart(ITestResult tr) {
		logger = extent.createTest(tr.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult tr) {
		System.out.println("Inside on test success of REporting.java");
		// logger = extent.createTest(tr.getName());//Create New Entry in report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		// logger.pass(tr.getMethod().getMethodName() +" is Passed");
	}

	public void onTestFailure(ITestResult tr) {
		System.out.println("Inside on test failure of REporting.java");
		// logger = extent.createTest(tr.getName());//Create New Entry in report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		// logger.fail(tr.getMethod().getMethodName() +" is Failed");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// timestamp

		try {
			webCaptureScreen(DriverManager.getWdriver(), tr.getName(), timeStamp);
		} catch (IOException e) {

			e.printStackTrace();
		}

		// ✅ FIX: Dynamic screenshot path based on multi-report status
		// Absolute path for file existence check
		String screenshotPathAbsolute = getScreenshotAbsolutePath(tr.getName(), timeStamp);
		// Relative path for Extent Report (dynamic based on multi-report status)
		String screenshotPathRelative = getScreenshotPathForReport(tr.getName(), timeStamp);

		File f = new File(screenshotPathAbsolute);
		if (f.exists()) {
			System.out
					.println("[Screenshot] ✓ Screenshot found, adding to report with path: " + screenshotPathRelative);
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPathRelative));
		} else {
			System.out.println("[Screenshot] ❌ Screenshot file not found: " + screenshotPathAbsolute);
		}
	}

	public void onTestSkipped(ITestResult tr) {
		// logger = extent.createTest(tr.getName());//Create New Entry in report

		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.BLACK));
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// timestamp

		try {
			webCaptureScreen(DriverManager.getWdriver(), tr.getName(), timeStamp);
		} catch (IOException e) {

			e.printStackTrace();
		}

		// ✅ FIX: Dynamic screenshot path based on multi-report status
		// Absolute path for file existence check
		String screenshotPathAbsolute = getScreenshotAbsolutePath(tr.getName(), timeStamp);
		// Relative path for Extent Report (dynamic based on multi-report status)
		String screenshotPathRelative = getScreenshotPathForReport(tr.getName(), timeStamp);

		File f = new File(screenshotPathAbsolute);
		if (f.exists()) {
			System.out.println("[Screenshot] ✓ Screenshot found for skipped test, adding to report with path: "
					+ screenshotPathRelative);
			logger.skip("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPathRelative));
		} else {
			System.out.println("[Screenshot] ❌ Screenshot file not found for skipped test: " + screenshotPathAbsolute);
		}

	}

	public void onFinish(ITestContext testContext) {
		System.out.println("Inside On finish of REporting.java");
		extent.flush();
	}

	@Override
	public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
		iTestAnnotation.setRetryAnalyzer(RetryAnalyser.class);
	}

	public static void webCaptureScreen(WebDriver driver, String methodName, String timeStamp) throws IOException {
		try {
			System.out.println("[Screenshot] Attempting to take screenshot for: " + methodName);
			File screenshotDir = new File(System.getProperty("user.dir") + "/Screenshot/");
			screenshotDir.mkdirs();
			String targetBasePath = screenshotDir.getAbsolutePath() + "/" + methodName + timeStamp;
			File target = ScreenshotCompressionUtil.captureAndCompressScreenshot(driver, targetBasePath);
			System.out.println("[Screenshot] Target path: " + target.getAbsolutePath());
			System.out.println("[Screenshot] ✓ Screenshot taken successfully: " + methodName);

		} catch (Exception e) {
			System.out.println("[Screenshot] ❌ Exception caught while taking screenshot: " + e.getClass().getName()
					+ " - " + e.getMessage());
			e.printStackTrace();
			// DON'T quit driver here - let the test framework handle it
			// Commenting out driver quit to preserve driver state for debugging
			// if (DriverManager.getWdriver() != null) {
			// DriverManager.getWdriver().quit();
			// }
			// DriverManager.setWdriver(null);
		}

	}

	public static String getBase64Screenshot() {

		try {
			// Get screenshot as File
			File screenshotFile = ((TakesScreenshot) DriverManager.getWdriver()).getScreenshotAs(OutputType.FILE);

			// Read image
			BufferedImage originalImage = ImageIO.read(screenshotFile);

			// Resize
			Image resized = originalImage.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
			BufferedImage resizedBuffered = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = resizedBuffered.createGraphics();
			g2d.drawImage(resized, 0, 0, null);
			g2d.dispose();

			// Write to ByteArray
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(resizedBuffered, "png", baos);
			byte[] imageBytes = baos.toByteArray();

			// Encode to Base64
			return Base64.getEncoder().encodeToString(imageBytes);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get the correct screenshot path for Extent Report based on multi-report
	 * status
	 *
	 * @param screenshotName Screenshot file name
	 * @param timeStamp      Timestamp suffix
	 * @return Relative path to screenshot (../../Screenshot/ for multi-report,
	 *         ../Screenshot/ for single report)
	 */
	public static String getScreenshotPathForReport(String screenshotName, String timeStamp) {
		// Check if multi-report (per-author reports) is enabled
		boolean isMultiReportEnabled = com.reports.MultiReportManager.isPerAuthorReportsEnabled();

		// Multi-report ENABLED: Reports are in test-output/Execution_XXX/ → Need
		// ../../Screenshot/
		// Multi-report DISABLED: Reports are in test-output/ → Need ../Screenshot/
		String pathPrefix = isMultiReportEnabled ? "../../Screenshot/" : "../Screenshot/";
		String screenshotPath = pathPrefix + screenshotName + timeStamp + getScreenshotFileExtension();

		System.out.println("[Screenshot] Multi-report enabled: " + isMultiReportEnabled + " → Using path: "
				+ screenshotPath);

		return screenshotPath;
	}

	public static String getScreenshotAbsolutePath(String screenshotName, String timeStamp) {
		return System.getProperty("user.dir") + "/Screenshot/" + screenshotName + timeStamp + getScreenshotFileExtension();
	}

	public static String getScreenshotFileExtension() {
		boolean compressionEnabled = Boolean.parseBoolean(
				PropertyUtils.getOrDefaultValue("screenshot.compression.enabled", "true"));
		if (!compressionEnabled) {
			return ".png";
		}

		String format = PropertyUtils.getOrDefaultValue("screenshot.format", "JPEG");
		return ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) ? ".jpg" : ".png";
	}

}
