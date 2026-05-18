package com.businessnext.objects.lead.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.common.pages.CommonProductFunctions;
import com.utilities.ReUsableMethods;

public class LeadProcessHomePage {

	WebDriver wdriver;

	public LeadProcessHomePage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "(//*[local-name()='svg' and @name='icon-srsearch'])[1]")
	private WebElement searchIcon;

	@FindBy(xpath = "//div[@data-autoid='undefined_ctrl']")
	private WebElement searchDropDown;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement search_Input;

	@FindBy(xpath = "//a[contains(@data-autoid, '_LINK_LAYOUTView02')]")
	private WebElement detailLink_Process;

	@FindBy(xpath = "//div[@data-autoid='1_Name_val']")
	private WebElement firstProcessName_AfterSearch;

	@FindBy(xpath = "//a[@data-autoid='nextButton_CrmGrid']//i[@class='icon icon-arrow-right2']")
	private WebElement nextButton;

	@FindBy(xpath = "//*[contains(@class,'pagination')]//input[@data-testid='input']")
	private WebElement pageInput;

	@FindBy(xpath = "//div[contains(text(),'No data exists')]")
	private WebElement noDataExist;


	@FindBy(xpath = "//a[contains(@data-autoid,'LINK_DETAILView')]")
	private WebElement viewLink;

	@FindBy(xpath = "(//a[@data-autoid='defaultlaysummary_1_1'][normalize-space()='Default'])[2]")
	private WebElement defaultLayout;

	@FindBy(xpath = "//a[@data-autoid='autoid_link1']")
	private WebElement newEditLink;

	@FindBy(xpath = "//a[@data-autoid='autoid_link2']")
	private WebElement detailLink;
	
	@FindBy(xpath = "(//*[local-name()='svg' and @name='icon-custom-menu'])//parent::div")
	private WebElement threeDotIcon_LeadProcess;

	@FindBy(xpath = "//a[@data-autoid='nextButton_CrmGrid']")
	private WebElement nextPageButton;
	
	@FindBy(xpath="//div[@data-autoid='search']")
	private WebElement searchForLayout;
	
	@FindBy(xpath="//div[@data-autoid='undefined_ctrl']")
	private WebElement chooseSelectBy;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement chooseLayoutName;
	
	@FindBy(xpath = "//div[contains(@data-autoid, '_AI')]")
	private WebElement threeDots;


	public void clickOnThreeDots() throws InterruptedException {
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(threeDots, "Three Dots");
		
	}

	
	public void clickOnThreeDotsUntilVIewLinkIsVisible() throws InterruptedException {
		  clickOnThreeDots();
		int maxAttempts = 3;
	    int attemptCount = 0;
	    
	    while (!isDetailVisible() && attemptCount < maxAttempts) {
	        clickOnThreeDots();
	        Thread.sleep(1000); // Add a short delay between clicks
	        System.out.println("Attempting " +attemptCount + " times");
	        attemptCount++;
	    }
	}

	public boolean isDetailVisible() {
	  return ReUsableMethods.WebIsElementDisplayed(viewLink, "viewLink");
	}

	

	@FindBy(xpath="//div[@data-autoid='QueryViewId_ctrl']//div[text()='List View']")
	private List<WebElement> listView;
	
	@FindBy(xpath="//div[@data-autoid='QueryViewId_ctrl']//div[text()='Tile View']")
	private WebElement tileView;
	
	@FindBy(xpath="//div[@data-autoid='QueryViewId_ctrl']")
	private WebElement viewDropdwon;
	
	@FindBy(xpath="//div[@role='list']//span[@title='List View']")
	private WebElement selectListView;
	
	public void searchProcess(String filterBy, String processName) throws InterruptedException {
	//	pageInput.sendKeys("19");
		if(listView.size()<1) {
			ReUsableMethods.webClickElement(viewDropdwon, "viewDropdwon");
			ReUsableMethods.webClickElement(selectListView, "selectListView");
		}
		ReUsableMethods.webClickElement(searchIcon, "Search Icon");
		ReUsableMethods.webSelectByVisibleText_Swift(searchDropDown, filterBy, "SearchDropDown Process");
		ReUsableMethods.webEnterTextwithFluentwait(search_Input, processName, "Input Process Name");

		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);
		commonProductFunctions.checkSearchRecord(threeDotIcon_LeadProcess, "Three Dots");


//		try {
//
//			ReUsableMethods.webClickElement(searchIcon, "Search Icon");
//			ReUsableMethods.webSelectByVisibleText(searchDropDown, filterBy, "SearchDropDown Process");
//			int i = 2;
//			while (nextButton.isEnabled()) {
//				ReUsableMethods.webEnterTextwithFluentwait(search_Input, processName, "Input Process Name");
//
//				if (noDataExist.isDisplayed()) {
//					System.out.println("Exception found while searching process");
//					search_Input.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
//					Thread.sleep(1000);
////					search_Input.sendKeys(Keys.BACK_SPACE);
////					Thread.sleep(2000);
//					// search_Input.sendKeys(Keys.ENTER);
//					// ReUsableMethods.scrollDownToElement(nextButton);
//					// ReUsableMethods.webClickElement(nextButton, "Next Button");
//					pageInput.sendKeys(String.valueOf(i));
//					Thread.sleep(1000);
//					pageInput.sendKeys(Keys.ENTER);
//					System.out.println("Value of i inout is " + i);
//					i++;
//				} else {
//					System.out.println("Element Found");
//
//				}
//
//			}
//		} catch (Exception e) {
//			System.out.println("Exception Found while searching for process"+e);
//		}
	}

	public void clickDetailTab() throws InterruptedException {
		ReUsableMethods.webClickElement(detailLink_Process, "Detail Link");
	}

	public void clickOnViewLink() throws InterruptedException {
		
		ReUsableMethods.webClickElement(viewLink, "View Process");
	}
	
	public void clickOnThreeDotIcon_LeadProcess() throws InterruptedException {
    	ReUsableMethods.safeClick(threeDotIcon_LeadProcess, viewLink);
    	 
    }    
	

	public void clickOnDefaultLayout() throws InterruptedException {
		
		ReUsableMethods.webClickElement(defaultLayout, "default Layout");
		
	}

	public void clickonNewEdit() {
		ReUsableMethods.webClickElement(newEditLink, "New edit Link");
	}

	public void clickonDetailLink() throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webClickElement(detailLink, "New edit Link");
		Thread.sleep(2000);
	}
	
	
	
	public void clickOnsearchIconForLayout() {
		ReUsableMethods.webClickElement(searchForLayout, "click on layout");
	}
	
	public void enterOnTextBox() throws InterruptedException {
		CommonProductFunctions cmp = new CommonProductFunctions(wdriver);
		ReUsableMethods.webEnterText(chooseLayoutName, "Lead_System", "select name of layout");
		cmp.checkSearchRecord(threeDotIcon_LeadProcess, "Three Dots");
		
	}
	
	public void selectOnSelectBy() throws InterruptedException {
		ReUsableMethods.webSelectByVisibleText_Swift(chooseSelectBy, "Name", "select from select by");
	}

	public void navigateToLatestProcessPage() {

		try {

			while (nextPageButton.isEnabled()) {
				System.out.println("Next page buttin enabled?=" + nextPageButton.isEnabled());
				ReUsableMethods.scrollDownToElement(nextPageButton);
				ReUsableMethods.webClickElement(nextPageButton, "Next Page");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	  public void goToPageNumber(String page) throws InterruptedException {
		  Thread.sleep(2000);
		  ReUsableMethods.scrollDownToElement(pageInput);
		  ReUsableMethods.webClearText(pageInput,"");
		 ReUsableMethods.webEnterText(pageInput, page, "input text box");
		 pageInput.sendKeys(Keys.ENTER);
		
	}  
		
}
