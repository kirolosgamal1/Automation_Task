package com.Amazon.Pages;

import org.openqa.selenium.By;

import static com.Amazon.utils.WebDriverUtils.*;

public class HomePage {
    private By languageDropdown = By.id("icp-nav-flyout");
    private By englishLanguageOption = By.cssSelector("div#nav-flyout-icp i[class='icp-radio']");

    private By allMenuButton = By.cssSelector("a#nav-hamburger-menu");
    private By signInButton = By.cssSelector("a#nav-link-accountList[data-nav-role=\"signin\"]");

    private By videoGamesLink = By.xpath("//div[text()='Video Games']");
    public By cartButton = By.cssSelector("a#nav-cart");


    public void changeLangIntoEnglish() {

        waitForElementVisible(languageDropdown, 20);
        hoverOverElement(languageDropdown);
        clickOnElement(englishLanguageOption);


    }

    public void openAllMenu() {

        waitForElementLocated(allMenuButton, 20);
        clickOnElement(allMenuButton);
    }

    public void clickOnSignIn() {
        try {
            scrollToElement(signInButton);
            waitForElementLocated(signInButton, 20);
            waitForElementToBeClickableAndClick(signInButton, 20);
        } catch (Exception e) {
            waitForElementLocated(signInButton, 20);
            clickOnElement(signInButton);
        }
    }
}