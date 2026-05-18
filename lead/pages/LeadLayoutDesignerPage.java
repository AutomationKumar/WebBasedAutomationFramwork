package com.businessnext.objects.lead.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.ReUsableMethods;

public class LeadLayoutDesignerPage {

	WebDriver wdriver;

	public LeadLayoutDesignerPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "(//*[local-name()='text' and normalize-space()='Address'])/following::*[local-name()='text' and normalize-space()='[ drop here ]'][1]")
	public WebElement dropDownBelowAddressField;

	@FindBy(xpath = "//input[@id='toolbox-search']")
	private WebElement layoutDesignerSearch;

	@FindBy(xpath = "//i[@class='icon icon-Back']")
	private WebElement backButton_layoutDesigner;

	@FindBy(xpath = "//input[@value='Save']")
	private WebElement saveButton_OnPopup;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[9]")
	private WebElement dropLocation_belowLeadAmount;

	@FindBy(xpath = "//div[normalize-space()='Lead_System' and contains(@data-autoid,'layoutname_')]//ancestor::div[contains(@id,'quickView_')]//a[contains(text(),'Non Admin')]//parent::li//i")
	private WebElement nonAdmin;

	@FindBy(xpath = "//div[normalize-space()='Lead_System' and contains(@data-autoid,'layoutname_')]//ancestor::div[contains(@id,'quickView_')]//a[contains(text(),'Administrator')]//parent::li//i")
	private WebElement Admin;

	@FindBy(xpath = "//div[normalize-space()='Lead_System' and contains(@data-autoid,'layoutname_')]//ancestor::div[contains(@id,'quickView_')]//a[contains(text(),'Administrator')]//parent::li//a[contains(@data-autoid,'iconsummary')]")
	private WebElement adminSumamry;

	@FindBy(xpath = "//div[normalize-space()='Lead_System' and contains(@data-autoid,'layoutname_')]//ancestor::div[contains(@id,'quickView_')]//a[contains(text(),'Non Admin')]//parent::li//a[contains(@data-autoid,'iconsummary')]")
	private WebElement nonadminSumamry;

	@FindBy(xpath = "//a[@data-autoid='autoid_link1']")
	private WebElement editNew;

	@FindBy(xpath = "//a[@data-autoid='autoid_link2']")
	private WebElement detail;

	@FindBy(xpath = "//a[@data-autoid='header_userbtn']")
	private WebElement HeaderIcon;

	@FindBy(xpath = "//a[@data-autoid='Logout_link']")
	private WebElement logOut;

	@FindBy(xpath = "//span[text() = 'More Details']")
	private WebElement moreDetails;

	@FindBy(xpath = "//span[text() = 'remove-customization']")
	private WebElement removeCustomization;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[9]")
	private WebElement dropLocationFor_AmountWithRange_Element;

	@FindBy(xpath = "//input[@value='Ok']")
	private WebElement ok;

	@FindBy(xpath = "//a[@data-autoid='custhomepagelink']")
	private WebElement customizeHomePage;

	@FindBy(xpath = "//span[normalize-space()='Card']")
	private WebElement card;

	@FindBy(xpath = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	private WebElement searchBoxRole;

	@FindBy(xpath = "(//*[ local-name()='svg' and @name='icon-custom-menu'])[1]")
	private WebElement actionIcon;

	@FindBy(xpath = "(//span[normalize-space()='Web view'])[1]")
	private WebElement webView;

	@FindBy(xpath = "//span[normalize-space()='Create for Web']")
	private WebElement createForWeb;

	@FindBy(xpath = "//div[@data-autoid='pagetitleheading' and text()='Layouts']")
	private WebElement pageTitleLeads;

	@FindBy(xpath = "//div[contains(text(), 'Layouts')]")
	private WebElement pageTitleLayouts;

	@FindBy(xpath = "//div[@data-autoid='0_AI']")
	private WebElement threeDots;

	@FindBy(xpath = "(//a[@data-autoid='button'])[1]")
	private WebElement card_oldDesigner;

	public void clickOnThreeDots() throws InterruptedException {
		ReUsableMethods.webClickElement(threeDots, "threeDots");
		Thread.sleep(2000);
	}

	public void searchElementOnLayoutDesigner(String elementName) {
		ReUsableMethods.webEnterText(layoutDesignerSearch, elementName, "Search Box");
	}

	public void clickOnBackButton() throws InterruptedException {
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(backButton_layoutDesigner, "Back Button");
		Thread.sleep(2000);
	}

	public void clickOnSaveButton_onPopup() throws InterruptedException {

		ReUsableMethods.webClickElement(saveButton_OnPopup, "Save Button_onPopup");
		Thread.sleep(2000);
	}

	public void dragAndDropSearchedItem_toLayout(String element) throws InterruptedException, AWTException {

		ReUsableMethods.scrollSlider_ByCount(20);

		Thread.sleep(3000);

		String baseXpath = "//label[normalize-space()='elementName']";
		String searchElementXpath = baseXpath.replaceAll("elementName", element);
		ReUsableMethods.scrollDownToElement(dropLocationFor_AmountWithRange_Element);
		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)),
				dropLocation_belowLeadAmount);

		Thread.sleep(3000);
	}

	public void clickOnNonAdminLead() {
		ReUsableMethods.webClickElement(nonAdmin, "click on non admin lead");
		ReUsableMethods.webClickElement(nonadminSumamry, "Admin Summary");
	}

	public void clickOnAdminLead() {
		ReUsableMethods.webClickElement(Admin, "click on admin lead");
		ReUsableMethods.webClickElement(adminSumamry, "Admin Summary");
	}

	public void clickOnNewEdit() {
		ReUsableMethods.webClickElement(editNew, "click on new edit button");
	}

	public void clickOndetailIcon() {
		ReUsableMethods.webClickElement(detail, "click on detail icon");
	}

	public void clickOnheaderUserIcon() {
		ReUsableMethods.webClickElement(HeaderIcon, "click on header");

	}

	// Logout not to be supposed here to be removed
	public void clickOnLogOut() {
		ReUsableMethods.webClickElement(logOut, "click on logout");

	}

	public void hoverOverMoreDetails() {
		ReUsableMethods.webMoveToElement(moreDetails, "more details icon");
	}

	public void clickOnRemoveCustomization() {
		hoverOverMoreDetails();
		ReUsableMethods.webClickElement(removeCustomization, "remove customization");

	}

	public void dragAndDrop_AmountWithRangeElement(String element) throws InterruptedException {
		ReUsableMethods.scrollSlider_ByCount(5);
		String baseXpath = "//div[@title='ElementName']";
		String searchElementXpath = baseXpath.replaceAll("ElementName", element);
		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)),
				dropLocationFor_AmountWithRange_Element);
	}

	public void clickOnOk() {
		ReUsableMethods.webClickElement(ok, "Ok");

	}

	public void clickOnCustmizeHomePage() {
		ReUsableMethods.webClickElement(customizeHomePage, "Home Page");

	}

	public void clickOnCard() throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webClickElement(card, "card");
	}

	public void searchRoleName(String elementName) throws InterruptedException {
		ReUsableMethods.webEnterText(searchBoxRole, elementName, "Search Box for Role Name");
		Thread.sleep(2000);
	}

	public void hoverOverActionIcon() {
		ReUsableMethods.webClickElement(actionIcon, "Action Icon");
	}

	public void clickOnWebView() throws InterruptedException {
		hoverOverActionIcon();
		ReUsableMethods.webClickElement(webView, "Web View");
		Thread.sleep(2000);
	}

	public void clickOnCreateForWeb() throws InterruptedException {
		try {
			hoverOverActionIcon();
			ReUsableMethods.webClickElement(createForWeb, "Web View");
			Thread.sleep(2000);
		} catch (Exception e) {
			ReUsableMethods.webClickElement(createForWeb, "Web View");
			Thread.sleep(2000);
		}

	}

	public boolean isPageTitleLeads() throws InterruptedException {
		Thread.sleep(1000);
		return ReUsableMethods.WebIsElementDisplayed(pageTitleLeads, "Leads Page Title");
	}

	public boolean isPageTitleLayouts() throws InterruptedException {
		Thread.sleep(1000);
		return ReUsableMethods.WebIsElementDisplayed(pageTitleLayouts, "Layouts Page Title");
	}

	public void clickOnCard_OldDesigner() throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webClickElement(card_oldDesigner, "card");
	}

}
