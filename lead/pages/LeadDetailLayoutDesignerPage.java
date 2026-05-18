package com.businessnext.objects.lead.pages;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;
import com.utilities.WebWait;

public class LeadDetailLayoutDesignerPage {

	WebDriver wdriver;

	public LeadDetailLayoutDesignerPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//input[@id='toolbox-search']")
	private WebElement layoutDesignerSearch;

//	@FindBy(xpath = "//div[@data-autoid='back-button']")
//	private WebElement backButtonDesigner;

	@FindBy(xpath = "//a[@data-testid='button' and normalize-space()='Save']")
	private WebElement saveButton_OnPopup;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[9]")
	private WebElement dropLocation_belowLNewEmail;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[11]")
	public WebElement dropLocation_belowLLeadChild;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[10]")
	private WebElement dropLocationCorporateCard;

	@FindBy(xpath = "//label[normalize-space()='Lead Child']")
	private WebElement leadChildSource;

	@FindBy(xpath = "//label[normalize-space()='Lead Parent']")
	public WebElement leadParentSource;

	@FindBy(xpath = "(//label[normalize-space()='Corporate ...'])[1]")
	private WebElement corporateHierarchyAfterSearch;

	@FindBy(xpath = "(//*[contains(text(),'Configure Hierarchy')])[1]")
	private WebElement configureHierarchyLink;

	@FindBy(xpath = "(//a[contains(@class, 'field__item')])[1]")
	private WebElement hierarchyPicker;

	@FindBy(xpath = "//select[@name='displayAs']")
	private WebElement displayAsDropdown;

	@FindBy(xpath = "//input[@id='Save']")
	private WebElement saveHierarchyConfiguration;

	@FindBy(xpath = "//*[contains(text(),'Contact_Tab')]")
	private WebElement contactRelatedObjectTab;

	@FindBy(xpath = "(//*[contains(text(),'Manage Custom Column')])[1]")
	private WebElement manageCustomColumn;

	@FindBy(xpath = "(//*[contains(text(),'Activity')])[4]")
	private WebElement activityTab;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[1]")
	private WebElement dropLocation_FirstRow_ContactRelatedObject;

	@FindBy(xpath = "//*[@data-autoid='back-button']")
	private WebElement backButtonDesigner;

	@FindBy(xpath = "(//*[local-name()='g' and @class='Ex_52_63']//*[local-name()='text' and @class='deleteIcon icon'])[20]")
	private WebElement deleteMashupContol;

	@FindBy(xpath = "(//*[local-name()='g' and @class='Ex_52_63']//*[local-name()='text' and @class='deleteIcon icon'])[31]")
	private WebElement deleteMashupContol3;

	@FindBy(xpath = "(//*[local-name()='g' and @class='Ex_52_63']//*[local-name()='text' and @class='deleteIcon icon'])[32]")
	private WebElement deleteMashupContol1;

	// (//*[local-name()='g' and @class='Ex_52_63']//*[local-name()='text' and
	// @class='icon'])[10]

	@FindBy(xpath = "//input[@value='Confirm']")
	private WebElement confirmButton;

	@FindBy(xpath = "//*[contains(text(),'Automation_Mashup03-28-2023 16:30:27 IST')]")
	private WebElement LayoutConfiguration;

	/*
	 * @FindBy(xpath =
	 * "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[12]")
	 * private WebElement dropLocation_belowMashup;
	 */
	// *[local-name()='text' and contains(text(),'drop')]
	@FindBy(xpath = "//*[local-name()='text' and text()='Mashup Control']//following-sibling::*[local-name()='g']//child::*[local-name()='text' and contains(text(),'drop')]")
	private WebElement dropLocation_belowMashup;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[16]")
	private WebElement dropLocation_belowMashupdetail;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[15]")
	private WebElement dropLocation_belowMashupdetail3;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[16]")
	private WebElement dropLocation_belowMashupdetail8;

	@FindBy(xpath = "//div[@title='Social']")
	private WebElement sourceSocialOnLayout;

	@FindBy(xpath = "//input[@value='Ok']")
	private WebElement okSave;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[1]")
	private WebElement dropLocation_belowMashupdetail1;

	@FindBy(xpath = "(//*[local-name()='g'and @class='clsTab']/*[local-name()='rect'])[4]")
	private WebElement newTAB;

	@FindBy(xpath = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[1]")
	private WebElement dropLocation_belowMashupnewEdit;

	@FindBy(xpath = "//a[@title='Save']")
	private WebElement layoutDesigner;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='SaveContinue']")
	public WebElement saveAndContinue;

	@FindBy(xpath = "//a[@data-autoid='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-3dot-menu']")
	public WebElement threeDotMenu;

	@FindBy(xpath = "//div[@data-autoid='REMOVE_CUSTOMIZATION-button']")
	public WebElement removeCustomization;

	@FindBy(xpath = "//input[@id='btncancel']")
	public WebElement cancelButtonofPopup;

	@FindBy(xpath = "//a[@id='header-icon-card']")
	public WebElement switchToCardDesigner;

	@FindBy(xpath = "//div[@id='cardoneCol']//child::a//child::i[contains(@class,'icon-card-2')]")
	public WebElement cardLayout;

	@FindBy(xpath = "//input[@id='btnSelect']")
	public WebElement applyButtonOnCard;

	@FindBy(xpath = "//a[@id='header-icon-classic']")
	public WebElement switchToClassicDesigner;

	public void clickOnContactRelatedObjectTab() throws InterruptedException {
		ReUsableMethods.webClickElement(contactRelatedObjectTab, "Contact Tab");
	}

	public void searchElementOnLayoutDesigner(String elementName) throws InterruptedException {

		ReUsableMethods.webClearText(layoutDesignerSearch, "Search Box");

		ReUsableMethods.webEnterText(layoutDesignerSearch, elementName, "Search Box");
	}

	public void clickOnBackButton() throws InterruptedException {

		ReUsableMethods.webClickElement(backButtonDesigner, "Back Button");
		WebWait.fluentWaitForInvisibility(saveButton);
	}

	public void clickOnSaveButton_onPopup() throws InterruptedException {
		if (ReUsableMethods.WebIsElementDisplayed(saveButton_OnPopup, null)) {
			ReUsableMethods.webClickElement(saveButton_OnPopup, "Save Button_onPopup");
		}
	}

	public void dragAndDropSearchedItem_toLayout(String element, String row) throws InterruptedException, AWTException {
		try {
			Thread.sleep(3000);
			//
			String baseXpath = "(//label[normalize-space()='elementName'])[1]";
			String searchElementXpath = baseXpath.replaceAll("elementName", element);

			String baseXpathForDrop = "(//*[local-name()='g'and @class='BlankRow']/*[local-name()='rect'])[NUMBER]";
			String actualXpathDRopLocation = baseXpathForDrop.replaceAll("NUMBER", row);
			System.out.println("Dropping on " + actualXpathDRopLocation);
			WebElement source = wdriver.findElement(By.xpath(searchElementXpath));

			WebElement dest = wdriver.findElement(By.xpath(actualXpathDRopLocation));
			// ReUsableMethods.webDragAndDropWithActionOffset(wdriver.findElement(By.xpath(searchElementXpath)),
			// wdriver.findElement(By.xpath(actualXpathDRopLocation)), 0, -4);
			ReUsableMethods.webDragAndDropWithAction(source, dest);
			// ReUsableMethods.webDragAndDropWithActionOffset(leadChildSource,
			// dropLocation_FirstRow_ContactRelatedObject, 0, 2);
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Exception caught while dropping the element " + e);
			// row = String.valueOf(Integer.parseInt(row)+1);
			// dragAndDropSearchedItem_toLayout(element, row);
		}
	}

	public void dragAndDropSearchedCorporateCard_toLayout() throws InterruptedException, AWTException {
		Thread.sleep(3000);

		ReUsableMethods.webDragAndDropWithActionOffset(corporateHierarchyAfterSearch, dropLocationCorporateCard, 0, 2);

		Thread.sleep(3000);
	}

	public void clickOnConfigureHierarchyLink() {
		ReUsableMethods.webClickElement(configureHierarchyLink, "Configiure Hierarchy Link");
	}

	public void selectTheHierarchyFromPopup(String hName) {

		String baseXpath = "(//div[@title='HierarchyName'])[1]";
		String actualXpath = baseXpath.replaceAll("HierarchyName", hName);
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "Hierarchy");

	}

	public void clickOnHierarchyPicker() throws InterruptedException {
		ReUsableMethods.webClickElement(hierarchyPicker, "Hierarchy picker");

	}

	public void selectvalueFromDisplayDropDown(String displayMode) throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webSelectByVisibleText(displayAsDropdown, displayMode, "Display Mode DropDown");
	}

	public void saveHierarchyConfiguration() {
		ReUsableMethods.webClickElement(saveHierarchyConfiguration, "Save hierarchy Configuration Button");
	}

	public void dragAndDropSearchedItem_toLayout(String element) throws InterruptedException, AWTException {
		//
		// ReUsableMethods.scrollSlider_ByCount(12);
		// Thread.sleep(3000);

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);

		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)),
				dropLocation_belowMashup);

		Thread.sleep(3000);
	}

	public void dragAndDropSearchedItem_toLayout7(String element) throws InterruptedException, AWTException {

		ReUsableMethods.scrollSlider_ByCount(17);
		Thread.sleep(3000);

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);

		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)),
				dropLocation_belowMashupdetail8);

		Thread.sleep(3000);
	}

	public void dragAndDropSocialCard_toLayout(String element) throws InterruptedException, AWTException {
		Thread.sleep(200);
		ReUsableMethods.webDragAndDropWithActionOffset(sourceSocialOnLayout, sourceSocialOnLayout, 400, 0);
		Thread.sleep(3000);
	}

	public void dragAndDropSearchedItem_toLayout3(String element) throws InterruptedException, AWTException {

		// ReUsableMethods.scrollSlider_ByCount(12);
		// Thread.sleep(3000);

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);
		WebElement element1 = ReUsableMethods.findElementByPath(searchElementXpath).get(0);
		ReUsableMethods.scrollSlider_ByCountAndClick(50, element1, "Mashup Control");
		ReUsableMethods.webDragAndDropWithAction(ReUsableMethods.findElementByPath(searchElementXpath).get(0),
				dropLocation_belowMashup);

		Thread.sleep(3000);
	}

	public void dragAndDropSearchedItem_toLayout2(String element) throws InterruptedException, AWTException {

		// ReUsableMethods.scrollSlider_ByCount(20);
		//
		// Thread.sleep(3000);

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);
		ReUsableMethods.scrollDownToElement(dropLocation_belowMashupdetail);
		ReUsableMethods.webDragAndDropWithAction(ReUsableMethods.findElementByPath(searchElementXpath).get(0),
				dropLocation_belowMashupdetail);
	}

	public void dragAndDropSearchedItem_toLayout6(String element) throws InterruptedException, AWTException {

		ReUsableMethods.scrollSlider_ByCount(12);

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);
		ReUsableMethods.scrollDownToElement(dropLocation_belowMashupdetail);
		ReUsableMethods.webDragAndDropWithAction(ReUsableMethods.findElementByPath(searchElementXpath).get(0),
				dropLocation_belowMashup);

	}

	public void dragAndDropSearchedItem_toLayout9(String element) throws InterruptedException, AWTException {

		// ReUsableMethods.scrollSlider_ByCount(17);
		//

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);
		ReUsableMethods.scrollDownToElement(dropLocation_belowMashupdetail3);
		ReUsableMethods.webDragAndDropWithAction(ReUsableMethods.findElementByPath(searchElementXpath).get(0),
				dropLocation_belowMashupdetail3);

	}

	public void dragAndDropSearchedItem_toLayout1(String element) throws InterruptedException, AWTException {

		ReUsableMethods.scrollSlider_ByCount(17);

		Thread.sleep(3000);

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);
		ReUsableMethods.scrollDownToElement(dropLocation_belowMashupdetail);
		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)),
				dropLocation_belowMashupdetail3);

		Thread.sleep(3000);
	}

	public void dragAndDropSearchedItem_toLayout8(String element) throws InterruptedException, AWTException {

		// ReUsableMethods.scrollSlider_ByCount(17);
		//
		// Thread.sleep(3000);

		String baseXpath = "//div[@title='MashUpControlname']";
		String searchElementXpath = baseXpath.replaceAll("MashUpControlname", element);
		ReUsableMethods.scrollDownToElement(dropLocation_belowMashupdetail);
		ReUsableMethods.webDragAndDropWithAction(wdriver.findElement(By.xpath(searchElementXpath)),
				dropLocation_belowMashupdetail8);

		Thread.sleep(3000);
	}

	public void clickOnOkIcon() throws InterruptedException {
		if (WebWait.fluentWaitForDisplayedwithCustomTimeOut(okSave, 3)) {
			ReUsableMethods.webClickElement(okSave, "OK Button");
		}
	}

	public void clickOnNewTAB() {
		ReUsableMethods.webClickElement(newTAB, "New tab");
	}

	@FindBy(xpath = "//*[local-name()='g' and @class='buttons']")
	private WebElement drophere;

	@FindBy(xpath = "//div[@class='toolDiv ui-draggable ui-draggable-handle']")
	private WebElement buttonlinkdropdown;

	@FindBy(xpath = "//*[local-name()='title' and normalize-space()='Button Field Settings']//parent::*[local-name()='text']")
	private WebElement buttonLinks;

	@FindBy(xpath = "//input[@id='Cancel']")
	private WebElement cancelButton;

	public void dropCustomactionbuttonToDropHere() throws InterruptedException, AWTException {

		WebElement slider = ReUsableMethods
				.findElementByPath("//div[@id='vSlider']//a[@class='ui-slider-handle ui-state-default ui-corner-all']")
				.get(0);

		int maxScrolls = 500; // Prevent infinite loop

		for (int i = 0; i < maxScrolls; i++) {
			if (isElementVisible(drophere)) {
				System.out.println("Element is now visible!");
				ReUsableMethods.newDragDrop(buttonlinkdropdown, drophere);
				break;
			}
			slider.sendKeys(Keys.ARROW_UP);
		}

		Thread.sleep(500);

	}

	public static boolean isElementVisible(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWdriver();
		return (Boolean) js.executeScript("var elem = arguments[0], box = elem.getBoundingClientRect();"
				+ "return (box.top >= 0 && box.bottom <= window.innerHeight);", element);
	}
	// ReUsableMethods.scrollSliderForElementView(20, buttonlinkdropdown,
	// "buttonlinkdropdown");

	/*
	 * WebElement scrollableDiv = ReUsableMethods.
	 * findElementByPath("//*[local-name()='g' and @class='layoutitems']") .get(0);
	 * JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWdriver();
	 * js.executeScript("arguments[0].scrollTop = arguments[1].offsetTop;",
	 * scrollableDiv, drophere);
	 */
	// ReUsableMethods.newDragDrop(buttonlinkdropdown, drophere);
	/*
	 * ReUsableMethods.webDragAndDropWithAction(buttonlinkdropdown, drophere);; }
	 */
	
	@FindBy(xpath = "//div[@data-autoid='designer-header-btn']")
	private WebElement saveLayoutDesigner;
	
	public void clickOnSaveLayout() throws InterruptedException {
		ReUsableMethods.safeClick(saveLayoutDesigner, backButtonDesigner);

		ReUsableMethods.webClickElement(backButtonDesigner, "Save button on layout");
	}

	public void clickOnDeleteMashupControlFromLayout() throws InterruptedException {
		// ReUsableMethods.scrollSlider_ByCount(20);
		ReUsableMethods.scrollSlider_ByCountAndClick(50, deleteMashupContol, "delete mashup Control");
		// ReUsableMethods.webClickElement(deleteMashupContol, "delete mashup Control");
	}

	public void clickOnDeleteMashupControlFromLayout3() throws InterruptedException {
		// ReUsableMethods.scrollSlider_ByCount(30);
		ReUsableMethods.scrollSlider_ByCountAndClick(50, deleteMashupContol3, "delete mashup Control");

	}

	public void clickOnDeleteMashupControlFromLayout1() throws InterruptedException {
		ReUsableMethods.scrollSlider_ByCount(32);
		ReUsableMethods.webClickElement(deleteMashupContol1, "delete mashup Control");
	}

	public void clickOnConfirmButton() {
		ReUsableMethods.webClickElement(confirmButton, "click on confirm button");

	}
	// public String GetLayoutConfiguration() {
	// ReUsableMethods.scrollSlider_ByCount(20);
	// return ReUsableMethods.WebGetElementText(LayoutConfiguration, "get layout
	// configuration");
	// }

	public boolean verify_Layout(String MashupControl) throws InterruptedException {
		ReUsableMethods.scrollSlider_ByCount(20);
		try {
			String baseXpath = "//*[contains(text(),'layout')]";
			String actualXpath = baseXpath.replaceAll("layout", MashupControl);
			Thread.sleep(1000);
			return wdriver.findElement(By.xpath(actualXpath)).isDisplayed();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean verifyLayoutDesignerVisibleOrNot() throws InterruptedException {
		Thread.sleep(2000);
		return ReUsableMethods.WebIsElementDisplayed(layoutDesigner, "Layout");
	}

	public void clickOnSaveLayoutButton() throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webClickElement(layoutDesigner, "Layout");
	}

	public void clickOnActivityTab() throws InterruptedException {
		Thread.sleep(5000);
		ReUsableMethods.webClickElement(activityTab, "Layout");
	}

	public void clickOnManageCustomColumn_FromActivityTab() throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webClickElement(manageCustomColumn, "Layout");
		Thread.sleep(2000);

		ReUsableMethods.switchToChildWindowHandle();

	}

	public void SaveAndContinueDesign() throws InterruptedException {
		Thread.sleep(2000);
		ReUsableMethods.webClickElement(saveAndContinue, "Save");

	}

	public void clickOnSaveButton() throws InterruptedException {

		ReUsableMethods.webClickElement(saveButton, " button");

		ReUsableMethods.switchToWindowHandle();
	}

	@FindBy(xpath = "//div[@data-autoid='designer-panel-container']//input[@placeholder='Search']")
	private WebElement newLayoutDesignerSearchBox;

	@FindBy(xpath = "//div[normalize-space()='Drop here']")
	private WebElement dropLocator;

	private String searchCustomActionButton_pre = "//div[normalize-space()='";
	private String searchCustomActionButton_post = "']//parent::Div[contains(@class,'dragNode-container')]";

	public void leadnewLayoutCustomActionButtonSearch(String buttonName) {
		ReUsableMethods.webEnterText(newLayoutDesignerSearchBox, buttonName, "Search text box");
	}

	@FindBy(xpath = "//div[contains(@class,'react-grid-layout-canvas')]")
	public WebElement designerCanvas1;

	public void newLayoutDragAndDrop(String buttonName) throws InterruptedException, AWTException {
		leadnewLayoutCustomActionButtonSearch(buttonName);
		String actualXpath = searchCustomActionButton_pre + buttonName + searchCustomActionButton_post;
		WebElement sourceElement = ReUsableMethods.findElementByPath(actualXpath).get(0);
		// ReUsableMethods.scrollElementToCentreOfScreen(dropLocator);
		// JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWdriver();
		// js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;",
		// designerCanvas);
		Thread.sleep(2000);
		ReUsableMethods.scrollElementToCentreOfScreen(dropLocator);
		// ReUsableMethods.dragAndDropWithRetry(sourceElement, dropLocator);
		ReUsableMethods.newDragDropUpdated(sourceElement, dropLocator);
		// div[normalize-space()='Update03:07:39:869PM']//parent::Div[contains(@class,'designerPanelActionitem')]

	}

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-widget']		")
	public WebElement cardIconInNewLayoutDesigner;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-content']")
	public WebElement contentIconInNewLayoutDesigner;

	@FindBy(xpath = "//a[text()='Ok']")
	public WebElement okButton;

	@FindBy(xpath = "//input[@data-testid='input']")
	public WebElement searchArea;

	@FindBy(xpath = "//div[@data-autoid='label_Event_Planner']")
	public WebElement selectEventPlanner;

	@FindBy(xpath = "//div[@data-autoid='label_Parent_Record_Event_Planner']")
	public WebElement parentRecordEventPlanner;

	@FindBy(xpath = "//div[contains(@class,'react-grid-layout-canvas')]")
	public WebElement designerCanvas;

	public void newDragDropForEventPlanner(WebElement fromStatusElement, WebElement toStatusElement)
			throws InterruptedException, AWTException {

		Actions actions = new Actions(DriverManager.getWdriver());

		actions
				// Move to source and hold it
				.moveToElement(fromStatusElement).pause(Duration.ofMillis(500)).clickAndHold(fromStatusElement)
				.pause(Duration.ofMillis(500))

				// Begin dragging
				.moveByOffset(5, 5) // small move to start drag
				.pause(Duration.ofMillis(300))

				// Hover on target BEFORE dropping
				.moveToElement(toStatusElement) // <--- hover over target
				.pause(Duration.ofMillis(500)) // give time for hover effects

				// Slight movement inside target to ensure drop accuracy
				.moveByOffset(1, 1).pause(Duration.ofMillis(300))

				// Release the drag
				.release().build().perform();
	}

	@FindBy(xpath = "//div[@data-autoid='field_Event_Planner']	")
	public WebElement eventPlannerCard;

	@FindBy(xpath = "//div[@data-autoid='field_Parent_Record_Event_Planner']")
	public WebElement parentRecordEventPlannerCard;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-edit']")
	public WebElement editIconOnCard;

	@FindBy(xpath = "//div[contains(@class,'multiselect')]")
	public WebElement dataSourceDropdownOnCard;

	@FindBy(xpath = "//a[@data-autoid='button']//child::*[text()='Save']")
	public WebElement saveButtonOnCardPopup;

	@FindBy(xpath = "//div[contains(@class,'multiSelectDropdwonSearch')]//input[@type='text' and @placeholder='Search']")
	public WebElement searchTextBoxInDataSourceDropdown;

	String preXpath = "//span[text()='";
	String postXpath = "']";

	public void selectValueFromDataSourceDropdown(String valueToSelect) throws InterruptedException {
		String actualXpath = preXpath + valueToSelect + postXpath;
		List<WebElement> elements = ReUsableMethods.findElementByPath(actualXpath);
		ReUsableMethods.webClickElement(elements.get(0), "Value to select from Data Source Dropdown");
	}

	public void clickOnEditIconAndSelectDropDownValuesForEventPlannerCard() throws InterruptedException {
		ReUsableMethods.webClickElement(editIconOnCard, "Edit Icon on Card");

		ReUsableMethods.waitforElementInvisible(searchTextBoxInDataSourceDropdown);
		ReUsableMethods.webClickElement(dataSourceDropdownOnCard, "Data Source Dropdown on Card");

		ReUsableMethods.webEnterText(searchTextBoxInDataSourceDropdown, "Test New",
				"Search Text Box In Data Source Dropdown");
		selectValueFromDataSourceDropdown("Test New");

		ReUsableMethods.waitforElementInvisible(searchTextBoxInDataSourceDropdown);
		ReUsableMethods.webClearText(searchTextBoxInDataSourceDropdown,
				"Clear Search Text Box In Data Source Dropdown");

		ReUsableMethods.webEnterText(searchTextBoxInDataSourceDropdown, "Test InProgress",
				"Search Text Box In Data Source Dropdown");

		selectValueFromDataSourceDropdown("Test InProgress");

		ReUsableMethods.waitforElementInvisible(searchTextBoxInDataSourceDropdown);
		ReUsableMethods.webClearText(searchTextBoxInDataSourceDropdown,
				"Clear Search Text Box In Data Source Dropdown");
		ReUsableMethods.webEnterText(searchTextBoxInDataSourceDropdown, "Test Completed",
				"Search Text Box In Data Source Dropdown");
		selectValueFromDataSourceDropdown("Test Completed");

		ReUsableMethods.webClickElement(saveButtonOnCardPopup, "Save Button On Card Pop-up");
	}

}
