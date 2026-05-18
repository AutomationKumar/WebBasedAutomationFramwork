package com.businessnext.objects.lead.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.ReUsableMethods;

public class LeadHomeDesignerPage {

	WebDriver wdriver;

	public LeadHomeDesignerPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//input[@id='toolbox-search']")
	private WebElement searchTextBox;

	@FindBy(xpath = "(//*[local-name()='g'and @class='Cards']/*[local-name()='rect'])[1]")
	private WebElement newCard_Field;

	@FindBy(xpath = "(//*[local-name()='g'and @class='Cards']/*[local-name()='rect'])[5]")
	private WebElement newRSS_Field;

	@FindBy(xpath = "(//a[@class='button--ld'])[1]")
	private WebElement saveLayout;

	@FindBy(xpath = "//*[local-name()='g' and contains(@class,'card')]")
	private WebElement dashboardCard;

	@FindBy(xpath = "(//*[local-name()='text' and @class='deleteIcon icon'])[4]")
	private WebElement deleteIconCard;

	@FindBy(xpath = "//input[@value='Confirm']")
	private WebElement confirmButton;

	@FindBy(xpath = "//div[@data-autoid='back-button']")
	private WebElement backButton;

	@FindBy(xpath = "//input[@value='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//h6[@data-testid='card-header-title' and text()='Action Buttons']")
	private WebElement actionButtonControl;

	@FindBy(xpath = "//*[local-name()='text' and normalize-space()='Action Buttons']/following-sibling::*[local-name()='text' and @class='icon']")
	private WebElement actionButtonConfigIcon;

	@FindBy(xpath = "//select[@name='FirstList']//option[@value='BTN_MASSUPDATE']")
	private WebElement firstListMassUpdate;

	@FindBy(xpath = "//select[@name='SecondList']//option[@value='BTN_MASSUPDATE']")
	private WebElement secondListMassUpdate;

	@FindBy(xpath = "//select[@name='FirstList']//option[@value='BTN_MASSDELETE']")
	private WebElement firstListMassDelete;

	@FindBy(xpath = "//select[@name='SecondList']//option[@value='BTN_MASSDELETE']")
	private WebElement secondListMassDelete;

	@FindBy(xpath = "//div[@class='memberselectionbutton mr2']//*[1]")
	private WebElement rightArrow;

	@FindBy(xpath = "//div[@class='memberselectionbutton mr2']//*[2]")
	private WebElement leftArrow;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-edit']")
	private WebElement edit;

	@FindBy(xpath = "// a[@data-autoid='button']//div[text()='Save']")
	public WebElement save;

	@FindBy(xpath = "//a[@data-autoid='button' and text()='Save']")
	public WebElement saveDesigner;

	// a[@data-autoid='button']//div[text()='Save']

	public String enterInSearchTextBox(String card) throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webEnterText(searchTextBox, card, "social Card");
		return card;
	}

	public void dragAndDropSocialCard_toLayout(String element) throws InterruptedException, AWTException {
		ReUsableMethods.scrollSlider_ByCount(0);
		Thread.sleep(3000);
		String baseXpath = "//div[@title='CARD']";
		String searchElementXpath = baseXpath.replaceAll("CARD", element);
		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)), newCard_Field);
		Thread.sleep(3000);
	}

	public void dragAndDropRSSFEED_toLayout(String element) throws InterruptedException, AWTException {
		ReUsableMethods.scrollSlider_ByCount(9);
		Thread.sleep(3000);
		String baseXpath = "//div[@title='RSS']";
		String searchElementXpath = baseXpath.replaceAll("RSS", element);
		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)), newRSS_Field);
		Thread.sleep(3000);
	}

	public void clickOnSaveLayout() throws InterruptedException {
		ReUsableMethods.webClickElement(saveLayout, "click on save botton");
		Thread.sleep(2000);
	}

	public void hoverOverDashboardCard() {
		ReUsableMethods.webMoveToElement(dashboardCard, "Dashboard Card");
	}

	public void clickOnDeleteIconForCard() throws InterruptedException {
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(deleteIconCard, "Delete Card");
		Thread.sleep(1000);
	}

	public void clickOnConfirmButton() {
		ReUsableMethods.webClickElement(confirmButton, "Confirm Button");
	}

	public void dragDashboardOnDesignerPage(String dashboard) throws InterruptedException {
		String basicXpath = "//div[@title='abc']";
		String actualXpath = basicXpath.replaceAll("abc", dashboard);
		WebElement source = wdriver.findElement(By.xpath(actualXpath));
		WebElement destination = wdriver.findElement(By.xpath("(//*[local-name()='g' and @class='Cards'])[2]"));
		ReUsableMethods.webDragAndDropWithAction(source, destination);
		Thread.sleep(1000);
	}

	public void clickOnBackButton() {
		ReUsableMethods.webClickElement(backButton, "Back Button");
	}

	public void exitWithSavingLayout() throws InterruptedException {
		clickOnBackButton();
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(saveDesigner, "Save");
		Thread.sleep(2000);
	}

	public boolean isActionButtonControlPresent() throws InterruptedException {
		Thread.sleep(1000);
		return ReUsableMethods.WebIsElementDisplayed(actionButtonControl, "Action Buttons Control");
	}

	public void clickOnActionButtonControlIcon() throws InterruptedException {
		// ReUsableMethods.scrollSlider_ByCount(16);
		// ReUsableMethods.scrollSliderToElementDynamic("vSlider",
		// "//h6[@data-testid='card-header-title' and text()='Action Buttons']",
		// 5, 500,
		// 10);

		ReUsableMethods.scrollElementToCentreOfScreen(actionButtonControl);

		Thread.sleep(2000);
		// ReUsableMethods.scrollElementToCentreOfScreen(actionButtonConfigIcon);
		//ReUsableMethods.webMoveToElement(actionButtonControl, "");
		ReUsableMethods.webClickElement(actionButtonControl, "Control Config Icon");
		ReUsableMethods.webClickElement(edit, "edit");

		Thread.sleep(1000);
	}

	public void selectButtonsInEditInformationPage() throws InterruptedException {
		if (ReUsableMethods.WebIsElementDisplayed(secondListMassUpdate, "2nd Mass Update")) {

			ReUsableMethods.webClickElement(secondListMassUpdate, "Mass Update in Second List");
			ReUsableMethods.webClickElement(leftArrow, "Left Arrow");
		}

		Thread.sleep(1000);
		ReUsableMethods.webClickElement(firstListMassUpdate, "Mass Update in First List");
		ReUsableMethods.webClickElement(rightArrow, "Right Arrow");
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(saveButton, "Save");
		Thread.sleep(2000);
	}

	public void selectButtonsInEditInformationPageMassDelete() throws InterruptedException {
		ReUsableMethods.webClickElement(secondListMassDelete, "Mass Delete in Second List");
		ReUsableMethods.webClickElement(leftArrow, "Left Arrow");
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(firstListMassDelete, "Mass Delete in First List");
		ReUsableMethods.webClickElement(rightArrow, "Right Arrow");
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(saveButton, "Save");
		Thread.sleep(2000);
	}

	public void setToggleState(String label, String state) {

		String xpath = String.format(
				"//div[text()='%s']//parent::div//*[@data-testid='switch']",
				label);

		List<WebElement> elements = ReUsableMethods.findElementByPath(xpath);

		if (elements.isEmpty()) {
			throw new RuntimeException("Toggle not found for label: " + label);
		}

		WebElement toggle = elements.get(0);

		boolean isCurrentlyOn = "true".equalsIgnoreCase(toggle.getAttribute("value"));

		if ("on".equalsIgnoreCase(state) && !isCurrentlyOn) {
			ReUsableMethods.webClickElement(toggle, "Enable toggle for " + label);
		} else if ("off".equalsIgnoreCase(state) && isCurrentlyOn) {
			ReUsableMethods.webClickElement(toggle, "Disable toggle for " + label);
		}
	}

}
