package com.businessnext.knowledgebase.testcases;

import java.util.HashMap;

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
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;
import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)
public class KnowledgeBaseTestCasesConfiguration1 extends BaseClass {
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();

	ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryHomePage> knowledgeBaseCategoryHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryCreationPage> knowledgeBaseCategoryCreationPage = new ThreadLocal<>();

	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<String> parentWindow = new ThreadLocal<>();

	static String categoryName;
	static {
		categoryName = KnowledgeBaseConstants.KNOWLEDGEBASECATEGORY + ReUsableMethods.getCurrentdateTime();

	}

	@BeforeMethod(alwaysRun = true)
	public void initializePageObjects() {
		login.set(new webReusableBusinessFunctions());
		navigationPanel.set(new NavigationPanel(DriverManager.getWdriver()));
		knowledgeBaseHomePage.set(new KnowledgeBaseHomePage(DriverManager.getWdriver()));
		knowledgeBaseCreationPage.set(new KnowledgeBaseCreationPage(DriverManager.getWdriver()));
		knowledgeBaseDetailPage.set(new KnowledgeBaseDetailPage(DriverManager.getWdriver()));
		knowledgeBaseCategoryHomePage.set(new KnowledgeBaseCategoryHomePage(DriverManager.getWdriver()));
		knowledgeBaseCategoryCreationPage.set(new KnowledgeBaseCategoryCreationPage(DriverManager.getWdriver()));
		parentWindow.set(ReUsableMethods.getWindow());
		commonProductFunctions.set(new CommonProductFunctions(DriverManager.getWdriver()));
		s.set(new SoftAssert());
	}

	@FrameworkAnnotation(testCasePriority = "1", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"171340", "266272", "212924", "273395", "212950", "259086",
					"322349" }, scriptType = { "Configuration" })
	@Test(priority = 1, enabled = true, groups = { "Sanity" })
	public void createKnowledgeBaseArticle() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		String actualKnowledgeBaseNameGetting = actualKnowledgeBaseName;
		String expectedKnowledgeBaseGetting = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().tittle,
				"Tittle get");
		s.get().assertEquals(actualKnowledgeBaseNameGetting, expectedKnowledgeBaseGetting,
				"Fail Test Scripts Beacause both are not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "2", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"171335", "171559" }, scriptType = { "Configuration" })
	@Test(priority = 2, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void newcategoryCreation() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"categoryName not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "3", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"150061", "210069", "235124", "212923" }, scriptType = { "Configuration" })
	@Test(priority = 3, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void globalSearchBarDisplayed_KnowledgeBase() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		HashMap<String, Boolean> searchBarStatus = knowledgeBaseHomePage.get().verifyglobalSearchBarDisplayed();
		s.get().assertTrue(searchBarStatus.get("globalSearchBar"), "globalSearchBar is not displayed");
		/*
		 * s.get().assertTrue(searchBarStatus.get("globalSearchPicker"),
		 * "global Searc hPicker is not displayed as expected.");
		 */
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "4", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"210510" }, scriptType = { "Configuration" })
	@Test(priority = 4, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void markedUserAsFavouriteOrRemoveFromFavourite() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);

		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
						"add To Favourite"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().removeFromFavourite, "remove From Favourite");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().addToFavourite,
						"remove From Favourite"));
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "5", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"149660", "210514" }, scriptType = { "Configuration" })
	@Test(priority = 5, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void markedUserAsFavourite_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
						"Remove from Favourite"));
//		commonProductFunctions.get().waitForLoader();
//		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "click on cross button");
//		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
//		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
//				knowledgeBaseHomePage.get().globalSearchBar);
//		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
//				"Enter in Search box");
//		commonProductFunctions.get().waitForLoader();
//		knowledgeBaseCategoryHomePage.get().clickKnowledgebase(actualKnowledgeBaseName);
//		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
//		s.get().assertTrue(
//				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
//						"Remove from Favourite"));
//		ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "6", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"149657" }, scriptType = { "Configuration" })
	@Test(priority = 6, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void FunctionalityofFavoriteArticlesButton() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCreationPage.get().createCategory(categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "click on categoryn icon");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "click on cross button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName), "favourite articles");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "7", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"149659" }, scriptType = { "Configuration" })
	@Test(priority = 7, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void Favouritelist_OnHomePage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		// Thread.sleep(2000);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "click on cross button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"knowledge base Name not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "8", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"149665", "149666", "149663", "149664" }, scriptType = { "Configuration" })
	@Test(priority = 8, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void markAnArticleAsFavourite() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
				actualKnowledgeBaseName));
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "9", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"150058", "210075" }, scriptType = { "Configuration" })
	@Test(priority = 9, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void globalSearchBar_KnowledgeBase() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		HashMap<String, Boolean> searchBarStatus = knowledgeBaseHomePage.get().verifyglobalSearchBarDisplayed();
		s.get().assertTrue(searchBarStatus.get("globalSearchBar"), "globalSearchBar is not displayed");
		/*
		 * s.get().assertTrue(searchBarStatus.get("globalSearchPicker"),
		 * "globalSearchPicker is not displayed as expected.");
		 */
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "10", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"152710", "247259" ,"152705"}, scriptType = { "Configuration" })
	@Test(priority = 10, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void recentlyAccessedArticle_OnHomePage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		
		ReUsableMethods.safeClick(knowledgeBaseHomePage.get().recentlyAccessedArticles,
				knowledgeBaseHomePage.get().RecentlyAccessedArticles);
		
		ReUsableMethods.switchToChildWindowHandle();
		
//		s.get().assertEquals(
//				knowledgeBaseHomePage.get().verifyKnowledgebase_OnRecentlyAccessedArticles(actualKnowledgeBaseName),
//				actualKnowledgeBaseName);
//		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().recentlyAccessedArticles,
//				"recent articles");
//		ReUsableMethods.switchToChildWindowHandle();
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsCategoryShownByDefault(actualKnowledgeBaseName),
				"articles are not visible");
		// ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "11", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"152711", "152715", "152707", "152704" }, scriptType = { "Configuration" })
	@Test(priority = 11, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void topRecentlyAccessedArticle() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);

		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().recentlyAccessedArticles,
				"recently access");

		ReUsableMethods.switchToChildWindowHandle();
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsCategoryShownByDefault(actualKnowledgeBaseName),
				"articles are not visible");
		// ReUsableMethods.switchToWindow(parentWindow.get());
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "12", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"162900", "162898", "162899" }, scriptType = { "Configuration" })
	@Test(priority = 12, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void searchArticlesByDescriptionAndTittle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link icon");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
						"Remove from Favourite"));
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "14", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"164384", "164385", "164399" }, scriptType = { "Configuration" })
	@Test(priority = 14, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void ExpertFieldKeyInformation_OnDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		Thread.sleep(2000);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);

		// Thread.sleep(2000);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
				"Enter in Search box");
		knowledgeBaseCategoryHomePage.get().clickKnowledgebase(actualKnowledgeBaseName);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "Click on key information");
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyKeyInformationDisplayed(),
				"Asseration got failed because Key Information text is not Displayed");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "15", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"235128", "235131", "259112", "259099" }, scriptType = { "Configuration" })
	@Test(priority = 15, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void viewCrossButton_onGlobalearch() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		// Thread.sleep(2000);
		commonProductFunctions.get().waitForLoader();

		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);

		commonProductFunctions.get().waitForLoader();

		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link icon");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
						"Remove from Favourite"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "click on cross button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
				"Enter in Search box");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().crossButton, "cross button visible"));
		// Thread.sleep(2000);
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().crossButton,
		// "Click on cross button");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().crossButton, "cross button visible"));
		s.get().assertAll();
	}

	//this test case marked as manually
	
//	@FrameworkAnnotation(testCasePriority = "16", author = { "Rohan Kumar" }, category = {
//			"Knowledge Base" }, TestCaseId = {
//					"150063", "150064", "162905", "150068", "150066", "162906" }, scriptType = { "Configuration" })
//	@Test(priority = 16, enabled = true, groups = { "Regression", "KnowledgeBase" })
//	public void ViewAllResultArticles_OnHomePage() throws Exception {
//
//		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
//		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
//				knowledgeBaseCategoryHomePage, categoryName);
//		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
//				knowledgeBaseCreationPage);
//		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
//		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
//
//		// ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink,
//		// "related link");
//		// ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite,
//		// "add To Favourite");
//		// s.get().assertTrue(
//		// ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
//		// "add To Favourite"));
//		// ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon,
//		// "click on cross button");
//		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
//		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
//				knowledgeBaseHomePage.get().globalSearchBar);
//		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
//				"Enter in Search box");
//		commonProductFunctions.get().waitForLoader();
//		s.get().assertEquals(knowledgeBaseHomePage.get().verifyKnowledgebase(), actualKnowledgeBaseName);
////		String expectedViewAllResults = "View All Results";
////		//
////		String actualViewAllResults = (knowledgeBaseHomePage.get().getViewAllResultsRecord());
////		s.get().assertEquals(expectedViewAllResults, actualViewAllResults,
////				"asseration got failed because actual is not equal as per our expection");
//		s.get().assertAll();
//	}

	@FrameworkAnnotation(testCasePriority = "17", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"162901", "213756", "255510" }, scriptType = { "Configuration" })
	@Test(priority = 17, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifySearchOnlyPublishedArtical_OnHomePage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedLink, "related link");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().addToFavourite, "add To Favourite");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().removeFromFavourite,
						"add To Favourite"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "click on cross button");
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, actualKnowledgeBaseName,
				"Enter in Search box");
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(actualKnowledgeBaseName),
				"Test Case failed because edited knowledge base is not same");
//		commonProductFunctions.get().waitForLoader();
//		knowledgeBaseCategoryHomePage.get().clickKnowledgebase(actualKnowledgeBaseName);
//		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "click on edit Button");
//
//		ReUsableMethods.webClearText(knowledgeBaseCreationPage.get().tittleText, "");
//		String editedActualKnowledgeBaseName = ReUsableMethods.generateRandomText(4);
//		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, editedActualKnowledgeBaseName,
//				"enter edit name in tittle text box");
//		knowledgeBaseDetailPage.get().clickonSaveButton();
//		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
//		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
//		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
//				knowledgeBaseHomePage.get().globalSearchBar);
//		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().globalSearchBar, editedActualKnowledgeBaseName,
//				"Enter in Search box");
//		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(editedActualKnowledgeBaseName),
//				"Test Case failed because edited knowledge base is not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "18", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"203657", "204362", "204368" }, scriptType = { "Configuration" })
	@Test(priority = 18, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyArticles_onDetailPage() throws Exception {

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		HashMap<String, Boolean> ArticalStatus = knowledgeBaseDetailPage.get().verifyAnArticalFields();
		s.get().assertTrue(ArticalStatus.get("newCreateButton"), "CreateNewButton is not displayed");
		s.get().assertTrue(ArticalStatus.get("keyInformation"), "keyInformation is not displayed as expected.");
		s.get().assertTrue(ArticalStatus.get("addToFavourite"), "addToFavourite is not displayed as expected.");
		s.get().assertTrue(ArticalStatus.get("relatedLink"), "relatedLink is not displayed as expected.");
		s.get().assertTrue(ArticalStatus.get("editButton"), "editButton is not displayed as expected.");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "18", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {
					"268755" }, scriptType = { "Configuration" })
	@Test(priority = 19, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyPoppinsFieldOnLexicalEditor() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCreationPage.get().poppins, "poppins text field"),
				" Poppins field is not shown on Lexical Editor");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "20", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "299296", "385457" }, scriptType = { "Configuration" })
	@Test(priority = 20, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void rejectedStatusCode_oHistoryArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		//ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon,
		// "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "tittle text box");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
//		commonProductFunctions.get().waitForLoader();
//		String actualKnowledgeBaseNameGetting = actualKnowledgeBaseName;
//		String expectedKnowledgeBaseGetting = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().tittle,
//				"Tittle get");
//		s.get().assertEquals(actualKnowledgeBaseNameGetting, expectedKnowledgeBaseGetting,
//				"Fail Test Scripts Beacause both are not same");
//		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().publishActionIcon, "publish icon"),
				" publish and review icon is not visible");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "review button");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Commentt is published");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().saveButton, "save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().publishActionIcon, "publish icon"),
				" publish and review icon is not visible");
//		s.get().assertTrue(
//				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().relatedLink, "related icon"),
//				" related icon is not visible");
//		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "click on edit Button");
//		ReUsableMethods.webClearText(knowledgeBaseCreationPage.get().tittleText, "");
//		String editedActualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
//		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, editedActualKnowledgeBaseName,
//				"enter edit name in tittle text box");
//
//		knowledgeBaseDetailPage.get().clickonSaveButton();
//
//		commonProductFunctions.get().waitForLoader();
//
//		s.get().assertTrue(
//				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().publishActionIcon, "publish icon"),
//				" publish and review icon is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().relatedLink, "related icon"),
				" related icon is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "21", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "303314", "305779" }, scriptType = { "Configuration" })
	@Test(priority = 21, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void underReviewStatusCode_oHistoryArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon,
		// "new button");
		// String actualKnowledgeBaseName =
		// KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
		// knowledgeBaseCreationPage);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().checkbox_tag, "click on first record");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().history, "history tab");
		ReUsableMethods.switchToChildWindowHandle();
		String expectedRejectedcode = KnowledgeBaseConstants.STATUS_CODE_UNDER_REVIEWS;
		String expectedUserLastModifier = KnowledgeBaseConstants.USERNAME_EXPERTS;
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().underReviewStatus,
				"under re view status code");
		String actualRejectedCode = ReUsableMethods.WebGetElementText(knowledgeBaseHomePage.get().underReviewStatus,
				"under review status code");
		String actualUserLastModifier = ReUsableMethods.WebGetElementText(knowledgeBaseHomePage.get().lastModifier,
				"last modified by");
		s.get().assertEquals(actualUserLastModifier, expectedUserLastModifier,
				"actual and expected modifier name is not same");
		s.get().assertEquals(actualRejectedCode, expectedRejectedcode,
				"actual and expected Under review code not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "22", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "305770" }, scriptType = { "Configuration" })
	@Test(priority = 22, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void draftStatusCode_oHistoryArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, actualKnowledgeBaseName,
				"tittle text box");
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().history, "history tab");
		ReUsableMethods.switchToChildWindowHandle();
		String expectedRejectedcode = KnowledgeBaseConstants.STATUS_CODE_DRAFT;
		String actualRejectedCode = ReUsableMethods.WebGetElementText(knowledgeBaseHomePage.get().draftStatus,
				"draft status code");
		s.get().assertEquals(actualRejectedCode, expectedRejectedcode, "actual and expected draft code is  not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "23", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "316531", "315156", "330998" }, scriptType = { "Configuration" })
	@Test(priority = 23, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void commentsVisiblityUnderReview() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon,
		// "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "tittle text box");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		String expectedComments = KnowledgeBaseConstants.COMMENTS_PUBLISHED;
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				expectedComments, "commentBox");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button"),
				"save icon is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().cancelButton, "cancel button"),
				"cancel icon is not visible");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().checkbox_tag, "first record");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button");
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().commentIcon, "Comment icon");
		String actualComments = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().comments,
				"get base knowledge address");
		s.get().assertEquals(expectedComments, actualComments, "send for review comments are not shown equal");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "23", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "311780" }, scriptType = { "Configuration" })
	@Test(priority = 23, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void attchmentsShouldNotAppears() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);

		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);

		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseDetailPage.get().attchmentContent);
		s.get().assertTrue(commonProductFunctions.get().verifyIfNoDataExisVisible(),
				" Attachement section is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "25", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "341563", "341565" }, scriptType = { "Configuration" })
	@Test(priority = 25, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void statusIndicatorFieldsNotVisible_whileCloningArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);

		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		
	//	ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().clone, "clone  Icon");
		
		commonProductFunctions.get().clickOnClone();	
		commonProductFunctions.get().waitForLoader();
		
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCreationPage.get().expiredOn, "publish icon"),
				" expire on icon is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCreationPage.get().poppins, "poppins icon"),
				" poppins fields is not visible");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCreationPage.get().keyInformation,
				"key information"), " key information fields is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "26", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "341564" }, scriptType = { "Configuration" })
	@Test(priority = 26, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void statusIndicatorFieldsNotVisible_afterCloningArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
	   //	ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "enter in tittle text box");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().clone, "clone  Icon");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().saveButton, "save  Icon");
		String actualKnowledgeBaseNameGetting = actualKnowledgeBaseName;
		String expectedKnowledgeBaseGetting = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().tittle,
				"Tittle get");
		s.get().assertEquals(actualKnowledgeBaseNameGetting, expectedKnowledgeBaseGetting,
				"Fail Test Scripts Beacause both are not same");
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().keyInformation, "key information"),
				" key information fields is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().publishActionIcon, "publish icon"),
				" publish and review icon is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().relatedLink, "related icon"),
				" related icon is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "27", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "330148","330988" }, scriptType = { "Configuration" })
	@Test(priority = 27, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void shortUserEmailIDVisiblityUnderReview() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
		//ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "tittle text box");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		String expectedComments = KnowledgeBaseConstants.COMMENTS_PUBLISHED;
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon, expectedComments,
				"commentBox");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button"),
				"save icon is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().cancelButton, "cancel button"),
				"cancel icon is not visible");
		commonProductFunctions.get().searchRecordFromListing("Email", "auto5@crmnext.com");
		String expectedShortUserEmailID = KnowledgeBaseConstants.USER_NAME_EMAIL_ID;
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().userEmailID,
				expectedShortUserEmailID);
		String actualdShortUserEmailID = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().userEmailID,
				"User id Name");
		s.get().assertEquals(actualdShortUserEmailID, expectedShortUserEmailID,
				"actual user and expected short user email id is not same");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "28", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "323890", "330993" }, scriptType = { "Configuration" })
	@Test(priority = 28, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void shortUserNotVisiblityUnderReview() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);

		commonProductFunctions.get().waitForLoader();

		// ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
	//	ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "tittle text box");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		String expectedComments = KnowledgeBaseConstants.COMMENTS_PUBLISHED;
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon, expectedComments,
				"commentBox");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button"),
				"save icon is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().cancelButton, "cancel button"),
				"cancel icon is not visible");
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		commonProductFunctions.get().searchRecordFromListing("Short Name", "autoall");
		s.get().assertTrue(commonProductFunctions.get().verifyIfNoDataExisVisible(), "short user name data is visible");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().cancelButton, "cancel button");
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		commonProductFunctions.get().searchRecordFromListing("Employee Code", "36583");
		s.get().assertTrue(commonProductFunctions.get().verifyIfNoDataExisVisible(), "short user code is visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "29", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "385458" }, scriptType = { "Configuration" })
	@Test(priority = 29, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyRelatedActiclesField() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon,
		// "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "enter in tittle text box");
		KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker, "Search Picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().searchTextBox, categoryName,
				"search category name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyIcon, "click on Aplly Button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstRecord_AfterSearchCategory,
				"knowledgeBaseCreationPage.get().applyIcon");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().contentBody, "Knowledgebasebodycontent",
				"enter in content Body");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().description, "Automation Knowledge Base",
				"Knowledgebase");
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseCreationPage.get().articleSearchPicker);
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().articleSearchPicker, "article earch Picker");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().checkbox, "check box");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().okButton, "okk button");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryCreationPage.get().saveIcon, "click on save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().keyInformation, "key information");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().relatedActicle, "related acticle");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().articleRecord, "record fileds"),
				"Article record fileds not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "30", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "341567"}, scriptType = { "Configuration" })
	@Test(priority = 30, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsSubCategoryEditAndSave_usingAdminAndNonadmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"category name not matched");
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "KnowLedge");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "new category button");
		commonProductFunctions.get().waitForLoader();
		knowledgeBaseCategoryHomePage.get().toCreateNewSubCategoryKnowledgebase(categoryName);
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get()
				.verifyIsKnowledgeCategoryExist(categoryName),
				"Test cases get failed because subcategory for admin and non admin not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "31", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "341385", "341430" }, scriptType = { "Configuration" })
	@Test(priority = 31, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyFieldsOnNewEditedPage() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon,
		// "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "enter in tittle text box");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"knowledge base Name not matched");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "edit  Icon");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCreationPage.get().expiredOn, "publish icon"),
				" expire on icon is not visible");
		s.get().assertTrue(
				ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCreationPage.get().poppins, "poppins icon"),
				" poppins fields is not visible");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCreationPage.get().keyInformation,
				"key information"), " key information fields is not visible");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "32", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "341432" }, scriptType = { "Configuration" })
	@Test(priority = 32, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyAllFieldsOnDetailsPage() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		// ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,
		// "home Icon");
		// ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon,
		// "new button");
		// String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME +
		// ReUsableMethods.getCurrentdateTime();
		// ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText,
		// actualKnowledgeBaseName,
		// "enter in tittle text box");
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
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

	@FrameworkAnnotation(testCasePriority = "30", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "455608" }, scriptType = { "Configuration" })
	@Test(priority = 30, enabled = false, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsCategoryEditedByNonadmin_createdByAdmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"category name not matched");
		navigationPanel.get().navigateToLogout();
		// login.get().genericAdminLogin(webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "new category button");
		commonProductFunctions.get().waitForLoader();
		knowledgeBaseCategoryHomePage.get()
				.toEditCategoryKnowledgebase(categoryName, null);
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"Test cases get failed because subcategory for admin and non admin not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "31", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "330997" }, scriptType = { "Configuration" })
	@Test(priority = 31, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void rejectStatusVisiblityUnderReview() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		String actualKnowledgeBaseName = KnowledgeBaseTestHelper.createNewArticleAndEnterTitle(knowledgeBaseHomePage,
				knowledgeBaseCreationPage);
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
		String expectedComments = KnowledgeBaseConstants.COMMENTS_PUBLISHED;
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon, expectedComments,"commentBox");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryCreationPage.get().saveIcon, "save button"),
				"save icon is not visible");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().cancelButton, "cancel button"),
				"cancel icon is not visible");
		commonProductFunctions.get().searchRecordFromListing("Email", "auto5@crmnext.com");
		String expectedShortUserEmailID = KnowledgeBaseConstants.USER_NAME_EMAIL_ID;
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().userEmailID, expectedShortUserEmailID);
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().checkbox_tag, "first record");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().saveButton, "save button");
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.scrollElementToCentreOfScreen(knowledgeBaseHomePage.get().myActiclesSection);
        ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myActiclesSection, "my article section");
        ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().myReviews, "my review section");
        knowledgeBaseDetailPage.get().clickOnDraftKnowledgeBase(actualKnowledgeBaseName);
        ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "review button");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Commentt is published");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().saveButton, "save button");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().commentIcon, "comment icon");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed( knowledgeBaseDetailPage.get().rejectedComment, "reject comment"),
				"Test cases get failed because reject comment is not matched");
		s.get().assertAll();
	}
}