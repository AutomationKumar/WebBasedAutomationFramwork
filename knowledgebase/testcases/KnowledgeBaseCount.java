package com.businessnext.knowledgebase.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.businessnext.knowledgebase.pages.*;
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.common.pages.RelatedActivityPage;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

@Listeners(ListenerClass.class)
public class KnowledgeBaseCount extends BaseClass {

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

	static String categoryName = KnowledgeBaseConstants.KNOWLEDGEBASECATEGORY + ReUsableMethods.getCurrentdateTime();

	@BeforeMethod(alwaysRun = true)
	public void initializePageObjects() {
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

	@FrameworkAnnotation(testCasePriority = "48", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "322384" }, scriptType = { "Configuration" })
	@Test(priority = 48, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyPublishedArticleCountIncrease() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String initialPublishedCount = knowledgeBaseHomePage.get().publishedArticlesCount();
		if (initialPublishedCount == null || initialPublishedCount.trim().isEmpty()) {
			throw new RuntimeException("Published article count is empty");
		}
        int initialCount = Integer.parseInt(initialPublishedCount.trim());
		int expectedCountAfterPublish = initialCount + 1;
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String knowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), knowledgeBaseName, categoryName);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		String finalPublishedCount = knowledgeBaseHomePage.get().publishedArticlesCount();
		if (finalPublishedCount == null || finalPublishedCount.trim().isEmpty()) {
			throw new RuntimeException("Published article count is empty");
		}
		int actualCountAfterPublish = Integer.parseInt(finalPublishedCount.trim());
		s.get().assertEquals(actualCountAfterPublish, expectedCountAfterPublish,
				"Published article count did not increase by 1 after publish");
		s.get().assertAll();
	}
	
	
	@FrameworkAnnotation(testCasePriority = "22", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "241149", "234303", "322367",
					"322370" }, scriptType = { "Configuration" })
	@Test(priority = 22, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyArticleCount_usingDetletedBaseKnowledge() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedArticleCount = knowledgeBaseHomePage.get().getDraftArticlesCount();
		if (expectedArticleCount == null || expectedArticleCount.trim().isEmpty()) {
			throw new RuntimeException("Draft article count is null or empty");
		}
		int expectedArticleCountInt = Integer.parseInt(expectedArticleCount.trim());
		int expectedArticleCountIntAfterDelete = expectedArticleCountInt - 1;
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().countArticleRecord, "Click on article record");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().deleteButton, "Click on delete button");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().deleteButton, "Confirm delete");
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		String actualArticleCount = knowledgeBaseHomePage.get().getDraftArticlesCount();
		if (actualArticleCount == null || actualArticleCount.trim().isEmpty()) {
			throw new RuntimeException("Actual article count is null or empty");
		}
		int actualArticleCountIntAfterDelete = Integer.parseInt(actualArticleCount.trim());
		s.get().assertEquals(actualArticleCountIntAfterDelete, expectedArticleCountIntAfterDelete,
				"Article count after delete is not matching");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "23", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "322394" }, scriptType = { "Configuration" })
	@Test(priority = 23, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIncreaseDraftDecresePublishedWhileEditingArticle() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String draftBeforeStr = knowledgeBaseHomePage.get().getDraftArticlesCount();
		String publishBeforeStr = knowledgeBaseHomePage.get().publishedArticlesCount();
		int draftBefore = Integer.parseInt(draftBeforeStr.trim());
		int publishBefore = Integer.parseInt(publishBeforeStr.trim());
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String articleName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), articleName, categoryName);
		KnowledgeBaseTestHelper.publishArticle(knowledgeBaseDetailPage);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().editButton, "Click on Edit Button");
		knowledgeBaseDetailPage.get().clickonSaveButton();
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		String draftAfterStr = knowledgeBaseHomePage.get().getDraftArticlesCount();
		String publishAfterStr = knowledgeBaseHomePage.get().publishedArticlesCount();
		int draftAfter = Integer.parseInt(draftAfterStr.trim());
		int publishAfter = Integer.parseInt(publishAfterStr.trim());
		s.get().assertEquals(draftAfter, draftBefore + 1, "Draft count did NOT increase by 1 after editing");
		s.get().assertEquals(publishAfter, publishBefore, "Published count should remain same after editing");

		s.get().assertAll();
	}
}