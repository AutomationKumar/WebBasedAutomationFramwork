package com.businessnext.leads.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.objects.appointment.AppointmentCreationPage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.businessnext.objects.note.pages.NoteCreationPage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.common.pages.ObjectConstants;
import com.common.pages.RelatedActivityPage;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)

public class LeadRelatedMappings extends BaseClass {
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
	ThreadLocal<AppointmentCreationPage> appointmentCreationPage = new ThreadLocal<>();

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
		s.set(new SoftAssert());

		expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
		expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;
		expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "120848", "120852", "120853",
			"120854" }, scriptType = { "" }, testCasePriority = { "" })
	@Test(priority = 1, enabled = true, groups = { "Regression", "Lead" })

	public void verify_FieldOpTionMappingInToolBox() throws Exception {

		login.get().genericAdminLogin(webURL, auto5User, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		String expectedPageTitle = LeadConstants.TITLE_FIELD_OPTION_MAPPING;
		ReUsableMethods.webClickElement(leadHomePage.get().toolBox_Lead, " ToolBox");
		commonProductFunctions.get().navigateToToolboxMenuItem("Manage Field Option Mapping");
		ReUsableMethods.switchToChildWindowHandle();
		String actualPageTitle = commonProductFunctions.get().getPageTitle();
		Assert.assertEquals(actualPageTitle, expectedPageTitle, "actualPageTitle not equals expectedPageTitle.");

	}

}