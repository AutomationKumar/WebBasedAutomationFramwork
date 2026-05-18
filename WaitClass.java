package com.setup;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class WaitClass {

	static AppiumDriver ldriver;

	public WaitClass(AppiumDriver rdriver) {
		System.out.println("In Mobile Wait Class");
		ldriver = rdriver;

	}

	public void waitForExplictVisibility(WebElement element) throws Error {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForExplictVisibilityList(List<WebElement> element) throws Error {
		WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void fluentWaitForClickable(WebElement element) throws Error {
		Wait<AppiumDriver> fluentWait = new FluentWait<AppiumDriver>(ldriver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(org.openqa.selenium.NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void fluentWaitForDisplayed(WebElement element) throws Error {
		Wait<AppiumDriver> fluentWait = new FluentWait<AppiumDriver>(ldriver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(org.openqa.selenium.NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.visibilityOf(element));

	}
	
	
}
