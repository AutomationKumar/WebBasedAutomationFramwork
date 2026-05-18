package com.businessnext.leads.testcases;

import com.businessnext.login.page.WebLoginPage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

public final class LeadCommonFunctions {

	webReusableBusinessFunctions login;
	WebLoginPage webLoginPage;
	NavigationPanel navigationPanel;
	CommonProductFunctions commonProductFunctions;

	LeadHomePage leadHomePage;
	LeadCreationPage leadCreationPage;
	LeadDetailPage leadDetailPage;
	LeadConstants leadConstants;

	public LeadCommonFunctions() {
		navigationPanel = new NavigationPanel(DriverManager.getWdriver());
		commonProductFunctions = new CommonProductFunctions(DriverManager.getWdriver());
		leadHomePage = new LeadHomePage(DriverManager.getWdriver());
		leadCreationPage = new LeadCreationPage(DriverManager.getWdriver());
		commonProductFunctions = new CommonProductFunctions(DriverManager.getWdriver());

	}

	/*
	 *
	 *
	 */

	public void createLead(String Layout, String LastName, String RatingId, String Product, String StatusCode,
			String saveType) throws InterruptedException {
		leadHomePage.clickOnLayout(Layout);
		ReUsableMethods.webEnterTextString(leadCreationPage.enterLeadName, LastName, "Lead_Last name");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.leadRating, RatingId, "Select Lead rating Cold");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.leadProduct, Product, "Select Lead Product");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.leadStatusCode, StatusCode, "Status Code");
		if (saveType.equalsIgnoreCase("saveandnew")) {
			ReUsableMethods.webClickElement(leadCreationPage.saveandnew, "Click On Save Button");
		} else {
			ReUsableMethods.webClickElement(leadCreationPage.save, "Click On Save Button");

		}
		commonProductFunctions.waitForLoader();
	}

	public void createLeadForWidget(String Layout, String LastName, String RatingId, String Product, String StatusCode,
			String mobile, String email) throws InterruptedException {
		leadHomePage.clickOnLayout(Layout);
		ReUsableMethods.webEnterTextString(leadCreationPage.enterLeadName, LastName, "Lead_Last name");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.leadRating, RatingId, "Select Lead rating Cold");
		ReUsableMethods.scrollDownToElement(leadCreationPage.leadProduct);
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.leadProduct, Product, "Select Lead Product");
		ReUsableMethods.webSelectByVisibleText(leadCreationPage.leadStatusCode, StatusCode, "Status Code");
		ReUsableMethods.webEnterText(leadCreationPage.mobile, mobile, "Mobile Number");
		ReUsableMethods.webEnterText(leadCreationPage.email, email, "Email");

		commonProductFunctions.waitForLoader();
	}

}
