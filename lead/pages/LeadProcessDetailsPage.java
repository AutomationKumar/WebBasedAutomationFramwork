package com.businessnext.objects.lead.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

import com.common.pages.CommonProductFunctions;
import com.drivermanager.DriverManager;
import com.utilities.ReUsableMethods;

public class LeadProcessDetailsPage {

	WebDriver wdriver;

	public LeadProcessDetailsPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//span[@title='AutoDoc Document Settings']")
	private WebElement autoDoCDocument_SettingsTab;

	@FindBy(xpath = "//a[@data-autoid='LINK_NEWPROCESS_AutoDocRuleListing0']")
	private WebElement newAutoDocRule;

	@FindBy(xpath = "//input[@data-autoid='AD_RuleName_ctrl']")
	private WebElement inputAutoDocRuleName;

	@FindBy(xpath = "//div[@data-testid='switch']")
	private WebElement isActive;

	@FindBy(xpath = "//input[@data-autoid='AD_DefaultMessage_ctrl']")
	private WebElement defaultMessageAutoDocRule;

	@FindBy(xpath = "(//a[normalize-space()='Select Lead fields to add filter'])[1]")
	private WebElement selectLeadFilterToAddFilter_Button;

	@FindBy(xpath = "//input[@data-autoid='Grid_SearchTextBox_ctrl']")
	private WebElement searchFilterParameter;

	@FindBy(xpath = "//a[@data-autoid='gridHF_FilterField']")
	private WebElement searchArrowIcon;

	@FindBy(xpath = "(//button[normalize-space()='Ok'])[1]")
	private WebElement okButton;

	@FindBy(xpath = "//select[@data-autoid='LastName__OPR_ctrl']")
	private WebElement selectOperator_ForAddressParameter;

	@FindBy(xpath = "//div[@data-autoid='Save_0']")
	private WebElement save_AutoDocRuleBUtton;

	@FindBy(xpath = "//div[@data-autoid='0_AI']")
	private WebElement hoverIconForFirstAutoDocRule;

	@FindBy(xpath = "//a[@data-autoid='0_LINK_CONFIGUREPROCESS_AutoDocRuleListing0']")
	private WebElement configure_AutoDocButton;

	@FindBy(xpath = "//div[@data-autoid='0_AI']")
	private WebElement hoverElement_AutoDocRule;

	@FindBy(xpath = "//a[@title='Configure']")
	private WebElement configureAutoDocRule_Link;

	@FindBy(xpath = "//input[@placeholder='Search Documents']")
	private WebElement searchAutoDocPattern;

	@FindBy(xpath = "//a[@data-autoid='Lookup2IdText_srch']")
	private WebElement customFieldPicker;

	@FindBy(xpath = "//div[@class='css-mro3c9']")
	private WebElement mandatoryTab;

	@FindBy(xpath = "//*[local-name()='svg' and @name='icon-close']")
	private WebElement deleteIcon_AutoDocPattern;

	@FindBy(xpath = "//a[@data-autoid='Save']")
	private WebElement save_AutoDocConfigurationButton;

	@FindBy(xpath = "//div[contains(text(),'Any')]")
	private WebElement anyLink;

	@FindBy(xpath = "//a[@data-autoid='Save_0']")
	private WebElement saveAutoDocButton;

	@FindBy(xpath = "//span[@title='Read Only Rules']")
	private WebElement readOnlyRules;

	@FindBy(xpath = "//a[@data-autoid='LINK_NEWPROCESS_READONLYRULE0']")
	private WebElement new_readRule;

	@FindBy(how = How.XPATH, using = "//input[@data-autoid='TEXT1_ctrl']")
	public WebElement rule_Name;

	@FindBy(xpath = "//div[@data-testid='switch-text']")
	private WebElement isActiveStatus;

	@FindBy(xpath = "//div[@data-testid='switch-box']")
	private WebElement isActiveToggle;

	@FindBy(xpath = "//div[contains(@class, 'showrecords') and contains(., 'Showing')]")
	private WebElement totalRecords;

	public void clickOnSearchIcon() {
		ReUsableMethods.webClickElement(customFieldPicker, "Picker");
	}

	public void clickOnAutoDocSettings() throws InterruptedException {

		ReUsableMethods.webClickElement(autoDoCDocument_SettingsTab, "Auto Doc Setting Tab");
	}

	public void makeActive() {

		System.out.println("The value is " + isActiveStatus.getText().trim());
		if (isActiveStatus.getText().trim().equalsIgnoreCase("OFF")) {
			ReUsableMethods.webClickElement(isActiveToggle, "Is Active Toggle Button");
		}

		{
			System.out.println("The toggle is active");
		}
	}

	public String createNewAutoDocRule(String autoDocRule) throws InterruptedException {

		ReUsableMethods.webClickElement(newAutoDocRule, "New Button");
		ReUsableMethods.switchToChildWindowHandle();

		String autoDocRuleName = autoDocRule + ReUsableMethods.getCurrentdateTime();

		ReUsableMethods.webEnterTextwithFluentwait(inputAutoDocRuleName, autoDocRuleName, "Auto Doc Name Input");
		ReUsableMethods.webEnterTextwithFluentwait(defaultMessageAutoDocRule, "Test", "Default message AutoDoC Input");
		makeActive();
		ReUsableMethods.webClickElement(save_AutoDocRuleBUtton, "Save AutoDoc Rule Button");
		ReUsableMethods.waitforWindowSize(1);
		return autoDocRuleName;

	}

	public void setFilter_onAutoDocRuleCreation(String filterParmeter) throws InterruptedException {

		ReUsableMethods.webClickElement(selectLeadFilterToAddFilter_Button, "Select Lead Filter Button");
		ReUsableMethods.webEnterTextwithFluentwait(searchFilterParameter, filterParmeter, "Search Box");

		ReUsableMethods.webClickElement(searchArrowIcon, "Search Icon");

		selectCheckbox();
		ReUsableMethods.webClickElement(okButton, "OK Button");
		ReUsableMethods.webSelectByVisibleText(selectOperator_ForAddressParameter, "Is Specified", "operator");

		// ReUsableMethods.webClickElement(save_AutoDocRuleBUtton, "Save AutoDoc Rule
		// Button");

	}

	public void saveAutoDocRule(String filterParmeter) throws InterruptedException {

		ReUsableMethods.webClickElement(save_AutoDocRuleBUtton, "Save AutoDoc Rule Button");

	}

	public void selectCheckbox() {
		wdriver.findElement(
				RelativeLocator.with(By.tagName("label")).toLeftOf(By.xpath("(//div[@title='Last Name'])[1]"))).click();
	}

	public void saveAutoDocRule() throws InterruptedException {
		ReUsableMethods.webClickElement(save_AutoDocRuleBUtton, "Save AutoDoc Button");
		// ReUsableMethods.switchToWindowHandle();
		ReUsableMethods.waitforWindowSize(1);
	}

	public void clickOnThreeDotsAgainst_AutoDocRule(String autoDocRule) throws InterruptedException {
		String baseXpath = "//div[div[contains(@data-autoid, 'RuleName_') and text()='AUTODOCRULENAME']]//following::div[contains(@data-autoid, '_AI')][1]";
		String actualXpath = baseXpath.replaceAll("AUTODOCRULENAME", autoDocRule);
		Thread.sleep(2000);
		ReUsableMethods.webClickElement(wdriver.findElement(By.xpath(actualXpath)), "autoDocRule");

	}

	public void clickOnConfigure_AutoDocRuleLink() {
		ReUsableMethods.webClickElement(configureAutoDocRule_Link, "Configure AutoDocRule Link");
	}

	public void selectCustomField(String customFieldName) {
		try {
			Thread.sleep(1000);
			// ReUsableMethods.webClickElement(customFieldPicker, "Custom Field Picker");
			new CommonProductFunctions(DriverManager.getWdriver()).clickOnSearchPicker("Select Custom Field");
			String baseXpath = "//div[text()='FieldName']";
			String xpathCustomField = baseXpath.replaceAll("FieldName", customFieldName);

			WebElement autodocCreated = wdriver.findElement(By.xpath(xpathCustomField));
			ReUsableMethods.scrollElementToCentreOfScreen(autodocCreated);
			ReUsableMethods.webClickElement(autodocCreated, "Custom Field" + customFieldName);
			;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception encountered while selecting custom field" + e);
		}
	}

	public boolean isAutoDocPattern_MappedWithCustomFieldVisible(String autoDocPatternName) {
		try {

			ReUsableMethods.webEnterText(searchAutoDocPattern, autoDocPatternName, "Search Box AutoDocPattern");
			String baseXpath_AutoDocpattern = "//div[@draggable='true'][normalize-space()='AutoDoc']";
			String xpath_AutoDocPattern = baseXpath_AutoDocpattern.replaceAll("AutoDoc", autoDocPatternName);

			System.out.println("the autodoc dislayed is "
					+ wdriver.findElement(By.xpath(xpath_AutoDocPattern)).isDisplayed() + "and value of auto doc is"
					+ wdriver.findElement(By.xpath(xpath_AutoDocPattern)).getText());
			return wdriver.findElement(By.xpath(xpath_AutoDocPattern)).isDisplayed();
		} catch (Exception e) {
			System.out.println("The exception encountered is " + e);
			return false;
		}

	}

	public void dragAutoDocToMandatory(String autoDocPatternName) throws InterruptedException, AWTException {
		String baseXpath_AutoDocpattern = "//div[contains(text(),'AutoDoc')]";
		String xpath_AutoDocPattern = baseXpath_AutoDocpattern.replaceAll("AutoDoc", autoDocPatternName);
		ReUsableMethods.webDragAndDrop(wdriver.findElement(By.xpath(xpath_AutoDocPattern)), mandatoryTab);
	}

	public boolean isAutoDocPresent_InMandatoryTab(String autoDocPatternName) {
		try {
			String baseXpath = "//div[@class='flex flex-wrap flex-1']//div[text()='AutoDoc']";
			String actualXpath = baseXpath.replaceAll("AutoDoc", autoDocPatternName);

			WebElement autoDocInMandatoryPanel = wdriver.findElement(By.xpath(actualXpath));
			return ReUsableMethods.WebIsElementDisplayed(autoDocInMandatoryPanel, "autoDocInMandatoryPanel");
		}

		catch (Exception e) {
			return false;
		}

	}

	public void save_AutoDocConfiguration() {
		ReUsableMethods.webClickElement(save_AutoDocConfigurationButton, "Save Button");
	}

	public void deleteAutoDocpattern() {
		ReUsableMethods.webClickElement(deleteIcon_AutoDocPattern, "Delete Icon");
	}

	public boolean verifyIfmandatoryLinkisPresent() {
		System.out.println("The mandatory is enabled " + mandatoryTab.isEnabled());
		return mandatoryTab.isEnabled();
	}

	public boolean verifyIfAnyLinkisPresent() {
		System.out.println("The any link is enabled " + anyLink.isEnabled());
		return anyLink.isEnabled();
	}

	public boolean verifyIfCustomFieldPickerisPresent() {
		return customFieldPicker.isEnabled();
	}

	public boolean verifyIf_IsActiveToggleIsPresent() {
		System.out.println("The is active enabled " + isActive.isEnabled());
		return isActive.isEnabled();
	}

	public boolean isAutoDocIsCreated(String autoDoc) {
		String baseXpath = "//div[contains(text(),'AutoDocName')]";
		String expectedXpath = baseXpath.replaceAll("AutoDocName", autoDoc);
		return wdriver.findElement(By.xpath(expectedXpath)).isDisplayed();
	}

	public void clickOnReadOnlyRules_Tab() {
		ReUsableMethods.webClickElement(readOnlyRules, "rule");
	}

	public void createNewReadOnlyRules() throws InterruptedException {
		Thread.sleep(1000);
		ReUsableMethods.webClickElement(new_readRule, "rule");
		ReUsableMethods.switchToChildWindowHandle();
	}

	public String getTotalRecords() {
		return ReUsableMethods.WebGetElementText(totalRecords, "Total");
	}

}
