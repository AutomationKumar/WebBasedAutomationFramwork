package com.businessnext.knowledgebase.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.common.pages.CommonProductFunctions;
import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;

public class KnowledgeBaseCategoryHomePage {
	WebDriver wdriver;

	public KnowledgeBaseCategoryHomePage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[@title='New Category']")
	public WebElement newCategory;

	@FindBy(how = How.XPATH, using = "//*[contains(@title,'Knowledge Home')]")
	public WebElement knowledgeHome;

	public String createcategory_pre = "//div[@title='";
	public String createcategory_post = "']";

	public String searchresult_pre = "//div[contains(text(),'";
	public String searchresult_post = "')]";

	public String subcategory_pre = "//div[@title='";
	public String midcategory_post = "']";
	public String subcategory_post = "//ancestor::div[@data-rowid]//descendant::*[local-name()='svg' and @name='icon-custom-menu']";

	@FindBy(how = How.XPATH, using = "//a[@title='Add Sub Category']")
	public WebElement addSubCategory;

	@FindBy(how = How.XPATH, using = "//a[@title='Delete Category']")
	public WebElement deleteSubCategory;

	@FindBy(how = How.XPATH, using = "//input[@data-autoid='SUBJECT_ctrl']")
	public WebElement addSubCategory_enterText;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//a[@title='Edit Category']")
	public WebElement editCategory;

	@FindBy(xpath = "//label[@for='radioVISIBLETO1']")
	public WebElement radioButton;

	@FindBy(xpath = "//a[@title='User']")
	public WebElement userManagement;

	@FindBy(xpath = "//a[@title='Team']")
	public WebElement teamManagement;

	@FindBy(xpath = "//a[@title='Role']")
	public WebElement roleManagement;

	@FindBy(xpath = "//label[@for='radioVISIBLETO2']")
	public WebElement radioButton_All;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='Save']")
	public WebElement saveIcon;

	@FindBy(how = How.XPATH, using = "//input[@data-autoid='SUBJECT_ctrl']")
	public WebElement categoryNameField;

	@FindBy(how = How.XPATH, using = "//a[@title='Manage Category']")
	public WebElement manageCategory;

	@FindBy(how = How.XPATH, using = "//button[@data-autoid='0_button']")
	public WebElement okButton_AfterDeleteCategory;

	// button[@data-autoid='0_button']

	public boolean verifyIsKnowledgeCategoryExist(String param) {
		String actualXpath = createcategory_pre + param + createcategory_post;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		return ReUsableMethods.WebIsElementDisplayed(element.get(0), "Catagory");

	}

	public void clickKnowledgebase(String param) {
		String actualXpath = searchresult_pre + param + searchresult_post;
		List<WebElement> elements = ReUsableMethods.findElementByPath(actualXpath);
		ReUsableMethods.webClickElement(elements.get(0), "Category");
	}

	public String toCreateNewSubCategoryKnowledgebase(String param) {
		String actualXpath = subcategory_pre + param + midcategory_post + subcategory_post;
		List<WebElement> elements = ReUsableMethods.findElementByPath(actualXpath);
		ReUsableMethods.webClickElement(elements.get(0), "Category");
		ReUsableMethods.webClickElement(addSubCategory, "Click on Add Sub Category");
		String addSubCategoryName = "SubCategory";
		String nameActual = addSubCategoryName + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(addSubCategory_enterText, nameActual, "Enter sub category name");
		ReUsableMethods.webClickElement(saveButton, "Click on save button");
		return nameActual;
	}

	public String toEditCategoryKnowledgebase(String param, String button) {

		String actualXpath = subcategory_pre + param + midcategory_post + subcategory_post;
		List<WebElement> elements = ReUsableMethods.findElementByPath(actualXpath);
		ReUsableMethods.webClickElement(elements.get(0), "Category");
		ReUsableMethods.webClickElement(editCategory, "Click on edit Sub Category");
		// ReUsableMethods.webClickElement(elements.get(0), "save button");
		return actualXpath;
	}

	/*
	 * public String createCategory(String categoryname) {
	 * ReUsableMethods.webClickElement(manageCategory, "manage category icon");
	 * ReUsableMethods.webClickElement(newCategory, "Icon home page"); String
	 * nameActual = categoryNameField + ReUsableMethods.getCurrentdateTime();
	 * ReUsableMethods.webEnterText(categoryNameField, categoryname,
	 * "Category text box"); ReUsableMethods.webClickElement(saveIcon,
	 * "click on save button"); return nameActual; }
	 */

	public String toDeleteSubCategoryKnowledgebase(String param) {
		String actualXpath = subcategory_pre + param + midcategory_post + subcategory_post;
		List<WebElement> elements = ReUsableMethods.findElementByPath(actualXpath);
		ReUsableMethods.webClickElement(elements.get(0), "Category");
		ReUsableMethods.webClickElement(deleteSubCategory, "Click on delete Sub Category");
		return actualXpath;
	}

	public void clickonKnowledgeHome() {
		ReUsableMethods.safeClick(knowledgeHome, addSubCategory);
		ReUsableMethods.waitforElementInvisible(knowledgeHome);
	}

	//////////// new test cases///////////////

	@FindBy(xpath = "//label[@for='radioVISIBLETO1']")
	public WebElement selectRadio;

	@FindBy(xpath = "//a[@title='Role']")
	public WebElement role;

	@FindBy(xpath = "//a[@title='Team']")
	public WebElement team;

	@FindBy(xpath = "//a[@title='User']")
	public WebElement user;

	@FindBy(xpath = "//div[@title='Service _Role_Non Admin']")
	public WebElement serviceRoleNonAdmin;

	@FindBy(xpath = "//div[@title='AssignmentTeam1']")
	public WebElement assignmentTeam1;

	@FindBy(xpath = "//div[@title='Auto5']")
	public WebElement userName;

	@FindBy(xpath = "(//div[@role='table'])[2]")
	public WebElement scrollRow;

	public String expandKnowledgeBase_pre = "//div[@title='";
	public String expandKnowledgeBase_post = "']";
	public String expand_post = "//ancestor::div[@data-rowid]//descendant::*[local-name()='svg' and @name='icon-chevron-down']";

	@FindBy(xpath = "//a[@data-autoid='gridHF_VISIBLETO']")
	public WebElement applyButton;

	@FindBy(xpath = "//*[@name='Grid_SearchTextBox']")
	public WebElement searchPermitAccess;
	
	@FindBy(xpath = "//div[@title='Service _Role_Non Admin']")
	public WebElement roleName;
	
	@FindBy(xpath = "//div[@title='Automation Team']")
	public WebElement teamName;

	
	

	public boolean verifyKnowledgebaseCategory(String param) {
		String actualXpath = subcategory_pre + param + midcategory_post;
		List<WebElement> list = ReUsableMethods.findElementByPath(actualXpath);
		if (list.size() > 0) {
			ReUsableMethods.scrollElementToMiddle(list.get(0));
		}
		return ReUsableMethods.isWebElementDisplayed(list);
	}

	public void createCategoryWithPermitAccess(String category, String permitType, String... permitNames)
			throws InterruptedException {
		// Step 1: Navigate to category creation
		ReUsableMethods.webClickElement(manageCategory, "Click on 'Manage Category'");
		ReUsableMethods.webClickElement(newCategory, "Click on 'New Category' button");
		ReUsableMethods.webEnterText(addSubCategory_enterText, category, "Enter category name");
		ReUsableMethods.webClickElement(selectRadio, "Click on radio button");
		switch (permitType.toLowerCase()) {
		case "role":
			ReUsableMethods.webClickElement(role, "Click on 'Role' permit");
			break;
		case "team":
			ReUsableMethods.webClickElement(team, "Click on 'Team' permit");
			break;
		case "user":
			ReUsableMethods.webClickElement(user, "Click on 'User' permit");
			break;
		default:
			throw new IllegalArgumentException("Invalid permit access type: " + permitType);
		}

		ReUsableMethods.webClickElement(applyButton, "Click on 'Apply'");
		new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
		for (String name : permitNames) {
			name = name.trim();
			ReUsableMethods.webClearText(searchPermitAccess, "clear");
			new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
			ReUsableMethods.webEnterText(searchPermitAccess, name, "Enter permit name: " + name);

			String dynamicXpath = createcategory_pre + name + createcategory_post;

			ReUsableMethods.scrollUntilDynamicElementVisible(wdriver, scrollRow, dynamicXpath, 10);
			List<WebElement> elements = ReUsableMethods.findElementByPath(dynamicXpath);

			for (WebElement element : elements) {
				new CommonProductFunctions(DriverManager.getWdriver() ).waitForLoader();
				new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
				ReUsableMethods.webClickElement(element, "Select " + permitType + ": " + name);
			}
		}
		new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
		ReUsableMethods.webClearText(searchPermitAccess, "clear");
		new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
		ReUsableMethods.webClickElement(saveButton, "Click on 'Save' for " + permitType + "(s)");
	}

	// we are using for multi Teams,Users,Roles
	public void createCategoryWithMultiPermitAccess(String category, String permitType, String... permitNames)
			throws InterruptedException {
		ReUsableMethods.webClickElement(manageCategory, "Click on Manage Category");
		ReUsableMethods.webClickElement(newCategory, "Click on New Category");
		ReUsableMethods.webEnterText(addSubCategory_enterText, category, "Enter category name");
		ReUsableMethods.webClickElement(selectRadio, "Click on radio button");

		switch (permitType.toLowerCase()) {
		case "role":
			ReUsableMethods.webClickElement(role, "Click on Role permit");
			break;
		case "team":
			ReUsableMethods.webClickElement(team, "Click on Team permit");
			break;
		case "user":
			ReUsableMethods.webClickElement(user, "Click on User permit");
			break;
		default:
			throw new IllegalArgumentException("Invalid permit access type: " + permitType);
		}

		ReUsableMethods.webClickElement(applyButton, "Click Apply");
		new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();

		for (String name : permitNames) {

			name = name.trim();
			// ReUsableMethods.webClickElement(searchPermitAccess, "search permit");
			// searchPermitAccess.click();
			ReUsableMethods.selectAllAndBackspaceToClear(searchPermitAccess);
			// searchPermitAccess.sendKeys(Keys.CONTROL + "a");
			// searchPermitAccess.sendKeys(Keys.DELETE);

			new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();

			ReUsableMethods.webEnterText(searchPermitAccess, name, "Enter permit name: " + name);

			String dynamicXpath = createcategory_pre + name + createcategory_post;

			ReUsableMethods.scrollUntilDynamicElementVisible(wdriver, scrollRow, dynamicXpath, 10);

			List<WebElement> elements = ReUsableMethods.findElementByPath(dynamicXpath);

			for (WebElement element : elements) {
				ReUsableMethods.webClickElement(element, "Select " + permitType + ": " + name);
			}
		}

		new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
		// searchPermitAccess.click();
		// searchPermitAccess.sendKeys(Keys.CONTROL + "a");
		// searchPermitAccess.sendKeys(Keys.DELETE);
		// ReUsableMethods.webClickElement(saveButton, "Click Save for " + permitType +
		// "(s)");
		ReUsableMethods.selectAllAndBackspaceToClear(searchPermitAccess);
		new CommonProductFunctions(DriverManager.getWdriver() ).waitForLoader();
        new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
		ReUsableMethods.webClickElement(saveButton, "Click on 'Save' for " + permitType + "(s)");
		new CommonProductFunctions(DriverManager.getWdriver()).waitForLoader();
	}

	public boolean clickAndVerifySubCategory_UsingExpandKnowledgebase(String param) {
		String actualXpath = expandKnowledgeBase_pre + param + expandKnowledgeBase_post + expand_post;
		List<WebElement> elements = ReUsableMethods.findElementByPath(actualXpath);
		if (!ReUsableMethods.isWebElementDisplayed(elements)) {
			System.out.println("Element with category '" + param + "' is not visible.");
			return false;
		} else {
			System.out.println("Clicking on the category: '" + param + "'");
			ReUsableMethods.waitUntilTextIsPresent(elements.get(0), "Category");
			ReUsableMethods.webClickElement(elements.get(0), "Category");
			return true;
		}
	}

	public boolean verifyKnowledgebaseCategoryAndSubCategoryExist_usingAdminNodAdmin(String param) {
		String actualXpath = subcategory_pre + param + midcategory_post;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		return ReUsableMethods.WebIsElementDisplayed(element.get(0), "Catagory");
	}

	
	public String subcategory_pre_ = "//div[@title='";
	public String midcategory_post_ = "']";
	public String subcategory_post_ = "/preceding::*[local-name()='svg' and @name='icon-chevron-down']";
	
	public String createAndEditSubCategoryKnowledgebase(String param) {
		String actualXpath=subcategory_pre_ + param + midcategory_post_ + subcategory_post_;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		ReUsableMethods.webClickElement(element.get(0), "list element");
		String expectedXpath = subcategory_pre + param + midcategory_post + subcategory_post;
		List<WebElement> elements = ReUsableMethods.findElementByPath(expectedXpath);
		ReUsableMethods.webClickElement(elements.get(0), "Category");
		ReUsableMethods.webClickElement(addSubCategory, "Click on Add Sub Category");
		String addSubCategoryName = "SubCategory";
		String nameActual = addSubCategoryName + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(addSubCategory_enterText, nameActual, "sub category name");
		ReUsableMethods.webClickElement(saveButton, "save button");
		return nameActual;
	}
	
	public String createEditSubCategory(String parentCategoryName) throws Exception {
		return createAndEditSubCategoryKnowledgebase(parentCategoryName);
	}
}
