package com.setup;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.listeners.ListenerClass;
import com.utilities.DriverSetup;
import com.utilities.ExcelManager;
import com.utilities.FrameworkHelper;
import com.utilities.PerformanceManager;
import com.utilities.SessionManager;

@Listeners({ ListenerClass.class })
public class BaseClass {

    @BeforeSuite(alwaysRun = true)
    @Parameters({ "portNumber", "executionOn", "suite" })
    public void beforeSuite(@Optional String port,
                            @Optional String execution,
                            @Optional String suite) {

        DriverSetup.initializeSuite(port, execution, suite);
        ExcelManager.initialize(suite);
        PerformanceManager.startSuite();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({ "executionOn", "browser" })
    public void beforeClass(@Optional String execution,
                            @Optional String browser) {

        DriverSetup.initializeClass(execution, browser);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({ "executionOn", "browser" })
    public void beforeMethod(@Optional String execution,
                             @Optional String browser,
                             Method method) {

        DriverSetup.initializeDriver(browser);
        SessionManager.start(method);
    }

    @AfterMethod(alwaysRun = true)
    @Parameters({ "executionOn", "suite" })
    public void afterMethod(ITestResult result,
                            @Optional String execution,
                            @Optional String suite) {

        SessionManager.afterTest(result);
        ExcelManager.update(result);
        PerformanceManager.track(result);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {

        SessionManager.afterClass();
    }

    @AfterSuite(alwaysRun = true)
    @Parameters({ "suite" })
    public void afterSuite(@Optional String suite) {

        ExcelManager.finalizeExcel();
        PerformanceManager.generateReport();
        DriverSetup.closeAll();
    }

    protected void captureFailure(ITestResult result) {

        FrameworkHelper.captureScreenshot(result);
    }

    protected void updateExecution(ITestResult result) {

        FrameworkHelper.updateExecution(result);
    }

    protected void updateAzure(ITestResult result) {

        FrameworkHelper.updateAzure(result);
    }

    protected void cleanWindows() {

        SessionManager.closeChildWindows();
    }

    protected void validateSession() {

        SessionManager.validateSession();
    }

    protected void resetBrowser() {

        SessionManager.resetBrowser();
    }

    protected void writeResult(ITestResult result) {

        ExcelManager.write(result);
    }

    protected void flushReports() {

        PerformanceManager.flush();
    }

    protected void createSourceFile() {

        ExcelManager.createSourceFile();
    }

    protected void finalizeExecution() {

        DriverSetup.releaseResources();
    }

    protected void skipExecution(String message) {

        throw new SkipException(message);
    }
}
