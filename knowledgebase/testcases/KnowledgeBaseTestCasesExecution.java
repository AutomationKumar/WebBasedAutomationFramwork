package com.businessnext.knowledgebase.testcases;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.knowledgebase.pages.KnowledgeBaseCategoryCreationPage;
import com.businessnext.knowledgebase.pages.KnowledgeBaseCategoryHomePage;
import com.businessnext.knowledgebase.pages.KnowledgeBaseConstants;
import com.businessnext.knowledgebase.pages.KnowledgeBaseCreationPage;
import com.businessnext.knowledgebase.pages.KnowledgeBaseDetailPage;
import com.businessnext.knowledgebase.pages.KnowledgeBaseHomePage;
import com.businessnext.objects.account.pages.AccountConstant;
import com.businessnext.objects.account.pages.AccountCreationPage;
import com.businessnext.objects.account.pages.AccountDetailPage;
import com.businessnext.objects.account.pages.AccountHomePage;
import com.businessnext.objects.account.pages.AccountLayoutDesignerPage;
import com.businessnext.objects.cases.pages.CasesCreationPage;
import com.businessnext.objects.cases.pages.CasesDetailPage;
import com.businessnext.objects.cases.pages.CasesHomePage;
import com.businessnext.objects.lead.pages.LeadConstants;
import com.businessnext.objects.lead.pages.LeadCreationPage;
import com.businessnext.objects.lead.pages.LeadDetailPage;
import com.businessnext.objects.lead.pages.LeadHomePage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)
public class KnowledgeBaseTestCasesExecution extends BaseClass {
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<CasesHomePage> casesHomePage = new ThreadLocal<>();
	ThreadLocal<CasesCreationPage> casesCreationPage = new ThreadLocal<>();
	ThreadLocal<CasesDetailPage> casesDeatilPage = new ThreadLocal<>();
	ThreadLocal<AccountHomePage> accountHomePage = new ThreadLocal<>();
	ThreadLocal<AccountCreationPage> accountCreationPage = new ThreadLocal<>();
	ThreadLocal<AccountDetailPage> accountDetailPage = new ThreadLocal<>();
	ThreadLocal<AccountLayoutDesignerPage> accountLayoutDesignerPage = new ThreadLocal<>();
	ThreadLocal<LeadHomePage> leadHomePage = new ThreadLocal<>();
	ThreadLocal<LeadCreationPage> leadCreationPage = new ThreadLocal<>();
	ThreadLocal<LeadDetailPage> leadDetailPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryHomePage> knowledgeBaseCategoryHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryCreationPage> knowledgeBaseCategoryCreationPage = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<String> parentWindow = new ThreadLocal<>();
	ThreadLocal<String> categoryName = new ThreadLocal<>();

	@BeforeMethod(alwaysRun = true)
	public void initializePageObjects() {
		// Cache driver to avoid 18 ThreadLocal lookups - Performance optimization
		WebDriver driver = DriverManager.getWdriver();

		login.set(new webReusableBusinessFunctions());
		navigationPanel.set(new NavigationPanel(driver));
		accountHomePage.set(new AccountHomePage(driver));
		accountCreationPage.set(new AccountCreationPage(driver));
		accountDetailPage.set(new AccountDetailPage(driver));
		accountLayoutDesignerPage.set(new AccountLayoutDesignerPage(driver));
		knowledgeBaseHomePage.set(new KnowledgeBaseHomePage(driver));
		knowledgeBaseCreationPage.set(new KnowledgeBaseCreationPage(driver));
		knowledgeBaseDetailPage.set(new KnowledgeBaseDetailPage(driver));
		knowledgeBaseCategoryHomePage.set(new KnowledgeBaseCategoryHomePage(driver));
		knowledgeBaseCategoryCreationPage.set(new KnowledgeBaseCategoryCreationPage(driver));
		casesCreationPage.set(new CasesCreationPage(driver));
		casesHomePage.set(new CasesHomePage(driver));
		leadHomePage.set(new LeadHomePage(driver));
		leadCreationPage.set(new LeadCreationPage(driver));
		leadDetailPage.set(new LeadDetailPage(driver));
		casesDeatilPage.set(new CasesDetailPage(driver));
		s.set(new SoftAssert());
		parentWindow.set(ReUsableMethods.getWindow());
		commonProductFunctions.set(new CommonProductFunctions(driver));
		categoryName.set(KnowledgeBaseConstants.KNOWLEDGEBASECATEGORY + ReUsableMethods.getCurrentdateTime());
	}

	@AfterMethod(alwaysRun = true)
	public void cleanupThreadLocalVariables() {
		// Clean up ThreadLocal variables to prevent memory leaks
		login.remove();
		navigationPanel.remove();
		accountHomePage.remove();
		accountCreationPage.remove();
		accountDetailPage.remove();
		accountLayoutDesignerPage.remove();
		knowledgeBaseHomePage.remove();
		knowledgeBaseCreationPage.remove();
		knowledgeBaseDetailPage.remove();
		knowledgeBaseCategoryHomePage.remove();
		knowledgeBaseCategoryCreationPage.remove();
		casesCreationPage.remove();
		casesHomePage.remove();
		leadHomePage.remove();
		leadCreationPage.remove();
		leadDetailPage.remove();
		casesDeatilPage.remove();
		s.remove();
		parentWindow.remove();
		commonProductFunctions.remove();
		categoryName.remove();
	}

	@FrameworkAnnotation(testCasePriority = "30", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "171335" }, scriptType = { "Configuration" })
	@Test(priority = 30, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void newcategoryCreation() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName.get()),
				"categoryName.get() not matched");
		s.get().assertAll();
	}

	/*
	 * protected void createCategory(String categoryName.get()) {
	 * ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().manageCategory,
	 * "manage category icon"); String categorypath =
	 * knowledgeBaseCategoryHomePage.get().createcategory_pre + categoryName.get() +
	 * knowledgeBaseCategoryHomePage.get().createcategory_post; if
	 * (ReUsableMethods.findElementByPath(categorypath).size() < 1) {
	 * ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().
	 * newCategory, "Icon home page");
	 * ReUsableMethods.webEnterText(knowledgeBaseCategoryCreationPage.get().
	 * categoryName.get()Field, categoryName.get(), "Category text box");
	 * ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().
	 * saveIcon, "click on save button"); } }
	 */

	@FrameworkAnnotation(testCasePriority = "31", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "162907" }, scriptType = { "Configuration" })
	@Test(priority = 31, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void markedUserAsFavouriteOrRemoveFromFavourite() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);

		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
				"add To Favourite"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().removeFromFavourite, "remove From Favourite");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().addToFavourite,
				"remove From Favourite"));
		s.get().assertAll();
	}

	/*
	 * public void createknowledgeBase(String knowledgeBaseName) {
	 * ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker,
	 * "Search Picker");
	 * ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().searchTextBox,
	 * categoryName.get(), "search category name");
	 * commonProductFunctions.get().waitForLoader();
	 * ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyIcon,
	 * "click on Aplly Button");
	 * ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().
	 * firstRecord_AfterSearchCategory,
	 * "knowledgeBaseCreationPage.get().applyIcon");
	 * ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody,
	 * "Knowledgebasebodycontent", "enter in content Body");
	 * ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description,
	 * "Automation Knowledge Base", "Knowledgebase");
	 * knowledgeBaseCreationPage.get().description.sendKeys(Keys.TAB); int
	 * currentWindowSize = ReUsableMethods.currentWindowSize();
	 * ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().
	 * saveIcon, "click on save button");
	 * commonProductFunctions.get().waitForLoader(); if (currentWindowSize > 1) {
	 * ReUsableMethods.waitforWindowSize(currentWindowSize - 1); }
	 * ReUsableMethods.switchToWindow(parentWindow.get());
	 * commonProductFunctions.get().waitForLoader(); }
	 */

	@FrameworkAnnotation(testCasePriority = "32", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "213801" }, scriptType = { "Configuration" })
	@Test(priority = 32, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCatgoryNameLinkDisabledBrowseByCategory() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseHomePage.get().getBrowseByCategory(), "get Browse By Category not desplayed");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().browser_categoryName,
				"click on bworer category name");
		s.get().assertTrue(knowledgeBaseHomePage.get().getBrowseByCategory(),
				"if clicking on category name working Browse By Category got not visible ");
		s.get().assertAll();
	}

//	@FrameworkAnnotation(testCasePriority = "33", author = { "Rohan Kumar" }, category = {
//			"Knowledge Base" }, TestCaseId = { "210080" }, scriptType = { "Configuration" })
//	@Test(priority = 33, enabled = true, groups = { "Regression", "knowledgeBase" })
//	public void toVerifySearchBarFunctionality_knowledgeBase() throws Exception {
//		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
//		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
//				knowledgeBaseCategoryHomePage, categoryName.get());
//		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
//				knowledgeBaseCreationPage);
//		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
//				actualKnowledgeBaseName);
//		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
//		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
//				knowledgeBaseHomePage.get().globalSearchBar);
//		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
//				"Enter in Search box");
//		s.get().assertEquals(knowledgeBaseHomePage.get().verifyKnowledgebase(), actualKnowledgeBaseName);
//		s.get().assertAll();
//	}

	@FrameworkAnnotation(testCasePriority = "34", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "171341", "171338", "171343", "257733" }, scriptType = { "Execution" })
	@Test(priority = 34, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsSubCategoryVisible_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName.get()),
				"categoryName.get() not matched");
		knowledgeBaseCategoryHomePage.get().toCreateNewSubCategoryKnowledgebase(categoryName.get());
		ReUsableMethods.scrollByPixels(-500);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "home Icon");
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage, knowledgeBaseCreationPage);
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName.get());
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
		commonProductFunctions.get().clickOnSave();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
				"add To Favourite"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().removeFromFavourite, "remove From Favourite");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().addToFavourite,
				"remove From Favourite"));
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "35", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "247109", "247096" }, scriptType = { "Execution" })
	@Test(priority = 35, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void newRecordObjectFieldMapping() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);

		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on toolbox");

		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().objectFieldMapping,
				"Click on Object Field Mapping");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().newButton_objectFieldMapping,
				"Click on new button");
		ReUsableMethods.switchToChildWindowHandle();
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().mappingField, "Filed Mapping"),
				"Mapping Field Not Visible.");

		s.get().assertAll();
	}

	// deleted because of duplicate test case id
	

	@FrameworkAnnotation(testCasePriority = "37", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "254967" }, scriptType = { "Configuration" })
	@Test(priority = 37, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void searchIconNotDisplayedOnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());

		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "Category icon Home Page");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		String actualKnowledgeBaseNameGetting = actualKnowledgeBaseName;
		String expectedKnowledgeBaseGetting = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().tittle,
				"Tittle get");
		s.get().assertEquals(actualKnowledgeBaseNameGetting, expectedKnowledgeBaseGetting,
				"Fail Test Scripts Beacause both are not same");
		HashMap<String, Boolean> searchBarStatus = knowledgeBaseHomePage.get().verifyglobalSearchBarDisplayed();
		s.get().assertFalse(searchBarStatus.get("globalSearchBar"), "globalSearchBar is not displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "38", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "261035" }, scriptType = { "Configuration" })
	@Test(priority = 38, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void isToolBoxIconVisible() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on toolbox");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().toolBox, "ToolBox icon is visible"),
				"Test SCripts got failed because toolbox not visible.");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "39", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "247121" }, scriptType = { "Execution" })
	@Test(priority = 39, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void anyObjectFieldMapping() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToObjectFieldMapping(knowledgeBaseHomePage);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().newButton_objectFieldMapping,
				"Click on new button");
		String parentWindowHandle = ReUsableMethods.getWindow();
		ReUsableMethods.switchToChildWindowHandle();
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().mappingField, "Filed Mapping"),
				"Mapping Field Not Visible.");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().searchPicker_mappingField,
				"Click on search picker Field Mapping");
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().mappingField_withLeadFields, "Address",
				"Enter in search test box");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().applyButton, "Click on Apply button Field Mapping");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().checkBox, "Click on check button Field Mapping");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().okButton, "Click on Ok button Field Mapping");
		knowledgeBaseHomePage.get().clickonSave();
		ReUsableMethods.switchToWindow(parentWindowHandle);
		//ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().objectName_Lead, "Filed Mapping"),
				"Mapping Field Not Visible.");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().objectFieldValued_Address,
				"Filed Mapping"), "Mapping Field Not Visible.");
		s.get().assertAll();

	}
	

	@FrameworkAnnotation(testCasePriority = "40", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164106" }, scriptType = { "Execution" })
	@Test(priority = 40, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsFileimportDatawithXLSX() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToArticleImportWithSwitch(knowledgeBaseHomePage);
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
		knowledgeBaseHomePage.get().uploadFile(KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
		String expectedImportExpertUserName = KnowledgeBaseConstants.USERNAME_IMPORT;
		KnowledgeBaseTestHelper.mapFieldWithDefaultValueInImport(knowledgeBaseHomePage, "ExpertOwnerName",
				expectedImportExpertUserName);
		String actualImportExpertUserName = KnowledgeBaseTestHelper.verifyMappedFieldValue(knowledgeBaseHomePage);
		s.get().assertEquals(expectedImportExpertUserName, actualImportExpertUserName,
				"Test Case failed because actual is not equal as per our expection");
		s.get().assertAll();
	}

	
	// duplicate id
//	@FrameworkAnnotation(testCasePriority = "41", author = { "Rohan Kumar" }, category = {
//			"Knowledge Base" }, TestCaseId = { "164118", "164126" }, scriptType = { "Execution" })
//	@Test(priority = 41, enabled = true, groups = { "Regression", "KnowledgeBase" })
//	public void mappingRichContentEditor_UsingImportFile() throws Exception {
//
//		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
//		KnowledgeBaseTestHelper.openToolboxAndNavigateToArticleImportWithSwitch(knowledgeBaseHomePage);
//		ReUsableMethods.switchToChildWindowHandle();
//		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
//		commonProductFunctions.get().waitForLoader();
//		knowledgeBaseHomePage.get().uploadFile(KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
//		commonProductFunctions.get().waitForLoader();
//		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
//		KnowledgeBaseTestHelper.mapFieldInImport(knowledgeBaseHomePage, "RichContentEditor");
//		ReUsableMethods.webSelectByVisibleText(knowledgeBaseHomePage.get().mapping_richContentEditorField, "Age",
//				"mapping field");
//		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().nextButton, "next button");
//		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().importButton, "import button");
//		s.get().assertTrue(
//				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().recordsUpdate,
//						"records Should be Update"),
//				"Test SCripts got failed because Record not updated as per required");
//		s.get().assertAll();
//	}

	@FrameworkAnnotation(testCasePriority = "42", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164129" }, scriptType = { "Execution" })
	@Test(priority = 42, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void mappingExpireField_UsingImportFile() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToArticleImportWithSwitch(knowledgeBaseHomePage);
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
		knowledgeBaseHomePage.get().uploadFile(KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
		KnowledgeBaseTestHelper.mapFieldInImport(knowledgeBaseHomePage, "ArticleExpireOn");
		ReUsableMethods.webSelectByVisibleText(knowledgeBaseHomePage.get().articleExpireField, "Age", "mapping field");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().nextButton, "next button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().importButton, "import button");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().recordsUpdate,
						"records Should be Update"),
				"Test SCripts got failed because Record not updated as per required");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "43", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "254940", "273284", "278336" }, scriptType = { "Configuration" })
	@Test(priority = 43, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsCommentsCorrect_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "publish Action Icon");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Commentt is published");
		String expectedComments = KnowledgeBaseConstants.COMMENTS_PUBLISHED;
		knowledgeBaseDetailPage.get().clickonSaveButton();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().commentIcon, "Comment icon");
		String actualComments = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().comments,
				"get base knowledge address");
		s.get().assertEquals(expectedComments, actualComments, "Both are getting not equal");
		s.get().assertAll();
	}

	//duplicate method and id 
	
	// this test case marked as manuallly its not working on our port.

	@FrameworkAnnotation(testCasePriority = "47", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "203141" }, scriptType = { "Execution" })
	@Test(priority = 47, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsUserRoleAndTeamManagementVisibleOrNot() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().manageCategory, "manage category icon");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().newCategory, "Icon home page");
//		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
//		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName.get()),
//				"categoryName.get() not matched");
		// knowledgeBaseCategoryHomePage.get().toEditCategoryKnowledgebase(categoryName.get());
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().newCategory, "New Category");

		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().radioButton, "click on radio button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseCategoryHomePage.get().userManagement,
				"User mangement Should be displayed");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().userManagement,
						"User mangement Should be displayed"),
				"Test Scripts got failed because users has been not shown");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().teamManagement,
						"Team mangement Should be displayed"),
				"Test Scripts got failed because team has been not shown");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().roleManagement,
						"Role mangement Should be displayed"),
				"Test Scripts got failed because role has been not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "48", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "273146" }, scriptType = { "Execution" })
	@Test(priority = 48, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyDisappearingUserRoleAndTeamManagement_UsingALLRadioButton() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().manageCategory, "manage category icon");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().newCategory, "Icon home page");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().radioButton, "click on radio button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseCategoryHomePage.get().userManagement,
				"User mangement Should be displayed");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().userManagement,
						"User mangement Should be displayed"),
				"Test Scripts got failed because users has been not shown");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().teamManagement,
						"Team mangement Should be displayed"),
				"Test Scripts got failed because team has been not shown");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().roleManagement,
						"Role mangement Should be displayed"),
				"Test Scripts got failed because role has been not shown");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().radioButton_All, "click on radio button");
		s.get().assertFalse(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().userManagement,
						"User mangement Should be displayed"),
				"Test Scripts got failed because users has been not shown");
		s.get().assertFalse(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().teamManagement,
						"Team mangement Should be displayed"),
				"Test Scripts got failed because team has been not shown");
		s.get().assertFalse(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().roleManagement,
						"Role mangement Should be displayed"),
				"Test Scripts got failed because role has been not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "49", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "171557", "171560" }, scriptType = { "Configuration" })
	@Test(priority = 49, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifySubcategoryCount_knowledgeBaseHomePage() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "KnowLedge");
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName.get()),
				"categoryName.get() not matched");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "home Icon");
		ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().subCategory_With0Folder, "o folder upload");
		if (ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().subCategory_With0Folder,
				"o folder upload")) {
			String elementText = knowledgeBaseHomePage.get().subCategory_With0Folder.getText();
			if (elementText.contains("0 folder")) {
				System.out.println("The element contains '0 folder'.");
			} else {
				System.out.println("The element does not contain '0 folder'.");
			}
		}
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().manageCategory, "manage category icon");
		knowledgeBaseCategoryHomePage.get().toCreateNewSubCategoryKnowledgebase(categoryName.get());
		ReUsableMethods.scrollByPixels(-500);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "home Icon");
		ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().subCategory_With0Folder, "1 folder upload");
		if (ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().subCategory_With0Folder,
				"1 folder upload")) {
			String elementText = knowledgeBaseHomePage.get().subCategory_With1Folder.getText();
			if (elementText.contains("1 folder")) {
				System.out.println("The element contains '1 folder'.");
			} else {
				System.out.println("The element does not contain '1 folder'.");
			}
		}
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().subCategory_With0Folder,
						"User mangement Should be displayed"),
				"Test Scripts got failed because users has been not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "50", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "234304", "234300" }, scriptType = { "Configuration" })
	@Test(priority = 50, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsDeleteCategoryFromHomePage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String categoryName1 = "AA" + categoryName.get();
		knowledgeBaseCreationPage.get().createCategory(categoryName1);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyKnowledgebaseCategory(categoryName1),
				"categoryName not matched");
		commonProductFunctions.get().waitForLoader();
		knowledgeBaseCategoryHomePage.get().toDeleteSubCategoryKnowledgebase(categoryName1);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().okButton_AfterDeleteCategory, "ok Icon");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertFalse(knowledgeBaseCategoryHomePage.get().verifyKnowledgebaseCategory(categoryName1),
				"categoryName not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "51", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "203149" }, scriptType = { "Configuration" })
	@Test(priority = 51, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsDeleteCategory_ForNonAdmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().manageCategory, "manage category icon");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().newCategory, "Icon home page");

		knowledgeBaseCategoryCreationPage.get().enterCategoryName("Category");

		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().manageCategory,
		// "manage
		// category icon");
		// knowledgeBaseCategoryHomePage.get().toDeleteSubCategoryKnowledgebase(expectedCategoryName);

		// access denied issue after resolve to be continous

		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "52", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "203165", "235161", "203272", "305785" }, scriptType = { "Execution" })
	@Test(priority = 52, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void loadingPDFKeyInformation_OnDetailPage() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage, knowledgeBaseCreationPage);
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName.get());
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
		knowledgeBaseHomePage.get().uploadFile(KnowledgeBaseConstants.FILEUPLOADE_PATH);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "Click on key information");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyKeyInformationDisplayed(),
				"Asseration got failed because Key Information text is not Displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "53", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "204568", "247120" }, scriptType = { "Execution" })
	@Test(priority = 53, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCaseTaggedArticleCardOn_CaseCustomizeLayout() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		navigationPanel.get().NavigateToObject("Case");
		ReUsableMethods.webClickElement(casesHomePage.get().toolbox, "click on toolbox");
		ReUsableMethods.webClickElement(casesHomePage.get().customizePageLayouts, "click on toolbox");
		ReUsableMethods.webClickElement(casesHomePage.get().case_SystemLayouts, "click on layout");
		ReUsableMethods.webClickElement(casesHomePage.get().detailPage_SystemLayouts,
				"click on detail page of acse system");
		casesDeatilPage.get().addCaseRelatedArticleIfNotPresent();
		s.get().assertTrue(casesHomePage.get().verifyIsCaseRelatedCardVisibleOrNot(),
				"Asseration got failed because caseRelated Card is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "53", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "204570", "287842" }, scriptType = { "Execution" })
	@Test(priority = 53, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyUserAbleToViewCaseRelatedArticleCard() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		navigationPanel.get().NavigateToObject("Case");
		ReUsableMethods.webClickElement(casesHomePage.get().newIcon, "click on new button");
		ReUsableMethods.webClickElement(casesHomePage.get().Case_System, "click on system layout");
		casesCreationPage.get().EnterSubject("Case knowledge base");
		casesCreationPage.get().clickOnSearchTextBox();
		casesCreationPage.get().clickOnGoldLoan();
		ReUsableMethods.scrollElementToCentreOfScreen(casesCreationPage.get().searchIcon_caseCategory);
		ReUsableMethods.webClickElement(casesCreationPage.get().searchIcon_caseCategory, "click on search icon");
		ReUsableMethods.webSelectByVisibleText_Swift(casesCreationPage.get().selectTag, "Tag", "select tag field");
		ReUsableMethods.webEnterText(casesCreationPage.get().searchChatBot_caseCategory, "Chatbot", "select chatpot");
		ReUsableMethods.webClickElement(casesCreationPage.get().firstRecord, "click on first record");
		casesCreationPage.get().clickOnSave();
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(casesDeatilPage.get().cardIcon, "card icon");
		s.get().assertTrue(casesHomePage.get().verifyIsCaseRelatedCardVisibleOrNot(),
				"Asseration got failed because caseRelated Card is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "53", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "204571", "204573" }, scriptType = { "Execution" })
	@Test(priority = 53, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCaseCustomizeLayout_CaseRelatedArticleCardPanel() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		navigationPanel.get().NavigateToObject("Case");
		ReUsableMethods.webClickElement(casesHomePage.get().newIcon, "click on new button");
		ReUsableMethods.webClickElement(casesHomePage.get().Case_System, "click on system layout");
		casesCreationPage.get().EnterSubject("Case knowledge base");
		casesCreationPage.get().clickOnSearchTextBox();
		casesCreationPage.get().clickOnGoldLoan();
		ReUsableMethods.scrollElementToCentreOfScreen(casesCreationPage.get().searchIcon_caseCategory);
		ReUsableMethods.webClickElement(casesCreationPage.get().searchIcon_caseCategory, "click on search icon");
		ReUsableMethods.webSelectByVisibleText_Swift(casesCreationPage.get().selectTag, "Tag", "select tag field");
		ReUsableMethods.webEnterText(casesCreationPage.get().searchChatBot_caseCategory, "Chatbot", "select chatpot");
		ReUsableMethods.webClickElement(casesCreationPage.get().firstRecord, "click on first record");
		casesCreationPage.get().clickOnSave();
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(casesDeatilPage.get().cardIcon, "card icon");
		s.get().assertTrue(casesHomePage.get().verifyIsCaseRelatedCardVisibleOrNot(),
				"Asseration got failed because caseRelated Card is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "54", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "288994", "288627", "288985", "288627", "288985",
					"288075" }, scriptType = { "Configuration" })
	@Test(priority = 54, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsAbleToCloningArticle() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().clone, "clone  Icon");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_tag, "click on search icon");
		ReUsableMethods.webEnterText(casesCreationPage.get().searchChatBot_caseCategory, "Verma Automation",
				"select chatpot");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "click on search icon");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().checkbox_tag, "click on first record");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().okButton, "click on ok button");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "Click on key information");
		s.get().assertTrue(casesDeatilPage.get().toDispayedkeywordTagsOrNot(),
				"Asseration got failed because DispayedkeywordTags is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "55", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "204575", "204572", "204574",
					"269102" }, scriptType = { "Configuration" })
	@Test(priority = 55, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsAbleToViewMoreAndSubmitSolutionActivityVisible() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());

		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,

				knowledgeBaseCreationPage);
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName.get());
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_tag,
				"click on search icon for Keywords/Tags");
		/*
		 * ReUsableMethods.webEnterText(casesCreationPage.get().
		 * searchChatBot_caseCategory, "chatbot", "select chatpot");
		 * ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton,
		 * "click on search icon");
		 */
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().checkbox_tag, "click on first record");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().okButton, "click on ok button");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		webReusableBusinessFunctions.replaceandNavigate("Summary", "KnowLedge");
		navigationPanel.get().navigateToCaseObject();
		ReUsableMethods.webClickElement(casesHomePage.get().newIcon, "click on new button");
		ReUsableMethods.webClickElement(casesHomePage.get().Case_System, "click on system layout");
		casesCreationPage.get().EnterSubject("Case knowledge base");
		casesCreationPage.get().clickOnSearchTextBox();
		casesCreationPage.get().clickOnGoldLoan();
		ReUsableMethods.scrollByPixels(800);
		ReUsableMethods.webClickElement(casesCreationPage.get().searchIcon_caseCategory, "click on search icon");
		ReUsableMethods.webSelectByVisibleText_Swift(casesCreationPage.get().selectTag, "Tag", "select tag field");
		ReUsableMethods.webEnterText(casesCreationPage.get().searchChatBot_caseCategory, "Chatbot", "select chatpot");
		ReUsableMethods.webClickElement(casesCreationPage.get().firstRecord, "click on first record");

		commonProductFunctions.get().clickOnSave();

		commonProductFunctions.get().waitForLoader();

		ReUsableMethods.webClickElement(casesDeatilPage.get().dataField_BaseKnowledgeCard, "click on first record");
		s.get().assertTrue(casesDeatilPage.get().toDispayedViewMoreIconOrNot(),
				"Asseration got failed because view more icon is not visible");
		s.get().assertTrue(casesDeatilPage.get().toDispayedSubmitSolutionIconOrNot(),
				"Asseration got failed because view more icon is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "56", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "289539", "289521" }, scriptType = { "Configuration" })
	@Test(priority = 56, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void ExpertFieldKeyInformation_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "Click on key information");
		s.get().assertTrue(knowledgeBaseDetailPage.get().validateAuthorNameIsVisibleOrNot(),
				"Test SCripts got failed because Record not updated as per required");
		s.get().assertAll();
	}

	//// this test case mentioned manually
	@FrameworkAnnotation(testCasePriority = "57", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "247271" }, scriptType = { "Configuration" })
	@Test(priority = 57, enabled = false, groups = { "Regression", "KnowledgeBase" })
	public void verifyViewMoreButtonKnowledgeArticle_OnHomePage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
				"Enter in Search box");
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyIsViewAllResultsFieldVisibleOrNot(),
				"Test SCripts got failed because all Records not shown as per required");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "58", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "298837", "289629", "303309" }, scriptType = { "Configuration" })
	@Test(priority = 58, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsUserNameVisible_keyIformation() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);

		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "home Icon");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().clone, "clone  Icon");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_tag, "click on search icon");
		ReUsableMethods.webEnterText(casesCreationPage.get().searchChatBot_caseCategory, "Verma Automation",
				"select chatpot");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "click on search icon");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().checkbox_tag, "click on first record");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().okButton, "click on ok button");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "Click on key information");
		s.get().assertTrue(knowledgeBaseDetailPage.get().validateAuthorNameIsVisibleOrNot(),
				"Test SCripts got failed because Record not updated as per required");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "59", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "299283", "303252", "303328" }, scriptType = { "Configuration" })
	@Test(priority = 59, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyAllPublishedArticles_OnMyPublishSection() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().myActiclesSection);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myActiclesSection, "my article section");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myPublished, "my published section");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"categoryName.get() not matched");

		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "60", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "299279" }, scriptType = { "Configuration" })
	@Test(priority = 60, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyAllDraftArticles_OnMyDraftection() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().myActiclesSection);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myActiclesSection, "my article section");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"categoryName.get() not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "61", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "301227" }, scriptType = { "Configuration" })
	@Test(priority = 61, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCancelButton_onPublishCommentSection() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "publishActionIcon");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().cancelButton, "cancel button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"categoryName not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "62", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "259094" }, scriptType = { "Configuration" })
	@Test(priority = 62, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void validateNoDataExists_UsingIncorrectID() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String inCorrectIDKnowledgeBaseName = "123456";
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, inCorrectIDKnowledgeBaseName,
				"Enter in Search box");
		s.get().assertFalse(knowledgeBaseHomePage.get().verifyNoDataExist_usingWrongID(), "not data exist ");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "63", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "301213" }, scriptType = { "Configuration" })
	@Test(priority = 63, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyValidationMassage_publishedArticles() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "publishActionIcon");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().saveButton, "Save button");
		s.get().assertTrue(knowledgeBaseDetailPage.get().getValidationMassage(), "Validation Massage not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "64", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "288090" }, scriptType = { "Configuration" })
	@Test(priority = 64, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsCloningArticleDisplay_onMyDraftSection() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().clone, "clone  Icon");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().myActiclesSection);
		// ReUsableMethods.scrollByPixels(100);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myActiclesSection, "my article section");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"categoryName.get() not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "65", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "322410", "322407" }, scriptType = { "Configuration" })
	@Test(priority = 65, enabled = true, groups = { "Regression", "Client", "KnowledgeBase" })
	public void byDefaultArticleShownOnCategoryPanel() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().myActiclesSection);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myActiclesSection, "article my section");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().myReviews, "reviews");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().myReviews,
				"On knowledge home page my reviews articles not getting visible"));
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().myDraft,
				"On knowledge home page my draft articles not getting visible"));
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().myPublished,
				"On knowledge home page published articles not getting visible"));
		commonProductFunctions.get().waitForLoader();
		knowledgeBaseDetailPage.get().clickOnDraftKnowledgeBase(actualKnowledgeBaseName);
		String expectedKnowledgeBaseGetting = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().tittle,
				"Tittle get");
		s.get().assertEquals(actualKnowledgeBaseName, expectedKnowledgeBaseGetting,
				"Fail Test Scripts Beacause both are not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "66", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "354415" }, scriptType = { "Configuration" })
	@Test(priority = 66, enabled = false, groups = { "Regression", "Client", "KnowledgeBase" })
	public void uINotDistortingAfterSearchingArticles() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
//		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
//		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
//		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
//		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
//				knowledgeBaseCreationPage);
//		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
//				actualKnowledgeBaseName);
//		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
//		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
//		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
//				"Search box");
//		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(actualKnowledgeBaseName),
//				"actual knowledge base is not shown");
		ReUsableMethods.webClearText(knowledgeBaseHomePage.get().globalSearchBar, "clean");
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, "Knowledge", "Search box");
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().myPublished);
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().allResults, "All record view"),
				"test case failed because not records has been shown");
		int expectedSizeKB = knowledgeBaseHomePage.get().getKnowledgeBaseCount();
		int actualSizeKB = KnowledgeBaseConstants.TOTAL_KNOWLEDGEBASE_5;
		s.get().assertEquals(actualSizeKB, expectedSizeKB, "Both count are not equal");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "67", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "362046" }, scriptType = { "Configuration" })
	@Test(priority = 67, enabled = true, groups = { "Regression", "Client", "KnowledgeBase" })
	public void defaultAscendingDescendingSorting() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		accountHomePage.get().sortRequirementSubject(AccountConstant.ASCENDING_ORDER);
		List<WebElement> listAscending = ReUsableMethods
				.findElementByPath(accountHomePage.get().xpathViewListingElements);
		List<String> elementTextAscending = ReUsableMethods.getListOfText_FromElementList(listAscending);
		commonProductFunctions.get().waitForLoader();
		boolean isAscending = ReUsableMethods.isListSorted(elementTextAscending, true);
		s.get().assertTrue(isAscending, "List not in ascending order");
		accountHomePage.get().sortRequirementSubject(AccountConstant.DESCENDING_ORDER);
		List<WebElement> listDescending = ReUsableMethods
				.findElementByPath(accountHomePage.get().xpathViewListingElements);
		List<String> elementTextDescending = ReUsableMethods.getListOfText_FromElementList(listDescending);
		commonProductFunctions.get().waitForLoader();
		boolean isDescending = ReUsableMethods.isListSorted(elementTextDescending, false);
		s.get().assertTrue(isDescending, "List not in descending order");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "68", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "345723" }, scriptType = { "Configuration" })
	@Test(priority = 68, enabled = true, groups = { "Regression", "Client", "KnowledgeBase" })
	public void ratingAndReviewsVerified() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().starRating, "Click on start rating for reviews");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().giveRating, "Good", "give  a good rating");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendReview, "Click on send rating ");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().ratingAndReviews_move, "click on rating review");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().goodReview, "review"),
				"Test Scripts got failed because good review  not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().ratingCount, "rating count"),
				"Test Scripts got failed because rating count not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "69", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "328633" }, scriptType = { "Configuration" })
	@Test(priority = 69, enabled = true, groups = { "Regression", "Client", "KnowledgeBase" })

	public void selectManageCategoryFields() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new icon");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker, "Search Picker");
		List<String> ActualDropdownValues = knowledgeBaseCreationPage.get().storeDropDownFields();
		System.out.println(ActualDropdownValues);
		List<String> expectedDropDownValues = KnowledgeBaseConstants.KNOWLEDGE_BASE_CATEGORY_FIELDS;
		s.get().assertEquals(ActualDropdownValues, expectedDropDownValues, "Dropdown values do not match!");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "70", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "333038" }, scriptType = { "Configuration" })
	@Test(priority = 70, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void accessDeniedPopUp_forNonUser() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedcategoryName = categoryName.get();
		knowledgeBaseCategoryHomePage.get().createCategoryWithMultiPermitAccess(expectedcategoryName,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER, KnowledgeBaseConstants.USERNAME_EXPERTS);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().viewAll);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().viewAll, "view all record");
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().accessDenied,
				"access denied popup"), "Test Scripts failed because access denied not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "71", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "305745" }, scriptType = { "Configuration" })
	@Test(priority = 71, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyLastModifyDateAndTime_historyTab() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().history, "history tab");
		ReUsableMethods.switchToChildWindowHandle();
		String CurrentDate = ReUsableMethods.getCurrentdateInFormat("dd-MM-yyyy");
		String LastModifiedOn = knowledgeBaseHomePage.get().getTextOfLastModifiedOn();
		s.get().assertEquals(LastModifiedOn, CurrentDate, "Last modified time date not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "71", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "308554" }, scriptType = { "Configuration" })
	@Test(priority = 71, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyLastCreatedBy_historyTab() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		String expectedCreatedBy = KnowledgeBaseConstants.USERNAME_EXPERTS;
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().history, "history tab");
		ReUsableMethods.switchToChildWindowHandle();
		String actualCreatedBy = ReUsableMethods.WebGetElementText(knowledgeBaseHomePage.get().lastCreatedBy,
				"last created by user");
		s.get().assertEquals(actualCreatedBy, expectedCreatedBy, "Last created by user name not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "72", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "305757", "339914" }, scriptType = { "Configuration" })
	@Test(priority = 72, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void ascendingDescendingSorting_historyTab() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().history, "history tab");
		ReUsableMethods.switchToChildWindowHandle();
		knowledgeBaseHomePage.get().sortingByTittle(AccountConstant.ASCENDING_ORDER);
		List<WebElement> listAscending = ReUsableMethods
				.findElementByPath(accountHomePage.get().xpathViewListingElements);
		List<String> elementTextAscending = ReUsableMethods.getListOfText_FromElementList(listAscending);
		commonProductFunctions.get().waitForLoader();
		boolean isAscending = ReUsableMethods.isListSorted(elementTextAscending, true);
		s.get().assertTrue(isAscending, "List not in ascending order");
		knowledgeBaseHomePage.get().sortingByTittle(AccountConstant.DESCENDING_ORDER);
		List<WebElement> listDescending = ReUsableMethods
				.findElementByPath(accountHomePage.get().xpathViewListingElements);
		List<String> elementTextDescending = ReUsableMethods.getListOfText_FromElementList(listDescending);
		commonProductFunctions.get().waitForLoader();
		boolean isDescending = ReUsableMethods.isListSorted(elementTextDescending, false);
		s.get().assertTrue(isDescending, "Records not shown as acending and descending order");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "73", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "307295", "308619", "308620" }, scriptType = { "Configuration" })
	@Test(priority = 73, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void functionalityOfClosingHistoryTab() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().history, "history tab");
		ReUsableMethods.switchToChildWindowHandle();
	//	int totalWindows = ReUsableMethods.currentWindowSize();
//		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().titleName, "tittle name");
//		ReUsableMethods.waitforWindowSize(totalWindows - 1);
		ReUsableMethods.switchToWindow(parentWindow.get());
//		s.get().assertTrue(
//				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().closeButton, "close button"),
//				"create close button is not displayed");
//		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().closeButton, "close button");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().createNewIcon, "create new button"),
				"create new button is not displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "74", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "308598" }, scriptType = { "Configuration" })
	@Test(priority = 74, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void rejectedStatusCode_oHistoryArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "review button");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Commentt is published");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().saveButton, "save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().history, "history tab");
		ReUsableMethods.switchToChildWindowHandle();
		String expectedRejectedcode = KnowledgeBaseConstants.STATUS_CODE_REJECTED_TYPE;
		String actualRejectedCode = ReUsableMethods.WebGetElementText(knowledgeBaseHomePage.get().rejectedStatus,
				"rejected status code");
		s.get().assertEquals(actualRejectedCode, expectedRejectedcode,
				"actual rejected and expected rejected code not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "75", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "308599" }, scriptType = { "Configuration" })
	@Test(priority = 75, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void rejectedStatusCode_keyinformationArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "review button");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Commentt is published");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().saveButton, "save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "key information icon");
		String actualRejectedArticle = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().rejectedStatus,
				"rejected article");
		String expectedRejectedArticle = KnowledgeBaseConstants.STATUS_CODE_REJECTED_TYPE;
		s.get().assertEquals(actualRejectedArticle, expectedRejectedArticle,
				"actual rejected and expected rejected article on key information not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "76", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "305781" }, scriptType = { "Configuration" })
	@Test(priority = 76, enabled = false, groups = { "Regression", "KnowledgeBase" })
	public void viewAndExecuteExportToPdfFile() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().exportToPdf, "export to pdf file");
		// to be continues
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "77", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "385703" }, scriptType = { "Configuration" })
	@Test(priority = 77, enabled = true, groups = { "Regression", "client", "KnowledgeBase" })
	public void createNewKeywordAndTags() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		String keywordAndTagValue = knowledgeBaseCreationPage.get().enterKeywordTags();
		s.get().assertTrue(knowledgeBaseCreationPage.get().verifyIsKeywordAndTagVisible(keywordAndTagValue),
				"Keyword and tag value is not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "78", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "386286" }, scriptType = { "Configuration" })
	@Test(priority = 78, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNewKeywordAndTagsOnArticleDetailPage() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName.get());
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "home Icon");

		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,

				knowledgeBaseCreationPage);
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName.get());
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"content Body");
		String keywordAndTagValue = knowledgeBaseCreationPage.get().enterKeywordTags();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "key information icon");
		s.get().assertTrue(knowledgeBaseCreationPage.get().verifyIsKeywordAndTagVisible(keywordAndTagValue),
				"Keyword and tag value is not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "79", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "357569" }, scriptType = { "Configuration" })
	@Test(priority = 79, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyKnowledgeButtonOnObjectDetailPage() throws Exception {
		login.get().genericAdminLogin(webURL, auto5User, webpassword);
		navigationPanel.get().navigateToLeadObject();
		leadHomePage.get().clickOnNewButton();
		leadHomePage.get().clickOnLayout(LeadConstants.LEAD_SYSTEM_ENGLISH);
		String expectedLeadName = leadCreationPage.get().enterLastName(LeadConstants.LASTNAME_KUMAR);
		leadCreationPage.get().createLeadWithMandatoryField(expectedLeadName, LeadConstants.LEAD_RATING_ENGLISH,
				LeadConstants.PRODUCT_HOME_LOAN);
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(leadDetailPage.get().knowledgeButton, "knowledge article button"),
				"Test Scripts failed because knolwdge button is not shown");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "80", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "322567" }, scriptType = { "Execution" })
	@Test(priority = 80, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyHistoryVersionsDetails() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"knowledge base not matched");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().verisonHistory, "'Version History'");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().version1,
				"VI version is not displayed"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().version1, "VI version");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"knowledge base not displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "81", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "311768" }, scriptType = { "Configuration" })
	@Test(priority = 81, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyKeyInformationDetails() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName.get());
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName.get(),
				actualKnowledgeBaseName);
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().keyInformation,
				"Key Infomation not displayed"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "key information");
		HashMap<String, Boolean> keyInformationSections = knowledgeBaseDetailPage.get().keyInformationDetails();
		s.get().assertTrue(keyInformationSections.get("Document Details"),
				"Document Details not displayed under Key Information");
		s.get().assertTrue(keyInformationSections.get("Attachments"),
				"Attachments not displayed under Key Information");
		HashMap<String, Boolean> keyInformationFields = knowledgeBaseDetailPage.get()
				.verifyKeyInformationFieldsDisplayed();
		s.get().assertTrue(keyInformationFields.get("Author"), "Author field is not displayed");
		s.get().assertTrue(keyInformationFields.get("Expert"), "Expert field is not displayed");
		s.get().assertTrue(keyInformationFields.get("Expire On"), "Expire On field is not displayed");
		s.get().assertTrue(keyInformationFields.get("Keywords / Tags"), "Keywords / Tags field is not displayed");
		s.get().assertTrue(keyInformationFields.get("Versioning"), "Versioning field is not displayed");
		s.get().assertTrue(keyInformationFields.get("Article Status"), "Article Status field is not displayed");
		s.get().assertAll();
	}

	/**
	 * Test Case: Verify Knowledge Card is displayed on Account Detail Page Card
	 * View Steps: 1. Login with Admin user 2. Go to Account object 3. Go to Toolbox
	 * -> Customize Page Layout 4. Click on 3 pin icon against 'Default' layout ->
	 * Summary -> Detail 5. Click on Card under Layout Mode -> Select under Old
	 * Designer 6. Search 'Knowledge' & drag & drop it on layout 7. Save the layout
	 * 8. Create/Open an account record on the same layout 9. On detail page, click
	 * on card view Expected: Knowledge card should display on card view of account
	 * detail page
	 */
	@FrameworkAnnotation(testCasePriority = "54", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "247256" }, scriptType = { "Execution" })
	@Test(priority = 54, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyKnowledgeCardOnAccountCustomizeLayout() throws Exception {
		login.get().genericAdminLogin(webURL, auto5User, webpassword);
		navigationPanel.get().NavigateToObject("Account");
		ReUsableMethods.webClickElement(accountHomePage.get().toolBox, "click on toolbox");
		ReUsableMethods.webClickElement(accountHomePage.get().customizePagelayout, "click on customize page layout");
		ReUsableMethods.webClickElement(accountLayoutDesignerPage.get().defaultLayout_Account_System, "click on default layout");
		ReUsableMethods.webClickElement(accountLayoutDesignerPage.get().detailPageOfLayoutDesigner,
				"click on detail page of layout designer");
		accountLayoutDesignerPage.get().addAccountRelatedArticleIfNotPresent();
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(accountLayoutDesignerPage.get().accountCardValue, "card value"),
				"Asseration got failed because accountRelated Card is not visible");
		s.get().assertAll();
	}

	/**
	 * Test Case: Verify Non-admin user is able to import Knowledge Base articles
	 * using CSV format Steps: 1. Launch the application and login 2. Go to
	 * Knowledge home page 3. Click on Toolbox button 4. Click on 'Import Articles'
	 * 5. Choose 'CSV Format' in Source Type field -> Fill all details -> Click Next
	 * -> Choose Files -> Upload CSV file -> Click Next -> Click Import Expected: -
	 * User should be able to import the file data as per the selected data file -
	 * Data should be imported if correctly structured, otherwise fail records
	 * should display under Import logs
	 */
	@FrameworkAnnotation(testCasePriority = "101", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "261291" }, scriptType = { "Execution" })
	@Test(priority = 101, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyUserAbleToImportKnowledgeBaseArticlesUsingCSVFormat() throws Exception {
		// Step 1: Launch the application and login to the application
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);

		// Step 2: Go to Knowledge home page - Verify Toolbox button is displayed
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().toolBox, "Toolbox button"),
				"Toolbox button with 'Toolbox' name & an icon should display");

		// Step 3: Click on Toolbox button
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on toolbox");

		// Verify 'Import Articles' is displayed in drop-down
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().articleImport, "Import Articles"),
				"'Import Articles' should display in drop-down");

		// Step 4: Click on 'Import Articles'
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().articleImport, "Click on Import Articles");
		ReUsableMethods.switchToChildWindowHandle();

		// Verify new window with Import article configuration is displayed
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().next, "Next button"),
				"New window with Import article configuration should display on page");

		// Step 5: Choose 'CSV Format' in Source Type field -> Fill all details -> Click
		// Next
		// Note: CSV Format is the default source type, so we proceed directly
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "Click on Next button");
		commonProductFunctions.get().waitForLoader();

		// Click on Choose Files link on Upload Data field -> Choose the File with
		// multiple knowledge data
		String csvFilePath = KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH;
		knowledgeBaseHomePage.get().uploadFile(csvFilePath);

		// Verify file is uploaded
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().uploaededFileName,
				"Uploaded file name"), "File should be uploaded successfully");

		// Perform field mapping as per CSV file structure
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		KnowledgeBaseTestHelper.mapFieldWithDefaultValueInImport(knowledgeBaseHomePage, "ExpertOwnerName",
				expectedExpertUserName);

		// Verify the mapped field value
		String actualExpertUserName = KnowledgeBaseTestHelper.verifyMappedFieldValue(knowledgeBaseHomePage);
		s.get().assertEquals(expectedExpertUserName, actualExpertUserName,
				"Field mapping verification failed - Expert Owner Name does not match");
		// Click on Next button to proceed to field mapping
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "Click on	Next button");
		commonProductFunctions.get().waitForLoader();
		// Click on Import button
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().importButton, "Click on Import button");
		commonProductFunctions.get().waitForLoader();

		// Expected: Verify data is imported successfully
		// Wait for import process to complete
		ReUsableMethods.waitforElementInvisible(knowledgeBaseHomePage.get().pleaseWaitImportInProgress);

		// Verify import completion message or export button is displayed (indicates
		// import completed)
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().exportButton, "Export button")
						|| ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().recordsUpdate,
								"Records update message"),
				"User should be able to import the file data as per the selected data file");

		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "102", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "262144" }, scriptType = { "Execution" })
	@Test(priority = 102, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyUserAbleToImportKnowledgeBaseArticlesUsingXLSXFormat() throws Exception {
		// Step 1: Launch the application and login to the application with auto15User
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);

		// Step 2: Go to Knowledge home page - Verify Toolbox button is displayed
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().toolBox, "Toolbox button"),
				"Toolbox button with 'Toolbox' name & an icon should display");

		// Step 3: Click on Toolbox button
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on Toolbox button");
		commonProductFunctions.get().waitForLoader();

		// Expected: 'Import Articles' should display in drop-down
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().articleImport,
				"Import Articles option"), "'Import Articles' should display in drop-down");

		// Step 4: Click on 'Import Articles'
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().articleImport, "Click on Import Articles");
		ReUsableMethods.switchToChildWindowHandle();

		// Expected: New window with Import article configuration should display on page
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().next, "Next button in import window"),
				"New window with Import article configuration should display on page");

		// Step 5: Choose 'XLSX Format' in Source Type field -> Fill all details ->
		// Click Next
		// Select 'XLSX Format' from Source Type dropdown
		ReUsableMethods.webSelectByVisibleText(knowledgeBaseHomePage.get().sourceTypeDropdown, "XLSX Format",
				"Select XLSX Format from Source Type dropdown");
		commonProductFunctions.get().waitForLoader();

		// Verify XLSX Format is selected
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().sourceTypeDropdown,
				"Source Type dropdown"), "XLSX Format should be selected in Source Type field");

		// Click Next button to proceed to file upload
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "Click on Next button");
		commonProductFunctions.get().waitForLoader();

		// Click on Choose Files link on Upload Data field -> Choose the File with
		// multiple knowledge data
		String xlsxFilePath = KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH1;
		knowledgeBaseHomePage.get().uploadFile(xlsxFilePath);
		commonProductFunctions.get().waitForLoader();

		// Verify file is uploaded
//		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().uploaededExcelFileName,
//				"Uploaded file name"), "File should be uploaded successfully");

		// Perform field mapping as per XLSX file structure
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		KnowledgeBaseTestHelper.mapFieldWithDefaultValueInImport(knowledgeBaseHomePage, "ExpertOwnerName",
				expectedExpertUserName);

		// Verify the mapped field value
		String actualExpertUserName = KnowledgeBaseTestHelper.verifyMappedFieldValue(knowledgeBaseHomePage);
		s.get().assertEquals(expectedExpertUserName, actualExpertUserName,
				"Field mapping verification failed - Expert Owner Name does not match");
		// Click on Next button to proceed to field mapping
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "Click on	Next button");
		commonProductFunctions.get().waitForLoader();

		// Click on Import button
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().importButton, "Click on Import button");
		commonProductFunctions.get().waitForLoader();

		// Expected: Verify data is imported successfully
		// Wait for import process to complete
		ReUsableMethods.waitforElementInvisible(knowledgeBaseHomePage.get().pleaseWaitImportInProgress);
		// Verify import completion message or export button is displayed (indicates
		// import completed)
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().exportButton, "Export button"),"User should be able to import the file data as per the selected data file. Data should be imported if the data is correctly structured, otherwise fail record should display under the Import logs");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().recordsUpdate,
								"Records update message"),
				
				"User should be able to import the file data as per the selected data file. Data should be imported if the data is correctly structured, otherwise fail record should display under the Import logs");

		s.get().assertAll();
	}

}
