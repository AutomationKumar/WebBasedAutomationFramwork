package com.businessnext.knowledgebase.testcases;

import java.util.HashMap;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.common.pages.RelatedActivityPage;
import com.drivermanager.DriverManager;
import com.listeners.*;
import com.setup.BaseClass;
import com.utilities.Constants;
import com.utilities.ReUsableMethods;
import com.utilities.WebWait;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)
public class KnowledgeBaseTestCasesConfiguration extends BaseClass {
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<RelatedActivityPage> relatedActivityPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryHomePage> knowledgeBaseCategoryHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryCreationPage> knowledgeBaseCategoryCreationPage = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<String> parentWindow = new ThreadLocal<>();

	static String categoryName;
	static {
		categoryName = KnowledgeBaseConstants.KNOWLEDGEBASECATEGORY + ReUsableMethods.getCurrentdateTime();

	}

	@BeforeMethod(alwaysRun = true)
	public void initializePageObjects() {
		// Cache driver to avoid 8 ThreadLocal lookups - Performance optimization
		WebDriver driver = DriverManager.getWdriver();

		login.set(new webReusableBusinessFunctions());
		navigationPanel.set(new NavigationPanel(driver));
		relatedActivityPage.set(new RelatedActivityPage(driver));
		commonProductFunctions.set(new CommonProductFunctions(driver));
		knowledgeBaseHomePage.set(new KnowledgeBaseHomePage(driver));
		knowledgeBaseCreationPage.set(new KnowledgeBaseCreationPage(driver));
		knowledgeBaseDetailPage.set(new KnowledgeBaseDetailPage(driver));
		knowledgeBaseCategoryHomePage.set(new KnowledgeBaseCategoryHomePage(driver));
		knowledgeBaseCategoryCreationPage.set(new KnowledgeBaseCategoryCreationPage(driver));
		s.set(new SoftAssert());
		parentWindow.set(ReUsableMethods.getWindow());
	}

	@FrameworkAnnotation(testCasePriority = "19", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164369", "259083","164370" }, scriptType = { "Configuration" })
	@Test(priority = 19, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifyExpertFieldOnNewEditPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		// Select expert user
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_expertField,
				"click on search picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().enterUserName_expert, expectedExpertUserName,
				"enter user name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "Click on apply button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstUserName, "click on first user name");
		// Select category
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName);
		// Fill article content
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
//		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button");
//		//KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
//		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "click on edit buuton");
		//ReUsableMethods.switchToChildWindowHandle();
		String actualExpertUserName = knowledgeBaseDetailPage.get().verifyUserName();
		s.get().assertEquals(expectedExpertUserName, actualExpertUserName,
				"Test Case failed because actual is not equal as per our expection");
		//ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "20", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164120", "164116", "164110", "164115" }, scriptType = { "Execution" })
	@Test(priority = 20, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void importfunctionality_expertFieldMapping() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToArticleImportWithSwitch(knowledgeBaseHomePage);
		KnowledgeBaseTestHelper.uploadFileAndProceed(knowledgeBaseHomePage,
				KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		String expectedImportExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		KnowledgeBaseTestHelper.mapFieldWithDefaultValueInImport(knowledgeBaseHomePage, "ExpertOwnerName",
				expectedImportExpertUserName);
		String actualImportExpertUserName = KnowledgeBaseTestHelper.verifyMappedFieldValue(knowledgeBaseHomePage);
		ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertEquals(expectedImportExpertUserName, actualImportExpertUserName,
				"Test Case failed because actual is not equal as per our expection");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "20", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164105", "262174" }, scriptType = { "Execution" })
	@Test(priority = 20, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void importAnyFile_OnBaseKnowledge() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on toolbox");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().articleImport, "click on import article");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
		String expectedUploadedFile = KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH;
		knowledgeBaseHomePage.get().uploadFile(KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		String actualUploadedFile = Constants.sourceFilePath1
				+ knowledgeBaseHomePage.get().getUploaedfFileName();

		/*
		 * String actualUploadedFile1 = Paths.get("D:",
		 * "Businessnext_AutoSwiftFramework", "Businessnext_Automation_Swift", "src",
		 * "test", "resources", "data",
		 * knowledgeBaseHomePage.get().getUploaedfFileName()).toString();
		 */

		// ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertEquals(actualUploadedFile, expectedUploadedFile, "Uploadfile is not matched as per uploaded");
		ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "21", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "162902", "162903", "162904", "210516",
					"210517" }, scriptType = { "Configuration" })
	@Test(priority = 21, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreatedMultipleArticles_OnHomePage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		// Thread.sleep(2000);
		commonProductFunctions.get().waitForLoader();

		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);

		String actualKnowledgeBaseName2 = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName2, categoryName);

		commonProductFunctions.get().waitForLoader();

		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);

		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName2,
				"Enter in Search box");
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgebase_NotPublished(actualKnowledgeBaseName2),
				"Failed Scripts because base knowledge name is not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "22", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "204683", "204662", "204687", "204689",
					"204693" }, scriptType = { "Configuration" })
	@Test(priority = 22, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyRatingsAndReviewsBaseKnowledge() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage, knowledgeBaseCreationPage);
		// Select expert user
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_expertField,
				"click on search picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().enterUserName_expert, expectedExpertUserName,
				"enter user name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "Click on apply button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstUserName, "click on first user name");
		// Select category
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName);
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description,
				"enter in description Automation Knowledge Base", "Knowledgebase");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webMoveToElement(knowledgeBaseDetailPage.get().ratingAndReviews_move, "move to element");
		HashMap<String, Boolean> ArticalStatus = knowledgeBaseDetailPage.get().verifyAnArtical_ratingAndReviews();
		s.get().assertTrue(ArticalStatus.get("Ratings and Reviews"), "CreateNewButton is not displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "23", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "204666", "204665", "204691",
					"204667" }, scriptType = { "Configuration" })
	@Test(priority = 23, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifyStarReviewfield_BaseKnowledge() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, actualKnowledgeBaseName,
				"enter in tittle text box");
		// Select expert user
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_expertField,
				"click on search picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().enterUserName_expert, expectedExpertUserName,
				"enter user name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "Click on apply button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstUserName, "click on first user name");
		// Select category
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName);
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description,
				"enter in description Automation Knowledge Base", "Knowledgebase");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		Thread.sleep(2000);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().starRating, "Click on start rating for reviews");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().giveRating, "Good", "give  a good rating");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendReview, "Click on send rating ");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().ratingAndReviews_move, "click on rating review");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyGettingReviewRating(),
				"Approved rating not desplayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "24", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "256502", "257675", "259069",
					"283475" }, scriptType = { "Configuration" })
	@Test(priority = 24, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreatedAndModifiedPublishedArtical_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		// ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar,
		// actualKnowledgeBaseName,
		// "Enter in Search box");
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().myActiclesSection);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myActiclesSection, "article section");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myPublished, "published section");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName), "article is not visible");
//		knowledgeBaseCategoryHomePage.get().clickKnowledgebase(actualKnowledgeBaseName);
//		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "click on edit Button");
//
//		ReUsableMethods.webClearText(knowledgeBaseCreationPage.get().tittleText, "");
//		String editedActualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
//		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, editedActualKnowledgeBaseName,
//				"enter edit name in tittle text box");
//		knowledgeBaseDetailPage.get().clickonSaveButton();
//		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
//		String modifiedKnowledgeBaseName = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().tittleName,
//				"get base knowledge tittle name");
//		s.get().assertEquals(editedActualKnowledgeBaseName, modifiedKnowledgeBaseName, "Both are getting not equal");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "25", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "171561" }, scriptType = { "Configuration" })
	@Test(priority = 25, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyBrowseByCategory_OnHomePage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
				"add To Favourite"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "click on cross button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		// boolean browseByCategoryDisplaedOrNot =
		// ReUsableMethods.isElementDisplayed(knowledgeBaseHomePage.get().browseByCategory,
		// "visible");
		s.get().assertTrue(knowledgeBaseHomePage.get().getBrowseByCategory(), "get Browse By Category not desplayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "26", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "255438" }, scriptType = { "Configuration" })
	@Test(priority = 26, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsFolderFileVisibleorNot() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);

		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyFolderFileIsVisible(), "no file not geeting visible");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().expandCollapse, "click on expand and collapse");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyFolderFileIsVisible(), "file geeting visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "27", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164397", "203182" }, scriptType = { "Configuration" })
	@Test(priority = 27, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifyExpireOn_OnNewEditPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage, knowledgeBaseCreationPage);
		// Select expert user
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_expertField,
				"click on search picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().enterUserName_expert, expectedExpertUserName,
				"enter user name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "Click on apply button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstUserName, "click on first user name");
		// Select category
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName);
		// Fill article content
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
		//ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button");
		
		commonProductFunctions.get().clickOnSave();
		
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		
		commonProductFunctions.get().clickOnEdit();
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
	// 	ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "click on edit buuton");
	//	
	//	ReUsableMethods.switchToChildWindowHandle();
		s.get().assertTrue(knowledgeBaseCreationPage.get().verifyIsExpiredDateFieldDisabled(),
				"Expired On type feiled not visible");
	//	ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "28", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "162908" }, scriptType = { "Configuration" })
	@Test(priority = 28, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifyUIUXShouldDisplayExpectedResultsOrNot() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		Thread.sleep(2000);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
				"Enter in Search box");
		HashMap<String, Boolean> searchBarStatus = knowledgeBaseHomePage.get().verifyglobalSearchBarDisplayed();
		s.get().assertTrue(searchBarStatus.get("globalSearchBar"), "globalSearchBar is not displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "29", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164372" }, scriptType = { "Configuration" })
	@Test(priority = 29, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifyExpertDataOnNewEditPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage, knowledgeBaseCreationPage);
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		// Select expert user
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_expertField,
				"click on search picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().enterUserName_expert, expectedExpertUserName,
				"enter user name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "Click on apply button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstUserName, "click on first user name");
		// Select category
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName);
		// Fill article content
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
		knowledgeBaseCreationPage.get().description.sendKeys(Keys.TAB);
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button");
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "click on edit buuton");
		ReUsableMethods.switchToChildWindowHandle();
		String actualExpertUserName = knowledgeBaseDetailPage.get().verifyUserName();
		s.get().assertEquals(expectedExpertUserName, actualExpertUserName,
				"Test Case failed because actual is not equal as per our expection");
		ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "30", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "242118" }, scriptType = { "Configuration" })
	@Test(priority = 30, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifyIsScrollBarVisible() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().scrollbar);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyIsScrollbarIconDisabled(),
				"Expired On type feiled not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "31", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "204669", "204670", "298835" }, scriptType = { "Configuration" })
	@Test(priority = 31, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifySubmitTheReview_BaseKnowledge() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		String expectedExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		// Select expert user
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker_expertField,
				"click on search picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().enterUserName_expert, expectedExpertUserName,
				"enter user name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyButton, "Click on apply button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstUserName, "click on first user name");
		// Select category
		KnowledgeBaseTestHelper.selectCategory(knowledgeBaseCreationPage, categoryName);
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description,
				"enter in description Automation Knowledge Base", "Knowledgebase");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		Thread.sleep(2000);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().starRating, "Click on start rating for reviews");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().giveRating, "Good", "give  a good rating");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendReview, "Click on send rating ");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().ratingAndReviews_move, "click on rating review");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyGettingReviewRating(),
				"Approved rating not desplayed");
		s.get().assertAll();
	}

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

	@FrameworkAnnotation(testCasePriority = "33", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "210080" }, scriptType = { "Configuration" })
	@Test(priority = 33, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void toVerifySearchBarFunctionality_knowledgeBase() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "publishActionIcon");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Commentt is published");
		knowledgeBaseDetailPage.get().clickonSaveButton();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
				"Enter in Search box");
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(actualKnowledgeBaseName), "true");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "34", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "171341", "171338", "171343",
					"257733" }, scriptType = { "Configuration" })
	@Test(priority = 34, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsSubCategoryVisible_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
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
			"Knowledge Base" }, TestCaseId = { "247109" }, scriptType = { "Configuration" })
	@Test(priority = 35, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void newRecordObjectFieldMapping() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToObjectFieldMapping(knowledgeBaseHomePage);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().newButton_objectFieldMapping,
				"Click on new button");
		ReUsableMethods.switchToChildWindowHandle();
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().mappingField, "Filed Mapping"),
				"Mapping Field Not Visible.");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "36", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "247113" }, scriptType = { "Configuration" })
	@Test(priority = 36, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void newLeadObjectFieldMapping() throws Exception {

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

	@FrameworkAnnotation(testCasePriority = "37", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "254967" }, scriptType = { "Configuration" })
	@Test(priority = 37, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void searchIconNotDisplayedOnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
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

	//test case removed because of duplictae test id
	
	@FrameworkAnnotation(testCasePriority = "40", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164106" }, scriptType = { "Configuration" })
	@Test(priority = 40, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsFileimportDatawithXLSX() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToArticleImportWithSwitch(knowledgeBaseHomePage);
		KnowledgeBaseTestHelper.uploadFileAndProceed(knowledgeBaseHomePage,
				KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		// Search and verify imported expert user
		String expectedImportExpertUserName = KnowledgeBaseConstants.USERNAME_EXPERTS;
		KnowledgeBaseTestHelper.mapFieldWithDefaultValueInImport(knowledgeBaseHomePage, "ExpertOwnerName",
				expectedImportExpertUserName);
		String actualImportExpertUserName = KnowledgeBaseTestHelper.verifyMappedFieldValue(knowledgeBaseHomePage);
		s.get().assertEquals(expectedImportExpertUserName, actualImportExpertUserName,
				"Test Case failed because actual is not equal as per our expection");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "41", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164118", "164126" }, scriptType = { "Configuration" })
	@Test(priority = 41, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void mappingRichContentEditor_UsingImportFile() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToArticleImportWithSwitch(knowledgeBaseHomePage);
		KnowledgeBaseTestHelper.uploadFileAndProceed(knowledgeBaseHomePage,
				KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		KnowledgeBaseTestHelper.mapFieldInImport(knowledgeBaseHomePage, "StatusCode");
		ReUsableMethods.webSelectByVisibleText(knowledgeBaseHomePage.get().mapping_richContentEditorField, "Age",
				"mapping field");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().nextButton, "next button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().importButton, "import button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().exportButton,"export button");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().recordsUpdate,
						"records Should be Update"),
				"Test SCripts got failed because Record not updated as per required");
		//ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "42", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "164129" }, scriptType = { "Configuration" })
	@Test(priority = 42, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void mappingExpireField_UsingImportFile() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.openToolboxAndNavigateToArticleImportWithSwitch(knowledgeBaseHomePage);
		KnowledgeBaseTestHelper.uploadFileAndProceed(knowledgeBaseHomePage,
				KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		KnowledgeBaseTestHelper.mapFieldInImport(knowledgeBaseHomePage, "ArticleExpireOn");
		ReUsableMethods.webSelectByVisibleText(knowledgeBaseHomePage.get().articleExpireField, "Age", "mapping field");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().nextButton, "next button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().importButton, "import button");
		WebWait.fluentWaitForDisplayed(knowledgeBaseHomePage.get().exportButton);
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().recordsUpdate,
						"records Should be Update"),
				"Test SCripts got failed because Record not updated as per required");
		ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "43", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "254940" }, scriptType = { "Configuration" })
	@Test(priority = 43, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsCommentsCorrect_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
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

	@FrameworkAnnotation(testCasePriority = "44", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "255798", "254897", "255273" }, scriptType = { "Configuration" })
	@Test(priority = 44, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsTableImageYouTubeVideo_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().threeIcon, knowledgeBaseDetailPage.get().addArticle);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addArticle, "add article button");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().imageIcon, "image button");
		HashMap<String, Boolean> ArticalStatus = knowledgeBaseDetailPage.get().verifyTableImageYouTubeVideo();
		s.get().assertTrue(ArticalStatus.get("Line"), "Line is not displayed");
		s.get().assertTrue(ArticalStatus.get("Image"), "Image is not displayed as expected.");
		s.get().assertTrue(ArticalStatus.get("Youtube Video"), "Youtube Video is not displayed as expected.");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "45", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "257692" }, scriptType = { "Configuration" })
	@Test(priority = 34, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsAbleToEditCategory() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"categoryName not matched");
		knowledgeBaseCategoryHomePage.get().toEditCategoryKnowledgebase(categoryName, null);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.KNOWLEDGEBASECATEGORY
				+ ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webClearText(knowledgeBaseCategoryHomePage.get().addSubCategory_enterText, "");
		ReUsableMethods.webEnterText(knowledgeBaseCategoryHomePage.get().addSubCategory_enterText,
				actualKnowledgeBaseName, "enter in tittle text box");
		// s.get().assertTrue(
		// ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
		// actualKnowledgeBaseName));
		s.get().assertAll();
	}

	// this test case marked as manuallly its not working on our port.
	
	@FrameworkAnnotation(testCasePriority = "47", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "308610", "308612" }, scriptType = { "Configuration" })
	@Test(priority = 44, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyUserNameCreatedBy() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
		String expectedModifiedBy = "Last Modified By " + navigationPanel.get().getLoggedInUserName() + " on "
				+ ReUsableMethods.getCurrentdateInFormat("dd/MM/yy");
		String actualModifiedBy = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().modifiedBy,
				expectedModifiedBy);
		String actualModifiedBy_WithoutTime = relatedActivityPage.get().getCreatedByWithoutTime(actualModifiedBy);
		s.get().assertEquals(actualModifiedBy_WithoutTime, expectedModifiedBy, "Modified By do not match");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "45", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "341577", "341575" }, scriptType = { "Configuration" })
	@Test(priority = 45, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsSubCategoryEditAndSave_usingNonadmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"category name not matched");
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "KnowLedge");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "new category button");
		commonProductFunctions.get().waitForLoader();
		knowledgeBaseCategoryHomePage.get()
				.toCreateNewSubCategoryKnowledgebase(categoryName);
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"Test cases get failed because subcategory for admin and non admin not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "46", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "446871" }, scriptType = { "Execution" })
	@Test(priority = 46, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void importAnyFileWithNonAdmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, webNonadminautomation_Ind15, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "toolbox");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().articleImport, "import article");
		ReUsableMethods.switchToChildWindowHandle();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "next button");
		String expectedUploadedFile = KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH;
		knowledgeBaseHomePage.get().uploadFile(KnowledgeBaseConstants.IMPORT_EXCEL_FILE_PATH);
		String actualUploadedFile = Constants.sourceFilePath1 + knowledgeBaseHomePage.get().getUploaedfFileName();
		s.get().assertEquals(actualUploadedFile, expectedUploadedFile, "Uploadfile is not matched as per uploaded");
		ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "47", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "340518", "340521","341574","341573" }, scriptType = { "Configuration" })
	@Test(priority = 47, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsCategoryEditAndSave_usingNonAdmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"category name not matched");
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, webNonadminautomation_Ind15, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "new category button");
		commonProductFunctions.get().waitForLoader();
		knowledgeBaseCategoryHomePage.get().toEditCategoryKnowledgebase(categoryName,
				null);
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"Test cases get failed because subcategory for admin and non admin not matched");
		s.get().assertAll();
	}
}
