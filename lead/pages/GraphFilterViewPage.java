package com.businessnext.objects.lead.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.utilities.ReUsableMethods;

public class GraphFilterViewPage {
	WebDriver wdriver;
   public GraphFilterViewPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

   @FindBy(how = How.XPATH, using = "//div[@data-testid='dialog-close']")
   public WebElement closeIcon;
   
   @FindBy(how = How.XPATH, using = "//*[contains(text(),'Filters')]")
   public WebElement filtersTittle;
   
   @FindBy(how = How.XPATH, using = "//select[@data-autoid='GraphFilterId_ctrl']")
   public WebElement ratingId_dropDown;
   
   @FindBy(how = How.XPATH, using = "//label[@data-autoid='GraphFilterId_lbl']")
   public WebElement graphBox;
   
   @FindBy(how = How.XPATH, using = "//i[@class='icon icon-graphchange2']")
   public WebElement chartIcon;
   
   public void clickOnCloseIcon() {
   	ReUsableMethods.webClickElement(closeIcon, "icon");
   	ReUsableMethods.switchToWindowHandle();
   }
   
   public boolean isFiltersIconDisabled() throws InterruptedException {
		 Thread.sleep(1000);
		 return ReUsableMethods.WebIsElementDisplayed(filtersTittle, "icon");
	 }
   
   public String selectLeadRating(String rating) throws InterruptedException {
		ReUsableMethods.webSelectByVisibleText(ratingId_dropDown, rating, "rating");
		Thread.sleep(2000);
	return	ReUsableMethods.webGetFirstSelectedOption(ratingId_dropDown);
		
	}
   
   public boolean isGraphBoxFeasible() throws InterruptedException {
		 Thread.sleep(2000);
		 return ReUsableMethods.WebIsElementDisplayed(graphBox, "icon");
	 }
   
   public boolean isChartIconFeasible() throws InterruptedException {
		 Thread.sleep(2000);
		 return ReUsableMethods.WebIsElementDisplayed(chartIcon, "icon");
	 } 
}
