package com.businessnext.leads.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
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
public class Lead_TestCasesII extends BaseClass {
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<LeadHomePage> leadHomePage = new ThreadLocal<>();
	ThreadLocal<LeadCreationPage> leadCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadDetailPage> leadDetailPage = new ThreadLocal<>();
	ThreadLocal<LeadCommonFunctions> leadCommonFunctions = new ThreadLocal<>();
	ThreadLocal<RelatedActivityPage> relatedActivityPage = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();

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
		s.set(new SoftAssert());
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184776" }, scriptType = {
			"" }, testCasePriority = { "1" })
	@Test(priority = 1, enabled = true, groups = { "Regression", "Lead" })

	public void systemCurrency_Field_On_New_Edit_And_Detail_Layout_ForLeads_And_Opportunity_Objects() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Lead");
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_FOR_CURRENCY_FIELD);
		leadCreationPage.get().createLeadWithMandatoryField(LeadConstants.LASTNAME, LeadConstants.LEAD_RATING_COLD,
				LeadConstants.PRODUCT_AUTO_LOAN);
		String actualCurrency = leadDetailPage.get().getCurrency();
		s.get().assertEquals(actualCurrency, "INR", "Currency is mismatch");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184750", "184751", "192568",
			"192569","184713" }, scriptType = { "" }, testCasePriority = { "2" })
	@Test(priority = 2, enabled = true, groups = { "Regression", "Lead" })

	public void executionOfStatusCodeAndProductsMapping() throws Exception {

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Lead");
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_LEAD_SYSTEM);

		String expectedLastName = leadCreationPage.get().createLeadWithMandatoryField(LeadConstants.Lastname,
				LeadConstants.LEAD_HOT_RATING, LeadConstants.PRODUCT_AUTO_LOAN);

		commonProductFunctions.get().clickOnEdit();

		//ReUsableMethods.scrollElementToCentreOfScreen(leadCreationPage.get().leadStatusCode);

		leadCreationPage.get().selectLeadStatusCode(LeadConstants.LEAD_STATUSCODE_ACTIVE);

		commonProductFunctions.get().clickSaveOrSaveAndNewOnCreationPage("Save");

		String actualProduct = leadDetailPage.get().getProduct();
		s.get().assertEquals(actualProduct, LeadConstants.PRODUCT_AUTO_LOAN, "Product is mismatch");

		String actualStatusCode = leadDetailPage.get().get_StatusCode();
		s.get().assertEquals(actualStatusCode, LeadConstants.LEAD_STATUSCODE_ACTIVE, "Status code is mismatch");

		commonProductFunctions.get().clickOnEdit();

		leadCreationPage.get().selectLeadStatusCode(LeadConstants.LEAD_STATUSCODE_NEW);

		commonProductFunctions.get().clickSaveOrSaveAndNewOnCreationPage("Save");

		String actualLastName = leadDetailPage.get().getLastName();
		s.get().assertEquals(actualLastName, expectedLastName, "Last name is mismatch");

		s.get().assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184740" }, scriptType = {
			"" }, testCasePriority = { "3" })
	@Test(priority = 3, enabled = true, groups = { "Regression", "Lead" })

	public void defaultTerritoryOnLeadCreation() throws Exception {
		login.get().genericAdminLogin(webURL,autoind1, webpassword);
		navigationPanel.get().NavigateToObject("Lead");
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_LEAD_SYSTEM);

		boolean isLeadSystemLayoutDisplay = ReUsableMethods
				.WebIsElementDisplayed(commonProductFunctions.get().pageTitle, "pageTitle");
		s.get().assertTrue(isLeadSystemLayoutDisplay, "Lead System layout is not visible");

		String actualTextOfTerritory = leadCreationPage.get().getTerritoryText();
		s.get().assertEquals(actualTextOfTerritory, "", "Territory text is not null");

		navigationPanel.get().navigateToLogout();

		login.get().genericAdminLogin(webURL, auto_sub2, webpassword);
		navigationPanel.get().NavigateToObject("Lead");
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		commonProductFunctions.get().clickOnLayout(LayoutConstants.LAYOUT_LEAD_SYSTEM);

		boolean isLeadSystemLayoutDisplayAfterAnotherLogin = ReUsableMethods
				.WebIsElementDisplayed(commonProductFunctions.get().pageTitle, "pageTitle");
		s.get().assertTrue(isLeadSystemLayoutDisplayAfterAnotherLogin, "Lead System layout is not visible");

		ReUsableMethods.webClickElement(leadCreationPage.get().territorySearchIcon, "Territory");
		commonProductFunctions.get().searchApply("Juhu");

		ReUsableMethods.webClickElement(commonProductFunctions.get().firstName, "First Territory After Search");
		String actualTextOfTerritoryInAnotherLogin = leadCreationPage.get().getTerritoryText();
		s.get().assertEquals(actualTextOfTerritoryInAnotherLogin, "Juhu", "Territory text is mismatch");

		s.get().assertAll();
	}

	
}
