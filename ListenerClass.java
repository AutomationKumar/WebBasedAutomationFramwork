package com.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.listeners.helper.ExecutionManager;
import com.listeners.helper.ModuleStatsManager;
import com.listeners.helper.ReportManager;
import com.listeners.helper.ScreenshotManager;

public class ListenerClass implements ITestListener, ISuiteListener,
        IExecutionListener, IAnnotationTransformer {

    @Override
    public void onStart(ISuite suite) {
        ExecutionManager.beforeSuite(suite);
        ReportManager.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExecutionManager.afterSuite(suite);
        ModuleStatsManager.generateReport();
        ReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ReportManager.startTest(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ModuleStatsManager.update(result, "pass");
        ReportManager.pass(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotManager.capture(result);
        ModuleStatsManager.update(result, "fail");
        ReportManager.fail(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ModuleStatsManager.update(result, "skip");
        ReportManager.skip(result);
    }

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor constructor,
                          Method method) {
        annotation.setRetryAnalyzer(RetryAnalyser.class);
    }
}
