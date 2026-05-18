
package com.businessnext.objects.lead.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.common.pages.CommonProductFunctions;
import com.utilities.ReUsableMethods;

public class LeadDetailPageWithCustomField {

	WebDriver wdriver;

	public LeadDetailPageWithCustomField(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	// Element Functions

	@FindBy(xpath = "//label[@title='Auto_Age_from_To']/ancestor::div[contains(@data-autoid,'cust')]//span[@title='61 D 0 H 0 M ']")
	public WebElement auto_Age_From_To;

	@FindBy(xpath = "//label[@title='Auto_temp_text']/parent::div//div[contains(@class,'acid-shadow-dom')]")
	public WebElement text_NormalPickerOnDetail;

	@FindBy(xpath = "//label[@title='Auto_Temp_HTML']/parent::div//div[contains(@class,'acid-shadow-dom')]")
	public WebElement html_NormalPickerOnDetail;

	@FindBy(xpath = "//*[@data-autoid='cust_1140_ctrl']")
	public WebElement aut_Sla;

	@FindBy(xpath = "//label[@title='Aut_weburl']/ancestor::div[contains(@data-autoid,'cust')]//div")
	public WebElement webUrlOnDetail;

	@FindBy(xpath = "//label[@title='Aut_GeoLocation']/ancestor::div[contains(@data-autoid,'cust')]//i")
	public WebElement jioLocation;

	@FindBy(xpath = "//label[@title='Aut_Image']/ancestor::div[contains(@data-autoid,'cust')]//img")
	public WebElement imageOnAut;

	@FindBy(xpath = "//label[@title='Aut_Barcode']/ancestor::div[contains(@data-autoid,'cust')]//div")
	public WebElement barCode;

	@FindBy(xpath = "//div[@data-testid='dialog-footer']//a[text()='Continue']")
	public WebElement continueText;

	public String getWebUrlOnDetail() {
		return ReUsableMethods.WebGetElementText(webUrlOnDetail, "Web Url");
	}

	public boolean isDisplayedAutoAge() {
		return ReUsableMethods.WebIsElementDisplayed(auto_Age_From_To, "Auto Age");
	}

	public String getAutSla() {
		return ReUsableMethods.WebGetElementText(aut_Sla, "SLA");
	}

	public String getText_NormalPicker() {
		return ReUsableMethods.WebGetElementText(text_NormalPickerOnDetail, "Normal Picker Text");
	}

	public void clickJioLocation() {
		ReUsableMethods.webClickElement(jioLocation, "Jio Location");
	}

	public void clickContinue() {
		ReUsableMethods.webClickElement(continueText, "Continue");
	}

	public String getHtml_NormalPicker() {
		return ReUsableMethods.WebGetElementText(html_NormalPickerOnDetail, "Normal Picker Html");
	}

	@FindBy(xpath = "//label[@data-autoid='LE_URL_lbl']")
	public WebElement specifiedBy;

	String preXpath = "//label[@title='";
	String postXpath = "']/ancestor::div[contains(@data-autoid,'cust')]//div/div";

	public WebElement returnWebElementForCustomFieldOnDetail(String title) {
		String xpath = preXpath + title + postXpath;
		List<WebElement> ele = ReUsableMethods.findElementByPath(xpath);
		return ele.get(0);
	}

	public String getCustomFieldDataOnDetailPage(String title) {
		ReUsableMethods.scrollElementToCentreOfScreen(returnWebElementForCustomFieldOnDetail(title));
		return ReUsableMethods.WebGetElementText(returnWebElementForCustomFieldOnDetail(title), title);

	}

	String preXpathCheckbox = "//label[@title='Aut_Boolean']/following::*[@name='";
	String postXpathCheckbox = "']";

	public WebElement booleanCheckbox(String name) {
		String xpath = preXpathCheckbox + name + postXpathCheckbox;
		WebElement ele = ReUsableMethods.findElementByPath(xpath).get(0);
		return ele;

	}

	public boolean isDisplayedBooleanCheckbox(String name) throws InterruptedException {
		return ReUsableMethods.WebIsElementDisplayed(booleanCheckbox(name), "Checkbox on Detail Page");

	}

	@FindBy(xpath = "//*[@data-autoid='cust_1324_ctrl']")
	public WebElement aut_DateSet;

	public String getAut_DateSet() {
		return ReUsableMethods.WebGetElementText(aut_DateSet, "aut_DateSet");
	}

	public void clickOnCustomFieldDataOnDetailPage(String title) {
		ReUsableMethods.scrollElementToCentreOfScreen(returnWebElementForCustomFieldOnDetail(title));
		ReUsableMethods.webClickElement(returnWebElementForCustomFieldOnDetail(title), title);

	}

	@FindBy(xpath = "//span[@data-autoid='cust_973_ctrl']")
	public WebElement aut_text;

	public String getAut_Text() {
		return ReUsableMethods.WebGetElementText(aut_text, "aut_text");
	}

	String SHADOW_CUSTOM_PAGE_DISCUSSION_THREAD = "div.discussionThread__text-content";

	String SHADOW_LONG_TEXT = "div.form-element__control.overflow-auto";

	public String getShadow_WithTitle(String title) {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);
		return ReUsableMethods.WebGetElementText(
				commonProductFunctions.getShadowElement(title, SHADOW_LONG_TEXT),
				"title");
	}

	public String getShadow_Aggregation(String title) {
		CommonProductFunctions commonProductFunctions = new CommonProductFunctions(wdriver);
		return ReUsableMethods.WebGetElementText(
				commonProductFunctions.getShadowElement(title, SHADOW_CUSTOM_PAGE_DISCUSSION_THREAD),
				"Aut_Aggregation");
	}

public boolean performActionAsPerText(String text, boolean shouldClick) {

    List<WebElement> elements = ReUsableMethods.findElementByPath(
            String.format("//div[@data-testid='dialog-container']//*[text()=\"%s\"]", text));

    if (elements == null || elements.isEmpty()) {
        return false;
    }

    WebElement el = elements.get(0);
    ReUsableMethods.scrollElementToCentreOfScreen(el);

    if (shouldClick) {
        ReUsableMethods.webClickElement(el, text);
        return true;
    }

    return ReUsableMethods.WebIsElementDisplayed(el, text);
}
}