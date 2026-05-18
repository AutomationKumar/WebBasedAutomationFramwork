package com.setup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.azure.utils.AzureConstants;
import com.azure.utils.FrameworkUtils;
import com.azure.utils.GetTestPoints;
import com.azure.utils.JsonUtility;
import com.azure.utils.Prerequisite;
import com.azure.utils.UpdateExcelWithTestPoint;
import com.azure.utils.returnStatusAsPerTestCase;
import com.businessnext.objects.analyticalexplorer.pages.AnalyticsExplorerConstants;
import com.drivermanager.DriverManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.listeners.ListenerClass;
import com.reports.BuildVersionManager;
import com.utilities.CommonUtils;
import com.utilities.Constants;
import com.utilities.JsonDataUtils;
import com.utilities.PropertyUtils;
import com.utilities.PythonUtilityRunner;
import com.utilities.ReUsableMethods;
import com.utilities.ReadConfig;
import com.utilities.SessionDiagnostics;
import com.utilities.WebWait;

import annotations.FrameworkAnnotation;
import genericLogger.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

@Listeners({ com.healingcapability.HealingReportListener.class })
public class BaseClass {

	static XSSFWorkbook workbook;
	static FileOutputStream fileOut;
	static XSSFSheet spreadSheet;
	static XSSFRow rowhead;
	public FileHandler fh;
	// public static String currentUsertype;

	static File directory; // Directory for Source File

	String isolatedBrowserForTest; // Browser needed when testCase Run individually

	// read config file and initiate variables
	ReadConfig readConfig = new ReadConfig();

	public static final String environmentType = ReadConfig.getEnv();

	public static final String auto21User = PropertyUtils.get("auto21User");
	public static final String auto22User = PropertyUtils.get("auto22User");
	public static final String auto23User = PropertyUtils.get("auto23User");
	public static final String webMasterURL = PropertyUtils.get("webmasterURL");
	public static final String webusername_CP = PropertyUtils.get("webusername_CP");
	public static final String webusername_CNP = PropertyUtils.get("webusername_CNP");
	public static final String auto15User = PropertyUtils.get("automation_All15user");
	public static final String automation_Team2 = PropertyUtils.get("automation_Team2");
	public static final String automation_All34 = PropertyUtils.get("automation_All34");
	public static final String Automation_All35User = PropertyUtils.get("automation_All35user");
	public static final String webNonadminautomation_Ind15 = PropertyUtils.get("automation_Ind15user");
	public static final String auto16User = PropertyUtils.get("automation_All16user");
	public static final String adminUserMaster_Auto6 = PropertyUtils.get("webuserNameMasterAuto6");
	public static final String adminUserMaster_Auto2 = PropertyUtils.get("webuserNameMasterAuto2");
	public static final String NonAdminWebUsername_CP = PropertyUtils.get("webusername_CP");
	public static final String Automation_MakerUser = PropertyUtils.get("Automation_MakerUser");
	public static final String Automation_CheckerUser = PropertyUtils.get("Automation_CheckerUser");
	public static final String auto14_NonAdminUser = PropertyUtils.get("auto4User");
	public static final String A_ind_5_NonAdminUser = PropertyUtils.get("webuserNameA_ind_5");
	public static final String A_ind_share5_NonAdminUser = PropertyUtils.get("WebUser_Non_Admin_A_All_share5");
	public static final String A_sub5_A_NonAdminUser = PropertyUtils.get("A_sub5_A_NonAdminUser");

	public static final String appmautoURL = PropertyUtils.get("appmautoURL");
	public static final String appmautousername = PropertyUtils.get("appmautousername");
	public static final String appmautopassword = PropertyUtils.get("passwordauto2mobile");
	public static final String appmautoWidgetChatbotURL = PropertyUtils.get("appmautoWidgetChatbotURL");
	public static final String adminUserMaster_Auto1 = PropertyUtils.get("webuserNameMasterAuto1");
	public static final String adminUserMaster_Auto4 = PropertyUtils.get("webuserNameMasterAuto4");
	public static final String adminUserAuto = PropertyUtils.get("webuserNameMasterAuto");
	public static final String roleTestUser = PropertyUtils.get("roleTest@gmail.co");
	public static final String user342 = PropertyUtils.get("user342");
	public static final String nonAdminUserAuto = PropertyUtils.get("auto_NonAdmin");
	public static final String nonAdminUserAutoTeam = PropertyUtils.get("autoTeam");

	public static final String mongoDBPassword = ReadConfig.getMongoPassword();

	public static final String nonAdminUserAutomationTeam1 = PropertyUtils.get("automation_Team1");
	public static final String nonAdminUserMaster_AutoAll = PropertyUtils.get("webuserNameMasterAutoAll");
	public static final String nonAdminUser_AutoAll = PropertyUtils.get("webuserNameMasterAutoAll");
	public static final String nonAdminAuto_AllEmail = PropertyUtils.get("Auto_All@crmnext");
	public static final String nonAdminUser_autowidget = PropertyUtils.get("webuserNameMasterAutoWidget");
	public static final String nonAdminUser_automation_ind1 = PropertyUtils.get("automation_ind1");
	public static final String nonAdminUser_automation_ind2 = PropertyUtils.get("automation_ind2");
	public static final String msub = PropertyUtils.get("msub");
	public static final String nonAdminUser_serviceall = PropertyUtils.get("webuserNameMasterServiceall");
	public static final String nonAdminUser_serviceind1 = PropertyUtils.get("webuserNameMasterServiceind1");
	public static final String nonAdminUser_servicesub1 = PropertyUtils.get("webuserNameMasterServicesub1");
	public static final String nonAdminUser_serviceteam = PropertyUtils.get("webuserNameMasterServiceteam");
	public static final String username_priya = PropertyUtils.get("username_priya");
	public static final String nonAdminUser_ST1All = PropertyUtils.get("webuserNameMasterST1_All");
	public static final String nonAdminUser_ST1IND = PropertyUtils.get("webuserNameMasterST1_IND");
	public static final String nonAdminUser_ST2IND = PropertyUtils.get("webuserNameMasterST2_IND");
	public static final String nonAdminUser_ST2SUB = PropertyUtils.get("webuserNameMasterST2_SUB");
	public static final String nonAdminUser_ST2TEAM = PropertyUtils.get("webuserNameMasterST2_TEAM");
	public static final String ST1_SUB = PropertyUtils.get("st1_sub");
	public static final String ST2_SUB = PropertyUtils.get("st2_sub");

	public static final String adminUserMaster_Auto = PropertyUtils.get("webuserNameMasterAuto");
	public static final String mobileauto_Auto = PropertyUtils.get("webuserMobileAuto");

	public static final String nonAdminUser_nehaind = PropertyUtils.get("nehaInd");
	public static final String rahul8User = PropertyUtils.get("username_Rahul8");
	public static final String rahul9User = PropertyUtils.get("username_Rahul9");
	public static final String amUser = PropertyUtils.get("Auto_Manager_User");
	public static final String aaaUser = PropertyUtils.get("automation_AAAuser");
	public static final String jayaUser = PropertyUtils.get("automation_Jayauser");

	public static final String elonUser = PropertyUtils.get("automation_Elonuser");
	public static final String johnUser = PropertyUtils.get("automation_Jhonuser");
	public static final String johnyUser = PropertyUtils.get("automation_Jhonyuser");

	public static final String salesAllUser = PropertyUtils.get("automation_Sales_Alluser");
	public static final String salesIndUser = PropertyUtils.get("automation_Sales_Induser");
	public static final String salesTeamUser = PropertyUtils.get("automation_Sales_Teamuser");
	public static final String subuserUser = PropertyUtils.get("automation_SUBUSER_user");

	public static final String downloadPath = PropertyUtils.get("downloadPath");
	public static final String sourceFileToDBflag = PropertyUtils.get("sourceFileToDB");
	public static final String adminUserMaster_Auto3 = PropertyUtils.get("webuserNameMasterAuto3");
	public static final String dependentField_UserBOC = PropertyUtils.get("autoBOC");
	public static final String dependentField_USER_AUTOMATION1 = PropertyUtils.get("automation1");
	public static final String dataNextbaseURL = PropertyUtils.get("dataNextbaseURL");
	public static final String datanextadmin = PropertyUtils.get("admindatanext");
	public static final String userdatascientist = PropertyUtils.get("userdatascientist");
	public static final String userplatformadmin = PropertyUtils.get("userplatformadmin");
	public static final String userbusiness = PropertyUtils.get("userbusiness");
	public static final String baseURL = PropertyUtils.get("baseURL");
	public static final String freshDB_URLG7 = PropertyUtils.get("freshDb_G7_URL");
	public static final String freshDB_URLG8 = PropertyUtils.get("freshDb_G8_URL");
	public static final String username = PropertyUtils.get("webusernameG8");
	public static final String password = PropertyUtils.get("webpassword");
	public static final String adminusername = PropertyUtils.get("adminusername");
	public static final String adminpassword = PropertyUtils.get("adminpassword");
	public static final String myUserId = PropertyUtils.get("myUserName");
	public static final String freshDBG7_UserName = PropertyUtils.get("freshDbG7");
	public static final String AppiumNodePath = PropertyUtils.get("AppiumNodePath");
	public static final String AppiumPath = PropertyUtils.get("AppiumPath");
	public static final String auto5User = PropertyUtils.get("auto5User");
	public static final String reportNonAdminUser = PropertyUtils.get("non@gmail.co");
	public static final String personalUser = PropertyUtils.get("personalUser");
	public static final String auto4User = PropertyUtils.get("auto4User");
	public static final String auto3User = PropertyUtils.get("webuserNameMasterAuto3");
	public static final String auto1User = PropertyUtils.get("auto1User");
	public static final String autoShare4 = PropertyUtils.get("autoShare4");
	public static final String autoind1 = PropertyUtils.get("webuserNameMasterAUTO_IND1");
	public static final String autoind2 = PropertyUtils.get("webuserNameMasterAUTO_IND2");
	public static final String rameshNonAdminUser = PropertyUtils.get("ramesh@bbc.com");
	public static final String nikMPDNonAdminUser = PropertyUtils.get("Nik1@mpd.com");

	public static final String autosub1 = PropertyUtils.get("webuserNameMasterAUTO_SUB1");
	public static final String autosub2 = PropertyUtils.get("webuserNameMasterAUTO_SUB2");

	public static final String autoteam = PropertyUtils.get("webuserNameMasterAUTO_TEAM");
	public static final String maf = PropertyUtils.get("maf@crmnext.in");
	public static final String caf = PropertyUtils.get("caf@crmnext.in");
	public static final String st1_team_NonAdmin = PropertyUtils.get("st1_team");

	public static final String auto_aaf_USer = PropertyUtils.get("AAF_User");
	public static final String dualRoleUser = PropertyUtils.get("dualRoleUser");

	public static final String AppName = PropertyUtils.get("AppName");
	public static final String freshDBG8_UserName = PropertyUtils.get("freshDb_oracle");
	public static final String automation_ind1 = PropertyUtils.get("automation_ind1");
	public static final String automation_ind1_2 = PropertyUtils.get("automation_ind1_2");

	public static final String automation_all1 = PropertyUtils.get("automation_All1user");
	public static final String automation_sub1 = PropertyUtils.get("automation_Sub1user");
	public static final String auto_sub2 = PropertyUtils.get("auto_Sub2user");
	public static final String automation_team1 = PropertyUtils.get("automation_Team1user");
	public static final String automation_team14 = PropertyUtils.get("automation_Team14");
	public static final String initialSuiteName = PropertyUtils.get("initialSuite");
	public static final String finalSuiteName = PropertyUtils.get("finalSuite");
	public static final String addToImageRepository = AnalyticsExplorerConstants.addToImageRepository;
	public static final String webURL = ReadConfig.getwebURL();

	public static final String rolePersonalUser = PropertyUtils.get("rolePersonalUser");

	private static final String azureIntegrationConfig = PropertyUtils.get("azureIntegrationKey");

	public static final String myUser = PropertyUtils.get("myUserName");

	public static final String outlookUser = PropertyUtils.get("outlookClientUser");
	public static final String outlookPassword = ReadConfig.getOutlookPassword();
	public static final String outlookURL = PropertyUtils.get("outlookURL");

	public static final String hub_URL = PropertyUtils.get("hubURL");

	public static final String webpassword = ReadConfig.getWebPassword();

	public static final String myPassword = ReadConfig.getMYWebPassword();

	public static final String freshDBPassword = ReadConfig.getWebPasswordForFreshDB();
	public static final String port = ReadConfig.getPortname();


	// public String webURL = readConfig.getwebURL();
	public static final String dataNextPassword = ReadConfig.getDataNextPassword();

	public static final String PERSONAL_ACCESS_TOKEN = ReadConfig.getPAT();
	private static final String local = ReadConfig.isLocalExecutionEnabled();
	

	public static AppiumDriver driver;
	protected static AppiumDriverLocalService service;
	public static AppiumServiceBuilder builder;

	// public static WebDriver wdriver;;
	public Boolean switchtoAdminrole = true;
	// public static String executionOn;

	// datanext user login

	public String sourceFileName;

	private static final ConcurrentHashMap<String, Long> classStartTimes = new ConcurrentHashMap<>();
	public static final ConcurrentHashMap<String, Long> classExecutionTimes = new ConcurrentHashMap<>();

	// Method-level execution statistics HashMap
	public static final ConcurrentHashMap<String, HashMap<String, String>> methodLevelExecutionStats = new ConcurrentHashMap<>();

	private static ThreadLocal<String> parentWindowHandle = new ThreadLocal<>();

	public String getParentWindowHandle() {
		return parentWindowHandle.get();
	}

	public void setParentWindowHandle(String handle) {
		parentWindowHandle.set(handle);
	}

	/**
	 * Clear parent window handle ThreadLocal to prevent memory leaks
	 */
	public static void clearParentWindowHandle() {
		parentWindowHandle.remove();
	}

	private static ThreadLocal<String> currentUser = new ThreadLocal<>();

	public String getCurrentUser() {
		return currentUser.get();
	}

	public static void setCurrentUser(String handle) {
		currentUser.set(handle);
	}

	/**
	 * Clear current user ThreadLocal to prevent memory leaks IMPORTANT: Use this
	 * instead of setCurrentUser(null) for proper cleanup
	 */
	public static void clearCurrentUser() {
		currentUser.remove();
	}
	// String parentWindowHandle;
	//
	// public String getParentWindowHandle() {
	// return parentWindowHandle;
	// }
	//
	// public void setParentWindowHandle(String parentWindowHandle) {
	// this.parentWindowHandle = parentWindowHandle;
	// }

	private static final String FILE_PATH = "executed_tests.txt";

	@BeforeTest(alwaysRun = true)
	@Parameters()
	public void beforeTest(ITestContext context) {
		String testName = context.getName();
		Log.info("Running Test: " + testName);
		writeTestNameToFile(testName);
		try {
			Log.info("=== Before <test> tag ===");

			// Initialize driver or setup logic

		} catch (Exception e) {
			Log.info("Exception in @BeforeTest: " + e.getMessage());
			e.printStackTrace();
			throw new SkipException("Skipping tests due to setup failure: " + e.getMessage());
		}
	}

	@AfterTest
	public void afterTest(ITestContext context) {

		Log.info("The driver inside afterTest is " + DriverManager.getWdriver());
		try {
			String testName = context.getName();
			Log.info("=== After <test> tag === " + testName);
			writeTestNameToFile(testName);
		} catch (Exception e) {
			Log.error("Exception in @AfterTest: " + e.getMessage());
			e.printStackTrace();
			throw new SkipException("Skipping tests due to cleanup failure: " + e.getMessage());
		}
		if (DriverManager.getWdriver() != null) {

			DriverManager.getWdriver().quit();
			DriverManager.setWdriver(null);

		}

		DriverManager.unload();
		Log.info("The driver inside afterTest is " + DriverManager.getWdriver());
	}

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "portNumber", "executionOn", "suite" })
	// Parameters made optional to facilitate individual test case execution from
	// Test class itself.

	public void beforeClassSetup(@Optional String portNumber, @Optional String executionOn, @Optional String suite)
			throws Exception {

		String logfilename;
		if ("false".equalsIgnoreCase(local)) {
			Log.configureConsoleLogging();
			if (Boolean.parseBoolean(PropertyUtils.getOrDefaultValue("framework.console.redirect.stdout", "true"))) {
				redirectConsoleOutput();
			}
		}
		if (suite == null) {
			logfilename = "Logs" + "_" + new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		} else {
			logfilename = "suite" + "_" + new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		}
		if (fh == null) {

			try {
				fh = new FileHandler(System.getProperty("user.dir") + "/logs/" + logfilename + ".log", true);
				Log.LOGGER.addHandler(fh);
				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
			} catch (Exception ex) {
				System.out.println("Error in log file creation: " + ex.getLocalizedMessage());
			}
		}

		try { // Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			if (executionOn.equals("mobile")) {
				startService(portNumber);
			} else if (executionOn.toUpperCase().equals("API")) {
				Log.info("API Execution is started");
			}

			else if (executionOn.toUpperCase().equals("WEB")) {
				// ReUsableMethods.clearContentOfFile("D:\\Auto\\Businessnext_Auto\\test-output\\testng-failed.xml");
				// ReUsableMethods.clearContentOfFile("D:\\Auto\\Businessnext_Auto\\testng-failed.xml");

				Log.info("Web Execution Started");
				CommonUtils.isSuiteExecutableInExcel(suite); // Checking if suite is marked for execution in master
				// excel, Suite will not run only if marked as NO.

				if (suite.equalsIgnoreCase(initialSuiteName)) { // Create Source file will be called in
					// only
					// when first Suite is run.
					sourceFileName = createSourceFile("SourceFile.xlsx");
					Log.info("The source File Created is " + sourceFileName);
				} else {
					Log.info("File will be created on first suite execution only the initial suite from config file is"
							+ initialSuiteName + "and initial suite from xml is " + suite);
				}
			} else {
				Log.info("Execution on is not valid. Expected Values are WEB or MOBILE or API");

			}
		} catch (Exception e) {
			Log.info("Before suited encountered exception " + e);

		}
		ReUsableMethods.createNewDirectory(Constants.DOWNLOAD_FOLDER_PATH);

		ReUsableMethods.createExcelFile(Constants.DOWNLOAD_FOLDER_PATH, suite, suite);

		System.out.println("The value of azure key is " + azureIntegrationConfig);

		if (azureIntegrationConfig.equalsIgnoreCase("true")) {
			try {

				// Running prerequisite
				Prerequisite.runPrerequisite();

				// Getting suite id from jSON
				String suiteId_Json = JsonDataUtils.jsonFileReader(AzureConstants.azureFiles, "outputSuiteIds", suite);
				suiteId = Integer.parseInt(suiteId_Json);

				// Getting Test Plan from jSON
				String testPlanID_Json = JsonDataUtils.jsonFileReader(AzureConstants.azureFiles, "outputSuiteIds",
						"testPlanID");
				testPlanId = Integer.parseInt(testPlanID_Json);

				JsonNode testPointJson = GetTestPoints.getTestPoints(testPlanId, suiteId);
				JsonUtility.writeToJson(testPointJson, AzureConstants.jsonPath);

				Log.info("The total test point in jsin I is "
						+ JsonUtility.getValueFromJson(AzureConstants.jsonPath, "count"));

				if (GetTestPoints.continuation_Token != null) {
					JsonNode testPointJsonI = GetTestPoints.getTestPoints(testPlanId, suiteId);
					JsonUtility.writeToJson(testPointJsonI, AzureConstants.jsonPathII);
					Log.info("The total test point in jsin II is "
							+ JsonUtility.getValueFromJson(AzureConstants.jsonPathII, "count"));
				}

			} catch (Exception e) {
				Log.info("The exception encountered is " + e);
			}
		}
		/*
		 * System.out.println("Thread ID in BeforeClass: " +
		 * Thread.currentThread().getId());
		 * System.out.println("Thread name in BeforeClass: " +
		 * Thread.currentThread().getName());
		 */
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "executionOn", "deviceName", "platformName", "platformVersion", "portNumber", "browser" })

	// Parameters made optional to facilitate individual test case execution from
	// Test class itself.
	public void setUpMobile(@Optional String executionOn, @Optional String deviceName, @Optional String platformName,
			@Optional String platformVersion, @Optional String portNumber, @Optional String browser) throws Exception {
		String className = this.getClass().getSimpleName();
		long classStartTime = System.currentTimeMillis();
		classStartTimes.put(className, classStartTime);

		try {

			if ((executionOn.equals("mobile")) && (platformName.equals("Android"))) {
				MobileSetup mobileS = new MobileSetup();
				mobileS.mobileAndroidSetup(deviceName, platformVersion);
				mobileS.appPopupClicks();
				mobileS.mpinSet();

			} else if ((executionOn.equals("mobile")) && (platformName.equals("IOS"))) {
				MobileSetup mobileS = new MobileSetup();// to be changed
				mobileS.mobileAndroidSetup(deviceName, platformVersion);
				mobileS.appPopupClicks();
				mobileS.mpinSet();
			} else if (executionOn.toUpperCase().equals("API")) {
				Log.info("API Execution is started");

			}
			// else if (executionOn.equals("web")) {
			// // driver = new WebDriver();
			// WebSetup webS = new WebSetup();
			// webS.browserSetup(browser, baseURL, headless);
			// }

			else {
				Log.info("Execution on is not valid. Expected Values is web or mobile or API");

			}
		} catch (Exception e) {
			Log.info("Before class encountered exception " + e);
		}
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "executionOn", "deviceName", "platformName", "platformVersion", "portNumber", "browser" })
	// Parameters made optional to facilitate individual test case execution from
	// Test class itself.
	public void setUp(@Optional String executionOn, @Optional String deviceName, @Optional String platformName,
			@Optional String platformVersion, @Optional String portNumber, @Optional String browser, Method method)
			throws Exception {
		Log.info("Environment type is: " + environmentType);
		long beforeMethodStartTime = System.currentTimeMillis();
		String methodName = method.getName();
		String testFullName = method.getDeclaringClass().getName() + "#" + methodName + "()";
		String[] category = getCategoryFromMethod(method);

		WebDriver currentDriver = DriverManager.getWdriver();

		// 🚀 CRITICAL FIX: If previous execution was LOCAL → force new session
		if (DriverManager.isLocalExecution()) {
			Log.info("🔄 Previous test ran on LOCAL. Forcing fresh Grid retry.");

			try {
				currentDriver.quit();
			} catch (Exception e) {
				Log.info("Error quitting local driver: " + e.getMessage());
			}

			DriverManager.setWdriver(null);
			DriverManager.clearExecutionType();
			currentDriver = null;
		}

		// Initialize performance tracking for this test (only if enabled)
		if (ReadConfig.isPerformanceTrackingEnabled()) {
			com.utilities.PerformanceTracker.startTest(testFullName);
		}

		Log.info("⏱️ [BeforeMethod] START for test: " + methodName + " at " + beforeMethodStartTime);
		Log.info("Executing Test Case Name: " + methodName);
		Log.info("Category is: " + Arrays.toString(category));

		// Adjust headless mode based on category
		// if (headless == null) {
		// headless = "false";
		// }
		// headless = adjustHeadlessModeForCategory(category, headless);

		if (executionOn == null) {
			executionOn = "web";
		}

		// Handling WebDriver setup for web execution
		if ("web".equalsIgnoreCase(executionOn)) {
			long webSetupStartTime = System.currentTimeMillis();
			Log.info("⏱️ [BeforeMethod] Web setup START at " + webSetupStartTime);

			long sessionCheckStart = System.currentTimeMillis();
			boolean sessionValid = safeSessionCheck();
			long sessionCheckDuration = System.currentTimeMillis() - sessionCheckStart;
			Log.info("⏱️ [BeforeMethod] Session check completed in " + sessionCheckDuration + "ms, valid: "
					+ sessionValid);
			if (ReadConfig.isPerformanceTrackingEnabled()) {
				com.utilities.PerformanceTracker.trackEvent("SESSION_CHECK", sessionCheckDuration,
						"Session valid: " + sessionValid);
			}

			// FIX: Always get current driver from ThreadLocal instead of caching at start

			// ✅ FAILSAFE: If session is invalid but driver still exists, force cleanup and
			// create new session
			if (!sessionValid && currentDriver != null) {
				Log.info("========================================");
				Log.info("🔧 [FAILSAFE] Session invalid but driver exists - forcing cleanup");
				Log.info("========================================");
				try {
					currentDriver.quit();
				} catch (Exception quitEx) {
					Log.info("[FAILSAFE] Exception during driver.quit(): " + quitEx.getMessage());
				}
				DriverManager.setWdriver(null);
				setCurrentUser(null);
				currentDriver = null;
				Log.info("✅ [FAILSAFE] Driver cleaned up, will create new session");
			}

			if (currentDriver == null) {
				long driverInitStartTime = System.currentTimeMillis();
				Log.info("⏱️ [BeforeMethod] Driver initialization START at " + driverInitStartTime);
				if (ReadConfig.isPerformanceTrackingEnabled()) {
					com.utilities.PerformanceTracker.trackEvent("BROWSER_LAUNCH - START",
							"Launching new browser session");
				}

				initializeWebDriver(browser);

				long driverInitDuration = System.currentTimeMillis() - driverInitStartTime;
				Log.info("⏱️ [BeforeMethod] Driver initialization completed in " + driverInitDuration + "ms");
				if (ReadConfig.isPerformanceTrackingEnabled()) {
					com.utilities.PerformanceTracker.trackEvent("BROWSER_LAUNCH - COMPLETE", driverInitDuration,
							"Browser: " + browser);
				}

				long postLaunchSessionCheckStart = System.currentTimeMillis();
				sessionValid = safeSessionCheck();
				long postLaunchSessionCheckDuration = System.currentTimeMillis() - postLaunchSessionCheckStart;
				Log.info("⏱️ [BeforeMethod] Post-launch session check completed in " + postLaunchSessionCheckDuration
						+ "ms, valid: " + sessionValid);
				if (ReadConfig.isPerformanceTrackingEnabled()) {
					com.utilities.PerformanceTracker.trackEvent("POST_LAUNCH_SESSION_CHECK",
							postLaunchSessionCheckDuration, "Session valid: " + sessionValid);
				}
			} else {
				if (ReadConfig.isPerformanceTrackingEnabled()) {
					com.utilities.PerformanceTracker.trackEvent("SESSION_REUSE", "Reusing existing browser session");
				}
			}

			// Reintroduce the setParentWindowHandle after WebDriver is initialized
			// FIX: Get fresh driver reference after initialization
			long parentWindowHandleStart = System.currentTimeMillis();
			currentDriver = DriverManager.getWdriver();
			if (sessionValid && currentDriver != null) {
				setParentWindowHandle(currentDriver.getWindowHandle());
			}
			long parentWindowHandleDuration = System.currentTimeMillis() - parentWindowHandleStart;
			Log.info("⏱️ [BeforeMethod] Parent window handle set in " + parentWindowHandleDuration + "ms");
			if (ReadConfig.isPerformanceTrackingEnabled()) {
				com.utilities.PerformanceTracker.trackEvent("SET_PARENT_WINDOW_HANDLE", parentWindowHandleDuration,
						"Window handle captured");
			}

			long webSetupDuration = System.currentTimeMillis() - webSetupStartTime;
			Log.info("⏱️ [BeforeMethod] Web setup COMPLETE in " + webSetupDuration + "ms");
			if (ReadConfig.isPerformanceTrackingEnabled()) {
				com.utilities.PerformanceTracker.trackEvent("WEB_SETUP_COMPLETE", webSetupDuration,
						"Browser ready for test");
			}

			long beforeMethodDuration = System.currentTimeMillis() - beforeMethodStartTime;
			Log.info("⏱️ [BeforeMethod] Total BeforeMethod time: " + beforeMethodDuration + "ms");
			if (ReadConfig.isPerformanceTrackingEnabled()) {
				com.utilities.PerformanceTracker.trackEvent("BEFOREMETHOD_COMPLETE", beforeMethodDuration,
						"Ready to start test execution");
			}
		}
		System.out.println("Thread ID in Test: " + Thread.currentThread().getId());
		System.out.println("Thread name in Test: " + Thread.currentThread().getName());

	}

	private String[] getCategoryFromMethod(Method method) {
		try {
			if (method.isAnnotationPresent(FrameworkAnnotation.class)) {
				FrameworkAnnotation annotation = method.getAnnotation(FrameworkAnnotation.class);
				return annotation.category();
			}
		} catch (Exception e) {
			Log.info("Error fetching categories: " + e.getMessage());
		}
		return new String[0]; // return an empty array if no category is found
	}

	// private String adjustHeadlessModeForCategory(String[] category, String
	// headless) {
	// for (String cat : category) {
	// if (isHeadlessCategory(cat)) {
	// return "true"; // Override headless mode for certain categories
	// } else {
	// return headless;
	// }
	// }
	// return headless;
	// }

	// private boolean isHeadlessCategory(String category) {
	// return "David".equalsIgnoreCase(category) ||
	// "Reports".equalsIgnoreCase(category)
	// || "Campaign Designer".equalsIgnoreCase(category) || "Custom Action
	// Button".equalsIgnoreCase(category)
	// || "Knowledge Base".equalsIgnoreCase(category) || "Widget
	// Control".equalsIgnoreCase(category)
	// || "Manage Format".equalsIgnoreCase(category);
	// }

	private void initializeWebDriver(String browser) throws MalformedURLException {
		WebSetup webS = new WebSetup();
		try {
			webS.browserSetup(browser != null ? browser : "chrome", local);
		} catch (MalformedURLException e) {
			Log.info("Error in browser setup: " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			Log.info("Error in browser setup: " + e.getMessage());
			e.printStackTrace();
		}
		WebDriver driver1 = DriverManager.getWdriver();
		if (driver1 != null) {
			// No need to set driver to itself - already set by browserSetup
			System.out.println("[BeforeMethod] Driver re-bound for thread " + Thread.currentThread().getId());
		} else {
			System.out.println("[BeforeMethod] Warning: Driver was null during rebind for thread "
					+ Thread.currentThread().getId());
		}
	}

	int suiteId = 0;
	public int testPlanId = 0;

	@AfterMethod(alwaysRun = true)
	@Parameters({ "executionOn", "suite" })
	public void tearDown(ITestResult result, @Optional String executionOn, @Optional String suite) throws IOException {
		System.out.println("After Method Driver is: " + DriverManager.getWdriver());
		if (DriverManager.isLocalExecution()) {
			Log.info("🧹 Local execution detected. Quitting driver after test.");
			quitWebDriver();
			WebSetup.releaseLocalFallbackSlotIfHeld();
			DriverManager.clearExecutionType();
		}
		if (executionOn == null) {
			executionOn = "web";
		}

		try {
			if ("web".equalsIgnoreCase(executionOn)) {
				handleWebDriverSession(result);
				closeChildWindowsIfNeeded();
			}

			// Handle annotations after test run
			getAnnotationDataAfterRun(result);
			handleAzureIntegration(result, suite);

		} catch (Exception e) {
			Log.info("Ignore Exception in AfterMethod: " + e);
		}

		finalizeDriverSession();

		// Clear caches at the end after all driver operations are complete
		ReUsableMethods.clearCaches();

		// MEMORY LEAK FIX: Clear ThreadLocals if driver was quit
		// This prevents ThreadLocal memory leaks in thread pool scenarios
		if (DriverManager.getWdriver() == null) {
			clearCurrentUser();
			clearParentWindowHandle();
			SessionDiagnostics.clearSessionDiagnostics();
		}

		// Clean up async node info request for this test

		// End performance tracking for this test (only if enabled)
		if (ReadConfig.isPerformanceTrackingEnabled()) {
			com.utilities.PerformanceTracker.endTest();
		}
	}

	private void handleWebDriverSession(ITestResult result) {
		if (DriverManager.getWdriver() != null) {
			if (result.getStatus() != ITestResult.SUCCESS) {
				handleFailedTestSession();
			} else {
				handleSuccessTestSession();
			}
		}
	}

	private void handleFailedTestSession() {
		Log.info("Test failed. Driver session: " + ((RemoteWebDriver) DriverManager.getWdriver()).getSessionId());

		// Try enhanced cleanup to retain session instead of quitting
		try {
			WebDriver driver = DriverManager.getWdriver();

			Log.info("🔧 [Enhanced Cleanup] Starting comprehensive browser state cleanup...");

			// Step 1: Dismiss any alerts that might be open
			try {
				driver.switchTo().alert().dismiss();
				Log.info("✅ [Cleanup] Dismissed alert");
			} catch (Exception e) {
				// No alert present, continue
			}

			// Step 2: Close any child windows that were opened during the failed test
			String parentWindowHandle = getParentWindowHandle();
			Set<String> allWindowHandles = driver.getWindowHandles();
			if (allWindowHandles.size() > 1) {
				closeChildWindows(allWindowHandles, parentWindowHandle);
				Log.info("✅ [Cleanup] Closed " + (allWindowHandles.size() - 1) + " child windows");
			}

			// Step 3: Switch back to parent window
			driver.switchTo().window(parentWindowHandle);
			Log.info("✅ [Cleanup] Switched to parent window");

			// Step 4: Navigate to login page to reset browser state
			try {
				Log.info("🔄 [Cleanup] Navigating to login page: " + webURL);
				driver.get(webURL);

				// Wait for navigation to complete (with timeout)
				long navigationStart = System.currentTimeMillis();
				WebWait.waitForPageLoad(10); // 10 retries = ~10 seconds max
				long navigationDuration = System.currentTimeMillis() - navigationStart;
				Log.info("✅ [Cleanup] Navigation completed in " + navigationDuration + "ms");
			} catch (Exception navEx) {
				Log.info("⚠️ [Cleanup] Navigation failed or timed out: " + navEx.getMessage());
				// Continue with cleanup even if navigation fails
			}

			// Step 5: Clear current user to force re-login
			clearCurrentUser();
			Log.info("✅ [Cleanup] Current user cleared - will force re-login on next test");

			Log.info("✅ [Enhanced Cleanup] Browser session retained with comprehensive cleanup");
			if (ReadConfig.isPerformanceTrackingEnabled()) {
				com.utilities.PerformanceTracker.trackEvent("SESSION_RETAINED_AFTER_FAILURE",
						"Enhanced cleanup successful - session retained");
			}

		} catch (Exception e) {
			Log.info("❌ [Enhanced Cleanup] Failed: " + e.getMessage());
			Log.info("🔄 [Fallback] Quitting driver and will create fresh session for next test");
			if (ReadConfig.isPerformanceTrackingEnabled()) {
				com.utilities.PerformanceTracker.trackEvent("SESSION_QUIT_AFTER_FAILURE_CLEANUP_FAILED",
						"Cleanup failed: " + e.getClass().getSimpleName());
			}
			quitWebDriver(); // Fallback: Only quit if cleanup fails
		}
	}

	private void handleSuccessTestSession() {
		WebDriver driver1 = DriverManager.getWdriver();
		try {
			Set<String> allWindowHandles = driver1.getWindowHandles();
			String parentWindowHandle = getParentWindowHandle();

			if (allWindowHandles.size() > 1) {
				closeChildWindows(allWindowHandles, parentWindowHandle);
			}
			// Ensure we're back on the parent window
			driver1.switchTo().window(parentWindowHandle);
		} catch (Exception e) {
			Log.info("Exception in window handling: " + e);
			quitWebDriver();
		}
	}

	private void closeChildWindows(Set<String> allWindowHandles, String parentWindowHandle) {
		WebDriver driver1 = DriverManager.getWdriver();
		for (String windowHandle : allWindowHandles) {
			if (!windowHandle.equals(parentWindowHandle)) {
				driver1.switchTo().window(windowHandle);
				driver1.close(); // Close the child window
			}
		}
	}

	private void closeChildWindowsIfNeeded() {
		try {
			Set<String> allWindowHandles = DriverManager.getWdriver().getWindowHandles();
			if (allWindowHandles.size() > 1) {
				String parentWindowHandle = getParentWindowHandle();
				closeChildWindows(allWindowHandles, parentWindowHandle);
			}
		} catch (Exception e) {
			Log.info("Exception during child window cleanup: " + e);
		}
	}

	private void handleAzureIntegration(ITestResult result, String suite) {
		if (azureIntegrationConfig.equalsIgnoreCase("true")) {
			try {
				updateAzureIntegrationData(suite, result);
			} catch (Exception e) {
				Log.info("Exception in Azure integration: " + e);
			}
		}
	}

	private void updateAzureIntegrationData(String suite, ITestResult result) throws IOException {
		String[] testCaseID = FrameworkUtils.getAnnotationDataAfterRun(result, "Testcaseid");

		// Read suite and test plan ids from JSON
		suiteId = Integer.parseInt(JsonDataUtils.jsonFileReader(AzureConstants.azureFiles, "outputSuiteIds", suite));
		testPlanId = Integer
				.parseInt(JsonDataUtils.jsonFileReader(AzureConstants.azureFiles, "outputSuiteIds", "testPlanID"));

		// Write data to Excel
		FrameworkUtils.writeArrayToExcel(Constants.DOWNLOAD_FOLDER_PATH, suite, suite, testCaseID,
				FrameworkUtils.getStatus(result));
		UpdateExcelWithTestPoint.getValueFromJsonAndWriteToExcel(AzureConstants.jsonPath,
				Constants.DOWNLOAD_FOLDER_PATH + "/" + suite + ".xlsx");

		// Handle continuation token if exists
		if (GetTestPoints.continuation_Token != null) {
			UpdateExcelWithTestPoint.getValueFromJsonAndWriteToExcel(AzureConstants.jsonPathII,
					Constants.DOWNLOAD_FOLDER_PATH + "/" + suite + ".xlsx");
		}
	}

	private void finalizeDriverSession() {
		// quitWebDriver();
		safeSessionCheck();
		WebDriver driver1 = DriverManager.getWdriver();
		try {
			Log.info("Final WebDriver session info: " + ((RemoteWebDriver) driver1).getSessionId());
			if (driver1 != null && ((RemoteWebDriver) driver1).getSessionId() == null) {
				quitWebDriver();
			}
		} catch (Exception e) {
			Log.info("Exception during final driver handling: " + e);
			quitWebDriver();
		}

	}

	private void quitWebDriver() {
		System.out.println("In quitWebDriver method: " + DriverManager.getWdriver());
		WebDriver driver1 = DriverManager.getWdriver();
		if (driver1 != null) {
			DriverManager.allDrivers.remove(driver1);
			driver1.quit();
			DriverManager.setWdriver(null);

			clearCurrentUser();
		}
	}

	@AfterClass(alwaysRun = true)
	@Parameters({ "executionOn" })
	public void tearDownMobile(ITestResult result, @Optional String executionOn) throws IOException {

		String testClassName = this.getClass().getSimpleName();
		long endTime = System.currentTimeMillis();
		long startTime = classStartTimes.getOrDefault(testClassName, endTime);
		long executionTime = endTime - startTime;
		classExecutionTimes.put(testClassName, executionTime);
		// try {
		//
		// if (executionOn.equals("web")) {
		//
		// System.out.println("The value of driver is " + DriverManager.getWdriver());
		//
		// if (DriverManager.getWdriver() != null) {
		// DriverManager.getWdriver().quit();
		// // DriverManager.unload();
		// DriverManager.setWdriver(null);
		//
		// }
		//
		// setCurrentUser(null);
		//
		// }
		//
		// } catch (Exception e) {
		// System.out.println("Ignore Exception in TearDown " + e);
		// setCurrentUser(null);
		// if (DriverManager.getWdriver() != null) {
		// DriverManager.getWdriver().quit();
		// DriverManager.setWdriver(null);
		//
		// }
		// }
		System.out.println("The driver is " + DriverManager.getWdriver());

		// Flush author reports once per class (reduces disk I/O by 50%)
		try {
			com.reports.ExtentReport.flushAuthorReports();
			Log.info("✅ Author reports flushed after class completion");
		} catch (Exception e) {
			Log.error("Failed to flush author reports in @AfterClass: " + e.getMessage());
		}
	}

	@AfterSuite(alwaysRun = true)
	@Parameters({ "portNumber", "executionOn", "suite" })
	public void AfterSuiteTearDown(@Optional String portNumber, @Optional String executionOn, @Optional String suite)
			throws IOException {
		
		
		   
		    
		
		
		System.out.println("classExecutionTimes: " + classExecutionTimes);

		Log.info("Inside BaseClass on suite finish" + DriverManager.getWdriver());
		try {
			DriverManager.getWdriver().quit();
		} catch (Exception ex) {
			Log.info("Inside BaseClass on suite finish in catch block " + DriverManager.getWdriver());
		}
		try {

			try {
				if (suite.equalsIgnoreCase(finalSuiteName)) {
					finaliseExcel();

					if (sourceFileToDBflag.equalsIgnoreCase("true")) {
						PythonUtilityRunner.runPythonScript(Constants.APP_PATH + Constants.DATA_IMPORTER_PYTHON_UTILITY,
								Constants.sourceFilePath);
					}

				} else {
					Log.info("Excel will be finalised after last suite ");
				}

			} catch (Exception e) {
				Log.info("Exception Found " + e);
			}

		} catch (Exception e) {
			Log.info("Ignore Exception in AfterClass " + e);
		}
		if (azureIntegrationConfig.equalsIgnoreCase("true")) {
			try {
				returnStatusAsPerTestCase.returnStatusAndTestPlan(
						Constants.DOWNLOAD_FOLDER_PATH + "/" + suite + ".xlsx", suiteId, testPlanId);
			} catch (Exception e) {
				System.out.println("Exception Caught while returnStatusAndTestPlan");
			}
		}

		// Generate performance report at the end of the suite (only if enabled)
		if (ReadConfig.isPerformanceTrackingEnabled()) {
			try {
				com.reports.PerformanceReport.generateReport();
				Log.info("📊 Performance report generated successfully at: "
						+ com.reports.PerformanceReport.getReportPath());
			} catch (Exception e) {
				Log.error("Failed to generate performance report: " + e.getMessage());
			}
		} else {
			Log.info("📊 Performance tracking disabled - skipping report generation");
		}

	}

	void startService(String portNumber) throws Exception {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		// Tell builder where node is installed. Or set this path in an environment
		// variable named NODE_PATH
		builder.usingDriverExecutable(new File(AppiumNodePath));
		// Tell builder where Appium is installed. Or set this path in an environment
		// variable named APPIUM_PATH
		builder.withAppiumJS(new File(AppiumPath));
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(Integer.parseInt(portNumber));
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
		builder.withEnvironment(environment);
		builder.withArgument(() -> "--base-path", "/wd/");
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withLogFile(new File(System.getProperty("user.dir") + "/target/resources/appium_server_logs"
				+ portNumber + Thread.currentThread().getId()));

		service = AppiumDriverLocalService.buildService(builder);

		service.start();
		Thread.sleep(20000);
		System.out.println("After Service Start URL: " + service.getUrl());
	}

	public void captureScreen(AppiumDriver driver, String tname) throws IOException {
		TakesScreenshot ts = driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/screenshots/" + tname + ".png");
		System.out.println(System.getProperty("user.dir"));
		FileUtils.copyFile(source, target);
		System.out.println("ScreenShot taken of " + tname);

	}

	public void webCaptureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/screenshots/" + tname + ".png");
		System.out.println(System.getProperty("user.dir"));
		FileUtils.copyFile(source, target);
		System.out.println("ScreenShot taken of " + tname);

	}

	public String randomString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(6);
		return generatedstring;
	}

	public String randomemail() {
		String randomemail = randomString() + "@gmail.com";
		return randomemail;
	}

	static int count;

	public void writeIntoExcel(int column1, int column2, int column3, int column4, int column5, int column6,
			int column7, int column8, String[] caseID, String methodName, String status, int count,
			String[] patternName, String[] scriptType, String[] testCasePriority, String exception, String dateTime) {
		try {
			int i = 0;
			for (i = 0; i < caseID.length; i++) {
				rowhead = spreadSheet.createRow(count);
				XSSFCell cell = rowhead.createCell(column1);
				XSSFCell cell2 = rowhead.createCell(column2);
				XSSFCell cell3 = rowhead.createCell(column3);
				XSSFCell cell4 = rowhead.createCell(column4);
				XSSFCell cell5 = rowhead.createCell(column5);
				XSSFCell cell6 = rowhead.createCell(column6);
				XSSFCell cell7 = rowhead.createCell(column7);
				XSSFCell cell8 = rowhead.createCell(column8);

				cell.setCellValue(caseID[i]);
				cell2.setCellValue(methodName);
				cell3.setCellValue(status);
				cell4.setCellValue(patternName[0]);
				cell5.setCellValue(scriptType[0]);
				cell6.setCellValue(testCasePriority[0]);
				cell7.setCellValue(exception);
				cell8.setCellValue(dateTime);

				System.out.println("value input in excel is " + caseID[i]);
				System.out.println("value input in excel is " + methodName);
				System.out.println("value input in excel is " + status);
				System.out.println("Value input in excel for script type is " + scriptType[0]);
				try {
					System.out.println("value input in excel is " + patternName[0]);
				}

				catch (Exception e) {
					System.out.println("Exception Found while writing pattern name to excel method " + e);
				}
				if (caseID.length <= 1) {
					System.out.println("Inside if");
				} else {
					count = count + 1;
				}

			}
			this.count = this.count + i;
		} catch (Exception e) {
			System.out.println("Exception Found" + e);
		}
	}

	public void getAnnotationDataAfterRun(ITestResult result) {
		String testCasestatus = null;

		// Getting TestCase Id , Pattern Name and scriptType
		// Cache the annotation object to avoid 4 repeated reflection calls
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		FrameworkAnnotation annotation = method.getAnnotation(FrameworkAnnotation.class);

		String[] TestCaseId = annotation.TestCaseId();
		String[] patternName = annotation.category();
		String[] scriptType = annotation.scriptType();
		String[] testCasePriority = annotation.testCasePriority();
		String[] groups = result.getMethod().getGroups();

		String groupString = String.join(",", groups);

		System.out.println(result.getStatus());

		// Getting Test case Status
		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("Test case execution status is pass");
			testCasestatus = "PASS";
		} else if (result.getStatus() == ITestResult.FAILURE) {

			System.out.println("Test case execution status is FAILURE");
			testCasestatus = "Fail";
		} else if (result.getStatus() == ITestResult.SKIP) {

			System.out.println("Test case execution status is Skipped");
			testCasestatus = "Skip";
		} else {
			System.out.println("Test case execution status is Unknown");
			testCasestatus = "Unknown";
		}

		System.out.println("The method name is " + result.getMethod().getMethodName());
		System.out.println("Inside after method");
		System.out.println("Case Id is " + TestCaseId[0]);

		String exceptionAsString = getExceptionAfterMethodRun(result);

		// System.out.println("The xception found iis "+ exceptionAsString);

		System.out.println("Build Version: " + BuildVersionManager.getBuildVersion());

		System.out.println("Environment Name: " + environmentType);
		System.out.println("TestCase IDs: " + String.join(",", TestCaseId));
		System.out.println("Method name: " + result.getMethod().getMethodName());
		System.out.println("Method Result: " + testCasestatus);

		System.out.println("Category Name: " + patternName[0]);
		System.out.println("TestCase owner is: " + new ListenerClass().getCategoryOwnerFromTest(result));
		System.out.println("Exception After Method Run: " + exceptionAsString);

		// Calculate execution time in milliseconds
		long executionTimeMs = result.getEndMillis() - result.getStartMillis();
		System.out.println("Execution Time (ms): " + executionTimeMs);
		String dbType = com.utilities.ReadConfig.getDatabaseType();
		// Set EnvironmentName - extract from URL if environmentType is null
		String environmentName;
		if (environmentType != null && !environmentType.isEmpty()) {
			environmentName = environmentType;
		} else {
			// Extract environment from URL using regex
			// Example: https://automation.crmnextlab.com/master/login/logout -> extract
			// "master"
			String finalwebURL = webURL;
			environmentName = null; // default value
			if (finalwebURL != null) {
				java.util.regex.Pattern pattern = java.util.regex.Pattern
						.compile("https?://[^/]+/([^/]+)/login/logout");
				java.util.regex.Matcher matcher = pattern.matcher(webURL);
				if (matcher.find()) {
					environmentName = matcher.group(1);
				}
			}
		}

		// Create separate database entry for each test case ID
		// This handles TWO formats:
		// Format 1: {"181630", "181631"} - separate array elements
		// Format 2: {"182134, 168085, 182138, 168089"} - comma-separated in single
		// element
		for (int i = 0; i < TestCaseId.length; i++) {
			String testCaseIDString = TestCaseId[i].trim();

			// ✅ FIX: Split by comma in case multiple IDs are in single string
			String[] individualTestCaseIDs = testCaseIDString.split("\\s*,\\s*");

			// Process each individual test case ID
			for (String testCaseID : individualTestCaseIDs) {
				testCaseID = testCaseID.trim();

				// Skip empty strings
				if (testCaseID.isEmpty()) {
					continue;
				}

				// Create a unique key for each test case ID: methodName_testCaseID
				String uniqueKey = result.getMethod().getMethodName() + "_" + testCaseID;

				// Populate methodLevelExecutionStats HashMap for this specific test case ID
				HashMap<String, String> testStats = new HashMap<>();
				testStats.put("BuildVersion", BuildVersionManager.getBuildVersion());
				testStats.put("EnvironmentName", environmentName);
				testStats.put("TestCaseID", testCaseID);
				testStats.put("MethodName", result.getMethod().getMethodName());
				testStats.put("MethodResult", testCasestatus);
				testStats.put("CategoryName", patternName[0]);
				testStats.put("TestCaseOwner", new ListenerClass().getCategoryOwnerFromTest(result));
				testStats.put("ErrorMessage", exceptionAsString);
				testStats.put("ExecutionTime(ms)", String.valueOf(executionTimeMs));
				testStats.put("DBType", String.valueOf(dbType));
				testStats.put("Groups", groupString);
				Log.info("[BaseClass] Stats for " + result.getMethod().getMethodName()
						+ " — Groups: '" + groupString + "' (length=" + groupString.length() + ")");

				// Store in the ConcurrentHashMap using unique key (methodName_testCaseID)
				methodLevelExecutionStats.put(uniqueKey, testStats);
				Log.info("Added to methodLevelExecutionStats - Key: " + uniqueKey + ", TestCaseID: " + testCaseID);
			}
		}

		writeIntoExcel(0, 1, 2, 3, 4, 5, 6, 7, TestCaseId, result.getMethod().getMethodName(), testCasestatus, count,
				patternName, scriptType, testCasePriority, exceptionAsString, ReUsableMethods.getCurrentdateTime());
	}

	public void finaliseExcel() {
		try {
			// directory = new File(System.getProperty("user.home") + "/Desktop/" +
			// "Test.xlsx");
			fileOut = new FileOutputStream(directory);
			workbook.write(fileOut);
			fileOut.close();

			System.out.println("Written Succesffully");
		} catch (Exception e) {
			System.out.println("Exception encountered while finalising excel" + e);
		}
	}

	public String createSourceFile(String fileName) {

		ReUsableMethods.deleteFile(Constants.sourceFilePath, "SourceFile", "xlsx");

		String sourceFileVariable = null; // Initialize outside try block to ensure it's accessible in the return
		// statement
		try {
			sourceFileVariable = fileName;
			directory = new File(Constants.sourceFilePath + sourceFileVariable);
			workbook = new XSSFWorkbook();
			spreadSheet = workbook.createSheet("SourceFile");
		} catch (Exception e) {

			System.out.println("Exception encountered while Creating Source File" + e);
		}
		return sourceFileVariable;
	}

	public static String getExceptionAfterMethodRun(ITestResult result) {
		String exceptionAsString = null;
		try {
			StringWriter sw = new StringWriter();
			result.getThrowable().printStackTrace(new PrintWriter(sw));
			exceptionAsString = sw.toString();
			exceptionAsString = StringUtils.substringBefore(exceptionAsString, "at com.");

		} catch (Exception e) {
			System.out.println("Exception encountered while gettinh exception after Test Run");
		}
		return exceptionAsString;
	}

	public static boolean safeSessionCheck() {
		boolean result = false;
		WebDriver driver1 = DriverManager.getWdriver();
		try {
			if (driver1 != null) {
				// ✅ FIX: Use getCurrentUrl() instead of getTitle() - it's faster and doesn't
				// wait for page load
				String currentUrl = ((RemoteWebDriver) driver1).getCurrentUrl();
				Log.info("Driver URL is: " + currentUrl);
				result = true;
			}
		} catch (Exception e) {
			Log.info("Detected stale WebDriver session. Cleaning up...");
			try {
				if (driver1 != null) {
					driver1.quit();
				}
				DriverManager.unload();
			} catch (Exception ex) {
				Log.info("Exception in safeSessionCheck" + ex);
				DriverManager.unload();
			}
			DriverManager.setWdriver(null);
			clearCurrentUser();
		}
		return result;
	}

	private void writeTestNameToFile(String testName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
			writer.write(testName);
			writer.newLine();
		} catch (Exception e) {
			genericLogger.Log.info("Failed to write test name to file: " + e.getMessage());
		}
	}

	public void redirectConsoleOutput() throws FileNotFoundException {
		File file = new File("logs/testng-console-output.txt");
		file.getParentFile().mkdirs(); // Create 'logs' folder if not exists
		PrintStream fileOut = new PrintStream(new FileOutputStream(file, true), true);
		System.setOut(fileOut); // Redirect standard output (System.out)
		System.setErr(fileOut); // Redirect error output (System.err)
	}
}
