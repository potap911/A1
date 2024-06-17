package org.potapenko;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A1Page {
    private WebDriver driver;
    private JavascriptExecutor js;

    @FindBy(xpath = "//button[contains(@data-action-button,'acceptAll')]")
    private WebElement buttonAcceptAll;

    @FindBy(xpath = "//input[@class='form-input']")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[contains(@class,'is-icon-only')]")
    private WebElement buttonSendEmail;

    @FindBy(id = "toast-error-subscribe")
    private WebElement toastErrorSubscribe;

    @FindBy(xpath = "//div[@id='toast-error-subscribe']/div[1]")
    private WebElement titleErrorSubscribe;

    @FindBy(xpath = "//div[@id='toast-error-subscribe']/div[2]")
    private WebElement textErrorSubscribe;

    @FindBy(xpath = "//div[contains(@class,'success toast-content')]")
    private WebElement toastSuccessSubscribe;

    @FindBy(xpath = "//div[contains(@class,'success toast-content')]/div[1]")
    private WebElement titleSuccessSubscribe;

    @FindBy(xpath = "//div[contains(@class,'success toast-content')]/div[2]")
    private WebElement textSuccessSubscribe;

    public A1Page() {
        driver = Driver.getInstance();
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void scrollDown() {
        js.executeScript("scroll(0, 500);");
    }

    public void clickButtonAcceptAll() {
        js.executeScript("arguments[0].click();", buttonAcceptAll);
    }

    public void clickInputEmail() {
        js.executeScript("arguments[0].click();", inputEmail);
    }

    public void clickButtonSendEmail() {
        js.executeScript("arguments[0].click();", buttonSendEmail);
    }

    public void scriptSendEmail(String email) {
        clickButtonAcceptAll();
        scrollDown();
        clickInputEmail();
        getInputEmail().sendKeys(email);
        clickButtonSendEmail();
    }

    public WebElement getInputEmail() {
        return inputEmail;
    }

    public WebElement getButtonSendEmail() {
        return buttonSendEmail;
    }

    public WebElement getToastErrorSubscribe() {
        return toastErrorSubscribe;
    }

    public WebElement getTitleErrorSubscribe() {
        return titleErrorSubscribe;
    }

    public WebElement getTextErrorSubscribe() {
        return textErrorSubscribe;
    }

    public WebElement getTextSuccessSubscribe() {
        return textSuccessSubscribe;
    }

    public WebElement getTitleSuccessSubscribe() {
        return titleSuccessSubscribe;
    }

    public WebElement getToastSuccessSubscribe() {
        return toastSuccessSubscribe;
    }
}
