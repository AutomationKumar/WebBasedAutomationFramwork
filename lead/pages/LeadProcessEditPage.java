package com.businessnext.objects.lead.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.ReUsableMethods;

public class LeadProcessEditPage {
	WebDriver wdriver;

	public LeadProcessEditPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//a[@data-autoid='STATUSCODE_srch']")
	private WebElement statusCodePicker;
	
	@FindBy(xpath = "//a[@data-autoid='PRODUCT_srch']")
	private WebElement productPicker;
	
	@FindBy(xpath = "//a[@data-autoid='ROLE_srch']")
	private WebElement rolePicker;
	
	@FindBy(xpath = "(//label[@class='css-1onpt9u'])[1]")
	private WebElement selectAllCheckbox;
	
	@FindBy(xpath = "//button[@type='button']")
	private WebElement okButtonForCheckbox;
	
	@FindBy(xpath = "//a[@data-autoid='PRODUCTSELECTIONMODE_0']")
	private WebElement productFeatureModeSingle;
	
	@FindBy(xpath = "//a[@data-autoid='Save']")
	private WebElement saveProcess;
	
	public void clickOnStatusCodePicker() throws InterruptedException {
		ReUsableMethods.scrollDownToElement(statusCodePicker);
		ReUsableMethods.webClickElement(statusCodePicker, "Status Code Picker");
		Thread.sleep(1000);
	}
	
	public void clickOnProductPicker() throws InterruptedException {
		ReUsableMethods.scrollDownToElement(productPicker);
		ReUsableMethods.webClickElement(productPicker, "Product Picker");
		Thread.sleep(1000);
	}
	
	public void clickOnRolePicker() throws InterruptedException {
		ReUsableMethods.scrollDownToElement(rolePicker);
		ReUsableMethods.webClickElement(rolePicker, "Role Picker");
		Thread.sleep(1000);
	}
	
	public void selectAllCheckboxes() throws InterruptedException {
		ReUsableMethods.webClickElement(selectAllCheckbox, "All Checkbox");
		ReUsableMethods.webClickElement(okButtonForCheckbox, "Ok");
		Thread.sleep(1000);
	}
	
	public void selectProductFeatureMode() {
		ReUsableMethods.scrollDownToElement(productFeatureModeSingle);
		ReUsableMethods.webClickElement(productFeatureModeSingle, "Single Mode");
	}
	
	public void saveProcess() throws InterruptedException {
		ReUsableMethods.webClickElement(saveProcess, "Save");
		Thread.sleep(1000);
	}

}
