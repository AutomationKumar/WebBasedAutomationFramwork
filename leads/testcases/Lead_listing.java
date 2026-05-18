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
import com.common.pages.ObjectConstants;
import com.common.pages.RelatedActivityPage;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;
@Listeners(ListenerClass.class)


public class Lead_listing extends BaseClass{

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
		expectedLastName.set(ReUsableMethods.generateRandomText(8) + ReUsableMethods.getCurrentdateTime());
		s.set(new SoftAssert());


    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184609" }, scriptType = { "" }, testCasePriority = { "1" })
	@Test(priority = 1, enabled = true, groups = { "Regression", "Lead" })

	public void leadListingMyActiveLeads() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;
		ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().clickOnCloseButton();


        commonProductFunctions.get().searchByAttribute("My Active Leads", "All", "Last Name", lastNameAssigned);
        String actualMyLeadAll = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualMyLeadAll, lastNameAssigned, "Lead is not listed in My Active Leads view");
        s.get().assertAll();

    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184610" }, scriptType = { "" }, testCasePriority = { "2" })
    @Test(priority = 2, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingMyActiveLeads_Today() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("My Active Leads", "Today", "Last Name", lastNameAssigned);
        String actualMyLeadToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualMyLeadToday, lastNameAssigned, "Lead is not listed in My Active Leads view for Today filter");
        s.get().assertAll();

    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184611" }, scriptType = { "" }, testCasePriority = { "3" })
    @Test(priority = 3, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingMyActiveLeads_RecentlyCreated() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);    
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("My Active Leads", "Recently Created", "Last Name", lastNameAssigned);
        String actualMyLeadRecentlyCreated = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualMyLeadRecentlyCreated, lastNameAssigned, "Lead is not listed in My Active Leads view for Recently Created filter");
        s.get().assertAll();

    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184612" }, scriptType = { "" }, testCasePriority = { "4" })
    @Test(priority = 4, enabled = true, groups = { "Regression", "Lead" })  
    public void leadListingMyActiveLeads_RecentlyViewed() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);    
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
				expectedLeadProduct, expectedLeadStatusCode, "");
		leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("My Active Leads", "Recently Viewed", "Last Name", lastNameAssigned);
        String actualMyLeadRecentlyViewed = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualMyLeadRecentlyViewed, lastNameAssigned, "Lead is not listed in My Active Leads view for Recently Viewed filter");
        s.get().assertAll();

    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184613","184614" }, scriptType = { "" }, testCasePriority = { "5" })
    @Test(priority = 5, enabled = true, groups = { "Regression", "Lead" })  
    public void leadListingMyActiveLeads_RecentlyModified() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);    
		navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
		leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("My Active Leads", "Recently Modified", "Last Name", lastNameAssigned);
        String actualMyLeadRecentlyModified = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualMyLeadRecentlyModified, lastNameAssigned, "Lead is not listed in My Active Leads view for Recently Modified filter");
        s.get().assertAll();

    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184615" }, scriptType = { "" }, testCasePriority = { "6" })
    @Test(priority = 6, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllNewLeads_All() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);    
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All New Leads", "All", "Last Name", lastNameAssigned);
        String actualAllNewLeadsAll = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualAllNewLeadsAll, lastNameAssigned, "Lead is not listed in All New Leads view for All filter");
        s.get().assertAll();

    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184616" }, scriptType = { "" }, testCasePriority = { "7" })
    @Test(priority = 7, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllNewLeads_Today() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);    
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All New Leads", "Today", "Last Name", lastNameAssigned);
        String actualAllNewLeadsToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualAllNewLeadsToday, lastNameAssigned, "Lead is not listed in All New Leads view for Today filter");
        s.get().assertAll();

    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184617" }, scriptType = { "" }, testCasePriority = { "8" })
    @Test(priority = 8, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllNewLeads_RecentlyCreated() throws Exception { 
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);    
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;   
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Created", "Last Name", lastNameAssigned);
        String actualAllNewLeadsRecentlyCreated = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualAllNewLeadsRecentlyCreated, lastNameAssigned, "Lead is not listed in All New Leads view for Recently Created filter");
        s.get().assertAll();

    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184618" }, scriptType = { "" }, testCasePriority = { "9" })
    @Test(priority = 9, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllNewLeads_RecentlyViewed() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Viewed", "Last Name", lastNameAssigned);
        String actualAllNewLeadsRecentlyViewed = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualAllNewLeadsRecentlyViewed, lastNameAssigned, "Lead is not listed in All New Leads view for Recently Viewed filter");
        s.get().assertAll();

    }   

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184619" }, scriptType = { "" }, testCasePriority = { "10" })
    @Test(priority = 10, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllNewLeads_RecentlyModified() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All New Leads", "Recently Modified", "Last Name", lastNameAssigned);
        String actualAllNewLeadsRecentlyModified = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");    
        s.get().assertEquals(actualAllNewLeadsRecentlyModified, lastNameAssigned, "Lead is not listed in All New Leads view for Recently Modified filter");
        s.get().assertAll();

    }       

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184620" }, scriptType = { "" }, testCasePriority = { "11" })
    @Test(priority = 11, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllNewLeads_UnRead() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "saveandnew");
       ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "cancel button");
        commonProductFunctions.get().searchByAttribute("All New Leads", "Unread", "Last Name", lastNameAssigned);
        String actualAllNewLeadsUnread = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualAllNewLeadsUnread, lastNameAssigned, "Lead is not listed in All New Leads view for Unread filter");
        s.get().assertAll();
    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184621" }, scriptType = { "" }, testCasePriority = { "12" })
    @Test(priority = 12, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllNewLeads_Hot() throws Exception {

        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All New Leads", "Hot", "Last Name", lastNameAssigned);
        String actualAllNewLeadsHot = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
				"searchedLeadName");
        s.get().assertEquals(actualAllNewLeadsHot, lastNameAssigned, "Lead is not listed in All New Leads view for Hot filter");
        s.get().assertAll();    
    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184622" }, scriptType = { "" }, testCasePriority = { "13" })
    @Test(priority = 13, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDisqualifiedLeads_All() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DISQUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
		ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
					"Disqualify Discription");
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");


		commonProductFunctions.get().waitForLoader();

		 
		leadDetailPage.get().clickOnCloseButton();

        commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "All", "Last Name", lastNameAssigned);
        String actualAllDisqualifiedLeadsAll = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDisqualifiedLeadsAll, lastNameAssigned, "Lead is not listed in All Disqualified Leads view for All filter");
        s.get().assertAll();
    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184623" }, scriptType = { "" }, testCasePriority = { "14" })
    @Test(priority = 14, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDisqualifiedLeads_Today() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DISQUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
		ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
                    "Disqualify Discription");
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");


		commonProductFunctions.get().waitForLoader();

		 
		leadDetailPage.get().clickOnCloseButton();

        commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "Today", "Last Name", lastNameAssigned);
        String actualAllDisqualifiedLeadsToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDisqualifiedLeadsToday, lastNameAssigned, "Lead is not listed in All Disqualified Leads view for Today filter");
        s.get().assertAll();    
    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184624" }, scriptType = { "" }, testCasePriority = { "15" })
    @Test(priority = 15, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDisqualifiedLeads_RecentlyCreated() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DISQUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
		ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
                    "Disqualify Discription");  
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");


		commonProductFunctions.get().waitForLoader();

		 
		leadDetailPage.get().clickOnCloseButton();

        commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "Recently Created", "Last Name", lastNameAssigned);
        String actualAllDisqualifiedLeadsRecentlyCreated = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDisqualifiedLeadsRecentlyCreated, lastNameAssigned, "Lead is not listed in All Disqualified Leads view for Recently Created filter");
        s.get().assertAll();    
    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184625" }, scriptType = { "" }, testCasePriority = { "16" })
    @Test(priority = 16, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDisqualifiedLeads_RecentlyViewed() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DISQUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
		ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
                    "Disqualify Discription");
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");


		commonProductFunctions.get().waitForLoader();

		 
		leadDetailPage.get().clickOnCloseButton();

        commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "Recently Viewed", "Last Name", lastNameAssigned);
        String actualAllDisqualifiedLeadsRecentlyViewed = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDisqualifiedLeadsRecentlyViewed, lastNameAssigned, "Lead is not listed in All Disqualified Leads view for Recently Viewed filter");
        s.get().assertAll();    
    }   

     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184626" }, scriptType = { "" }, testCasePriority = { "17" })
    @Test(priority = 17, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDisqualifiedLeads_RecentlyModified() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;   
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DISQUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");       
        ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
                    "Disqualify Discription");
		ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");


		commonProductFunctions.get().waitForLoader();

		 
		leadDetailPage.get().clickOnCloseButton();

        commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "Recently Modified", "Last Name", lastNameAssigned);
        String actualAllDisqualifiedLeadsRecentlyModified = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDisqualifiedLeadsRecentlyModified, lastNameAssigned, "Lead is not listed in All Disqualified Leads view for Recently Modified filter");
        s.get().assertAll();    
    }       

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184627" }, scriptType = { "" }, testCasePriority = { "18" })
    @Test(priority = 18, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDisqualifiedLeads_Unread() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DISQUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "saveandnew");  
            ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "cancel button");     
        // ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		// ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
        //             "Disqualify Discription");
		// ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");


		commonProductFunctions.get().waitForLoader();

		 
		leadDetailPage.get().clickOnCloseButton();

        commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "Unread", "Last Name", lastNameAssigned);
        String actualAllDisqualifiedLeadsUnread = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDisqualifiedLeadsUnread, lastNameAssigned, "Lead is not listed in All Disqualified Leads view for Unread filter");
        s.get().assertAll();    
    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184628" }, scriptType = { "" }, testCasePriority = { "19" })
    @Test(priority = 19, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDisqualifiedLeads_Hot() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DISQUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        ReUsableMethods.webSelectByVisibleText(leadDetailPage.get().drpDwnReasonDisQualify, "Pricing", "Pricing");
		ReUsableMethods.webEnterText(leadDetailPage.get().disqualifyDiscription, ReUsableMethods.generateRandomText(10),
                    "Disqualify Discription");
        ReUsableMethods.webClickElement(leadDetailPage.get().disqualifyLeadBtn, "Disqualify Lead Button");


		commonProductFunctions.get().waitForLoader();

		 
		leadDetailPage.get().clickOnCloseButton();

        commonProductFunctions.get().searchByAttribute("All Disqualified Leads", "Hot", "Last Name", lastNameAssigned);
        String actualAllDisqualifiedLeadsHot = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDisqualifiedLeadsHot, lastNameAssigned, "Lead is not listed in All Disqualified Leads view for Hot filter");
        s.get().assertAll();    
    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184629" }, scriptType = { "" }, testCasePriority = { "20" })
    @Test(priority = 20, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllQualifiedLeads_All() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_QUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Qualified Leads", "All", "Last Name", lastNameAssigned);
        String actualAllQualifiedLeadsAll = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllQualifiedLeadsAll, lastNameAssigned, "Lead is not listed in All Qualified Leads view for All filter");
        s.get().assertAll();    
    }


    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184630" ,"184668","184673"}, scriptType = { "" }, testCasePriority = { "21" })
    @Test(priority = 21, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllQualifiedLeads_Today() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_QUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Qualified Leads", "Today", "Last Name", lastNameAssigned);
        String actualAllQualifiedLeadsToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllQualifiedLeadsToday, lastNameAssigned, "Lead is not listed in All Qualified Leads view for Today filter");
        s.get().assertAll();    
    }

     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184631" }, scriptType = { "" }, testCasePriority = { "22" })
    @Test(priority = 22, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllQualifiedLeads_RecentlyCreated() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_QUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Qualified Leads", "Recently Created", "Last Name", lastNameAssigned);
        String actualAllQualifiedLeadsRecentlyCreated = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");        
        s.get().assertEquals(actualAllQualifiedLeadsRecentlyCreated, lastNameAssigned, "Lead is not listed in All Qualified Leads view for Recently Created filter");
        s.get().assertAll();    
    }   

     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184632" }, scriptType = { "" }, testCasePriority = { "23" })
    @Test(priority = 23, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllQualifiedLeads_RecentlyViewed() throws Exception {
            login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_QUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Qualified Leads", "Recently Viewed", "Last Name", lastNameAssigned);
        String actualAllQualifiedLeadsRecentlyViewed = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllQualifiedLeadsRecentlyViewed, lastNameAssigned, "Lead is not listed in All Qualified Leads view for Recently Viewed filter");
        s.get().assertAll();    
    }   

     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184633" }, scriptType = { "" }, testCasePriority = { "24" })
    @Test(priority = 24, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllQualifiedLeads_RecentlyModified() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);  

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();                
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_QUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Qualified Leads", "Recently Modified", "Last Name", lastNameAssigned);
        String actualAllQualifiedLeadsRecentlyModified = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllQualifiedLeadsRecentlyModified, lastNameAssigned, "Lead is not listed in All Qualified Leads view for Recently Modified filter");
        s.get().assertAll();    
    }       

     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184634" }, scriptType = { "" }, testCasePriority = { "25" })          
    @Test(priority = 25, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllQualifiedLeads_Unread() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_QUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "saveandnew"); 
        ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "Click on Cancel Button");
                commonProductFunctions.get().searchByAttribute("All Qualified Leads", "Unread", "Last Name", lastNameAssigned);
        String actualAllQualifiedLeadsUnread = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllQualifiedLeadsUnread, lastNameAssigned, "Lead is not listed in All Qualified Leads view for Unread filter");
        s.get().assertAll();    
    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184635" }, scriptType = { "" }, testCasePriority = { "26" })
    @Test(priority = 26, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllQualifiedLeads_Hot() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_QUALIFIED;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Qualified Leads", "Hot", "Last Name", lastNameAssigned);
        String actualAllQualifiedLeadsHot = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllQualifiedLeadsHot, lastNameAssigned, "Lead is not listed in All Qualified Leads view for Hot filter");
        s.get().assertAll();    
    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184636" }, scriptType = { "" }, testCasePriority = { "27" })
    @Test(priority = 27, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDeletedLeads_All() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");

        ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");

		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click onDelete confirmation Button");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Deleted Leads", "All", "Last Name", lastNameAssigned);
        String actualAllDeletedLeadsAll = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
        s.get().assertEquals(actualAllDeletedLeadsAll, lastNameAssigned, "Lead is not listed in All Deleted Leads view for All filter");
        s.get().assertAll();    
    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184637","184667" }, scriptType = { "" }, testCasePriority = { "28" })
    @Test(priority = 28, enabled = true, groups = { "Regression", "Lead" })    
    public void leadListingAllDeletedLeads_Today() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;
        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
                
        ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");

		ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click onDelete confirmation Button");
        leadDetailPage.get().clickOnCloseButton();
        commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Today", "Last Name", lastNameAssigned);
        String actualAllDeletedLeadsToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName,
                "searchedLeadName");
                
        s.get().assertEquals(actualAllDeletedLeadsToday, lastNameAssigned, "Lead is not listed in All Deleted Leads view for Today filter");        
        s.get().assertAll();    
    }   
    
    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184638" }, scriptType = { "" }, testCasePriority = { "29" })          
    @Test(priority = 29, enabled = true, groups = { "Regression", "Lead" })    
    public void leadListingAllDeletedLeads_RecentlyCreated() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);

        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;

        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");                
                
        ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");
        
        ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click onDelete confirmation Button");        
        leadDetailPage.get().clickOnCloseButton();        
        commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Recently Created", "Last Name", lastNameAssigned);        
        String actualAllDeletedLeadsRecentlyCreated = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllDeletedLeadsRecentlyCreated, lastNameAssigned, "Lead is not listed in All Deleted Leads view for Recently Created filter");        
        s.get().assertAll();    
    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184639" }, scriptType = { "" }, testCasePriority = { "30" })
    @Test(priority = 30, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDeletedLeads_RecentlyViewed() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DELETED;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "saveandnew"); 
        ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "Click on Cancel Button");
                       
       // ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");        
      //  ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click onDelete confirmation Button");        
        leadDetailPage.get().clickOnCloseButton();        
        commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Recently Viewed", "Last Name", lastNameAssigned);        
        String actualAllDeletedLeadsRecentlyViewed = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllDeletedLeadsRecentlyViewed, lastNameAssigned, "Lead is not listed in All Deleted Leads view for Recently Viewed filter");        
        s.get().assertAll();    
    }   

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184640" }, scriptType = { "" }, testCasePriority = { "31" })
    @Test(priority = 31, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDeletedLeads_RecentlyModified() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");
        ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click onDelete confirmation Button");        
        leadDetailPage.get().clickOnCloseButton();        
        commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Recently Modified", "Last Name", lastNameAssigned);        
        String actualAllDeletedLeadsRecentlyModified = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllDeletedLeadsRecentlyModified, lastNameAssigned, "Lead is not listed in All Deleted Leads view for Recently Modified filter");        
        s.get().assertAll();    
    }

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184641" }, scriptType = { "" }, testCasePriority = { "32" })
        
    @Test(priority = 32, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDeletedLeads_Unread() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_DELETED;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "saveandnew"); 
        ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "Click on Cancel Button");
        
        // ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");        
        // ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click onDelete confirmation Button");        
        // leadDetailPage.get().clickOnCloseButton();        
        commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Unread", "Last Name", lastNameAssigned);        
        String actualAllDeletedLeadsUnread = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllDeletedLeadsUnread, lastNameAssigned, "Lead is not listed in All Deleted Leads view for Unread filter");        
        s.get().assertAll();
    }

    
        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184642" }, scriptType = { "" }, testCasePriority = { "33" })
    @Test(priority = 33, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllDeletedLeads_Hot() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe" + ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating,
                expectedLeadProduct, expectedLeadStatusCode, "");
        
        ReUsableMethods.webClickElement(leadDetailPage.get().Delete, "Click on Delete Button");
        ReUsableMethods.webClickElement(leadDetailPage.get().Delete1, "Click onDelete confirmation Button");        
        leadDetailPage.get().clickOnCloseButton();        
        commonProductFunctions.get().searchByAttribute("All Deleted Leads", "Hot", "Last Name", lastNameAssigned);        
        String actualAllDeletedLeadsHot = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");  
        s.get().assertEquals(actualAllDeletedLeadsHot, lastNameAssigned, "Lead is not listed in All Deleted Leads view for Hot filter");        
        s.get().assertAll();    
    }
    
    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184643" }, scriptType = { "" }, testCasePriority = { "34" })
    @Test(priority = 34, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingEscalatedLeads_All() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "EsclatedTC"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        

        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("Escalated Leads", "All", "Last Name", lastNameAssigned);        
        String actualEscalatedLeadsAll = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualEscalatedLeadsAll, lastNameAssigned, "Lead is not listed in Escalated Leads view for All filter");        
        s.get().assertAll();    
    }

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184644" }, scriptType = { "" }, testCasePriority = { "35" })
        
    @Test(priority = 35, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingEscalatedLeads_Today() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "EsclatedTC"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN; 
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        

        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("Escalated Leads", "Today", "Last Name", lastNameAssigned);        
        String actualEscalatedLeadsToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualEscalatedLeadsToday, lastNameAssigned, "Lead is not listed in Escalated Leads view for Today filter");        
        s.get().assertAll();    
    }   

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184645", "184646" }, scriptType = { "" }, testCasePriority = { "36" })
        
    @Test(priority = 36, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingEscalatedLeads_RecentlyCreated() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "EsclatedTC"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);        
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("Escalated Leads", "Recently Created", "Last Name", lastNameAssigned);        
        String actualEscalatedLeadsRecentlyCreated = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualEscalatedLeadsRecentlyCreated, lastNameAssigned, "Lead is not listed in Escalated Leads view for Recently Created filter");        
        s.get().assertAll();    
    }   

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184647" }, scriptType = { "" }, testCasePriority = { "37" })
        
    @Test(priority = 37, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingEscalatedLeads_RecentlyViewed() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "EsclatedTC"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("Escalated Leads", "Recently Viewed", "Last Name", lastNameAssigned);        
        String actualEscalatedLeadsRecentlyViewed = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualEscalatedLeadsRecentlyViewed, lastNameAssigned, "Lead is not listed in Escalated Leads view for Recently Viewed filter");  
                  
        s.get().assertAll();    
    }   

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184648" }, scriptType = { "" }, testCasePriority = { "38" })
        
    @Test(priority = 38, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingEscalatedLeads_RecentlyModified() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "EsclatedTC"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("Escalated Leads", "Recently Modified", "Last Name", lastNameAssigned);        
        String actualEscalatedLeadsRecentlyModified = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");  
              
        s.get().assertEquals(actualEscalatedLeadsRecentlyModified, lastNameAssigned, "Lead is not listed in Escalated Leads view for Recently Modified filter");        
        s.get().assertAll();    
    }   

        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184649" }, scriptType = { "" }, testCasePriority = { "39" })
        
    @Test(priority = 39, enabled = true, groups = { "Regression", "Lead" })    
    public void leadListingEscalatedLeads_Unread() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "EsclatedTC"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "saveandnew");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("Escalated Leads", "Unread", "Last Name", lastNameAssigned);        
        String actualEscalatedLeadsUnread = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualEscalatedLeadsUnread, lastNameAssigned, "Lead is not listed in Escalated Leads view for Unread filter");        
        s.get().assertAll();    
    }   
    
        @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184650" }, scriptType = { "" }, testCasePriority = { "40" })           
    @Test(priority = 40, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingEscalatedLeads_Hot() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "EsclatedTC"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_NEW;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("Escalated Leads", "Hot", "Last Name", lastNameAssigned);        
        String actualEscalatedLeadsHot = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualEscalatedLeadsHot, lastNameAssigned, "Lead is not listed in Escalated Leads view for Hot filter");        
        s.get().assertAll();    
    }  

    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184680" }, scriptType = { "" }, testCasePriority = { "41" })
    @Test(priority = 41, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllActiveLeads_All() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE; 
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("All Active Leads", "All", "Last Name", lastNameAssigned);        
        String actualAllActiveLeadsAll = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllActiveLeadsAll, lastNameAssigned, "Lead is not listed in All Active Leads view for All filter");        
        s.get().assertAll();    
    }
    
    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184681" }, scriptType = { "" }, testCasePriority = { "42" })
    @Test(priority = 42, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllActiveLeads_Today() throws Exception {
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);  
        String lastNameAssigned = "AssignedToMe"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");     
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("All Active Leads", "Today", "Last Name", lastNameAssigned);        
        String actualAllActiveLeadsToday = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllActiveLeadsToday, lastNameAssigned, "Lead is not listed in All Active Leads view for Today filter");        
        s.get().assertAll();    
    }

     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184682" }, scriptType = { "" }, testCasePriority = { "43" })
    @Test(priority = 43, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllActiveLeads_RecentlyCreated() throws Exception {      
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe"+ReUsableMethods.getCurrentTime();  
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
              
        commonProductFunctions.get().searchByAttribute("All Active Leads", "Recently Created", "Last Name", lastNameAssigned);        
        String actualAllActiveLeadsRecentlyCreated = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllActiveLeadsRecentlyCreated, lastNameAssigned, "Lead is not listed in All Active Leads view for Recently Created filter");        
        s.get().assertAll();    
    }   
    
     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184683" }, scriptType = { "" }, testCasePriority = { "44" })
    @Test(priority = 44, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllActiveLeads_RecentlyViewed() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("All Active Leads", "Recently Viewed", "Last Name", lastNameAssigned);        
        String actualAllActiveLeadsRecentlyViewed = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllActiveLeadsRecentlyViewed, lastNameAssigned, "Lead is not listed in All Active Leads view for Recently Viewed filter");        
        s.get().assertAll();    
    }   
    
     @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184684" }, scriptType = { "" }, testCasePriority = { "45" })
    @Test(priority = 45, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllActiveLeads_RecentlyModified() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");   
        
        leadDetailPage.get().clickOnEditButton();
        String expectedLeadDetailLastName = "Modified"+ReUsableMethods.getCurrentTime();
        leadCreationPage.get().enterLastName(expectedLeadDetailLastName);
        commonProductFunctions.get().clickOnSave();
        leadDetailPage.get().clickOnCloseButton();    

        commonProductFunctions.get().searchByAttribute("All Active Leads", "Recently Modified", "Last Name", expectedLeadDetailLastName);        
        String actualAllActiveLeadsRecentlyModified = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllActiveLeadsRecentlyModified, expectedLeadDetailLastName, "Lead is not listed in All Active Leads view for Recently Modified filter");        
        s.get().assertAll();    
    }   
    
    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184685" }, scriptType = { "" }, testCasePriority = { "46" })
    @Test(priority = 46, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllActiveLeads_Unread() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe"+ReUsableMethods.getCurrentTime();   
        String expectedLeadRating = LeadConstants.LEAD_RATING_WARM;     
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;     
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);    
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "saveandnew");        
        ReUsableMethods.webClickElement(leadCreationPage.get().cancel, "Close the lead detail page");    
        
        commonProductFunctions.get().searchByAttribute("All Active Leads", "Unread", "Last Name", lastNameAssigned);        
        String actualAllActiveLeadsUnread = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllActiveLeadsUnread, lastNameAssigned, "Lead is not listed in All Active Leads view for Unread filter");        
        s.get().assertAll();    
    }   
    
    @FrameworkAnnotation(author = { "Anupriya" }, category = { "Leads" }, TestCaseId = { "184686" }, scriptType = { "" }, testCasePriority = { "47" })
    @Test(priority = 47, enabled = true, groups = { "Regression", "Lead" })
    public void leadListingAllActiveLeads_Hot() throws Exception {  
        login.get().genericAdminLogin(webURL,nonAdminUser_AutoAll, webpassword);        
        navigationPanel.get().NavigateToObject(ObjectConstants.NAVIGATE_LEAD);        
        String lastNameAssigned = "AssignedToMe"+ReUsableMethods.getCurrentTime();        
        String expectedLeadRating = LeadConstants.LEAD_RATING_HOT;        
        String expectedLeadProduct = LeadConstants.PRODUCT_GOLD_LOAN;
        String expectedLeadStatusCode = LeadConstants.LEAD_STATUSCODE_ACTIVE;        
        ReUsableMethods.safeClick(leadHomePage.get().newIcon, leadHomePage.get().layoutSearchInput);
        leadCommonFunctions.get().createLead(LayoutConstants.LAYOUT_LEAD_SYSTEM, lastNameAssigned, expectedLeadRating, expectedLeadProduct, expectedLeadStatusCode, "");        
        leadDetailPage.get().clickOnCloseButton();    
        
        commonProductFunctions.get().searchByAttribute("All Active Leads", "Hot", "Last Name", lastNameAssigned);        
        String actualAllActiveLeadsHot = ReUsableMethods.WebGetElementText(leadHomePage.get().searchedLeadName, "searchedLeadName");        
        s.get().assertEquals(actualAllActiveLeadsHot, lastNameAssigned, "Lead is not listed in All Active Leads view for Hot filter");        
        s.get().assertAll();    
    }   

}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    





    
    

