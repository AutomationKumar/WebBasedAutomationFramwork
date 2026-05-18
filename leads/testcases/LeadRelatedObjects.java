package com.businessnext.leads.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.customactionbutton.pages.ChangeOwnerActionButtonCreationPage;
import com.businessnext.objects.cases.pages.CasesHomePage;
import com.businessnext.objects.issue.pages.IssueConstants;
import com.businessnext.objects.issue.pages.IssueCreationPage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.businessnext.objects.note.pages.NoteConstants;
import com.businessnext.objects.note.pages.NoteCreationPage;
import com.businessnext.objects.playbook.pages.PlaybookConstants;
import com.businessnext.objects.playbook.pages.PlaybookCreationPage;
import com.businessnext.objects.playbook.pages.PlaybookDesignerPage;
import com.businessnext.objects.playbook.pages.PlaybookHomePage;
import com.businessnext.objects.subsidiary.pages.SubsidiaryConstantPage;
import com.businessnext.objects.subsidiary.pages.SubsidiaryCreationPage;
import com.businessnext.setupmodules.lookupmanagement.LookupManagementPage;
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
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)

public class LeadRelatedObjects extends BaseClass {
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();

	ThreadLocal<LeadHomePage> leadHomePage = new ThreadLocal<>();
	ThreadLocal<LeadCreationPage> leadCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadDetailPage> leadDetailPage = new ThreadLocal<>();
	ThreadLocal<LeadCommonFunctions> leadCommonFunctions = new ThreadLocal<>();
	ThreadLocal<RelatedActivityPage> relatedActivityPage = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<String> expectedLastName = new ThreadLocal<>();
	ThreadLocal<NoteCreationPage> noteCreationPage = new ThreadLocal<>();
	ThreadLocal<SubsidiaryCreationPage> subsidiaryCreationPage = new ThreadLocal<>();
	ThreadLocal<LookupManagementPage> lookupMangementPage = new ThreadLocal<>();

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
		subsidiaryCreationPage.set(new SubsidiaryCreationPage(DriverManager.getWdriver()));
		expectedLastName.set(ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime());
		lookupMangementPage.set(new LookupManagementPage(DriverManager.getWdriver()));

		s.set(new SoftAssert());

		expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
		expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;
		expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184567",
			"184577" }, scriptType = { "" }, testCasePriority = { "1" })

	@Test(priority = 1, enabled = true, groups = { "Regression", "Lead" })

	public void createAndCloseTaskFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		String expectedTaskSubject = ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Task from Lead Detail

		leadDetailPage.get().clickOnRelatedActivity_Tab();
		String parentWindowHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_TASK);

		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_TASK_SYSTEM);

		relatedActivityPage.get().createNewTask(expectedTaskSubject, "In Progress");

		ReUsableMethods.switchToWindow(parentWindowHandle);
		relatedActivityPage.get().searchByRelatedUsingActivities("Open Activities", "Subject", expectedTaskSubject);
		String actualTaskSubject = relatedActivityPage.get().verifyActivity("Open Activities", expectedTaskSubject);

		s.get().assertEquals(actualTaskSubject, expectedTaskSubject,
				"actual Task Subject not Equals expected Task Subject");

		ReUsableMethods.safeClick(relatedActivityPage.get().threeDots, relatedActivityPage.get().close);

		ReUsableMethods.webClickElement(relatedActivityPage.get().close, "Close Task");

		ReUsableMethods.switchToChildWindowHandle();

		relatedActivityPage.get().clickOn_SaveButton_Task();

		ReUsableMethods.waitforWindowSize(1);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().searchByRelatedUsingActivities("Closed Activities", "Subject", expectedTaskSubject);
		String actualClosedTaskSubject = relatedActivityPage.get().verifyActivity("Closed Activities",
				expectedTaskSubject);

		s.get().assertEquals(actualClosedTaskSubject, expectedTaskSubject,
				"actual Task Subject not Equals expected Task Subject");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184568", "184743",
			"184744" }, scriptType = { "" }, testCasePriority = { "2" })

	@Test(priority = 2, enabled = true, groups = { "Regression", "Lead" })

	public void createAppoinmentFromLeadDetail() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		String expectedAppointmentSubject = ReUsableMethods.generateRandomText(8)
				+ ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Appointment from Lead Detail
		leadDetailPage.get().clickOnRelatedActivity_Tab();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_APPOINTMENT);

		String parentWindowHandle = ReUsableMethods.getWindow();
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_APPOINTMENT_SYSTEM);

		relatedActivityPage.get().createNewAppointment(expectedAppointmentSubject, "In Progress");

		ReUsableMethods.switchToWindow(parentWindowHandle);
		// relatedActivityPage.get().searchByRelatedUsingActivities("Open Activities",
		// "Subject",
		// expectedAppointmentSubject);
		String actualAppointmentSubject = relatedActivityPage.get().verifyActivity("Open Activities",
				expectedAppointmentSubject);

		s.get().assertEquals(actualAppointmentSubject, expectedAppointmentSubject,
				"actual Task Subject not Equals expected Task Subject");

		ReUsableMethods.webClickElement(leadDetailPage.get().threeDot, "three dot");
		leadDetailPage.get().clickButtonByText("Edit");

		String expectedEdittedName = ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime();

		relatedActivityPage.get().createNewAppointment(expectedEdittedName, "In Progress");

		ReUsableMethods.switchToWindow(parentWindowHandle);

		// String actualEdittedName = relatedActivityPage.get().verifyActivity("Open
		// Activities", expectedEdittedName);
		//
		// s.get().assertEquals(actualEdittedName, expectedEdittedName, "Editted name do
		// not match");

		ReUsableMethods.webClickElement(leadDetailPage.get().threeDot, "three dot");
		leadDetailPage.get().clickButtonByText("Close");

		ReUsableMethods.switchToChildWindowHandle();

		relatedActivityPage.get().clickOn_SaveButton_Task();

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);
		// relatedActivityPage.get().searchByRelatedUsingActivities("Open Activities",
		// "Subject",
		// expectedAppointmentSubject);

		ReUsableMethods.scrollElementToCentreOfScreen(relatedActivityPage.get().closedActivity);

		String actualClosedAppointmentSubject = relatedActivityPage.get().verifyActivity("Closed Activities",
				expectedEdittedName);

		s.get().assertEquals(actualClosedAppointmentSubject, expectedEdittedName,
				"actual Task Subject not Equals expected Task Subject");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184569",
			"184730" }, scriptType = {

					"" }, testCasePriority = { "3" })
	@Test(priority = 3, enabled = true, groups = { "Regression", "Lead" })

	public void createIssueFromLeadDetail() throws Exception {
		IssueCreationPage issueCreationPage = new IssueCreationPage(DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		// Create Appointment from Lead Detail

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_COLLABORATION);
		String parentWindowHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_ISSUE);
		String expectedIssueSubject = issueCreationPage.createIssue(LayoutConstants.LAYOUT_ISSUE_SYSTEM,
				IssueConstants.ISSUE_SUBJECT, null, null, null);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOnModule(RelatedObjectConstants.TAB_NAME_RELATED_COLLABORATION);

		String actualIssueSubject = ReUsableMethods.WebGetElementText(relatedActivityPage.get().relatedSubject,
				"relatedSubject First");

		s.get().assertEquals(actualIssueSubject, expectedIssueSubject);
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184571" }, scriptType = {
			"" }, testCasePriority = { "4" })
	@Test(priority = 4, enabled = true, groups = { "Regression", "Lead" })

	public void createNotesFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		// Create Notes from Lead Detail
		relatedActivityPage.get().clickOnTab("Related Note");

		String parentWindowHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_NOTE);
		String expectedNoteSubject = noteCreationPage.get().createNote(null, NoteConstants.NOTE_SUBJECT);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOnTab("Related Note");

		String actualNoteSubject = ReUsableMethods.WebGetElementText(relatedActivityPage.get().relatedSubject,
				"relatedSubject First");

		s.get().assertEquals(actualNoteSubject, expectedNoteSubject);
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184599" }, scriptType = {
			"" }, testCasePriority = { "5" })
	@Test(priority = 5, enabled = true, groups = { "Regression", "Lead" })

	public void editNotesFromLeadDetail() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		// Create Notes from Lead Detail
		relatedActivityPage.get().clickOnTab("Related Note");

		String parentWindowHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_NOTE);
		String expectedNoteSubject = noteCreationPage.get().createNote(null, NoteConstants.NOTE_SUBJECT);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOnTab("Related Note");

		relatedActivityPage.get().searchByRelated("", "Subject", expectedNoteSubject);
		String actualNoteSubject = ReUsableMethods.WebGetElementText(relatedActivityPage.get().relatedSubject,
				"relatedSubject First");

		s.get().assertEquals(actualNoteSubject, expectedNoteSubject, "actualNoteSubject not Equals actualNoteSubject");

		// Edit Note

		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				commonProductFunctions.get().editOnListing);
		ReUsableMethods.webClickElement(commonProductFunctions.get().editOnListing, "Edit On Listing");
		ReUsableMethods.switchToChildWindowHandle();
		String expectedEditedNote = noteCreationPage.get().enterSubject("Edited Note");
		commonProductFunctions.get().clickOnSave();

		// commonProductFunctions.get().clickOnClose();
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().searchByRelated("", "Subject", expectedEditedNote);
		String actualEditedNote = ReUsableMethods.WebGetElementText(relatedActivityPage.get().relatedSubject,
				"relatedSubject First");

		s.get().assertEquals(actualEditedNote, expectedEditedNote,
				"Actual Edited Note not equals  Expected Edited Note");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184600" }, scriptType = {
			"" }, testCasePriority = { "6" })
	@Test(priority = 6, enabled = true, groups = { "Regression", "Lead" })

	public void deleteNotesFromLeadDetail() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();

		// Create Notes from Lead Detail
		relatedActivityPage.get().clickOnTab("Related Note");

		String parentWindowHandle = ReUsableMethods.getWindow();
		leadDetailPage.get().clickOnLink(RelatedObjectLinkConstants.LINK_NAME_NEW_NOTE);
		String expectedNoteSubject = noteCreationPage.get().createNote(null, NoteConstants.NOTE_SUBJECT);
		ReUsableMethods.switchToWindow(parentWindowHandle);

		relatedActivityPage.get().clickOnTab("Related Note");
		relatedActivityPage.get().searchByRelated("", "Subject", expectedNoteSubject);
		String actualNoteSubject = ReUsableMethods.WebGetElementText(relatedActivityPage.get().relatedSubject,
				"relatedSubject First");

		s.get().assertEquals(actualNoteSubject, expectedNoteSubject, "actualNoteSubject not Equals actualNoteSubject");

		// Delete Note
		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				leadDetailPage.get().linkDeleteFirstRelatedNotes);

		ReUsableMethods.safeClick(leadDetailPage.get().linkDeleteFirstRelatedNotes,

				commonProductFunctions.get().ok_Button);

		commonProductFunctions.get().clickOkButtonForMassUpdate();

		commonProductFunctions.get().waitForLoader();

		relatedActivityPage.get().clickOnTab("Related Note");

		s.get().assertTrue(commonProductFunctions.get().isNoDataExistDisplayed(), "No Data Exists Not Displayed");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184768" }, scriptType = {
			"" }, testCasePriority = { "7" })
	@Test(priority = 7, enabled = true, groups = { "Regression", "Lead" })

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

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184769" }, scriptType = {
			"" }, testCasePriority = { "8" })
	@Test(priority = 8, enabled = true, groups = { "Regression", "Lead" })

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

		ReUsableMethods.waitforWindowSize(1);

		ReUsableMethods.switchToWindow(parentWindowHandle);

		String actualEditedSubsidiaryLastName = ReUsableMethods
				.WebGetElementText(relatedActivityPage.get().firstNameRecordInRelatedList, "First Subsidiary Name");

		s.get().assertEquals(actualEditedSubsidiaryLastName, expectedEditedSubsidiaryname,
				"Actual Edited Subsidiary Name not equals  Expected Edited Subsidiary Name");
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184770" }, scriptType = {
			"" }, testCasePriority = { "9" })
	@Test(priority = 9, enabled = true, groups = { "Regression", "Lead" })

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

		// System.out.println("actualSubsidiaryLastName is printed as
		// "+actualSubsidiaryLastName);

		s.get().assertEquals(actualSubsidiaryLastName, subsidiaryLastName,
				"Actual Subsidiary Name not equals  Expected Subsidiary Name");
		// Delete Subsidiary

		ReUsableMethods.safeClick(commonProductFunctions.get().threeDotsOnListing,
				leadDetailPage.get().deleteSubsidiary);
		ReUsableMethods.webClickElement(leadDetailPage.get().deleteSubsidiary, "link Delete Subsidiary");
		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on Delete Button");

		commonProductFunctions.get().waitForLoader();

		commonProductFunctions.get().advanceSearch(subsidiaryLastName);

		s.get().assertTrue(commonProductFunctions.get().isNoDataExistDisplayed());
		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184782" }, scriptType = {
			"" }, testCasePriority = { "10" })
	@Test(priority = 10, enabled = true, groups = { "Regression", "Lead" })

	public void verifyIfEmailIsNotENteredOnLeadCreationPageThenUserShouldBeAbleToAdd() throws Exception {

		PlaybookHomePage playbookHomePage = new PlaybookHomePage(DriverManager.getWdriver());
		PlaybookCreationPage playbookCreationPage = new PlaybookCreationPage(DriverManager.getWdriver());
		PlaybookDesignerPage playbookDesignerPage = new PlaybookDesignerPage(DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, auto5User, webpassword);
		navigationPanel.get().NavigateToObject("Playbook");
		playbookHomePage.clickOnNewButton();
		String expectedPlaybook = playbookCreationPage.enterMandatoryFields(PlaybookConstants.PLAYBOOK_NAME,
				PlaybookConstants.PLAYBOOK_STATUS_PUBLISHED, PlaybookConstants.PLAYBOOK_CATEGORY_ONBOARDING);
		playbookCreationPage.selectObjectForPlaybook(PlaybookConstants.PLAYBOOK_OBJECT_LEAD);
		playbookCreationPage.clickOnSaveAndDesignButton();
		playbookDesignerPage.clickOnAddIconOnDetailPage("1");
		playbookDesignerPage.addEmailElement();
		playbookDesignerPage.clickOnAddIconOnDetailPage("2");
		playbookDesignerPage.addEndElement();
		playbookDesignerPage.emailAdvanceConfig(PlaybookConstants.PLAYBOOK_EMAIL_STEPNAME,
				PlaybookConstants.PLAYBOOK_ACTIVITY_LAYOUT, PlaybookConstants.PLAYBOOK_EMAIL_TEMPLATE,
				PlaybookConstants.PLAYBOOK_EMAIL_TO, PlaybookConstants.PLAYBOOK_EMAIL_SUBJECT);
		// playbookDesignerPage.sendAttachment();
		playbookDesignerPage.saveEmailConfiguration();
		playbookDesignerPage.saveBeforeExit();
		navigationPanel.get().navigateToHomePage();
		navigationPanel.get().NavigateToObject("Lead");
		leadHomePage.get().hoverOverNewButton();
		leadHomePage.get().clickOnLayout(LeadConstants.LAYOUT_1);
		leadCreationPage.get().enterLeadLastName(LeadConstants.LEAD_LAST_NAME);
		leadCreationPage.get().selectLeadRating(LeadConstants.LEAD_RATING_ENGLISH);
		leadCreationPage.get().selectLeadProduct(LeadConstants.LEAD_PRODUCT);
		leadCreationPage.get().clickOnSaveLead();
		leadDetailPage.get().clickOnCardToggleButton();
		leadDetailPage.get().clickOnAddPlaybookButton();
		leadDetailPage.get().addTaggedPlaybookOnDetailPage(expectedPlaybook);
		leadDetailPage.get().clickOnPauseButton();
		leadDetailPage.get().clickOnPauseButtonPlaybook();
		leadDetailPage.get().clickOnPlayButtonPlaybook();
		leadDetailPage.get().clickOnStepNamePlaybookOnDetailPage(PlaybookConstants.PLAYBOOK_EMAIL_STEPNAME);
		leadDetailPage.get().sendEmailForPlaybookCard(PlaybookConstants.PLAYBOOK_EMAIL_TO,
				PlaybookConstants.PLAYBOOK_EMAIL_SUBJECT);
		Assert.assertTrue(leadDetailPage.get().isCompleteStatusVisible());
		leadDetailPage.get().clickOnCardToggleButton();
		leadDetailPage.get().switchToActivityTab();
		commonProductFunctions.get().searchRecordFromListing(PlaybookConstants.SEARCH_BY_SUBJECT,
				PlaybookConstants.PLAYBOOK_EMAIL_SUBJECT);
		Assert.assertTrue(
				leadDetailPage.get().isClosedActivityPresentOnDetailPage(PlaybookConstants.PLAYBOOK_EMAIL_SUBJECT));

		s.get().assertAll();
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184608", "165160","165164","165166","165168"}, scriptType = {
			"" }, testCasePriority = { "11" })
	@Test(priority = 11, enabled = true, groups = { "Regression", "Lead" })

	public void changeOwnerButtonOnLeadHomePage() throws Exception {

		ChangeOwnerActionButtonCreationPage changeOwnerActionButtonCreationPage = new ChangeOwnerActionButtonCreationPage(
				DriverManager.getWdriver());
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", expectedLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");

		commonProductFunctions.get().clickOnClose();

		commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Created", "Last Name",
				expectedLastName.get());

		commonProductFunctions.get().clickOnFirstCheckBox();

		changeOwnerActionButtonCreationPage.clickOnChangeOwner_AfterSelectrecord();

		changeOwnerActionButtonCreationPage.clickOnSearchPickerIcon_AfterClickingChangeOwnerButton();

		String expectedLeadOwner = LeadConstants.MR_AUTO_2;
		
		changeOwnerActionButtonCreationPage.searchApplyForChangeOwner(expectedLeadOwner);

		//changeOwnerActionButtonCreationPage.clickOnArrowIcon_AfterSearchUser_SelectRecord();

		changeOwnerActionButtonCreationPage.clickOnFirstShortName_AfterSearch();

		changeOwnerActionButtonCreationPage.clickOnChangeOwner_AfterSelectUser();

		changeOwnerActionButtonCreationPage.clickOnOkButton_AfterMapping();

		commonProductFunctions.get().advanceSearch(expectedLastName.get());

		String actualLeadOwner = ReUsableMethods.WebGetElementText(leadCreationPage.get().inputTxtLeadOwner,
				"Lead Owner");

		s.get().assertEquals(actualLeadOwner, expectedLeadOwner, "Lead Owner do not matches");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184593" }, scriptType = {
			"" }, testCasePriority = { "12" })
	@Test(priority = 12, enabled = true, groups = { "Regression", "Lead" })

	public void creationOfNewLookUpValueForSalutationType() throws Exception {

		login.get().genericAdminLogin(webURL, adminUserMaster_Auto2, webpassword);

		String parentWindowHandle = ReUsableMethods.getWindow();

		navigationPanel.get().navigateToSetup();

		navigationPanel.get().navigateToAdminMenuItem("Lookup Management");

		lookupMangementPage.get().selectTheObjectFromDropdown("Lead");

		lookupMangementPage.get().selectObjects("Salutation");

		ReUsableMethods.switchToChildWindowHandle();

		String windowHandle2 = ReUsableMethods.getWindow();

		lookupMangementPage.get().clickOnNewButton();

		ReUsableMethods.switchToChildWindowHandle();

		String expectedSalutation = lookupMangementPage.get().enterLookupName("AutoEr");

		lookupMangementPage.get().clickOnSaveButton();

		ReUsableMethods.switchToWindow(parentWindowHandle);

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().clickOnNewIcon();

		commonProductFunctions.get().clickOnLayout("Lead_System");

		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadSalutation, expectedSalutation, "Salutation");

		String actualSalutation = ReUsableMethods.WebGetElementText(leadCreationPage.get().leadSalutation,
				"Lead salutation");
		s.get().assertEquals(actualSalutation, expectedSalutation, "Salutation do not match");

		navigationPanel.get().navigateToSetup();

		navigationPanel.get().navigateToAdminMenuItem("Lookup Management");

		lookupMangementPage.get().selectTheObjectFromDropdown("Lead");

		lookupMangementPage.get().selectObjects("Salutation");

		ReUsableMethods.switchToChildWindowHandle();

		commonProductFunctions.get().searchApply(expectedSalutation);

		ReUsableMethods.webClickElement(commonProductFunctions.get().threeDots, "Three Dots");

		ReUsableMethods.webClickElement(lookupMangementPage.get().deleteButton_UnsubscriberReason,
				"Delete the Salutation");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webClickElement(lookupMangementPage.get().deleteConfirmation_UnsubscriberReason,
				"Delete confirmation");

		ReUsableMethods.switchToWindow(windowHandle2);

		commonProductFunctions.get().searchApply(expectedSalutation);

		boolean isNoDataExistsDisplayed = ReUsableMethods
				.WebIsElementDisplayed(commonProductFunctions.get().noDataExists, "No Data Exists");
		s.get().assertTrue(isNoDataExistsDisplayed, "Data Exists");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184714","184708" }, scriptType = {
			"" }, testCasePriority = { "13" })
	@Test(priority = 13, enabled = true, groups = { "Regression", "Lead" })

	public void creationOfFieldOptionMappingForCustomField() throws Exception {

		CasesHomePage casesHomePage = new CasesHomePage(DriverManager.getWdriver());

		login.get().genericAdminLogin(webURL, adminUserMaster_Auto2, webpassword);


		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

		commonProductFunctions.get().clickOnToolboxMenuItem("Manage Field Option Mapping");

		ReUsableMethods.switchToChildWindowHandle();
		String secondParentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.webClickElement(casesHomePage.newMapping, "New Mapping");

		ReUsableMethods.switchToChildWindowHandle();

		ReUsableMethods.webEnterText(casesHomePage.nameOnFieldOption, "Mapping Name", "Name");

		ReUsableMethods.webClickElement(commonProductFunctions.get().save, "Save the new Mapping");
		ReUsableMethods.switchToWindow(secondParentWindowHandle);

		ReUsableMethods.safeClick(casesHomePage.FirstThreeDots, casesHomePage.view);
		ReUsableMethods.webClickElement(casesHomePage.view, "View");
		ReUsableMethods.switchToChildWindowHandle();

		s.get().assertTrue(casesHomePage.verifyAddMoreNew(), "Add More is not visible");
		ReUsableMethods.webClickElement(casesHomePage.addMoreNew, "Add More");
		ReUsableMethods.switchToChildWindowHandle();

		s.get().assertTrue(casesHomePage.verifyFieldOptionMappingHeading(), "Field Option Mapping is not visible");
		s.get().assertAll();

	}

}
