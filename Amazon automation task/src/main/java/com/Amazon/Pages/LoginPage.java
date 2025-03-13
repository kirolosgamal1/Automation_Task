package com.Amazon.Pages;

import org.openqa.selenium.By;

import static com.Amazon.utils.WebDriverUtils.*;

public class LoginPage {

    private By emailField = By.xpath("//input[contains(@id,'ap_email')]");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By signInSubmitButton = By.id("signInSubmit");

    public void login(String username, String password) {
        waitForElementLocated(emailField, 10);
        typing(emailField, username);
        waitForElementClickable(continueButton, 5);
        clickOnElement(continueButton);
        waitForElementLocated(passwordField, 10);
        typing(passwordField, password);
        waitForElementClickable(signInSubmitButton, 10);
        clickOnElement(signInSubmitButton);
    }

}