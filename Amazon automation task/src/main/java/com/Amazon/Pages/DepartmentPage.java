package com.Amazon.Pages;

import org.openqa.selenium.By;

import static com.Amazon.utils.WebDriverUtils.*;

public class DepartmentPage {
    private By allMenuButton = By.cssSelector("a#nav-hamburger-menu");
    private By seeAllCategoriesButton = By.cssSelector("a.hmenu-compressed-btn[aria-label*='See All']");
    private By videoGamesLink = By.cssSelector("a[data-menu-id='16'] div");
    private By allVideoGamesLink = By.xpath("//a[text()='All Video Games']");

    public void openAllMenu() {
        waitForElementLocated(allMenuButton, 20);
        clickOnElement(allMenuButton);
    }

    public void navigateToAllVideoGames() {
        waitForElementToBeClickableAndClick(allMenuButton, 20);
        waitForElementToBeClickableAndClick(seeAllCategoriesButton, 20);
        waitForElementToBeClickableAndClick(videoGamesLink, 20);
        waitForElementToBeClickableAndClick(allVideoGamesLink, 20);
    }
}