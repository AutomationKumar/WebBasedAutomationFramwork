package com.businessnext.leads.testcases;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.alertrule.pages.AlertRuleHomepage;
import com.businessnext.assignmentrule.pages.AssignmentRuleCreationPage;
import com.businessnext.charge.pages.ChargeConstantPage;
import com.businessnext.composeemail.pages.ComposeEmailConstants;
import com.businessnext.composeemail.pages.ComposeEmailCreationPage;
import com.businessnext.contentlibrary.pages.WorkSpaceLibraryCreationPage;
import com.businessnext.object.assets.pages.AssetsConstants;
import com.businessnext.object.assets.pages.AssetsCreationPage;
import com.businessnext.object.assets.pages.AssetsDetailPage;
import com.businessnext.object.assets.pages.AssetsHomePage;
import com.businessnext.objects.account.pages.AccountConstant;
import com.businessnext.objects.account.pages.AccountCreationPage;
import com.businessnext.objects.account.pages.AccountDetailPage;
import com.businessnext.objects.alert.pages.AlertConstant;
import com.businessnext.objects.alert.pages.AlertCreationPage;
import com.businessnext.objects.alert.pages.AlertDetailPage;
import com.businessnext.objects.application.pages.ApplicationConstants;
import com.businessnext.objects.application.pages.ApplicationCreationPage;
import com.businessnext.objects.application.pages.ApplicationDetailPage;
import com.businessnext.objects.application.pages.ApplicationHomePage;
import com.businessnext.objects.appointment.AppointmentCreationPage;
import com.businessnext.objects.approval.pages.ApprovalConstants;
import com.businessnext.objects.approval.pages.ApprovalCreationPage;
import com.businessnext.objects.approval.pages.ApprovalDetailPage;
import com.businessnext.objects.approval.pages.ApprovalHomePage;
import com.businessnext.objects.budget.pages.BudgetDetailpage;
import com.businessnext.objects.cases.pages.CaseConstants;
import com.businessnext.objects.cases.pages.CasesDetailPage;
import com.businessnext.objects.contact.pages.ConstantClassContact;
import com.businessnext.objects.contact.pages.ContactCreationPage;
import com.businessnext.objects.contact.pages.ContactDetailPage;
import com.businessnext.objects.contact.pages.ContactHomePage;
import com.businessnext.objects.delight.pages.DelightConstants;
import com.businessnext.objects.delight.pages.DelightCreationPage;
import com.businessnext.objects.delight.pages.DelightDetailPage;
import com.businessnext.objects.dispatch.pages.DispatchConstants;
import com.businessnext.objects.dispatch.pages.DispatchCreationPage;
import com.businessnext.objects.dispatch.pages.DispatchDetailPage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.businessnext.objects.note.pages.NoteCreationPage;
import com.businessnext.objects.opportunity.pages.OpportunityConstants;
import com.businessnext.objects.opportunity.pages.OpportunityDetailPage;
import com.businessnext.objects.playbook.pages.PlaybookHomePage;
import com.businessnext.objects.portfolio.pages.PortfolioNewEditPage;
import com.businessnext.objects.requirement.pages.RequirementConstants;
import com.businessnext.objects.requirement.pages.RequirementCreationPage;
import com.businessnext.objects.requirement.pages.RequirementDetailPage;
import com.businessnext.objects.requirement.pages.RequirementHomePage;
import com.businessnext.objects.review.pages.ReviewConstants;
import com.businessnext.objects.review.pages.ReviewCreationPage;
import com.businessnext.objects.review.pages.ReviewDetailPage;
import com.businessnext.objects.sanction.pages.SanctionConstant;
import com.businessnext.objects.task.pages.TaskDetailPage;
import com.businessnext.product.pages.ProductHomepage;
import com.businessnext.quickLinks.pages.QuickLinksCreationPage;
import com.businessnext.sharing.pages.SharingConstants;
import com.businessnext.sharing.pages.SharingCreationPage;
import com.businessnext.toolbar.customfield.pages.CustomFieldConstants;
import com.businessnext.toolbar.customfield.pages.CustomFieldPage;
import com.businessnext.toolbar.leadQualifyAssignmentRule.pages.LeadQualifyAssignmentRuleConstant;
import com.businessnext.toolbar.leadQualifyAssignmentRule.pages.LeadQualifyAssignmentRuleCreationPage;
import com.businessnext.toolbar.leadQualifyAssignmentRule.pages.LeadQualifyAssignmentRuleDetailPage;
import com.businessnext.toolbar.leadQualifyAssignmentRule.pages.LeadQualifyAssignmentRuleHomePage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.LayoutConstants;
import com.common.pages.NavigationPanel;
import com.common.pages.ObjectConstants;
import com.common.pages.RelatedActivityPage;
import com.common.pages.RelatedObjectLinkConstants;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.AssertionUtils;
import com.utilities.Constants;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)

public class LeadRelatedObjectsII extends BaseClass {
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<LeadHomePage> leadHomePage = new ThreadLocal<>();
	ThreadLocal<LeadCreationPage> leadCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadDetailPage> leadDetailPage = new ThreadLocal<>();
	ThreadLocal<LeadCommonFunctions> leadCommonFunctions = new ThreadLocal<>();
	ThreadLocal<RelatedActivityPage> relatedActivityPage = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<AccountDetailPage> accountDetailPage = new ThreadLocal<>();
	ThreadLocal<String> expectedLastName = new ThreadLocal<>();
	ThreadLocal<NoteCreationPage> noteCreationPage = new ThreadLocal<>();
	ThreadLocal<AppointmentCreationPage> appointmentCreationPage = new ThreadLocal<>();
	ThreadLocal<ReviewCreationPage> reviewCreationPage = new ThreadLocal<>();
	ThreadLocal<ReviewDetailPage> reviewDetailPage = new ThreadLocal<>();
	ThreadLocal<DispatchCreationPage> dispatchCreationPage = new ThreadLocal<>();
	ThreadLocal<DispatchDetailPage> dispatchDetailPage = new ThreadLocal<>();
	ThreadLocal<ContactCreationPage> contactCreationPage = new ThreadLocal<>();
	ThreadLocal<ContactDetailPage> contactDetailPage = new ThreadLocal<>();
	ThreadLocal<ContactHomePage> contactHomePage = new ThreadLocal<>();
	ThreadLocal<DelightCreationPage> delightCreationPage = new ThreadLocal<>();
	ThreadLocal<DelightDetailPage> delightDetailPage = new ThreadLocal<>();
	ThreadLocal<ApprovalDetailPage> approvalDetailPage = new ThreadLocal<>();
	ThreadLocal<ApprovalCreationPage> approvalCreationPage = new ThreadLocal<>();
	ThreadLocal<WorkSpaceLibraryCreationPage> workSpaceLibraryCreationPage = new ThreadLocal<>();
	ThreadLocal<CasesDetailPage> casesDetailPage = new ThreadLocal<>();
	ThreadLocal<PortfolioNewEditPage> portfolioNewEditPage = new ThreadLocal<>();
	ThreadLocal<ApprovalHomePage> approvalHomePage = new ThreadLocal<>();
	ThreadLocal<ProductHomepage> productHomepage = new ThreadLocal<>();
	ThreadLocal<QuickLinksCreationPage> quickLinksCreationPage = new ThreadLocal<>();
	ThreadLocal<BudgetDetailpage> budgetDetailPage = new ThreadLocal<>();
	ThreadLocal<AssetsHomePage> assetsHomePage = new ThreadLocal<>();
	ThreadLocal<AssetsCreationPage> assetCreationPage = new ThreadLocal<>();
	ThreadLocal<AssetsDetailPage> assetsDetailPage = new ThreadLocal<>();
	ThreadLocal<AccountCreationPage> accountCreationPage = new ThreadLocal<>();
	ThreadLocal<ApplicationHomePage> applicationHomePage = new ThreadLocal<>();
	ThreadLocal<ApplicationDetailPage> applicationDetailPage = new ThreadLocal<>();
	ThreadLocal<ApplicationCreationPage> applicationCreationPage = new ThreadLocal<>();
	ThreadLocal<CustomFieldPage> customFieldPage = new ThreadLocal<>();
	ThreadLocal<SharingCreationPage> sharingCreationPage = new ThreadLocal<>();
	ThreadLocal<AssignmentRuleCreationPage> assignmentRuleCreationPage = new ThreadLocal<>();
	ThreadLocal<PlaybookHomePage> playbookHomePage = new ThreadLocal<>();
	ThreadLocal<OpportunityDetailPage> opportunityDetailPage = new ThreadLocal<>();
	ThreadLocal<LeadQualifyAssignmentRuleCreationPage> leadQualifyAssignmentRuleCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadQualifyAssignmentRuleDetailPage> leadQualifyAssignmentRuleDetailPage = new ThreadLocal<>();
	ThreadLocal<LeadQualifyAssignmentRuleHomePage> leadQualifyAssignmentRuleHomePage = new ThreadLocal<>();
	ThreadLocal<ComposeEmailCreationPage> composeEmailCreationPage = new ThreadLocal<>();
	ThreadLocal<TaskDetailPage> taskDetailPage = new ThreadLocal<>();
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
		relatedActivityPage.set(new RelatedActivityPage(DriverManager.getWdriver()));
		noteCreationPage.set(new NoteCreationPage(DriverManager.getWdriver()));
		appointmentCreationPage.set(new AppointmentCreationPage(DriverManager.getWdriver()));
		expectedLastName.set(ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime());
		accountCreationPage.set(new AccountCreationPage(DriverManager.getWdriver()));
		s.set(new SoftAssert());
		playbookHomePage.set(new PlaybookHomePage(DriverManager.getWdriver()));
		reviewCreationPage.set(new ReviewCreationPage(DriverManager.getWdriver()));
		reviewDetailPage.set(new ReviewDetailPage(DriverManager.getWdriver()));
		expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
		expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;
		expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
		dispatchCreationPage.set(new DispatchCreationPage(DriverManager.getWdriver()));
		dispatchDetailPage.set(new DispatchDetailPage(DriverManager.getWdriver()));
		contactCreationPage.set(new ContactCreationPage(DriverManager.getWdriver()));
		contactDetailPage.set(new ContactDetailPage(DriverManager.getWdriver()));
		contactHomePage.set(new ContactHomePage(DriverManager.getWdriver()));
		delightCreationPage.set(new DelightCreationPage(DriverManager.getWdriver()));
		delightDetailPage.set(new DelightDetailPage(DriverManager.getWdriver()));
		accountDetailPage.set(new AccountDetailPage(DriverManager.getWdriver()));
		approvalDetailPage.set(new ApprovalDetailPage(DriverManager.getWdriver()));
		approvalCreationPage.set(new ApprovalCreationPage(DriverManager.getWdriver()));
		workSpaceLibraryCreationPage.set(new WorkSpaceLibraryCreationPage(DriverManager.getWdriver()));
		casesDetailPage.set(new CasesDetailPage(DriverManager.getWdriver()));
		approvalHomePage.set(new ApprovalHomePage(DriverManager.getWdriver()));
		portfolioNewEditPage.set(new PortfolioNewEditPage(DriverManager.getWdriver()));
		productHomepage.set(new ProductHomepage(DriverManager.getWdriver()));
		quickLinksCreationPage.set(new QuickLinksCreationPage(DriverManager.getWdriver()));
		budgetDetailPage.set(new BudgetDetailpage(DriverManager.getWdriver()));
		assetsHomePage.set(new AssetsHomePage(DriverManager.getWdriver()));
		assetCreationPage.set(new AssetsCreationPage(DriverManager.getWdriver()));
		assetsDetailPage.set(new AssetsDetailPage(DriverManager.getWdriver()));
		applicationHomePage.set(new ApplicationHomePage(DriverManager.getWdriver()));
		applicationCreationPage.set(new ApplicationCreationPage(DriverManager.getWdriver()));
		customFieldPage.set(new CustomFieldPage(DriverManager.getWdriver()));
		applicationDetailPage.set(new ApplicationDetailPage(DriverManager.getWdriver()));
		sharingCreationPage.set(new SharingCreationPage(DriverManager.getWdriver()));
		assignmentRuleCreationPage.set(new AssignmentRuleCreationPage(DriverManager.getWdriver()));
		opportunityDetailPage.set(new OpportunityDetailPage(DriverManager.getWdriver()));
		leadQualifyAssignmentRuleCreationPage
				.set(new LeadQualifyAssignmentRuleCreationPage(DriverManager.getWdriver()));
		leadQualifyAssignmentRuleDetailPage.set(new LeadQualifyAssignmentRuleDetailPage(DriverManager.getWdriver()));
		leadQualifyAssignmentRuleHomePage.set(new LeadQualifyAssignmentRuleHomePage(DriverManager.getWdriver()));
		taskDetailPage.set(new TaskDetailPage(DriverManager.getWdriver()));

		composeEmailCreationPage.set(new ComposeEmailCreationPage(DriverManager.getWdriver()));
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "179158",
			"184660" }, scriptType = { "" }, testCasePriority = { "1" })

	@Test(priority = 1, enabled = true, groups = { "Regression", "Lead" })

	public void logACallLeadHome() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Created", "Last Name",
				expectedLastName.get());

		ReUsableMethods.safeClick(leadHomePage.get().threeDots, leadHomePage.get().logACallBtn);
		ReUsableMethods.webClickElement(leadHomePage.get().logACallBtn, "Log A Call");

		commonProductFunctions.get().clickOnLayout("Appointment_System");

		ReUsableMethods.switchToChildWindowHandle();

		String expectedAppointment_SystemSubjectName = ChargeConstantPage.APPOINTMENTSYSTEM_SUBJECTNAME_SUBJECT
				+ ReUsableMethods.getCurrentdateTime();
		appointmentCreationPage.get().enterAppointmentSubject(expectedAppointment_SystemSubjectName);
		commonProductFunctions.get().clickSaveOrSaveAndNewOnCreationPage("Save");
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);
		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		commonProductFunctions.get().waitForLoader();

		relatedActivityPage.get().clickOn_RelatedActivity();

		relatedActivityPage.get().searchByRelatedUsingActivities("Closed Activities", "Subject",
				expectedAppointment_SystemSubjectName);

		String actualAppoinmentSubject = relatedActivityPage.get().verifyActivity("Open Activities",
				expectedAppointment_SystemSubjectName);

		s.get().assertEquals(actualAppoinmentSubject, expectedAppointment_SystemSubjectName,
				"actual Appoinment Subject not Equals expected Appoinment Subject");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184594",
			"184675" }, scriptType = { "" }, testCasePriority = { "2" })

	@Test(priority = 2, enabled = true, groups = { "Regression", "Lead" })

	public void logACallLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		ReUsableMethods.scrollElementToCentreOfScreen(leadDetailPage.get().logACallBtn);
		ReUsableMethods.webClickElement(leadDetailPage.get().logACallBtn, "log A Call Btn");

		commonProductFunctions.get().clickOnLayout("Appointment_System");

		ReUsableMethods.switchToChildWindowHandle();

		String expectedAppointment_SystemSubjectName = ChargeConstantPage.APPOINTMENTSYSTEM_SUBJECTNAME_SUBJECT
				+ ReUsableMethods.getCurrentdateTime();
		appointmentCreationPage.get().enterAppointmentSubject(expectedAppointment_SystemSubjectName);
		commonProductFunctions.get().clickSaveOrSaveAndNewOnCreationPage("Save");
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);
		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		commonProductFunctions.get().waitForLoader();

		relatedActivityPage.get().clickOn_RelatedActivity();

		relatedActivityPage.get().searchByRelatedUsingActivities("Closed Activities", "Subject",
				expectedAppointment_SystemSubjectName);

		String actualAppoinmentSubject = relatedActivityPage.get().verifyActivity("Open Activities",
				expectedAppointment_SystemSubjectName);

		s.get().assertEquals(actualAppoinmentSubject, expectedAppointment_SystemSubjectName,
				"actual Appoinment Subject not Equals expected Appoinment Subject");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184570" }, scriptType = {
			"" }, testCasePriority = { "3" })
	@Test(priority = 3, enabled = true, groups = { "Regression", "Lead" })

	public void leadRequirementCreationAndView() throws Exception {

		RequirementCreationPage requirementCreationPage = new RequirementCreationPage(DriverManager.getWdriver());
		RequirementDetailPage requirementDetailPage = new RequirementDetailPage(DriverManager.getWdriver());

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Collaboration");
		relatedActivityPage.get().clickOnNewRequirementLinkLink();
		relatedActivityPage.get().clickOnLayout("Requirements_System");
		ReUsableMethods.switchToChildWindowHandle();

		String expectedRequirementSubject = requirementCreationPage.enterRequirementSubject(RequirementConstants.REQUIREMENTS_SUBJECT);
		commonProductFunctions.get().clickOnSave();

		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		ReUsableMethods.scrollElementToCentreOfScreen(relatedActivityPage.get().relatedSubject);

		String actualRequirementSubject = relatedActivityPage.get().getRelatedSubject();
		s.get().assertEquals(actualRequirementSubject, expectedRequirementSubject, "Subject Do not match");

		ReUsableMethods.webClickElement(relatedActivityPage.get().activitySubject, expectedRequirementSubject);

		String actualRequirementRelatedTo = requirementDetailPage.getRelatedTo();
		s.get().assertEquals(actualRequirementRelatedTo, expectedLastName.get(), "Related Subject do not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184572", "184601",
			"184745" }, scriptType = { "" }, testCasePriority = { "4" })
	@Test(priority = 4, enabled = true, groups = { "Regression", "Lead" })

	public void leadALertCreationAndView() throws Exception {

		AlertCreationPage alertCreationPage = new AlertCreationPage(DriverManager.getWdriver());
		AlertDetailPage alertDetailPage = new AlertDetailPage(DriverManager.getWdriver());

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Alert");
		relatedActivityPage.get().clickOnLink("New Alert");
		HashMap<String, String> map = alertCreationPage.createAlert(AlertConstant.LAYOUT_ALERT_SYSTEM,
				AlertConstant.Alert_Subject, AlertConstant.AlERT_TITLE, "New");
		String expectedAlertSubject = map.get("alertSubject");
		String expectedAlertTitle = map.get("alertTitle");
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualAlertTitleOnDetail = alertDetailPage.getAlertRelatedTitle();
		s.get().assertEquals(actualAlertTitleOnDetail, expectedAlertTitle, "Alert Title donot match");

		ReUsableMethods.webClickElement(alertDetailPage.alertTitleForRelated, "Alert Title");

		ReUsableMethods.switchToChildWindowHandle();

		String actualAlertSubject = alertDetailPage.getAlertSubject();
		s.get().assertEquals(actualAlertSubject, expectedAlertSubject, "Alert Subject donot match");

		String actualAlertRelatedTo = alertDetailPage.getAlertRelatedTo();
		s.get().assertEquals(actualAlertRelatedTo, expectedLastName.get(), "Related To Case do not match");

		ReUsableMethods.webClickElement(relatedActivityPage.get().edit, "Edit Alert");

		ReUsableMethods.switchToChildWindowHandle();

		String expectedEdittedAlertTitle = alertCreationPage.enterAlertTitle(AlertConstant.AlERT_TITLE + "-edit");

		String expectedStatusCode = AlertConstant.ALERT_PRIORITY_CLOSED;
		ReUsableMethods.webSelectByVisibleText(alertCreationPage.statusCode, expectedStatusCode, "Status Code");

		relatedActivityPage.get().clickOn_SaveButton_LogACall();

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualEdittedAlertTitleOnDetail = alertDetailPage.getAlertRelatedTitle();
		s.get().assertEquals(actualEdittedAlertTitleOnDetail, expectedEdittedAlertTitle, "Title donot match");

		String actualStatusCode = relatedActivityPage.get().getStatusCodeOnOfferHistory();
		s.get().assertEquals(actualStatusCode, expectedStatusCode, "Status code is not same");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184573" }, scriptType = {
			"" }, testCasePriority = { "5" })
	@Test(priority = 5, enabled = true, groups = { "Regression", "Lead" })

	public void leadChildLeadCreation() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Lead Child");
		relatedActivityPage.get().clickOnLink("New Lead");

		relatedActivityPage.get().clickOnLayout("Lead_System");

		ReUsableMethods.switchToChildWindowHandle();
		String expectedChildLeadName = leadCreationPage.get().enterLastLeadName(LeadConstants.LASTNAME_KUMAR);

		leadCreationPage.get().selectLeadRating(LeadConstants.LEAD_HOT_RATING);
		leadCreationPage.get().selectLeadProduct(LeadConstants.PRODUCT_GOLD_LOAN);

		ReUsableMethods.scrollElementToCentreOfScreen(leadCreationPage.get().leadParent);
		String actualLeadParent = ReUsableMethods.getTextElementAttribute(leadCreationPage.get().leadParent, "value");
		s.get().assertEquals(actualLeadParent, expectedLastName.get(), "Lead Parents do not match");

		commonProductFunctions.get().clickOnSave();

		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualChildLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().firstLeadDedupeListing,
				"Child Lead Name");
		s.get().assertEquals(actualChildLeadName, expectedChildLeadName, "Child Lead do not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184574",
			"184679" }, scriptType = { "Configuration" }, testCasePriority = { "" })
	@Test(priority = 6, enabled = true, groups = { "Regression", "Lead" })

	public void leadReviewCreation() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Review");

		relatedActivityPage.get().clickOnLink("New Review");

		String expectedReviewTitle = reviewCreationPage.get().createReview(ReviewConstants.REVIEW_SYSTEM_LAYOUT,
				ReviewConstants.REVIEW_SUBJECT, Constants.SAVE_BUTTON, "", "");
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualReviewTitle = reviewDetailPage.get().getRelatedNameDetail();
		s.get().assertEquals(actualReviewTitle, expectedReviewTitle, "Title not match");

		ReUsableMethods.webClickElement(reviewDetailPage.get().nameRelated, "Related Title");

		ReUsableMethods.switchToChildWindowHandle();

		String actualReviewTitleOnReviewDetail = reviewDetailPage.get().getNameDetail();
		s.get().assertEquals(actualReviewTitleOnReviewDetail, expectedReviewTitle, "Title not match");

		boolean isRelatedOnReviewDetail = ReUsableMethods.WebIsElementDisplayed(reviewDetailPage.get().relatedToField,
				"Related Case");
		s.get().assertTrue(isRelatedOnReviewDetail, "Related Case not clickable");

		// navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		//
		// commonProductFunctions.get().advanceSearch(expectedLastName.get());
		//
		// relatedActivityPage.get().clickOnModule("Related Review");
		//
		// leadDetailPage.get().SelectReviewType("Review By Me");
		//
		// s.get().assertEquals(actualReviewTitle, expectedReviewTitle, "Title not
		// match");
		//
		// leadDetailPage.get().SelectReviewType("Review For Me");
		//
		// s.get().assertEquals(actualReviewTitle, expectedReviewTitle, "Title not
		// match");

		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184575",
			"184747" }, scriptType = { "" }, testCasePriority = { "7" })
	@Test(priority = 7, enabled = true, groups = { "Regression", "Lead" })

	public void leadDispatchCreation() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		String actualLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().lead_lastName, "Last Name");
		s.get().assertEquals(actualLeadName, expectedLastName.get(), "last Name do not match");

		relatedActivityPage.get().clickOnModule("Related Dispatch");
		relatedActivityPage.get().clickOnLink("New Dispatch");

		String expectedDispatchSubject = dispatchCreationPage.get().createDispatch("Dispatch_System",
				DispatchConstants.Dispatch_Subject, DispatchConstants.Dispatch_GeneratedOnTime, "Save");

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualDispatchSubject = dispatchDetailPage.get().getcallLogListSubject();
		s.get().assertEquals(actualDispatchSubject, expectedDispatchSubject, "Subject do not match");

		ReUsableMethods.webClickElement(dispatchDetailPage.get().callLogListSubject, "Dispatch Subject");

		String actualDispatchRelatedTo = dispatchDetailPage.get().getRelatedToACtivities();
		s.get().assertEquals(actualDispatchRelatedTo, expectedLastName.get(), "Related Case do not match");

		ReUsableMethods.webClickElement(dispatchDetailPage.get().relatedTo, "Related Case");
		s.get().assertEquals(actualLeadName, expectedLastName.get(), "Dispatch Subject do not match");

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		relatedActivityPage.get().clickOnModule("Related Dispatch");

		ReUsableMethods.webClickElement(relatedActivityPage.get().threeDotsOnListing, "Three Dots");

		relatedActivityPage.get().clickOnThreeDot_ActionMenu("Delete");

		relatedActivityPage.get().clickOnOkButton();

		boolean noDataExists = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(noDataExists, "Data exists");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184576",
			"184678" }, scriptType = { "" }, testCasePriority = { "8" })
	@Test(priority = 8, enabled = true, groups = { "Regression", "Lead" })

	public void leadContactCreation() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		String actualLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().lead_lastName, "Last Name");
		s.get().assertEquals(actualLeadName, expectedLastName.get(), "last Name do not match");

		relatedActivityPage.get().clickOnModule("Related Contact");
		relatedActivityPage.get().clickOnLink("New Contact");

		String expectedContactName = contactCreationPage.get()
				.enterContactDetailsAndSave(LayoutConstants.LAYOUT_CONTACT_SYSTEM, "Contact");

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualContactNameOnLeadDetail = ReUsableMethods.WebGetElementText(
				relatedActivityPage.get().firstNameRecordInRelatedList, "Related contact on detail page");
		s.get().assertEquals(actualContactNameOnLeadDetail, expectedContactName,
				"Contact name on Lead Detail do not match");

		ReUsableMethods.webClickElement(relatedActivityPage.get().firstNameRecordInRelatedList, "Related Contact Name");

		AssertionUtils.assertElementText(contactDetailPage.get().contactDetails, expectedContactName);

		commonProductFunctions.get().clickOnClose();

		navigationPanel.get().NavigateToObject("Contact");

		commonProductFunctions.get().searchByAttribute("All Contacts", "All", "Short Name", expectedContactName);

		AssertionUtils.assertElementText(contactHomePage.get().firstRecord, expectedContactName);

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		relatedActivityPage.get().clickOnModule("Related Contact");

		ReUsableMethods.webClickElement(relatedActivityPage.get().threeDotsOnListing, "Three Dots on Related Contact");
		relatedActivityPage.get().clickOnThreeDot_ActionMenu("Delete");

		relatedActivityPage.get().clickOnOkButton();

		boolean noDataexists = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(noDataexists, "Data exists");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184578",
			"184728" }, scriptType = { "" }, testCasePriority = { "9" })
	@Test(priority = 9, enabled = true, groups = { "Regression", "Lead", "Client" })

	public void leadComposeEmail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();
		String expectedEmailTemplate = LeadConstants.EMAIL_TEMPLATE_LEAD_TEMPLATE;

		relatedActivityPage.get().createComposeEmail(ApprovalConstants.COMPOSEEMAILTO_ANUPRIYA,
				SanctionConstant.USERNAME_NAME,
				"Closed", expectedEmailTemplate,
				expectedEmailTemplate, "sendEmail");
		commonProductFunctions.get().waitForLoader();

		ReUsableMethods.switchToWindow(parentWindowHandle);

		ReUsableMethods.scrollElementToCentreOfScreen(relatedActivityPage.get().closedActivity);

		String actualEmailSubject = ReUsableMethods.WebGetElementText(relatedActivityPage.get().activitySubject,
				"Subject do not match");

		s.get().assertEquals(actualEmailSubject, expectedEmailTemplate, "Email Subject is not verified");

		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184579",
			"184605" }, scriptType = {
					"" }, testCasePriority = { "10" })
	@Test(priority = 10, enabled = true, groups = { "Regression", "Lead" })

	public void leadMergeMail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Activity");

		relatedActivityPage.get().clickOnLink("Merge Mail");

		ReUsableMethods.switchToChildWindowHandle();

		String expectedEmailSubject = ApprovalConstants.COMPOSEEMAILSUBJECT_APPROVALEMAIL;
		relatedActivityPage.get().createMailMerge(ApprovalConstants.COMPOSEEMAILTO_ANUPRIYA, null, "Closed",
				expectedEmailSubject);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualClosedActivity = relatedActivityPage.get().verifyActivity("Closed Activities",
				expectedEmailSubject);

		s.get().assertEquals(actualClosedActivity, expectedEmailSubject, "Email Subject is not verified");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184580",
			"184597" }, scriptType = { "" }, testCasePriority = { "11" })
	@Test(priority = 11, enabled = true, groups = { "Regression", "Lead" })

	public void leadShowThread() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOn_NewTaskLink();
		commonProductFunctions.get().clickOnLayout("Task_System");
		ReUsableMethods.switchToChildWindowHandle();

		String subject = relatedActivityPage.get().enterSubject_LogACall();
		String actual_Lead = ReUsableMethods.getTextElementAttribute(reviewDetailPage.get().related_To, "value");
		s.get().assertEquals(actual_Lead, expectedLastName.get(), "Related Lead do not matches");

		relatedActivityPage.get().clickOn_SaveButton_Task();
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualOpenTask = relatedActivityPage.get().verifyActivity("Open Activities", subject);
		s.get().assertEquals(actualOpenTask, subject);

		relatedActivityPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_SHOW_THREAD);

		ReUsableMethods.switchToChildWindowHandle();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String expectedDueDate = formatter.format(new Date());

		String actualDueDate = ReUsableMethods.WebGetElementText(reviewDetailPage.get().dueDate, "dueDate");
		s.get().assertEquals(actualDueDate, expectedDueDate, "The DueDate is mismatch");

		String actualTaskName = ReUsableMethods.WebGetElementText(reviewDetailPage.get().ThreadSubject, "taskSubject");
		s.get().assertEquals(actualTaskName, subject, "The Task Name is mismatch");

		String actualStatus = ReUsableMethods.WebGetElementText(reviewDetailPage.get().statusThread, "taskStatus");
		s.get().assertEquals(actualStatus, "Cancelled", "The Status is mismatch");

		String actualPriority = reviewDetailPage.get().getPriorityTittle();
		s.get().assertEquals(actualPriority, RequirementConstants.PRIORITY, "The Priority is mismatch");

		String actualRelatedType = reviewDetailPage.get().getRelatedType();
		s.get().assertEquals(actualRelatedType, "Lead", "The RelatedType is mismatch");

		// playbookHomePage.get().clickOnPrintButton();

		// ReUsableMethods.switchToChildWindowHandle();

		// // System.out.println("Current windows size is " +
		// // ReUsableMethods.currentWindowSize());
		// boolean isPrintViewDisplay = ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().printHeading,
		// 		"printPage");
		// s.get().assertTrue(isPrintViewDisplay, "The print view page is not displayed");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184581" }, scriptType = {
			"" }, testCasePriority = { "12" })
	@Test(priority = 12, enabled = true, groups = { "Regression", "Lead" })

	public void leadDelightCreation() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Delight");

		relatedActivityPage.get().clickOnLink("New Delight");

		String expectedDelightName = delightCreationPage.get().createDelight(null, DelightConstants.DELIGHT_NAME,
				DelightConstants.DELIGHT_OWNER_AUTO_ALL);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(delightDetailPage.get().closeButton, "Close Button");

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualDelightName = ReUsableMethods
				.WebGetElementText(accountDetailPage.get().searchedFirstRecordByDelightName, "Delight Name");
		s.get().assertEquals(actualDelightName, expectedDelightName);

		ReUsableMethods.webClickElement(accountDetailPage.get().searchedFirstRecordByDelightName,
				"Delight name on Lead Detail Page");

		String actualDelightNameOnDelightDetail = ReUsableMethods.WebGetElementText(delightDetailPage.get().delightName,
				"Delight Name on detail");
		s.get().assertEquals(actualDelightNameOnDelightDetail, expectedDelightName,
				"Delight Name on detail do not match");

		String actualRelatedToLead = ReUsableMethods.WebGetElementText(delightDetailPage.get().relatedTo,
				"Lead name in related");
		s.get().assertEquals(actualRelatedToLead, expectedLastName.get(), "Last name related to do not match");

		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184582","407845" }, scriptType = {
			"" }, testCasePriority = { "13" })
	@Test(priority = 13, enabled = true, groups = { "Regression", "Lead" })

	public void leadLinkDocumentsFromDMS() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnRelatedAttachmentTab();
		relatedActivityPage.get().clickOnLink("Link Documents From DMS");
		ReUsableMethods.switchToChildWindowHandle();
		relatedActivityPage.get().linkDocumentFromDMS(ApprovalConstants.FOLDER_FORALL, null);

		boolean actualAttachmentRecord = ReUsableMethods
				.WebIsElementDisplayed(approvalDetailPage.get().attachmentRecord, "attachment record");

		s.get().assertTrue(actualAttachmentRecord, "no Attached Document");

		ReUsableMethods.webClickElement(relatedActivityPage.get().threeDots, "Three Dots Button");

		relatedActivityPage.get().clickOnThreeDot_ActionMenu("Remove");

		relatedActivityPage.get().clickOnOkButton();

		boolean isNoDataExistDisplayed = ReUsableMethods
				.WebIsElementDisplayed(commonProductFunctions.get().noDataExists, "No Data Exists");
		s.get().assertTrue(isNoDataExistDisplayed, "Draft Not Deleted");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184583" }, scriptType = {
			"" }, testCasePriority = { "14" })
	@Test(priority = 14, enabled = true, groups = { "Regression", "Lead" })

	public void leadAttachNewDocument() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnRelatedAttachmentTab();
		relatedActivityPage.get().clickOnLink("Attach New Document");

		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClickElement(relatedActivityPage.get().searchFolderIcon, "Existing Folder");
		commonProductFunctions.get().selectNameFromPicker(ApprovalConstants.FOLDER_FORALL);
		approvalCreationPage.get().uploadFile(ApprovalConstants.DMS_DOCUMENT_TYPE_PNG);
		commonProductFunctions.get().waitForLoader();
		relatedActivityPage.get().newAppointmentSaveButton();

		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		boolean isAttachmentVisible = ReUsableMethods
				.WebIsElementDisplayed(relatedActivityPage.get().relatedAttachedDocumentFromDMS, "attachment record");
		s.get().assertTrue(isAttachmentVisible, "attachment not visible");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184585",
			"184746" }, scriptType = { "" }, testCasePriority = { "15" })
	@Test(priority = 15, enabled = true, groups = { "Regression", "Lead" })

	public void leadApprovalCreation() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Approval");
		relatedActivityPage.get().clickOnLink("New Approval");

		String expectedApprovalSubject = ApprovalConstants.APPROVAL_SUBJECT + ReUsableMethods.getCurrentdateTime();
		approvalCreationPage.get().createApproval("Approval_System", expectedApprovalSubject, "", "");
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualApprovalSubject = approvalDetailPage.get().getSubjectForActivities();
		s.get().assertEquals(actualApprovalSubject, expectedApprovalSubject, "Subject do not match");

		ReUsableMethods.webClickElement(approvalDetailPage.get().subjectForActivities, "Related Approval Subject");

		String actualLeadOnApprovalDetail = approvalDetailPage.get().getRelatedTo();
		s.get().assertEquals(actualLeadOnApprovalDetail, expectedLastName.get(), "Case Subject do not matches");

		boolean isRelatedCaseClickable = ReUsableMethods.WebIsElementDisplayed(approvalDetailPage.get().related_To,
				"Related Case ");
		s.get().assertTrue(isRelatedCaseClickable, "Related Case not Clickable");

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		relatedActivityPage.get().clickOnModule("Related Approval");

		ReUsableMethods.webClickElement(relatedActivityPage.get().threeDotsOnListing, "Three Dots");

		relatedActivityPage.get().clickOnThreeDot_ActionMenu("Delete");

		relatedActivityPage.get().clickOnOkButton();

		boolean noDataExists = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(noDataExists, "Data Exists");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184586",
			"184748" }, scriptType = { "Configuration" }, testCasePriority = { "" })
	@Test(priority = 16, enabled = true, groups = { "Regression", "Lead" })

	public void leadAddContent() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Content");
		relatedActivityPage.get().clickOnLink("Content Deliver");

		ReUsableMethods.switchToChildWindowHandle();
		String expectedContent = CaseConstants.CONTENT_RELATED_CONTENT1;
		workSpaceLibraryCreationPage.get().enterInSearchTextBoxFindWorkspace(expectedContent);
		commonProductFunctions.get().clickOnFirstCheckBox();
		workSpaceLibraryCreationPage.get().clickOnSelectedContent();

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOnModule("Related Content");

		String actualContent = ReUsableMethods.WebGetElementText(relatedActivityPage.get().firstNameRecordInRelatedList,
				"Related Content Name");
		s.get().assertEquals(actualContent, expectedContent, "Content Name do not meet");

		// ReUsableMethods.webClickElement(relatedActivityPage.get().firstNameRecordInRelatedList, "Content Name");
		// ReUsableMethods.switchToChildWindowHandle();
		// relatedActivityPage.get().clickOnLink("Content Deliver");
		// ReUsableMethods.switchToChildWindowHandle();

		// workSpaceLibraryCreationPage.get().enterInSearchTextBoxFindWorkspace(expectedContent);
		// commonProductFunctions.get().clickOnFirstCheckBox();
		// workSpaceLibraryCreationPage.get().clickOnSelectedContent();

		// ReUsableMethods.waitforWindowSize(1);

		// ReUsableMethods.switchToWindow(parentWindowHandle);

		// s.get().assertEquals(actualContent, expectedContent, "Name do not meet");

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		relatedActivityPage.get().clickOnModule("Related Content");

		ReUsableMethods.webClickElement(relatedActivityPage.get().threeDotsOnListing, "three dots");

		leadDetailPage.get().clickButtonByText("Remove");
		relatedActivityPage.get().clickOnOkButton();

		s.get().assertTrue(commonProductFunctions.get().isNoDataExistDisplayed(), "Content not deleted");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184590" }, scriptType = {
			"" }, testCasePriority = { "17" })
	@Test(priority = 17, enabled = true, groups = { "Regression", "Lead" })

	public void leadDraft() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnComposeEmailPicker();

		ReUsableMethods.switchToChildWindowHandle();

		String expectedEmailSubject = CaseConstants.Case_Subject;

		relatedActivityPage.get().createComposeEmail(CaseConstants.COMPOSEEMAILTO_ANUPRIYA, "", "New",
				expectedEmailSubject, "", "saveAsDraft");

		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOnModule("Related Draft");

		String actualEmailSubject = relatedActivityPage.get().getActivitySubject();
		s.get().assertEquals(actualEmailSubject, expectedEmailSubject, "subject do not match");

		ReUsableMethods.webClickElement(relatedActivityPage.get().threeDots, "Three Dots Button");

		relatedActivityPage.get().clickOnThreeDot_ActionMenu("Edit");

		ReUsableMethods.switchToChildWindowHandle();

		String expectedEdittedEmailSubject = CaseConstants.Case_Subject + "edit";

		ReUsableMethods.webClearText(relatedActivityPage.get().composeEmailSubject, "Email SUbject");
		relatedActivityPage.get().createComposeEmail(CaseConstants.COMPOSEEMAILTO_ANUPRIYA, "", "New",
				expectedEdittedEmailSubject, "", "saveAsDraft");

		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualEdittedEmailSubject = relatedActivityPage.get().getActivitySubject();
		s.get().assertEquals(actualEdittedEmailSubject, expectedEdittedEmailSubject, "subject do not match");

		ReUsableMethods.webClickElement(relatedActivityPage.get().threeDots, "Three Dots Button");

		relatedActivityPage.get().clickOnThreeDot_ActionMenu("Edit");

		ReUsableMethods.switchToChildWindowHandle();

		relatedActivityPage.get().clickOnSendEmail();
		relatedActivityPage.get().clickOnOkButton();

		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOn_RelatedActivity();

		String actualClosedActivity = relatedActivityPage.get().verifyActivity("Closed Activities",
				expectedEdittedEmailSubject);

		s.get().assertEquals(actualClosedActivity, expectedEdittedEmailSubject, "Email Subject is not verified");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184595" }, scriptType = {
			"" }, testCasePriority = { "18" })
	@Test(priority = 18, enabled = true, groups = { "Regression", "Lead" })

	public void leadSendSMS() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();

		relatedActivityPage.get().clickOnLink("Send SMS");

		ReUsableMethods.switchToChildWindowHandle();

		relatedActivityPage.get().selectUserEnterSmsAndSend("internal", RequirementConstants.CONTACT_NUMBER, null);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualSMSSubject = ReUsableMethods.WebGetElementText(relatedActivityPage.get().subjectOfSendSMS,
				"subject of SMS");
		s.get().assertEquals(actualSMSSubject, RequirementConstants.CONTACT_NUMBER_WITH_MASKING, "The number is mismatch");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184596" }, scriptType = {
			"" }, testCasePriority = { "19" })
	@Test(priority = 19, enabled = true, groups = { "Regression", "Lead" })

	public void lead_Send_Email_ThroughThread() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOn_NewTaskLink();
		commonProductFunctions.get().clickOnLayout("Task_System");
		ReUsableMethods.switchToChildWindowHandle();

		String expectedTaskSubject = relatedActivityPage.get().enterSubject_LogACall();
		String expected_Case = ReUsableMethods.getTextElementAttribute(casesDetailPage.get().related_Case, "value");
		s.get().assertEquals(expectedLastName.get(), expected_Case);

		relatedActivityPage.get().clickOn_SaveButton_Task();
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualOpenTask = relatedActivityPage.get().verifyActivity("Open Activities", expectedTaskSubject);
		s.get().assertEquals(actualOpenTask, expectedTaskSubject);

		relatedActivityPage.get().clickOnLink("Show Thread");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.safeClick(relatedActivityPage.get().sendEmailButton, relatedActivityPage.get().sendEmailThread);

		ReUsableMethods.webEnterText(relatedActivityPage.get().sendEmailThread, CaseConstants.COMPOSEEMAILTO_ANUPRIYA,
				"Send Email");

		ReUsableMethods.webEnterText(relatedActivityPage.get().activitySubject, CaseConstants.Case_Subject,
				"Subject of Email");

		ReUsableMethods.webClickElement(relatedActivityPage.get().sendEmailButton, "Send Email button");

		ReUsableMethods.switchToWindow(parentWindowHandle);

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184587" }, scriptType = {
			"" }, testCasePriority = { "20" })
	@Test(priority = 20, enabled = true, groups = { "Regression", "Lead" })

	public void leadCreateExistingPortfolio() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Portfolio");
		relatedActivityPage.get().clickOnLink("New Portfolio");

		relatedActivityPage.get().clickOnLayout("Portfolio_System");

		ReUsableMethods.switchToChildWindowHandle();

		portfolioNewEditPage.get().searchWealthButtonClick();
		portfolioNewEditPage.get().enterWealthNameSearchBox("Acidaes_wealth");
		portfolioNewEditPage.get().searchEnterWealthClick();
		portfolioNewEditPage.get().selectFirstWealthClick();
		String expectedStockName = portfolioNewEditPage.get().enterStockName("Portfolio");
		portfolioNewEditPage.get().enterCode("Portfolio");
		//portfolioNewEditPage.get().selectFirstAccountClick();
		portfolioNewEditPage.get().clickOwnerSearchPicker();
		commonProductFunctions.get().searchApply("auto2");
		portfolioNewEditPage.get().selectFirstOwnerClick();

		String actualLeadOnPortfolio = ReUsableMethods.getTextElementAttribute(portfolioNewEditPage.get().relatedTo,
				"value");
		s.get().assertEquals(actualLeadOnPortfolio, expectedLastName.get(), "Lead Name do not match");

		portfolioNewEditPage.get().savePortfolioClick();

		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualStockName = ReUsableMethods.WebGetElementText(portfolioNewEditPage.get().stockName,
				"Stock Name on Detail Page");
		s.get().assertEquals(actualStockName, expectedStockName, "Stock Name do not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184588" }, scriptType = {
			"" }, testCasePriority = { "21" })
	@Test(priority = 21, enabled = true, groups = { "Regression", "Lead" })

	public void leadCreateStrategyPortfolio() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Strategy Portfolio");
		relatedActivityPage.get().clickOnLink("New Portfolio");

		relatedActivityPage.get().clickOnLayout("Portfolio_System");

		ReUsableMethods.switchToChildWindowHandle();

		portfolioNewEditPage.get().searchWealthButtonClick();
		portfolioNewEditPage.get().enterWealthNameSearchBox("Acidaes_wealth");
		portfolioNewEditPage.get().searchEnterWealthClick();
		portfolioNewEditPage.get().selectFirstWealthClick();
		String expectedStockName = portfolioNewEditPage.get().enterStockName("Portfolio");
		portfolioNewEditPage.get().enterCode("Portfolio");
		//portfolioNewEditPage.get().selectFirstAccountClick();
		portfolioNewEditPage.get().clickOwnerSearchPicker();
		portfolioNewEditPage.get().enterWealthNameSearchBox("Auto");
		portfolioNewEditPage.get().searchEnterOwnerClick();
		portfolioNewEditPage.get().selectFirstOwnerClick();

		String actualLeadOnPortfolio = ReUsableMethods.getTextElementAttribute(portfolioNewEditPage.get().relatedTo,
				"value");
		s.get().assertEquals(actualLeadOnPortfolio, expectedLastName.get(), "Lead Name do not match");

		portfolioNewEditPage.get().savePortfolioClick();

		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualStockName = ReUsableMethods.WebGetElementText(portfolioNewEditPage.get().stockName,
				"Stock Name on Detail Page");
		s.get().assertEquals(actualStockName, expectedStockName, "Stock Name do not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184658" }, scriptType = {
			"" }, testCasePriority = { "22" })
	@Test(priority = 22, enabled = true, groups = { "Regression", "Lead" })

	public void leadSearch_QuickSearch() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		String actualLastName = leadDetailPage.get().getLastName();
		s.get().assertEquals(actualLastName, expectedLastName.get(), "Last Name do not matches");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184662",
			"184663" }, scriptType = { "" }, testCasePriority = { "24" })
	@Test(priority = 24, enabled = true, groups = { "Regression", "Lead" })

	public void leadPagingOnHomePage() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().searchByAttribute("All New Leads", "All", "Last Name", expectedLastName.get());

		String actualLastName = ReUsableMethods.WebGetElementText(leadHomePage.get().firstLead, "Lead name");
		s.get().assertEquals(actualLastName, expectedLastName.get(), "Lead Name do not match");

		List<String> pageSizes = Arrays.asList("10", "20", "50", "100");

		for (String size : pageSizes) {
			approvalHomePage.get().selectPageSize(size);
			int expectedSize = Integer.parseInt(size);
			List<WebElement> pageListElement = ReUsableMethods
					.findElementByPath(approvalHomePage.get().xpathViewListingElements);
			List<String> pageList = ReUsableMethods.getListOfText_FromElementList(pageListElement);
			s.get().assertEquals(pageList.size(), expectedSize, "Expected list size is " + pageList.size());

			s.get().assertAll();

		}
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184665" }, scriptType = {
			"" }, testCasePriority = { "25" })
	@Test(priority = 25, enabled = true, groups = { "Regression", "Lead" })

	public void leadPulseOnHomePage() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().waitForLoader();
		boolean isPulseIconVisible = ReUsableMethods.WebIsElementDisplayed(leadHomePage.get().pulseIcon, "Pulse");
		s.get().assertTrue(isPulseIconVisible, "Pulse Icon is not visible");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184666" }, scriptType = {
			"" }, testCasePriority = { "26" })
	@Test(priority = 26, enabled = true, groups = { "Regression", "Lead" })

	public void leadClosedStatus() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		leadDetailPage.get().editLead();

		leadCreationPage.get().selectLeadStatusCode("Closed");

		commonProductFunctions.get().clickOnSave();

		boolean isEditButtonVisible = ReUsableMethods.WebIsElementDisplayed(commonProductFunctions.get().editLink,
				"Edit button on detail Page");
		s.get().assertFalse(isEditButtonVisible, "Edit Button is Visible");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184676" }, scriptType = {
			"" }, testCasePriority = { "27" })
	@Test(priority = 27, enabled = true, groups = { "Regression", "Lead" })

	public void leadAddToQuickLinks() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		
		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		ReUsableMethods.webClickElement(productHomepage.get().relatedLink, "Related Link");
		ReUsableMethods.webClickElement(productHomepage.get().addToQuickLink, "addToQuickLink");
		ReUsableMethods.webClickElement(productHomepage.get().crossQuickLink, "crossQuickLink");

		quickLinksCreationPage.get().clickOnQuickLinksIcon();
		WebElement quickLinkPath = leadHomePage.get().getLeadPath(expectedLastName.get());
		ReUsableMethods.scrollElementToCentreOfScreen(quickLinkPath);
		ReUsableMethods.webClickElement(quickLinkPath, "Quick link path");
		
		//AssertionUtils.assertElementText(leadDetailPage.get().lead_lastName, "Last name exists");
		ReUsableMethods.webClickElement(budgetDetailPage.get().relatedLinks, "related links");
		ReUsableMethods.webClickElement(budgetDetailPage.get().removeFromQuickLinks, "remove related links");

		boolean isAddToDisplayed = ReUsableMethods.WebIsElementDisplayed(productHomepage.get().addToQuickLink,
				"add to quick Links is displayed");
		s.get().assertFalse(isAddToDisplayed, "Quick link is Displayed");

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184718" }, scriptType = {
			"" }, testCasePriority = { "28" })
	@Test(priority = 28, enabled = true, groups = { "Regression", "Lead" })

	public void leadRelatedAsset() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		navigationPanel.get().NavigateToObject("Account");
		String expectedAccountName = accountCreationPage.get().accountCreate(AccountConstant.LAYOUT_ACCOUNT_SYSTEM,
				AccountConstant.ACCOUNT_NAME);
		commonProductFunctions.get().clickOnClose();

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		relatedActivityPage.get().clickOnModule("Related Asset");
		relatedActivityPage.get().clickOnLink("New Asset");

		String expectedAssetCreation = assetCreationPage.get().createAssets(AssetsConstants.LAYOUT_NAME,
				AssetsConstants.ASSETS_NAME, expectedAccountName, AssetsConstants.CATEGORY_NAME_COMPETITORS,
				AssetsConstants.SERIAL_NO, AssetsConstants.STATUS_NEW, ReUsableMethods.getCurrentTime("hh:mm:ss"),
				ReUsableMethods.getCurrentTime("hh:mm:ss"), AssetsConstants.QUANTITY, null, "Save");

		ReUsableMethods.switchToWindow(parentWindowHandle);

		commonProductFunctions.get().clickOnFlagCrossIcon();

		String actualAssetCreation = ReUsableMethods.WebGetElementText(assetsDetailPage.get().attachmentNameFirst,
				"First Asset Name");
		s.get().assertEquals(actualAssetCreation, expectedAssetCreation, "Asset name On Detail");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184735" }, scriptType = {
			"" }, testCasePriority = { "29" })
	@Test(priority = 29, enabled = true, groups = { "Regression", "Lead" })

	public void leadRecentlyAccessed() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().clickOnObjectInRecentlyAccessedSection(expectedLastName.get());

		String actualLastName = leadDetailPage.get().getLastName();
		s.get().assertEquals(actualLastName, expectedLastName.get(), "Last Name do not matches");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184717" }, scriptType = {
			"" }, testCasePriority = { "30" })
	@Test(priority = 30, enabled = true, groups = { "Regression", "Lead" })

	public void leadrelatedApplication() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOnModule("Related Application");
		relatedActivityPage.get().clickOnLink("New Application");

		String expectedApplicationname = applicationCreationPage.get().createApplication(
				null, ApplicationConstants.Application_LastName, "Save");

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualApplicationName = applicationDetailPage.get().getApplicationLastName();
		s.get().assertEquals(actualApplicationName, expectedApplicationname, "Application donot match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184565" }, scriptType = {
			"" }, testCasePriority = { "31" })
	@Test(priority = 31, enabled = true, groups = { "Regression", "Lead" })

	public void leadMandatoryMessageOnOnlineLeadCaptureHTMLGenerator() throws Exception {
		login.get().genericAdminLogin(webURL, adminUserMaster_Auto, webpassword);

		navigationPanel.get().navigateToSetup();
		navigationPanel.get().navigateToAdminMenuItem("Online Lead Capture");
		AssertionUtils.assertElementText(commonProductFunctions.get().pageTitle, "Custom Fields");

		ReUsableMethods.webClickElement(customFieldPage.get().generateHTML, "Generate HTML");

		ReUsableMethods.switchToChildWindowHandle();

		commonProductFunctions.get().clickOnSave();

		List<String> actualMandatoryValidation = commonProductFunctions.get().getValidationMessage();
		commonProductFunctions.get().clickOnCrossSign_OnMandatoryPanel();
		List<String> expectedMandatoryValidation = CustomFieldConstants.MANDATORY_VALIDATION;
		s.get().assertEquals(actualMandatoryValidation, expectedMandatoryValidation, "Mandatory Fields");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "120841" }, scriptType = {
			"" }, testCasePriority = { "32" })
	@Test(priority = 32, enabled = true, groups = { "Regression", "Lead" })

	public void leadVerifyManageEscalationClickableOrNot() throws Exception {

		AlertRuleHomepage alertRuleHomepage = new AlertRuleHomepage(DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, adminUserMaster_Auto, webpassword);

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().clickOnToolboxMenuItem("Manage Escalation");

		boolean isManageEscalationruleWindowOpens = ReUsableMethods
				.WebIsElementDisplayed(alertRuleHomepage.pageTitleHeading, "Manage Escalation Rule");
		s.get().assertTrue(isManageEscalationruleWindowOpens, "Escalation Rule do not open");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184591" }, scriptType = {
			"" }, testCasePriority = { "33" })
	@Test(priority = 33, enabled = true, groups = { "Regression", "Lead" })

	public void leadSharingFromDetailPage() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		String expectedMemberName = SharingConstants.SHARING_USER_AUTO_IND1;
		sharingCreationPage.get().shareWithAndSharingMembersVisibility(SharingConstants.SHARE_WITH_SPECIFIC_MEMBERS,
				SharingConstants.SHARING_UTTRP_USER, expectedMemberName, ObjectConstants.OBJECT_OPPORTUNITY,
				SharingConstants.SHARING_PERMISSION_READ);

		relatedActivityPage.get().relatedBudgetSaveButton();
		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOnModule("Sharing Members");

		String actualMemberName = ReUsableMethods.WebGetElementText(assignmentRuleCreationPage.get().memberFirst,
				"member Name of User");
		s.get().assertEquals(actualMemberName, expectedMemberName, "Member Name do not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184598" }, scriptType = {
			"" }, testCasePriority = { "34" })
	@Test(priority = 34, enabled = true, groups = { "Regression", "Lead" })

	public void leadAddMailingListFromDetailPage() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		contactDetailPage.get().clickOnRelatedItems("Mailing List");

		relatedActivityPage.get().clickOnLink("Add To Mailing List");

		ReUsableMethods.switchToChildWindowHandle();

		commonProductFunctions.get().clickOnFirstCheckBox();
		ReUsableMethods.webClickElement(contactCreationPage.get().saveMailingList, "save mailing list button");

		ReUsableMethods.switchToWindow(parentWindowHandle);
		contactDetailPage.get().clickOnRelatedItems("Mailing List");

		boolean isRecordVisible = ReUsableMethods.WebIsElementDisplayed(contactDetailPage.get().firstName,
				"first record");
		s.get().assertTrue(isRecordVisible, "record is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184664" }, scriptType = {
			"" }, testCasePriority = { "35" })
	@Test(priority = 35, enabled = true, groups = { "Regression", "Lead" })

	public void leadPrintLinkFromHomePage() throws Exception {
		RequirementHomePage requirementHomePage = new RequirementHomePage(DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
	
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		//ReUsableMethods.webClickElement(requirementHomePage.printButton, "printButton");
		// System.out.println("Current windows size is " + ReUsableMethods.currentWindowSize());
		// ReUsableMethods.switchToChildWindowHandle();

		// System.out.println("Current windows size is " + ReUsableMethods.currentWindowSize());
		boolean isPrintButtonDisplay = ReUsableMethods.WebIsElementDisplayed(requirementHomePage.printButton, "printButton");	
		s.get().assertTrue(isPrintButtonDisplay, "The print view page is not displayed");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184672" }, scriptType = {
			"" }, testCasePriority = { "36" })
	@Test(priority = 36, enabled = true, groups = { "Regression", "Lead" })

	public void leadPrintLinkFromDetailPage() throws Exception {

		RequirementHomePage requirementHomePage = new RequirementHomePage(DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		// ReUsableMethods.webClickElement(requirementHomePage.printButton, "printButton");
		// System.out.println("Current windows size is " + ReUsableMethods.currentWindowSize());
		// ReUsableMethods.switchToChildWindowHandle();

		// System.out.println("Current windows size is " + ReUsableMethods.currentWindowSize());
		boolean isPrintButtonDisplay = ReUsableMethods.WebIsElementDisplayed(requirementHomePage.printButton, "printButton");
		s.get().assertTrue(isPrintButtonDisplay, "The print button is not displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184677" }, scriptType = {
			"" }, testCasePriority = { "37" })
	@Test(priority = 37, enabled = true, groups = { "Regression", "Lead" })

	public void leadAssignContact() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		ReUsableMethods.webClickElement(opportunityDetailPage.get().relatedContact,
				"related Contact from opportunity detail");
		ReUsableMethods.webClickElement(contactCreationPage.get().newRelatedContact, "Create New Related Contact");
		String expectedContactName = contactCreationPage.get()
				.enterContactDetailsAndSave(LayoutConstants.LAYOUT_CONTACT_SYSTEM, ConstantClassContact.CONTACT_NAME);
		ReUsableMethods.switchToWindow(parentWindowHandle);
		opportunityDetailPage.get().selectAssignedContact(OpportunityConstants.OPPORTUNITY_ASSIGNEDCONTACT);
		ReUsableMethods.switchToWindow(parentWindowHandle);
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(opportunityDetailPage.get().firstRecordAfterSearch,
				expectedContactName));
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184687", "184688",
			"184689","184690" ,"184691","184693"}, scriptType = { "" }, testCasePriority = { "38" })
	@Test(priority = 38, enabled = true, groups = { "Regression", "Lead" })

	public void CreationEditionDeletionOfleadQualifyLayoutAssignmentRule() throws Exception {
		login.get().genericAdminLogin(webURL, adminUserAuto, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		String parentWindowHandle = ReUsableMethods.getWindow();

		commonProductFunctions.get().clickOnToolboxMenuItem("Lead Qualify Layout Assignment Rule");
		ReUsableMethods.webClickElement(leadQualifyAssignmentRuleHomePage.get().leadQualifyAssignmentRuleNewIcon,
				"New Icon of Lead Qualify Assignment Rule");

		String expectedLeadQualifySubject = LeadQualifyAssignmentRuleConstant.LEADQUALIFYSUBJECT_AUTOASSIGNMENTLEAD
				+ ReUsableMethods.getCurrentdateTime();
		String expectedLeadLayout = "Lead_System";
		String expectedAccountLayout = "Account_System";
		String expectedContactLayout = "Contact_System";
		String expectedOpportunityLayout = "Status";
		leadQualifyAssignmentRuleCreationPage.get().CreateLeadQualifyAssignmentRule(expectedLeadQualifySubject,
				expectedLeadLayout, expectedAccountLayout, expectedContactLayout, expectedOpportunityLayout);

		String actualLeadSubject = ReUsableMethods.WebGetElementText(leadQualifyAssignmentRuleHomePage.get().ruleName,
				"Rule Name of Assignment Rule");
		s.get().assertEquals(actualLeadSubject, expectedLeadQualifySubject, "Subject do not match");

		String actualLeadLayout = ReUsableMethods.WebGetElementText(leadQualifyAssignmentRuleHomePage.get().leadLayout,
				"Lead Layout");
		s.get().assertEquals(actualLeadLayout, expectedLeadLayout, "Lead Layout");

		String actualAccountLayout = ReUsableMethods
				.WebGetElementText(leadQualifyAssignmentRuleHomePage.get().accountLayout, "Account Layout");
		s.get().assertEquals(actualAccountLayout, expectedAccountLayout, "Account Layout");

		String actualContactLayout = ReUsableMethods
				.WebGetElementText(leadQualifyAssignmentRuleHomePage.get().contactLayout, "Contact Layout");
		s.get().assertEquals(actualContactLayout, expectedContactLayout, "Contact Layout ");

		String actualOpportunityLayout = ReUsableMethods
				.WebGetElementText(leadQualifyAssignmentRuleHomePage.get().opportunityLayout, "Opportunity Layout");
		s.get().assertEquals(actualOpportunityLayout, expectedOpportunityLayout, "Opportunity Layout");

		ReUsableMethods.safeClick(leadQualifyAssignmentRuleHomePage.get().threeDots,
				leadQualifyAssignmentRuleHomePage.get().editLeadQualify);

		ReUsableMethods.webClickElement(leadQualifyAssignmentRuleHomePage.get().editLeadQualify, "Edit the Lead");

		ReUsableMethods.switchToChildWindowHandle();

		String expectedEdittedSubject = expectedLeadQualifySubject + "-edit";

		leadQualifyAssignmentRuleCreationPage.get().enterLeadQualifyAssignmentRuleSubject(expectedEdittedSubject);

		ReUsableMethods.webClickElement(leadQualifyAssignmentRuleCreationPage.get().save, "Save the editted Page");

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualEdittedLeadSubject = ReUsableMethods
				.WebGetElementText(leadQualifyAssignmentRuleHomePage.get().ruleName, "Rule Name of Assignment Rule");
		s.get().assertEquals(actualEdittedLeadSubject, expectedEdittedSubject, "Editted Subject do not match");

		ReUsableMethods.safeClick(leadQualifyAssignmentRuleHomePage.get().threeDots,
				leadQualifyAssignmentRuleHomePage.get().deleteLeadQualify);

		ReUsableMethods.webClickElement(leadQualifyAssignmentRuleHomePage.get().deleteLeadQualify, "Delete the Lead");

		relatedActivityPage.get().clickOnOkButton();

		boolean noDataExists = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(noDataExists, "Data exists");

		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = {
			"184715" }, scriptType = { "" }, testCasePriority = { "39" })
	@Test(priority = 39, enabled = true, groups = { "Regression", "Lead" })

	public void leadAddBccToComposeEmail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();
		String expectedSendToAccount = composeEmailCreationPage.get()
				.enterInTextBox_ComposeEmailSendTo(ComposeEmailConstants.COMPOSEEMAIL_TO);
		//ReUsableMethods.safeClick(composeEmailCreationPage.get().ccLabel,composeEmailCreationPage.get().emailCCID);

		composeEmailCreationPage.get().clickOnCcBcc("Cc");
		String expectedSendToCCMail = ComposeEmailConstants.COMPOSEEMAIL_CC;
		composeEmailCreationPage.get().enterCCMail(expectedSendToCCMail);

		//ReUsableMethods.webClickElement(composeEmailCreationPage.get().bccLabel, "BCC Link");
		composeEmailCreationPage.get().clickOnCcBcc("Bcc");
		String expectedSendToBCCMail = ComposeEmailConstants.COMPOSEEMAIL_BCCMAILTO;
		composeEmailCreationPage.get().enterBCCMail(expectedSendToBCCMail);


		composeEmailCreationPage.get().enterInTextBox_Subject(ComposeEmailConstants.COMPOSEEMAIL_SUBJECT);
		composeEmailCreationPage.get().clickOnSendEmailButton();
		relatedActivityPage.get().clickOnOkButton();
		ReUsableMethods.switchToWindow(parentWindowHandle);
		leadDetailPage.get().clickOnSubject_ClosedActivities();

		// String expectedPriorityTittle =
		// composeEmailCreationPage.get().Priority_Tittle();

		String actualSendToAccount = taskDetailPage.get().getAccountId_ToSendEmail();
		//String actualSendToCCMail = taskDetailPage.get().getAccountId_ToSendCCEmail();
		Assert.assertEquals(expectedSendToAccount, actualSendToAccount, "value not match");
		//Assert.assertEquals(expectedSendToCCMail, actualSendToCCMail, "value not match");
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184721" }, scriptType = {
			"" }, testCasePriority = { "40" })
	@Test(priority = 40, enabled = true, groups = { "Regression", "Lead" })

	public void leadNormalPriorityComposeEmail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();
		composeEmailCreationPage.get()
				.enterInTextBox_ComposeEmailSendTo(ComposeEmailConstants.COMPOSEEMAIL_TO);
		ReUsableMethods.webClickElement(composeEmailCreationPage.get().emailPrioprity, "Email Priority Dropdown");
		composeEmailCreationPage.get().clickOnPriority_Normal();
		String expectedPriorityTittle = ComposeEmailConstants.PRIORITY_NORMAL;

		composeEmailCreationPage.get().enterInTextBox_Subject(ComposeEmailConstants.COMPOSEEMAIL_SUBJECT);
		composeEmailCreationPage.get().clickOnSendEmailButton();
		relatedActivityPage.get().clickOnOkButton();
		ReUsableMethods.switchToWindow(parentWindowHandle);
		leadDetailPage.get().clickOnSubject_ClosedActivities();

		ReUsableMethods.switchToChildWindowHandle();

		String actualPriorityTittle = taskDetailPage.get().getPriorityTittle();
		Assert.assertEquals(actualPriorityTittle, expectedPriorityTittle, "value not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184719" }, scriptType = {
			"" }, testCasePriority = { "41" })
	@Test(priority = 41, enabled = true, groups = { "Regression", "Lead" })

	public void leadHighPriorityComposeEmail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();
		composeEmailCreationPage.get()
				.enterInTextBox_ComposeEmailSendTo(ComposeEmailConstants.COMPOSEEMAIL_TO);

		ReUsableMethods.webClickElement(composeEmailCreationPage.get().emailPrioprity, "Email Priority Dropdown");

		composeEmailCreationPage.get().clickOnPriority_High();

		String expectedPriorityTittle = ComposeEmailConstants.PRIORITY_HIGH;

		composeEmailCreationPage.get().enterInTextBox_Subject(ComposeEmailConstants.COMPOSEEMAIL_SUBJECT);
		composeEmailCreationPage.get().clickOnSendEmailButton();
		relatedActivityPage.get().clickOnOkButton();
		ReUsableMethods.switchToWindow(parentWindowHandle);
		leadDetailPage.get().clickOnSubject_ClosedActivities();

		ReUsableMethods.switchToChildWindowHandle();

		String actualPriorityTittle = taskDetailPage.get().getPriorityTittle();
		Assert.assertEquals(actualPriorityTittle, expectedPriorityTittle, "value not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184720" }, scriptType = {
			"" }, testCasePriority = { "42" })
	@Test(priority = 42, enabled = true, groups = { "Regression", "Lead" })

	public void leadLowPriorityComposeEmail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();
		composeEmailCreationPage.get()
				.enterInTextBox_ComposeEmailSendTo(ComposeEmailConstants.COMPOSEEMAIL_TO);
		ReUsableMethods.webClickElement(composeEmailCreationPage.get().emailPrioprity, "Email Priority Dropdown");

		composeEmailCreationPage.get().clickOnPriority_Low();

		String expectedPriorityTittle = ComposeEmailConstants.PRIORITY_LOW;

		composeEmailCreationPage.get().enterInTextBox_Subject(ComposeEmailConstants.COMPOSEEMAIL_SUBJECT);
		composeEmailCreationPage.get().clickOnSendEmailButton();
		relatedActivityPage.get().clickOnOkButton();
		ReUsableMethods.switchToWindow(parentWindowHandle);
		leadDetailPage.get().clickOnSubject_ClosedActivities();

		ReUsableMethods.switchToChildWindowHandle();

		String actualPriorityTittle = taskDetailPage.get().getPriorityTittle();
		Assert.assertEquals(actualPriorityTittle, expectedPriorityTittle, "value not match");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184722" }, scriptType = {
			"" }, testCasePriority = { "43" })
	@Test(priority = 43, enabled = true, groups = { "Regression", "Lead" })

	public void leadVerifyMailWithAttachmentWhenMAilCompose() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();

		composeEmailCreationPage.get().enterInTextBox_ComposeEmailSendTo(ComposeEmailConstants.COMPOSEEMAIL_TO);
		ReUsableMethods.webClickElement(composeEmailCreationPage.get().emailPrioprity, "Email Priority Dropdown");

		composeEmailCreationPage.get().clickOnPriority_High();
		composeEmailCreationPage.get().selectStatusCode_ComposeEmail(ComposeEmailConstants.STATUS_CODE);
		composeEmailCreationPage.get().enterInTextBox_Subject(ComposeEmailConstants.COMPOSEEMAIL_SUBJECT);
		composeEmailCreationPage.get().clickOnSelectTemplate_Activity_Tab();
		composeEmailCreationPage.get().enterInSearchTextBox();
		composeEmailCreationPage.get().clickOnArrowIcon();
		composeEmailCreationPage.get().clickOnFirstTemplate_AfterArrow();
		Assert.assertTrue(composeEmailCreationPage.get().verifyInputFileAttachment(), "File not match");

		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184723",
			"184724" }, scriptType = { "" }, testCasePriority = { "44" })
	@Test(priority = 44, enabled = true, groups = { "Regression", "Lead" })

	public void leadVerifyMailWithAttachmentsOnDetailPage() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();

		composeEmailCreationPage.get().enterInTextBox_ComposeEmailSendTo(ComposeEmailConstants.COMPOSEEMAIL_TO);
		composeEmailCreationPage.get().clickOnPriority_High();
		composeEmailCreationPage.get().selectStatusCode_ComposeEmail(ComposeEmailConstants.STATUS_CODE);
		composeEmailCreationPage.get().enterInTextBox_Subject(ComposeEmailConstants.COMPOSEEMAIL_SUBJECT);

		ReUsableMethods.scrollElementToCentreOfScreen(composeEmailCreationPage.get().DMSAttachmentToggle);
		ReUsableMethods.webClickElement(composeEmailCreationPage.get().DMSAttachmentToggle,
				"Clicks on DMS toggle button");
		composeEmailCreationPage.get().clickOnDMSFile_Attachment();

		String expectedattchementName = ComposeEmailConstants.ATTCHMENT_DMSDOCUMENT;
		commonProductFunctions.get().searchAndSelectCheckBox_UsingParameter(expectedattchementName);

		composeEmailCreationPage.get().clickOnSendEmailButton();
		commonProductFunctions.get().clickOnOkayConfirmationMessage();
		;
		ReUsableMethods.switchToWindow(parentWindowHandle);

		leadDetailPage.get().clickOnSubject_ClosedActivities();

		ReUsableMethods.switchToChildWindowHandle();
		String actualAttchmentName = composeEmailCreationPage.get()
				.getDMSAttchmentName(ComposeEmailConstants.ATTCHMENT_DMSDOCUMENT);
		Assert.assertEquals(actualAttchmentName, expectedattchementName, "Attachment is not visible");

		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184739" }, scriptType = {
			"" }, testCasePriority = { "45" })
	@Test(priority = 45, enabled = true, groups = { "Regression", "Lead" })

	public void NumberDecimalAndPercentageTypeSystemCustomFields() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		String expectedLeadAmount = LeadConstants.LEADAMOUNT_0;

		String actualLeadAmount = ReUsableMethods.WebGetElementText(leadDetailPage.get().getAmount, "Lead Amount");

		s.get().assertEquals(actualLeadAmount, expectedLeadAmount, "Lead AMount do not matches");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184725" }, scriptType = {
			"" }, testCasePriority = { "46" })
	@Test(priority = 46, enabled = true, groups = { "Regression", "Lead" })

	public void verifyUserAbleToSwitchFromPlainToHTMLEditor() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		//String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();

		boolean isEditorSwitch = composeEmailCreationPage.get().verifyHTMLEditorBody();
		s.get().assertTrue(isEditorSwitch, "Failed to Switch to HTML editor");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184726" }, scriptType = {
			"" }, testCasePriority = { "47" })
			@Test(priority = 47, enabled = true, groups = { "Regression", "Lead" })

	public void verifyUserAbleToSeeTheBodyContentWhenUserSwitchToPlainEditor() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		//String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();	
		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOnLink("Compose Email");
		ReUsableMethods.switchToChildWindowHandle();

		composeEmailCreationPage.get().clickOnPlainHTMLBody_Toggle();


		boolean isEditorSwitch = composeEmailCreationPage.get().verifyPlainHTMLBody();
		s.get().assertTrue(isEditorSwitch, "Switched to HTML editor");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184729" }, scriptType = {
			"" }, testCasePriority = { "48" })
	@Test(priority = 48, enabled = true, groups = { "Regression", "Lead" })

	public void recentlyAccessAndToolBoxSegregatedIfThereIsNoControlInSideSection() throws Exception {

		login.get().genericAdminLogin(webURL,adminUserMaster_Auto2, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		boolean isRecentlyAccessedSectionDisplayed = ReUsableMethods.WebIsElementDisplayed(
				leadCreationPage.get().recentlyAccessedSection, "Recently Accessed Section");
		s.get().assertTrue(isRecentlyAccessedSectionDisplayed, "Recently Accessed Section is not displayed");

		boolean isToolBoxSectionDisplayed = ReUsableMethods.WebIsElementDisplayed(leadCreationPage.get().toolBoxSection,
				"Tool Box Section");
		s.get().assertTrue(isToolBoxSectionDisplayed, "Tool Box Section is not displayed");

		s.get().assertAll();

	}

}