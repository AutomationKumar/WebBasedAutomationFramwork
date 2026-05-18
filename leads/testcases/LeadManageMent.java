package com.businessnext.leads.testcases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.assignmentrule.pages.AssignmentRuleCreationPage;
import com.businessnext.object.deviation.pages.DeviationConstants;
import com.businessnext.object.deviation.pages.DeviationCreationPage;
import com.businessnext.object.facility.pages.FacilityConstants;
import com.businessnext.object.facility.pages.FacilityCreationPage;
import com.businessnext.objects.account.pages.AccountConstant;
import com.businessnext.objects.account.pages.AccountCreationPage;
import com.businessnext.objects.credit.pages.CreditConstants;
import com.businessnext.objects.credit.pages.CreditCreationPage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.businessnext.objects.liability.pages.LiabilityConstants;
import com.businessnext.objects.liability.pages.LiabilityCreationPage;
import com.businessnext.objects.subsidiary.pages.SubsidiaryConstantPage;
import com.businessnext.objects.subsidiary.pages.SubsidiaryCreationPage;
import com.businessnext.objects.task.pages.TaskDetailPage;
import com.businessnext.objects.verifcation.pages.VerificationConstants;
import com.businessnext.objects.verifcation.pages.VerificationCreationPage;
import com.businessnext.setupmodules.trigger.pages.TriggerActionCreationPage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.LayoutConstants;
import com.common.pages.NavigationPanel;
import com.common.pages.ObjectConstants;
import com.common.pages.RelatedActivityPage;
import com.common.pages.RelatedObjectConstants;
import com.common.pages.RelatedObjectLinkConstants;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.Constants;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)

public class LeadManageMent extends BaseClass {
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();

	ThreadLocal<LeadHomePage> leadHomePage = new ThreadLocal<>();
	ThreadLocal<LeadCreationPage> leadCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadDetailPage> leadDetailPage = new ThreadLocal<>();
	ThreadLocal<VerificationCreationPage> verificationCreationPage = new ThreadLocal<>();
	ThreadLocal<CreditCreationPage> creditCreationPage = new ThreadLocal<>();
	ThreadLocal<FacilityCreationPage> facilityCreationPage = new ThreadLocal<>();
	ThreadLocal<LiabilityCreationPage> liabilityCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadCommonFunctions> leadCommonFunctions = new ThreadLocal<>();
	ThreadLocal<SubsidiaryCreationPage> subsidiaryCreationPage = new ThreadLocal<>();
	ThreadLocal<RelatedActivityPage> relatedActivityPage = new ThreadLocal<>();
	ThreadLocal<TaskDetailPage> taskDetailPage = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<String> expectedLastName = new ThreadLocal<>();
	ThreadLocal<AssignmentRuleCreationPage> assignmentRuleCreationPage = new ThreadLocal<>();
	ThreadLocal<AccountCreationPage> accountCreationPage = new ThreadLocal<>();
	ThreadLocal<TriggerActionCreationPage> triggerActionCreationPage = new ThreadLocal<>();
	String expectedLeadRating;
	String expectedLeadStatusCode;
	String expectedLeadProduct;

	@BeforeMethod(alwaysRun = true)
	public void initialiseclassObjects() {
		login.set(new webReusableBusinessFunctions());
		navigationPanel.set(new NavigationPanel(DriverManager.getWdriver()));
		commonProductFunctions.set(new CommonProductFunctions(DriverManager.getWdriver()));
		leadHomePage.set(new LeadHomePage(DriverManager.getWdriver()));
		leadCommonFunctions.set(new LeadCommonFunctions());
		leadCreationPage.set(new LeadCreationPage(DriverManager.getWdriver()));
		commonProductFunctions.set(new CommonProductFunctions(DriverManager.getWdriver()));
		leadDetailPage.set(new LeadDetailPage(DriverManager.getWdriver()));
		verificationCreationPage.set(new VerificationCreationPage(DriverManager.getWdriver()));
		creditCreationPage.set(new CreditCreationPage(DriverManager.getWdriver()));
		facilityCreationPage.set(new FacilityCreationPage(DriverManager.getWdriver()));
		liabilityCreationPage.set(new LiabilityCreationPage(DriverManager.getWdriver()));
		subsidiaryCreationPage.set(new SubsidiaryCreationPage(DriverManager.getWdriver()));
		relatedActivityPage.set(new RelatedActivityPage(DriverManager.getWdriver()));
		taskDetailPage.set(new TaskDetailPage(DriverManager.getWdriver()));
		expectedLastName.set(ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime());
		assignmentRuleCreationPage.set(new AssignmentRuleCreationPage(DriverManager.getWdriver()));
		accountCreationPage.set(new AccountCreationPage(DriverManager.getWdriver()));
		triggerActionCreationPage.set(new TriggerActionCreationPage(DriverManager.getWdriver()));
		expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
		expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;
		expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
		s.set(new SoftAssert());

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184742",
			"184659" ,"385820"}, scriptType = { "" }, testCasePriority = { "1" })
	@Test(priority = 1, enabled = true, groups = { "Regression", "Lead","Client" })

	public void leadSaveAndNew() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "saveandnew");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(leadCreationPage.get().save, "save btn is visible"),
				"save btn is not visible");
		ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "cancelBtn");

		leadHomePage.get().enterInAdvancedSearchText(expectedLastName.get());
		ReUsableMethods.webClickElement(leadHomePage.get().advanceSearchIcon, "advanceSearchIcon");

		String actualLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, "LastName");
		s.get().assertEquals(actualLeadName, expectedLastName.get());
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184671" }, scriptType = {
			"" }, testCasePriority = { "2" })
	@Test(priority = 2, enabled = true, groups = { "Regression", "Lead" })

	public void leadClone() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		String actualLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, "LastName");
		String clonedLeadRating = LeadConstants.LEAD_RATING_WARM;
		String clonedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;
		String clonedLeadProduct = LeadConstants.PRODUCT_HOME_LOAN;
		s.get().assertEquals(actualLeadName, expectedLastName.get());
		leadDetailPage.get().clickOnClone();
		// Cloning Lead
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadRating, clonedLeadRating, "Lead rating Cold");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadProduct, clonedLeadProduct, "Lead Product");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadStatusCode, clonedLeadStatusCode,
				"Status Code");
		leadCreationPage.get().clickOnSaveButton();

		String clonedLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, "LastName");
		s.get().assertEquals(actualLeadName, clonedLeadName, "actual Lead Name not equals cloned Lead Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184755" }, scriptType = {
			"" }, testCasePriority = { "3" })
	@Test(priority = 3, enabled = true, groups = { "Regression", "Lead" })

	public void createDeviationFromLeadDetail() throws Exception {

		DeviationCreationPage deviationCreationPage = new DeviationCreationPage(DriverManager.getWdriver());
		
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		//String editedDeviationName = "Edited" + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		//String LeadID = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadID, "Get Lead Id");

		// Create Related Deviation
		relatedActivityPage.get().clickOnModule(LeadConstants.TAB_NAME_RELATED_DEVIATION);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_DEVIATION);
		String parentHandle = ReUsableMethods.getWindow();

		String expectedDeviationSubject = DeviationConstants.DEVIATION_SUBJECT + ReUsableMethods.getCurrentdateTime();
		deviationCreationPage.createDeviation(LayoutConstants.LAYOUT_DEVIATION_SYSTEM, expectedDeviationSubject,
				DeviationConstants.DEVIATION_STATUSCODE_APPROVED, DeviationConstants.DEVIATION_LEVEL_ONE,
				DeviationConstants.DEVIATION_EXECUTED_FOR_GURANTOR, DeviationConstants.DEVIATION_ADD_ON_EXE_DATE_ONE,
				Constants.SAVE_BUTTON);
		ReUsableMethods.switchToWindow(parentHandle);

		String actualDeviationName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualDeviationName, expectedDeviationSubject,
				"actualDeviationName not equals expectedDeviationName");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184756" }, scriptType = {
			"" }, testCasePriority = { "4" })
	@Test(priority = 4, enabled = true, groups = { "Regression", "Lead" })

	public void editDeviationFromLeadDetail() throws Exception {
		DeviationCreationPage deviationCreationPage = new DeviationCreationPage(DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String editedDeviationName = "Edited" + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		//String LeadID = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadID, "Get Lead Id");

		// Create Related Deviation
		relatedActivityPage.get().clickOnModule(LeadConstants.TAB_NAME_RELATED_DEVIATION);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_DEVIATION);
		String parentHandle = ReUsableMethods.getWindow();

		String expectedDeviationSubject = DeviationConstants.DEVIATION_SUBJECT + ReUsableMethods.getCurrentdateTime();

		deviationCreationPage.createDeviation(LayoutConstants.LAYOUT_DEVIATION_SYSTEM, expectedDeviationSubject,
				DeviationConstants.DEVIATION_STATUSCODE_APPROVED, DeviationConstants.DEVIATION_LEVEL_ONE,
				DeviationConstants.DEVIATION_EXECUTED_FOR_GURANTOR, DeviationConstants.DEVIATION_ADD_ON_EXE_DATE_ONE,
				Constants.SAVE_BUTTON);
		ReUsableMethods.switchToWindow(parentHandle);

		String actualDeviationName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualDeviationName, expectedDeviationSubject,
				"actualDeviationName not equals expectedDeviationName");

		// Edit Deviation
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClearText(deviationCreationPage.subjectDeviation, "Subject Deviation");
		ReUsableMethods.webEnterText(deviationCreationPage.subjectDeviation, editedDeviationName,
				"Edited Deviation Name");

		ReUsableMethods.webClickElement(deviationCreationPage.saveDeviation, "saveDeviation");
		ReUsableMethods.switchToWindow(parentHandle);
		leadDetailPage.get().clickOnCloseButton();

		String actualEditedDeviationName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualEditedDeviationName, editedDeviationName,
				"actualEditedDeviationName not equals editedDeviationName");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184757" }, scriptType = {
			"" }, testCasePriority = { "5" })
	@Test(priority = 5, enabled = true, groups = { "Regression", "Lead" })

	public void deleteDeviationFromLeadDetail() throws Exception {
		DeviationCreationPage deviationCreationPage = new DeviationCreationPage(DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		//String LeadID = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadID, "Get Lead Id");

		// Create Related Deviation
		relatedActivityPage.get().clickOnModule(LeadConstants.TAB_NAME_RELATED_DEVIATION);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_DEVIATION);
		String parentHandle = ReUsableMethods.getWindow();

		String expectedDeviationSubject = DeviationConstants.DEVIATION_SUBJECT + ReUsableMethods.getCurrentdateTime();

		deviationCreationPage.createDeviation(LayoutConstants.LAYOUT_DEVIATION_SYSTEM, expectedDeviationSubject,
				DeviationConstants.DEVIATION_STATUSCODE_APPROVED, DeviationConstants.DEVIATION_LEVEL_ONE,
				DeviationConstants.DEVIATION_EXECUTED_FOR_GURANTOR, DeviationConstants.DEVIATION_ADD_ON_EXE_DATE_ONE,
				Constants.SAVE_BUTTON);
		ReUsableMethods.switchToWindow(parentHandle);

		String actualDeviationName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualDeviationName, expectedDeviationSubject,
				"actualDeviationName not equals expectedDeviationName");

		// Delete Deviation
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				leadDetailPage.get().linkDeleteFirstRelated);
		ReUsableMethods.safeClick(leadDetailPage.get().linkDeleteFirstRelated, leadDetailPage.get().Delete1);
		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on Delete Button");

		commonProductFunctions.get().waitForLoader();
		commonProductFunctions.get().advanceSearch(actualDeviationName);

		s.get().assertTrue(commonProductFunctions.get().isNoDataExistDisplayed());
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184758" }, scriptType = {
			"" }, testCasePriority = { "6" })
	@Test(priority = 6, enabled = true, groups = { "Regression", "Lead" })

	public void createVerificationFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);


		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Verification from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_VERIFCATION);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_VERIFICATION);
		String parentHandle = ReUsableMethods.getWindow();
		String expectedVerificationName = verificationCreationPage.get()
				.createVerification(LayoutConstants.LAYOUT_VERIFCATION_SYSTEM, VerificationConstants.VERIFICATION_NAME);
		ReUsableMethods.switchToWindow(parentHandle);
		String actualVerificationNameName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualVerificationNameName, expectedVerificationName,
				"actual Verification Name not equals expected Verification Name");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184759" }, scriptType = {
			"" }, testCasePriority = { "7" })
	@Test(priority = 7, enabled = true, groups = { "Regression", "Lead" })

	public void editVerificationFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String editedVerificationName = "Edited" + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Verification from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_VERIFCATION);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_VERIFICATION);
		String parentHandle = ReUsableMethods.getWindow();
		String expectedVerificationName = verificationCreationPage.get()
				.createVerification(LayoutConstants.LAYOUT_VERIFCATION_SYSTEM, VerificationConstants.VERIFICATION_NAME);
		ReUsableMethods.switchToWindow(parentHandle);
		String actualVerificationNameName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualVerificationNameName, expectedVerificationName,
				"actual Verification Name not equals expected Verification Name");
		// Edit Verification
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClearText(verificationCreationPage.get().name, "Clear Name");
		ReUsableMethods.webEnterText(verificationCreationPage.get().name, editedVerificationName, "Verification Name");
		ReUsableMethods.webClickElement(verificationCreationPage.get().save, "Save");
		ReUsableMethods.switchToWindow(parentHandle);
		leadDetailPage.get().clickOnCloseButton();
		String actualEditedVerificationName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualEditedVerificationName, editedVerificationName,
				"actual Edited Verification Name not equals edited Verification Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184760" }, scriptType = {
			"" }, testCasePriority = { "8" })
	@Test(priority = 8, enabled = true, groups = { "Regression", "Lead" })

	public void deleteVerificationFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Verification from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_VERIFCATION);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_VERIFICATION);
		String parentHandle = ReUsableMethods.getWindow();
		String expectedVerificationName = verificationCreationPage.get()
				.createVerification(LayoutConstants.LAYOUT_VERIFCATION_SYSTEM, VerificationConstants.VERIFICATION_NAME);
		ReUsableMethods.switchToWindow(parentHandle);
		String actualVerificationNameName = leadDetailPage.get().getFirstActivityName();
		s.get().assertEquals(actualVerificationNameName, expectedVerificationName,
				"actual Verification Name not equals expected Verification Name");

		// Delete Verification
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				leadDetailPage.get().linkDeleteFirstRelated);
		ReUsableMethods.safeClick(leadDetailPage.get().linkDeleteFirstRelated, leadDetailPage.get().Delete1);
		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on Delete Button");

		commonProductFunctions.get().waitForLoader();

		commonProductFunctions.get().advanceSearch(expectedVerificationName);

		s.get().assertTrue(commonProductFunctions.get().isNoDataExistDisplayed());
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184761" }, scriptType = {
			"" }, testCasePriority = { "9" })
	@Test(priority = 9, enabled = true, groups = { "Regression", "Lead" })

	public void createCreditFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);


		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Credit from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_CREDIT);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_CREDIT);
		String expectedCreditName = creditCreationPage.get().createCredit(null, CreditConstants.CREDIT_NAME, null,
				null);
		relatedActivityPage.get().clickOnActionButton(LeadConstants.CLOSE);
		commonProductFunctions.get().waitForLoader();
		String actualCreditName = ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedNameFirst,
				"related Name First");
		s.get().assertEquals(actualCreditName, expectedCreditName,
				"actual Credit Name not equals expected Credit Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184762" }, scriptType = {
			"" }, testCasePriority = { "10" })
	@Test(priority = 10, enabled = true, groups = { "Regression", "Lead" })

	public void editCreditFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Credit from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_CREDIT);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_CREDIT);
		String expectedCreditName = creditCreationPage.get().createCredit(null, CreditConstants.CREDIT_NAME, null,
				null);
		relatedActivityPage.get().clickOnActionButton(LeadConstants.CLOSE);
		commonProductFunctions.get().waitForLoader();
		String actualCreditName = ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedNameFirst,
				"related Name First");
		s.get().assertEquals(actualCreditName, expectedCreditName,
				"actual Credit Name not equals expected Credit Name");
		// Edit Credit
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		ReUsableMethods.switchToChildWindowHandle();
		String editedCreditSubject = creditCreationPage.get().enterCreditName("Edited");
		ReUsableMethods.webClickElement(creditCreationPage.get().save, "Save");
		relatedActivityPage.get().clickOnActionButton(LeadConstants.CLOSE);
		commonProductFunctions.get().waitForLoader();
		String actualEditedCreditName = ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedNameFirst,
				"related Name First");
		s.get().assertEquals(actualEditedCreditName, editedCreditSubject,
				"actual Edited Credit Name not equals expected edited Credit Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184763" }, scriptType = {
			"" }, testCasePriority = { "11" })
	@Test(priority = 11, enabled = true, groups = { "Regression", "Lead" })

	public void createFacilityFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);


		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Facility from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_FACILITY);

		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW);
		String expectedFacilityName = facilityCreationPage.get().createFacility(null, FacilityConstants.FACILITY_NAME,
				FacilityConstants.RELATED_TO_NAME, FacilityConstants.RELATED_TO);
		commonProductFunctions.get().waitForLoader();
		relatedActivityPage.get().clickOnActionButton(LeadConstants.CLOSE);
		commonProductFunctions.get().waitForLoader();
		String actualFacilityName = ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedNameFirst,
				"related Name First");
		s.get().assertEquals(actualFacilityName, expectedFacilityName,
				"actual Facility Name not equals expected Facility Name");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184764" }, scriptType = {
			"" }, testCasePriority = { "12" })
	@Test(priority = 12, enabled = true, groups = { "Regression", "Lead" })

	public void editFacilityFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String editedfacilitySubject = "Edited" + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Facility from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_FACILITY);

		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW);
		String expectedFacilityName = facilityCreationPage.get().createFacility(null, FacilityConstants.FACILITY_NAME,
				FacilityConstants.RELATED_TO_NAME, FacilityConstants.RELATED_TO);
		commonProductFunctions.get().waitForLoader();
		relatedActivityPage.get().clickOnActionButton(LeadConstants.CLOSE);
		commonProductFunctions.get().waitForLoader();
		String actualFacilityName = ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedNameFirst,
				"related Name First");
		s.get().assertEquals(actualFacilityName, expectedFacilityName,
				"actual Facility Name not equals expected Facility Name");
		// Edit Credit
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webClearText(facilityCreationPage.get().name, "Clear Name TextBox");

		ReUsableMethods.webEnterText(facilityCreationPage.get().name, editedfacilitySubject, "Name");

		ReUsableMethods.webClickElement(facilityCreationPage.get().save, "Save");
		relatedActivityPage.get().clickOnActionButton(LeadConstants.CLOSE);
		commonProductFunctions.get().waitForLoader();
		String actualEditedFacilityName = ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedNameFirst,
				"related Name First");
		s.get().assertEquals(actualEditedFacilityName, editedfacilitySubject,
				"actual Credit Name not equals expected Credit Name");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184765" }, scriptType = {
			"" }, testCasePriority = { "13" })
	@Test(priority = 13, enabled = true, groups = { "Regression", "Lead" })

	public void createLiabilityFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Liability from Lead Detail
		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_LIABILITY);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_LIABILITY);
		String parentWindowHandle = ReUsableMethods.getWindow();
		String expectedLiabilityName = liabilityCreationPage.get().createLiabilityWithMandatoryFields(
				LayoutConstants.LAYOUT_LIABILITY_SYSTEM,
				LiabilityConstants.FINANCIER_NAME + ReUsableMethods.getCurrentdateTime(),
				LiabilityConstants.TYPE_ENQUIRY);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualLiabilityName = ReUsableMethods.WebGetElementText(relatedActivityPage.get().firstFinancierName,
				"Financer Name");
		s.get().assertEquals(actualLiabilityName, expectedLiabilityName,
				"Actual Liability Name not equals  Expected Liability Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184766" }, scriptType = {
			"" }, testCasePriority = { "14" })
	@Test(priority = 14, enabled = true, groups = { "Regression", "Lead" })

	public void editLiabilityFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Liability from Lead Detail
		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_LIABILITY);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_LIABILITY);
		String parentWindowHandle = ReUsableMethods.getWindow();
		String expectedLiabilityName = liabilityCreationPage.get().createLiabilityWithMandatoryFields(
				LayoutConstants.LAYOUT_LIABILITY_SYSTEM,
				LiabilityConstants.FINANCIER_NAME + ReUsableMethods.getCurrentdateTime(),
				LiabilityConstants.TYPE_ENQUIRY);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualLiabilityName = ReUsableMethods.WebGetElementText(relatedActivityPage.get().firstFinancierName,
				"Financer Name");

		s.get().assertEquals(actualLiabilityName, expectedLiabilityName,
				"Actual Liability Name not equals  Expected Liability Name");

		// Edit Liability
		String expectedEditedLiabilityname = "Edited Liability" + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webClearText(liabilityCreationPage.get().financierName, "Clear Financier Name");
		ReUsableMethods.webEnterText(liabilityCreationPage.get().financierName, expectedEditedLiabilityname,
				"Financier Name");
		liabilityCreationPage.get().clickOnSave_Liability();
		relatedActivityPage.get().clickOnActionButton(LeadConstants.CLOSE);
		commonProductFunctions.get().waitForLoader();

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualEditedLiabilityName = ReUsableMethods
				.WebGetElementText(relatedActivityPage.get().firstFinancierName, "Financer Name");

		s.get().assertEquals(actualEditedLiabilityName, expectedEditedLiabilityname,
				"Actual Edited Liability Name not equals  Expected Edited Liability Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184767" }, scriptType = {
			"" }, testCasePriority = { "15" })
	@Test(priority = 15, enabled = true, groups = { "Regression", "Lead" })

	public void deleteLiabilityFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Liability from Lead Detail
		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_LIABILITY);
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_LIABILITY);
		String parentWindowHandle = ReUsableMethods.getWindow();
		String expectedLiabilityName = liabilityCreationPage.get().createLiabilityWithMandatoryFields(
				LayoutConstants.LAYOUT_LIABILITY_SYSTEM,
				LiabilityConstants.FINANCIER_NAME + ReUsableMethods.getCurrentdateTime(),
				LiabilityConstants.TYPE_ENQUIRY);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualLiabilityName = ReUsableMethods.WebGetElementText(relatedActivityPage.get().firstFinancierName,
				"Financer Name");

		s.get().assertEquals(actualLiabilityName, expectedLiabilityName,
				"Actual Liability Name not equals  Expected Liability Name");

		// Delete Liability
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				leadDetailPage.get().linkDeleteFirstRelated);
		ReUsableMethods.safeClick(leadDetailPage.get().linkDeleteFirstRelated, leadDetailPage.get().Delete1);
		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on Delete Button");

		commonProductFunctions.get().waitForLoader();

		commonProductFunctions.get().advanceSearch(expectedLiabilityName);

		s.get().assertTrue(commonProductFunctions.get().isNoDataExistDisplayed());
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184768" }, scriptType = {
			"" }, testCasePriority = { "16" })
	@Test(priority = 16, enabled = true, groups = { "Regression", "Lead" })

	public void createSubsidiaryFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		String subsidiaryLastName = ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Subsidiary from Lead Detail
		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_SUBSIDIARY);
		String parentWindowHandle = ReUsableMethods.getWindow();

		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_SUBSIDIARY);
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_SUBSIDIARY_SYSTEM);

		ReUsableMethods.switchToChildWindowHandle();
		subsidiaryCreationPage.get().enterSubsidiary_LastName(subsidiaryLastName);
		ReUsableMethods.webSelectByVisibleText(subsidiaryCreationPage.get().subsidiaryType,
				SubsidiaryConstantPage.TYPE_SUBSIDIARY_APPLICANT, "Appicant");
		ReUsableMethods.webEnterText(subsidiaryCreationPage.get().subsidiaryEmail,
				SubsidiaryConstantPage.EMAIL_SUBSIDIARY, "Email Subsidiary");
		commonProductFunctions.get().clickOnSave();

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualSubsidiaryLastName = ReUsableMethods
				.WebGetElementText(relatedActivityPage.get().firstNameRecordInRelatedList, "First Subsidiary Name");

		assertEquals(actualSubsidiaryLastName, subsidiaryLastName,
				"Actual Subsidiary Name not equals  Expected Subsidiary Name");

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184769" }, scriptType = {
			"" }, testCasePriority = { "17" })
	@Test(priority = 17, enabled = true, groups = { "Regression", "Lead" })

	public void editSubsidiaryFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		String subsidiaryLastName = ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Subsidiary from Lead Detail
		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_SUBSIDIARY);
		String parentWindowHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_SUBSIDIARY);
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_SUBSIDIARY_SYSTEM);

		ReUsableMethods.switchToChildWindowHandle();

		subsidiaryCreationPage.get().enterSubsidiary_LastName(subsidiaryLastName);
		ReUsableMethods.webSelectByVisibleText(subsidiaryCreationPage.get().subsidiaryType,
				SubsidiaryConstantPage.TYPE_SUBSIDIARY_APPLICANT, "Appicant");
		ReUsableMethods.webEnterText(subsidiaryCreationPage.get().subsidiaryEmail,
				SubsidiaryConstantPage.EMAIL_SUBSIDIARY, "Email Subsidiary");
		commonProductFunctions.get().clickOnSave();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualSubsidiaryLastName = ReUsableMethods
				.WebGetElementText(relatedActivityPage.get().firstNameRecordInRelatedList, "First Subsidiary Name");
		// ReUsableMethods.WebGetElementText(relatedActivityPage.get().relatedSubject,"relatedSubject
		// First");

		s.get().assertEquals(actualSubsidiaryLastName, subsidiaryLastName,
				"Actual Subsidiary Name not equals  Expected Subsidiary Name");

		// Edit Liability
		String expectedEditedSubsidiaryname = "Edited Subsidiary";
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		ReUsableMethods.switchToChildWindowHandle();
		subsidiaryCreationPage.get().enterSubsidiary_LastName(expectedEditedSubsidiaryname);
		commonProductFunctions.get().clickOnSave();
		commonProductFunctions.get().clickOnClose();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualEditedSubsidiaryLastName = ReUsableMethods
				.WebGetElementText(relatedActivityPage.get().firstNameRecordInRelatedList, "First Subsidiary Name");

		s.get().assertEquals(actualEditedSubsidiaryLastName, expectedEditedSubsidiaryname,
				"Actual Edited Subsidiary Name not equals  Expected Edited Subsidiary Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184770" }, scriptType = {
			"" }, testCasePriority = { "18" })
	@Test(priority = 18, enabled = true, groups = { "Regression", "Lead" })

	public void deleteSubsidiaryFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		String subsidiaryLastName = ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Subsidiary from Lead Detail
		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_SUBSIDIARY);
		String parentWindowHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_SUBSIDIARY);
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_SUBSIDIARY_SYSTEM);

		ReUsableMethods.switchToChildWindowHandle();

		subsidiaryCreationPage.get().enterSubsidiary_LastName(subsidiaryLastName);
		ReUsableMethods.webSelectByVisibleText(subsidiaryCreationPage.get().subsidiaryType,
				SubsidiaryConstantPage.TYPE_SUBSIDIARY_APPLICANT, "Appicant");
		ReUsableMethods.webEnterText(subsidiaryCreationPage.get().subsidiaryEmail,
				SubsidiaryConstantPage.EMAIL_SUBSIDIARY, "Email Subsidiary");
		commonProductFunctions.get().clickOnSave();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualSubsidiaryLastName = ReUsableMethods
				.WebGetElementText(relatedActivityPage.get().firstNameRecordInRelatedList, "First Subsidiary Name");

		s.get().assertEquals(actualSubsidiaryLastName, subsidiaryLastName,
				"Actual Subsidiary Name not equals  Expected Subsidiary Name");

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184651" }, scriptType = {
			"" }, testCasePriority = { "19" })
	@Test(priority = 19, enabled = true, groups = { "Regression", "Lead" })

	public void viewEditFromRecentItems() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		leadDetailPage.get().clickOnCloseButton();

		commonProductFunctions.get().clickOnObjectInRecentlyAccessedSection(expectedLastName.get());
		String actualLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, "LastName");
		s.get().assertEquals(actualLeadName, expectedLastName.get(),
				"actualLeadName not equals expectedLastName.get()");

		String expectedEditedLeadName = "Edited From Recent" + ReUsableMethods.getCurrentdateTime();

		leadDetailPage.get().clickOnEditButton();
		leadCreationPage.get().enterLastNameWithoutTime(expectedEditedLeadName);
		leadCreationPage.get().clickOnSaveButton();

		String actualEditedLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, "LastName");
		s.get().assertEquals(actualEditedLeadName, expectedEditedLeadName,
				"actualEditedLeadName not equals expectedEditedLeadName");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184661" }, scriptType = {
			"" }, testCasePriority = { "20" })
	@Test(priority = 20, enabled = true, groups = { "Regression", "Lead" })

	public void editLeadFromHomePageListing() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		leadDetailPage.get().clickOnCloseButton();

		commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Created", "Last Name",
				expectedLastName.get());
		String actualLeadName = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");

		s.get().assertEquals(actualLeadName, expectedLastName.get(),
				"actualLeadName not equals expectedLastName.get()");

		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		String expectedEditedLeadName = "Edited From Recent" + ReUsableMethods.getCurrentdateTime();

		leadCreationPage.get().enterLastNameWithoutTime(expectedEditedLeadName);
		leadCreationPage.get().clickOnSaveButton();

		String actualEditedLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, "LastName");
		s.get().assertEquals(actualEditedLeadName, expectedEditedLeadName,
				"actualEditedLeadName not equals expectedEditedLeadName");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184606",
			"184674" }, scriptType = { "" }, testCasePriority = { "21" })
	@Test(priority = 21, enabled = true, groups = { "Regression", "Lead" })

	public void disQualifyLeadFromLeadDetail() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		// Click on DisQualify Button
		String parentHandle = ReUsableMethods.getWindow();
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyButton, "Disqualify Button");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
				"Disqualify Discription");
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");

		ReUsableMethods.switchToWindow(parentHandle);

		commonProductFunctions.get().waitForLoader();

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		boolean leadDisQualified = ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().activateBtn,
				"Activate Btn");
		boolean leadEditBtnDisplayed = ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().LeadEditButton,
				"Activate Btn");

		leadDetailPage.get().clickOnCloseButton();

		// Lead DisQualified Listing

		commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "Today", "Last Name",
				expectedLastName.get());
		String actualDisqualifiedToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
		s.get().assertEquals(actualDisqualifiedToday, expectedLastName.get(),
				"actualDisqualifiedToday not Equals lastNameAssigned");

		s.get().assertFalse(leadEditBtnDisplayed, "lead Edit Btn not Displayed");
		s.get().assertTrue(leadDisQualified, "leadDisQualified is not displayed");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184669" }, scriptType = {
			"" }, testCasePriority = { "22" })
	@Test(priority = 22, enabled = true, groups = { "Regression", "Lead" })

	public void disQualifyLeadFromStatusCode() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		// Select status code on DisQualify Lead
		String parentHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnEditButton();
		leadCreationPage.get().selectLeadStatusCode("Disqualified");
		leadCreationPage.get().clickOnSave_Lead();

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
				"Disqualify Discription");
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");

		ReUsableMethods.switchToWindow(parentHandle);

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		boolean leadDisQualified = ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().activateBtn,
				"Activate Btn");

		s.get().assertTrue(leadDisQualified);
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184607" }, scriptType = {
			"" }, testCasePriority = { "23" })
	@Test(priority = 23, enabled = true, groups = { "Regression", "Lead" })

	public void activateDisQualifyLeadFromLeadDetail() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		// Click on DisQualify Button
		String parentHandle = ReUsableMethods.getWindow();
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyButton, "Disqualify Button");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
				"Disqualify Discription");
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");

		ReUsableMethods.switchToWindow(parentHandle);

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		boolean leadDisQualified = ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().activateBtn,
				"Activate Btn");

		s.get().assertTrue(leadDisQualified, "Lead is not Dis Qualified");
		// Activate Disqualified Lead
		ReUsableMethods.webClickElement(leadDetailPage.get().activateBtn, "Activate Btn");
		commonProductFunctions.get().clickOkButtonForMassUpdate();

		boolean leadActivated = ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().disqualifyLeadBtn,
				"Disqualify Lead Button");

		s.get().assertTrue(leadActivated, "Lead is not Activated");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184752",
			"184604" }, scriptType = { "" }, testCasePriority = { "24" })
	@Test(priority = 24, enabled = true, groups = { "Regression", "Lead" })

	public void deleteLeadRelatedactivityIndependent() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		// Create New Activity
		String parentWindowHandle = ReUsableMethods.getWindow();
		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOn_NewTaskLink();
		commonProductFunctions.get().clickOnLayout("Task_System");
		ReUsableMethods.switchToChildWindowHandle();
		String subject = relatedActivityPage.get().enterSubject_LogACall();
		relatedActivityPage.get().clickOn_SaveButton_Task();
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);
		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().searchByAttribute("All New Leads", "Today", "Last Name", expectedLastName.get());
		// Delete Lead activities Independent

		commonProductFunctions.get().massDeleteDependentAndIndependentfromviews("DeleteIndependent");
		commonProductFunctions.get().waitForLoader();
		// Validate Lead deleted
		commonProductFunctions.get().advanceSearch(expectedLastName.get());
		boolean leadDeleted = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(leadDeleted, "Lead is not Deleted");

		// Validate that related activity not deleted
		navigationPanel.get().NavigateToObject("Task");

		commonProductFunctions.get().advanceSearch(subject);
		// boolean taskNotDeleted =
		// commonProductFunctions.get().isNoDataExistDisplayed();

		String actualSubjectForTask = taskDetailPage.get().getTaskSubject();
		s.get().assertEquals(actualSubjectForTask, subject, "Task Subject is not verified");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184753",
			"184754" }, scriptType = { "" }, testCasePriority = { "25" })
	@Test(priority = 25, enabled = true, groups = { "Regression", "Lead" })

	public void deleteLeadWithRelatedactivity() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		// Create New Activity
		String parentWindowHandle = ReUsableMethods.getWindow();
		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOn_NewTaskLink();
		commonProductFunctions.get().clickOnLayout("Task_System");
		ReUsableMethods.switchToChildWindowHandle();
		String subject = relatedActivityPage.get().enterSubject_LogACall();
		relatedActivityPage.get().clickOn_SaveButton_Task();
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);
		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().searchByAttribute("All New Leads", "Today", "Last Name", expectedLastName.get());
		// Delete Lead activities Independent

		commonProductFunctions.get().massDeleteDependentAndIndependentfromviews("DeleteDependent");
		commonProductFunctions.get().waitForLoader();
		// Validate Lead deleted
		commonProductFunctions.get().advanceSearch(expectedLastName.get());
		boolean leadDeleted = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(leadDeleted, "Lead is not Deleted");

		// Validate that related activity not deleted
		navigationPanel.get().NavigateToObject("Task");

		commonProductFunctions.get().advanceSearch(subject);
		commonProductFunctions.get().waitForLoader();
		boolean activityDeleted = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(activityDeleted, "Activity is not Deleted");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184602" }, scriptType = {
			"" }, testCasePriority = { "26" })
	@Test(priority = 26, enabled = true, groups = { "Regression", "Lead" })

	public void activateDeletedLead() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		// Delete Lead
		ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");
		// ReUsableMethods.clickCheckBoxUsingJavaScript(leadDetailPage.get().chckboxDltPermanently);

		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on Delete Button");
		// Search Deleted Lead
		commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Today", "Last Name",
				expectedLastName.get());
		ReUsableMethods.webClickElement(leadHomePage.get().searchedLeadName, "Searched Lead Name");
		// Activate Deleted Lead
		ReUsableMethods.scrollElementToCentreOfScreen(leadDetailPage.get().activateBtn);
		ReUsableMethods.webClickElement(leadDetailPage.get().activateBtn, "Activate Btn");
		commonProductFunctions.get().clickOkButtonForMassUpdate();
		leadDetailPage.get().waitforEditButton();

		ReUsableMethods.scrollElementToCentreOfScreen(leadDetailPage.get().Delete);

		boolean leadActivated = ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().Delete,
				"Delete Lead Button");

		s.get().assertTrue(leadActivated, "Lead is not Activated");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184603" }, scriptType = { "" }, testCasePriority = { "27" })
	@Test(priority = 27, enabled = true, groups = { "Regression", "Lead" })

	public void leadMassUpdate() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Lead");

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().searchByAttribute("All New Leads", "All", "Last Name", expectedLastName.get());

		String expectedupdatedSubject = LeadConstants.LEAD_LASTNAME1
				+ ReUsableMethods.getCurrentdateTime();
		leadHomePage.get().massUpdateLeadFromHomePage("Last Name", expectedupdatedSubject);

		//leadHomePage.get().clickOnMassUpdate();
		commonProductFunctions.get().searchByAttribute("All New Leads", "All", "Last Name", expectedupdatedSubject);

		String actualUpdatedSubject = ReUsableMethods.WebGetElementText(leadHomePage.get().firstLead, "Last name");
		s.get().assertEquals(actualUpdatedSubject, expectedupdatedSubject, "Last name do not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184703",
			"184704" }, scriptType = { "" }, testCasePriority = { "28" })
	@Test(priority = 28, enabled = true, groups = { "Regression", "Lead" })

	public void leadAccountFieldMapping() throws Exception {
		login.get().genericAdminLogin(webURL, adminUserMaster_Auto2, webpassword);
		navigationPanel.get().NavigateToObject("Lead");
		String parentWindowHandle = ReUsableMethods.getWindow();

		commonProductFunctions.get().clickOnToolboxMenuItem("Account Lead Mapping");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webClickElement(assignmentRuleCreationPage.get().mappingFieldSearch,
				"Mapping Lead Fields to account");

		commonProductFunctions.get().searchAndSelectCheckBox_UsingParameter("AadharCardNumber");

		ReUsableMethods.webClickElement(leadHomePage.get().aadharMapping, "Aadhar number Mapping");

		triggerActionCreationPage.get().clickAadharNoPicker();

		commonProductFunctions.get().selectNameFromPicker("AadharCardNumber");

		commonProductFunctions.get().clickOnSave();

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Account");

		commonProductFunctions.get().clickOnNewIcon();

		accountCreationPage.get().accountCreate(AccountConstant.LAYOUT_ACCOUNT_SYSTEM, AccountConstant.ACCOUNT_NAME);

		commonProductFunctions.get().clickOnEdit();

		String expectedAadharNumber = "123456789012";
		ReUsableMethods.webEnterText(accountCreationPage.get().aadharNumber, expectedAadharNumber, "Aadhar number");

		commonProductFunctions.get().clickOnSave();

		relatedActivityPage.get().clickOnModule("Related Lead");

		relatedActivityPage.get().clickOnLink("New Lead");

		relatedActivityPage.get().clickOnLayout("Lead_System");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.scrollElementToCentreOfScreen(leadCreationPage.get().aadharNumberMapping);

		//Thread.sleep(2000);
		String actualAadharNumber = ReUsableMethods.getTextElementAttribute(leadCreationPage.get().aadharNumberMapping,
				"Value");
		s.get().assertEquals(actualAadharNumber, expectedAadharNumber, "Aadhar Card do not match");

		commonProductFunctions.get().clickOnCancel();

		ReUsableMethods.switchToWindow(parentWindowHandle);

		commonProductFunctions.get().clickOnClose();

		login.get().genericAdminLogin(webURL, adminUserMaster_Auto2, webpassword);
		navigationPanel.get().NavigateToObject("Lead");

		commonProductFunctions.get().clickOnToolboxMenuItem("Account Lead Mapping");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webClickElement(leadHomePage.get().clear, "Clear the saved mapping");

		relatedActivityPage.get().clickOnOkButton();

		boolean isMappingPickerVisible = ReUsableMethods
				.WebIsElementDisplayed(triggerActionCreationPage.get().aadharNoPicker, "AAdhar number Picker");
		s.get().assertFalse(isMappingPickerVisible, "Aadhar Picker is visible");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184784" }, scriptType = {
			"" }, testCasePriority = { "29" })
	@Test(priority = 29, enabled = true, groups = { "Regression", "Lead" })

	public void showLeadListingWithoutApplyingFilters() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Lead");

		commonProductFunctions.get().clickOnToolboxMenuItem("Mass Update");

		// ReUsableMethods.webClickElement(assignmentRuleCreationPage.get().criteriaFieldSearch,
		// 		" Lead Fields to add filter");

		// commonProductFunctions.get().searchAndSelectCheckBox_UsingParameter("LastName");

		// commonProductFunctions.get().chooseFieldOperator("Last Name", "Equal");

		// leadCreationPage.get().enterLeadLastName(expectedLastName.get());

		ReUsableMethods.webClickElement(leadHomePage.get().showItemListMassUpdateCheckBox, "Show Item List Checkbox");

		ReUsableMethods.webClickElement(commonProductFunctions.get().nextButtonOnMassUpdatePage, "Next Button");
		
		List<WebElement> leadNames = ReUsableMethods.findElementByPath(leadHomePage.get().leadListingPath);
		List<String> leadNameList = ReUsableMethods.getListOfText_FromElementList(leadNames);
		boolean hasMoreThanOneLead = leadNameList.size() > 1;
		s.get().assertTrue(hasMoreThanOneLead, "Lead listing does not show multiple leads");

		s.get().assertAll();


	}

}
