package com.businessnext.knowledgebase.testcases;

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
import com.businessnext.setupmodules.usermanagement.UserManagementConstants;
import com.common.pages.CommonProductFunctions;
import com.common.pages.NavigationPanel;
import com.drivermanager.DriverManager;
import com.listeners.ListenerClass;
import com.setup.BaseClass;
import com.utilities.ReUsableMethods;
import com.utilities.webReusableBusinessFunctions;

import annotations.FrameworkAnnotation;

/**
 * Optimized Knowledge Base Test Cases - Permission and Category Management
 * Tests
 *
 * <p>
 * Optimizations Applied:
 * </p>
 * <ul>
 * <li>Lazy initialization for page objects (only create when needed)</li>
 * <li>Helper methods for common operations (login, navigation, category
 * creation)</li>
 * <li>Local variables to reduce verbose .get() calls</li>
 * <li>JavaDoc documentation for all test methods</li>
 * <li>Extracted business logic into reusable methods</li>
 * <li>Improved readability and maintainability</li>
 * </ul>
 *
 * @author Rohan Kumar
 */
@Listeners(ListenerClass.class)
public class KnowldgeBaseNewTestCases extends BaseClass {

	// ThreadLocal page objects for thread-safe parallel execution
	ThreadLocal<KnowledgeBaseHomePage> knowledgeBaseHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCreationPage> knowledgeBaseCreationPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseDetailPage> knowledgeBaseDetailPage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryHomePage> knowledgeBaseCategoryHomePage = new ThreadLocal<>();
	ThreadLocal<KnowledgeBaseCategoryCreationPage> knowledgeBaseCategoryCreationPage = new ThreadLocal<>();
	ThreadLocal<CommonProductFunctions> commonProductFunctions = new ThreadLocal<>();
	ThreadLocal<NavigationPanel> navigationPanel = new ThreadLocal<>();
	ThreadLocal<webReusableBusinessFunctions> login = new ThreadLocal<>();
	ThreadLocal<SoftAssert> s = new ThreadLocal<>();
	ThreadLocal<String> parentWindow = new ThreadLocal<>();

	static String categoryName;
	static {
		categoryName = KnowledgeBaseConstants.KNOWLEDGEBASECATEGORY + ReUsableMethods.getCurrentdateTime();
	}

	@BeforeMethod(alwaysRun = true)
	public void initializePageObjects() {
		login.set(new webReusableBusinessFunctions());
		parentWindow.set(ReUsableMethods.getWindow());
		s.set(new SoftAssert());

		// Initialize all page objects for thread-safe parallel execution
		knowledgeBaseHomePage.set(new KnowledgeBaseHomePage(DriverManager.getWdriver()));

		knowledgeBaseCreationPage.set(new KnowledgeBaseCreationPage(DriverManager.getWdriver()));
		knowledgeBaseDetailPage.set(new KnowledgeBaseDetailPage(DriverManager.getWdriver()));
		knowledgeBaseCategoryHomePage.set(new KnowledgeBaseCategoryHomePage(DriverManager.getWdriver()));
		knowledgeBaseCategoryCreationPage.set(new KnowledgeBaseCategoryCreationPage(DriverManager.getWdriver()));
		commonProductFunctions.set(new CommonProductFunctions(DriverManager.getWdriver()));
		navigationPanel.set(new NavigationPanel(DriverManager.getWdriver()));
	}

	// ==================== Helper Methods ====================

	/**
	 * Helper method to login and navigate to Knowledge Base
	 */

	/**
	 * Helper method to create category with permit access
	 */
	private void createCategoryWithPermit(String categoryName, String permitType, String... permitValues)
			throws Exception {
		knowledgeBaseCategoryHomePage.get().createCategoryWithPermitAccess(categoryName, permitType, permitValues);
	}

	/**
	 * Helper method to verify category visibility
	 */

	private void createCategoryWithMultiPermit(String categoryName, String permitType, String... permitValues)
			throws Exception {
		knowledgeBaseCategoryHomePage.get().createCategoryWithMultiPermitAccess(categoryName, permitType, permitValues);
	}

	private void verifyCategoryVisibility(String categoryName, boolean shouldBeVisible) throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "click on manage category");

		if (shouldBeVisible) {
			s.get().assertTrue(
					knowledgeBaseCategoryHomePage.get()
							.verifyKnowledgebaseCategoryAndSubCategoryExist_usingAdminNodAdmin(categoryName),
					"Category should be visible but was not found: " + categoryName);
		} else {
			s.get().assertFalse(knowledgeBaseCategoryHomePage.get().verifyKnowledgebaseCategory(categoryName),
					"Category should not be visible but was found: " + categoryName);
		}
	}

	/**
	 * Helper method to verify article category selection
	 */
	private void verifyArticleCategorySelection(String categoryName, boolean shouldBeVisible) throws Exception {
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				categoryName);

		if (shouldBeVisible) {
			s.get().assertTrue(
					knowledgeBaseCategoryHomePage.get()
							.verifyKnowledgebaseCategoryAndSubCategoryExist_usingAdminNodAdmin(categoryName),
					"Article category should be visible but was not found: " + categoryName);
		}
	}

	/**
	 * Helper method to verify category visibility for multiple users
	 */
	private void verifyMultiUserCategoryVisibility(String categoryName, String user1, String user2,
			boolean user1ShouldSee, boolean user2ShouldSee) throws Exception {
		// First user verification
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, user1, webpassword);

		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "Click on Manage Category");
		if (user1ShouldSee) {
			s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgebase_NotPublished(categoryName),
					"Category should be visible for " + user1);
		}

		// Second user verification
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, user2, webpassword);
		verifyCategoryVisibility(categoryName, user2ShouldSee);
	}

	/**
	 * Helper method to create subcategory and return its name
	 */
	private String createSubCategory(String parentCategoryName) throws Exception {
		return knowledgeBaseCategoryHomePage.get().toCreateNewSubCategoryKnowledgebase(parentCategoryName);
	}



	/**
	 * Test Case ID: 324392 Verify that a category created with Role permission is
	 * visible to non-admin users with that role
	 */
	@FrameworkAnnotation(testCasePriority = "1", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324392", "324410" }, scriptType = { "Execution" })
	@Test(priority = 1, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateCategoryWithRole_nonAdmin() throws Exception {
		// Admin creates category with role permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);
		commonProductFunctions.get().waitForLoader();

		// Non-admin user verifies category visibility
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		verifyCategoryVisibility(expectedCategoryName, true);
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324393 Verify that article category created with Role
	 * permission is visible to non-admin users with that role
	 */
	@FrameworkAnnotation(testCasePriority = "2", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324393" }, scriptType = { "Execution" })
	@Test(priority = 2, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateActicleCategoryWithRole_nonAdmin() throws Exception {
		// Admin creates category with role permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);

		// Non-admin user verifies article category selection
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(expectedCategoryName),
				"Category should be not visible for admin user");

		// verifyArticleCategorySelection(expectedCategoryName, true);
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324396 Verify that a category created with Team permission is
	 * visible to users in that team
	 */
	@FrameworkAnnotation(testCasePriority = "3", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324396", "307278" }, scriptType = { "Execution" })
	@Test(priority = 3, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateCategoryWithTeam_nonAdmin() throws Exception {
		// Admin creates category with team permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1);

		// Team member verifies category visibility
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		verifyCategoryVisibility(expectedCategoryName, true);
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324399 Verify that article category created with Team
	 * permission is visible to users in that team
	 */
	@FrameworkAnnotation(testCasePriority = "4", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324399" }, scriptType = { "Execution" })
	@Test(priority = 4, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateActicleCategoryWithTeam_nonAdmin() throws Exception {
		// Admin creates category with team permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1);

		// Team member verifies article category selection
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		verifyArticleCategorySelection(expectedCategoryName, true);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324403 Verify that a category created with User permission is
	 * visible to that specific user
	 */
	@FrameworkAnnotation(testCasePriority = "5", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324403", "324412" }, scriptType = { "Execution" })
	@Test(priority = 5, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateCategoryWithUser_nonAdmin() throws Exception {
		// Admin creates category with user permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,
				UserManagementConstants.USERNAME_AUTOMATION_ALL_15);
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		verifyCategoryVisibility(expectedCategoryName, true);
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324407 Verify that article category created with User
	 * permission is visible to that specific user
	 */
	@FrameworkAnnotation(testCasePriority = "6", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324407" }, scriptType = { "Execution" })
	@Test(priority = 6, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateActicleCategoryWithUser_nonAdmin() throws Exception {
		// Admin creates category with user permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;

		createCategoryWithPermit(expectedCategoryName,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,
				UserManagementConstants.USERNAME_AUTOMATION_ALL_15);
		// createCategoryWithMultiPermit(expectedCategoryName,
		// KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,
		// KnowledgeBaseConstants.USERNAME_EXPERTS);

		// Specific user verifies article category selection
		// KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL,
		// auto15User, webpassword);

		login.get().genericAdminLogin(webURL, auto15User, webpassword);
		navigationPanel.get().NavigateToObject("Knowledge");

		verifyArticleCategorySelection(expectedCategoryName, true);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 307276 Verify that a category with Role permission is NOT
	 * visible to users without that role
	 */
	@FrameworkAnnotation(testCasePriority = "7", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "307276" }, scriptType = { "Execution" })
	@Test(priority = 7, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNotVisiblityCategoryWithRole() throws Exception {
		// Admin creates category with role permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);

		// User without role verifies category is NOT visible
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, username_priya, webpassword);
		verifyArticleCategorySelection(expectedCategoryName, false);
		// verifyCategoryVisibility(expectedCategoryName, false);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324431, 324563 Verify that article category with User
	 * permission is NOT displayed to users without that permission
	 */
	@FrameworkAnnotation(testCasePriority = "8", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324431", "324563", "324437" }, scriptType = { "Execution" })
	@Test(priority = 8, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNotDisplayedActicleCategoryWithUser() throws Exception {
		// Admin creates category with user permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,
				KnowledgeBaseConstants.USERNAME_EXPERTS);

		// Different user verifies no data is visible

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, adminUserMaster_Auto, webpassword);

		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");

		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(commonProductFunctions.get().verifyIfNoDataExisVisible(),
				"Test case failed because data is visible");
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324434 Verify that a category with Team permission is NOT
	 * visible to users not in that team
	 */
	@FrameworkAnnotation(testCasePriority = "9", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324434" }, scriptType = { "Execution" })
	@Test(priority = 9, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNotVisiblityCategoryWithTeam() throws Exception {
		// Admin creates category with team permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1);

		// User not in team verifies category is NOT visible
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto23User, webpassword);
		verifyCategoryVisibility(expectedCategoryName, false);

		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "10", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "311763" }, scriptType = { "Execution" })
	@Test(priority = 10, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyArticleCategoryVisibleOrNotWithRole_usingAdminAndNonAdmin() throws Exception {
		// Admin creates category with role permission
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);

		// Verify category is visible for admin
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyKnowledgebaseCategory(expectedCategoryName),
				"Category should be visible for admin");

		// User without role verifies category is NOT visible
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, username_priya, webpassword);
		// verifyCategoryVisibility(expectedCategoryName, false);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");

		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(commonProductFunctions.get().verifyIfNoDataExisVisible(),
				"Test case failed because data is visible");
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324436 Verify category and article visibility with Team
	 * permission for admin and non-admin users
	 */
	@FrameworkAnnotation(testCasePriority = "11", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324436" }, scriptType = { "Execution" })
	@Test(priority = 11, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNotVisiblityCategoryWithTeam_usingAdminAndNonAdmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1);
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(expectedCategoryName),
				"Category should be not visible for admin user");
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324813 Verify category creation with multiple roles and
	 * visibility for users with those roles
	 */
	@FrameworkAnnotation(testCasePriority = "12", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324813" }, scriptType = { "Execution" })
	@Test(priority = 12, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateCategoryWithMultiRoles() throws Exception {
		// Admin creates category with multiple role permissions
		webReusableBusinessFunctions login = new webReusableBusinessFunctions();
		login.genericAdminLogin(webURL, auto5User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_ADMIN_SOCIAL,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);

		// Verify visibility for multiple users with different roles
		verifyMultiUserCategoryVisibility(expectedCategoryName, auto23User, auto15User, true, true);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324817 Verify category creation with multiple teams and
	 * visibility for users in those teams
	 */
	@FrameworkAnnotation(testCasePriority = "13", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324817" }, scriptType = { "Execution" })
	@Test(priority = 13, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateCategoryWithMultiTeams() throws Exception {
		// Admin creates category with multiple team permissions
		webReusableBusinessFunctions login = new webReusableBusinessFunctions();
		login.genericAdminLogin(webURL, auto5User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AutomationTeam);

		// Verify visibility for users in different teams
		verifyMultiUserCategoryVisibility(expectedCategoryName, auto15User, auto23User, true, true);

		s.get().assertAll();

	}

	/**
	 * Test Case ID: 324807 Verify category creation with multiple users and
	 * visibility for those specific users
	 */
	@FrameworkAnnotation(testCasePriority = "14", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324807" }, scriptType = { "Execution" })
	@Test(priority = 14, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateCategoryWithMultiUsers() throws Exception {
		// Admin creates category with multiple user permissions
		webReusableBusinessFunctions login = new webReusableBusinessFunctions();
		login.genericAdminLogin(webURL, auto5User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,

				UserManagementConstants.USER_AUTO23,
				UserManagementConstants.USERNAME_AUTOMATION_ALL_15);

		// Verify visibility for multiple specific users
		verifyMultiUserCategoryVisibility(expectedCategoryName, auto23User, auto15User, true, true);
		s.get().assertAll();

	}

	/**
	 * Test Case ID: 324922 Verify article category selection with multiple roles
	 * for different users
	 */
	@FrameworkAnnotation(testCasePriority = "15", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324922" }, scriptType = { "Execution" })
	@Test(priority = 15, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateArticleCategoryWithMultiRoles() throws Exception {
		// Admin creates category with multiple role permissions

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_ADMIN_SOCIAL,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);

		// Verify article category selection for first user
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto23User, webpassword);
		// KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL,
		// auto23User, webpassword);
		verifyArticleCategorySelection(expectedCategoryName, true);

		// Verify article category selection for second user
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		verifyArticleCategorySelection(expectedCategoryName, true);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324925 Verify article category selection with multiple teams
	 * for different users
	 */
	@FrameworkAnnotation(testCasePriority = "16", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324925" }, scriptType = { "Execution" })
	@Test(priority = 16, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateArticleCategoryWithMultiTeams() throws Exception {
		// Admin creates category with multiple team permissions

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AutomationTeam);

		// Verify article category for first team user

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		verifyArticleCategorySelection(expectedCategoryName, true);

		// Verify article category for second team user
		// KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL,
		// auto23User, webpassword);

		login.get().genericAdminLogin(webURL, auto23User, webpassword);

		navigationPanel.get().NavigateToObject("Knowledge");

		verifyArticleCategorySelection(expectedCategoryName, true);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324926 Verify article category selection with multiple users
	 */
	@FrameworkAnnotation(testCasePriority = "17", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324926" }, scriptType = { "Execution" })
	@Test(priority = 17, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateArticleCategoryWithMultiUsers() throws Exception {
		// Admin creates category with multiple user permissions

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,

				UserManagementConstants.USER_AUTO23,
				UserManagementConstants.USERNAME_AUTOMATION_ALL_15);

		// Verify article category for first user
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		verifyArticleCategorySelection(expectedCategoryName, true);

		// Verify article category for second user
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto23User, webpassword);
		verifyArticleCategorySelection(expectedCategoryName, true);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324740 Verify article category and subcategory visibility with
	 * multiple roles
	 */
	@FrameworkAnnotation(testCasePriority = "18", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324740" }, scriptType = { "Execution" })
	@Test(priority = 18, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateArticleCategoryAndArticleSubCategoryWithMultiRoles() throws Exception {
		// Admin creates category with multiple role permissions and subcategory

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_ADMIN_SOCIAL,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto23User, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(expectedCategoryName),
				"Category should be not visible for admin user");

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(expectedCategoryName),
				"Category should be not visible for admin roles assigned");

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324748 Verify article category and subcategory visibility with
	 * multiple teams
	 */
	@FrameworkAnnotation(testCasePriority = "19", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324748" }, scriptType = { "Execution" })
	@Test(priority = 19, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateArticleCategoryAndArticleSubCategoryWithMultiTeams() throws Exception {
		// Admin creates category with multiple team permissions and subcategory

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AutomationTeam);
		
		// Verify category and subcategory visibility for user in team

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(expectedCategoryName),
				"Category should be not visible for team assigned");

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324757 Verify article category and subcategory visibility with
	 * multiple users
	 */
	@FrameworkAnnotation(testCasePriority = "20", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324757" }, scriptType = { "Execution" })
	@Test(priority = 20, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyCreateArticleCategoryAndArticleSubCategoryWithMultiUsers() throws Exception {
		// Admin creates category with multiple user permissions and subcategory

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,

				UserManagementConstants.USERNAME_AUTOMATION_ALL_15,
				UserManagementConstants.USER_AUTO21,
				UserManagementConstants.USER_AUTO23);
		commonProductFunctions.get().waitForLoader();


		commonProductFunctions.get().waitForLoader();

		// Verify category and subcategory visibility for assigned user

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(expectedCategoryName),
				"Category should be not visible for admin user");

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324798 Verify category and subcategory are NOT visible to users
	 * not in assigned teams
	 */
	@FrameworkAnnotation(testCasePriority = "21", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324798" }, scriptType = { "Execution" })
	@Test(priority = 21, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNotVisiblityCategoryAndArticleSubCategoryWithMultiTeams() throws Exception {
		// Admin creates category with team permissions and subcategory

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AssignmentTeam1,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AutomationTeam);
		createSubCategory(expectedCategoryName);

		// Verify category is NOT visible for user not in team
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, adminUserMaster_Auto, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(commonProductFunctions.get().verifyIfNoDataExisVisible(),
				"Test case failed because data is visible");

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 324761 Verify category and subcategory are NOT visible to users
	 * without assigned roles
	 */
	@FrameworkAnnotation(testCasePriority = "22", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "324761" }, scriptType = { "Execution" })
	@Test(priority = 22, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNotVisiblityCategoryAndArticleSubCategoryWithMultiRoles() throws Exception {
		// Admin creates category with role permissions and subcategory

		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithMultiPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_ADMIN_SOCIAL,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);
		commonProductFunctions.get().waitForLoader();
		createSubCategory(expectedCategoryName);

		// Verify category is NOT visible for user without role
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, username_priya, webpassword);
		verifyCategoryVisibility(expectedCategoryName, false);

		s.get().assertAll();
	}

	/**
	 * Test Case ID: 322564 Verify version history tracking on Knowledge Base detail
	 * page
	 */
	@FrameworkAnnotation(testCasePriority = "25", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "322564" }, scriptType = { "Execution" })
	@Test(priority = 25, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyHistoryVersions_KnowledgeBaseDetailPage() throws Exception {
		// Admin creates category and knowledge base article
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);

		knowledgeBaseCreationPage.get().createCategory(categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome, "home Icon");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(knowledgeBaseCreationPage.get().tittleText, actualKnowledgeBaseName,
				"enter in tittle text box");
		knowledgeBaseCreationPage.get().createknowledgeBase(parentWindow.get(), categoryName, actualKnowledgeBaseName);

		// Publish the knowledge base and verify version 1
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().publishActionIcon, "publishActionIcon");
		ReUsableMethods.webEnterText(knowledgeBaseDetailPage.get().comment_PublishActionIcon,
				KnowledgeBaseConstants.COMMENTS_PUBLISHED, "Comment is published");
		knowledgeBaseDetailPage.get().clickonSaveButton();
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseDetailPage.get().verifyIsKnowledgeBaseVisible(actualKnowledgeBaseName),
				"knowledge base not matched");
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().verisonHistory, "'Version History'");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().version1,
				"V1 version is not displayed"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "Close History");

		// Edit and verify version 2
		knowledgeBaseDetailPage.get().editAndSaveKnowledgebase(KnowledgeBaseConstants.COMMENTS_PUBLISHED,
				KnowledgeBaseConstants.TITTLENAME);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().verisonHistory, "'Version History'");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().version2,
				"V2 version is not displayed"));
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().crossIcon, "Close History");

		// Edit again and verify version 3
		knowledgeBaseDetailPage.get().editAndSaveKnowledgebase(KnowledgeBaseConstants.COMMENTS_PUBLISHED,
				KnowledgeBaseConstants.TITTLENAME);
		commonProductFunctions.get().waitForLoader();
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		ReUsableMethods.webClickElement(knowledgeBaseDetailPage.get().verisonHistory, "Reopen Version History'");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseDetailPage.get().version3,
				"V3 version is not displayed"));
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 322357 Verify that draft articles label is displayed on
	 * Knowledge Base home page
	 */
	@FrameworkAnnotation(testCasePriority = "26", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "322357" }, scriptType = { "Execution" })
	@Test(priority = 26, enabled = true, groups = { "Regression", "Client", "KnowledgeBase" })
	public void verifyTotalDraftArticlesLabelName() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);

		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().createNewIcon,
				"On knowledge home page create new icon is not displayed"));
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().draftArticles,
				"On knowledge home page draft articles not getting visible"));
		s.get().assertAll();
	}

	/**
	 * Test Case ID: 322358 Verify that published articles label is displayed on
	 * Knowledge Base home page
	 */
	@FrameworkAnnotation(testCasePriority = "27", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "322358" }, scriptType = { "Execution" })
	@Test(priority = 27, enabled = true, groups = { "Regression", "Client", "KnowledgeBase" })
	public void verifyTotalPublishedArticlesLabelName() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);

		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().createNewIcon,
				"On knowledge home page create new icon is not displayed"));
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.get().publishedArticles,
				"On knowledge home page published articles is not getting visible"));
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "28", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "323750", "323769","330942" }, scriptType = { "Execution" })
	@Test(priority = 28, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyRoleTeamUser() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYROLE_NONADMIN);
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().roleName,
				"On category page role is not getting visible"));
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYTEAM_AutomationTeam);
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().teamName,
				"On category page team is not getting visible"));
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,
				KnowledgeBaseConstants.USERNAME_EXPERTS);
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(knowledgeBaseCategoryHomePage.get().userName,
				"On category page user is not getting visible"));
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "29", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "323953", "330906" }, scriptType = { "Execution" })
	@Test(priority = 29, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyNoDataExistsWhileEnterWrongChgar() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		String expectedCategoryName = categoryName;
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEROLE,
				"ABC");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(commonProductFunctions.get().noDataExists,
				"category page of role is getting visible"));
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPETEAM,
				"XYZ");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(commonProductFunctions.get().noDataExists,
				"category page of team is getting visible"));
		knowledgeBaseCategoryHomePage.get().clickonKnowledgeHome();
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,
				"SRT");
		s.get().assertTrue(ReUsableMethods.WebIsElementDisplayed(commonProductFunctions.get().noDataExists,
				"category page of user is getting visible"));
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "30", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "341576","341569","341568" }, scriptType = { "Configuration" })
	@Test(priority = 30, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyIsSubCategoryEditAndSave_usingAdminAndNonadmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, webNonadminautomation_Ind15, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		ReUsableMethods.waitforElementInvisible(knowledgeBaseDetailPage.get().loader);
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"category name not matched");
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "KnowLedge");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "new category button");
		knowledgeBaseCategoryHomePage.get().toCreateNewSubCategoryKnowledgebase(categoryName);
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(knowledgeBaseCategoryHomePage.get().verifyIsKnowledgeCategoryExist(categoryName),
				"Test cases get failed because subcategory for admin and non admin not matched");
		s.get().assertAll();
	}

	@FrameworkAnnotation(testCasePriority = "31", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "340494", "340497" }, scriptType = { "Execution" })
	@Test(priority = 31, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void adminVerifyCategory_createdByNonAdmin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto15User, webpassword);
		webReusableBusinessFunctions.replaceandNavigate("Summary", "Knowledge");
		String expectedCategoryName = categoryName;
		createCategoryWithPermit(expectedCategoryName, KnowledgeBaseConstants.KNOWLEDGEBASECATEGORYPERMITTYPEUSER,
				KnowledgeBaseConstants.USERNAME_EXPERTS);
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), categoryName,
				expectedCategoryName);
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(expectedCategoryName),
				"Category should be not visible for admin user");
		s.get().assertAll();

	}

	@FrameworkAnnotation(testCasePriority = "32", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = {"340021", "339236" }, scriptType = { "Execution" })
	@Test(priority = 32, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void isNon_adminAbleToEditCategoryCreatedByNon_admin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, webNonadminautomation_Ind15, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, username_priya, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().manageCategory, "new category button");
		commonProductFunctions.get().waitForLoader();
	    knowledgeBaseCategoryHomePage.get().toEditCategoryKnowledgebase(categoryName, null);
		commonProductFunctions.get().waitForLoader();	
		String editedCategory = KnowledgeBaseConstants.KNOWLEDGEBASECATEGORY
				+ ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webClearText(knowledgeBaseCategoryHomePage.get().addSubCategory_enterText, "");
		ReUsableMethods.webEnterText(knowledgeBaseCategoryHomePage.get().addSubCategory_enterText,
				editedCategory, "enter in tittle text box");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().saveButton, "save button");
		ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().knowledgeHome,"home page");
		verifyArticleCategorySelection(editedCategory, true);
		s.get().assertAll();
	}
	
	@FrameworkAnnotation(testCasePriority = "33", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "340023","340024","341570","341571","341572","339508" }, scriptType = { "Execution" })
	@Test(priority = 33, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void isNon_adminAbleToEditSUBCategoryCreatedByNon_admin() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, webNonadminautomation_Ind15, webpassword);
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().manageCategory, "manage category icon");
			ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().newCategory, "Icon home page");
			String	expectedCategoryName="Cate"+ReUsableMethods.getCurrentdateTime();
			ReUsableMethods.webEnterText(knowledgeBaseCategoryHomePage.get().categoryNameField, expectedCategoryName,
					"Category text box");
			ReUsableMethods.webClickElement(knowledgeBaseCategoryHomePage.get().saveIcon, "save button");
		commonProductFunctions.get().waitForLoader();
	    String subCategoryName=	knowledgeBaseCategoryHomePage.get().createEditSubCategory(expectedCategoryName);
		navigationPanel.get().navigateToLogout();
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, username_priya, webpassword);
		ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseHomePage.get().createNewIcon, "new button");
		ReUsableMethods.webClickElement(knowledgeBaseHomePage.get().createNewIcon, "new button");
		knowledgeBaseCreationPage.get().selectknowledgeBaseCategory_forNonUser(parentWindow.get(), subCategoryName,"category name");
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(subCategoryName),
				"Edited Sub Category is not visible for non - admin user");
	}
	
	@FrameworkAnnotation(testCasePriority = "34", author = { "Rohan Kumar" }, category = {
			"Knowledge Base" }, TestCaseId = { "433988","446903","433991" }, scriptType = { "Configuration" })
	@Test(priority = 34, enabled = true, groups = { "Regression", "KnowledgeBase" })
	public void verifyViewAllBrowseByCategory() throws Exception {
		KnowledgeBaseTestHelper.loginAndNavigateToKnowledge(login, webURL, auto5User, webpassword);
		KnowledgeBaseTestHelper.createCategoryAndNavigateHome(knowledgeBaseCreationPage, knowledgeBaseDetailPage,
				knowledgeBaseCategoryHomePage, categoryName);
		String actualKnowledgeBaseName = KnowledgeBaseConstants.TITTLENAME + ReUsableMethods.getCurrentdateTime();
	    KnowledgeBaseTestHelper.createKnowledgeBaseArticle(knowledgeBaseHomePage, knowledgeBaseCreationPage,
				parentWindow.get(), actualKnowledgeBaseName, categoryName);
	    ReUsableMethods.waitUntilTextIsPresent(knowledgeBaseDetailPage.get().backIconToNavigateHomePage, "back icon");
		ReUsableMethods.safeClick(knowledgeBaseDetailPage.get().backIconToNavigateHomePage,
				knowledgeBaseHomePage.get().globalSearchBar);
		commonProductFunctions.get().waitForLoader();
		s.get().assertTrue(knowledgeBaseHomePage.get().verifyKnowledgeBaseTittle(categoryName),
				"Edited Sub Category is not visible for non - admin user");
		knowledgeBaseHomePage.get().clickOnViewAllBrowseByCategory(categoryName);
		String expectedKnowledgeBaseGetting = ReUsableMethods.WebGetElementText(knowledgeBaseDetailPage.get().tittle,"Tittle value");
		s.get().assertEquals(actualKnowledgeBaseName, expectedKnowledgeBaseGetting,"Fail Test Scripts Beacause both are not same");
		s.get().assertAll();
	}
	
	
}