package com.businessnext.knowledgebase.testcases;

import com.businessnext.knowledgebase.pages.KnowledgeBaseCategoryHomePage;
import com.businessnext.knowledgebase.pages.KnowledgeBaseConstants;
import com.businessnext.knowledgebase.pages.KnowledgeBaseCreationPage;
import com.businessnext.knowledgebase.pages.KnowledgeBaseDetailPage;
import com.businessnext.knowledgebase.pages.KnowledgeBaseHomePage;
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

/**
 * Knowledge Base Test Helper Class
 * 
 * <p>
 * This class contains all common helper methods used across Knowledge Base test
 * classes. All methods are designed to be thread-safe for parallel test
 * execution.
 * </p>
 * 
 * <p>
 * Key Features:
 * </p>
 * <ul>
 * <li>Thread-safe helper methods for parallel execution</li>
 * <li>Reusable methods to eliminate code duplication</li>
 * <li>Centralized business logic for Knowledge Base operations</li>
 * <li>Easy maintenance - changes in one place affect all tests</li>
 * </ul>
 * 
 * @author Rohan Kumar
 * @version 2.0
 */
public class KnowledgeBaseTestHelper {

	/**
	 * Helper method to login and navigate to Knowledge Base module
	 * 
	 * @param login    ThreadLocal login object
	 * @param webURL   Base URL for the application
	 * @param username Username for login
	 * @param password Password for login
	 * @throws Exception if login or navigation fails
	 */
	public static void loginAndNavigateToKnowledge(ThreadLocal<webReusableBusinessFunctions> login, String webURL,
			String username, String password) throws Exception {
		login.get().genericAdminLogin(webURL, username, password);
		NavigationPanel navigationPanel = new NavigationPanel(DriverManager.getWdriver());
		navigationPanel.NavigateToObject("Knowledge");

		// navigateBackToHomePage(null);
		// webReusableBusinessFunctions.replaceandNavigate("Summary", "KnowLedge");
	}

	/**
	 * Helper method to create category and navigate to home
	 * 
	 * @param knowledgeBaseCreationPage     ThreadLocal creation page object
	 * @param knowledgeBaseDetailPage       ThreadLocal detail page object
	 * @param knowledgeBaseCategoryHomePage ThreadLocal category home page object
	 * @param categoryName                  Name of the category to create
	 * @throws Exception if category creation fails
	 */
	public static void createCategoryAndNavigateHome(ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage,
			ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage,
			ThreadLocal<KnowledgeBaseCategoryHomePage> knowledgeBaseCategoryHomePage, String categoryName)
			throws Exception {
		knowledgeBaseCreationPage.get().createCategory(categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
	}

	/**
	 * Helper method to create new article and enter title
	 * 
	 * @param knowledgeBaseHomePage     ThreadLocal home page object
	 * @param knowledgeBaseCreationPage ThreadLocal creation page object
	 * @return Article name with timestamp
	 * @throws Exception if article creation fails
	 */
	public static String createNewArticleAndEnterTitle(ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage,
			ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage) throws Exception {
		String articleName = ReUsableMethods.generateRandomText(8);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, articleName,
				"enter in tittle text box");
		return articleName;
	}

	/**
	 * Helper method to create a complete knowledge base article
	 * 
	 * @param knowledgeBaseHomePage     ThreadLocal home page object
	 * @param knowledgeBaseCreationPage ThreadLocal creation page object
	 * @param parentWindow              Parent window handle
	 * @param articleName               Name of the article
	 * @param categoryName              Category for the article
	 * @throws Exception if article creation fails
	 */
	public static void createKnowledgeBaseArticle(

			ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage,
			ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage, String parentWindow, String articleName,
			String categoryName) throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, articleName,
				"enter in tittle text box");
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow, categoryName, articleName);
	}

	/**
	 * Helper method to publish a knowledge base article
	 * 
	 * @param knowledgeBaseDetailPage ThreadLocal detail page object
	 * @throws Exception if publishing fails
	 */
	public static void publishArticle(ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage) throws Exception {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(DriverManager.getWdriver());
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "publish Action Icon");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Comment is published");
		knowledgeBaseDetailPage.get().clickonSaveButton();
		commonProductFunctions.waitForLoader();
		// ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
	}

	/**
	 * Helper method to open toolbox and navigate to article import
	 * 
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @throws Exception if navigation fails
	 */
	public static void openToolboxAndNavigateToArticleImport(ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage)
			throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on toolbox");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().articleImport, "Click on article import");
	}

	/**
	 * Helper method to open toolbox and navigate to article import with window
	 * switch
	 *
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @throws Exception if navigation fails
	 */
	public static void openToolboxAndNavigateToArticleImportWithSwitch(
			ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage) throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on toolbox");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().articleImport, "click on import article");
		ReUsableMethods.switchToChildWindowHandle();
	}

	/**
	 * Helper method to open toolbox and navigate to object field mapping
	 *
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @throws Exception if navigation fails
	 */
	public static void openToolboxAndNavigateToObjectFieldMapping(
			ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage) throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().toolBox, "Click on toolbox");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().objectFieldMapping,
				"Click on Object Field Mapping");
		ReUsableMethods.switchToChildWindowHandle();
	}

	/**
	 * Helper method to select category using search picker
	 *
	 * @param knowledgeBaseCreationPage ThreadLocal creation page object
	 * @param categoryName              Category name to select
	 * @throws Exception if selection fails
	 */
	public static void selectCategory(ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage,
			String categoryName) throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().searchPicker, "Search Picker");
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().searchTextBox, categoryName,
				"search category name");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().applyIcon, "click on Apply Button");
		ReUsableMethods.webClickElement(knowledgeBaseCreationPage.get().firstRecord_AfterSearchCategory,
				"first record after search");
	}

	/**
	 * Helper method to upload file and proceed with import
	 *
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @param filePath              Path to the file to upload
	 * @throws Exception if upload fails
	 */
	public static void uploadFileAndProceed(ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage, String filePath)
			throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
		knowledgeBaseHomePage.get().uploadFile(filePath);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().next, "click on next button");
	}

	/**
	 * Helper method to map expert field in import
	 *
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @throws Exception if mapping fails
	 */
	public static void mapExpertFieldInImport(ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage)
			throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().setFileds, "click on fields");
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().search_textbox, "ExpertOwnerName",
				"enter ExpertOwnerName in search text box");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().applyButton, "apply button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().checkBox, "click on check box");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().okButton, "click on okk button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().switchButton, "click on switch button");
	}

	/**
	 * Helper method to map a field in import with custom field name
	 *
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @param fieldName             Name of the field to map
	 * @throws Exception if mapping fails
	 */
	public static void mapFieldInImport(ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage, String fieldName)
			throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().setFileds, "click on fields");
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().search_textbox, fieldName,
				"enter " + fieldName + " in search text box");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().applyButton, "apply button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().checkBox, "click on check box");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().okButton, "click on okk button");
	}

	/**
	 * Helper method to perform complete field mapping with default value for import
	 * This includes mapping the field and setting a default value from search
	 * picker
	 *
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @param fieldName             Name of the field to map
	 * @param defaultValue          Default value to set for the field
	 * @throws Exception if mapping fails
	 */
	public static void mapFieldWithDefaultValueInImport(ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage,
			String fieldName, String defaultValue) throws Exception {
		// Map the field
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().setFileds, "Click on Set Fields");
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().search_textbox, fieldName,
				"Enter " + fieldName + " in search text box");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().applyButton, "Click on Apply button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().checkBox, "Click on checkbox to select field");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().okButton, "Click on OK button");

		// Set default value for the mapped field
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().switchButton, "Click on Set Default button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().searchPicker, "Click on search picker");
		ReUsableMethods.webEnterText(knowledgeBaseHomePage.get().search_textbox, defaultValue,
				"Enter " + defaultValue + " in search text box");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().applyButton, "Click on Apply button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().firstRecord, "Click on first record");
	}

	/**
	 * Helper method to verify mapped field value in import
	 *
	 * @param knowledgeBaseHomePage ThreadLocal home page object
	 * @return String value of the mapped field
	 * @throws Exception if verification fails
	 */
	public static String verifyMappedFieldValue(ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage)
			throws Exception {
		return knowledgeBaseHomePage.get().verifyUserName();
	}

	/**
	 * Helper method to create category with permit access
	 *
	 * @param knowledgeBaseCategoryHomePage ThreadLocal category home page object
	 * @param categoryName                  Name of the category
	 * @param permitType                    Type of permit (User/Role/Team)
	 * @param permitValues                  Values for the permit
	 * @throws Exception if creation fails
	 */
	public static void createCategoryWithPermit(
			ThreadLocal<KnowledgeBaseCategoryHomePage> knowledgeBaseCategoryHomePage, String categoryName,
			String permitType, String... permitValues) throws Exception {
		knowledgeBaseCategoryHomePage.get().createCategoryWithMultiPermitAccess(categoryName, permitType, permitValues);
	}

	/**
	 * Helper method to send article for review
	 *
	 * @param knowledgeBaseDetailPage ThreadLocal detail page object
	 * @throws Exception if sending for review fails
	 */
	public static void sendForReview(ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage) throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().sendForReview, "send for review");
	}

	/**
	 * Helper method to navigate back to home page
	 *
	 * @param knowledgeBaseDetailPage ThreadLocal detail page object
	 * @throws Exception if navigation fails
	 */
	public static void navigateBackToHomePage(ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage)
			throws Exception {
		knowledgeBaseDetailPage.get().clickonbacktoHomePage();
	}

	/**
	 * Helper method to click on manage category
	 *
	 * @param knowledgeBaseCategoryHomePage ThreadLocal category home page object
	 * @throws Exception if click fails
	 */
	public static void clickManageCategory(ThreadLocal<KnowledgeBaseCategoryHomePage> knowledgeBaseCategoryHomePage)
			throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "click on manage category");
	}

	/**
	 * Helper method to select article category for non-user
	 *
	 * @param knowledgeBaseCreationPage ThreadLocal creation page object
	 * @param parentWindow              Parent window handle
	 * @param searchValue               Value to search
	 * @param categoryName              Category name
	 * @throws Exception if selection fails
	 */
	public static void selectArticleCategoryForNonUser(ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage,
			String parentWindow, String searchValue, String categoryName) throws Exception {
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow, searchValue, categoryName);
	}
}
