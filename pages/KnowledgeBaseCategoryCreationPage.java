package com.businessnext.knowledgebase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.utilities.ReUsableMethods;

public class KnowledgeBaseCategoryCreationPage {

	WebDriver wdriver;

	public KnowledgeBaseCategoryCreationPage(WebDriver driver) {
		this.wdriver = driver;
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@data-autoid='SUBJECT_ctrl']")
	public WebElement categoryNameField;

	@FindBy(how = How.XPATH, using = "//a[@data-autoid='Save']")
	public WebElement saveIcon;

	@FindBy(xpath = "//div[@data-testid=\"dialog-close\"]//*[local-name()='svg' and @name='cross']")
	public WebElement crossIcon;

	public String enterCategoryName(String categoryName) {
		String nameActual = categoryName + ReUsableMethods.getCurrentdateTime();
		ReUsableMethods.webEnterText(categoryNameField, nameActual, "enter name");
		return nameActual;
	}

}
