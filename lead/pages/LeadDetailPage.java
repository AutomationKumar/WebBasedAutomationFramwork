package com.businessnext.objects.lead.pages;

import java.awt.AWTException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.pages.CommonProductFunctions;
import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;
import com.utilities.WebWait;

public class LeadDetailPage {

	WebDriver wdriver;

	public LeadDetailPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//a[@data-autoid='LE_OF_NAME_ctrl']")
	public WebElement offerName;

	@FindBy(xpath = "//span[normalize-space()='Remove From Quick Links']")
	public WebElement removeFromQuickLinks;

	@FindBy(xpath = "//h5[text()='Item Not In Scope']")
	public WebElement notInScope;

	@FindBy(xpath = "//span[normalize-space()='Closed Activities']")
	public WebElement closedActivityTitle;

	// Element Functions

	@FindBy(xpath = "//span[@data-autoid='LE_NAME_ctrl']")
	public WebElement leadName;

	@FindBy(xpath = "//span[@data-autoid='LE_LEADRATING_ctrl']")
	public WebElement leadRating;

	@FindBy(xpath = "//a[@data-autoid='LE_CAMPAIGN_ctrl']")
	public WebElement LeadCampaign;

	@FindBy(xpath = "//a[@data-autoid='Edit_1']")
	public WebElement LeadEditButton;

	@FindBy(xpath = "//*[@data-autoid='audit_0']")
	public WebElement createdBy;

	@FindBy(xpath = "//*[@data-autoid='audit_1']")
	public WebElement lastModifiedBy;

	@FindBy(xpath = "//div[@class='errorMsgText f13 ph2 secondary ff-light']")
	private WebElement accessRuleFiredMessage;

	@FindBy(xpath = "//a[@title='(Show Hierarchy)']")
	private WebElement showHierarchyLink;

	@FindBy(xpath = "//a[@data-autoid='LINK_NEW_LEADLEAD_CHILD0']")
	private WebElement newLeadLink;

	@FindBy(xpath = "(//*[name()='a'][@class='treeNodeTitleLink'])[1]")
	private WebElement leadLinkOnFirstLevel;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-close' ]")
	public WebElement closeIcon;

	@FindBy(xpath = "(//*[name()='a'][@class='treeNodeTitleLink'])[2]")
	private WebElement leadLinkOnSecondLevel;

	@FindBy(xpath = "//div[contains(text(), 'No Record Found')]")
	private WebElement nodataFoundInMultipleHierarchyPopup;

	// tabs in lead page - Task
	@FindBy(xpath = "//span[@data-autoid='tab_2']")
	private WebElement activityTab;

	@FindBy(xpath = "//a[@data-autoid='LINK_NEW_TASKLE_OPEN_ACTIVITIES4']")
	private WebElement newTaskLink;

	@FindBy(xpath = "//a[@data-autoid='Lay_4']")
	private WebElement defaultLayoutTask;

	@FindBy(xpath = "//span[@data-autoid='tab_1']")
	private WebElement detailTab;

	@FindBy(xpath = "//*[text()='Test New']//ancestor::div[@data-testid='slider-item']//div[contains(@class,'lifeEvents__item isActive flex-1 relative')]")
	public WebElement eventStatusOfLeadActive;

	@FindBy(xpath = "//span[contains(@title,'Activities')]")
	private WebElement RelatedActivity_tab;

	@FindBy(xpath = "//span[@title='Related_BigData']")
	public WebElement RelatedBigData_tab;

	@FindBy(xpath = "//a[@title='New']")
	public WebElement newLinkActivity;

	@FindBy(xpath = "//div[@data-autoid='header']//div")
	public WebElement layoutDesigner;

	// @FindBy(xpath = "//a[@title='Log a Call']")
	// public WebElement logACallBtn;

	@FindBy(xpath = "//span[@data-autoid='tab_4']")
	private WebElement contact_tab;

	@FindBy(xpath = "//div[@data-autoid='cust_10993']//div//div")
	private WebElement leadDescriptionValue;

	// @FindBy(xpath = "//div[@data-autoid='LE_DESCRIPTION']//div//div")
	// private WebElement leadDescriptionValue;

	@FindBy(xpath = "//a[@data-autoid='LE_PRODUCT_ctrl']")
	public WebElement product;

	@FindBy(xpath = "//i[@class='dark-gray icon icon-jpg']")
	private WebElement verifyAttachmentIcon;

	@FindBy(xpath = "//a[contains(@data-autoid,'Edit_')]")
	private WebElement editLead;

	@FindBy(xpath = "//*[contains(@data-autoid,'Delete_')]")
	public WebElement Delete;

	@FindBy(xpath = "//a[@data-autoid='0_LINK_DELLEAD_RELATED_SUBSIDIARY0']")
	public WebElement deleteSubsidiary;

	@FindBy(xpath = "//a[contains(@data-autoid,'0_LINK_DELRelated')]")
	public WebElement linkDeleteFirstRelated;

	@FindBy(xpath = "//a[@data-autoid='0_LINK_DELLE_RELATED_NOTES0']")
	public WebElement linkDeleteFirstRelatedNotes;

	@FindBy(xpath = "//*[@data-autoid='MassDelete_0']")
	public WebElement Delete1;

	@FindBy(xpath = "//input[@data-autoid='CTRL_DELETEPERMANENT_ctrl']")
	public WebElement chckboxDltPermanently;

	@FindBy(xpath = "//*[@data-autoid='Close_1']")
	public WebElement Cancel;

	@FindBy(xpath = "//a[@title='Attach New Document']")
	public WebElement attachNewDocumentTab;

	@FindBy(xpath = "(//label[@data-autoid='LE_PRODUCT_lbl'])[2]")
	private WebElement leadProductLabel;

	@FindBy(xpath = "(//label[@class='form-element__label'])[35]")
	private WebElement verificatrionMashupOndetail;

	@FindBy(xpath = "//span[@data-autoid='LE_NAME_ctrl']")
	public WebElement LastName;

	@FindBy(xpath = "//div[@data-autoid='CTRL_CONFIRM']")
	WebElement systemMessageText;

	@FindBy(xpath = "//a[@data-autoid='btn_toggle']")
	private WebElement cardToggleButton;

	@FindBy(xpath = "//label[@data-autoid='LE_LEADRATING_lbl']")
	private WebElement leadRatingLabel;

	@FindBy(xpath = "//label[@data-autoid='LE_NAME_lbl']")
	private WebElement leadNameLabel;

	@FindBy(xpath = "//a[@data-autoid='Delete_2']")
	WebElement deleteLead;

	@FindBy(xpath = "//label[@data-autoid='LE_TITLE_lbl']")
	private WebElement leadTitleLabel;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-plus' and ancestor::*[@data-autoid='button']]")
	private WebElement addTaggedPlaybookButton;

	@FindBy(xpath = "//a[normalize-space()='Add']")
	private WebElement addPlaybook;

	@FindBy(xpath = "(//a[normalize-space()='Play'])[1]")
	private WebElement playButtonPlaybook;

	@FindBy(xpath = "(//a[normalize-space()='Pause'])[1]")
	private WebElement pauseButtonPlaybook;

	@FindBy(xpath = "//input[@data-autoid='EMAILTOID_ctrl']")
	private WebElement sendToEmailPlaybook;

	@FindBy(xpath = "//input[@data-autoid='SUBJECT_ctrl']")
	private WebElement subjectEmailPlaybook;

	@FindBy(xpath = "//a[@data-autoid='SendEmail']")
	private WebElement sendEmailPlaybook;

	@FindBy(xpath = "(//div[contains(text(),'Completed')])[1]")
	private WebElement completeMessagePlaybook;

	@FindBy(xpath = "//span[@data-autoid='cust_11491_ctrl']")
	private WebElement amountWithRange;

	@FindBy(xpath = "//span[@data-autoid='LE_ADDRESS_ctrl']")
	private WebElement getPunjab;

	@FindBy(xpath = "//label[@data-autoid='CTRL_DELETEPERMANENT_lbl']")
	private WebElement permanentDeleteCheckBox;

	@FindBy(xpath = "//button[text()='Update']")
	public WebElement updateDedupeButton;

	@FindBy(xpath = "//button[text()='Compare']")
	public WebElement compareDedupeButton;

	@FindBy(how = How.XPATH, using = "//span[@data-autoid='cust_11449_ctrl']")
	private WebElement Subject;

	@FindBy(how = How.XPATH, using = "//div[@class='discussionThread__msg']//child::*[1]//child::*")
	private WebElement Detail;

	@FindBy(how = How.XPATH, using = "//span[@data-autoid='cust_11447_ctrl']")
	private WebElement EmailTo;

	@FindBy(how = How.XPATH, using = "//span[@data-autoid='cust_11448_ctrl']")
	private WebElement EmailFrom;

	@FindBy(xpath = "(//div[normalize-space()='Steps'])[1]")
	private WebElement verifyStepPlaybook;

	@FindBy(xpath = "(//div[normalize-space()='Progress'])[1]")
	private WebElement verifyProgressPlaybook;

	@FindBy(xpath = "(//i[@class='icon-checked'])[1]")
	private WebElement tagIcon1;

	@FindBy(xpath = "(//i[@class='icon-checked'])[2]")
	private WebElement tagIcon2;

	@FindBy(xpath = "(//div[normalize-space()='NotSpecified'])[1]")
	private WebElement notSpecifiedText;

	@FindBy(xpath = "(//*[text()='Test New']//ancestor::div[@data-testid='slider-item']//div[contains(@class,'lifeEvents__item flex-1 relative')])[1]")
	public WebElement eventStatusOfLeadInActive;

	// @FindBy(xpath = "//a[@title='Mandatoryupdate']")
	// private WebElement mandatoryUpdate;
	@FindBy(xpath = "//a[@data-autoid='None_11']")
	private WebElement MandatoryUpdatebutton;
	@FindBy(xpath = "//a[@data-autoid='None_8']")
	private WebElement screenflowButton;
	@FindBy(xpath = "//a[@data-autoid='None_15']")
	private WebElement approvalActionButton;
	@FindBy(xpath = "//button[@class='acid-btn acid-btn--brand mh-8']")
	private WebElement okIcon;
	@FindBy(xpath = "//button[contains(@class,'footer-button mh-8 min-wt80 mid css-12qlhi0')]")
	public WebElement okConfirmActivateLead;
	@FindBy(xpath = "//a[@data-autoid='None_11']")
	private WebElement updateButton;
	@FindBy(xpath = "(//label[@data-autoid='LE_AMOUNT_lbl'])[2]")
	private WebElement getAlternateLabel;
	@FindBy(xpath = "//span[@data-autoid='LE_ADDRESS_ctrl']")
	private WebElement getAddress;
	// @FindBy(xpath = "//span[@title='Hot']")
	// private WebElement getRatingID;
	@FindBy(xpath = "//span[@data-autoid='LE_LEADRATING_ctrl']")
	private WebElement rating;
	@FindBy(xpath = "(//span[@data-autoid='LE_STATUSCODE_ctrl'])[2]")
	public WebElement statusCode;
	@FindBy(xpath = "//span[@data-autoid='LE_STATUSCODE_ctrl']")
	public WebElement statusCode_Active;
	@FindBy(xpath = "//a[@title='A_Sub']")
	private WebElement getAssignedTo;
	@FindBy(xpath = "//span[@title='Deleted']")
	public WebElement statusCode_Deleted;
	@FindBy(xpath = "//a[@data-autoid='None_9']")
	private WebElement importActionButton;
	@FindBy(xpath = "//a[@data-autoid='None_10']")
	private WebElement massPrintButton;
	@FindBy(xpath = "//a[@data-autoid='None_11']")
	private WebElement assigmentUpdateActionButton;

	@FindBy(xpath = "//a[@data-autoid='Lay_5008']")
	private WebElement newAttachmentLayout;

	@FindBy(xpath = "//a[@data-autoid='LE_LEADOWNER_ctrl']")
	public WebElement user;

	@FindBy(xpath = "//span[@data-autoid='LE_TITLE_ctrl']")
	public WebElement title;

	@FindBy(xpath = "//*[@data-autoid='LE_COMPANY_ctrl']")
	public WebElement company;

	@FindBy(xpath = "//*[@data-autoid='LE_URL_ctrl']")
	public WebElement URL;

	@FindBy(xpath = "//span[@data-autoid='LE_INDUSTRY_ctrl']")
	public WebElement industry;

	@FindBy(xpath = "//span[@data-autoid='LE_EMPLOYEES_ctrl']")
	public WebElement employeeCount;

	@FindBy(xpath = "//span[@data-autoid='LE_LEADSOURCE_ctrl']")
	public WebElement leadSource;

	@FindBy(xpath = "//span[@data-autoid='tab_5']")
	private WebElement relatedAttachment;

	@FindBy(xpath = "//span[@data-autoid='LE_ASSIGNTO_ctrl']")
	private WebElement getAssignedTo_Queues;

	@FindBy(xpath = "//span[@title='Auto_Queue']")
	private WebElement getOwner_AutoQueues;

	@FindBy(xpath = "//span[@data-autoid='LE_ADDRESS_ctrl']")
	private WebElement adressCurrentStatus;

	@FindBy(xpath = "//div[@class='print-container']")
	private WebElement templateStatus;

	@FindBy(xpath = "//span[@data-autoid='LE_AMOUNT_ctrl']")
	public WebElement getAmount;

	@FindBy(xpath = "//span[@data-autoid='LE_PREFERRED_ctrl']")
	public WebElement prefferedChanel;

	@FindBy(xpath = "//a[@data-autoid='LE_ASSIGNTO_ctrl']")
	public WebElement verifyAssignTo;

	@FindBy(xpath = "//a[@data-autoid='LE_TERRITORY_ctrl']")
	public WebElement verifyTerretory;

	@FindBy(xpath = "//span[@data-autoid='LE_ADDRESS_ctrl']")
	public WebElement leadAddress;

	@FindBy(xpath = "//span[@data-autoid='LE_MOBILE_ctrl']")
	public WebElement leadMobile;

	@FindBy(xpath = "//span[@data-autoid='LE_FAX_ctrl']")
	public WebElement leadFAX;

	@FindBy(xpath = "//*[@data-autoid='LE_EMAIL_ctrl']")
	public WebElement leadEmail;

	@FindBy(xpath = "//*[@data-autoid='LE_DESCRIPTION']/div/div")
	public WebElement leadDescription;

	@FindBy(xpath = "//*[@title='Related History']")
	public WebElement relatedHistoryTab;

	@FindBy(xpath = "//*[@data-autoid='StatusCode_0']")
	public WebElement relatedHistoryStatusCodeFirst;

	@FindBy(xpath = "//*[@data-autoid='StatusCode_1']")
	public WebElement relatedHistoryStatusCodeSecond;

	@FindBy(xpath = "//*[@data-autoid='LastName_0']")
	public WebElement relatedHistoryLastNameFirst;

	//
	@FindBy(xpath = "//*[@data-autoid='Name_0']")
	public WebElement relatedNameFirst;
	// StatusCode_0

	@FindBy(xpath = "//span[@data-autoid='Band_lastmodifiedon_ctrl']")
	private WebElement timeZone;

	@FindBy(xpath = "//img[@title='hdfc.png']")
	private WebElement fileUpload;

	@FindBy(xpath = "//a[@data-autoid='Subject_0']")
	private WebElement taskValue;

	@FindBy(xpath = "//a[@data-autoid='btn_toggle']")
	private WebElement card;

	@FindBy(xpath = "//h6[@title='Streams']")
	private WebElement socialCard;

	@FindBy(xpath = "//*[@class='deleteIcon icon']")
	private WebElement delete_socialCard;

	// @FindBy(xpath = "//*[@class='deleteIcon icon']")
	// private WebElement delete_socialCard;

	@FindBy(xpath = "//span[@data-autoid='LE_NAME_ctrl']")
	public WebElement lead_lastName;

	@FindBy(xpath = "//span[@data-autoid='CON_NAME_ctrl']")
	private WebElement contact_lastName;

	@FindBy(xpath = "//span[@data-autoid='CASE_SUBJECT_ctrl']")
	private WebElement case_lastName;

	@FindBy(xpath = "//span[contains(@title,'Activit')]")
	public WebElement reatedActivity_Tab;

	@FindBy(xpath = "//a[@title='Compose Email']")
	public WebElement composeEmail;

	@FindBy(xpath = "//a[@data-autoid='Subject_0']")
	private WebElement subject_ClosedActivity;

	@FindBy(xpath = "//*[@data-autoid='0_AI']//div")
	private WebElement threedots;

	@FindBy(xpath = "//a[normalize-space()='Forward']")
	private WebElement forwardButton;

	@FindBy(xpath = "//a[normalize-space()='Reply']")
	private WebElement replyButton;

	@FindBy(xpath = "//a[normalize-space()='Reply All']")
	private WebElement replyAllButton;

	@FindBy(xpath = "(//span[@data-autoid='LE_NUMBER_ctrl'])[1]")
	public WebElement leadID;

	@FindBy(xpath = "//a[@data-autoid='button']//i")
	private WebElement pauseButton;

	@FindBy(xpath = "//div[@data-testid='loader-bn']")
	private WebElement loader;

	@FindBy(xpath = "//*[@title='Close']")
	public WebElement closeButton;

	@FindBy(xpath = "//*[@title='Disqualify']")
	public WebElement disqualifyButton;

	@FindBy(xpath = "//*[@title='Activate']")
	public WebElement activateBtn;

	@FindBy(xpath = "//select[@data-autoid='Lookup1Id_ctrl']")
	public WebElement drpDwnReasonDisQualify;

	@FindBy(xpath = "//*[@data-autoid ='Description1_ctrl']")
	public WebElement disqualifyDiscription;

	@FindBy(xpath = "//*[contains(@data-autoid,'Disqualify')]")
	public WebElement disqualifyLeadBtn;

	@FindBy(xpath = "//*[@title='Revise']")
	public WebElement reviseButton;

	@FindBy(xpath = "//*[@title='Contact']//ancestor::div[@data-testid='card-root']")
	public WebElement contactCard;

	@FindBy(xpath = "//input[@title='File Input']")
	private WebElement uploadFile;

	@FindBy(xpath = "//span[@title='Attachments']")
	public WebElement attachments_DMS;

	@FindBy(xpath = "//a[@title='DMS Binary']")
	public WebElement dMSBinary;

	@FindBy(xpath = "//a[@data-autoid='DM_FolderName_srch']")
	public WebElement dmsBinaryFolderPicker;

	@FindBy(xpath = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	public WebElement dmsBinaryFolderSearchBox;

	@FindBy(xpath = "//div[contains(text(),'Apply')]")
	public WebElement dmsBinaryApplyButton;

	@FindBy(xpath = "(//div[@data-toggle='tooltip'])[1]")
	public WebElement firstSecordAfterSearch;

	@FindBy(xpath = "//a[@title='BulkDetail.pdf']")
	private WebElement pdfUploaded;

	@FindBy(xpath = "//a[@data-autoid='Save']")
	public WebElement saveButton;

	@FindBy(xpath = "(//*[local-name()='svg' and @name='icon-custom-menu'])[5]//parent::div")
	public WebElement threeIcon;

	@FindBy(xpath = "//a[@title='View']")
	public WebElement viewIcon;

	@FindBy(xpath = "(//div[starts-with(@title, 'BulkDetail.pdf')])[2]")
	public WebElement fileUploaded_ViewIcon;

	@FindBy(xpath = "//span[contains(text(), 'Sample Watermark')]")
	public WebElement sampleTextWatermark;

	@FindBy(xpath = "//div[@class='react-pdf__Page']")
	public WebElement pdfWatermark;

	@FindBy(xpath = "(//div[@data-autoid='button' and @type='action']//span)")
	public WebElement actionButton;

	@FindBy(xpath = "//*[@data-autoid='button' and @type='action']")
	public WebElement action;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-cross']")
	public WebElement crossIcon_watermark;

	@FindBy(xpath = "//span[@data-autoid='cust_11915_ctrl' and normalize-space()='********']")
	public WebElement advanceEncryptionEmailEncrypt_detail;

	@FindBy(xpath = "//span[@data-autoid='cust_11915_ctrl' or contains(text(), '.com')]")
	public WebElement advanceEncryptionEmailPlainText_detail;

	@FindBy(xpath = "//span[@data-autoid='cust_11913_ctrl' and normalize-space()='********']")
	public WebElement advanceEncryptionTextEncrypt_detail;

	@FindBy(xpath = "//span[@data-autoid='cust_11913_ctrl' or contains(text(), 'text')]")
	public WebElement advanceEncryptionTextPlainText_detail;

	@FindBy(xpath = "//div[@data-autoid='Lea_ex2_66_0' or contains(text(), 'text')]")
	public WebElement advanceEncryptionTextPlainText_History;

	@FindBy(xpath = "//span[contains(text(), '.com')]")
	public WebElement advanceEncryptionEmailPlainText_History;

	@FindBy(xpath = "//span[@data-autoid='LE_PANNUMBER_ctrl' and normalize-space()='********']")
	public WebElement panNumber_detail;

	@FindBy(xpath = "//span[contains(@data-autoid, 'cust_11916') and contains(text(), '********')]")
	public WebElement regularExpressionEncrypt_detail;

	@FindBy(xpath = "//span[@data-autoid='LE_PANNUMBER_ctrl' or contains(text(), '4L')]")
	public WebElement panNumberPlainText_detail;

	@FindBy(xpath = "//div[@data-autoid='Lea_ex2_66_0' and @title='********']")
	public WebElement advanceEncryptionTextEncrypt;

	@FindBy(xpath = "//a[@title='********']")
	public WebElement advanceEncryptionEmailEncrypt;

	@FindBy(xpath = "//span[@title='History']")
	public WebElement history;

	@FindBy(xpath = "//a[@data-autoid='StatusCode_0']")
	public WebElement leadStatusCode;

	@FindBy(xpath = "//h6[@title='Dedupe Rule Fired']")
	private WebElement dedupeRuleFired;

	@FindBy(xpath = "//a[@title='Clone']")
	private WebElement clone;

	@FindBy(xpath = "//a[@title='Call Script']")
	public WebElement callScript;

	@FindBy(xpath = "//button[contains(text(),'Ignore And Create')]")
	public WebElement dedupeIgnoreAndCreate;

	@FindBy(xpath = "//input[@name='processId']")
	public WebElement lead_VRadioButton;

	@FindBy(xpath = "//label[text()='Lead_V - 1.0 ']")
	public WebElement lead_V;

	@FindBy(xpath = "//label[contains(@data-autoid,'sec_')]//child::span[contains(text(),'Closed Activities')]")
	public WebElement closedActivitySection;

	@FindBy(xpath = "//input[@value='save']")
	public WebElement selectProcessSave;

	@FindBy(xpath = "//a[@title='Toggle to Card View']")
	public WebElement cardToggle;

	@FindBy(xpath = "//h6[@title='Contact']/following-sibling::*//*[@data-autoid='Card_100091_h_act']")
	public WebElement newContactButtonOnCard;

	@FindBy(xpath = "//a[@title='New Contact']")
	public WebElement newContactLink;

	@FindBy(xpath = "//h1[normalize-space()='Print']")
	public WebElement printHeading;

	@FindBy(xpath = "//span[normalize-space()='Contact_System']")
	public WebElement contactSystem;

	@FindBy(xpath = "//button[text()='Update/Create']")
	public WebElement updateCreateButton;

	@FindBy(xpath = "(//*[@data-autoid='0_LastName_val'])[2]")
	public WebElement contactNameOnCard;

	@FindBy(xpath = "//div[@data-autoid='Lst_Review_ctrl']")
	public WebElement relatedReviewType;

	@FindBy(xpath = "//label[@data-autoid='LE_FAX_lbl']")
	public WebElement faxLabel;

	public void SelectReviewType(String reviewtype) {
		ReUsableMethods.webSelectByVisibleText(relatedReviewType, reviewtype, "Review Type is clicked");
	}

	@FindBy(xpath = "//a[@data-autoid = 'LE_COMPANY_ctrl']")
	public WebElement companyName;

	@FindBy(xpath = "//*[@data-autoid = 'CTRL_CURRENCY_ctrl']")
	public WebElement currency;

	@FindBy(xpath = "//a[@title='Merge Issue']")
	public WebElement mergeIssue;

	public String getContactNameOnCard() {
		return ReUsableMethods.WebGetElementText(contactNameOnCard, "Contact card name");
	}

	@FindBy(xpath = "//*[normalize-space(text())='Event Planner']")
	public WebElement eventPlannerCard;

	@FindBy(xpath = "//*[normalize-space(text())='Parent Record Event Planner']")
	public WebElement parentRecordEventPlannerCard;

	@FindBy(xpath = "//a[contains(@data-autoid, 'ScreenFlowJourney')]")
	public WebElement screenFlowButton;

	@FindBy(xpath = "//*[@data-autoid='0_AI']")
	public WebElement threeDot;

	@FindBy(xpath = "//div[normalize-space()='Edit']")
	public WebElement edit;

	@FindBy(xpath = "//*[@data-autoid='Subject_0']")
	public WebElement subjectForActivities;

	@FindBy(xpath = "//a[@title='Print']")
	public WebElement printIcon;

	@FindBy(xpath = "//span[@data-autoid='LE_LEADRATING_ctrl']")
	private WebElement getRatingID;

	public void clickButtonByText(String text) {
		String xpath = "//span[normalize-space()='" + text + "']";
		WebElement ele = ReUsableMethods.findElementByPath(xpath).get(0);
		ReUsableMethods.webClickElement(ele, text);
	}

	@FindBy(xpath = "//div[contains(text(),'CTI code is currently inactive. Please contact your CRMnext application admin.')]")
	public WebElement inactiveCTI;

	@FindBy(xpath = "//a[@title='Knowledge']")
	public WebElement knowledgeButton;

	public void clickOnScreenFlowButton() {

		ReUsableMethods.webClickElement(screenFlowButton, "ScreenFlow");
	}

	public boolean getLead_VProcess() {
		return ReUsableMethods.WebIsElementDisplayed(lead_V, "Lead_V - 1.0");
	}

	public void switchToActivityTab() throws InterruptedException {
		ReUsableMethods.webClickElement(activityTab, "Activity tab");
	}

	public void switchToDetailTab() {

		ReUsableMethods.webClickElement(detailTab, "Details tab");
	}

	public void switchToContactTab() {

		ReUsableMethods.webClickElement(contact_tab, "Contact tab");
	}

	public void hoverOverNewTaskLink() {
		ReUsableMethods.webClickElement(newTaskLink, "New Task Link");
	}

	public void clickOnDefaultLayout() throws InterruptedException {
		ReUsableMethods.webClickElement(defaultLayoutTask, "DEfault Layout");
		//
		ReUsableMethods.switchToChildWindowHandle();
	}

	public void clickOnLayout(String layout) {
		String baseXpath = "//a[@title='LAYOUT']";
		String actualXpath = baseXpath.replace("LAYOUT", layout);
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "LAYOUT");
		ReUsableMethods.switchToChildWindowHandle();

	}

	public String getLeadName() {
		String Actual_LeadName = ReUsableMethods.WebGetElementText(leadName, "Lead Name");
		return Actual_LeadName;
	}

	public void waitforEditButton() {
		WebWait.fluentWaitForDisplayed(LeadEditButton);
	}

	public void clickOnEditButton() {
		ReUsableMethods.webMoveToElement(LeadEditButton, "Edit Button");
		ReUsableMethods.webClickElement(LeadEditButton, "Lead Edit Button");
	}

	public Boolean verifyOnAccessRuleFiredMessage(String message) {
		String baseXpath = "//div[contains(text(),'MESSAGE')]";
		String actualXpath = baseXpath.replace("MESSAGE", message);

		return ReUsableMethods.WebIsElementDisplayed(wdriver.findElement(By.xpath(actualXpath)), "Rule");
	}

	public void clickOnShowHierarchy() {
		ReUsableMethods.webClickElement(showHierarchyLink, "Show Hierarchy Link");
	}

	public void hoverOverNewLeadLinkFromDetailPage() {
		ReUsableMethods.webClickElement(newLeadLink, "New Lead Link");
		ReUsableMethods.switchToChildWindowHandle();
	}

	public String getLeadName_OnFirstLevel() {
		return ReUsableMethods.WebGetElementText(leadLinkOnFirstLevel, "Parent Lead Name");
	}

	public String getLeadName_OnSecondLevel() {

		return ReUsableMethods.WebGetElementText(leadLinkOnSecondLevel, "Second Child");

	}

	public void clickOnLeadNameOnSecondLevel() {
		ReUsableMethods.webClickElement(leadLinkOnSecondLevel, "Lead On Second level");

	}

	public boolean verifyIfHierarchyIsConfiguredOnDetailPage(String heirrarchyName) {
		String basicXpath = "//label[@title='HIERARCHYNAME']";
		String actualXpath = basicXpath.replace("HIERARCHYNAME", heirrarchyName);
		return ReUsableMethods.WebIsElementDisplayed(wdriver.findElement(By.xpath(actualXpath)), "Hierarchy");
	}

	public void clickOnShowHierarchy_Multiple(String heirrarchyName) {
		String basicXpath = "//label[@title='HIERARCHYNAME']/following-sibling::*/a";
		String actualXpath = basicXpath.replace("HIERARCHYNAME", heirrarchyName);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));

		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Hierarchy");
	}

	public boolean checkIfNoDataFoundElementIsPresentInMultipleHierarchyPopup() {
		return ReUsableMethods.WebIsElementDisplayed(nodataFoundInMultipleHierarchyPopup, "No data Found ELement");
	}

	public boolean verifyIfObjectisPresentOnMultipleHierarchyPopup(String hierarchyName) {
		String basicXpath = "(//*[name()='text'][contains(text(),'HierarchyName')])[1]";
		String actualXpath = basicXpath.replace("HierarchyName", hierarchyName);
		return ReUsableMethods.WebIsElementDisplayed(wdriver.findElement(By.xpath(actualXpath)),
				"Object ON Hierarchy Popup");
	}

	public String getLeadDescription() {
		String actualDescription = ReUsableMethods.WebGetElementText(leadDescriptionValue, "Lead Description");
		return actualDescription;
	}

	public String getLastName() {

		return ReUsableMethods.WebGetElementText(LastName, "Last name");
	}

	public String getCurrency() {

		return ReUsableMethods.WebGetElementText(currency, "currency");
	}

	public String getCompanyName() {

		return ReUsableMethods.WebGetElementText(companyName, "company name");
	}

	public String getProduct() {
		return ReUsableMethods.WebGetElementText(product, "Lead Product");
	}

	public void editLead() {
		ReUsableMethods.scrollElementToCentreOfScreen(editLead);
		ReUsableMethods.webClickElement(editLead, "edit  lead page");
	}

	public void DeleteBotton() {
		ReUsableMethods.webClickElement(Delete, " Delete");
		ReUsableMethods.switchToChildWindowHandle();
	}

	public boolean Delete1Botton() throws InterruptedException {
		//
		try {
			ReUsableMethods.webClickElement(Delete1, "delete1");
			return false;
		} catch (Exception e) {
			return true;
		}

	}

	public void CancelBotton() {
		ReUsableMethods.webClickElement(Cancel, "click on cancel");
	}

	public String getMashupBodyOnDetailPage() throws InterruptedException {
		//
		ReUsableMethods.scrollDownToElement(verificatrionMashupOndetail);
		return ReUsableMethods.WebGetElementText(verificatrionMashupOndetail, "get the elmenet on detail");
	}

	public void clickOnCardToggleButton() throws InterruptedException {

		ReUsableMethods.webClickElement(cardToggleButton, "Card Toggle Button");
	}

	@FindBy(xpath = "//a[@title='Toggle to Detail View']")
	public WebElement detailToggleButton;

	public void clickOnDetailToggleButton() {

		ReUsableMethods.webClickElement(detailToggleButton, "Detail Toggle Button");
	}

	public void clickOnAddPlaybookButton() throws InterruptedException {

		ReUsableMethods.webClickElement(addTaggedPlaybookButton, "Add Tagged PLaybook");
	}

	public boolean verifyIfPlaybookVisibleOnDetailPage(String playbookName) {
		try {
			String basicXpath = "//div[normalize-space()='PLAYBOOKNAME']";
			String actualXpath = basicXpath.replace("PLAYBOOKNAME", playbookName);
			List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);

			return ReUsableMethods.isWebElementDisplayed(list);
		} catch (Exception e) {
			return false;
		}

	}

	public void addTaggedPlaybookOnDetailPage(String playBook) throws InterruptedException, AWTException {
		String basicXpath = "//div[normalize-space()='PLAYBOOKNAME']";
		String actualXpath = basicXpath.replace("PLAYBOOKNAME", playBook);
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Playbook");
		ReUsableMethods.webClickElement(addPlaybook, "Add");
		//
	}

	public void addMultipleTaggedPlaybookOnDetailPage() throws InterruptedException {
		ReUsableMethods.webClickElement(tagIcon1, "Icon 1");
		ReUsableMethods.webClickElement(tagIcon2, "Icon 2");
		ReUsableMethods.webClickElement(addPlaybook, "Add");
		//
	}

	public void clickOnPlayButtonPlaybook() throws InterruptedException {
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(playButtonPlaybook, "Playbook");
	}

	public void clickOnPauseButtonPlaybook() throws InterruptedException {
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(pauseButtonPlaybook, "Playbook");
	}

	public void clickOnStepNamePlaybookOnDetailPage(String stepName) throws InterruptedException {
		Thread.sleep(1000);
		String basicXpath = "//a[@title='Step']";
		String actualXpath = basicXpath.replace("Step", stepName);
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Playbook");
		//
	}

	public void sendEmailForPlaybookCard(String emailTo, String emailSubject) throws InterruptedException {
		ReUsableMethods.webEnterText(sendToEmailPlaybook, emailTo, "Email To");
		ReUsableMethods.scrollDownToElement(subjectEmailPlaybook);
		ReUsableMethods.webClearText(subjectEmailPlaybook, "Clear");
		ReUsableMethods.webEnterText(subjectEmailPlaybook, emailSubject, "Subject");
		ReUsableMethods.webClickElement(sendEmailPlaybook, "Send EMail");
		waitForLoader();
	}

	public boolean isCompleteStatusVisible() {
		return ReUsableMethods.WebIsElementDisplayed(completeMessagePlaybook, "Complete Message");
	}

	public boolean verifyReadOnlyEmailFields() {
		return sendToEmailPlaybook.getDomAttribute("readonly").equals("true");

	}

	public boolean isClosedActivityPresentOnDetailPage(String subject) {
		try {
			String basicXpath = "//a[normalize-space()='PLAYBOOKNAME']";
			String actualXpath = basicXpath.replace("PLAYBOOKNAME", subject);
			return ReUsableMethods.WebIsElementDisplayed(wdriver.findElement(By.xpath(actualXpath)), "Activity");
		} catch (Exception e) {
			return false;
		}

	}

	public String verifyOnleadNameLabel() {
		return ReUsableMethods.WebGetElementText(leadNameLabel, "Lead Name Label");
	}

	public String verifyOnleadTitleLabel() {
		return ReUsableMethods.WebGetElementText(leadTitleLabel, "Lead Title Label");
	}

	public String verifyOnleadProductLabel() {
		return ReUsableMethods.WebGetElementText(leadProductLabel, "Lead Product Label");
	}

	public void clickOnDeleteButton_Lead() throws InterruptedException {
		ReUsableMethods.webClickElement(deleteLead, "Delete Lead");
		//
	}

	public String verifySystemMessageText() {
		return ReUsableMethods.WebGetElementText(systemMessageText, "System Message Text");
	}

	public String verifyOnleadRatingLabel() {
		return ReUsableMethods.WebGetElementText(leadRatingLabel, "Lead Rating Label");
	}

	public String removeAlphabetsFromHindiString() {

		String str = "आखिरीबार Mr. Automation_All1 दढ़वारा 27/02/2023 11:32 PM परदेखागयाथा।";

		str = str.replaceAll("[a-zA-Z]", "");
		str = str.replaceAll("[0-9]", "");
		str = str.replaceAll("[.,:,/,_]", "");
		str = str.replaceAll("\\s", "");

		System.out.println(str);
		return str;

	}

	public String getAmountWithRange() {
		return ReUsableMethods.WebGetElementText(amountWithRange, "amountWithRange");
	}

	public boolean verifyPlayPauseDetailPageTags() {
		if (ReUsableMethods.WebIsElementDisplayed(verifyStepPlaybook, "Steps Text")) {
			return ReUsableMethods.WebIsElementDisplayed(verifyProgressPlaybook, "Progress Text");
		} else {
			return false;
		}

	}

	public String getLeadsubject() {

		return ReUsableMethods.WebGetElementText(Subject, "Subject");
	}

	public String getLeaddetail() {

		return ReUsableMethods.WebGetElementText(Detail, "Detail");
	}

	public String getLeademail_to() {

		return ReUsableMethods.WebGetElementText(EmailTo, "EmailTo");
	}

	public String getLeademail_from() {

		return ReUsableMethods.WebGetElementText(EmailFrom, "EmailFrom");
	}

	public void clickOnRelatedActivityTab() throws InterruptedException {
		ReUsableMethods.webClickElement(RelatedActivity_tab, "click on Related Activity Tab");
		Thread.sleep(5000);
	}

	@FindBy(xpath = "//a[@data-autoid='Subject_0']")
	private WebElement getFirstActivityName;

	public String getFirstActivityName() throws InterruptedException {
		return ReUsableMethods.WebGetElementText(getFirstActivityName, "getFirstActivityName");
	}

	@FindBy(xpath = "//div[@data-autoid='AssignedToName_0']")
	private WebElement FirstAssignTo;

	public String getFirstAssignTo() throws InterruptedException {
		return ReUsableMethods.WebGetElementText(FirstAssignTo, "FirstAssignTo");
	}

	public String getDescriptionValue() {
		return ReUsableMethods.WebGetElementText(leadDescription, "get description value");
	}

	public String getAddressValue() {
		ReUsableMethods.scrollDownToElement(getAddress);
		return ReUsableMethods.WebGetElementText(getAddress, "get address value");
	}

	public void clickOnUpdateActionButton_UsingMandatoryField(String updateButtonName) throws InterruptedException {
		String basicXpath = "//div[normalize-space()='button']";
		String actualXpath = basicXpath.replace("x", updateButtonName);
		ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Custom Action button");
		//
	}

	public void clickOnScreenTypeButton() {
		ReUsableMethods.scrollDownToElement(screenflowButton);
		ReUsableMethods.webClickElement(screenflowButton, "click on reoort type button");
		// ReUsableMethods.switchToChildWindowHandle();
	}

	public void clickOnApprovalActionButton(String approvalButtonName) throws InterruptedException {
		String basicXpath = "//div[normalize-space()='button']";
		String actualXpath = basicXpath.replace("button", approvalButtonName);
		List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
		WebElement ele = list.get(0);
		ReUsableMethods.webMoveToElement(ele, approvalButtonName);
		ReUsableMethods.webClickElement(ele, "Custom Action button");
	}

	public void clickOnOkButton() throws InterruptedException {
		ReUsableMethods.webClickElement(okIcon, "click on okk icpon");
		ReUsableMethods.switchToChildWindowHandle();
		//
	}

	public void clickOnItegrationActionButton(String integration) throws InterruptedException {
		String basicXpath = "//div[normalize-space()='button']";
		String actualXpath = basicXpath.replace("button", integration);
		List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
		WebElement ele = list.get(0);
		ReUsableMethods.webMoveToElement(ele, integration);
		ReUsableMethods.webClickElement(ele, "Custom Action button");
	}

	// public void clickOnUpdateActionButton(String updateButtonName) throws
	// InterruptedException {
	// // ReUsableMethods.scrollElementToCentreOfScreen(closeButton);
	//// ReUsableMethods.WebIsElementDisplayed(LeadEditButton, "Edit Button");
	//// ReUsableMethods.webMoveToElement(LeadEditButton, "Edit Button");
	// String basicXpath = "//a[@title='x']";
	// String actualXpath = basicXpath.replace("x", updateButtonName);
	// ReUsableMethods.webMoveToElement(wdriver.findElement(By.xpath(actualXpath)),
	// updateButtonName);
	// ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)),
	// "Custom Action button");
	// //
	// }

	public void clickOnUpdateActionButton(String updateButtonName) {
		String basicXpath = "//div[normalize-space()='button']";
		String actualXpath = basicXpath.replace("button", updateButtonName);
		List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
		WebElement ele = list.get(0);
		ReUsableMethods.webMoveToElement(ele, updateButtonName);
		ReUsableMethods.webClickElement(ele, "Custom Action button");
	}

	public boolean verifyCurrentStatus_Address() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(getAddress, "Progress Status");
	}

	public boolean verifyCurrentStatus_RatingId() throws InterruptedException {
		ReUsableMethods.waitUntilTextIsPresent(getRatingID, "Hot");
		ReUsableMethods.scrollElementToCentreOfScreen(getRatingID);
		return ReUsableMethods.WebIsElementDisplayed(getRatingID, "Rating ID");
	}

	public boolean verifyCurrentStatus_Rating() throws InterruptedException {
		ReUsableMethods.waitUntilTextIsPresent(rating, "Cold");
		ReUsableMethods.scrollElementToCentreOfScreen(rating);
		return ReUsableMethods.WebIsElementDisplayed(rating, "Rating ID");
	}

	public String getCurrent_StatusCode() {

		return ReUsableMethods.WebGetElementText(statusCode, "Status code");
	}

	public boolean verifyCurrentStatus_Address_massPrint() throws InterruptedException {
		//
		ReUsableMethods.scrollElementToCentreOfScreen(adressCurrentStatus);
		return ReUsableMethods.WebIsElementDisplayed(adressCurrentStatus, "Progress Status");
	}

	public boolean verifyCurrentStatus_Template() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(templateStatus, "Progress Status");
	}

	public boolean getCurrentStatus_StatusCode() {

		return ReUsableMethods.WebIsElementDisplayed(statusCode_Active, "Status code");
	}

	public String get_StatusCode() {

		return ReUsableMethods.WebGetElementText(statusCode_Active, "Status Code");
	}

	public boolean verifyCurrentStatus_AssidnedTo() throws InterruptedException {
		//
		ReUsableMethods.scrollDownToElement(getAssignedTo);
		return ReUsableMethods.WebIsElementDisplayed(getAssignedTo, "Progress Status");
	}

	public boolean verifyCurrentStatusgetAssignedTo_Queues() throws InterruptedException {
		//
		ReUsableMethods.scrollDownToElement(getAssignedTo_Queues);
		return ReUsableMethods.WebIsElementDisplayed(getAssignedTo_Queues, "Progress Status");
	}

	public boolean verifyCurrentStatusgetAssignedTo_Auto_Queues() throws InterruptedException {
		//
		ReUsableMethods.scrollDownToElement(getOwner_AutoQueues);
		return ReUsableMethods.WebIsElementDisplayed(getOwner_AutoQueues, "Progress Status");
	}

	public void clickOnImportActionButton(String importButtonName) throws InterruptedException {
		String basicXpath = "//div[normalize-space()='button']";
		String actualXpath = basicXpath.replace("button", importButtonName);
		List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
		WebElement ele = list.get(0);
		ReUsableMethods.webMoveToElement(ele, importButtonName);
		ReUsableMethods.webClickElement(ele, "Custom Action button");
		ReUsableMethods.WebIsElementDisplayed(uploadFile, "upload file visible");
	}

	public void clickOnMassPrintActionButton(String massPrintButtonName) throws InterruptedException {
		String basicXpath = "//div[normalize-space()='button']";
		String actualXpath = basicXpath.replace("button", massPrintButtonName);
		List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
		WebElement ele = list.get(0);
		ReUsableMethods.webMoveToElement(ele, massPrintButtonName);
		ReUsableMethods.webClickElement(ele, "Custom Action button");
		ReUsableMethods.switchToChildWindowHandle();
		//
	}

	// public void clickOnUpdateActionButton_UsingAssignmentRule(String
	// updateButtonName) throws InterruptedException {
	// ReUsableMethods.scrollElementToCentreOfScreen(closeButton);
	// String basicXpath = "//a[@title='x']";
	// String actualXpath = basicXpath.replace("x", updateButtonName);
	// ReUsableMethods.waitUntilTextIsPresent(actualXpath, "action button");
	// ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)),
	// "Custom Action button");
	// //
	// }

	public void clickOnUpdateActionButton_UsingAssignmentRule(String updateButtonName) {

		// ReUsableMethods.scrollElementToCentreOfScreen(closeButton);

		WebElement actionButton = wdriver.findElement(
				By.xpath("//div[normalize-space()='" + updateButtonName + "']"));

		ReUsableMethods.waitUntilTextIsPresent(actionButton, updateButtonName);

		ReUsableMethods.webClickElement(actionButton,
				updateButtonName + " Custom Action Button");
	}

	// public void clickOnImportActionButtonName(String importButton) throws
	// InterruptedException {
	// Thread.sleep(1000);
	// String basicXpath = "//span[normalize-space()='import']";
	// String actualXpath = basicXpath.replaceAll("import", importButton);
	// ReUsableMethods.scrollDownToElement(wdriver.findElement(By.xpath(actualXpath)));
	// ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)),
	// "Custom Action button");
	// //
	// }
	//
	public boolean verifyCurrentStatus_User() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(user, "Progress Status");
	}

	public String getUserValue() {
		return ReUsableMethods.WebGetElementText(user, "user");
	}

	public boolean verifyCurrentStatus_Amount() throws InterruptedException {

		return ReUsableMethods.WebIsElementDisplayed(getAmount, "Progress Status");
	}

	public String getAddignedToUser_UsingQueues() throws InterruptedException {

		ReUsableMethods.scrollDownToElement(getAssignedTo_Queues);
		return ReUsableMethods.WebGetElementText(getAssignedTo_Queues, "user");
	}

	public boolean verifyIsButtonExist(String buttonParam) {
		// ReUsableMethods.scrollElementToCentreOfScreen(closeButton);
		try {
			String baseXpath = "//button[@title='BUTTONNAME']";
			String actualXpath = baseXpath.replace("BUTTONNAME", buttonParam);
			WebElement element = ReUsableMethods.findElementByPath(actualXpath).get(0);
			return ReUsableMethods.WebIsElementDisplayed(element, "Action Button");
		} catch (Exception e) {
			return false;
		}

	}

	public boolean verifyAssignTo_OnDetailPage() throws InterruptedException {
		//
		ReUsableMethods.scrollDownToElement(verifyAssignTo);
		return ReUsableMethods.WebIsElementDisplayed(verifyAssignTo, "Progress Status");
	}

	public void clickOnRelatedAttachment() {
		ReUsableMethods.webClickElement(relatedAttachment, "RelatedAttachment");
	}

	public void clickOnAttachNewDocument() {
		ReUsableMethods.webClickElement(attachNewDocumentTab, "attachNewDocumentTab");
	}

	public void clickOnNewAttachmentLayout(String parameter) {
		ReUsableMethods.webClickElement(newAttachmentLayout, "newAttachmentLayout");
		ReUsableMethods.switchToChildWindowHandle();
	}

	public String verifyAttachmentIcon() {
		return ReUsableMethods.WebGetElementText(verifyAttachmentIcon, "verifyAttachmentIcon");
	}

	public boolean verifyCurrentStatusTimeZone() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(timeZone, "time zone");
	}

	public boolean verifyFileUploaded() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(fileUpload, "time zone");
	}

	public String getTaskSubject() {
		return ReUsableMethods.WebGetElementText(taskValue, "task subject");
	}

	public void clickOnSocialCard_DetailPage() throws InterruptedException {
		ReUsableMethods.webClickElement(card, "card");
		//
	}

	public boolean verifySocialCard_DetailPage() throws InterruptedException {
		ReUsableMethods.scrollDownToElement(socialCard);
		//
		return ReUsableMethods.WebIsElementDisplayed(socialCard, "time zone");
	}

	public String getLeadNameForLeadObject() {
		return ReUsableMethods.WebGetElementText(lead_lastName, "last name");
	}

	public String getTitle() {
		return ReUsableMethods.WebGetElementText(title, "Title");
	}

	public String getLeadNameForContactObject() {
		return ReUsableMethods.WebGetElementText(contact_lastName, "last name");
	}

	public String getSubjectForCaseObject() {
		return ReUsableMethods.WebGetElementText(case_lastName, "last name");
	}

	public String getLeadID() {
		return ReUsableMethods.WebGetElementText(leadID, "last name");
	}

	public String removeLeadingZeros(String value) {
		return value.replaceFirst("^0+(?!$)", "");
	}

	// public String getLeadID() throws InterruptedException {
	// //
	// String a=leadID.getDomAttribute("Value");
	// return a;
	// }
	public void clickOnPemanentDeleteCheckBox() throws InterruptedException {
		ReUsableMethods.webClickElement(permanentDeleteCheckBox, "checkbox");
		//
	}

	public void clickOnRelatedActivity_Tab() throws InterruptedException {
		ReUsableMethods.webClickElement(reatedActivity_Tab, "Tab");
		//
	}

	public void clickOnComposeEmail() throws InterruptedException {
		ReUsableMethods.webClickElement(composeEmail, "click on manage tag");
	}

	public boolean verifyComposeEmail_OnDetailPage() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(composeEmail, "time zone");
	}

	public void clickOnSubject_ClosedActivities() throws InterruptedException {
		Thread.sleep(9000);
		// ReUsableMethods.scrollDownToElement(subject_ClosedActivity);
		//
		ReUsableMethods.scrollElementToCentreOfScreen(subject_ClosedActivity);
		ReUsableMethods.webClickElement(subject_ClosedActivity, "subject");
		ReUsableMethods.switchToChildWindowHandle();
		//
	}

	public String getSubjectValue_OnDetailPage() {
		return ReUsableMethods.WebGetElementText(subject_ClosedActivity, "last name");
	}

	public void clickThreeDotsActionMenu() throws InterruptedException {
		ReUsableMethods.webClickElement(threedots, "three dot");
		//
	}

	public boolean verifyForwardButtonFromDotIcon_OnDetailPage() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(forwardButton, "Button");
	}

	public boolean verifyReplyButtonFromDotIcon_OnDetailPage() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(replyButton, "Button");
	}

	public boolean verifyReplyAllButtonFromDotIcon_OnDetailPage() throws InterruptedException {
		//
		return ReUsableMethods.WebIsElementDisplayed(replyAllButton, "Button");
	}

	public String getRatingId() {
		String Actual_LeadRating = ReUsableMethods.WebGetElementText(leadRating, "Lead Rating");
		return Actual_LeadRating;
	}

	public void clickOnReplyAllButtonFromDotIcon_OnDetailPage() throws InterruptedException {
		ReUsableMethods.safeClick(threeDot, replyAllButton);
		ReUsableMethods.webClickElement(replyAllButton, "reply all button");
		ReUsableMethods.switchToChildWindowHandle();
		//
	}

	public void emailReplyButton() throws InterruptedException {
		ReUsableMethods.safeClick(threeDot, replyButton);
		ReUsableMethods.webClickElement(replyButton, "reply button");
		ReUsableMethods.switchToChildWindowHandle();
		//
	}

	public void clickOnForwardButtonFromDotIcon_OnDetailPage() throws InterruptedException {
		ReUsableMethods.webClickElement(forwardButton, "click on manage tag");
		ReUsableMethods.switchToChildWindowHandle();
		//
	}

	public String getSubject_AfterReplyButton() {
		return ReUsableMethods.WebGetElementText(subject_ClosedActivity, "subject");
	}

	public boolean verifySubject_AfterReplyButton() throws InterruptedException {
		Thread.sleep(3000);
		return ReUsableMethods.WebIsElementDisplayed(subject_ClosedActivity, "Button");
	}

	public boolean verifyCloseButton() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(closeButton, "Close Button");
	}

	public boolean verifyReviseButton() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(reviseButton, "Revise Button");
	}

	public void clickOnMergeTypeActionButton(String updateButtonName) throws InterruptedException {
		// waitandMovetoCloseButtn();
		String basicXpath = "//div[normalize-space()='button']";
		String actualXpath = basicXpath.replace("button", updateButtonName);
		List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
		WebElement ele = list.get(0);
		ReUsableMethods.webMoveToElement(ele, updateButtonName);
		ReUsableMethods.webClickElement(ele, "Custom Action button");

	}

	public String getUploadedFile() throws InterruptedException {
		return ReUsableMethods.WebGetElementText(fileUploaded_ViewIcon, "file uploaded successfully");

	}

	public void clickOnPauseButton() {
		ReUsableMethods.webClickElement(pauseButton, "Pause Button");
	}

	public void waitForLoader() {
		WebDriverWait wait = new WebDriverWait(wdriver, Duration.ofSeconds(10));

		try {
			// Check if the loader is displayed
			if (ReUsableMethods.WebIsElementDisplayed(loader, "loader")) {
				System.out.println("Loader is displayed");

				// Wait for the loader to disappear
				wait.until(ExpectedConditions.invisibilityOf(loader));
				System.out.println("Loader has disappeared.");
			}

		} catch (Exception e) {
			// General exception handling for any unexpected errors
			System.err.println("An error occurred while waiting for the loader.");
			e.printStackTrace(); // Optionally, log the stack trace
		}
	}

	public void waitandMovetoCloseButtn() {
		WebWait.waitForExplictVisibility(closeButton);
		ReUsableMethods.webMoveToElement(closeButton, null);
	}

	public void clickOnCloseButton() {
		if (ReUsableMethods.WebIsElementDisplayed(closeButton, "close button")) {
			ReUsableMethods.scrollElementToMiddle(closeButton);
			ReUsableMethods.webClickElement(closeButton, "Close Button");
			// new CommonProductFunctions(DriverManager.getWdriver()).clickOnClose();
		}

	}

	public String getUploadedAttchedFile(String PDFFile) throws InterruptedException {
		ReUsableMethods.WebGetElementText(pdfUploaded, "file uploaded successfully");
		return PDFFile;
	}

	public String uploadFile(String path) throws InterruptedException {
		uploadFile.sendKeys(path);
		return path;
	}

	public boolean verifySampleTextWatermark() {
		return ReUsableMethods.WebIsElementDisplayed(sampleTextWatermark, "Verify sample watermark is visible or not");
	}

	public boolean verifyPdfWatermark() {
		return ReUsableMethods.WebIsElementDisplayed(pdfWatermark, "Verify pdf watermark is visible or not");
	}

	public boolean verifyAdvanceEncryptionTextEncrypt_detail() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionTextEncrypt_detail,
				"Advance Encryption Text Detail");
	}

	public boolean verifyAdvanceEncryptionTextPlainText_detail() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionTextPlainText_detail,
				"Advance Encryption PlainText Detail");
	}

	public boolean verifyAdvanceEncryptionTextPlainText_History() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionTextPlainText_History,
				"PlainText Advance Encryption History");
	}

	public boolean verifyAdvanceEncryptionEmailEncrypt_detail() throws InterruptedException {
		ReUsableMethods.scrollElementToCentreOfScreen(advanceEncryptionEmailEncrypt_detail);
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionEmailEncrypt_detail,
				"Advance Encryption Email Detail");
	}

	public boolean verifyAdvanceEncryptionEmailPlainText_History() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionEmailPlainText_History,
				"PlainText Advance Encryption Email History");
	}

	public boolean verifyAdvanceEncryptionEmailPlainText_detail() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionEmailPlainText_detail,
				"Advance Encryption Email PlainText Detail");
	}

	public boolean verifyPanNumber_detail() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(panNumber_detail, "Advance Encryption Pan Number Detail");
	}

	public boolean verifyPanNumberPlainText_detail() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(panNumberPlainText_detail,
				"Advance Encryption Pan Number PlainText Detail");
	}

	public boolean verifyAdvanceEncryptionEmailEncrypt() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionEmailEncrypt, "Advance Encryption Email");
	}

	public boolean verifyAdvanceEncryptionTextEncrypt() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(advanceEncryptionTextEncrypt, "Advance Encryption Text");
	}

	public boolean verifyRegularExpressionEncrypt_detail() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(regularExpressionEncrypt_detail,
				"Advance Encryption Regular Expression");
	}

	public boolean verifyDedupeRuleFired() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(dedupeRuleFired, "Dedupe Rule Fired");
	}

	public void clickOnClone() {
		ReUsableMethods.webClickElement(clone, "Clone");

	}

	public void clickOnCardToogle() {
		ReUsableMethods.webClickElement(cardToggle, "Card Toggle");
	}

	String preXpathForDetailPageButton = "//*[contains(@data-autoid,'";
	String postXpathForDetailPageButton = "')]";

	public void clickOnDetailPageButton(String buttonName) {
		String xpath = preXpathForDetailPageButton + buttonName + postXpathForDetailPageButton;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.webClickElement(ele.get(0), buttonName);
	}

	// Life CycleProgressLead current rating is Hot
	String preXpathForCardHeading = "//h6[@title='";
	String preXpathForState = "']//ancestor::div[@data-testid='card-header']//following-sibling::div//div[@title='";
	String preXpathForTitle = "']//ancestor::div[@id='ThirdField']//descendant::div[@title='";
	String postXpathForTitle = "']";

	public boolean isStateAndMilestoneDisplayed(String cardHeading, String state, String title) {
		String xpath = preXpathForCardHeading + cardHeading + preXpathForState + state + preXpathForTitle + title
				+ postXpathForTitle;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.WebIsElementDisplayed(ele.get(0), "isStateAndMilestoneDisplayed");

	}

	// webpresenter execution on card
	// h6[text()='Employee_None']//following-sibling::a[text()='Click here to
	// Fetch']
	String prexpathForWp = "//h6[text()='";
	String postXpathForWp = "']//following-sibling::a[text()='Click here to Fetch']";

	public void clickOnFetchRecord(String wpNameOnCard) {
		String xpath = prexpathForWp + wpNameOnCard + postXpathForWp;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.webClickElement(ele.get(0), wpNameOnCard);

	}

	@FindBy(xpath = "//label[@title='id']//following-sibling::*//input[@type='text']")
	public WebElement id;

	@FindBy(xpath = "//button[text()='Execute']")
	public WebElement executeButton;

	String preXpathForNameOnWP = "//h6[.='";
	String postXpathForNameOnWP = "']//ancestor::div[contains(@class,'webPresenter')]//div[@title='";
	String pathAfterNameOnWP = "']";

	public boolean verifyNameOnWP(String wpName, String nameOnWPCard) {
		String xpath = preXpathForNameOnWP + wpName + postXpathForNameOnWP + nameOnWPCard + pathAfterNameOnWP;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.WebIsElementDisplayed(ele.get(0), wpName);

	}

	public void clickNameOnWP(String wpName, String nameOnWPCard) {
		String xpath = preXpathForNameOnWP + wpName + postXpathForNameOnWP + nameOnWPCard + pathAfterNameOnWP;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.scrollElementToCentreOfScreen(ele.get(0));
		// ReUsableMethods.webClickElement(ele.get(0), nameOnWPCard);
		ReUsableMethods.webClickJavaScriptExecutor(ele.get(0));

	}

	@FindBy(xpath = "//div[@title='Employee']//following::div[text()='Sushant']")
	public WebElement sushantOnWP;

	public boolean isSushantOnWPDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(sushantOnWP, "sushantOnWP");
	}

	@FindBy(xpath = "//label[text()='Mobile Phone']")
	public WebElement mobilePhone;

	public boolean isMobilePhoneDisplayedOnWidget() {
		return ReUsableMethods.WebIsElementDisplayed(mobilePhone, "mobilePhone");
	}

	@FindBy(xpath = "//div[text()='QA_Mobile']")
	public WebElement qaMobile;

	public boolean isQAMobileDisplayedOnReport() {
		return ReUsableMethods.WebIsElementDisplayed(qaMobile, "qaMobile");
	}

	@FindBy(xpath = "//header[contains(@class, 'ui-dialog-titlebar')]//span[text()='Duplicate Record Listing']")
	private WebElement dedupeRecordListing;

	public boolean isDedupeRecordingListingDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(dedupeRecordListing, "Duplicate Record Listing");
	}

	@FindBy(xpath = "//button[text()='Ignore and Update']")
	public WebElement ignoreAndUpdateButton;

	public boolean isIgnoreAndUpdateButtonDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(ignoreAndUpdateButton, "Ignore And Update Button");

	}

	@FindBy(xpath = "//button[text()='Ignore And Create']")
	public WebElement ignoreAndCreateButton;

	public boolean isIgnoreAndCreateButtonDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(ignoreAndCreateButton, "Ignore And Create Button");
	}

	@FindBy(xpath = "//button[text()='Revise']")
	public WebElement reviseDedupeButton;

	public boolean isReviseButtonDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(reviseDedupeButton, "Revise Button");
	}

	@FindBy(xpath = "//div[@class='dedupe-dialog']//*[@data-autoid='ded_msg']")

	public WebElement dedupeRuleMessage;

	public String getDedupeRuleMessage() {
		return ReUsableMethods.WebGetElementText(dedupeRuleMessage, "Rule Name");

	}

	@FindBy(xpath = "//a[@data-autoid='LeadName_0']")
	public WebElement firstLeadDedupeListing;

	public String getFirstLeadNameDedupeListing() {
		return ReUsableMethods.WebGetElementText(firstLeadDedupeListing, "Lead Name");

	}

	@FindBy(xpath = "//button[contains(@data-autoid, 'button') and normalize-space(text())='Update']")
	public WebElement updateLeadDedupe;

	public void clickOnUpdateButton_Dedupe() {
		ReUsableMethods.webClickElement(checkFirstCheckBox, "Choose first lead");
		ReUsableMethods.webClickElement(updateLeadDedupe, "Update Button");
	}

	@FindBy(xpath = "//label[@data-autoid='checkbox_input_row_index_0']")
	public WebElement checkFirstCheckBox;

	@FindBy(xpath = "//a[@data-autoid='button' and .//div[text()='Pulse']]")
	public WebElement pulseButton;

	@FindBy(xpath = "//div[contains(text(), 'key Information')]")
	public WebElement keyInformation;

	public void clickPulseButton() {
		ReUsableMethods.webClickElement(pulseButton, "Choose first lead");
	}

	String customButtonPre = "//a[normalize-space(div)='";
	String customButtonPost = "']";

	public void clickCustomActionDedupeButton(String buttonName) {

		String xpath = customButtonPre + buttonName + customButtonPost;
		WebElement element = ReUsableMethods.findElementByPath(xpath).get(0);
		ReUsableMethods.scrollElementToCentreOfScreen(element);
		ReUsableMethods.webClickElement(element, "Custom Action Button");

	}

	public boolean isCAB_DedupePresent(String buttonName) {
		String xpath = customButtonPre + buttonName + customButtonPost;
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		// ReUsableMethods.scrollElementToCentreOfScreen(list.get(0));
		return ReUsableMethods.isWebElementDisplayed(list);

	}

	@FindBy(xpath = "//*[normalize-space(text()) = 'API_Age']/parent::div/div/*")
	public WebElement age_API;

	@FindBy(xpath = "//*[normalize-space(text()) = 'API_Count']/parent::div/div/*")
	public WebElement count_API;

	@FindBy(xpath = "//*[contains(@data-autoid,'LogACall_')]")
	public WebElement logACallBtn;

	@FindBy(xpath = "//span[@data-autoid='LE_MOBILE_ctrl']")
	public WebElement moblieNumber;

	// public String getLeademail() {
	//
	// return ReUsableMethods.WebGetElementText(getShadowEmailElement(), "EmailTo");
	//
	// }

	String preColumnDedupeListingTitle = "//div[@class='dedupe-dialog']//div[@title='";
	String postColumnDedupeListingTitle = "']";

	public boolean verifyDedupeListingTitleVisible(String title) {

		String xpath = preColumnDedupeListingTitle + title + postColumnDedupeListingTitle;

		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.isWebElementDisplayed(list);
	}

	public boolean isAccountExistOnDuplicateRecordListing(String title) {
		String xpath = String.format("//div[@title='%s' and contains(@data-autoid, 'F')]", title);
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);

		return ReUsableMethods.isWebElementDisplayed(list);

	}

	// comments elements on Related History

	String xpath = "//*[contains(@data-autoid,'Comments_')]";

	public List<WebElement> getCommentsLeadHistoryElements() throws InterruptedException {

		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		return list;
	}

	public List<String> getCommentsLeadHistory() throws InterruptedException {

		List<String> listValues = new ArrayList<>();
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		WebWait.waitForExplictVisibility(list.get(0));

		for (int i = 0; i < list.size(); i++) {
			listValues.add(i, list.get(i).getText());
			System.out.println(listValues.get(i));
		}

		return listValues;
	}

	// Copied from Account Detail Page for Related Activities

	String preXpathLinkName = "//span[text()='";
	String postXpathLinkName = "']";

	public String clickOnLink(String link) {
		String xpath = preXpathLinkName + link + postXpathLinkName;
		WebElement ele = ReUsableMethods.findElementByPath(xpath).get(0);
		ReUsableMethods.webClickElement(ele, link);
		return link;
	}

	@FindBy(xpath = "//*[@data-autoid='LE_LOCALITY_ctrl']")
	public WebElement locality;

	public String getLocality() {
		return ReUsableMethods.WebGetElementText(locality, "locality");
	}

	@FindBy(xpath = "//a[@data-autoid='FlowFinish']")
	public WebElement finishButton;

	@FindBy(xpath = "//a[@data-autoid='DisqualifyLead']")
	public WebElement disqulify;

	@FindBy(xpath = "//select[@data-autoid='Lookup1Id_ctrl']")
	public WebElement disqualifyReason;

	@FindBy(xpath = "//textarea[@data-autoid='Description1_ctrl']")
	public WebElement descriptionValue;

	// select[@data-autoid='Lookup1Id_ctrl']

	public void reasonForDisqualifyLead(String reason, String description) {
		ReUsableMethods.webSelectByVisibleText(disqualifyReason, reason, "reason message");
		ReUsableMethods.webEnterText(descriptionValue, description, "description subject");
		ReUsableMethods.webClickElement(disqulify, "disqualify lead  button");
	}

	@FindBy(xpath = "//label[@data-testid='radiobox-label']")
	public WebElement radioButtonInDedupeListing;

	public void clickOnDedupeButton(String buttonName) {
		String xpath = String.format("//button[@data-testid='button' and text()='%s']", buttonName);
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.webClickElement(list.get(0), buttonName);

	}

	public void clickOnFinish() {
		ReUsableMethods.webClickElement(finishButton, "Finish Button");
		ReUsableMethods.waitforWindowSize(1);
	}

	String preXpathForCardName = "//div[text()='";
	String preXpathForCardMessage = "']//ancestor::div[contains(@data-autoid,'Card_')]//following::*[text()='";
	String postXpathForCardMessage = "']";

	public boolean isMessageDisplayedOnCard(String cardName, String cardMessage) {
		String xpath = preXpathForCardName + cardName + preXpathForCardMessage + cardMessage + postXpathForCardMessage;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.WebIsElementDisplayed(ele.get(0), cardMessage);
	}

	public void clickOnCardState(String cardName, String cardState) {
		String xpath = preXpathForCardName + cardName + preXpathForCardMessage + cardState + postXpathForCardMessage;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.webClickElement(ele.get(0), cardState);
	}

	public boolean isDisplayed_ElementWithLabelAndValuethLabel(String label, String title) {
		String xpath = String.format("//label[@title='%s']//parent::div//*[@title='%s']", label, title);
		WebElement element = ReUsableMethods.findElementByPath(xpath).get(0);
		return ReUsableMethods.WebIsElementEnabled(element, title);

	}

	String shadowEmail = "div[data-autoid='LE_EMAIL_ctrl']";

	public String getLeademail(String title) {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);

		return ReUsableMethods.WebGetElementText(commonProductFunctions.getShadowElement(title, shadowEmail),
				"EmailTo");

	}

	String shadowEmailEncryption = "div[data-autoid='cust_11915_ctrl']";

	public String getEmailEncryption(String title) {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);

		return ReUsableMethods.WebGetElementText(commonProductFunctions.getShadowElement(title, shadowEmailEncryption),
				"Email");

	}

	public void scrollToLayout(String layoutName) {
		String xpath = String.format("//span[contains(@data-autoid , 'layoutname') and text() = ' %s']", layoutName);

		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);

		WebElement ele = list.get(0);

		ReUsableMethods.scrollElementToCentreOfScreen(ele);

	}

	String preXpathForDescription = "//div[@data-autoid='LE_DESCRIPTION']//descendant::div[text()='";
	String postXpathForDescription = "']";

	public boolean getDescription(String descriptionData) {
		String xpath = preXpathForDescription + descriptionData + postXpathForDescription;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.scrollElementToCentreOfScreen(ele.get(0));
		return ReUsableMethods.isWebElementDisplayed(ele);

	}

	public String getTitleWithLabel(String label, String title) {
		String xpath = String.format("//label[@title='%s']//parent::div//span[@title='%s']", label, title);
		WebElement element = ReUsableMethods.findElementByPath(xpath).get(0);
		ReUsableMethods.WebGetElementText(element, title);
		return title;

	}

	public String getLeadFieldText(String fieldText) {
		String xpath = String.format(
				"//div[contains(@class,'section')]//div//descendant::label[text()='%s']//parent::div//span", fieldText);

		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);

		WebElement ele = list.get(0);

		return ReUsableMethods.WebGetElementText(ele, fieldText);
	}

	String preXpathForFileTypeInsideExportLink = "//span[text()='";
	String postXpathForFileTypeInsideExportLink = "']";

	public boolean isFileTypeLinkInExportDisplayed(String fileType) {
		String xpath = preXpathForFileTypeInsideExportLink + fileType + postXpathForFileTypeInsideExportLink;
		List<WebElement> element = ReUsableMethods.findElementByPath(xpath);
		return ReUsableMethods.isWebElementDisplayed(element);
	}

	public void clickOnFileTypeLinkInExportDisplayed(String fileType) {
		String xpath = preXpathForFileTypeInsideExportLink + fileType + postXpathForFileTypeInsideExportLink;
		List<WebElement> element = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.webClickElement(element.get(0), fileType);
	}

	@FindBy(xpath = "//a[@title='Export']")
	public WebElement exportLink;

	public void clickOnExportLink() {
		ReUsableMethods.webClickElement(exportLink, "exportLink");
	}

	public WebElement getShadowEmailElement() {
		WebElement shadowHost = DriverManager.getWdriver()
				.findElement(By.xpath("(//div[contains(@class,'acid-shadow-dom')])[2]"));
		SearchContext shadowRoot = shadowHost.getShadowRoot();
		WebElement emailButton = shadowRoot.findElement(By.cssSelector("div[data-autoid='LE_EMAIL_ctrl']"));
		return emailButton;
	}

	public boolean checkIfEmailExistInShadowRoot(String email) {
		WebDriver driver = DriverManager.getWdriver();

		try {
			// 1. Find the <a> element using the email attribute
			WebElement emailAnchor = driver.findElement(By.cssSelector("a[email='" + email + "']"));

			// 2. Find the shadow host inside that anchor
			WebElement shadowHost = emailAnchor.findElement(By.cssSelector("div.acid-shadow-dom"));

			// 3. Enter shadow root
			SearchContext shadowRoot = shadowHost.getShadowRoot();

			// 4. Look for the email element inside shadow DOM
			WebElement ele = shadowRoot.findElement(By.cssSelector("div[data-autoid='LE_EMAIL_ctrl']"));
			ReUsableMethods.scrollElementToCentreOfScreen(ele);
			return ReUsableMethods.WebIsElementDisplayed(ele, email);
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getOfferNameOnDetail() {
		ReUsableMethods.scrollElementToCentreOfScreen(offerName);
		return ReUsableMethods.WebGetElementText(offerName, "Offer Name");
	}

	public boolean isAttchedDocumentDisplayed(String documentName) {
		String xpath = String.format("//label[@data-autoid='ACT_ATTACHMENT_lbl']/following::span[@title='%s']",
				documentName);
		List<WebElement> element = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.scrollElementToCentreOfScreen(element.get(0));
		return ReUsableMethods.WebIsElementDisplayed(element.get(0), documentName);
	}

	public String getLeadNameFromDetail(String leadName) {
		String xpath = String.format("//label[@data-autoid = 'LE_NAME_lbl']//parent::div//span[@title = '%s']",
				leadName);
		List<WebElement> list = ReUsableMethods.findElementByPath(xpath);

		WebElement ele = list.get(0);

		return ReUsableMethods.WebGetElementText(ele, leadName);
	}

	@FindBy(xpath = "//div[@data-testid='card-footer']//span[text()='View More']")
	public WebElement viewMore;

	public boolean isViewMoreDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(viewMore, "viewMore");
	}

	public boolean isDesginerHomePageDisplay() {
		return ReUsableMethods.WebIsElementDisplayed(layoutDesigner, "layoutDesignerHeading");
	}

	@FindBy(xpath = "//div[contains(@aria-label,'Editing area')]")

	private WebElement editingArea;

	public boolean isEditingAreaDisplayed() {
		return ReUsableMethods.WebIsElementDisplayed(editingArea, "Editing are");
	}

	public void clickOnPlusIconByCardName(String cardName) {
		String xpath = "//*[@title='" + cardName
				+ "']/ancestor::div[contains(@data-testid,'card-')]//*[local-name()='svg' and contains(@name,'plus')]";
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		ReUsableMethods.scrollElementToCentreOfScreen(ele.get(0));
		ReUsableMethods.webClickElement(ele.get(0), "Plus Icon");
	}

	@FindBy(xpath = "//div[@data-testid='card-scroll']//a[contains(@data-autoid,'Subject_')]")
	private WebElement subjectOnListing;

	public String getSubjectOnListing() {
		ReUsableMethods.scrollElementToCentreOfScreen(subjectOnListing);
		return ReUsableMethods.WebGetElementText(subjectOnListing, "Subject");
	}

	@FindBy(xpath = "//div[@data-testid='card-scroll']//a[contains(@data-autoid,'Subject_') and contains(@class,'text')]")
	public WebElement getCompositeCardSubject;

	public String getSubjectOfCompositeCard() {
		ReUsableMethods.scrollElementToCentreOfScreen(getCompositeCardSubject);
		return ReUsableMethods.WebGetElementText(getCompositeCardSubject, "Subject");
	}

	@FindBy(xpath = "//*[@title='comp_List+data']/ancestor::div[contains(@data-testid,'card-')]//*[@title='employee2']/following::div[contains(@class,'medium atachment')]//div[@title='Sushant']")
	public WebElement compEDSValue;

	public boolean isEDSValueOfCompositeCardDisplayed() {
		ReUsableMethods.scrollElementToCentreOfScreen(compEDSValue);
		return ReUsableMethods.WebIsElementDisplayed(compEDSValue, "compEDSValue");
	}


	@FindBy(xpath = "//*[@title='OrderWiseListCard']/ancestor::div[contains(@data-testid,'card-')]//div[contains(@data-autoid,'_ActivityNo_val')]")
	public List<WebElement> activityNo;

	@FindBy(xpath = "//div[@data-autoid='0_Employee_name_val' and text()='Sushant'] /ancestor::div[contains(@class,'atachment-card')]")
	public WebElement sushantOnVerticalCard;

	public boolean isSushantOnVerticalCardDisplayed() {
		ReUsableMethods.scrollElementToCentreOfScreen(sushantOnVerticalCard);
		return ReUsableMethods.WebIsElementDisplayed(sushantOnVerticalCard, "sushantOnVerticalCard");
	}


	@FindBy(xpath = "//*[starts-with(@data-autoid,'AttachmentName')]")
	public WebElement attachmentName;
	
	@FindBy(xpath = "//*[@title='Document generation criteria not matched ']")
	public WebElement creteriaNotMatched;

	@FindBy(xpath = "//div[@role='textbox']")
	public WebElement textBox;
	
	public void editableTemplateForBulkGerneration(String text) {
		ReUsableMethods.webClearText(textBox, "clear template");
		ReUsableMethods.webEnterText(textBox, text, "template text");
		ReUsableMethods.webClickElement(saveButton, "save button");
	}
}
