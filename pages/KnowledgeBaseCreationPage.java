package com.businessnext.knowledgebase.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.common.pages.CommonProductFunctions;
import com.reports.ExtentLogger;
import com.utilities.ReUsableMethods;
import com.utilities.WebWait;

public class KnowledgeBaseCreationPage {

	WebDriver wdriver;

	public KnowledgeBaseCreationPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@data-autoid='TITLE_ctrl']")
	public WebElement tittleText;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='Save']")
	public WebElement saveIcon;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='CATEGORYID_srch']")
	public WebElement searchPicker;

	@FindBy(how = How.XPATH, using = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	public WebElement searchTextBox;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='gridHF_CATEGORYID']")
	public WebElement applyIcon;

	@FindBy(how = How.XPATH, using = "//div[@data-autoid='Subject_0']")
	public WebElement firstRecord_AfterSearchCategory;

	@FindBy(how = How.XPATH, using = "//div[@role='textbox']")
	public WebElement contentBody;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//textarea[@data-autoid='SUMMARY_ctrl']")
	public WebElement description;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='ExpertOwnerID_Knowledge_srch']")
	public WebElement searchPicker_expertField;

	@FindBy(how = How.XPATH, using = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	public WebElement enterUserName_expert;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Apply')]")
	public WebElement applyButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@data-autoid,'Name_0')]")
	public WebElement firstUserName;

	@FindBy(how = How.XPATH, using = "//input[@title='DD-MM-YY' or @title='DD/MM/YY']")
	public WebElement expiredOn;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='TAGID_srch']")
	public WebElement searchPicker_tag;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='gridHF_TAGID']")
	public WebElement applyButton_tag;

	@FindBy(how = How.XPATH, using = "//div[@data-autoid='checkbox_input_row_index_0']")
	public WebElement checkbox_tag;

	@FindBy(xpath = "//a[@data-autoid='picker_ok']")
	public WebElement okButton;

	@FindBy(xpath = "//div[@data-autoid='Pck_SearchDropDown_ctrl']")
	public WebElement searchDropDown;

	@FindBy(xpath = "//span[@role='option']//span")
	public List<WebElement> dropdownOptions;
	
	@FindBy(xpath = "//input[@data-autoid='TAGID_ctrl']")
	public WebElement keyboardTags;
	
	public String keywordTag_pre = "//*[normalize-space(text())='";
	public String keywordTag_post = "']";
	
	@FindBy(xpath = "//span[normalize-space(text())='Poppins']")
	public WebElement poppins;
	
	@FindBy(xpath = "//span[@title='Key Information']")
	public WebElement keyInformation;
	
	@FindBy(xpath = "//a[@data-autoid='RELATEDARTICLES_srch']")
	public WebElement articleSearchPicker;
	
	@FindBy(how = How.XPATH, using = "//input[@data-autoid='checkbox_input_row_index_0']")
	public WebElement checkbox;
	
	
	public String enterKeywordTags() {
		String tags= "Tag"+ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(keyboardTags, tags, "tag value");
		ReUsableMethods.webClickElement(tittleText, "tittle text ");
		return tags;
	}
	
	public boolean verifyIsKeywordAndTagVisible(String tags) {
		String actualXpath = keywordTag_pre + tags + keywordTag_post;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		return ReUsableMethods.WebIsElementDisplayed(element.get(0), "Keyword and tag");
	}


	public boolean verifyIsExpiredDateFieldDisabled() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(expiredOn, "mentioned expired On date");
	}

	public void createCategory(String categoryname) {

		KnowledgeBaseHomePage knowledgeBaseHomePage = new KnowledgeBaseHomePage(wdriver);
		KnowledgeBaseCategoryHomePage knowledgeBaseCategoryHomePage = new KnowledgeBaseCategoryHomePage(wdriver);
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);

		ReUsableMethods.webClickElement(knowledgeBaseHomePage.manageCategory, "manage category icon");
		String categorypath = knowledgeBaseCategoryHomePage.createcategory_pre + categoryname
				+ knowledgeBaseCategoryHomePage.createcategory_post;
		if (ReUsableMethods.findElementByPath(categorypath).size() < 1) {
			ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.newCategory, "Icon home page");
			ReUsableMethods.webEnterText(knowledgeBaseCategoryHomePage.categoryNameField, categoryname,
					"Category text box");
			ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.saveIcon, "click on save button");
			commonProductFunctions.waitForLoader();
		} else {
			ExtentLogger.info("Category is already created with the name: " + categoryname);
		}

	}

	public void createknowledgeBase(String parentWindow, String categoryName, String knowledgeBaseName) {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);
		ReUsableMethods.webClickElement(searchPicker, "Search Picker");
		ReUsableMethods.webEnterText(searchTextBox, categoryName, "search category name");
		commonProductFunctions.waitForLoader();
		ReUsableMethods.webClickElement(applyIcon, "click on Aplly Button");
		ReUsableMethods.webClickElement(firstRecord_AfterSearchCategory, "knowledgeBaseCreationPage.applyIcon");
		ReUsableMethods.webEnterText(contentBody, "Knowledgebasebodycontent", "enter in content Body");
		ReUsableMethods.webEnterText(description, "Automation Knowledge Base", "Knowledgebase");

		description.sendKeys(Keys.TAB);
		try {
			Thread.sleep(1000);
		} catch (Exception ex) {

		}
		int currentWindowSize = ReUsableMethods.currentWindowSize();
		ReUsableMethods.webClickElement(saveIcon, "click on save button");

		if (currentWindowSize > 1) {
			ReUsableMethods.waitforWindowSize(currentWindowSize - 1);
		} else {

			commonProductFunctions.waitForLoader();
		}
		ReUsableMethods.switchToWindow(parentWindow);
		WebWait.waitForPageLoad();
		commonProductFunctions.waitForLoader();

	}

	/////////////////// new test cases///////////////////

	public void selectknowledgeBaseCategory_forNonUser(String parentWindow, String categoryName, String knowledgeBaseName) {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);
		ReUsableMethods.webClickElement(searchPicker, "Search Picker");
		ReUsableMethods.webEnterText(searchTextBox, categoryName, "search category name");
		commonProductFunctions.waitForLoader();
		ReUsableMethods.webClickElement(applyIcon, "apply Button");
	}

	public List<String> storeDropDownFields() {
	    ReUsableMethods.webClickElement(searchDropDown, "drop down");
		List<String> listOfString = ReUsableMethods.getListOfText_FromElementList(dropdownOptions);
		return listOfString;
	}

}
