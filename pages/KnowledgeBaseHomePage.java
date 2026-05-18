package com.businessnext.knowledgebase.pages;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.utilities.ReUsableMethods;
import com.utilities.WebWait;

public class KnowledgeBaseHomePage {

	WebDriver wdriver;

	public KnowledgeBaseHomePage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@title='Draft Articles']")
	public WebElement draftArticles;

	@FindBy(how = How.XPATH, using = "//div[@title='Published Articles']")
	public WebElement publishedArticles;

	@FindBy(how = How.XPATH, using = "//*[contains(@title,'Create New')]")
	public WebElement createNewIcon;

	@FindBy(how = How.XPATH, using = "(//div[normalize-space()='Favourite Article'])[2]")
	public WebElement favouriteArticlesIcon;

	@FindBy(how = How.XPATH, using = "//*[@title='Manage Category' and normalize-space()='Manage Category']")
	public WebElement manageCategory;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Recently Accessed Article')]")
	public WebElement recentlyAccessedArticles;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Find articles, help and advice..']")
	public WebElement globalSearchBar;

	@FindBy(how = How.XPATH, using = "(//*[local-name()='svg' and @name='icon-search'])[2]")
	public WebElement globalSearchPicker;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='Title_0']/span")
	public WebElement RecentlyAccessedArticles;

	@FindBy(how = How.XPATH, using = "//*[local-name()='svg' and @name='icon-cross']//parent::div")
	public WebElement crossButton;

	@FindBy(how = How.XPATH, using = "//a[@title='View All Results']")
	public WebElement allResults;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'ToolBox')]")
	public WebElement toolBox;

	@FindBy(xpath = "//a[@title='Import Articles']")
	public WebElement articleImport;

	@FindBy(xpath = "//select[@data-autoid='Lookup3Id_ctrl']")
	public WebElement sourceTypeDropdown;

	@FindBy(xpath = "//input[@title='File Input']")
	public WebElement uploadFile;

	@FindBy(xpath = "//div[contains(text(),'Next')]")
	public WebElement next;

	@FindBy(xpath = "//a[@data-autoid='FilterField_srch']")
	public WebElement setFileds;

	@FindBy(xpath = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	public WebElement search_textbox;

	@FindBy(xpath = "//div[contains(text(),'Apply')]")
	public WebElement applyButton;

	@FindBy(xpath = "//div[@data-autoid='checkbox_input_row_index_0']")
	public WebElement checkBox;

	@FindBy(xpath = "//div[contains(text(),'Ok')]")
	public WebElement okButton;

	@FindBy(xpath = "(//a[@title='Set Deafult'])[1]")
	public WebElement switchButton;

	@FindBy(xpath = "//a[@data-autoid='ExpertOwnerName_srch']")
	public WebElement searchPicker;

	@FindBy(xpath = "//div[contains(@data-autoid,'Name_0')]")
	public WebElement firstRecord;

	@FindBy(xpath = "//input[@data-autoid='ExpertOwnerName_ctrl']")
	public WebElement importRecord;

	@FindBy(xpath = "//span[contains(text(),'sample.csv')]")
	public WebElement uploaededFileName;

	@FindBy(xpath = "//span[contains(text(),'sample.xlsx')]")
	public WebElement uploaededExcelFileName;

	@FindBy(xpath = "//div[contains(text(),'Browse by Categories')]")
	public WebElement browseByCategory;

	@FindBy(xpath = "//div[@data-autoid='footer_copyright']")
	public WebElement scrollbar;

	@FindBy(xpath = "(//div[@data-autoid='0_CategorySubject_val'])[1]")
	public WebElement browser_categoryName;

	@FindBy(how = How.XPATH, using = "//a[@title='Object Field mapping']")
	public WebElement objectFieldMapping;

	public String searchresult_pre = "//div[contains(text(),'";
	public String searchresult_post = "')]";

	public String searchtittle = "//a[@data-autoid='0_Title_val']/span";

	@FindBy(how = How.XPATH, using = "//a[@title='New']")
	public WebElement newButton_objectFieldMapping;

	@FindBy(how = How.XPATH, using = "//label[@title='Mapping']")
	public WebElement mappingField;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='TextPicker1Id_srch']")
	public WebElement searchPicker_mappingField;

	@FindBy(how = How.XPATH, using = "//input[@name='Grid_SearchTextBox']")
	public WebElement mappingField_withLeadFields;

	@FindBy(xpath = "//a[@data-autoid='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "(//div[@title='Leads'])[1]")
	public WebElement objectName_Lead;

	@FindBy(how = How.XPATH, using = "(//div[@title='Address'])[1]")
	public WebElement objectFieldValued_Address;

	@FindBy(xpath = "//select[@data-autoid='StatusCode_ctrl']")
	public WebElement mapping_richContentEditorField;

	@FindBy(xpath = "//a[@title='Next']")
	public WebElement nextButton;

	@FindBy(xpath = "//a[@title='Export']")
	public WebElement exportButton;

	@FindBy(xpath = "//div[contains(text(),'Import')]")
	public WebElement importButton;

	@FindBy(xpath = "//span[@data-autoid='TEXT13_ctrl']")
	public WebElement recordsUpdate;

	@FindBy(xpath = "//span[contains(@title,'Please wait!! Import in progress')]")
	public WebElement pleaseWaitImportInProgress;

	@FindBy(xpath = "//select[@data-autoid='ArticleExpireOn_ctrl']")
	public WebElement articleExpireField;

	@FindBy(xpath = "//div[@data-autoid='0_CategorySubject_val']")
	public WebElement getName;

	@FindBy(xpath = "//div[@data-autoid='0_SubFolders_val']")
	public WebElement subCategory_With0Folder;

	@FindBy(xpath = "//div[@data-autoid='1_SubFolders_val']")
	public WebElement subCategory_With1Folder;

	@FindBy(xpath = "//a[@title='View All Results']")
	public WebElement viewAllResults;

	@FindBy(xpath = "//div[@title='My Articles']")
	public WebElement myActiclesSection;

	@FindBy(xpath = "//div[@title='My Publish']")
	public WebElement myPublished;

	@FindBy(xpath = "(//div[@class='dashlet-value  css-0'])[1]//div")
	public WebElement countArticle;

	@FindBy(xpath = "(//div[@title='Draft Articles']/following::div[@class='dashlet-value  css-0'])[1]")
	public WebElement countDraftArticles;

	@FindBy(xpath = "(//*[contains(@data-autoid, '_Title_val')])[1]")
	public WebElement countArticleRecord;

	public String knowledgeBaseTitle_pre = "//div[text()='";
	public String knowledgeBaseTitle_post = "']";

	@FindBy(xpath = "//div[@title='My Draft']")
	public WebElement myDraft;

	@FindBy(xpath = "//div[@title='My Reviews']")
	public WebElement myReviews;

	@FindBy(xpath = "(//a[contains(@data-autoid,'Title')])[1]")
	public WebElement titleName;

	@FindBy(xpath = "//a[contains(@data-autoid,'Close')]")
	public WebElement closeButton;

	@FindBy(xpath = "(//div[contains(@class,'font-12') and contains(text(),'Category')])[position()<=5]")
	private List<WebElement> knowledgeBaseList;

	@FindBy(xpath = "(//a[@title='View All'])[1]")
	public WebElement viewAll;

	@FindBy(xpath = "//div[@data-autoid='0_PublishedOn_val']")
	public WebElement publishedDate;

	@FindBy(xpath = "//a[@title='History']")
	public WebElement history;

	@FindBy(xpath = "//a[@data-autoid='Title_0']/following::div[@data-autoid='LastModifiedOn_0']")
	public WebElement lastModifiedBy;

	@FindBy(xpath = "//a[@data-autoid='Title_0']/following::div[@data-autoid='CreatedBy_0']")
	public WebElement lastCreatedBy;

	@FindBy(xpath = "(//a[@data-autoid='Title_0']/following::div[@title='Rejected'])[1]")
	public WebElement rejectedStatus;

	@FindBy(xpath = "//div[contains(text(),'Ascending')]")
	public WebElement ascending;

	@FindBy(xpath = "(//div[starts-with(@data-autoid, 'LastModifiedBy')])[1]")
	public WebElement lastModifier;

	@FindBy(xpath = "//div[contains(text(),'Descending')]")
	public WebElement descending;

	@FindBy(xpath = "//div[normalize-space(text())='Title']/ancestor::div[1]//*[contains(@class,'menu') or @name='icon-custom-menu']")
	public WebElement shortedByTittle;

	@FindBy(xpath = "//div[normalize-space(text())='Under Review'][1]")
	public WebElement underReviewStatus;

	@FindBy(xpath = "(//div[@title='Draft'])[1]")
	public WebElement draftStatus;

	@FindBy(xpath = "//div[@title='Published Articles']/following::div[contains(@class,'dashlet-row')]")
	public WebElement publishedCountStatus;

	public int getKnowledgeBaseCount() {
		int sizeOfKnowledgeBase = knowledgeBaseList.size();
		System.out.println("Total KnowledgeBaseTittle: " + sizeOfKnowledgeBase);
		return sizeOfKnowledgeBase;
	}

	public boolean verifyNoDataExist_usingWrongID() {
		return ReUsableMethods.WebIsElementDisplayed(viewAllResults, "get file name");
	}

	public boolean verifyIsScrollbarIconDisabled() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(scrollbar, "get Rating Review");
	}

	public void clickonSave() {
		ReUsableMethods.webClickElement(saveButton, "Save");
		ReUsableMethods.waitforWindowSize(1);
	}

	public String verifyKnowledgebase() {
		ReUsableMethods.waitUntilTextIsPresent(countArticleRecord, "search Tittle");
		String result = "";

		try {
			List<WebElement> elements = ReUsableMethods.findElementByPath(searchtittle);
			if (!elements.isEmpty()) {
				result = ReUsableMethods.WebGetElementText(elements.get(0), "Latest Favourite Knowledgebase");
			}
		} catch (Exception e) {
			System.out.println("verifyKnowledgebase issue: " + e.getMessage());
		}

		return result;
	}

	public String verifyKnowledgebase_OnRecentlyAccessedArticles(String param) {
		return ReUsableMethods.WebGetElementText(RecentlyAccessedArticles, param);
	}

	public String getViewAllResultsRecord() {
		return ReUsableMethods.WebGetElementText(allResults, "get all rasult");
	}

	public boolean verifyKnowledgebase_NotPublished(String buttonParam) {
		try {
			String actualXpath = searchresult_pre + searchresult_post;
			WebElement element = wdriver.findElement(By.xpath(actualXpath));
			return ReUsableMethods.WebIsElementDisplayed(element, "visible element");
		} catch (Exception e) {
			System.out.println("Exception give verifyKnowledgebase_NotPublished: " + e.getMessage());
			return false;
		}
	}

	public HashMap<String, Boolean> verifyglobalSearchBarDisplayed() throws InterruptedException {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();

		map.put("globalSearchBar", ReUsableMethods.WebIsElementDisplayed(globalSearchBar, "Progress Status"));
		// map.put("globalSearchPicker",
		// ReUsableMethods.WebIsElementDisplayed(globalSearchPicker, "Progress
		// Status"));
		return map;
		// boolean isGlobalSearchBarDisplayed =
		// ReUsableMethods.WebIsElementDisplayed(globalSearchBar, "Progress Status");
		// boolean isGlobalSearchPickerDisplayed =
		// ReUsableMethods.WebIsElementDisplayed(globalSearchPicker,
		// "Progress Status");
		// return isGlobalSearchBarDisplayed && isGlobalSearchPickerDisplayed;
	}

	@FindBy(xpath = "//a[@class=\"fileDelete\"]")
	private WebElement deleteFileIcon;

	public void uploadFile(String path) throws InterruptedException {
		ReUsableMethods.switchToChildWindowHandle();
		uploadFile.sendKeys(path);
		WebWait.waitForExplictVisibility(deleteFileIcon);
	}

	public String verifyUserName() throws InterruptedException {
		return importRecord.getDomAttribute("Value");

	}

	public String getUploaedfFileName() {
		return ReUsableMethods.WebGetElementText(uploaededFileName, "get file name");
	}

	public boolean getKnowledgeBase() {
		return ReUsableMethods.WebIsElementDisplayed(uploaededFileName, "get file name");
	}

	public boolean getBrowseByCategory() {
		return ReUsableMethods.WebIsElementDisplayed(browseByCategory, "get browse By Category name");
	}

	public boolean getKnowledgeBaseCategory(String expectedCategoryName) throws InterruptedException {
		// WebWait.fluentWaitForInvisibility(getName);
		return ReUsableMethods.WebIsElementDisplayed(getName, "get name");
	}

	// public void getBaseKnowledgeCategoryFolderValue(){
	// if
	// (ReUsableMethods.WebIsElementDisplayed(knowledgeBaseHomePage.subCategory_With0Folder,
	// "o folder upload")) {
	// // Retrieve the text from the element
	// String elementText = knowledgeBaseHomePage.subCategory_With0Folder.getText();
	//
	// // Check if the text contains "0 folder"
	// if (elementText.contains("0 folder")) {
	// System.out.println("The element contains '0 folder'.");
	// } else {
	// System.out.println("The element does not contain '0 folder'.");
	// }
	// } else {
	// System.out.println("The element is not displayed.");
	// }
	public boolean verifyIsViewAllResultsFieldVisibleOrNot() {
		return ReUsableMethods.WebIsElementDisplayed(viewAllResults, "view all results");
	}

	public String getArticleCount() throws Exception {
		String count = ReUsableMethods.getTextElementAttribute(countArticle, "title");
		return count;
	}

	public boolean verifyKnowledgeBaseTittle(String knowledge) {
		String actualXpath = knowledgeBaseTitle_pre + knowledge + knowledgeBaseTitle_post;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		return ReUsableMethods.WebIsElementDisplayed(element.get(0), "Catagory");
	}

	public String getTextOfLastModifiedOn() {
		String lastmodifiedon = ReUsableMethods.WebGetElementText(lastModifiedBy, "lastmodified");
		String pattern = "\\d{2}[-/]\\d{2}[-/]\\d{4}";
		Pattern pattern1 = Pattern.compile(pattern);
		Matcher matcher = pattern1.matcher(lastmodifiedon);

		if (matcher.find()) {
			System.out.println(matcher.group());
			return matcher.group();
		} else {
			return null;

		}
	}

	public void sortingByTittle(String direction) {
		ReUsableMethods.scrollElementToCentreOfScreen(shortedByTittle);
		WebElement targetElement;

		if (direction.equalsIgnoreCase("ascending")) {
			targetElement = ascending;
		} else if (direction.equalsIgnoreCase("descending")) {
			targetElement = descending;
		} else {
			throw new IllegalArgumentException("Invalid sorting direction: " + direction);
		}

		ReUsableMethods.safeClick(shortedByTittle, targetElement);
		ReUsableMethods.webClickElement(targetElement, direction);
	}

	public String getDraftArticlesCount() {
		return ReUsableMethods.WebGetElementText(countDraftArticles, "Draft Articles Count");
	}

	public String publishedArticlesCount() {
		return ReUsableMethods.WebGetElementText(publishedCountStatus, "published Articles Count");
	}

	public void clickOnViewAllBrowseByCategory(String category) {
	    String xpath = "//div[@title='" + category + "']" +
	                   "/following::div[contains(@class,'css')]//div[normalize-space()='View All']";
	    WebElement element = ReUsableMethods.findElementByPath(xpath).get(0);
	    ReUsableMethods.webClickElement(element, "View All for " + category);
	}
}