package com.businessnext.objects.lead.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.pages.CommonProductFunctions;
import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;
import com.utilities.WebWait;

public class LeadCreationPage {

	WebDriver wdriver;

	public LeadCreationPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	// Enter Lead_Name Initialization

	@FindBy(xpath = "//input[@data-autoid='FIRSTNAME_ctrl']")
	public WebElement leadFirstName;

	public void enterLeadFirstName(String firstName) {
		ReUsableMethods.webClearText(leadFirstName, "leadFirstName");
		ReUsableMethods.webEnterText(leadFirstName, firstName, "leadFirstName");
	}

	@FindBy(xpath = "//div[@data-autoid='OfferName_0']")
	public WebElement firstOfferName;

	@FindBy(xpath = "//a[@data-autoid='LE_OF_NAME_srch']")
	public WebElement offerNameSearchIcon;

	@FindBy(xpath = "//div[contains(text(),'Please specify the valid search criteria')]")
	public WebElement warningMessageInAdvanceSearch;

	@FindBy(xpath = "//input[@data-autoid='MIDDLENAME_ctrl']")
	public WebElement leadMidName;

	@FindBy(xpath = "//input[@data-autoid='LASTNAME_ctrl']")
	public WebElement leadLastName;

	@FindBy(xpath = "//input[@data-autoid='LASTNAME_ctrl']")
	public WebElement enterLeadName;

	@FindBy(xpath = "//select[@data-autoid='SALUTATION_ctrl']")
	public WebElement leadSalutation;

	@FindBy(xpath = "//select[@data-autoid='LE_LEADRATING_ctrl']")
	public WebElement leadRating;

	@FindBy(xpath = "//select[@data-autoid='LE_STATUSCODE_ctrl']")
	public WebElement statusCode;

	@FindBy(xpath = "//select[@data-autoid='LE_PRODUCT_ctrl']")
	public WebElement leadProduct;

	@FindBy(xpath = "//select[@data-autoid='LE_LEADSOURCE_ctrl']")
	public WebElement leadSource;

	@FindBy(xpath = "//*[@data-autoid='Save']")
	public WebElement leadSaveButton;

	@FindBy(xpath = "//*[@data-autoid='LeadID_0']")
	public WebElement listingLeadIDFirst;

	@FindBy(xpath = "//a[@data-autoid='Search']")
	public WebElement searchButtonAdvanceSearchPage;

	@FindBy(xpath = "//*[text()='Fill atleast Subject']")
	public WebElement mandatoryValidationSearchPage;

	@FindBy(xpath = "(//div[@class='mobPopupBox db backdrop']//div)[9]")
	private WebElement validationRuleFiredMessage;

	@FindBy(xpath = "//button[@data-autoid='0_button']")
	private WebElement validationAlertOkButton;

	@FindBy(xpath = "//input[@data-autoid='LE_TITLE_ctrl']")
	public WebElement leadTitle;

	@FindBy(xpath = "//a[@data-autoid='LE_COMPANY_srch']")
	public WebElement Comapny_picker;

	@FindBy(xpath = "//a[@data-autoid='LE_PRODUCTCATEGORY_srch']")
	public WebElement Product_Category_picker;

	@FindBy(xpath = "//*[@data-autoid='Name_0']")
	public WebElement NameFirst;

	@FindBy(xpath = "//*[contains(@data-autoid, 'Name_0')]")
	public WebElement UserNameFirst;

	@FindBy(xpath = "//*[@data-autoid='LE_LEADOWNER_ctrl']")
	public WebElement inputTxtLeadOwner;

	@FindBy(xpath = "//*[@name='icon-stack']")
	public WebElement iconStack;

	@FindBy(xpath = "//a[@title='Show Results Summary']")
	public WebElement showResultSummary;

	@FindBy(xpath = "//div[@class='screenFlowJnryContainer']")
	public WebElement profilerReviewJourney_Page;

	@FindBy(xpath = "//div[@data-autoid='List_CallScriptActive_0']")
	public WebElement callScript_page;

	@FindBy(xpath = "//div[@class='crm-card reportGroupCard']")
	public WebElement callScriptReportDetail_page;

	@FindBy(xpath = "//*[@data-autoid='CampaignName_0']")
	public WebElement CampNameFirst;

	@FindBy(xpath = "//*[@data-autoid='CityName_0']")
	public WebElement CityNameFirst;

	@FindBy(xpath = "//input[@data-autoid='LE_URL_ctrl']")
	public WebElement webURL;

	@FindBy(xpath = "//a[@data-autoid='LE_CAMPAIGN_srch']")
	public WebElement Campaign_picker;

	@FindBy(xpath = "//a[@data-autoid='LE_ZIP_CODE_srch']//i")
	private WebElement zipcode_Picker;

	@FindBy(xpath = "//a[@data-autoid='gridHF_LE_ZIP_CODE']")
	public WebElement searchZipcode_Icon;

	@FindBy(xpath = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	public WebElement searchTextbox;

	@FindBy(xpath = "//div[@data-autoid='PinCode_0']")
	public WebElement pincode_FirstRow;

	@FindBy(xpath = "//input[@data-autoid='LE_STATE_ctrl']")
	public WebElement stateTextBox;

	@FindBy(xpath = "//input[@data-autoid='LE_CITY_ctrl']")
	public WebElement cityTextBox;

	@FindBy(xpath = "//input[@data-autoid='LE_ADDRESS_ctrl']")
	public WebElement leadAddress;

	@FindBy(xpath = "//label[@data-autoid='LE_LEADRATING_lbl']")
	public WebElement leadRatingLabel;

	@FindBy(xpath = "//a[@data-autoid='gridHF_cust_11403']")
	public WebElement multiPickerWithIdEnterButton_Lead;

	@FindBy(xpath = "//a[@data-autoid='FlowNext']")
	public WebElement leadSaveProceedButton;

	@FindBy(xpath = "//div[@data-autoid='Name_0']")
	public WebElement leadCountryName;

	@FindBy(xpath = "//select[@data-autoid='cust_11402_ctrl']")
	private WebElement lookupWithId_Lead;

	@FindBy(xpath = "//a[@data-autoid='cust_11403_srch']")
	private WebElement multiPickerWithIdSearchIcon_Lead;

	@FindBy(xpath = "//input[@data-autoid='LE_MOBILE_ctrl']")
	public WebElement mobile;

	@FindBy(xpath = "//input[@data-autoid='LE_SEC_MOBILE_ctrl']")
	public WebElement mobileSecondary;

	@FindBy(xpath = "//input[@data-autoid='LE_OFFICE_PHONE_ctrl']")
	public WebElement officePhone;

	@FindBy(xpath = "//input[@data-autoid='LE_FAX_ctrl']")
	public WebElement faX;

	@FindBy(xpath = "//textarea[@data-autoid='LE_DESCRIPTION_ctrl']")
	public WebElement leadDescription;

	@FindBy(xpath = "//input[@data-autoid='LE_EMAIL_ctrl']")
	public WebElement email;

	@FindBy(xpath = "//select[@data-autoid='LE_INDUSTRY_ctrl']")
	public WebElement leadIndustry;

	@FindBy(xpath = "//select[@data-autoid='LE_EMPLOYEES_ctrl']")
	public WebElement leadEmpCount;

	@FindBy(xpath = "//a[@data-autoid='Save']")
	public WebElement save;

	@FindBy(xpath = "//a[@data-autoid='SaveAndNew']")
	public WebElement saveandnew;

	@FindBy(xpath = "//*[contains(@data-autoid,'Cancel_')]")
	public WebElement cancel;

	@FindBy(xpath = "//*[@data-autoid='i_shr' and @name='icon-cross']")
	public WebElement cross;

	@FindBy(xpath = "//*[@name='icon-custom-menu']")
	public WebElement iconCustomMenu;

	// For FreashDB
	@FindBy(xpath = "//*[@data-autoid='button']")
	public WebElement iconCustomMenuFreshDB;

	@FindBy(xpath = "//input[@data-autoid='CTRL_ASSIGNMENT_RULE_ctrl']")
	public WebElement chckBoxAssignmentRule;

	@FindBy(xpath = "//label[@data-autoid='LE_PRODUCT_lbl']")
	public WebElement leadProductLabel;

	@FindBy(xpath = "//a[@data-autoid='FlowNext']")
	private WebElement saveandproceed;

	@FindBy(xpath = "//a[@data-autoid='LE_COUNTRY_srch']")
	public WebElement countrySearchIcon;

	@FindBy(xpath = "//a[@data-autoid='LE_COUNTRY_srch']")
	public WebElement citySearchIcon;

	@FindBy(xpath = "//a[@data-autoid='LE_COUNTRY_srch']")
	public WebElement stateSearchIcon;

	@FindBy(xpath = "//a[@data-autoid='LE_COUNTRY_srch']")
	public WebElement districtSearchIcon;

	@FindBy(xpath = "//a[@data-autoid='LE_ZIP_CODE_srch']")
	public WebElement zipcode_picker;

	@FindBy(xpath = "//div[@class='w--100 f12 text-regular tc']")
	private WebElement helpRuleFiredMessage;

	@FindBy(xpath = "//a[@data-autoid='LE_TERRITORY_srch']")
	public WebElement territorySearchIcon;

	@FindBy(xpath = "(//label[@class='form-element__label'])[23]")
	private WebElement automationMashup;

	@FindBy(xpath = "//i[@class='icon icon-new pointer']")
	private WebElement plusPointer;

	@FindBy(xpath = "//div[@data-autoid='Name_3']")
	private WebElement productCategoryName_LeadMultilingual;

	@FindBy(xpath = "//div[@class='crm-card__body db']")
	private WebElement bodyCheck;

	@FindBy(xpath = "//input[@data-autoid='LASTNAME_ctrl']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@data-autoid='LASTNAME_ctrl']")
	private WebElement lastName1;

	@FindBy(xpath = "//select[@data-autoid='LE_STATUSCODE_ctrl']")
	public WebElement leadStatusCode;

	@FindBy(xpath = "//select[@data-autoid='LE_PREFERRED_ctrl']")
	public WebElement leadPrefChanel;

	@FindBy(xpath = "//a[@data-autoid='LE_PRODUCTCATEGORY_srch']")
	private WebElement productCategorySearchButton;

	@FindBy(xpath = "//label[@data-autoid='LE_LEADOWNER_lbl']")
	private WebElement leadOwnerLabel;

	@FindBy(xpath = "//h6[contains(text(),'Warning')]")
	public WebElement validationWarning;

	@FindBy(xpath = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	public WebElement multiPickerWithIdEnterValue_Lead;

	@FindBy(xpath = "//*[@data-autoid='gridHF_LE_ASSIGNTO']")
	public WebElement applyLinkAssignTo;

	@FindBy(xpath = "//label[@data-autoid='LE_NAME_lbl']")
	private WebElement leadNameLabel;

	@FindBy(xpath = "//select[@data-autoid='cust_11401_ctrl']")
	private WebElement keyPairValue_Lead;

	@FindBy(xpath = "//div[@data-autoid='Name_0']")
	private WebElement territoryName;

	@FindBy(xpath = "//input[@data-autoid='cust_11491_ctrl']")
	private WebElement amountWithRange;

	@FindBy(xpath = "//label[@title='amount with range']")
	private WebElement amountWithRangeLabel;

	@FindBy(xpath = "//div[@data-autoid='vs_0']")
	private WebElement amountRange;

	@FindBy(xpath = "//input[@data-autoid='LE_AMOUNT_ctrl']")
	public WebElement leadAmount;

	@FindBy(xpath = "(//div[@data-autoid='button'])[2]")
	private WebElement threeDot;

	@FindBy(xpath = "//select[@data-autoid='CTRL_CURRENCY_ctrl']")
	private WebElement currencyDropDown;

	@FindBy(xpath = "//select[@data-autoid='LE_PRODUCT_ctrl']")
	private WebElement product;

	@FindBy(xpath = "//a[@data-autoid='cust_11102_srch']")
	private WebElement fileUploadSearchIcon;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-plus']")
	private WebElement fileUploadPluesIcon;

	@FindBy(xpath = "//a[@data-autoid='DM_FolderName_srch']")
	private WebElement fileUploadFolderSearchIcon;

	@FindBy(xpath = "(//input[@data-autoid='Grid_SearchTextBox_ctrl'])[2]")
	private WebElement enterFolderNameSearchBox_FileUpload;

	@FindBy(xpath = "(//div[@title='Ankita'])[1]")
	private WebElement clickOnFolderName_FileUpload;

	@FindBy(xpath = "//a[@data-autoid='gridHF_DM_FolderName']")
	private WebElement enterButton_FileUpload;

	@FindBy(xpath = "(//a[@data-autoid='Save'])[1]")
	private WebElement saveButton_FileUpload;

	@FindBy(xpath = "//div[@class='longtextmessage']")
	private WebElement processMandatoryRuleMessage;

	@FindBy(xpath = "//span[@class='cke_button_icon cke_button__smiley_icon']")
	private WebElement emojiSign;

	@FindBy(xpath = "//img[@title='smiley']")
	private WebElement smileyEmoji;

	@FindBy(xpath = "//input[@data-autoid='LE_PHONE_ctrl']")
	public WebElement phoneNumber;

	@FindBy(xpath = "//button[@data-autoid='0_button']")
	private WebElement ignoreAndUpdateButton;

	@FindBy(xpath = "//input[@title='File Input']")
	private WebElement docUploader;

	@FindBy(xpath = "//a[@data-autoid='LE_ASSIGNTO_srch']//i")
	public WebElement searchIconForAssignTo;

	@FindBy(xpath = "//a[@data-autoid='LE_ASSIGNTO_del")
	public WebElement deleteIconForAssignTo;

	@FindBy(xpath = "//a[@data-autoid='LE_LEADOWNER_srch']")
	public WebElement searchIconLeadOwner;

	@FindBy(xpath = "//*[@data-autoid='FlowNext']")
	private WebElement saveAndProceed;

	@FindBy(xpath = "//a[@data-autoid='menu_icon']")
	private WebElement menuIcon_ThreeDots;

	@FindBy(xpath = "(//label[@class='checkbox-button checkbox--right'])[1]")
	private WebElement checkBoxAssignemntRule;

	@FindBy(xpath = "//div[@data-autoid='DisplayName_0']")
	private WebElement firstElementAfterSearch_InPIckerPouup;

	@FindBy(xpath = "(//div[@title='hdfc.png'])[1]")
	private WebElement fileUpload;

	@FindBy(xpath = "//div[normalize-space()='Save & New']")
	private WebElement saveAndNewButton;

	@FindBy(xpath = "//input[@data-autoid='cust_11915_ctrl']")
	public WebElement advanceEncryptionEmail;

	@FindBy(xpath = "//input[@data-autoid='cust_11913_ctrl']")
	public WebElement advanceEncryptionText;

	@FindBy(xpath = "//input[@data-autoid='cust_11916_ctrl']")
	public WebElement advanceEncryptionRegularExpression;

	@FindBy(xpath = "//input[@data-autoid='LE_PANNUMBER_ctrl']")
	public WebElement panNumber;

	@FindBy(xpath = "//span[contains(text(), 'Invalid')]")
	public WebElement invalidValueValidation;

	@FindBy(xpath = "//div[normalize-space()='Save & New']")
	public WebElement encryption;

	@FindBy(xpath = "//*[@data-autoid='LE_PARENTNAME_ctrl']")
	public WebElement leadParent;

	@FindBy(xpath = "//input[@data-autoid='cust_11917_ctrl']")
	public WebElement advanceEncryptionEnterData_CustomReadOnly;

	@FindBy(xpath = "//input[@readonly and contains(@data-autoid, 'cust_')]")
	public WebElement advanceEncryption_CustomReadOnlyafterEdit;

	@FindBy(xpath = "//*[@data-autoid='gridHF_LE_COMPANY']")
	public WebElement applyButton;

	@FindBy(xpath = "//*[@data-autoid='LE_AADHARCARDNUMBER_ctrl']")
	public WebElement aadharNumberMapping;

	@FindBy(xpath = "//div[@data-autoid='Name_0']")
	public WebElement clickOnSearchedField;

	@FindBy(xpath = "//a[@data-autoid='FlowNext']")
	public WebElement nextButton;

	@FindBy(xpath = "//a[@data-autoid='FlowFinish']")
	public WebElement finishButton;

	@FindBy(xpath = "//input[@data-autoid='CTRL_ASSIGNMENT_RULE_ctrl']//following-sibling::label")
	public WebElement checkboxForAssignmentRule;

	@FindBy(xpath = "//div[@data-autoid='LE_DATEOFBIRTH_today']")
	public WebElement leadTodayDate;

	@FindBy(xpath = "//*[@data-autoid='cust_1320_ctrl']")
	public WebElement creditCardNumber;

	@FindBy(xpath = "//a[@data-autoid='cust_1231_srch']")
	public WebElement leadPickerField;

	@FindBy(xpath = "//*[@data-autoid='LE_SCHEMENAME_ctrl']")
	public WebElement schemeName;

	@FindBy(xpath = "//select[@name='SUFFIX']")
	public WebElement suffixName;

	@FindBy(xpath = "//span[normalize-space()='Scheme Information']")
	public WebElement schemeExternalBind;

	@FindBy(xpath = "//select[@data-autoid = 'LE_PRODUCT_ctrl']")
	public WebElement productText;

	@FindBy(xpath = "//input[@data-autoid = 'LE_TERRITORY_ctrl']")
	public WebElement territory;

	@FindBy(xpath = "//label[@title='numberField']/following-sibling::div//div//input")
	public WebElement numberFieldText;

	@FindBy(xpath = "//select[@data-autoid='Lookup1Id_ctrl']")
	public WebElement disQualifyReasonDropdown;

	@FindBy(xpath = "//a[@data-autoid='DisqualifyLead']")
	public WebElement disQualifyButton;

	public String getTerritoryText() throws Exception {
		return ReUsableMethods.getTextElementAttribute(territory, "value");

	}

	public String getProductText() {
		// ReUsableMethods.WebGetElementText(productText, "value");

		return ReUsableMethods.webGetFirstSelectedOption(productText);
	}

	@FindBy(xpath = "//select[@data-autoid = 'LE_STATUSCODE_ctrl']")
	public WebElement statusCodeText;

	public String getStatusCodeText() {
		return ReUsableMethods.webGetFirstSelectedOption(statusCodeText);
	}

	public void clickOnNextButton() {
		ReUsableMethods.webClickElement(nextButton, "Next Button");
		new CommonProductFunctions(wdriver).waitForLoader();
	}

	public void clickOnFinish() {
		ReUsableMethods.webClickElement(finishButton, "Finish Button");

		ReUsableMethods.waitforWindowSize(1);

	}

	public boolean verifyAdvanceEncryption_CustomReadOnlyafterEdit() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryption_CustomReadOnlyafterEdit,
				"Advance Encryption Custom Field ReadOnly");
	}

	public boolean verifyInvalidValueValidation() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(invalidValueValidation, "Invalid Value");
	}

	public boolean verifyPanNumberPlainText() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(panNumber, "Advance Encryption Pan Number");
	}

	public boolean verifyValidationWarning() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(validationWarning, "validation message Warning");
	}

	public boolean verifyAdvanceEncryptionEmailPlainText() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionEmail, "Advance Encryption Email");
	}

	public boolean verifyAdvanceEncryptionTextPlainText() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionText, "Advance Encryption Text");
	}

	public boolean isDisplayedMandatoryValidationMessage() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(mandatoryValidationSearchPage, "Validation Message");
	}

	public boolean verifySearchButtonAdvanceSearchPage() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(searchButtonAdvanceSearchPage,
				"Search Button Advance Search Page");
	}

	public boolean verifyIfCustomFieldIsVisible(String customFieldParam) {
		String baseXpath = "(//label[normalize-space()='customFieldName'])";
		String actualXpath = baseXpath.replaceAll("customFieldName", customFieldParam);
		return wdriver.findElement(By.xpath(actualXpath)).isDisplayed();
	}

	// Element Functions

	// Lead_Name Function

	public void enterFirstName() {
		ReUsableMethods.webEnterText(leadFirstName, "Starc", "enter in first name");
	}

	public String enterFirstNameWithTime(String lead_name) throws InterruptedException {
		String expectedLeadName = lead_name + ReUsableMethods.getCurrentTime();
		ReUsableMethods.webClearText(leadFirstName, "leadFirstName");
		ReUsableMethods.webEnterText(leadFirstName, expectedLeadName, "Lead Creation");
		// Thread.sleep(1000);
		return expectedLeadName;
	}

	public String enterLastLeadName(String lead_name) throws InterruptedException {
		String expectedLeadName = lead_name + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webClearText(enterLeadName, "Clear Last Name TextBox");
		ReUsableMethods.webEnterText(enterLeadName, expectedLeadName, "Lead Creation");

		return expectedLeadName;
	}

	public String enterLastName(String name) throws InterruptedException

	{
		ReUsableMethods.webClearText(lastName, "Clear Name");

		String nameActual = name + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(lastName, nameActual, "Lead Last name");
		// Thread.sleep(1000);
		return nameActual;
	}

	// ReUsableMethods.getCurrentdateTime();
	public String enterLastName1(String name) throws InterruptedException

	{
		ReUsableMethods.webClearText(lastName, "Clear Name");

		// String nameActual = name + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(lastName, name, "Lead Last name");
		return name;
	}

	public String enterLastNameWithoutTime(String name) throws InterruptedException

	{
		ReUsableMethods.webClearText(lastName, "Clear Name");

		// String nameActual = name + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(lastName, name, "Lead Last name");
		// Thread.sleep(1000);
		return name;
	}

	public String selectLeadRating(String rating) throws InterruptedException {
		// String lead_rating=ConstantClassLead.LEAD_RATING;
		ReUsableMethods.webSelectByVisibleText(leadRating, rating, "Lead Rating");

		return rating;
	}

	public String selectLeadProduct(String product) throws InterruptedException {
		ReUsableMethods.webSelectByVisibleText(leadProduct, product, "Lead Product");

		return product;
	}

	public void clickOnSaveLead() throws InterruptedException {
		int currentWindowSize = ReUsableMethods.currentWindowSize();

		ReUsableMethods.webClickElement(leadSaveButton, "Lead Save Button");
		if (currentWindowSize > 1) {
			ReUsableMethods.waitforWindowSize(currentWindowSize - 1);
		} else {
			waitForLeadSyncLoader();
		}

	}

	public void clickOnIconStack() throws InterruptedException {
		ReUsableMethods.webClickElement(iconStack, "Icon Stack");
	}

	public String getValidationMessage() throws InterruptedException {

		return ReUsableMethods.WebGetElementText(validationRuleFiredMessage, "validation message");

	}

	public void clickOnValidationAlertOkButton() {
		ReUsableMethods.webClickElement(validationAlertOkButton, "validation Alert Ok Button");
	}

	public void clickOnZipcodePicker() {
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath("//span[text()='Contact Information']")));
		ReUsableMethods.safeClick(zipcode_picker, searchZipcode_Icon);
	}

	public void searchZipcode(String code) throws InterruptedException {
		ReUsableMethods.webEnterText(searchTextbox, code, "Zipcode Picker");
		ReUsableMethods.webClickElement(searchZipcode_Icon, "Search icon");
		// Thread.sleep(2000);
	}

	public void clickOnPincodeOnFirstRow() {
		ReUsableMethods.webClickElement(pincode_FirstRow, "Pincode on First Row");
	}

	public boolean verifyWarningMessageInAdvanceSearch() {
		return ReUsableMethods.WebIsElementDisplayed(warningMessageInAdvanceSearch, "Warning Message In AdvanceSearch");
	}

	public String getValueInStateField() {
		return stateTextBox.getDomAttribute("value");
	}

	public String getValueInCityField() {
		return cityTextBox.getDomAttribute("value");

	}

	public String enterLeadLastName(String lead_name) throws InterruptedException {
		ReUsableMethods.webClearText(enterLeadName, "Name");
		ReUsableMethods.scrollDownToElement(enterLeadName);
		String expectedLeadName = lead_name + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(enterLeadName, expectedLeadName, "Lead Creation");
		return expectedLeadName;
	}

	public String enterLeadLastNameWithoutDate(String lead_name) throws InterruptedException {
		ReUsableMethods.webEnterText(enterLeadName, lead_name, "Lead Creation");
		// Thread.sleep(3000);
		return lead_name;
	}

	public String enterAddress(String address) throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollElementToCentreOfScreen(leadAddress);
		ReUsableMethods.webClearText(leadAddress, "Lead Address");
		ReUsableMethods.webEnterText(leadAddress, address, "Lead Address");
		return address;
	}

	public void enterLeadAmount(String amount) {
		ReUsableMethods.selectAllAndBackspaceToClear(leadAmount);
		ReUsableMethods.webEnterText(leadAmount, amount, "enter in lead amount");
	}

	public void clickOnSaveProceedLead() throws InterruptedException {
		ReUsableMethods.webClickElement(leadSaveProceedButton, "Lead Save And Proceed Button");
		new CommonProductFunctions(wdriver).waitForLoader();

	}

	public String verifyProductCategoryName() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(productCategoryName_LeadMultilingual, "Product Category Picker");
	}

	public void enterMobile(String leadMobile) {
		ReUsableMethods.scrollElementToCentreOfScreen(mobile);
		ReUsableMethods.webEnterText(mobile, leadMobile, "lead mobile number");
	}

	// To Enter Email
	public void enterEmail(String emailId) {
		ReUsableMethods.webEnterText(email, emailId, "lead email");
	}

	public String enterEmailReturn(String emailId) {
		ReUsableMethods.webEnterText(email, emailId, "lead email");
		return emailId;
	}

	public void enterEmail_ComposeEmail() {
		ReUsableMethods.webEnterText(email, "rohan.kumar@businessnext.com", "lead email");
	}

	public String getAutomationMashupControl() throws InterruptedException {

		ReUsableMethods.scrollDownToElement(automationMashup);
		return ReUsableMethods.WebGetElementText(automationMashup, "get the mashup control");
	}

	public void clickOnPlusPointer() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollDownToElement(plusPointer);
		ReUsableMethods.webClickElement(plusPointer, "click on plus pointer");
	}

	public String getOnMashupBody() {
		return ReUsableMethods.WebGetElementText(bodyCheck, "check the body");
	}

	public String getOnMashupBodyOnDetail() {
		return ReUsableMethods.WebGetElementText(bodyCheck, "check the body");
	}

	public String verifyProcessMandatoryRuleMessage() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(processMandatoryRuleMessage, "Process Mandatory Rule Message").trim();
	}

	public void enterInLastTextBox() throws InterruptedException {
		ReUsableMethods.webEnterText(lastName1, "mashupControl", "click on name");
		// Thread.sleep(2000);
	}

	@FindBy(xpath = "//*[@title='Close']")
	public WebElement close;

	@FindBy(xpath = "//div[@class='circleArrow']")
	private WebElement leadSyncLoader;

	public void waitForLeadSyncLoader() {
		WebDriverWait wait = new WebDriverWait(wdriver, Duration.ofSeconds(15));

		try {

			// Check if the loader is displayed
			if (WebWait.fluentWaitForDisplayedwithCustomTimeOut(leadSyncLoader, 1)) {
				System.out.println("leadSyncLoader is displayed");

				// Wait for the loader to disappear
				wait.until(ExpectedConditions.invisibilityOf(leadSyncLoader));
				System.out.println("leadSyncLoader has disappeared.");
			}

		} catch (Exception e) {
			// General exception handling for any unexpected errors
			System.err.println("An error occurred while waiting for the loader.");
			e.printStackTrace(); // Optionally, log the stack trace
		}
	}

	@FindBy(xpath = "//div[normalize-space()='Close']")
	public WebElement closeButton;

	public void clickOnSaveButton() throws InterruptedException {
		ReUsableMethods.webClickJavaScriptExecutor(save);
		ReUsableMethods.waitforElementInvisible(save);
		waitForLeadSyncLoader();
		ReUsableMethods.scrollElementToCentreOfScreen(closeButton);

	}

	public void clickOnSaveButtonAndClose() throws InterruptedException {
		ReUsableMethods.webClickJavaScriptExecutor(save);
		ReUsableMethods.waitforElementInvisible(save);
		waitForLeadSyncLoader();
		ReUsableMethods.webMoveToElement(close, "Close Button");
		if (ReUsableMethods.WebIsElementDisplayed(close, "Close Button")) {
			ReUsableMethods.webClickElement(close, "Close Button");
		}

	}

	public void clickOnSave_Lead() throws InterruptedException {

		ReUsableMethods.scrollDownToElement(save);
		int currentWindowSize = ReUsableMethods.currentWindowSize();
		ReUsableMethods.webClickElement(save, "click on save button");
		if (currentWindowSize > 1) {
			ReUsableMethods.waitforWindowSize(currentWindowSize - 1);
		}

	}

	// LeadIDplusone
	@FindBy(xpath = "//*[@data-autoid='LE_NUMBER_ctrl']")
	public WebElement LeadID;

	@FindBy(xpath = "//input[@data-autoid='APP_SEARCH_ITEM_ctrl']")
	private WebElement advanceSearchBox;

	public String get_modifiedLeadID(String val) {

		String leadID = ReUsableMethods.WebGetElementText(LeadID, "Lead ID");
		int leadintval = Integer.parseInt(leadID);

		if (val.equalsIgnoreCase("next")) {
			leadintval = leadintval + 1;
		}
		if (val.equalsIgnoreCase("previous")) {
			leadintval = leadintval - 1;
		}

		return Integer.toString(leadintval);

	}

	public boolean verifyIfLeadIDVisible() {
		return ReUsableMethods.WebIsElementDisplayed(LeadID, "LeadID");
	}

	@FindBy(xpath = "//*[@data-autoid='APP_SEARCH_ITEM_ICON']")
	private WebElement advanceSearchIcon;

	public void clickAdvanceSearchIcon() {
		ReUsableMethods.webClickElement(advanceSearchIcon, "advanceSearchIcon");
	}

	@FindBy(xpath = "//*[normalize-space(text()) = 'No data exists']")
	private WebElement noDataExists;

	public boolean verifyIfNoDataExisVisible() {
		return ReUsableMethods.WebIsElementDisplayed(noDataExists, "NoDataExists");
	}

	// a[@data-autoid='APP_SEARCH_ITEM_ICON']

	public String getAmoutRange() {
		return ReUsableMethods.WebGetElementText(amountRange, "amountRange");
	}

	public void clickOnThreeDot() {
		ReUsableMethods.webClickElement(threeDot, "Three Dot");
	}

	public void selectCurrencyDropDown(String param) throws InterruptedException {
		ReUsableMethods.webSelectByVisibleText(currencyDropDown, param, "Currency drop down");
		// Thread.sleep(2000);
	}

	public void enterAmountWithRange(String param) {
		ReUsableMethods.webClearText(amountWithRange, "Clear Name");
		ReUsableMethods.webEnterText(amountWithRange, param, "Amount with range");
	}

	public void enterInLeadAmount() {
		ReUsableMethods.webEnterText(leadAmount, "20151150", "enter in lead amount");
	}

	public void selectLeadRating() {
		ReUsableMethods.webSelectByVisibleText(leadRating, "Cold", "LeadRatingdropdown");
	}

	public void selectProduct() {
		ReUsableMethods.webSelectByVisibleText(product, "Credit Card", "lead product dropdown");
	}

	public String getAmountWithRangeLabel() throws InterruptedException {

		// Thread.sleep(1000);
		return amountWithRangeLabel.getText().trim();
	}

	// public String verifySave_Hindi() throws InterruptedException {
	// //Thread.sleep(2000);
	//
	// return ReUsableMethods.WebGetElementText(amountWithRangeLabel,
	// "amountWithRangeLabel");
	// }

	public String verifySave_Hindi() throws InterruptedException {

		return ReUsableMethods.WebGetElementText(leadSaveButton, "leadSaveButton");
	}

	public void clickOnCountrySearchIcon() throws InterruptedException {
		// Thread.sleep(2000);
		// ReUsableMethods.scrollDownToElement(countrySearchIcon);
		ReUsableMethods.scrollElementToCentreOfScreen(countrySearchIcon);
		ReUsableMethods.webClickElement(countrySearchIcon, "Country Search Icon");
	}

	public String verifyLeadCountryName() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(leadCountryName, "Lead Country Name");
	}

	public boolean verify_Translation(String outputExpression) {

		try {
			String baseXpath = "//*[normalize-space()='output']";
			String actualXpath = baseXpath.replaceAll("output", outputExpression);
			// Thread.sleep(1000);
			System.out.println("actual xpath is:" + actualXpath);
			List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
			return ReUsableMethods.isWebElementDisplayed(list);

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public String verifyleadNameLabel() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(leadNameLabel, "Lead Name Label");
	}

	public String verifyleadRatingLabel() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(leadRatingLabel, "Lead Rating Label");
	}

	public String verifyLeadProductLabel() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(leadProductLabel, "Lead Product Label");
	}

	public String verifyleadOwnerLabel() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(leadOwnerLabel, "Lead Owner Label");
	}

	public void clickOnTerritorySearchIcon() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.webClickElement(territorySearchIcon, "Territory Search Icon");
	}

	public String verifyLeadTerritoryName() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(territoryName, "Territory Name");
	}

	public String selectLeadStatusCode(String statusCode) throws InterruptedException {
		// ReUsableMethods.scrollDownToElement(leadStatusCode);
		ReUsableMethods.webSelectByVisibleText(leadStatusCode, statusCode, "Lead StatusCode");
		// Thread.sleep(2000);
		return statusCode;
	}

	public String verifyHelpRuleFiredMessage() throws InterruptedException {
		// Thread.sleep(2000);
		return ReUsableMethods.WebGetElementText(helpRuleFiredMessage, "Help Rule Fired Message");
	}

	public void clickOnMultiPickerWithIdSearchIcon_Lead() throws InterruptedException {
		ReUsableMethods.scrollElementToCentreOfScreen(multiPickerWithIdSearchIcon_Lead);
		ReUsableMethods.webClickElement(multiPickerWithIdSearchIcon_Lead, "MultPicker With Id Search_Icon_Lead");
		// Thread.sleep(2000);
	}

	public void enterValeInMultiPickerWithIdEnterValue_Lead(String parameter) throws InterruptedException {
		ReUsableMethods.webEnterText(multiPickerWithIdEnterValue_Lead, parameter,
				"MultiPicker WithId Enter Value_Lead");
		// Thread.sleep(2000);
	}

	public void ClickOnMultiPickerWithIdEnterButton_Lead() throws InterruptedException {
		ReUsableMethods.webClickElement(multiPickerWithIdEnterButton_Lead, "MultiPicker With Id Ok_Button_Lead");
		// Thread.sleep(2000);
	}

	public String selectFromLookUpWithId_Lead(String parameter) throws InterruptedException {
		ReUsableMethods.scrollDownToElement(lookupWithId_Lead);
		ReUsableMethods.webSelectByVisibleText(lookupWithId_Lead, parameter, "lookup With Id_Lead");
		// Thread.sleep(2000);
		return parameter;
	}

	public String selectFromkeyPairValue_Lead(String parameter) throws InterruptedException {
		ReUsableMethods.webSelectByVisibleText(keyPairValue_Lead, parameter, "Lead Product");
		// Thread.sleep(2000);
		return parameter;
	}

	public void clickOnProductCategorySearchButton() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollElementToCentreOfScreen(productCategorySearchButton);
		ReUsableMethods.webClickElement(productCategorySearchButton, "Pproduct Category Picker");
	}

	public void selectLeadIndustry(String industry) throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.webSelectByVisibleText(leadIndustry, industry, "Select Lead Industry");
	}

	public String enterLeadTitle(String lead_title) throws InterruptedException {
		ReUsableMethods.webClearText(leadTitle, "Lead Title");
		ReUsableMethods.webEnterText(leadTitle, lead_title, "Lead Title");
		// Thread.sleep(2000);
		return lead_title;
	}

	public String enterLeadTitleWithTimeStamp(String lead_title) throws InterruptedException {
		String title = lead_title + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(leadTitle, title, "Lead Title");
		// Thread.sleep(1000);
		return title;
	}

	public void clickOnEmojiSign() throws InterruptedException {
		ReUsableMethods.scrollDownToElement(emojiSign);
		ReUsableMethods.webClickElement(emojiSign, "click on emoji");
		// Thread.sleep(2000);
	}

	public void clickOnEmoji() {
		ReUsableMethods.webClickElement(smileyEmoji, "click on emoji");
	}

	public String enterAmount(String address) throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollDownToElement(leadAmount);
		ReUsableMethods.webEnterText(leadAmount, address, "Lead Address");
		return address;
	}

	public String enterPhoneNumber(String phone) throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollDownToElement(leadAmount);
		ReUsableMethods.webEnterText(phoneNumber, phone, "Lead phone");
		return phone;
	}

	public void clickOnIgnoreAndUpdateButton() throws InterruptedException {
		ReUsableMethods.webClickElement(ignoreAndUpdateButton, "click on emoji");
		// Thread.sleep(2000);

	}

	public void clickOnsearchIcon_AssignTo() throws InterruptedException {

		// Thread.sleep(2000);

		ReUsableMethods.scrollElementToCentreOfScreen(searchIconForAssignTo);

		ReUsableMethods.webClickElement(searchIconForAssignTo, "Search Icon");

	}

	public void clickOnTerritory_SearchIcon() throws InterruptedException {
		ReUsableMethods.webClickElement(territorySearchIcon, "Country Search Icon");
	}

	public void clickOnMenuIcon_ThreeDots() {
		ReUsableMethods.scrolltoTop();
		ReUsableMethods.webClickElement(menuIcon_ThreeDots, "Three Dots");
	}

	public void clickOnCheckBox_AssignmentRuleFromMenuIconThree() throws InterruptedException {

		ReUsableMethods.clickCheckBoxUsingJavaScript(checkBoxAssignemntRule);

	}

	public void clickOnSaveAndProceed() throws InterruptedException {
		ReUsableMethods.webClickElement(saveAndProceed, "click on Save & Next");
		// Thread.sleep(2000);
	}

	public void clickOnFileUploadSearchButton() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.scrollElementToMiddle(fileUploadSearchIcon);
		// Thread.sleep(2000);
		// ReUsableMethods.scrollDownToElement(fileUploadSearchIcon);
		ReUsableMethods.webClickElement(fileUploadSearchIcon, "FileUploadSearchButton");
		// Thread.sleep(2000);
	}

	public void clickOnFileUploadPluesIcon() throws InterruptedException {
		ReUsableMethods.webClickElement(fileUploadPluesIcon, "FileUploadPluesIcon");
		// Thread.sleep(2000);
	}

	public void clickOnFileUploadFolderSearchIcon() throws InterruptedException {
		ReUsableMethods.webClickElement(fileUploadFolderSearchIcon, "FileUploadFolderSearchIcon");
		// Thread.sleep(2000);
	}

	public void enterFolderNameSearchBox_FileUpload(String parameter) throws InterruptedException {
		ReUsableMethods.webEnterText(enterFolderNameSearchBox_FileUpload, parameter, "selectFolder_FileUpload");
		// Thread.sleep(2000);
	}

	public void clickOnFolderName_FileUpload() throws InterruptedException {
		ReUsableMethods.webClickElement(clickOnFolderName_FileUpload, "selectFolder_FileUpload");
		// Thread.sleep(2000);
	}

	public void enterEnterButton_FileUpload() throws InterruptedException {
		ReUsableMethods.webClickElement(enterButton_FileUpload, "enterButton_FileUpload");
		// Thread.sleep(2000);
	}

	public void uploadDocument(String path) throws InterruptedException {
		docUploader.sendKeys(path);
		// Thread.sleep(2000);
	}

	public void clickOnSaveButton_FileUpload() {
		ReUsableMethods.webClickElement(saveButton_FileUpload, "saveButton_FileUpload");
	}

	public String getFirstRecordInPickerPopup() {
		return ReUsableMethods.WebGetElementText(firstElementAfterSearch_InPIckerPouup,
				"First Elemenet in picker search popop");
	}

	public String getSelectedOption() {
		return ReUsableMethods.webGetFirstSelectedOption(leadProduct);

	}
	///

	public void clickOnRecord_FileUpload() throws InterruptedException {
		// Thread.sleep(2000);
		ReUsableMethods.webClickElement(fileUpload, "FileUploadPluesIcon");

	}

	public void clickOnSaveAndNewButton() {
		ReUsableMethods.webClickElement(saveAndNewButton, "saveAndNewButton");
		new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();

	}

	String preXpathForState = "//div[text()='";
	String postXpathForState = "']";

	public void clickOnState(String state) {
		String xpath = preXpathForState + state + postXpathForState;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.webClickElement(ele.get(0), state);
	}

	@FindBy(xpath = "//div[@data-testid='slider-item']")
	public WebElement initialState;

	// div[@data-testid='slider-item']//descendant::*[text()='']
	String preXpathForStateImage = "//div[@data-testid='slider-item']//descendant::*[text()='";
	String postXpathForStateImage = "']//ancestor::div[contains(@class,'flow-ribbon')]";

	// #5CB96F

	public WebElement getElementForStateImage(String state) {
		String xpath = preXpathForStateImage + state + postXpathForStateImage;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		return ele.get(0);
	}

	@FindBy(xpath = "//*[@data-autoid='LE_OFFICE_PHONE_ctrl']")
	public WebElement officePhoneNum;

	@FindBy(xpath = "//label[@data-autoid='checkbox_input_row_index_0']")
	public WebElement radioButton;

	public void createLead(String lastName, String leadProduct, String email, String advanceEncryptionText,
			String panNumber, String phoneNumber) throws Exception {

		// leadHomePage.clickOnNewButton();
		//
		// // Click on the encryption layout
		// ReUsableMethods.webClickElement(leadHomePage.advanceEncryptionLayout,
		// "advanceEncryptionLayout");

		// Enter Lead Details
		enterLastName(lastName);
		selectLeadRating();
		selectLeadProduct(leadProduct);

		// Enter text fields, even if empty (no null checks)
		ReUsableMethods.webEnterText(advanceEncryptionEmail, email, "AE Email");
		ReUsableMethods.webEnterText(this.advanceEncryptionText, advanceEncryptionText, "AE Text");
		ReUsableMethods.webEnterText(this.panNumber, panNumber, "Pan Number");
		ReUsableMethods.webEnterText(this.phoneNumber, phoneNumber, "Phone Number");

		// Save the lead
		clickOnSave_Lead();

	}

	public Map<String, List<String>> createLeadForNthTime(int loop, String layout, String title, String lastname,
			String expectedAccountName, String rating, String product, String mobile, String channel, String address,
			String source) throws InterruptedException {
		ArrayList<String> expectedNames = new ArrayList<>();
		LeadHomePage leadHomePage = new LeadHomePage(DriverManager.getWdriver());
		LeadDetailPage leadDetailPage = new LeadDetailPage(DriverManager.getWdriver());
		for (int i = 1; i <= loop; i++) {

			ReUsableMethods.webClickElement(leadHomePage.newIcon, "Create New");

			ReUsableMethods.webEnterText(leadHomePage.searchArea, layout, "Search Layout Name");

			ReUsableMethods.webClickElement(leadHomePage.clickOnSearchedLayout, "Searched Layout");

			if (title != null) {
				enterLeadTitle(title);
			}
			String expectedLeadName = enterLeadLastName(lastname);
			if (expectedAccountName != null) {
				ReUsableMethods.webClickElement(Comapny_picker, "Search Icon of Company");
				ReUsableMethods.webEnterText(searchTextbox, expectedAccountName, "Search Account Name");
				ReUsableMethods.webClickElement(applyButton, "Apply Button");
				ReUsableMethods.webClickElement(clickOnSearchedField, "Serached Field");

			}
			expectedNames.add(expectedLeadName);

			ReUsableMethods.webSelectByVisibleText(leadRating, rating, "Rating");
			ReUsableMethods.webSelectByVisibleText(leadProduct, product, "Product");
			if (mobile != null) {
				enterMobile(mobile);
			}
			if (channel != null) {
				ReUsableMethods.webSelectByVisibleText(leadPrefChanel, channel, "Preferred Channel");
			}
			if (address != null) {
				enterAddress(address);
			}
			if (source != null) {
				ReUsableMethods.webSelectByVisibleText(leadSource, source, "Lead Source");
			}
			ReUsableMethods.webClickElement(leadSaveButton, "Save Button");
			if (loop > 1) {
				ReUsableMethods.webClickElement(leadDetailPage.closeIcon, "Close Icon");
			}

		}

		Map<String, List<String>> result = new HashMap<>();
		result.put("leadLastName", expectedNames);

		return result;
	}

	public String createLeadWithMandatoryField(String expectedLastName, String rating, String product)
			throws InterruptedException {

		expectedLastName = enterLastName1(expectedLastName);
		selectLeadRating(rating);
		ReUsableMethods.scrollDown();
		selectLeadProduct(product);
		clickOnSave_Lead();

		return expectedLastName;
	}

	public String createLeadWithMandatoryFieldWithAdditionalFields(String expectedLastName, String rating,
			String product, String email) throws InterruptedException {

		expectedLastName = enterLastName1(expectedLastName);
		selectLeadRating(rating);
		ReUsableMethods.scrollDown();
		selectLeadProduct(product);
		enterEmail(email);

		return expectedLastName;
	}

	public void enterCostAmount() {

	}

	String xpathListing = "//a[contains(@data-autoid,'LeadName_')]";

	public List<String> getRunTimeLeadListing() throws InterruptedException {
		// Thread.sleep(1000);
		List<String> listValues = new ArrayList<String>();
		List<WebElement> list = ReUsableMethods.findElementByPath(xpathListing);
		WebWait.waitForExplictVisibility(list.get(0));

		for (int i = 0; i < list.size(); i++) {
			listValues.add(i, list.get(i).getText());
			System.out.println(listValues.get(i));
		}

		return listValues;
	}

	// Locator for Mapper Error Message

	@FindBy(xpath = "//*[@class='dataLoaderContent']")
	public WebElement mapperErrorMessage;

	@FindBy(xpath = "//div[text() = 'Ok']")
	public WebElement errorOkBtn;

	@FindBy(xpath = "//*[@class='ui-dialog-titlebar']/following::div//*[@class='longtextmessage']")
	public WebElement longTxtValMessage;

	@FindBy(xpath = "//label[@title='External Dedupe']/following::select[@class='selectbox']")
	public WebElement externalDedupe;

	public void selectExternalDedupe(String externalDedupeValue) throws InterruptedException {
		ReUsableMethods.scrollElementToCentreOfScreen(externalDedupe);
		ReUsableMethods.webSelectByVisibleText(externalDedupe, externalDedupeValue, "External Dedupe");
	}

	@FindBy(xpath = "//*[@data-autoid='LE_SCHEMENAME_ctrl']")
	public WebElement LeadScheme;

	public void selectSchemeInRelatedLead(String schemelead) throws InterruptedException {

		ReUsableMethods.webSelectByVisibleText(LeadScheme, schemelead, "Scheme in Related Lead");

	}

	@FindBy(xpath = "//*[@data-autoid='pagetitleheading']")
	public WebElement pageTitleHeading;

	public String getPageTitleHeading() {
		return ReUsableMethods.WebGetElementText(pageTitleHeading, "pageTitleHeading");
	}

	@FindBy(xpath = "//*[@data-autoid='LE_LOCALITY_ctrl']")
	public WebElement locality;

	public void enterLocality(String localityName) {
		ReUsableMethods.webClearText(locality, "locality");
		ReUsableMethods.webEnterText(locality, localityName, "locality");
	}

	public boolean isDataPresentInTableDedupePopup(String data) {
		String xpath = String.format("//div[@data-testid='table-body']//following::div[@title='%s']", data);
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.WebIsElementDisplayed(list.get(0), data);

	}

	public void clickOnLeadTodayDate() {
		ReUsableMethods.webClickElement(leadTodayDate, "lead Today Date");
	}

	@FindBy(xpath = "//div[text()='Note']")
	public WebElement note;

	@FindBy(xpath = "//div[@data-autoid='LASTNAME']")
	public WebElement last;

	public void clickOnNote() {
		ReUsableMethods.webClickElement(note, "note");
	}

	@FindBy(xpath = "//span[text()='crmnext coach Active State']")
	public WebElement crmnextCoachActiveState;

	public boolean isCrmnextCoachActiveStateDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(crmnextCoachActiveState, "crmnextCoachActiveState");
	}

	public String generateMobileNumberStartingWith3() {
		return "3" + (int) (Math.random() * 1000000000);
	}

	public String getLastNameValue() {
		return leadLastName.getDomAttribute("value");
	}

	public String getlastName() throws Exception {

		return ReUsableMethods.getTextElementAttribute(lastName, "value");
	}

	public String getSelectedRating() throws Exception {

		return ReUsableMethods.webGetFirstSelectedOption(leadRating);
	}

	public String getSelectedProduct() throws Exception {

		return ReUsableMethods.webGetFirstSelectedOption(product);
	}

	public boolean checkFieldVisibleOnLead(String fieldName) {
		String xpath = String.format("//label[contains(@data-autoid , 'LE_%s')]", fieldName);

		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);

		boolean isVisible = ReUsableMethods.isWebElementDisplayed(list);

		if (isVisible && !list.isEmpty()) {
			WebElement element = list.get(0); // take the first visible element
			ReUsableMethods.scrollElementToCentreOfScreen(element);
		}

		return isVisible;
	}

	public void selectValueInLeadFields(String name, String valueName) {
		String xpath = String.format("//select[contains(@data-autoid, 'LE_') and @name = 'LE_%s']", name, valueName);

		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);

		WebElement ele = list.get(0);

		ReUsableMethods.webSelectByVisibleText(ele, valueName, "ele");
	}

	public boolean isLabelDisplayed_Territory(String title) {
		String xpath = String.format("//label[@title='%s']", title);
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.WebIsElementDisplayed(list.get(0), title);

	}

	public void selectOfferName(String offerName) throws Exception {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(DriverManager.getWdriver());
		ReUsableMethods.scrollElementToCentreOfScreen(offerNameSearchIcon);
		ReUsableMethods.webClickElement(offerNameSearchIcon, "Offer Search icon");
		commonProductFunctions.selectNameFromPicker(offerName);

	}

	

	@FindBy(xpath = "//*[@title='Recently Accessed']")
	public WebElement recentlyAccessedSection;

	@FindBy(xpath = "//*[@title='ToolBox']")
	public WebElement toolBoxSection;

	public void enterOfficePhoneNo(String officePhoneNo) {
		ReUsableMethods.scrollElementToCentreOfScreen(officePhone);
		ReUsableMethods.webEnterText(officePhone, officePhoneNo, "office Phone number");
	}

}
