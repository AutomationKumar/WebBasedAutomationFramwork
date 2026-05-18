package com.businessnext.objects.lead.pages;

import java.awt.AWTException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.pages.CommonProductFunctions;
import com.drivermanager.DriverManager;
import com.reports.ExtentLogger;
import com.utilities.Constants;
import com.utilities.ReUsableMethods;
import com.utilities.SikuliUtils;
import com.utilities.WebWait;

public class LeadHomePage {

	WebDriver wdriver;

	public LeadHomePage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//*[contains(@data-autoid,'OfferName_')]")
	public WebElement offerNameOnLeadView;

	@FindBy(xpath = "//input[contains(@data-autoid,'checkbox_input_row_index_')]")
	public WebElement viewCheckBox;

	@FindBy(xpath = "//a[@title='Update Home Filter']")
	public WebElement updateHomeFilter;

	@FindBy(xpath = "//input[@type='text' and @placeholder='Search Action']")
	private WebElement searchActionBox;

	@FindBy(xpath = "//div[@class='pr-4 text-primary font-14 ff-medium css-0']")
	public WebElement moreView;

	@FindBy(xpath = "(//a[@title='New']/following::div/a[@title='Clear'])[1]")
	public WebElement clearLink;

	@FindBy(xpath = "//*[@name='cross']")
	public WebElement crossIcon;

	@FindBy(xpath = "//a[@title='New']")
	public WebElement newStatusCode;

	@FindBy(xpath = "//*[@title='Change owner']")
	public WebElement changeOwnerButton;

	@FindBy(xpath = "//*[@title='Disqualify']")
	public WebElement disqualifyButton;

	@FindBy(xpath = "//*[@title='Mail Merge']")
	public WebElement mailMergeButton;

	@FindBy(xpath = "//*[@title='Mass Delete']")
	public WebElement massDeleteButton;

	@FindBy(xpath = "//div[@data-autoid='sidebar']")
	public WebElement sidebar;

	@FindBy(xpath = "(//a[@title='New']/following::div/a[@title='Add More'])[1]")
	public WebElement addMoreStatus;

	@FindBy(xpath = "//*[@data-autoid='LeadName_0']")
	public WebElement firstLeadAfterAdvanceSearch;

	@FindBy(xpath = "//div[@data-autoid='ToolBox_2']")
	public WebElement toolBox_Lead;

	@FindBy(xpath = "//*[@data-autoid='PCK_FIELD_AadharCardNumber_ctrl']")
	public WebElement aadharMapping;

	@FindBy(xpath = "//*[@data-autoid='Clear_1']")
	public WebElement clear;

	@FindBy(xpath = "//label[@data-autoid='CHK_ITEM_CNTRL_lbl']")
	public WebElement showItemListMassUpdateCheckBox;;

	@FindBy(xpath = "//a[@title='Lead_System']")
	public WebElement leadSystem_Layout;

	@FindBy(xpath = "//a[@title='Lead_Cards']")
	public WebElement lead_Cards_Layout;

	@FindBy(xpath = "//a[@title='Default']")

	public WebElement leadDefault_Layout;

	@FindBy(xpath = "//a[@title='View']/span")
	public WebElement view;

	@FindBy(xpath = "(//*[local-name()='svg' and @name='icon-custom-menu'])[1]")
	public WebElement FirstThreeDots;
	
	  @FindBy(xpath = "//div[contains(@class,'acid-main-table-container')]//div[@data-autoid='button' and @shape='square']")
	    public WebElement threeDots;

	@FindBy(xpath = "//div[contains(text(),'ToolBox')]")
	public WebElement toolbox;

	@FindBy(xpath = "//div[@data-autoid='PanNumber_0']")
	private WebElement panNumberListing;

	@FindBy(xpath = "//*[@data-autoid='APP_ADVANCE_SEARCH_link']")
	public WebElement advanceSeachLink;

	@FindBy(xpath = "(//*[@data-testid='table-row'])[1]//a[@title='Add More']")
	public WebElement addMoreNew;

	@FindBy(xpath = "(//*[@data-testid='table-row'])[1]//a[@title='Clear']")
	public WebElement clearFirst;

	@FindBy(xpath = "//*[@data-autoid='ToolBox_2_19']")
	private WebElement manageProcessList;

	@FindBy(xpath = "//*[@data-autoid='ToolBox_2_15']")
	private WebElement manageCustomFields;

	@FindBy(xpath = "//*[@data-autoid='New_0']")
	public WebElement newIcon;

	@FindBy(xpath = "//div[@data-testid='dialog-body']//input[@data-testid='input']")
	public WebElement searchTextArea;

	@FindBy(xpath = "//div[@data-autoid='ph-root']")
	public WebElement viewHeader_layout;

	@FindBy(xpath = "//div[contains(@class,'switch-layout-box')]")
	public WebElement swiftLayoutUi;

	@FindBy(xpath = "//div[contains(@class,'acid-table__header')]")
	public WebElement viewHeader;

	@FindBy(xpath = "//*[@name='icon-layout']")
	public WebElement switchLayout;

	@FindBy(xpath = "//div[text()='Compact']")
	public WebElement compactView;

	@FindBy(xpath = "//div[text()='Default']")
	public WebElement defaultView;

	@FindBy(xpath = "//div[@data-testid='dialog-portal-rtl']//input[@data-testid='input']")
	public WebElement layoutSearchInput;

	@FindBy(xpath = "//a[@title='Layout_1']")
	private WebElement layout1;

	@FindBy(xpath = "//div[@data-autoid='search']")
	public WebElement searchIcon;
	
	@FindBy(xpath = "//div[@data-autoid='undefined_ctrl']")
	private WebElement searchDropDown;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchTextBox;

	@FindBy(xpath = "//input[@data-autoid='APP_SEARCH_ITEM_ctrl']")
	public WebElement advanceSearchtext;

	@FindBy(xpath = "//a[@data-autoid='APP_SEARCH_ITEM_ICON']")
	public WebElement advanceSearchIcon;

	@FindBy(xpath = "//div[@data-autoid='pagetitleheading']")
	public WebElement leadObjectName;

	@FindBy(xpath = "//*[@data-autoid='QueryFilterId_ctrl']")
	private WebElement viewFilter;

	@FindBy(xpath = "//*[@data-autoid='QueryCategoryId_ctrl']")
	private WebElement viewCategory;

	@FindBy(xpath = "//*[@data-autoid='QueryViewId_ctrl']")
	private WebElement viewDropdown;

	@FindBy(xpath = "//div[contains(text(),'No data exists')]")
	public WebElement noDataExits;

	@FindBy(xpath = "//a[@data-autoid='Lay_100091']")
	private WebElement currency_Pattern;

	@FindBy(xpath = "//a[@data-autoid='Lay_100116']")
	private WebElement currency_Management_Master;

	@FindBy(xpath = "//a[@title='UpdateTwoFilterType']")
	private WebElement UpdateTwoFilterType;

	@FindBy(xpath = "//input[@data-autoid='checkbox-input']")
	private WebElement checkBox;

	@FindBy(xpath = "//select[@name='Lookup1Text']")
	public WebElement selectControllingField;

	@FindBy(xpath = "//*[@name='icon-pulse']")
	public WebElement pulseIcon;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-chevron-down']")
	private WebElement moreIcon;

	@FindBy(xpath = "(//div[@data-autoid='checkbox'])[3]")
	private WebElement secondCheckBox;

	@FindBy(xpath = "//button[normalize-space()='Ok']")
	private WebElement okIcon;

	@FindBy(xpath = "//a[@data-autoid='picker_ok']")
	private WebElement okPicker;

	@FindBy(xpath = "//a[@data-autoid='ToolBox_2_14']")
	private WebElement managecustomActionButton;

	@FindBy(xpath = "//input[@data-autoid='APP_SEARCH_ITEM_ctrl']")
	private WebElement advanceSearchText;

	@FindBy(xpath = "//a[@data-autoid='APP_SEARCH_ITEM_ICON']")
	private WebElement advanceSearchButton;

	@FindBy(xpath = "//span[normalize-space()='Approval Button']")
	private WebElement approvalActionButton;

	@FindBy(xpath = "//button[@data-autoid='0_button']")
	private WebElement updateActionButton;

	@FindBy(xpath = "//button[@data-autoid='Update_0']")
	private WebElement updateAction_Icon;

	@FindBy(xpath = "//input[@data-autoid='LE_ADDRESS_ctrl']")
	private WebElement adrressClear;

	@FindBy(xpath = "//a[@title='Manage Custom Action Button']")
	private WebElement currentStatus;

	@FindBy(xpath = "//*[contains(@data-autoid,'LastName_')]")
	public WebElement firstLead;

	@FindBy(xpath = "//*[@data-autoid='LeadName_1']")
	public WebElement secondLead;

	@FindBy(xpath = "//a[@data-autoid='0_Field1_val']")
	public WebElement firstLeadValue;

	@FindBy(xpath = "//a[@data-autoid='ToolBox_2_21']")
	private WebElement manageQueues;

	@FindBy(xpath = "//label[@title='Address Should Be Updated']")
	private WebElement getAlternativeLabelFormatting;

	@FindBy(xpath = "//label[@title='Fill Your Amount']")
	private WebElement getAlternativeLabel;

	@FindBy(xpath = "//div[@data-autoid='pagesize_listing']")
	private WebElement getDisplaySize;

	@FindBy(xpath = "//a[@data-autoid='ToolBox_2_3']")
	private WebElement customizedLayout;

	@FindBy(xpath = "(//div[@data-autoid='undefined_AI'])[7]")
	private WebElement hoverOverAdmin_Social;

	@FindBy(xpath = "//span[text()='Create for Web']//parent::a")
	private WebElement webView;

	@FindBy(xpath = "//a[@controller='HomePageDesigner']")
	private WebElement socialCard_Twitter;

	@FindBy(xpath = "//div[@data-autoid='QueryViewId_ctrl']")
	public WebElement selectBox_View;

	@FindBy(xpath = "//select[@data-autoid='QueryFilterId_ctrl']")
	private WebElement selectBox_Filter;

	@FindBy(xpath = "(//div[contains(@data-autoid,'VIVID_')])[1]")
	private WebElement chartElementOnCard;

	@FindBy(xpath = "//a[@data-autoid='LINK_MANAGEVIEWView00']")
	private WebElement manageView;

	@FindBy(xpath = "//a[@title='Import Data']")
	public WebElement importData;

	@FindBy(xpath = "//span[normalize-space()='Mass Update']")
	private WebElement massUpdateOnToolbox;

	@FindBy(xpath = "//span[normalize-space()='Mass Delete']")
	private WebElement massDeleteOnToolbox;

	@FindBy(xpath = "//*[normalize-space()='Mass Update']//div")
	private WebElement massUpdate;

	@FindBy(xpath = "//*[@title='Mass Delete']")
	private WebElement massDelete;

	@FindBy(xpath = "//div[@data-testid='alert-title']//div[contains(normalize-space(.),'Record Created Successfully')]")
	public  WebElement toastMessage;

	@FindBy(xpath = "//a[normalize-space()='Mass Update' and contains(@class, 'disabled')]")
	private WebElement disabledMassUpdateButton;

	// @FindBy(xpath = "//a[normalize-space()='Mass Delete' and contains(@class,
	// 'disabled')]")
	// private WebElement disabledMassDeleteButton;

	@FindBy(xpath = "//label[@data-autoid='checkbox_input_row_index_0']")
	public WebElement firstSearchCheckbox;

	@FindBy(xpath = "//input[@data-autoid='checkbox-input']")
	public WebElement allSelectCheckbox;

	@FindBy(xpath = "//a[@data-autoid='Remove']")
	public WebElement removeMapping;

	@FindBy(xpath = "//button[@data-autoid='0_button']")
	public WebElement okMassUpdate;

	@FindBy(xpath = "//*[@data-autoid='MassDelete_0']")
	private WebElement Delete;

	@FindBy(xpath = "(//a[contains(@data-autoid,'LeadName_')])[1]")
	private WebElement firstRecordAfterSeach;

	@FindBy(xpath = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	public WebElement textBox;

	@FindBy(xpath = "//*[contains(text(),'Graph Filter')]")
	private WebElement graphFilterConfiguration;

	@FindBy(css = "div[data-autoid='QueryFilterId_ctrl']")
	private WebElement selectBox;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-chevron-down']")
	private WebElement moreIconButton;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-david-master']")
	private WebElement actionIconButton;

	@FindBy(xpath = "//input[@placeholder='Search Action']")
	private WebElement searchBox_moreActionButton;

	@FindBy(xpath = "//input[@placeholder='Type here']")
	private WebElement viewSearchBox;

	@FindBy(xpath = "(//span[@title='LeadViews'])[1]")
	private WebElement leadViewsClick;

	@FindBy(xpath = "//span[@tabindex='-1']")
	private WebElement firstFilterClick;

	@FindBy(xpath = "//span[@title='Today']")
	private WebElement todayFilterClick;

	@FindBy(xpath = "//a[contains(@data-autoid,'LNK_NEWMAPPING')]")
	public WebElement newMapping;

	@FindBy(xpath = "//a[@data-autoid='TextPicker1Id_del']")
	public WebElement deleteMapping;

	@FindBy(xpath = "//a[contains(@data-autoid,'TextPicker')]")
	public WebElement searchPicker;

	@FindBy(xpath = "//a[@data-autoid='Lay_100275']")
	public WebElement advanceEncryptionLayout;

	@FindBy(xpath = "//span[contains(text(),'Manage Conditional Access')]")
	public WebElement manageConditionalAccess;

	@FindBy(xpath = "//span[contains(text(), '.com')]")
	public WebElement advanceEncryptionEmailPlainText_Listing;

	@FindBy(xpath = "//div[@data-autoid='Lea_ex2_66_0' and contains(text(), 'text')]")
	public WebElement advanceEncryptionTextPlainText_Listing;

	@FindBy(xpath = "//div[@data-autoid='Lea_ex2_66_0' and contains(text(), '********')]")
	public WebElement advanceEncryptionTextEncrypt_Listing;

	@FindBy(xpath = "//a[@title='********']")
	public WebElement advanceEncryptionEmailEncrypt_Listing;

	@FindBy(xpath = "//a[@title='Manage Field Option Mapping']")
	public WebElement fieldOptionMapping;

	@FindBy(xpath = " //div[contains(text(),'Name is required')]")
	public WebElement nameRequired;

	@FindBy(xpath = "//div[contains(@data-autoid, 'Name_') and contains(text(), 'Gold Loan')]")
	public WebElement goldLoanNameOptionPicker;

	@FindBy(xpath = "//div[contains(@data-autoid, 'Name_') and contains(text(), 'Home Loan')]")
	public WebElement homeLoanNameOptionPicker;

	@FindBy(xpath = "//a[@title='New']")
	public WebElement newFieldOptionMapping;

	@FindBy(xpath = "//*[@title='icon-tick green']")
	public WebElement unRead;

	@FindBy(xpath = "//a[@data-autoid='DisplayName_0']")
	public WebElement firstFieldOptionMapping;

	@FindBy(xpath = "//*[@data-autoid='Product_0']")
	public WebElement productOnListing;

	@FindBy(xpath = " //div[contains(@data-autoid, 'ResourceId') and contains(text(), 'PRODUCT_STATUSCODE')]")
	public WebElement resourceIDStatusCodeListing;

	@FindBy(xpath = " //div[contains(@data-autoid, 'ResourceId') and contains(text(), 'Auto Mapping')]")
	public WebElement resourceIDAutoMappingListing;

	public String xpathCategory = "//div[contains(@data-autoid,'Name_')]";

	String preStatusCode = "//div[contains(@data-autoid, 'ResourceId') and contains(text(), '";
	String postStatusCode = "')]";

	@FindBy(xpath = "//div[@data-testid='dialog-body']//following::input[@data-testid='input']")
	public WebElement searchArea;

	@FindBy(xpath = "(//a[contains(@data-autoid,'Lay_')])[1]")
	public WebElement clickOnSearchedLayout;

	@FindBy(xpath = "//div[contains(@class,'acid-main-table-container')]//div[@data-autoid='search']//*[local-name()='svg' and @name='icon-srsearch']")
    public WebElement searchButton;

	@FindBy(xpath = "//a[@title='Update Home Filter']")
	private WebElement actionButton;

	@FindBy(xpath = "//input[@data-autoid='LASTNAME_ctrl']")
	private WebElement leadvalueMassUpdate;

	public String massUpdateLeadFromHomePage(String Column, String Value) throws InterruptedException {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);
		commonProductFunctions.clickOnAllCheckbox();
		clickOnMassUpdateButton();
		clickOkButtonForMassUpdate();
		ReUsableMethods.webSelectByVisibleText(commonProductFunctions.columnMassUpdate, Column, "Select Column");
		String expectedMassUpdateTraining = ReUsableMethods.webEnterTextString(leadvalueMassUpdate, Value,
				"Value Subject");
		ReUsableMethods.webClickElement(commonProductFunctions.massUpdateConfirmation, "Mass Update Confirmation");

		ReUsableMethods.webClickElement(commonProductFunctions.ok_Button, "Ok");

		return expectedMassUpdateTraining;

	}

	public void clickONSearch() {

		if (ReUsableMethods.WebIsElementDisplayed(searchButton, "Search")) {
			ReUsableMethods.scrollElementToCentreOfScreen(searchButton);
			ReUsableMethods.webClickElement(searchButton, "SearchButton");

		}

	}

	public boolean checkIFStatusCodeExist(String statusCode) {
		String xpath = preStatusCode + statusCode + postStatusCode;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.isWebElementDisplayed(ele);

	}

	public boolean verifyAddMoreNew() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(addMoreNew, "Add More New");
	}

	public boolean verifyToastMessage() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(toastMessage, "Toast Message");
	}

	public boolean verifyNameRequired() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(nameRequired, "Name Required");
	}

	public boolean verifyResourceIDStatusCodeListing() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(resourceIDStatusCodeListing, "ResourceID Listing");
	}

	public boolean verifyResourceIDAutoMappingListing() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(resourceIDAutoMappingListing, "ResourceID Listing");
	}

	public boolean verifyGoldLoanNameOptionPicker() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(goldLoanNameOptionPicker, "Gold Loan");
	}

	public boolean verifyHomeLoanNameOptionPicker() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(homeLoanNameOptionPicker, "Home Loan");
	}

	public boolean verifyAdvanceEncryptionTextPlainText_Listing() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionTextPlainText_Listing,
				"Advance Encryption Text Listing");
	}

	public boolean verifyPanNumberListing() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(panNumberListing, "Advance Encryption Pan Number Listing");
	}

	public boolean verifyAdvanceEncryptionTextEncrypt_Listing() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionTextEncrypt_Listing, "Text Encrypt Listing");
	}

	public void clickManageConditionalAccess() {
		ReUsableMethods.webClickElement(manageConditionalAccess, "Click on Manage Conditional Access");
	}

	public boolean verifyAdvanceEncryptionEmailEncrypt_Listing() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionEmailEncrypt_Listing, "Email Encrypt Listing");
	}

	public boolean verifyNoDataExits() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(noDataExits, "No Data Exits");
	}

	public boolean verifySearchPicker() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(searchPicker, "Search Picker");
	}

	public boolean verifyAdvanceEncryptionEmailPlainText_Listing() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionEmailPlainText_Listing,
				"Advance Encryption Email Listing");
	}

	@FindBy(xpath = "//input[@title='File Input']")
	private WebElement uploadFile;

	public void hoverOverAdmin_Social() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.webMoveToElement(hoverOverAdmin_Social, "New Button");
	}

	public void clickOnFirstCheckbox() throws InterruptedException {

		ReUsableMethods.webMoveToElement(firstSearchCheckbox, "FirstCheckbox");

		// ReUsableMethods.webClickElement(firstSearchCheckbox, "CheckBox");
		ReUsableMethods.webClickJavaScriptExecutor(firstSearchCheckbox);

	}

	public void viewSearchField(String fieldName) throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.webMoveToElement(applyButton, "applyButton");
		ReUsableMethods.webClickElement(viewDropdown, "View DropDown");
		ReUsableMethods.webEnterText(viewSearchBox, fieldName, "Field Name");
		ReUsableMethods.webClickElement(leadViewsClick, "LeadViews");

	}

	public void selectAllViewFilter(String fieldName) throws InterruptedException {
		ReUsableMethods.scrollElementToCentreOfScreen(viewFilter);
		ReUsableMethods.webClickElement(viewFilter, "click on filter");
		ReUsableMethods.webEnterText(viewSearchBox, fieldName, "Field Name");
		ReUsableMethods.webClickElement(firstFilterClick, "FirstView");
		ReUsableMethods.scrollByPixels(200);
		// ReUsableMethods.webSelectByVisibleText_Swift(viewFilter, parameter, "View
		// Filter");

	}

	public void todayFilterClick(String fieldName) throws InterruptedException {
		ReUsableMethods.webMoveToElement(viewFilter, fieldName);
		// ReUsableMethods.webClickElement(viewFilter, "click on filter");
		// ReUsableMethods.webEnterText(viewSearchBox, fieldName, "Field Name");

		ReUsableMethods.safeClick(viewFilter, viewSearchBox);
		ReUsableMethods.webEnterText(viewSearchBox, fieldName, "Field Name");
		ReUsableMethods.webClickElement(todayFilterClick, "TodayViews");
		ReUsableMethods.scrollByPixels(200);
	}

	public String enterTextBoxInSearch(String role) throws InterruptedException {

		ReUsableMethods.webEnterText(textBox, role, "enter in text box");
		// Thread.sleep(500);
		return role;
	}

	public void clickOnCreateForWeb() throws InterruptedException {
		ReUsableMethods.webClickElement(webView, "web view");

	}

	public void clickAdvanceSeachLink() throws InterruptedException {
		ReUsableMethods.webClickElement(advanceSeachLink, "Advance Seach Link");

	}

	public void clickFieldOptionMapping() throws InterruptedException {
		ReUsableMethods.webClickElement(fieldOptionMapping, "Manage Field Option Mapping");

	}

	public void hoverOverNewButton() throws InterruptedException {

		ReUsableMethods.webClickElement(newIcon, "New Button");

	}

	public void clickFirstLeadValue() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.webClickElement(firstLeadValue, "firstLeadValue");
		// Thread.sleep(1000);
	}

	/// for clicking new button swift
	public void clickOnNewButton() throws InterruptedException {
		try {
			ReUsableMethods.scrolltoTop();
			ReUsableMethods.webClickElement(newIcon, "New Button");
		} catch (Exception ex) {

		}
	}

	public void clickOnLayout(String layout) throws InterruptedException {

		String basicXpath = "//div[@data-testid='dialog-container']//a[@title='LayoutModel']";
		String actualxpathLayout = basicXpath.replace("LayoutModel", layout);
		List<WebElement> element = ReUsableMethods.findElementByPath(actualxpathLayout);
		if (element.size() > 0) {
			ReUsableMethods.webClickElement(ReUsableMethods.findElementByPath(actualxpathLayout).get(0), layout);
		} else {
			String actualxpathLayout1 = basicXpath.replace("LayoutModel", "Lead_System");
			ReUsableMethods.webClickElement(ReUsableMethods.findElementByPath(actualxpathLayout1).get(0),
					"Lead_System");
		}
	}

	public void clickOnToolBox() throws InterruptedException, AWTException {

		ReUsableMethods.scrolltoTop();
		ReUsableMethods.webClickElement(toolBox_Lead, "Tool Box");

	}

	@FindBy(xpath = "//div[@data-autoid='QueryViewId_ctrl']//div[contains(text(),'Tile View')]")
	private WebElement checktileviewElement;

	@FindBy(xpath = "//div[@role='list']//span[contains(text(),'Tile View')]")
	private WebElement listViewSelection;

	public void clickOnManageProcessList() throws InterruptedException {

		// ReUsableMethods.scrollDownToElement(manageProcessList);
		ReUsableMethods.webClickElement(manageProcessList, "manage Process List");
		if (ReUsableMethods.WebIsElementDisplayed(checktileviewElement, "Tile View")) {
			ReUsableMethods.webClickElement(viewDropdown, "View DropDown");
			ReUsableMethods.webClickElement(listViewSelection, "List View");
		}
	}

	public void clickOnManageCustomField() throws InterruptedException {

		ReUsableMethods.webClickElement(manageCustomFields, "Manage Custom Field");

	}

	public void ClickOnSearchIcon() throws InterruptedException {
		ReUsableMethods.scrollElementToCentreOfScreen(applyButton);
		// ReUsableMethods.scrollByPixels(500);
		// ReUsableMethods.scrollDownToElement(searchIcon);
		ReUsableMethods.webClickElement(searchIcon, "Search icon");
		try {
			ReUsableMethods.webSelectByVisibleText_Swift_SearchDown(viewFilter, "Recently Created", "view filter");
		} catch (Exception ex) {

		}

	}

	public String webSelectByVisibleText_Swift_Search(String name) throws InterruptedException {

		ReUsableMethods.webSelectByVisibleText_Swift_SearchDown(searchDropDown, name, "searchbyDropdown");

		String basecss = "(//span[normalize-space()='EVENT'])[2]";

		String actualCSS = basecss.replaceAll("EVENT", name);
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualCSS)), name);
		return name;
	}

	@FindBy(xpath = "(//div[@data-toggle='tooltip'])[1]")
	private WebElement searchRecord;

	@FindBy(xpath = "//input[@placeholder='Search']//following::*[local-name()='svg' and @name='icon-search']")
	private List<WebElement> leadViewsSearchIcon;

	public void enterOnSearch(String searchValue) throws InterruptedException {
		CommonProductFunctions cmp = new CommonProductFunctions(DriverManager.getWdriver());
		cmp.selectPaginationSize100();
		ReUsableMethods.webMoveToElement(searchTextBox, "searchTextBox");
		ReUsableMethods.webClearText(searchTextBox, "");
		ReUsableMethods.webEnterText(searchTextBox, searchValue, "enter on the serach");
		WebWait.fluentWaitForInvisibility(leadViewsSearchIcon.get(0));
		if (leadViewsSearchIcon.size() > 0) {
			ReUsableMethods.scrolltoTop();
			ReUsableMethods.scrollElementToCentreOfScreen(applyButton);
			ReUsableMethods.webClickElement(leadViewsSearchIcon.get(0), "Search icon");
		}
		cmp.checkSearchRecord(searchRecord, searchValue);

	}

	// public void enterOnSearchTextBoxSeacondRecord(String searchValue) {
	//
	// ReUsableMethods.webEnterText(searchTextBox, searchValue, "enter on the
	// serach");
	//
	// }
	public void enterOnAdvanceSearchTextBox(String searchValue) {
		ReUsableMethods.webEnterText(advanceSearchtext, searchValue, "enter on the serach");
	}

	public void clickOnAdvanceSearch() {

		ReUsableMethods.webClickElement(advanceSearchIcon, "advance search");
	}

	public boolean checkIfDataExist() {

		try {
			if (noDataExits.isDisplayed()) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.println("e");
			return true;
		}

	}

	public void clickOnCurrency_PatternLayout() throws InterruptedException {
		// Thread.sleep(2000);
		hoverOverNewButton();
		ReUsableMethods.webClickElement(currency_Pattern, "Currency pattern");

	}

	public void clickOnFirstLeadName() throws InterruptedException {
		ReUsableMethods.webClickElement(searchedLeadName, "First Name");

	}

	public void clickOnCurrency_Management_MasterLayout() throws InterruptedException {
		hoverOverNewButton();
		ReUsableMethods.webClickElement(currency_Management_Master, "currency management master");
		// Thread.sleep(2000);
	}

	public String selectViewCategory(String parameter) throws InterruptedException {
		ReUsableMethods.webSelectByVisibleText_Swift(viewCategory, parameter, "View category");
		return parameter;
	}

	public String selectView(String parameter) throws InterruptedException {
		ReUsableMethods.scrollElementToMiddle(viewDropdown);
		ReUsableMethods.webSelectByVisibleText_Swift(viewDropdown, parameter, "View");
		return parameter;
	}

	public String verifyLeadObjectName_Hindi() {
		return ReUsableMethods.WebGetElementText(leadObjectName, "Lead Object Name");

	}

	public void clickOnupdateTwoFilterTypeButton() {
		ReUsableMethods.scrollDownToElement(UpdateTwoFilterType);
		ReUsableMethods.webClickElement(UpdateTwoFilterType, "click on it");
	}

	@FindBy(xpath = "//div[@data-toggle='tooltip']")
	List<WebElement> listofLeads;

	public void clickOnCheckBox() throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.scrolltoTop();
		ReUsableMethods.scrollElementToCentreOfScreen(applyButton);
		ExtentLogger.info("Size of leads is: " + listofLeads.size());
		/*
		 * if (listofLeads.isEmpty()) {
		 * ReUsableMethods.webSelectByVisibleText_Swift_SearchDown(viewDropdown,
		 * "All New Leads", "view filter"); //
		 * ReUsableMethods.selectVisibleTextinSelectBox(chartElementOnCard, null, null);
		 * }
		 */

		ReUsableMethods.webClickJavaScriptExecutor(checkBox);
		// Thread.sleep(1000);
	}

	public void clickOnMoreIconForActionButton() throws InterruptedException {
		// Thread.sleep(1000);
		if (ReUsableMethods.WebIsElementDisplayed(moreIcon, "More Icon")) {
			ReUsableMethods.webClickElement(moreIcon, "click on check box");
		}
		// Thread.sleep(1000);
	}

	public void clickOnSecondRecordCheckBox() throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.webClickElement(secondCheckBox, "click on check box");
	}

	public void clickOnOkIcon() {
		if (ReUsableMethods.WebIsElementDisplayed(okIcon, "OK icon")) {
			ReUsableMethods.webClickElement(okIcon, "click on okk icpon");
		}
	}

	public void clickOnOkIcon_childWindow() {
		ReUsableMethods.webClickElement(okIcon, "click on okk icpon");
		ReUsableMethods.switchToChildWindowHandle();
	}

	public void clickOnOkPicker_childWindow() {
		ReUsableMethods.webClickElement(okPicker, "click on okk icpon");
		ReUsableMethods.switchToChildWindowHandle();
	}

	public String enterInAdvancedSearchText(String expectedName) throws InterruptedException {
		ReUsableMethods.waitUntilTextIsPresent(advanceSearchText, "advanceSearchButton");
		ReUsableMethods.webEnterText(advanceSearchText, expectedName, "enter name");
		return expectedName;
	}

	@FindBy(xpath = "//a[@title='Close']")
	private WebElement closeButton;

	public void clickOnAdvancedSearch() {
		ReUsableMethods.webClickElement(advanceSearchButton, "click on advance search");
		try {
			WebWait.fluentWaitForDisplayed(closeButton);
		} catch (Exception ex) {

		}

	}

	@FindBy(xpath = "//div[@data-testid='card-root']")
	private WebElement toolBoxSection;

	@FindBy(xpath = "(//div[@data-testid='card-root']//a)[1]")
	private WebElement firstToolBoxItem;

	public void clickOnCustomActionButton() throws InterruptedException {
		// Thread.sleep(2000);

		ReUsableMethods.webClickElement(managecustomActionButton, "click on custom action button");
		ReUsableMethods.switchToChildWindowHandle();

	}

	public void clickOnApprovalActionButton(String approvalRequest) throws InterruptedException {
		// Thread.sleep(1000);
		String basicXpath = "//span[normalize-space()='Approval']";
		String actualXpath = basicXpath.replaceAll("Approval", approvalRequest);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Custom Action button");
		// Thread.sleep(2000);
	}

	public void clickOnMassPrintActionButton(String massPrint) throws InterruptedException {
		// Thread.sleep(1000);
		String basicXpath = "//a[@title='Mass']";
		String actualXpath = basicXpath.replaceAll("Mass", massPrint);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Custom Action button");
		// Thread.sleep(2000);
	}

	public void clickOnIntegrationActionButton(String expectedIntegrationButtonName) throws InterruptedException {
		// Thread.sleep(1000);
		String basicXpath = "//span[normalize-space()='Integr']";
		String actualXpath = basicXpath.replaceAll("Integr", expectedIntegrationButtonName);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Custom Action button");
		ReUsableMethods.WebIsElementDisplayed(okIcon, "ok Icon");
	}

	public void clickOnImportActionButton(String expectedImportButtonName) throws InterruptedException {
		// Thread.sleep(1000);
		String basicXpath = "//span[normalize-space()='Import']";
		String actualXpath = basicXpath.replaceAll("Import", expectedImportButtonName);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Custom Action button");
		ReUsableMethods.switchToChildWindowHandle();
		// Thread.sleep(2000);
	}

	public void clickOnFileUploadActionButton(String expectedFileButtonName) throws InterruptedException {
		// Thread.sleep(1000);
		String basicXpath = "//span[normalize-space()='File']";
		String actualXpath = basicXpath.replaceAll("File", expectedFileButtonName);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Custom Action button");
		ReUsableMethods.switchToChildWindowHandle();
		// Thread.sleep(2000);
	}

	public void clickOnButton(String buttonTitle) throws InterruptedException {
		ReUsableMethods.webMoveToElement(closeButton, "Close button");
		String basicXpath = "//a[@title='BUTTONNAME']";
		String actualXpath = basicXpath.replace("BUTTONNAME", buttonTitle);

		System.out.println("Actual xpath is: " + actualXpath);
		Thread.sleep(2500);
		WebElement ele = ReUsableMethods.findElementByPath(actualXpath).get(0);

		ReUsableMethods.webClickElement(ele, buttonTitle);

	}

	public void clickOnUpdateActionButton() throws InterruptedException {

		ReUsableMethods.webClickElement(updateActionButton, "click on Ok for update action");
		if (ReUsableMethods.WebIsElementDisplayed(okIcon, "OK button")) {
			ReUsableMethods.webClickElement(okIcon, "Ok Button");
		}

	}

	public void clickOnUpdateAction_Icon() throws InterruptedException {

		ReUsableMethods.safeClick(updateAction_Icon, updateActionButton);

	}

	public void enterOnAddressrClear(String adrress) throws InterruptedException {
		ReUsableMethods.webClearText(adrressClear, "");
		ReUsableMethods.webEnterText(adrressClear, adrress, "enter in adddress field");
		// Thread.sleep(2000);
	}

	public boolean verifyCurrentStatus() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebIsElementDisplayed(currentStatus, "Progress Status");
	}

	public void clickOnCustomizePageLayouts() {
		ReUsableMethods.webClickElement(customizedLayout, "click on page layout");
	}

	public boolean verifyCurrentStatus_UpdateButton() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebIsElementDisplayed(currentStatus, "Progress Status");
	}

	public void clickOnFirstLeadRecord_HomePage(String LeadRecord) throws InterruptedException {
		// Thread.sleep(1000);
		String basicXpath = "(//a[@title='LEADNAMED'])[2]";
		String actualXpath = basicXpath.replaceAll("LEADNAMED", LeadRecord);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Custom Action button");
		// Thread.sleep(2000);
	}

	public boolean verifyIfButtonExist(String buttonParam) throws InterruptedException {
		// int a=0;
		Boolean result = false;
		// while(a<20 && result==false) {
		String baseXpath = "//a[@title='BUTTONNAME']";
		String actualXpath = baseXpath.replaceAll("BUTTONNAME", buttonParam);
		List<WebElement> elements = ReUsableMethods.findElementByPath(actualXpath);

		if (elements.size() > 0) {
			result = ReUsableMethods.WebIsElementDisplayed(elements.get(0), "Action Button");
		}
		/*
		 * if(result==true) { break;} } a++; try { Thread.sleep(4000);
		 * ReUsableMethods.refreshWebPage(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } clickOnMore_ActionIcon();
		 * enterSearchBox_MoreActionButtonh(buttonParam); }
		 */
		return result;

	}

	public boolean verifyIsButtonExist(String buttonParam) {

		try {
			String baseXpath = "//a[@title='BUTTONNAME']";
			String actualXpath = baseXpath.replaceAll("BUTTONNAME", buttonParam);
			WebElement element = wdriver.findElement(By.xpath(actualXpath));
			ReUsableMethods.scrollElementToMiddle(element);
			return ReUsableMethods.WebIsElementDisplayed(element, "Action Button");
		} catch (Exception e) {
			return false;
		}

	}

	public void clickOnReleaseActionButton_AfterSelectRecord() {
		ReUsableMethods.webClickElement(updateAction_Icon, "click on update action button");
		// ReUsableMethods.switchToChildWindowHandle();
	}

	public void clickOnManageQueues() {
		ReUsableMethods.webClickElement(manageQueues, "click on update action button");
		ReUsableMethods.switchToChildWindowHandle();
	}

	public boolean verifyCurrentStatus_AlternateLabelFormatting() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebIsElementDisplayed(getAlternativeLabelFormatting, "Progress Status");
	}

	public boolean verifyCurrentStatus_AlternateLabel() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebIsElementDisplayed(getAlternativeLabel, "Progress Status");
	}

	public boolean verifyCurrentStatusListDisplaySize_PersonalSettings() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollDownToElement(getDisplaySize);
		return ReUsableMethods.WebIsElementDisplayed(getDisplaySize, "Size");
	}

	public boolean verifySocialCard() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollDownToElement(socialCard_Twitter);
		return ReUsableMethods.WebIsElementDisplayed(socialCard_Twitter, "Size");
	}

	public String selectViewTypeFromSelectBox(String view) throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.scrollByPixels(500);

		ReUsableMethods.webSelectByVisibleText_Swift(selectBox_View, view, "view Icon");

		String basecss = "span[title='EVENT']";
		basecss.replaceAll("EVENT", view);

		return view;
	}

	// public String webSelectByVisibleText_Swift_LeadViews(String view) throws
	// InterruptedException {
	// //Thread.sleep(1000);
	// ReUsableMethods.scrollByPixels(500);
	//
	// //Thread.sleep(3000);
	//
	// ReUsableMethods.webSelectByVisibleText_Swift_LeadViews(selectBox_View, view,
	// "view Icon");
	// //ReUsableMethods.webClickElement(selectBox_View, "Select Box View");
	// String basecss = "span[title='EVENT']";
	// String actualCSS = basecss.replaceAll("EVENT", view);
	//
	//// WebElement w = wdriver.findElement(By.cssSelector(actualCSS));
	//// ReUsableMethods.scrollDownToElement(w);
	//// ReUsableMethods.webClickElement(wdriver.findElement(By.cssSelector(actualCSS)),
	// view);
	// return view;
	// }
	public String selectViewTypeFromSelectBox1(String view) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(wdriver, Duration.ofSeconds(10));

		// Scroll the select box view element into view
		ReUsableMethods.scrollByPixels(500);

		// Wait until the select box is clickable

		// Select the view from the dropdown
		ReUsableMethods.webSelectByVisibleText_Swift(selectBox_View, view, "view Icon");

		String basecss = "span[title='EVENT']";
		String actualCSS = basecss.replaceAll("EVENT", view);

		// Find the element using the modified CSS selector
		WebElement element = wdriver.findElement(By.cssSelector(actualCSS));

		// Scroll the element into view and wait until it's clickable
		ReUsableMethods.scrollByPixels(500);
		wait.until(ExpectedConditions.elementToBeClickable(selectBox_View));

		// Attempt to click the element, handle overlapping elements
		try {
			ReUsableMethods.webClickElement(element, view);
		} catch (ElementClickInterceptedException e) {
			// Retry clicking after ensuring the interfering element is handled
			try {
				WebElement overlappingElement = wdriver.findElement(By.cssSelector(actualCSS));
				((JavascriptExecutor) wdriver).executeScript("arguments[0].style.visibility='hidden'",
						overlappingElement);
				element.click();
			} catch (Exception ex) {
				throw new RuntimeException("Could not click the element due to overlapping element.", ex);
			}
		}

		return view;
	}

	public String selectFilterTypeFromSelectBox(String filter) throws InterruptedException {
		// Thread.sleep(3000);
		ReUsableMethods.webClickElement(selectBox, "Select Box View");
		String basecss = "//span[@title='EVENT']";
		String actualCSS = basecss.replace("EVENT", filter);

		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualCSS)), filter);
		return filter;
	}

	public void getExpectedScreenshotOfChart(String chartName) throws InterruptedException {
		// Thread.sleep(3000);
		ReUsableMethods.scrollDownToElement(chartElementOnCard);
		SikuliUtils.webCaptureElement(chartElementOnCard, Constants.FOLDER_IMAGE_REPOSITORY, chartName);
	}

	public void getActualScreenshotOfChart(String chartName) throws InterruptedException {
		// Thread.sleep(3000);
		ReUsableMethods.scrollDownToElement(chartElementOnCard);
		SikuliUtils.webCaptureElement(chartElementOnCard, Constants.FOLDER_CAPTURED_IMAGES, chartName);
	}

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-filter']")
	private WebElement filter;

	public void clickOnManageView() throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.safeClick(manageView, filter);
		// ReUsableMethods.switchToChildWindowHandle();
		// WebWait.fluentWaitForDisplayed(filter);
	}

	public boolean verifyMassUpdateButtonOnToolbox() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebIsElementDisplayed(massUpdateOnToolbox, "Mass Update");
	}

	public boolean verifyMassDeleteButtonOnToolbox() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebIsElementDisplayed(massDeleteOnToolbox, "Mass Delete");
	}

	public void clickOnMassUpdateOnToolbox() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.webClickElement(massUpdateOnToolbox, "Mass Update");
		// Thread.sleep(1000);
	}

	public boolean isMassUpdateButtonDisabled() throws InterruptedException {
		Thread.sleep(1000);
		try {
			ReUsableMethods.WebIsElementDisplayed(massUpdate, "Disabled Mass Update");
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public boolean isMassUpdateButtonEnabled() throws InterruptedException {

		return ReUsableMethods.WebIsElementDisplayed(massUpdate, "Disabled Mass Update");

	}

	public boolean isMassDeleteButtonPresent() throws InterruptedException {
		// Thread.sleep(1000);
		try {
			ReUsableMethods.WebIsElementDisplayed(massDelete, "Mass Delete");
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public void clickOnMassUpdateButton() throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.webClickElement(massUpdate, "Mass Update");
		// Thread.sleep(1000);
	}

	public void clickOnMassDeleteButton() throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.webClickElement(massDelete, "Mass Delete");
		// Thread.sleep(1000);
	}

	public void clickOkButtonForMassUpdate() throws InterruptedException {
		ReUsableMethods.webClickElement(okMassUpdate, "Ok Button");
		// Thread.sleep(2000);
	}

	public void clickOkButtonForMassDelete() throws InterruptedException {
		ReUsableMethods.webClickElement(okMassUpdate, "Ok Button");
		// Thread.sleep(2000);
	}

	public void clickDelete() throws InterruptedException {
		ReUsableMethods.webClickElement(Delete, "Delete Button");
		// Thread.sleep(2000);
	}

	public boolean isgraphFilterDisabled() throws InterruptedException {
		// Thread.sleep(1000);
		return ReUsableMethods.WebIsElementDisplayed(graphFilterConfiguration, "Disabled Mass Update");
	}

	public void clickOnFirstRecordAfterSearch() throws InterruptedException {
		// Thread.sleep(1000);
		ReUsableMethods.webMoveToElement(firstRecordAfterSeach, "Search record");
		ReUsableMethods.webClickElement(firstRecordAfterSeach, "First Lead Record");
	}

	public void clickOnGraphFilter() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.webClickElement(graphFilterConfiguration, "filter box");
		ReUsableMethods.switchToChildWindowHandle();
		// Thread.sleep(1000);
	}

	public void clickOnMoreIcon_ActionButton() throws InterruptedException {
		// Thread.sleep(2000);
		// ReUsableMethods.scrollByPixels(-200);
		/*
		 * ReUsableMethods.scrolltoTop();
		 * ReUsableMethods.webMoveToElement(moreIconButton, "More Action Button");
		 */
		ReUsableMethods.safeClick(moreIconButton, searchBox_moreActionButton);
	}

	public void clickOnMore_ActionIcon() throws InterruptedException {
		// Thread.sleep(2000);
		//ReUsableMethods.scrolltoTop();
		ReUsableMethods.scrollElementToCentreOfScreen(actionIconButton);
		ReUsableMethods.safeClick(actionIconButton, searchBox_moreActionButton);
	}

	public void enterSearchBox_MoreActionButtonh(String button) throws InterruptedException {
		// if (ReUsableMethods.WebIsElementDisplayed(searchBox_moreActionButton, null))
		// {
		// ReUsableMethods.webMoveToElement(applyButton, "Apply Button");
		ReUsableMethods.webEnterText(searchBox_moreActionButton, button, "enter in text box");
		// }

	}

	public boolean isValuePresentInViewDropDown(String value) {
		// Locate the element by its name or any other suitable locator
		WebElement element = wdriver.findElement(By.name("QueryCategoryId"));

		// Get the text content of the element
		String elementText = ReUsableMethods.WebGetElementText(element, "QueryCategoryID");

		// Check if the value is present in the element text
		return elementText.contains(value);
	}

	public boolean isValuePresentInLeadFilter(String value) {
		// Locate the element by its name or any other suitable locator
		WebElement element = wdriver.findElement(By.name("QueryFilterId"));

		// Get the text content of the element
		String elementText = ReUsableMethods.WebGetElementText(element, "Query Filter ID");

		// Check if the value is present in the element text
		return elementText.contains(value);
	}

	// @FindBy(xpath="//a[contains(@data-autoid, 'gridHF_')]")

	@FindBy(xpath = "//*[contains(@data-autoid,'gridHF')]//*[text()='Apply']")
	public WebElement applyButton;

	public String clickOnSearchByDropDown(String name) throws InterruptedException {

		ReUsableMethods.webSelectByVisibleText_Swift_SearchDown(searchDropDown, name, "searchbyDropdown");
		return name;
	}

	public String selectViewFilter(String parameter) throws InterruptedException {
		ReUsableMethods.webSelectByVisibleText_Swift(viewFilter, parameter, "View Filter");
		return parameter;
	}

	// input[@placeholder='Search']/ancestor::div/following-sibling::div/descendant::a[1][@title='AKAKOQFWN04-02-2025
	// 14:02:34 IST']

	String preLayoutCode = "//input[@placeholder='Search']/ancestor::div/following-sibling::div/descendant::a[1][@title='";
	String postLayoutCode = "']";

	public boolean checkLayoutOnTopExist(String LayoutCode) {
		String xpath = preLayoutCode + LayoutCode + postLayoutCode;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.WebIsElementDisplayed(ele.get(0), "LayoutCode");

	}

	public boolean checkIfLayoutExist(String layout) throws InterruptedException {
		String basicXpath = "//a[@title='LayoutModel']//span";
		String actualxpathLayout = basicXpath.replaceAll("LayoutModel", layout);
		return ReUsableMethods.WebIsElementDisplayed(ReUsableMethods.findElementByPath(actualxpathLayout).get(0),
				layout);

	}

	public Map<String, String> leadClearMapping() throws Exception {
		Map<String, String> windowHandlesMap = new HashMap<>();

		ReUsableMethods.webClickElement(toolbox, "ToolBox");
		ReUsableMethods.webClickElement(fieldOptionMapping, "Manage Field Option Mapping");
		ReUsableMethods.switchToChildWindowHandle();
		String childWindowII = ReUsableMethods.getWindow();

		ReUsableMethods.webEnterText(textBox, "Auto Mapping", "search Box");
		ReUsableMethods.webClickElement(applyButton, "Apply Button");

		ReUsableMethods.safeClick(FirstThreeDots, view);
		ReUsableMethods.webClickElement(view, "View");
		ReUsableMethods.switchToChildWindowHandle();
		String childWindowIII = ReUsableMethods.getWindow();

		ReUsableMethods.webClickElement(clearFirst, "clear");

		clickOkButtonForMassDelete();

		windowHandlesMap.put("childII", childWindowII);
		windowHandlesMap.put("childIII", childWindowIII);
		return windowHandlesMap;

	}

	// Record in Search Listing
	@FindBy(xpath = "//a[contains(@data-autoid,'LastName_')]")
	public WebElement searchedLeadName;

	// Action link on top of View

	@FindBy(xpath = "//div[contains(@class,'top-actions')]/div")
	public WebElement actionLink;

	@FindBy(xpath = "//i[@title='Assignment Button']")
	public WebElement assignmentCABBtn;

	@FindBy(xpath = "//*[@data-autoid='LBL_TITLE_lbl']")
	public WebElement postCABExecutionText;

	@FindBy(xpath = "//*[normalize-space(text()) = '100%']")
	public WebElement postCABExecutionPercentage;

	@FindBy(xpath = "//*[@data-autoid='LBL_UNMATCHEDITEM_ctrl']")
	public WebElement txtUnMatchedItems;

	@FindBy(xpath = "//a[@data-autoid='0_LINK_LOGACALLView01']")
	public WebElement logACallBtn;

	@FindBy(xpath = "//a[contains(@data-autoid , 'LastName')]")
	public WebElement firstrecord;

	public WebElement getLeadPath(String titleName) {
		String xpath = "//div[@title='" + titleName + "']";
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		return list.get(0);
	}

	@FindBy(xpath = "//div[@data-autoid='pagetitleheading']")
	public WebElement pageTitleHeading;

	public boolean isPageTitleHeadingDisplayed() {
		ReUsableMethods.scrollElementToCentreOfScreen(pageTitleHeading);
		return ReUsableMethods.WebIsElementDisplayed(pageTitleHeading, "pageTitleHeading");
	}

	public String getProductOnFieldOptionMappeing(String product) {

		String pre = "//div[normalize-space() = '";
		String post = "' and contains(@data-autoid,'Name_')]";
		String xpath = pre + product + post;
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.WebGetElementText(list.get(0), "Product name");

	}

	public boolean isCustomActionButtonVisible(String customActionButtonName) {
		ReUsableMethods.safeClick(moreView, searchActionBox);
		ReUsableMethods.webClearText(searchActionBox, "Action search box");
		ReUsableMethods.webEnterText(searchActionBox, customActionButtonName, "Action search box");

		String pre = "//a[@title='";
		String post = "']";
		String xpath = pre + customActionButtonName + post;
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		;

		return ReUsableMethods.WebIsElementDisplayed(list.get(0), "Update Home Filter Custom Action Button");
	}

	public boolean selectAndActiveFilter(String text) {

		String xpath = String.format("//a[text()='%s']", text);
		WebElement element = ReUsableMethods.findElementByPath(xpath).get(0);
		ReUsableMethods.webClickElement(element, text);

		String xpath1 = String.format("//a[text()='%s' and contains(@class,'filter__item--link ph-4 active')]", text);
		WebElement element1 = ReUsableMethods.findElementByPath(xpath1).get(0);
		return ReUsableMethods.WebIsElementDisplayed(element1, xpath1);

	}

	public String leadListingPath = "//a[contains(@data-autoid,'Name_')]";

	public void clickOnActionButton() {
		ReUsableMethods.webClickElement(actionButton, "actionButton");
	}

	@FindBy(xpath = "//div[@data-autoid='Cancel_0']")
	public WebElement closeIcon;
	
	@FindBy(xpath = "//*[normalize-space()='Completion Status' and '100%']")
	public WebElement completionStatusSuccessfully;
	
	public void closeAfterCompletionSuccessfully() {
		clickOnOkIcon();
		if(ReUsableMethods.WebIsElementDisplayed(completionStatusSuccessfully, "completion Status Successfully popup"));
		ReUsableMethods.webClickElement(closeButton, "close button");
	}
	
	public boolean isActionButtonDisplayed(String button) {
		String preButton = "//div[@data-testid='dropdown-content']//span[normalize-space()='";
		String postButton = "']";
	    String expectedPath = preButton + button + postButton;
	    List<WebElement> buttonPath = ReUsableMethods.findElementByPath(expectedPath);
	    return ReUsableMethods.WebIsElementDisplayed(buttonPath.get(0), "first button");
	}
}
