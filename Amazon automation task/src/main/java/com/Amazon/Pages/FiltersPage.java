package com.Amazon.Pages;

import org.openqa.selenium.By;

import static com.Amazon.utils.WebDriverUtils.*;

public class FiltersPage {

    private By sortDropdown = By.cssSelector("span#a-autoid-0-announce");
    private By highToLowOption = By.xpath("//a[contains(text(),'High to Low')]");
    private By freeShippingCheckbox = By.xpath("//input[@aria-labelledby='Free Shipping']/following-sibling::i");
    private By newConditionButton = By.xpath("//span[text()='New']");

    public void applyFiltersAndSort() {

        waitForElementToBeClickableAndClick(freeShippingCheckbox, 10);
        waitForElementToBeClickableAndClick(newConditionButton, 10);
        waitForElementVisible(sortDropdown, 10);
        clickOnElement(sortDropdown);
        waitForElementToBeClickableAndClick(highToLowOption, 10);

    }

}