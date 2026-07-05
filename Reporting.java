package com.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.reports.ExtentManager;
import com.utilities.ReportUtil;

public class Reporting extends TestListenerAdapter implements IAnnotationTransformer {

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(
                Status.PASS,
                MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentManager.getTest().log(
                Status.FAIL,
                MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

        ReportUtil.attachFailureScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentManager.getTest().log(
                Status.SKIP,
                MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));

        ReportUtil.attachSkippedScreenshot(result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor constructor,
                          Method method) {

        annotation.setRetryAnalyzer(RetryAnalyser.class);
    }
}
