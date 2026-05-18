package com.businessnext.leads.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.alertrule.pages.AlertRuleHomepage;
import com.businessnext.assignmentrule.pages.AssignmentRuleCreationPage;
import com.businessnext.contentlibrary.pages.WorkSpaceLibraryCreationPage;
import com.businessnext.object.assets.pages.AssetsCreationPage;
import com.businessnext.object.assets.pages.AssetsDetailPage;
import com.businessnext.object.assets.pages.AssetsHomePage;
import com.businessnext.objects.account.pages.AccountCreationPage;
import com.businessnext.objects.account.pages.AccountDetailPage;
import com.businessnext.objects.alert.pages.AlertCreationPage;
import com.businessnext.objects.alert.pages.AlertDetailPage;
import com.businessnext.objects.application.pages.ApplicationCreationPage;
import com.businessnext.objects.application.pages.ApplicationDetailPage;
import com.businessnext.objects.application.pages.ApplicationHomePage;
import com.businessnext.objects.appointment.AppointmentCreationPage;
import com.businessnext.objects.approval.pages.ApprovalCreationPage;
import com.businessnext.objects.approval.pages.ApprovalDetailPage;
import com.businessnext.objects.approval.pages.ApprovalHomePage;
import com.businessnext.objects.budget.pages.BudgetDetailpage;
import com.businessnext.objects.cases.pages.CasesDetailPage;
import com.businessnext.objects.contact.pages.ContactCreationPage;
import com.businessnext.objects.contact.pages.ContactDetailPage;
import com.businessnext.objects.contact.pages.ContactHomePage;
import com.businessnext.objects.delight.pages.DelightCreationPage;
import com.businessnext.objects.delight.pages.DelightDetailPage;
import com.businessnext.objects.dispatch.pages.DispatchCreationPage;
import com.businessnext.objects.dispatch.pages.DispatchDetailPage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.businessnext.objects.note.pages.NoteCreationPage;
import com.businessnext.objects.opportunity.pages.OpportunityDetailPage;
import com.businessnext.objects.playbook.pages.PlaybookHomePage;
import com.businessnext.objects.portfolio.pages.PortfolioNewEditPage;
import com.businessnext.objects.requirement.pages.RequirementCreationPage;
import com.businessnext.objects.requirement.pages.RequirementDetailPage;
import com.businessnext.objects.requirement.pages.RequirementHomePage;
import com.businessnext.objects.review.pages.ReviewCreationPage;
import com.businessnext.objects.review.pages.ReviewDetailPage;
import com.businessnext.objects.task.pages.TaskConstants;
import com.businessnext.objects.task.pages.TaskCreationPage;
import com.businessnext.objects.task.pages.TaskDetailPage;
import com.businessnext.product.pages.ProductHomepage;
import com.businessnext.quickLinks.pages.QuickLinksCreationPage;
import com.businessnext.setupmodules.trigger.pages.TriggerActionCreationPage;
import com.businessnext.sharing.pages.SharingCreationPage;
import com.businessnext.toolbar.customfield.pages.CustomFieldPage;
import com.businessnext.toolbar.leadQualifyAssignmentRule.pages.LeadQualifyAssignmentRuleCreationPage;
import com.businessnext.toolbar.leadQualifyAssignmentRule.pages.LeadQualifyAssignmentRuleDetailPage;
import com.businessnext.toolbar.leadQualifyAssignmentRule.pages.LeadQualifyAssignmentRuleHomePage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.LayoutConstants;
import com.common.pages.NavigationPanel;
import com.common.pages.RelatedActivityPage;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)
public class LeadsTestCases extends BaseClass {

	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<AlertCreationPage> alertCreationPage = new ThreadLocal<>();
	ThreadLocal<AlertDetailPage> alertDetailPage = new ThreadLocal<>();
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
	ThreadLocal<RequirementDetailPage> requirementDetailPage = new ThreadLocal<>();
	ThreadLocal<RequirementCreationPage> requirementCreationPage = new ThreadLocal<>();
	ThreadLocal<RequirementHomePage> requirementHomePage = new ThreadLocal<>();
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
	ThreadLocal<AlertRuleHomepage> alertRuleHomepage = new ThreadLocal<>();
	ThreadLocal<SharingCreationPage> sharingCreationPage = new ThreadLocal<>();
	ThreadLocal<AssignmentRuleCreationPage> assignmentRuleCreationPage = new ThreadLocal<>();
	ThreadLocal<PlaybookHomePage> playbookHomePage = new ThreadLocal<>();
	ThreadLocal<OpportunityDetailPage> opportunityDetailPage = new ThreadLocal<>();
	ThreadLocal<LeadQualifyAssignmentRuleCreationPage> leadQualifyAssignmentRuleCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadQualifyAssignmentRuleDetailPage> leadQualifyAssignmentRuleDetailPage = new ThreadLocal<>();
	ThreadLocal<LeadQualifyAssignmentRuleHomePage> leadQualifyAssignmentRuleHomePage = new ThreadLocal<>();
	ThreadLocal<TriggerActionCreationPage> triggerActionCreationPage = new ThreadLocal<>();
	ThreadLocal<TaskCreationPage> taskCreationPage = new ThreadLocal<>();
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
		requirementDetailPage.set(new RequirementDetailPage(DriverManager.getWdriver()));
		requirementCreationPage.set(new RequirementCreationPage(DriverManager.getWdriver()));
		expectedLastName.set(ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime());
		accountCreationPage.set(new AccountCreationPage(DriverManager.getWdriver()));
		s.set(new SoftAssert());
		playbookHomePage.set(new PlaybookHomePage(DriverManager.getWdriver()));
		reviewCreationPage.set(new ReviewCreationPage(DriverManager.getWdriver()));
		reviewDetailPage.set(new ReviewDetailPage(DriverManager.getWdriver()));
		alertCreationPage.set(new AlertCreationPage(DriverManager.getWdriver()));
		alertDetailPage.set(new AlertDetailPage(DriverManager.getWdriver()));
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
		alertRuleHomepage.set(new AlertRuleHomepage(DriverManager.getWdriver()));
		sharingCreationPage.set(new SharingCreationPage(DriverManager.getWdriver()));
		assignmentRuleCreationPage.set(new AssignmentRuleCreationPage(DriverManager.getWdriver()));
		requirementHomePage.set(new RequirementHomePage(DriverManager.getWdriver()));
		opportunityDetailPage.set(new OpportunityDetailPage(DriverManager.getWdriver()));
		leadQualifyAssignmentRuleCreationPage
				.set(new LeadQualifyAssignmentRuleCreationPage(DriverManager.getWdriver()));
		leadQualifyAssignmentRuleDetailPage.set(new LeadQualifyAssignmentRuleDetailPage(DriverManager.getWdriver()));
		leadQualifyAssignmentRuleHomePage.set(new LeadQualifyAssignmentRuleHomePage(DriverManager.getWdriver()));
		triggerActionCreationPage.set(new TriggerActionCreationPage(DriverManager.getWdriver()));
		taskCreationPage.set(new TaskCreationPage(DriverManager.getWdriver()));
		taskDetailPage.set(new TaskDetailPage(DriverManager.getWdriver()));

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184705",
			"184706","184592" }, scriptType = { "" }, testCasePriority = { "" })
	@Test(priority = 1, enabled = true, groups = { "Regression", "Lead" })

	public void verifyClearButtonInLeadsActivityFieldsMapping() throws Exception {

		String expectedLeadLastName = LeadConstants.LASTNAME + ReUsableMethods.getCurrentdateTime();
		login.get().genericAdminLogin(webURL, adminUserMaster_Auto2, webpassword);
		navigationPanel.get().NavigateToObject("Lead");
		String parentWindowHandle = ReUsableMethods.getWindow();

		commonProductFunctions.get().clickOnToolboxMenuItem("Task Lead Mapping");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webClickElement(assignmentRuleCreationPage.get().mappingFieldSearch,
				"Mapping Lead Fields to account");

		commonProductFunctions.get().searchAndSelectCheckBox_UsingParameter("AssignTo");

		commonProductFunctions.get().clickOnSearchPicker("Assigned To");

		commonProductFunctions.get().selectNameFromPicker("AssignedTo");

		commonProductFunctions.get().clickOnSave();

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);

		navigationPanel.get().NavigateToObject("Lead");
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead(LeadConstants.LEAD_SYSTEM_LAYOUT, expectedLeadLastName, expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		commonProductFunctions.get().waitForLoader();

		relatedActivityPage.get().clickOn_RelatedActivity();
		relatedActivityPage.get().clickOn_NewTaskLink();
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_TASK_SYSTEM);

		ReUsableMethods.switchToChildWindowHandle();

		String expectedTaskSubjectName = TaskConstants.TASK_SUBJECT + ReUsableMethods.getCurrentdateTime();
		taskCreationPage.get().enterNewTaskSubject(expectedTaskSubjectName);
		ReUsableMethods.webClickElement(taskCreationPage.get().assignedToSearchPicker, "assigned to search picker");
		commonProductFunctions.get().selectSearchByvalueInPickerSearch("User Name");
		commonProductFunctions.get().selectNameFromPicker("Mr. Auto All");
		//commonProductFunctions.get().clickOnFirstCheckBox();
		String expectedAssignedTo = ReUsableMethods.getTextElementAttribute(taskCreationPage.get().assignedToField,
				"value");

		commonProductFunctions.get().clickSaveOrSaveAndNewOnCreationPage("Save");

		ReUsableMethods.switchToWindow(parentWindowHandle);
		relatedActivityPage.get().searchByRelatedUsingActivities("Open Activities", "Subject", expectedTaskSubjectName);

		String actualTaskName = ReUsableMethods.WebGetElementText(taskDetailPage.get().nameOnDetail, "Task Subject");

		s.get().assertEquals(actualTaskName, expectedTaskSubjectName, "Task Subject Name Not Match");

		commonProductFunctions.get().advanceSearch(expectedLeadLastName);

		ReUsableMethods.scrollElementToCentreOfScreen(leadDetailPage.get().verifyAssignTo);
		String actualAssignedTo = ReUsableMethods.WebGetElementText(leadDetailPage.get().verifyAssignTo, "Assgined to");
		s.get().assertEquals(actualAssignedTo, expectedAssignedTo, "Assigned to value not matched");

		login.get().genericAdminLogin(webURL, adminUserMaster_Auto2, webpassword);
		navigationPanel.get().NavigateToObject("Lead");

		commonProductFunctions.get().clickOnToolboxMenuItem("Task Lead Mapping");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webClickElement(leadHomePage.get().clear, "Clear the saved mapping");

		relatedActivityPage.get().clickOnOkButton();

		boolean isMappingPickerVisible = ReUsableMethods
				.WebIsElementDisplayed(taskCreationPage.get().assignedToSearchPicker, "Assigned to search picker");
		s.get().assertFalse(isMappingPickerVisible, "Aadhar Picker is visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184711",
			"184712" }, scriptType = { "" }, testCasePriority = { "2" })
	@Test(priority = 2, enabled = true, groups = { "Regression", "Lead" })

	public void verfyStatusCodeAndProductsInManageFieldOptionMappingConfig() throws Exception {

		login.get().genericAdminLogin(webURL, adminUserMaster_Auto2, webpassword);
		navigationPanel.get().NavigateToObject("Lead");

		commonProductFunctions.get().clickOnToolboxMenuItem("Manage Field Option Mapping");
		ReUsableMethods.switchToChildWindowHandle();

		commonProductFunctions.get().searchApply(LeadConstants.PRODUCT_STATUSCODE);
		commonProductFunctions.get().waitforLoadingInList();
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing, leadHomePage.get().view);

		ReUsableMethods.webClickElement(leadHomePage.get().view, "View");
		ReUsableMethods.switchToChildWindowHandle();

		String parentWindowHandelIII = ReUsableMethods.getWindow();
		ReUsableMethods.webClickElement(leadHomePage.get().addMoreStatus, "Add More");

		ReUsableMethods.switchToChildWindowHandle();
		commonProductFunctions.get().clickOnSearchPicker("Field Option Picker");
		String expectedProduct = LeadConstants.PRODUCT_JEEVAN_ANAND;
		commonProductFunctions.get().searchAndSelectCheckBox_UsingParameter(expectedProduct);
		// ReUsableMethods.webClickElement(commonProductFunctions.get().okayButton,
		// "Ok");
		commonProductFunctions.get().clickOnSave();
		ReUsableMethods.switchToWindow(parentWindowHandelIII);
		ReUsableMethods.webClickElement(leadHomePage.get().newStatusCode, "New");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.refreshWebPage();

		commonProductFunctions.get().clickOnSearchPicker("Field Option Picker");
		commonProductFunctions.get().waitForLoader();
		String actualProduct = leadHomePage.get().getProductOnFieldOptionMappeing(expectedProduct);
		s.get().assertEquals(actualProduct, expectedProduct, "Product not matched");
		ReUsableMethods.webClickElement(leadHomePage.get().crossIcon, "Cross Icon");
		commonProductFunctions.get().clickOnCancel();
		ReUsableMethods.switchToWindow(parentWindowHandelIII);
		ReUsableMethods.webClickElement(leadHomePage.get().clearLink, "Clear");
		ReUsableMethods.webClickElement(commonProductFunctions.get().okConfirmation, "OK");

		ReUsableMethods.webClickElement(leadHomePage.get().newStatusCode, "New");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.refreshWebPage();

		commonProductFunctions.get().clickOnSearchPicker("Field Option Picker");
		commonProductFunctions.get().waitForLoader();
		boolean isProductNotExist = commonProductFunctions.get().isNoDataExistDisplayed();
		s.get().assertTrue(isProductNotExist, "Product exist");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { 
			"184734" }, scriptType = { "" }, testCasePriority = { "3" })
	@Test(priority = 3, enabled = true, groups = { "Regression", "Lead" })

	public void verifyButtonSectionWouldBeLinkedWithViewSection() throws Exception {

		String expectedLeadLastName = LeadConstants.LASTNAME + ReUsableMethods.getCurrentdateTime();

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);

		navigationPanel.get().NavigateToObject("Lead");
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead(LeadConstants.LEAD_SYSTEM_LAYOUT, expectedLeadLastName, expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		commonProductFunctions.get().waitForLoader();
		commonProductFunctions.get().clickOnClose();
		commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Created", "Last Name",
				expectedLeadLastName);

		ReUsableMethods.webClickJavaScriptExecutor(leadHomePage.get().viewCheckBox);

		boolean isCustomActionButtonDisplayed = leadHomePage.get().isCustomActionButtonVisible("Update Home Filter");

		s.get().assertTrue(isCustomActionButtonDisplayed, "Custom Action button not visible");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "146731","184733"}, scriptType = {
			"" }, testCasePriority = { "4" })
	@Test(priority = 4, enabled = true, groups = { "Lead","Regression" })
	public void verifyUIOfMoreActionButton() throws Exception {
		login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Lead");

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		commonProductFunctions.get().waitForLoader();
		commonProductFunctions.get().clickOnClose();	
		commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Created", "Last Name",
				expectedLastName.get());

		commonProductFunctions.get().clickOnFirstCheckBox();

		boolean isChangeOwnerButtonVisible = ReUsableMethods.WebIsElementDisplayed(leadHomePage.get().changeOwnerButton, "Change Owner Button");
		s.get().assertTrue(isChangeOwnerButtonVisible, "Change Owner Button is not visible");

		boolean isDisqualifyButtonVisible = ReUsableMethods.WebIsElementDisplayed(leadHomePage.get().disqualifyButton, "Disqualify Button");
		s.get().assertTrue(isDisqualifyButtonVisible, "Disqualify Button is not visible");

		// boolean isMailMergeButtonVisible = ReUsableMethods.WebIsElementDisplayed(leadHomePage.get().mailMergeButton, "Mail Merge Button");
		// s.get().assertTrue(isMailMergeButtonVisible, "Mail Merge Button is not visible");

		boolean isMassDeleteButtonVisible = ReUsableMethods.WebIsElementDisplayed(leadHomePage.get().massDeleteButton, "Mass Delete Button");
		s.get().assertTrue(isMassDeleteButtonVisible, "Mass Delete button not visible");

		s.get().assertAll();	
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "152012","152014" }, scriptType = {
			"" }, testCasePriority = { "5" })	
	@Test(priority = 5, enabled = true, groups = { "Lead","Regression" })
	public void verifyFieldShouldBeInVerticalMode() throws Exception {
		login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Lead");

		boolean isSideBarVisible = ReUsableMethods.WebIsElementDisplayed(leadHomePage.get().sidebar, "Side Bar");
		s.get().assertTrue(isSideBarVisible, "Side Bar is not visible");	

		s.get().assertAll();
	}
}
