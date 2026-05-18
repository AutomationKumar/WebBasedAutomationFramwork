package com.listeners;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.azure.utils.UpdateExecutionTimeIntoDB;
import com.businessnext.login.page.WebLoginPage;
import com.drivermanager.DriverManager;
import com.reports.BuildVersionManager;
import com.reports.ExtentLogger;
import com.reports.ExtentReport;
import com.reports.MultiReportManager;
import com.reports.StepTraceManager;
import com.setup.BaseClass;
import com.utilities.JsonDataUtils;
import com.utilities.QueryResolver;
import com.utilities.ReUsableMethods;
import com.utilities.ReadConfig;
import com.utilities.mongo_cleanup;
import com.utilities.api.LeadService;
import com.utilities.api.TokenManager;

import annotations.FrameworkAnnotation;
import genericLogger.Log;
import io.restassured.response.Response;

public class ListenerClass implements ITestListener, ISuiteListener, IAnnotationTransformer, IExecutionListener {

	String fileName = StringUtils.substringBefore(ReUsableMethods.checkIfAnExtFileExistInDirectoryAndReturnFileName(
			System.getProperty("user.dir") + "/src/test/resources/data/", ".json"), ".");

	private static final ConcurrentHashMap<String, Long> categoryExecutionTimes = new ConcurrentHashMap<>();

	// Counter for flush frequency control (flush combined report every 50 tests)
	private static final AtomicInteger testCompletionCounter = new AtomicInteger(0);
	private static final int FLUSH_FREQUENCY = 100; // Flush every 100 tests

	private static ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> moduleStatsMap = new ConcurrentHashMap<>();

	private static final long TEST_TIMEOUT_MS = 30000;

	/*
	 * private static final ConcurrentHashMap<String, Long> classStartTimes = new
	 * ConcurrentHashMap<>(); public static final ConcurrentHashMap<String, Long>
	 * classExecutionTimes = new ConcurrentHashMap<>();
	 */

	@Override
	public void onStart(ISuite suite) {
		Log.info("========================================");
		Log.info("[ListenerClass] ⏱️ onStart(ISuite) called for suite: " + suite.getName());
		Log.info("[ListenerClass] Suite XML file: " + suite.getXmlSuite().getFileName());
		Log.info("========================================");

		// Check if environment type is set (required for database cleanup)
		Log.info("[ListenerClass] Environment Type: "
				+ (BaseClass.environmentType == null ? "NULL (not set)" : "'" + BaseClass.environmentType + "'"));

		if (BaseClass.environmentType != null && !BaseClass.environmentType.isBlank()) {

			Log.info("[ListenerClass] ✅ Environment type is set - database cleanup will be checked");

			// Execute database cleanup ONCE at the very first suite start
			String cleanupExecuted = System.getProperty("database.cleanup.executed");
			if (cleanupExecuted == null || !cleanupExecuted.equals("true")) {
				long cleanupStartTimeMongoDB = System.currentTimeMillis();
				mongo_cleanup.runMongoCleanup();
				long cleanupDurationMongoDB = System.currentTimeMillis() - cleanupStartTimeMongoDB;

				Log.info("Mongo Database cleanup completed in " + cleanupDurationMongoDB
						+ "ms");

				Log.info("[ListenerClass] 🗑️ Executing database cleanup...");
				long cleanupStartTime = System.currentTimeMillis();

				try {
					boolean cleanupSuccess = com.utilities.DatabaseCleanupUtility.executeCleanup();
					long cleanupDuration = System.currentTimeMillis() - cleanupStartTime;

					if (cleanupSuccess) {
						Log.info("[ListenerClass] ✅ Database cleanup completed successfully in " + cleanupDuration
								+ "ms");
						// Mark cleanup as executed only when cleanup succeeds
						System.setProperty("database.cleanup.executed", "true");
					} else {
						Log.info("[ListenerClass] ⚠️ Database cleanup completed with some errors in " + cleanupDuration
								+ "ms");
					}
				} catch (Exception e) {
					Log.error("[ListenerClass] ❌ Database cleanup failed: " + e.getMessage());
					e.printStackTrace();
				}

				Log.info("========================================");

				Log.info("Leads For AE will be Created");
				executeForAELeadCreation("AE Leads");

			} else {
				Log.info("[ListenerClass] ⏭️ Database cleanup already executed - skipping");
				Log.info("========================================");
			}
		} else {
			Log.info("[ListenerClass] ⏭️ Environment type not set - skipping database cleanup");
			Log.info("[ListenerClass] 💡 To enable database cleanup, pass -Denv=<environment> in Maven command");
			Log.info("========================================");
		}

		// Count total tests in THIS suite
		int suiteTests = countTotalTestsInSuite(suite);

		// ACCUMULATE test count across multiple suites (for master suite with multiple
		// suite-files)
		String existingCountStr = System.getProperty("testng.total.test.count");
		int existingCount = 0;
		if (existingCountStr != null && !existingCountStr.isEmpty()) {
			try {
				existingCount = Integer.parseInt(existingCountStr);
			} catch (NumberFormatException e) {
				existingCount = 0;
			}
		}

		int totalTests = existingCount + suiteTests;
		System.setProperty("testng.total.test.count", String.valueOf(totalTests));
		Log.info("[ListenerClass] Tests in this suite: " + suiteTests + ", Accumulated total: " + totalTests);

		Log.info("[ListenerClass] ⏱️ Calling ExtentReport.initReports()...");
		long initStartTime = System.currentTimeMillis();
		ExtentReport.initReports();
		long initDuration = System.currentTimeMillis() - initStartTime;
		Log.info("[ListenerClass] ⏱️ ExtentReport.initReports() completed in " + initDuration + "ms");
		Log.info("========================================");

	
	}

	/**
	 * Count total number of test methods in the TestNG suite This is used to
	 * determine if multi-report should be enabled Filters by groups if specified: -
	 * If groups are specified (via XML or -Dgroups), only count tests in those
	 * groups - If no groups specified (blank), count all tests
	 */
	private int countTotalTestsInSuite(ISuite suite) {
		int totalTests = 0;

		try {
			// Get groups to include from suite or system property
			java.util.List<String> includedGroups = getIncludedGroups(suite);
			boolean filterByGroups = !includedGroups.isEmpty();

			if (filterByGroups) {
				Log.info("[ListenerClass] Filtering tests by groups: " + includedGroups);
			} else {
				Log.info("[ListenerClass] No group filter - counting all tests");
			}

			// Iterate through all tests in the suite
			for (org.testng.xml.XmlTest test : suite.getXmlSuite().getTests()) {
				// Iterate through all classes in each test
				for (org.testng.xml.XmlClass xmlClass : test.getXmlClasses()) {
					try {
						// Load the class
						Class<?> testClass = Class.forName(xmlClass.getName());

						// Count methods with @Test annotation
						for (java.lang.reflect.Method method : testClass.getDeclaredMethods()) {
							if (method.isAnnotationPresent(org.testng.annotations.Test.class)) {
								org.testng.annotations.Test testAnnotation = method
										.getAnnotation(org.testng.annotations.Test.class);

								// Only count if enabled=true (default is true)
								if (testAnnotation.enabled()) {
									// Filter by groups if specified
									if (filterByGroups) {
										// Check if test method belongs to any of the included groups
										String[] testGroups = testAnnotation.groups();
										if (hasMatchingGroup(testGroups, includedGroups)) {
											totalTests++;
										}
									} else {
										// No group filter - count all tests
										totalTests++;
									}
								}
							}
						}
					} catch (ClassNotFoundException e) {
						Log.error("[ListenerClass] Could not load class: " + xmlClass.getName());
					}
				}
			}

			Log.info("[ListenerClass] Counted " + totalTests + " test methods in suite"
					+ (filterByGroups ? " (filtered by groups)" : " (all tests)"));

		} catch (Exception e) {
			Log.error("[ListenerClass] Error counting tests: " + e.getMessage());
			e.printStackTrace();
		}

		return totalTests;
	}

	/**
	 * Get included groups from suite XML or system property
	 *
	 * @param suite TestNG suite
	 * @return List of included group names (empty if no groups specified)
	 */
	private java.util.List<String> getIncludedGroups(ISuite suite) {
		java.util.List<String> groups = new java.util.ArrayList<>();

		try {
			// First, try to get groups from suite XML
			org.testng.xml.XmlSuite xmlSuite = suite.getXmlSuite();
			if (xmlSuite.getIncludedGroups() != null && !xmlSuite.getIncludedGroups().isEmpty()) {
				groups.addAll(xmlSuite.getIncludedGroups());
				Log.info("[ListenerClass] Found groups in suite XML: " + groups);
			}

			// If no groups in XML, try system property (command line
			// -Dgroups=Regression,Smoke)
			if (groups.isEmpty()) {
				String systemGroups = System.getProperty("groups");
				if (systemGroups != null && !systemGroups.trim().isEmpty()) {
					String[] groupArray = systemGroups.split(",");
					for (String group : groupArray) {
						groups.add(group.trim());
					}
					Log.info("[ListenerClass] Found groups in system property: " + groups);
				}
			}

		} catch (Exception e) {
			Log.error("[ListenerClass] Error getting included groups: " + e.getMessage());
		}

		return groups;
	}

	/**
	 * Check if test method has any matching group
	 *
	 * @param testGroups     Groups defined in @Test annotation
	 * @param includedGroups Groups to include (from suite or system property)
	 * @return true if test has at least one matching group
	 */
	private boolean hasMatchingGroup(String[] testGroups, java.util.List<String> includedGroups) {
		if (testGroups == null || testGroups.length == 0) {
			return false; // Test has no groups
		}

		for (String testGroup : testGroups) {
			if (includedGroups.contains(testGroup)) {
				return true; // Found matching group
			}
		}

		return false; // No matching groups
	}

	@Override
	public void onFinish(ISuite suite) {

		DriverManager.quitAllDrivers();
		generateHtmlReport();

		// Log.info("classExecutionTimes: " + BaseClass.classExecutionTimes);
		Log.info("Inside listener on suite finish");
		String buildVersion = BuildVersionManager.getBuildVersion();
		// launchURLAndGetBuildVersion(ReUsableMethods.openChromeBrowser());

		// ExtentReport.addSuite_Duration1(categoryExecutionTimes,
		// BaseClass.classExecutionTimes);
		// if()
		ReadConfig readConfig = new ReadConfig();
		String insertExecutionTime = readConfig.getingsertExecutionTime();
		if (insertExecutionTime.equalsIgnoreCase("yes")) {
			UpdateExecutionTimeIntoDB.insertExecutionTime(categoryExecutionTimes, buildVersion);
		}
		// Get groups: first from -Dgroups (Maven/CLI), fallback to TestNG suite groups
		String groupString = System.getProperty("groups");
		if (groupString == null || groupString.isEmpty()) {
			// When running individual test from IDE, -Dgroups is not set.
			// Extract groups from the tests that actually ran in this suite.
			java.util.Set<String> runGroups = new java.util.LinkedHashSet<>();
			for (org.testng.ISuiteResult sr : suite.getResults().values()) {
				for (org.testng.ITestResult tr : sr.getTestContext().getPassedTests().getAllResults()) {
					String[] groups = tr.getMethod().getGroups();
					if (groups != null) {
						for (String g : groups)
							runGroups.add(g);
					}
				}
				for (org.testng.ITestResult tr : sr.getTestContext().getFailedTests().getAllResults()) {
					String[] groups = tr.getMethod().getGroups();
					if (groups != null) {
						for (String g : groups)
							runGroups.add(g);
					}
				}
			}
			groupString = runGroups.isEmpty() ? "Individual" : String.join(",", runGroups);
		}
		System.out.println("Inside Listener Groups: " + groupString);
		String localExecution = ReadConfig.isLocalExecutionEnabled();
		System.out.println("Inside Listener localExecution: " + localExecution);
		if (groupString != null && !groupString.isEmpty() && localExecution.equalsIgnoreCase("false")) {
			System.out.println("Mutireport is enabled, so db insert will happen");
			UpdateExecutionTimeIntoDB.insertMethodlevelStats(BaseClass.methodLevelExecutionStats);

			// Write the same data to CSV file in the execution folder
			String csvFilePath = MultiReportManager.getExecutionFolderPath() + "/MethodLevelStats" + ".csv";
			com.azure.utils.WriteMethodStatsToCSV.writeMethodStatsToCSV(BaseClass.methodLevelExecutionStats,
					csvFilePath);
		} else {
			System.out.println("Mutireport is disabled, so db insert will not happen");
		}

		// Get all test methods and extract unique class names

		try {
			Thread.sleep(1000);
			// Final flush of all reports (combined + author) and generate index page
			Log.info("🏁 Final flush: Total tests completed = " + testCompletionCounter.get());
			ExtentReport.flushReport();

		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}

		// try {
		// Thread.sleep(2000);
		// SSUtility.openURLandTakeSS("/test-output/Test-Report-New.html");
		// } catch (InterruptedException e) {

		// e.printStackTrace();
		// }

	}

	@Override
	public void onTestStart(ITestResult result) {
		Log.info("========================================");
		Log.info("[ListenerClass] 🚀 TEST STARTED: " + result.getTestClass().getName() + "."
				+ result.getMethod().getMethodName());
		Log.info("[ListenerClass] Test groups: " + java.util.Arrays.toString(result.getMethod().getGroups()));
		Log.info("========================================");

		// ✅ NEW: Log driver session info at test start
		logDriverSessionInfo(result.getMethod().getMethodName());

		// ✅ Lazy build version retrieval: Get build version from first test's WebDriver
		// This avoids creating separate browser instances during suite initialization
		if (!com.reports.BuildVersionManager.isBuildVersionAvailable()) {
			try {
				String version = com.reports.BuildVersionManager.getBuildVersion();
				Log.info("[ListenerClass] Build version retrieved: " + version);

				// ✅ Update build version in all Extent Reports
				if (version != null && !version.equals("Unknown")) {
					com.reports.ExtentReport.updateBuildVersion();
					Log.info("[ListenerClass] ✅ Build version updated in Extent Reports: " + version);
				}
			} catch (Exception e) {
				Log.info("[ListenerClass] Could not retrieve build version: " + e.getMessage());
			}
		}

		long startTime = System.currentTimeMillis();
		result.setAttribute("startTime", startTime);
		/*
		 * String className = result.getTestClass().getName();
		 * classStartTimes.putIfAbsent(className, System.currentTimeMillis());
		 */
		String[] TestCaseID = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).TestCaseId();
		String id = "";
		for (int i = 0; i < TestCaseID.length; i++) {
			id = id + "[" + TestCaseID[i] + "] ";
		}
		Log.info("Method name is: " + result.getMethod().getMethodName());
		ExtentReport.createTest(result.getMethod().getMethodName() + " " + "TestCase ID -" + id);
		ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).author());
		ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).category());

		ExtentReport.adddevices(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).scriptType());
		StepTraceManager.startTestTrace(result);
		/*
		 * ExtentReport.addCategoriesDuration(result.getMethod().getConstructorOrMethod(
		 * ).getMethod() .getAnnotation(FrameworkAnnotation.class).category());
		 */

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		/*
		 * long endTime = System.currentTimeMillis(); long duration = endTime - (long)
		 * result.getAttribute("startTime"); if (duration > 10000) {
		 * result.setStatus(ITestResult.FAILURE); ExtentLogger.fail(
		 * result.getMethod().getMethodName() +
		 * " - Failed due to longer execution time: " + duration + "ms");
		 * updateStats(result, "fail"); throw new
		 * RuntimeException("Failed due to longer execution time: " + duration + "ms");
		 * } else {
		 */
		ExtentLogger.pass(result.getMethod().getMethodName());
		logExecutionTime(result);
		updateStats(result, "pass");

		// trackExecutionTime(result);

		// Flush combined report every 50 tests (author reports flushed in @AfterClass)
		flushCombinedReportPeriodically();

		try {
			Alert alert = DriverManager.getWdriver().switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
		StepTraceManager.endTestTrace();
		// }

	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (DriverManager.getWdriver() != null) {
			ExtentLogger.info("Driver session for this method is: "
					+ ((RemoteWebDriver) DriverManager.getWdriver()).getSessionId());
		} else {
			ExtentLogger.info("Driver is null in Listener on TestFailure");
		}

		// Take screenshot FIRST before any other operations that might affect driver
		// state
		takeFailureScreenshot(result.getMethod().getMethodName());

		// Log failure without taking another screenshot
		ExtentLogger.failWithoutScreenshot(result.getMethod().getMethodName());
		updateStats(result, "fail");
		logExecutionTime(result);
		// trackExecutionTime(result);
		String exceptionAsString = BaseClass.getExceptionAfterMethodRun(result);

		// Log exception details without taking screenshot (use failWithoutScreenshot)
		ExtentLogger.failWithoutScreenshot("<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
				+ "Exception Occured:Click to see" + "</font>" + "</b >" + "</summary>"
				+ exceptionAsString.replaceAll(",", "\n") + "</details>" + " \n");

		// Flush combined report every 50 tests (author reports flushed in @AfterClass)
		flushCombinedReportPeriodically();

		try {
			Alert alert = DriverManager.getWdriver().switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			Log.info("Exception at test failure" + e);
		}
		StepTraceManager.endTestTrace();
	}

	/**
	 * Take screenshot on test failure This method tries to capture screenshot even
	 * if driver session is unhealthy
	 */
	private void takeFailureScreenshot(String testName) {
		try {
			WebDriver driver = DriverManager.getWdriver();
			Log.info("[Screenshot] takeFailureScreenshot called for: " + testName);
			Log.info("[Screenshot] Driver is null? " + (driver == null));

			if (driver != null) {
				// ✅ FIX: Validate session before attempting screenshot
				boolean sessionValid = isSessionValid(driver);
				Log.info("[Screenshot] Session valid? " + sessionValid);

				if (!sessionValid) {
					Log.info("[Screenshot] ❌ Session is invalid - cannot take screenshot");
					Log.error("WebDriver session is invalid - screenshot cannot be captured");
					ExtentLogger.warn(
							"&#9888; <b>SESSION LOST:</b> Cannot capture screenshot - WebDriver session is invalid");
					ExtentLogger.info("Possible causes: Grid timeout, browser crash, network issue");
					return; // Exit early - no point trying to take screenshot
				}

				String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
				String screenshotName = testName + "_FAILED";

				try {
					Log.info("[Screenshot] Calling Reporting.webCaptureScreen...");
					// Try to take screenshot - session is valid
					Reporting.webCaptureScreen(driver, screenshotName, timeStamp);

					// ✅ FIX: Dynamic screenshot path based on multi-report status
					// Multi-report ENABLED: Reports in test-output/Execution_XXX/ →
					// ../../Screenshot/
					// Multi-report DISABLED: Reports in test-output/ → ../Screenshot/
					String screenshotPathRelative = Reporting.getScreenshotPathForReport(screenshotName, timeStamp);
					String screenshotPathAbsolute = Reporting.getScreenshotAbsolutePath(screenshotName, timeStamp);

					Log.info("[Screenshot] Checking if file exists: " + screenshotPathAbsolute);
					java.io.File f = new java.io.File(screenshotPathAbsolute);
					if (f.exists()) {
						Log.info("[Screenshot] ✓ File exists, adding to Extent Report with path: "
								+ screenshotPathRelative);
						com.reports.ExtentManager.getExtentTest().addScreenCaptureFromPath(screenshotPathRelative);

						// Also add to author report if exists
						if (com.reports.ExtentManager.getAuthorExtentTest() != null) {
							com.reports.ExtentManager.getAuthorExtentTest()
									.addScreenCaptureFromPath(screenshotPathRelative);
						}

						Log.info("Screenshot captured for failed test: " + testName);
						ExtentLogger.info("&#128248; Screenshot captured successfully");
					} else {
						Log.info("[Screenshot] ❌ File NOT found: " + screenshotPathAbsolute);
						Log.info("Screenshot file not found: " + screenshotPathAbsolute);
						ExtentLogger.info("&#9888; Screenshot file not found at: " + screenshotPathAbsolute);
					}
				} catch (Exception e) {
					Log.error("[Screenshot] ❌ Exception in screenshot capture: " + e.getClass().getName() + " - "
							+ e.getMessage());
					e.printStackTrace();
					Log.error("Failed to capture screenshot for test: " + testName + ". Error: " + e.getMessage());
					ExtentLogger.info("&#9888; Unable to capture screenshot: " + e.getClass().getSimpleName() + " - "
							+ e.getMessage());
				}
			} else {
				Log.info("[Screenshot] ❌ Driver is NULL, cannot take screenshot");
				Log.info("Driver is null, cannot take screenshot for: " + testName);
				ExtentLogger.info("&#9888; Driver is null, cannot take screenshot");
			}
		} catch (Exception e) {
			Log.error("[Screenshot] ❌ Outer exception in takeFailureScreenshot: " + e.getMessage());
			e.printStackTrace();
			Log.error("Exception in takeFailureScreenshot: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentLogger.skip(result.getMethod().getMethodName());
		Throwable cause = result.getThrowable();
		if (cause != null) {
			ExtentLogger.info("Reason: " + cause.getMessage());
		}
		logExecutionTime(result);
		updateStats(result, "skip");
		// trackExecutionTime(result);

		// Flush combined report every 50 tests (author reports flushed in @AfterClass)
		flushCombinedReportPeriodically();

		try {
			Alert alert = DriverManager.getWdriver().switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
		StepTraceManager.endTestTrace();
	}

	/*
	 * private void trackExecutionTime(ITestResult result) { String className =
	 * result.getTestClass().getName(); long startTime =
	 * classStartTimes.getOrDefault(className, System.currentTimeMillis()); long
	 * executionTime = System.currentTimeMillis() - startTime;
	 * classExecutionTimes.put(className, executionTime); }
	 */

	private void logExecutionTime(ITestResult result) {
		String category = getCategoryFromTest(result);
		long executionTime = result.getEndMillis() - result.getStartMillis();
		categoryExecutionTimes.put(category, categoryExecutionTimes.getOrDefault(category, 0L) + executionTime);
		Log.info("categoryExecutionTimes hashmap is: " + categoryExecutionTimes);
		Log.info("Category Owner is: " + getCategoryOwnerFromTest(result));
	}

	private String getCategoryFromTest(ITestResult result) {
		String[] categories = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).category();
		// ✅ FIX: Trim category name to avoid duplicate module stats due to spacing
		return categories.length > 0 && categories[0] != null ? categories[0].trim() : "Uncategorized";
	}

	public String getCategoryOwnerFromTest(ITestResult result) {
		String[] authors = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).author();
		// ✅ FIX: Trim author name to avoid duplicate module stats due to spacing
		return authors.length > 0 && authors[0] != null ? authors[0].trim() : "Uncategorized";
	}

	/**
	 * Flush combined report every 50 tests to reduce disk I/O Author reports are
	 * flushed in @AfterClass Thread-safe: Uses AtomicInteger for parallel test
	 * execution Only applies when multi-report is enabled (otherwise flush every
	 * test)
	 */
	private void flushCombinedReportPeriodically() {
		// Check if multi-report is enabled
		if (!com.reports.MultiReportManager.isPerAuthorReportsEnabled()) {
			// Multi-report disabled - flush every test (original behavior)
			try {
				ExtentReport.flushCombinedReport();
			} catch (IOException e) {
				Log.error("Failed to flush combined report: " + e.getMessage());
				e.printStackTrace();
			}
			return;
		}

		// Multi-report enabled - flush every 50 tests
		int count = testCompletionCounter.incrementAndGet();

		// Flush every 50 tests (thread-safe with AtomicInteger)
		if (count % FLUSH_FREQUENCY == 0) {
			try {
				ExtentReport.flushCombinedReport();
				Log.info("✅ Combined report flushed after " + count + " tests (every " + FLUSH_FREQUENCY + " tests)");
			} catch (IOException e) {
				Log.error("Failed to flush combined report: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
		iTestAnnotation.setRetryAnalyzer(RetryAnalyser.class);
	}

	public String launchURLAndGetBuildVersion(WebDriver driver) {
		String buildversion = null;
		/*
		 * int retry = 0; int maxRetry = 3; while (buildversion == null && retry <
		 * maxRetry) {
		 */
		try {
			if (fileName.equalsIgnoreCase("appmauto")) {
				// Assign build version for appmauto
				buildversion = new WebLoginPage(driver).getBuildVersion(BaseClass.appmautoURL);
			} else {
				// Assign build version for default case
				buildversion = new WebLoginPage(driver).getBuildVersion(ReadConfig.getwebURL());
			}
		} catch (Exception e) {
			Log.error("No JSON file present for build version: " + e.getMessage());
			try {
				// Fallback logic in case of failure
				buildversion = new WebLoginPage(driver).getBuildVersion(ReadConfig.getwebURL());
			} catch (Exception fallbackException) {
				Log.error("Fallback attempt also failed: " + fallbackException.getMessage());
			}
		} finally {
			Log.info("WebDriver is: " + driver);
			Log.info("Build version is: " + buildversion);
			if (Objects.nonNull(driver)) {
				driver.quit();
			}
		}
		/*
		 * retry++; }
		 */
		return buildversion;
	}

	// @Override
	// public void onExecutionFinish() {
	// System.out.println("Test Execution Finished. Checking for active WebDriver
	// sessions...");
	//
	// try {
	// // Try to quit the WebDriver session if still active
	// WebDriver driver = DriverManager.getWdriver();
	// System.out.println("Driver is: " + driver);
	// String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
	// System.out.println("sessionId: " + sessionId);
	// if (driver != null) {
	// driver.quit(); // Release the session on the Selenium Grid node
	// DriverManager.unload();
	// System.out.println("Selenium Grid Node thread released.");
	// }
	// } catch (Exception e) {
	// System.out.println("Exception while releasing Selenium Grid thread: " +
	// e.getMessage());
	// }
	// }

	private void initializeModuleIfAbsent(String moduleName) {
		moduleStatsMap.computeIfAbsent(moduleName, k -> {
			ConcurrentHashMap<String, Object> stats = new ConcurrentHashMap<>();
			stats.put("owners", ConcurrentHashMap.newKeySet()); // Thread-safe Set<String>
			stats.put("pass", new AtomicInteger(0));
			stats.put("fail", new AtomicInteger(0));
			stats.put("skip", new AtomicInteger(0));
			stats.put("total", new AtomicInteger(0));
			stats.put("testCaseCount", new AtomicInteger(0)); // Track total test cases
			return stats;
		});
	}

	private void addOwnerToModule(String moduleName, String owner) {
		initializeModuleIfAbsent(moduleName);
		Set<String> owners = (Set<String>) moduleStatsMap.get(moduleName).get("owners");
		owners.add(owner);
	}

	private void incrementCount(String moduleName, String owner, String status) {
		initializeModuleIfAbsent(moduleName);
		addOwnerToModule(moduleName, owner);
		ConcurrentHashMap<String, Object> stats = moduleStatsMap.get(moduleName);

		// Atomic increment
		((AtomicInteger) stats.get(status.toLowerCase())).incrementAndGet();
		((AtomicInteger) stats.get("total")).incrementAndGet();
	}

	private void incrementCount(String moduleName, String owner, String status, int testCaseCount) {
		initializeModuleIfAbsent(moduleName);
		addOwnerToModule(moduleName, owner);
		ConcurrentHashMap<String, Object> stats = moduleStatsMap.get(moduleName);

		// Atomic increment
		((AtomicInteger) stats.get(status.toLowerCase())).incrementAndGet();
		((AtomicInteger) stats.get("total")).incrementAndGet();
		((AtomicInteger) stats.get("testCaseCount")).addAndGet(testCaseCount);
	}

	private void updateStats(ITestResult result, String status) {
		String[] groups = result.getMethod().getGroups();
		String moduleName = getCategoryFromTest(result);
		String moduleOwner = getCategoryOwnerFromTest(result);

		// Get test case count from annotation
		try {
			String[] testCaseIds = result.getMethod().getConstructorOrMethod().getMethod()
					.getAnnotation(FrameworkAnnotation.class).TestCaseId();
			int testCaseCount = countTestCases(testCaseIds);
			incrementCount(moduleName, moduleOwner, status, testCaseCount);
		} catch (Exception e) {
			// Fallback if annotation is missing
			incrementCount(moduleName, moduleOwner, status);
		}
	}

	/**
	 * Count total test cases from TestCaseId annotation Handles multiple formats:
	 * 1. Multiple array elements: {"123", "456", "789"} = 3 test cases 2.
	 * Comma-separated in single element: {"123, 456, 789"} = 3 test cases 3. Mixed:
	 * {"123, 456", "789"} = 3 test cases 4. Empty array: {} = 0 test cases 5. Empty
	 * string: {""} = 0 test cases 6. Whitespace only: {" "} = 0 test cases
	 *
	 * @param testCaseIds Array of test case IDs from annotation
	 * @return Total count of test cases (returns 1 if no valid IDs found, to count
	 *         the method itself)
	 */
	private int countTestCases(String[] testCaseIds) {
		if (testCaseIds == null || testCaseIds.length == 0) {
			return 1; // Empty array = count as 1 method with no test case ID
		}

		int count = 0;
		for (String testCaseId : testCaseIds) {
			if (testCaseId != null && !testCaseId.trim().isEmpty()) {
				// Split by comma to handle cases like "261492, 146642"
				String[] splitIds = testCaseId.split(",");
				for (String id : splitIds) {
					if (id != null && !id.trim().isEmpty()) {
						count++;
					}
				}
			}
		}

		// If no valid test case IDs found, count as 1 (the method itself)
		return count > 0 ? count : 1;
	}

	private void generateHtmlReport() {
		StringBuilder html = new StringBuilder();

		// ====== HTML Header - Email-friendly with inline styles ======
		html.append("<!DOCTYPE html>");
		html.append(
				"<html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		html.append("<title>Module Test Stats Report</title>");
		html.append("</head>");
		html.append(
				"<body style='margin:0; padding:20px; font-family: Arial, Helvetica, sans-serif; background-color:#f4f4f4;'>");

		// Container
		html.append(
				"<div style='max-width:1200px; margin:0 auto; background:#ffffff; border-radius:8px; overflow:hidden; box-shadow:0 4px 6px rgba(0,0,0,0.1);'>");

		// Header
		html.append("<div style='background:#667eea; color:#ffffff; padding:30px; text-align:center;'>");
		html.append(
				"<h1 style='margin:0 0 10px 0; font-size:28px; font-weight:600;'>&#128202; BusinessNext - QA Automation Report</h1>");
		html.append("<p style='margin:0; font-size:14px; opacity:0.95;'>Module-wise Test Statistics & Summary</p>");

		// Add Environment and DB Type if available
		StringBuilder envInfo = new StringBuilder();
		boolean hasEnvInfo = false;

		if (BaseClass.environmentType != null && !BaseClass.environmentType.isBlank()) {
			envInfo.append(
					"<span style='display:inline-block; margin:5px 10px; padding:5px 12px; background:rgba(255,255,255,0.2); border-radius:4px; font-size:12px;'>");
			envInfo.append("&#127760; Environment: <strong>").append(BaseClass.environmentType).append("</strong>");
			envInfo.append("</span>");
			hasEnvInfo = true;
		}

		try {
			String dbType = com.utilities.ReadConfig.getDatabaseType();
			if (dbType != null && !dbType.isBlank()) {
				envInfo.append(
						"<span style='display:inline-block; margin:5px 10px; padding:5px 12px; background:rgba(255,255,255,0.2); border-radius:4px; font-size:12px;'>");
				envInfo.append("&#128190; Database: <strong>").append(dbType.toUpperCase()).append("</strong>");
				envInfo.append("</span>");
				hasEnvInfo = true;
			}
		} catch (Exception e) {
			// Ignore if DB type is not available
		}

		if (hasEnvInfo) {
			html.append("<div style='margin-top:15px;'>").append(envInfo.toString()).append("</div>");
		}

		html.append("</div>");

		// ====== Summary Section ======
		int grandTotal = 0;
		int totalPassed = 0;
		int totalFailed = 0;
		int totalSkipped = 0;
		int totalTestCases = 0;

		for (ConcurrentHashMap<String, Object> stats : moduleStatsMap.values()) {
			totalPassed += ((AtomicInteger) stats.get("pass")).get();
			totalFailed += ((AtomicInteger) stats.get("fail")).get();
			totalSkipped += ((AtomicInteger) stats.get("skip")).get();
			grandTotal += ((AtomicInteger) stats.get("total")).get();
			totalTestCases += ((AtomicInteger) stats.get("testCaseCount")).get();
		}

		// Summary Cards - Using table for better email compatibility
		html.append("<div style='padding:30px; background:#f8f9fa;'>");
		html.append("<table width='100%' cellpadding='10' cellspacing='10' style='border-collapse:separate;'>");
		html.append("<tr>");

		// Card 1: Total Methods
		html.append(
				"<td style='background:#ffffff; padding:20px; border-radius:8px; text-align:center; box-shadow:0 2px 4px rgba(0,0,0,0.1);'>");
		html.append(
				"<div style='font-size:11px; color:#6c757d; text-transform:uppercase; letter-spacing:1px; margin-bottom:8px; font-weight:600;'>Total Methods</div>");
		html.append("<div style='font-size:32px; font-weight:bold; color:#667eea;'>").append(grandTotal)
				.append("</div>");
		html.append("</td>");

		// Card 2: Total Test Cases
		html.append(
				"<td style='background:#ffffff; padding:20px; border-radius:8px; text-align:center; box-shadow:0 2px 4px rgba(0,0,0,0.1);'>");
		html.append(
				"<div style='font-size:11px; color:#6c757d; text-transform:uppercase; letter-spacing:1px; margin-bottom:8px; font-weight:600;'>Total Test Cases</div>");
		html.append("<div style='font-size:32px; font-weight:bold; color:#764ba2;'>").append(totalTestCases)
				.append("</div>");
		html.append("</td>");

		// Card 3: Passed Methods
		html.append(
				"<td style='background:#ffffff; padding:20px; border-radius:8px; text-align:center; box-shadow:0 2px 4px rgba(0,0,0,0.1);'>");
		html.append(
				"<div style='font-size:11px; color:#6c757d; text-transform:uppercase; letter-spacing:1px; margin-bottom:8px; font-weight:600;'>Passed Methods</div>");
		html.append("<div style='font-size:32px; font-weight:bold; color:#28a745;'>").append(totalPassed)
				.append("</div>");
		html.append("</td>");

		// Card 4: Failed Methods
		html.append(
				"<td style='background:#ffffff; padding:20px; border-radius:8px; text-align:center; box-shadow:0 2px 4px rgba(0,0,0,0.1);'>");
		html.append(
				"<div style='font-size:11px; color:#6c757d; text-transform:uppercase; letter-spacing:1px; margin-bottom:8px; font-weight:600;'>Failed Methods</div>");
		html.append("<div style='font-size:32px; font-weight:bold; color:#dc3545;'>").append(totalFailed)
				.append("</div>");
		html.append("</td>");

		// Card 5: Skipped Methods
		html.append(
				"<td style='background:#ffffff; padding:20px; border-radius:8px; text-align:center; box-shadow:0 2px 4px rgba(0,0,0,0.1);'>");
		html.append(
				"<div style='font-size:11px; color:#6c757d; text-transform:uppercase; letter-spacing:1px; margin-bottom:8px; font-weight:600;'>Skipped Methods</div>");
		html.append("<div style='font-size:32px; font-weight:bold; color:#ffc107;'>").append(totalSkipped)
				.append("</div>");
		html.append("</td>");

		html.append("</tr>");
		html.append("</table>");
		html.append("</div>");

		// Table Section
		html.append("<div style='padding:30px;'>");
		html.append(
				"<h2 style='font-size:22px; color:#2c3e50; margin:0 0 20px 0; font-weight:600;'>Module-wise Summary</h2>");
		html.append(
				"<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; background:#ffffff; border-radius:8px; overflow:hidden; box-shadow:0 2px 4px rgba(0,0,0,0.1);'>");
		html.append("<thead>");
		html.append("<tr style='background:#667eea; color:#ffffff;'>");
		html.append(
				"<th style='padding:14px 12px; text-align:left; font-weight:600; font-size:12px; text-transform:uppercase; letter-spacing:0.5px;'>Module</th>");
		html.append(
				"<th style='padding:14px 12px; text-align:left; font-weight:600; font-size:12px; text-transform:uppercase; letter-spacing:0.5px;'>Owner(s)</th>");
		html.append(
				"<th style='padding:14px 12px; text-align:center; font-weight:600; font-size:12px; text-transform:uppercase; letter-spacing:0.5px;'>Passed</th>");
		html.append(
				"<th style='padding:14px 12px; text-align:center; font-weight:600; font-size:12px; text-transform:uppercase; letter-spacing:0.5px;'>Failed</th>");
		html.append(
				"<th style='padding:14px 12px; text-align:center; font-weight:600; font-size:12px; text-transform:uppercase; letter-spacing:0.5px;'>Skipped</th>");
		html.append(
				"<th style='padding:14px 12px; text-align:center; font-weight:600; font-size:12px; text-transform:uppercase; letter-spacing:0.5px;'>Total Methods</th>");
		html.append(
				"<th style='padding:14px 12px; text-align:center; font-weight:600; font-size:12px; text-transform:uppercase; letter-spacing:0.5px;'>Total Test Cases</th>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody>");

		List<Map.Entry<String, ConcurrentHashMap<String, Object>>> modulesList = new ArrayList<>(
				moduleStatsMap.entrySet());

		// 2. Sort by failure count (desc), then by module name
		modulesList.sort((e1, e2) -> {
			int fail1 = ((AtomicInteger) e1.getValue().get("fail")).get();
			int fail2 = ((AtomicInteger) e2.getValue().get("fail")).get();

			// Failures first
			if (fail1 != fail2) {
				return Integer.compare(fail2, fail1); // descending order
			}

			// Then alphabetically by module name
			return e1.getKey().compareTo(e2.getKey());
		});

		for (Map.Entry<String, ConcurrentHashMap<String, Object>> entry : modulesList) {
			String moduleName = entry.getKey();
			ConcurrentHashMap<String, Object> stats = entry.getValue();

			Set<String> ownersSet = (Set<String>) stats.get("owners");
			String owners = (ownersSet != null) ? String.join(", ", ownersSet) : "Unknown";

			int pass = ((AtomicInteger) stats.get("pass")).get();
			int fail = ((AtomicInteger) stats.get("fail")).get();
			int skip = ((AtomicInteger) stats.get("skip")).get();
			int total = ((AtomicInteger) stats.get("total")).get();
			int testCaseCount = ((AtomicInteger) stats.get("testCaseCount")).get();

			html.append("<tr style='border-bottom:1px solid #e9ecef;'>");
			html.append("<td style='padding:12px; font-size:14px; color:#2c3e50; font-weight:600;'>").append(moduleName)
					.append("</td>");
			html.append("<td style='padding:12px; font-size:13px; color:#6c757d;'>").append(owners).append("</td>");
			html.append("<td style='padding:12px; font-size:14px; color:#28a745; font-weight:600; text-align:center;'>")
					.append(pass).append("</td>");
			html.append("<td style='padding:12px; font-size:14px; color:#dc3545; font-weight:600; text-align:center;'>")
					.append(fail).append("</td>");
			html.append("<td style='padding:12px; font-size:14px; color:#ffc107; font-weight:600; text-align:center;'>")
					.append(skip).append("</td>");
			html.append("<td style='padding:12px; font-size:14px; color:#667eea; font-weight:600; text-align:center;'>")
					.append(total).append("</td>");
			html.append("<td style='padding:12px; font-size:14px; color:#764ba2; font-weight:600; text-align:center;'>")
					.append(testCaseCount).append("</td>");
			html.append("</tr>");
		}

		html.append("</tbody>");
		html.append("</table>");
		html.append("</div>"); // Close table section

		// Footer
		html.append("<div style='background:#2c3e50; color:#ffffff; padding:20px; text-align:center;'>");
		html.append("<p style='margin:0 0 5px 0; font-size:12px; opacity:0.9;'>Generated on: "
				+ new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) + "</p>");
		html.append(
				"<p style='margin:0; font-size:12px; opacity:0.8;'>Automation Framework - Module Test Statistics Report</p>");
		html.append("</div>");

		html.append("</div>"); // Close container
		html.append("</body></html>");

		// Determine report path based on multi-report status
		String reportPath;
		if (com.reports.MultiReportManager.isPerAuthorReportsEnabled()) {
			// Multi-report enabled: Create in test-output/Execution_XXX/ folder
			String executionFolder = MultiReportManager.getExecutionFolderPath();
			reportPath = executionFolder + "/module-test-stats.html";
			Log.info("[ModuleStats] Multi-report enabled - Creating module stats report in: " + executionFolder);
		} else {
			// Multi-report disabled: Create in current directory (test-output/)
			reportPath = "module-test-stats.html";
			Log.info("[ModuleStats] Multi-report disabled - Creating module stats report in: test-output/");
		}

		try (Writer writer = new OutputStreamWriter(new FileOutputStream(reportPath), StandardCharsets.UTF_8)) {
			writer.write(html.toString());
			Log.info("Module test stats report generated: " + reportPath);
		} catch (IOException e) {
			Log.info("Error in module test report generation: " + e.getMessage());
		}

	}

	/**
	 * Log driver session information at test start Provides visibility into
	 * WebDriver session state, Grid connection, and timing
	 *
	 * @param testName Name of the test being started
	 */
	private void logDriverSessionInfo(String testName) {
		try {
			WebDriver driver = DriverManager.getWdriver();

			Log.info("========================================");
			Log.info("[SessionInfo] 📊 DRIVER SESSION INFO");
			Log.info("[SessionInfo] Test: " + testName);
			Log.info("[SessionInfo] Thread: " + Thread.currentThread().getName() + " (ID: "
					+ Thread.currentThread().getId() + ")");

			if (driver == null) {
				Log.info("[SessionInfo] ⚠️ Driver is NULL - will be created during test execution");
				Log.info("========================================");
				return;
			}

			// Log driver type and hash
			Log.info("[SessionInfo] Driver Type: " + driver.getClass().getSimpleName());
			Log.info("[SessionInfo] Driver Hash: " + System.identityHashCode(driver));

			// Log session details for RemoteWebDriver (Grid)
			if (driver instanceof RemoteWebDriver) {
				RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
				org.openqa.selenium.remote.SessionId sessionId = remoteDriver.getSessionId();

				Log.info("[SessionInfo] Execution Mode: Remote (Selenium Grid)");
				Log.info("[SessionInfo] Session ID: " + (sessionId != null ? sessionId.toString() : "NULL"));

				// Get capabilities
				try {
					org.openqa.selenium.Capabilities caps = remoteDriver.getCapabilities();
					Log.info("[SessionInfo] Browser: " + caps.getBrowserName() + " " + caps.getBrowserVersion());
					Log.info("[SessionInfo] Platform: " + caps.getPlatformName());
				} catch (Exception e) {
					Log.info("[SessionInfo] ⚠️ Could not retrieve capabilities: " + e.getMessage());
				}

				// Validate session is active
				try {
					String currentUrl = driver.getCurrentUrl();
					Log.info("[SessionInfo] ✅ Session is ACTIVE");
					Log.info("[SessionInfo] Current URL: " + currentUrl);

					// Mark session start time for diagnostics
					com.utilities.SessionDiagnostics.markSessionStart();

				} catch (org.openqa.selenium.WebDriverException e) {
					Log.info("[SessionInfo] ❌ Session is INVALID - " + e.getClass().getSimpleName());
					Log.info("[SessionInfo] Error: " + e.getMessage());
					Log.error("[SessionInfo] Test starting with invalid session - this will likely fail!");
				}
			} else {
				Log.info("[SessionInfo] Execution Mode: Local");
			}

			Log.info("========================================");

		} catch (Exception e) {
			Log.error("[SessionInfo] ❌ Exception while logging session info: " + e.getMessage());
			Log.info("========================================");
		}
	}

	/**
	 * Validate if WebDriver session is still active
	 *
	 * @param driver WebDriver instance to check
	 * @return true if session is valid, false otherwise
	 */
	private boolean isSessionValid(WebDriver driver) {
		try {
			// Quick session validation - try to get session ID
			if (driver instanceof RemoteWebDriver) {
				org.openqa.selenium.remote.SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
				if (sessionId == null) {
					Log.info("[SessionCheck] ❌ Session ID is null");
					return false;
				}

				// Try a simple command to verify Grid connection
				try {
					driver.getCurrentUrl();
					Log.info("[SessionCheck] ✓ Session is valid");
					return true;
				} catch (org.openqa.selenium.WebDriverException e) {
					Log.info("[SessionCheck] ❌ WebDriverException: " + e.getMessage());
					// Log detailed diagnostics
					com.utilities.SessionDiagnostics.logSessionLoss("ListenerClass.isSessionValid", e);
					return false;
				}
			}
			return true; // Non-RemoteWebDriver, assume valid
		} catch (Exception e) {
			Log.error("[SessionCheck] ❌ Exception during validation: " + e.getMessage());
			return false;
		}
	}

	public void executeForAELeadCreation(String lastName) {

		try {

			String token = TokenManager.getToken(BaseClass.auto21User, BaseClass.webpassword);

			String path = "/Configurations/";
			String fileName = "ratingProduct";
			String db = QueryResolver.getDbType().toLowerCase();

			// Fetch layoutId once
			String layoutId = JsonDataUtils.jsonFileReader(path, fileName, db + ".layoutId");

			// Product + Rating combinations
			String[][] leadDataArray = { { "Auto Loan", "Hot", "New" }, { "Auto Loan", "Hot", "New" },
					{ "Auto Loan", "Cold", "Active" },
					{ "Auto Loan", "Cold", "Active" }, { "Credit Card", "Hot", "New" }, { "Credit Card", "Hot", "New" },
					{ "Credit Card", "Cold", "Active" }, { "Credit Card", "Cold", "Active" } };

			for (String[] data : leadDataArray) {

				String productName = data[0];
				String ratingType = data[1];
				String statusCodeType = data[2];

				int productId = Integer
						.parseInt(JsonDataUtils.jsonFileReader(path, fileName, db + ".product." + productName));

				int rating = Integer
						.parseInt(JsonDataUtils.jsonFileReader(path, fileName, db + ".rating." + ratingType));

				int statusCodeId = Integer
						.parseInt(JsonDataUtils.jsonFileReader(path, fileName, db + ".status." + statusCodeType));

				// -------- Create Lead Data Map --------
				Map<String, Object> leadData = new HashMap<>();

				leadData.put("lastname", lastName);
				leadData.put("layoutid", layoutId);
				leadData.put("ratingId", rating);
				leadData.put("productid", productId);
				leadData.put("productid", productId);
				leadData.put("StatuscodeID", statusCodeId);

				// -------- Call API --------
				Response response = LeadService.createObject(token, "Lead", leadData);

				// -------- Extract LeadId --------
				JSONArray resp = new JSONArray(response.asString());
				String leadId = resp.getJSONObject(0).getString("ObjectKey");

				System.out.println("Lead Created -> Product: " + productName + " | Rating: " + ratingType
						+ " | LeadId: " + leadId);
			}

		} catch (Exception e) {
			Log.info("Exception encountered in executing AE Queries " + e);
		}
	}
}
