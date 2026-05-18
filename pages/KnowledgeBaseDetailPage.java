package com.businessnext.knowledgebase.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.common.pages.CommonProductFunctions;
import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;

public class KnowledgeBaseDetailPage {

	WebDriver wdriver;

	public KnowledgeBaseDetailPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Related Links')]")
	public WebElement relatedLink;

	@FindBy(how = How.XPATH, using = "//a[@title='Add to Favourite']")
	public WebElement addToFavourite;

	@FindBy(how = How.XPATH, using = "//a[@title='Remove From Favourite']")
	public WebElement removeFromFavourite;

	@FindBy(how = How.XPATH, using = "//a[@title='Review and Publish']")
	public WebElement publishActionIcon;

	@FindBy(how = How.XPATH, using = "//textarea[@data-autoid='Description1_ctrl']")
	public WebElement comment_PublishActionIcon;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//div[@data-testid='dialog-close']//*[local-name()='svg' and contains(@name,'cross')]")

	public WebElement crossIcon;

	@FindBy(xpath = "//div[@data-testid=\"switch-box\"]")
	private WebElement markactiveArticle;

	@FindBy(xpath = "//span[normalize-space(text())='Related Articles']")
	public WebElement relatedActicle;
	
	@FindBy(xpath = "//div[@data-autoid='RELATEDARTICLES']")
	public WebElement articleRecord;

	
	
	
	public void clickonSaveButton() {
		if (ReUsableMethods.WebIsElementDisplayed(markactiveArticle, "markactiveArticle")) {
			ReUsableMethods.webClickElement(markactiveArticle, "markactiveArticle");
		}

		ReUsableMethods.webClickElement(saveButton, "Save button");
		CommonProductFunctions cmp = new CommonProductFunctions(DriverManager.getWdriver());
		cmp.waitForLoader();
	}

	@FindBy(how = How.XPATH, using = "//a[@title='HomePage']")
	public WebElement backIconToNavigateHomePage;

	@FindBy(xpath = "//div[@data-testid='loader-bn']")
	public WebElement loader;

	@FindBy(how = How.XPATH, using = "//*[local-name()='svg' and @name='icon-button']")
	public WebElement keyInformation;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Document Details')]")
	public WebElement documentDetails;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Attachments')]")
	public WebElement attachments;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Related Articles')]")
	public WebElement relatedArticles;

	@FindBy(xpath = "//a[@data-autoid='Edit_1']")
	public WebElement editButton;

	@FindBy(xpath = "//div[contains(text(),'Delete')]")
	public WebElement deleteButton;

	@FindBy(xpath = "//div[contains(text(),'Create New')]")
	public WebElement newCreateButton;

	@FindBy(xpath = "(//div[@data-toggle='tooltip'])[1]")
	public WebElement folderFile;

	@FindBy(xpath = "(//*[local-name()='svg' and @name='icon-custom-menu'])[4]//parent::div")
	public WebElement threeIcon;

	@FindBy(xpath = "//a[@title='Add Article']")
	public WebElement addArticle;

	@FindBy(xpath = "//input[@data-autoid='CurrentOwnerID_Knowledge_ctrl']")
	public WebElement userName;

	@FindBy(xpath = "//input[@data-autoid='ExpertOwnerName_ctrl']")
	public WebElement userName_Import;

	@FindBy(xpath = "//h5[normalize-space()='Item Not Found']")
	public WebElement noDataExits;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-review']")
	public WebElement ratingAndReviews_move;

	@FindBy(xpath = "(//*[local-name()='svg' and @data-testid='rating-svg'])[9]//parent::div")
	public WebElement starRating;

	@FindBy(xpath = "//*[contains(text(),'Reviews')]/following::input[@data-testid='input']")
	public WebElement giveRating;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-send']")
	public WebElement sendReview;

	@FindBy(xpath = "//div[@data-autoid='0_ModifiedOn_val']")
	public WebElement getRatingReview;

	@FindBy(xpath = "//span[@data-autoid='TITLE_ctrl']")
	public WebElement tittleName;

	@FindBy(xpath = "(//*[local-name()='svg' and @name='icon-chevron-left'])[2]")
	public WebElement expandCollapse;

	public String createcategory_pre = "//*[@title='";
	public String createcategory_post = "']";

	@FindBy(xpath = "//span[@data-autoid='TITLE_ctrl']")
	public WebElement tittle;

	@FindBy(xpath = "//div[@data-autoid='0_Comments_val']")
	public WebElement comments;

	@FindBy(xpath = "(//a[@data-autoid='button'])[4]")
	public WebElement commentIcon;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-image']")
	public WebElement imageIcon;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-table-line']")
	public WebElement line;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-horizontel-line']")
	public WebElement image;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-youtube']")
	public WebElement youTuberVedio;

	@FindBy(xpath = "//a[@title='Clone']")
	public WebElement clone;

	@FindBy(xpath = "//a[@data-autoid='CurrentOwnerID_Knowledge_ctrl']")
	public WebElement currentOwnerName;

	@FindBy(xpath = "//*[@title='Cancel' and normalize-space()='Cancel']")
	public WebElement cancelButton;

	@FindBy(xpath = "//h6[contains(text(),'Validation Summary')]")
	public WebElement validationMassage;
	
	@FindBy(xpath = "//div[@data-autoid='rating' and @value='5']")
	public WebElement ratingCount;
	
	@FindBy(xpath = "//div[@title='Good']")
	public WebElement goodReview;
	
	@FindBy(xpath = "//a[@title='Send For Review']")
	public WebElement sendForReview;
	
	@FindBy(xpath = "//h5[text()='Access Denied']")
	public WebElement accessDenied;
	
	@FindBy(xpath = "//span[@title='Rejected']")
	public WebElement rejectedStatus;
	
	@FindBy(xpath = "//div[@id='exportToPdf']//a[@data-autoid='button']")
	public WebElement exportToPdf;

	@FindBy(xpath = "//h1[contains(text(),'Attachments')]")
	public WebElement attchmentContent;
	
	@FindBy(xpath = "//div[starts-with(@data-autoid, 'ShortName_') and @title='Auto5']")
	public WebElement shortName;
	
	@FindBy(xpath = "//div[starts-with(@data-autoid, 'Email_')]")
	public WebElement userEmailID;
	
	@FindBy(xpath = "//li[@data-autoid='audit_1']")
	public WebElement modifiedBy;
	
	@FindBy(xpath = "//*[normalize-space()='Rejected']")
	public WebElement rejectedComment;
	
	public boolean verifyKeyInformationDisplayed() throws InterruptedException {
		boolean isKeyInformation_documentDisplayed = ReUsableMethods.WebIsElementDisplayed(documentDetails,
				"Progress Status");
		boolean isKeyInformation_attachmentstDisplayed = ReUsableMethods.WebIsElementDisplayed(attachments,
				"Progress Status");
		boolean isKeyInformation_relatedArticlesDisplayed = ReUsableMethods.WebIsElementDisplayed(relatedArticles,
				"Progress Status");
		return isKeyInformation_documentDisplayed && isKeyInformation_attachmentstDisplayed
				&& isKeyInformation_relatedArticlesDisplayed;
	}

	public HashMap<String, Boolean> verifyAnArticalFields() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("newCreateButton",
				ReUsableMethods.WebIsElementDisplayed(newCreateButton, "new buuton is visible or not"));
		map.put("keyInformation", ReUsableMethods.WebIsElementDisplayed(keyInformation, "Add to favorite"));
		map.put("addToFavourite", ReUsableMethods.WebIsElementDisplayed(relatedLink, "Add to related link"));
		map.put("relatedLink",
				ReUsableMethods.WebIsElementDisplayed(publishActionIcon, "pulish action visible pr not"));
		map.put("editButton", ReUsableMethods.WebIsElementDisplayed(editButton, "adit button is diplayed or not"));
		return map;

	}

	public String verifyUserName() throws InterruptedException {
		return userName.getDomAttribute("Value");

	}

	public boolean checkIfDataExistOrNot() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(noDataExits, " no record found element");
	}

	public HashMap<String, Boolean> verifyAnArtical_ratingAndReviews() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Ratings and Reviews",
				ReUsableMethods.WebIsElementDisplayed(ratingAndReviews_move, "new buuton is visible or not"));
		return map;
	}

	public boolean verifyGettingReviewRating() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(getRatingReview, "get Rating Review");
	}

	public boolean verifyFolderFileIsVisible() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(folderFile, "get Rating Review");
	}

	public boolean verifyIsKnowledgeBaseVisible(String Param) {
		String actualXpath = createcategory_pre + Param + createcategory_post;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		return ReUsableMethods.WebIsElementDisplayed(element.get(0), "Base Knowledge Not visible");
	}

//	public boolean verifyIsKnowledgeBaseVisible1(String Param) {
//		String actualXpath = createcategory_pre+Param+createcategory_post;
//			WebElement element = wdriver.findElement(By.xpath(actualXpath));
//			return ReUsableMethods.isElementPresentByXpath(actualXpath);		
//	}

	public HashMap<String, Boolean> verifyTableImageYouTubeVideo() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Line", ReUsableMethods.WebIsElementDisplayed(line, "line icon"));
		map.put("Image", ReUsableMethods.WebIsElementDisplayed(image, "image icon"));
		map.put("Youtube Video", ReUsableMethods.WebIsElementDisplayed(youTuberVedio, "Youtube Video"));
		return map;

	}

	public boolean validateAuthorNameIsVisibleOrNot() throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(currentOwnerName, "Test scripts failed because name not present");
	}

	public boolean getValidationMassage() {
		validationMassage.getDomAttribute("Validation Summary");
		return true;
	}

	public void clickonbacktoHomePage() {
		ReUsableMethods.webClickElement(backIconToNavigateHomePage, "Back to Knowledgebase home page");
		ReUsableMethods.waitforElementInvisible(backIconToNavigateHomePage);
	}
	
	
	
	///////////UTR Type//////////////////
	@FindBy(xpath = "//a[@data-autoid='button']/*[local-name()='svg' and @name='icon-acid-timer']")
	public WebElement verisonHistory;
	
	@FindBy(xpath = "//a[@title='Version: v1']")
	public WebElement version1;
	
	@FindBy(xpath = "//a[@title='Version: v2']")
	public WebElement version2;
	
	@FindBy(xpath = "//a[@title='Version: v3']")
	public WebElement version3;
	
	@FindBy(xpath = "//*[@data-autoid='TITLE_ctrl']")
	public WebElement tittleValue;

	public String category_pre = "//div[@title='";
	public String midcategory = "']";
	
	@FindBy(xpath = "//label[@data-autoid='CurrentOwnerID_Knowledge_lbl']")
    private WebElement author;

    @FindBy(xpath = "//label[@data-autoid='ExpertOwnerID_Knowledge_lbl']")
    private WebElement expert;

    @FindBy(xpath = "//label[@data-autoid='ArticleExpireOn_lbl']")
    private WebElement expireOn;

    @FindBy(xpath = "//label[@data-autoid='TAGID_lbl']")
    private WebElement keywords;

    @FindBy(xpath = "//label[@data-autoid='VERSIONNUMBER_lbl']")
    private WebElement versioning;

    @FindBy(xpath = "//label[@data-autoid='STATUSCODEID_lbl']")
    private WebElement articleStatus;

	
	public void editAndSaveKnowledgebase(String publishComment, String tittleName) {
	    ReUsableMethods.webClickElement(editButton, "Edit button");
	    ReUsableMethods.webEnterText(tittleValue, tittleName, "Enter title");
	    ReUsableMethods.webClickElement(saveButton, "Save button");
	    ReUsableMethods.webClickElement(publishActionIcon, "publishActionIcon");
		ReUsableMethods.webEnterText(comment_PublishActionIcon,
				publishComment, "commentBox");
		 clickonSaveButton();
	}
	
	public boolean verifyIsCategoryShownByDefault(String category) {
		String actualXpath = category_pre + category + midcategory;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		return ReUsableMethods.WebIsElementDisplayed(element.get(0), "Base Knowledge Category");
	}
	
	public HashMap<String, Boolean> keyInformationDetails() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Document Details", ReUsableMethods.WebIsElementDisplayed(documentDetails, "Document Details section"));
		map.put("Attachments", ReUsableMethods.WebIsElementDisplayed(attachments, "Attachments section"));
		map.put("Related Articles", ReUsableMethods.WebIsElementDisplayed(relatedArticles, "Related Articles section"));
		return map;
	}

	public HashMap<String, Boolean> verifyKeyInformationFieldsDisplayed() {
	    HashMap<String, Boolean> fieldVisibilityMap = new HashMap<>();
	    fieldVisibilityMap.put("Author", ReUsableMethods.WebIsElementDisplayed(author, "Author field"));
	    fieldVisibilityMap.put("Expert", ReUsableMethods.WebIsElementDisplayed(expert, "Expert field"));
	    fieldVisibilityMap.put("Expire On", ReUsableMethods.WebIsElementDisplayed(expireOn, "Expire On field"));
	    fieldVisibilityMap.put("Keywords / Tags", ReUsableMethods.WebIsElementDisplayed(keywords, "Keywords / Tags field"));
	    fieldVisibilityMap.put("Versioning", ReUsableMethods.WebIsElementDisplayed(versioning, "Versioning field"));
	    fieldVisibilityMap.put("Article Status", ReUsableMethods.WebIsElementDisplayed(articleStatus, "Article Status field"));
	    return fieldVisibilityMap;
	}

	
	public void clickOnDraftKnowledgeBase(String param) {
		String actualXpath = createcategory_pre + param + createcategory_post;
		List<WebElement> element = ReUsableMethods.findElementByPath(actualXpath);
		ReUsableMethods.webClickElement(element.get(0), "Category");
	}

}