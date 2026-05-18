package com.businessnext.objects.lead.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.common.pages.CommonProductFunctions;
import com.utilities.ReUsableMethods;

public class LeadHomePageDesigner {

	WebDriver wdriver;

	public LeadHomePageDesigner(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//a[@data-autoid='button']//div[contains(text(),'Edit')]")
	private WebElement editButton;

	@FindBy(xpath = "//div[@data-autoid='designer-panel-container']//input[@type='text']")
	private WebElement searchComponent;

	@FindBy(xpath = "//div[@title='Card Not Configured.']")
	private WebElement cardSpace;

	@FindBy(xpath = "//div[@title='PageTitle Buttons']")
	private WebElement pageTitleButton;

	@FindBy(xpath = "//input[@name='Grid_SearchTextBox']")
	private WebElement searchLayout_TextBox;

	@FindBy(xpath = "//div[contains(@class, 'designerPanelActionitem')]")
	private WebElement firstElementAfterSearch;

	@FindBy(xpath = "//div[@data-autoid='designer-header-btn']")
	public WebElement saveButton;

	@FindBy(xpath = ".//div[@name='Reset']")
	public WebElement resetButton;

	@FindBy(xpath = "//button[@data-autoid='0_button']")
	private WebElement okButton;

	String preString = "//div[@title='";

	String post = "IST']";

	public void searchComponent(String component) {
		ReUsableMethods.webEnterText(searchComponent, component, "Search Field");
	}

	public void searchLayoutForRole(String roleName) {
		ReUsableMethods.webEnterText(searchLayout_TextBox, roleName, "Search Box Layout");
	}

	public void clickOnEdit() {
		ReUsableMethods.webClickElement(editButton, "Edit Button");
	}

	public void dropComponentToDropLocation() throws InterruptedException, AWTException {
		Thread.sleep(1000);
		// ReUsableMethods.dragAndDropWithRetry(firstElementAfterSearch, cardSpace);
		ReUsableMethods.newDragDropUpdated(firstElementAfterSearch, cardSpace);
	}

	public void resetTheLayout() {
		ReUsableMethods.webClickElement(resetButton, "Reset Button");
		ReUsableMethods.webClickElement(okButton, "OK button");
		ReUsableMethods.webClickElement(saveButton, "Save Button");
		new CommonProductFunctions(wdriver).waitForLoader();
	}

	public boolean isComponentDroppedOnDesigner(String chartName) throws InterruptedException {

		String xpath = preString + chartName + post;
		new CommonProductFunctions(wdriver).waitForLoader();
		Thread.sleep(2000);
		List<WebElement> elements = ReUsableMethods.findElementByPath(xpath);

		return ReUsableMethods.WebIsElementDisplayed(elements.get(0), chartName);
	}

}
