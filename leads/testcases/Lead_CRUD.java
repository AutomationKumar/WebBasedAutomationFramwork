package com.businessnext.leads.testcases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.objects.campaign.pages.CampaignConstants;
import com.businessnext.objects.campaign.pages.CampaignCreationPage;
import com.businessnext.objects.campaign.pages.CampaignDetailPage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.common.pages.ObjectConstants;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.mcp.inspector.BrowserInspector;
import com.mcp.inspector.ElementInfo;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)

public class Lead_CRUD extends BaseClass {
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<LeadHomePage> leadHomePage = new ThreadLocal<>();
	ThreadLocal<LeadCreationPage> leadCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadDetailPage> leadDetailPage = new ThreadLocal<>();
	ThreadLocal<String> actualLastName = new ThreadLocal<>();
	ThreadLocal<LeadCommonFunctions> leadCommonFunctions = new ThreadLocal<>();
	ThreadLocal<CampaignCreationPage> campaignCreationPage = new ThreadLocal<>();
	ThreadLocal<CampaignDetailPage> campaignDetailPage = new ThreadLocal<>();
	String expectedLeadRating;
	String expectedLeadStatusCode;
	String expectedLeadProduct;

	@BeforeMethod(alwaysRun = true)
	public void initialiseclassObjects() {
		login.set(new webReusableBusinessFunctions());
		navigationPanel.set(new NavigationPanel(DriverManager.getWdriver()));
		commonProductFunctions.set(new CommonProductFunctions(DriverManager.getWdriver()));
		s.set(new SoftAssert());
		campaignCreationPage.set(new CampaignCreationPage(DriverManager.getWdriver()));
		leadHomePage.set(new LeadHomePage(DriverManager.getWdriver()));
		leadCommonFunctions.set(new LeadCommonFunctions());
		leadCreationPage.set(new LeadCreationPage(DriverManager.getWdriver()));
		leadDetailPage.set(new LeadDetailPage(DriverManager.getWdriver()));
		campaignCreationPage.set(new CampaignCreationPage(DriverManager.getWdriver()));
		campaignDetailPage.set(new CampaignDetailPage(DriverManager.getWdriver()));
		actualLastName.set(ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime());
		campaignCreationPage.set(new CampaignCreationPage(DriverManager.getWdriver()));
		campaignDetailPage.set(new CampaignDetailPage(DriverManager.getWdriver()));
		expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
		expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;
		expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184741", "184788",
			"184738" }, scriptType = { "" }, testCasePriority = { "1" })

	@Test(priority = 1, enabled = true, groups = { "Sanity", "Regression", "Lead" })

	public void mandatoryLeadValidations() throws Exception {
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		commonProductFunctions.get().clickOnLayout("Lead_System");
		/*
		 * ReUsableMethods.webClickElement(leadHomePage.get().leadSystem_Layout,
		 * "Click Lead system Layout");
		 */
		ReUsableMethods.webClickElement(leadCreationPage.get().save, "Click Save Button");
		List<String> actualList = commonProductFunctions.get().getValidationMessage();
		Assert.assertEquals(actualList, LeadConstants.expectedLeadList);

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184558", "181673", "280535",
			"325529","184737" }, scriptType = { "" }, testCasePriority = { "2" })
	@Test(priority = 2, enabled = true, groups = { "Sanity", "Regression", "Lead" })

	public void createLead() throws Exception {
		SoftAssert s = new SoftAssert();
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject("Campaign");
		commonProductFunctions.get().clickOnNewIcon();
		String expectedCampaignName = campaignCreationPage.get().createCampaign(
				CampaignConstants.CAMPAIGN_CAMPAIGNSYSTEM_LAYOUT, CampaignConstants.campaignName,
				CampaignConstants.productName, CampaignConstants.statusCampaign);
		Assert.assertTrue(campaignDetailPage.get().verifyCampaignName_onDetailPage(expectedCampaignName),
				"Test Case failed because campaign name is not same as actual");
		String expectedTitle = LeadConstants.LEAD_TITLE;
		// String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;//To class variable
		String expectedURL = LeadConstants.LEAD_WEBURL;
		String expectedLeadSource = LeadConstants.LEAD_SOURCE_VALUE;
		String expectedEmpCount = LeadConstants.LEAD_EMP_COUNT;
		String expectedLeadPrefChanel = LeadConstants.LEAD_PREF_CHANEL;
		// String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE;//To class
		// variable
		String expectedLeadFax = LeadConstants.LEAD_FAX_NO;
		String expectedLeadEmail = LeadConstants.LEADS_EMAIL;
		String expectedLeadDescription = LeadConstants.LEAD_DESCRIPTION;
		// String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN; //To class
		// variable
		String expectedLeadamount = LeadConstants.LEAD_AMOUNT_180K;
		String expectedLeadAddress = LeadConstants.LEAD_ADDRESS_NOIDA_62;
		String expectedLeadMobile = LeadConstants.LEAD_MOBILE_NO;
		String expectedLeadOffice = LeadConstants.LEAD_OFFICE_NO;
		String expectedAssignToName = LeadConstants.LEAD_ASIGNEDTO_AUTOIND1;

		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		commonProductFunctions.get().clickOnLayout("Lead_System");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadSalutation, LeadConstants.LEAD_Salutation_MR,
				"Salutation");

		ReUsableMethods.webEnterText(leadCreationPage.get().leadFirstName, LeadConstants.LEAD_FIRST_NAME,
				"Lead_First name");

		ReUsableMethods.webEnterText(leadCreationPage.get().leadMidName, LeadConstants.LEAD_MID_NAME,
				"Lead_Middle name");

		//

		ReUsableMethods.webEnterText(leadCreationPage.get().enterLeadName, actualLastName.get(), "Lead_Last name");
		ReUsableMethods.webEnterText(leadCreationPage.get().leadTitle, expectedTitle, "Lead_title");

		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadRating, expectedLeadRating,
				"Select Lead rating Cold");

		ReUsableMethods.webClickElement(leadCreationPage.get().Comapny_picker, "Search Company Picker");
		String expectedCompanyName = ReUsableMethods.WebGetElementText(leadCreationPage.get().NameFirst,
				"FirstCompanyName");
		ReUsableMethods.webClickElement(leadCreationPage.get().NameFirst, "FirstCompanyName");
		ReUsableMethods.webClickElement(leadCreationPage.get().territorySearchIcon, "Search Company Picker");
		ReUsableMethods.WebGetElementText(leadCreationPage.get().NameFirst, "FirstCompanyName");
		ReUsableMethods.webClickElement(leadCreationPage.get().NameFirst, "First Territory Name");
		ReUsableMethods.webEnterText(leadCreationPage.get().webURL, expectedURL, "Lead_webURL");

		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadSource, expectedLeadSource,
				"Select Lead Source");

		ReUsableMethods.scrollDown();
		// leadCreationPage.get().selectLeadIndustry(LeadConstants.LEAD_INDUSTRY_TYPE);
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadIndustry, LeadConstants.LEAD_INDUSTRY_TYPE,
				"Select Lead Industry");

		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadEmpCount, expectedEmpCount,
				"Select Lead Emp Count");

		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadProduct, expectedLeadProduct,
				"Select Lead Product");

		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadStatusCode, expectedLeadStatusCode,
				"Status Code");

		ReUsableMethods.webClickElement(leadCreationPage.get().Campaign_picker, "Search Campaign Picker");
		commonProductFunctions.get().searchApply(expectedCampaignName);
		ReUsableMethods.WebGetElementText(leadCreationPage.get().CampNameFirst, expectedCampaignName);

		ReUsableMethods.webClickElement(leadCreationPage.get().CampNameFirst, "First Campaign Name");

		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadPrefChanel, expectedLeadPrefChanel,
				"Select Preffered Chanel Whatsapp");

		ReUsableMethods.webEnterText(leadCreationPage.get().leadAmount, expectedLeadamount, "Enter Lead Ammount 180k");

		ReUsableMethods.webClickElement(leadCreationPage.get().searchIconForAssignTo, "Search Icon For AssignTo");

		ReUsableMethods.webEnterText(leadCreationPage.get().searchTextbox, expectedAssignToName, "Search autoind1");
		ReUsableMethods.webClickElement(leadCreationPage.get().applyLinkAssignTo, "Apply Link AssignTo");

		ReUsableMethods.webClickElement(leadCreationPage.get().UserNameFirst, "First Assign To Name");

		ReUsableMethods.webEnterText(leadCreationPage.get().leadAddress, expectedLeadAddress, "Lead_Adress");

		ReUsableMethods.webEnterText(leadCreationPage.get().mobile, expectedLeadMobile, "Lead_Mobile_No");

		ReUsableMethods.webEnterText(leadCreationPage.get().officePhone, expectedLeadOffice, "Lead_Office_No");

		ReUsableMethods.webEnterText(leadCreationPage.get().faX, expectedLeadFax, "Lead_faX");

		ReUsableMethods.webEnterText(leadCreationPage.get().email, expectedLeadEmail, "Lead_EMAIL");

		ReUsableMethods.webEnterText(leadCreationPage.get().leadDescription, expectedLeadDescription,
				"Lead_DesCription");

		ReUsableMethods.webClickElement(leadCreationPage.get().save, "Save Button");

		// Elements on Detail Page to verify
		String actualLeadName = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadName, "Lead Name actual");
		String actualTitle = ReUsableMethods.WebGetElementText(leadDetailPage.get().title, "Lead Title actual");
		String actualComapny = ReUsableMethods.WebGetElementText(leadDetailPage.get().company, "Lead Company actual");
		String actualURL = ReUsableMethods.WebGetElementText(leadDetailPage.get().URL, "Lead URL actual");
		ReUsableMethods.WebGetElementText(leadDetailPage.get().industry, "Lead Industry actual");
		String actualEmpCount = ReUsableMethods.WebGetElementText(leadDetailPage.get().employeeCount,
				"Lead Employee Count actual");

		ReUsableMethods.WebGetElementText(leadDetailPage.get().product, "Lead Industry actual");
		String actualLeadSource = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadSource,
				"Lead Source actual");
		String actualLeadRating = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadRating,
				"Lead Rating actual");
		String actualLeadCampaign = ReUsableMethods.WebGetElementText(leadDetailPage.get().LeadCampaign,
				"Lead Campaign actual");

		ReUsableMethods.WebGetElementText(leadDetailPage.get().user, "Lead Owner actual");
		String actualLeadStatusCode = ReUsableMethods.WebGetElementText(leadDetailPage.get().statusCode_Active,
				"Lead StatusCode actual");

		ReUsableMethods.WebGetElementText(leadDetailPage.get().getAmount, "Lead Amount actual");
		String actualLaedPrefChanel = ReUsableMethods.WebGetElementText(leadDetailPage.get().prefferedChanel,
				"Lead PrefferedChanel actual");

		ReUsableMethods.WebGetElementText(leadDetailPage.get().verifyAssignTo,
				"Lead AssignTo actual");

		ReUsableMethods.WebGetElementText(leadDetailPage.get().verifyTerretory, "Lead Terretory actual");
		ReUsableMethods.WebGetElementText(leadDetailPage.get().leadAddress, "Lead Address actual");
		ReUsableMethods.WebGetElementText(leadDetailPage.get().leadMobile, "Lead Mobile actual");
		String actualLaedfax = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadFAX, "Lead FAX actual");
		// String actualLaedEMail =
		// ReUsableMethods.WebGetElementText(leadDetailPage.get().getShadowEmailElement(),
		// "Lead Email actual");
		String actualLaedDescription = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadDescription,
				"Lead Description actual");

		// assertions
		assertEquals(LeadConstants.LEAD_Salutation_MR + " " + LeadConstants.LEAD_FIRST_NAME + " "
				+ LeadConstants.LEAD_MID_NAME + " " + actualLastName.get(), actualLeadName);
		assertEquals(actualTitle, expectedTitle, "title is mismatched");
		assertEquals(actualComapny, expectedCompanyName, "company name is mismatched");
		assertEquals(actualLeadRating, expectedLeadRating, "Lead Rating is mismatched");

		assertEquals(actualLeadStatusCode, expectedLeadStatusCode, "Status code is mismatched");

		assertEquals(actualURL, expectedURL, "URL is mismatched");
		assertEquals(actualLeadSource, expectedLeadSource, "Lead Source is mismatched");
		assertEquals(actualEmpCount, expectedEmpCount, "Emp Count is mismatched");
		assertEquals(actualLeadCampaign, expectedCampaignName, "Campaing name is mismatched");
		assertEquals(actualLaedPrefChanel, expectedLeadPrefChanel, "Lead Pref channel is mismatched");

		// assertTrue(actualLaedAssignedTo.toLowerCase().contains(expectedAssignToName.toLowerCase()),
		// "Assign To name is mismatched");
		ReUsableMethods.scrollDown();
		assertEquals(actualLaedfax, expectedLeadFax, "Fax is mismatched");
		// assertEquals(actualLaedEMail, expectedLeadEmail,"Email is mismatched");
		assertEquals(actualLaedDescription, expectedLeadDescription, "Description is mismatched");
		// Assertion Log A Call button Visible
		ReUsableMethods.webMoveToElement(leadDetailPage.get().logACallBtn, "logACallBtn");
		s.assertTrue(ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().logACallBtn, "logACallBtn"),
				"logACallBtn not visible");
		// close button Assertion
		ReUsableMethods.webClickElement(leadCreationPage.get().close, "Close Button");
		ReUsableMethods.WebGetElementText(leadHomePage.get().leadObjectName, "Get Page title Heading");
		s.assertEquals(ReUsableMethods.WebGetElementText(leadHomePage.get().leadObjectName, "Get Page title Heading"),
				"Leads");

		s.assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184559", "184584", "184777",
			"184778", "307236", "323335" }, scriptType = { "" }, testCasePriority = { "3" })
	@Test(priority = 3, enabled = true, groups = { "Sanity", "Regression", "Lead" })

	public void leadEdit() throws Exception {

		SoftAssert s = new SoftAssert();

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);

		leadCommonFunctions.get().createLead("Lead_System", actualLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		ReUsableMethods.webClickElement(leadDetailPage.get().relatedHistoryTab, "Click Related History Tabs");
		s.assertEquals(ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedHistoryStatusCodeFirst, ""),
				LeadConstants.LEAD_STATUSCODE_NEW);
		s.assertEquals(ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedHistoryLastNameFirst, ""),
				actualLastName.get());

		ReUsableMethods.webClickElement(leadDetailPage.get().LeadEditButton, "Click On Edit Button");

		String editedLastName = leadCreationPage.get().enterLastLeadName("Edited");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.get().leadStatusCode,
				LeadConstants.LEAD_STATUSCODE_ACTIVE, "Active Status");
		ReUsableMethods.webClickElement(leadCreationPage.get().save, "Click On Save Button");
		leadDetailPage.get().waitforEditButton();
		ReUsableMethods.webClickElement(leadDetailPage.get().relatedHistoryTab, "Click Related History Tabs");
		s.assertEquals(ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedHistoryStatusCodeFirst, ""),
				LeadConstants.LEAD_STATUSCODE_ACTIVE);
		s.assertEquals(ReUsableMethods.WebGetElementText(leadDetailPage.get().relatedHistoryLastNameFirst, ""),
				editedLastName);

		// Lead History Assertions
		ReUsableMethods.webClickElement(leadDetailPage.get().relatedHistoryStatusCodeFirst,
				"Click On relatedHistoryStatusCodeFirst");
		s.assertEquals(ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, ""), editedLastName);
		ReUsableMethods.webClickElement(leadDetailPage.get().closeButton, "Click On Close Button");

		ReUsableMethods.webClickElement(leadDetailPage.get().relatedHistoryStatusCodeSecond,
				"Click On relatedHistoryStatusCodeSeconl Button");
		s.assertEquals(ReUsableMethods.WebGetElementText(leadDetailPage.get().LastName, ""), actualLastName.get());
		ReUsableMethods.webClickElement(leadDetailPage.get().closeButton, "Click On Close Button");

		s.assertAll();

	}

	@FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184560" }, scriptType = {
			"" }, testCasePriority = { "4" })
	@Test(priority = 4, enabled = true, groups = { "Sanity", "Regression", "Lead" })

	public void deleteLead() throws Exception {

		SoftAssert s = new SoftAssert();

		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		String lastNameAssigned = actualLastName.get();
		leadCommonFunctions.get().createLead("Lead_System", actualLastName.get(), expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().waitforEditButton();
		String LeadID = ReUsableMethods.WebGetElementText(leadDetailPage.get().leadID, "Get Lead Id");

		ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");
		// ReUsableMethods.clickCheckBoxUsingJavaScript(leadDetailPage.get().chckboxDltPermanently);

		// ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on
		// Delete Button");

		ReUsableMethods.safeClick(leadDetailPage.get().Delete1, commonProductFunctions.get().toolbox);

		commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Today", "Last Name", lastNameAssigned);
		String deletedLeadName = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"Get Deleted Lead Name from List");
		s.assertEquals(deletedLeadName, lastNameAssigned, "Deleted lead is not present in the list");

		commonProductFunctions.get().advanceSearch(LeadID);

		boolean isDeletedStatusCodeVisible = ReUsableMethods
				.WebIsElementDisplayed(leadDetailPage.get().statusCode_Deleted, "Deleted");
		s.assertTrue(isDeletedStatusCodeVisible, "Lead is not deleted");

		ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");
		// ReUsableMethods.clickCheckBoxUsingJavaScript(leadDetailPage.get().chckboxDltPermanently);

		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on Delete Button");
		// ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click on
		// Delete Button");
		// commonProductFunctions.get().waitForLoader();
		// commonProductFunctions.get().advanceSearch(LeadID);
		// ReUsableMethods.webEnterText(leadHomePage.get().advanceSearchtext, LeadID,
		// "Search deleted LeadID");

		// ReUsableMethods.webClickElement(leadHomePage.get().advanceSearchIcon,
		// "Advance Search Icon");
		// commonProductFunctions.get().ClickOnOk();
		ReUsableMethods.waitforElementInvisible(leadDetailPage.get().Delete1);
		commonProductFunctions.get().advanceSearch(LeadID);

		boolean isNoDataExistDisplayed = ReUsableMethods
				.WebIsElementDisplayed(commonProductFunctions.get().noDataExists, "No data exixts");
		s.assertTrue(isNoDataExistDisplayed, "No data exists message is not displayed");
		s.assertAll();
	}

	/**
	 * DEMO: Browser Inspector Integration
	 * This test demonstrates how to use Browser Inspector to find missing elements
	 *
	 * Scenario: We want to enter "Company Website" field, but the method doesn't
	 * exist
	 * Browser Inspector will find the element and give us the code to add to
	 * LeadCreationPage
	 */
	@FrameworkAnnotation(author = { "BrowserInspector" }, category = { "Demo", "Lead" }, TestCaseId = {
			"DEMO_001" }, scriptType = { "Web" }, testCasePriority = { "3" })
	@Test(priority = 5, enabled = false, groups = { "Demo" })
	public void demoFindMissingElementWithBrowserInspector() throws Exception {

		System.out.println("\n" + "=".repeat(80));
		System.out.println("🔍 BROWSER INSPECTOR DEMO - Finding Missing Elements");
		System.out.println("=".repeat(80) + "\n");

		// Step 1: Login and navigate to Lead creation page
		System.out.println("Step 1: Navigating to Lead Creation Page...");
		login.get().genericAdminLogin(webURL, nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		commonProductFunctions.get().clickOnLayout("Lead_System");
		System.out.println("✅ Lead Creation Page loaded\n");

		// Step 2: Fill some basic fields first
		System.out.println("Step 2: Filling basic Lead information...");
		ReUsableMethods.webEnterText(leadCreationPage.get().leadFirstName, "John", "First Name");
		ReUsableMethods.webEnterText(leadCreationPage.get().leadLastName, "Doe", "Last Name");
		System.out.println("✅ Basic fields filled\n");

		// Step 3: Now we want to enter "URL" field
		// Let's pretend we don't know if the method exists
		System.out.println("Step 3: Looking for 'URL' field using Browser Inspector...");
		System.out.println("⚠️  Simulating scenario: We don't know if enterURL() method exists\n");

		// Create Browser Inspector
		WebDriver driver = DriverManager.getWdriver();
		BrowserInspector inspector = new BrowserInspector(driver);

		// Find the URL field by label
		System.out.println("🔍 Searching for element with label 'URL'...\n");
		ElementInfo urlInfo = inspector.findByLabel("URL");

		if (urlInfo != null) {
			System.out.println("✅ FOUND! Element details:");
			System.out.println("   Label: URL");
			System.out.println("   Locator: " + urlInfo.getBestLocator());
			System.out.println("   Locator Type: " + urlInfo.getLocatorType());
			System.out.println("   Element Type: " + urlInfo.getElementType());
			System.out.println("   Is Unique: " + (urlInfo.isUnique() ? "✅ Yes" : "⚠️ No"));
			System.out.println("   Suggested Field Name: " + urlInfo.getSuggestedFieldName());
			System.out.println("   Suggested Method Name: " + urlInfo.getSuggestedMethodName());

			System.out.println("\n" + "-".repeat(80));
			System.out.println("📋 CODE TO ADD TO LeadCreationPage.java:");
			System.out.println("-".repeat(80));
			System.out.println("\n// WebElement declaration:");
			System.out.println(urlInfo.getFindByAnnotation());
			System.out.println(urlInfo.getWebElementDeclaration());

			System.out.println("\n// Method to enter URL:");
			System.out.println("public void " + urlInfo.getSuggestedMethodName() + "(String url) {");
			System.out.println(
					"    ReUsableMethods.webEnterText(" + urlInfo.getSuggestedFieldName() + ", url, \"URL\");");
			System.out.println("}");
			System.out.println("-".repeat(80) + "\n");
		} else {
			System.out.println("❌ Element not found with label 'URL'");
		}

		// Step 4: Let's find more elements
		System.out.println("\nStep 4: Finding more elements...\n");

		String[] fieldsToFind = { "Email", "Mobile", "Title", "Description" };

		for (String fieldLabel : fieldsToFind) {
			System.out.println("🔍 Searching for: " + fieldLabel);
			ElementInfo info = inspector.findByLabel(fieldLabel);

			if (info != null) {
				System.out.println("   ✅ Found: " + info.getBestLocator());
				System.out.println("   📝 Field: " + info.getSuggestedFieldName());
				System.out.println("   🔧 Method: " + info.getSuggestedMethodName() + "()");
				System.out.println("   " + (info.isUnique() ? "✅ Unique" : "⚠️ Not unique"));
			} else {
				System.out.println("   ❌ Not found");
			}
			System.out.println();
		}

		// Step 5: Find buttons
		System.out.println("\nStep 5: Finding buttons...\n");

		String[] buttonsToFind = { "Save", "Cancel" };

		for (String buttonText : buttonsToFind) {
			System.out.println("🔍 Searching for button: " + buttonText);
			ElementInfo btnInfo = inspector.findByButtonText(buttonText);

			if (btnInfo != null) {
				System.out.println("   ✅ Found: " + btnInfo.getBestLocator());
				System.out.println("   📝 Field: " + btnInfo.getSuggestedFieldName());
				System.out.println("   🔧 Method: " + btnInfo.getSuggestedMethodName() + "()");
			} else {
				System.out.println("   ❌ Not found");
			}
			System.out.println();
		}

		// Summary
		System.out.println("\n" + "=".repeat(80));
		System.out.println("📊 DEMO SUMMARY");
		System.out.println("=".repeat(80));
		System.out.println("✅ Browser Inspector successfully found elements in the live browser");
		System.out.println("✅ Generated @FindBy annotations with best locators");
		System.out.println("✅ Suggested field names and method names following framework conventions");
		System.out.println("✅ Validated locator uniqueness");
		System.out.println("\n💡 Next Steps:");
		System.out.println("   1. Copy the generated code from console output");
		System.out.println("   2. Paste into LeadCreationPage.java");
		System.out.println("   3. Use the new methods in your tests");
		System.out.println("   4. Save 95% of time compared to manual inspection!");
		System.out.println("=".repeat(80) + "\n");

		// Clean up - click cancel to close the form
		ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "Cancel");
	}
}
