package com.Amazon.Pages;

import com.Amazon.utils.WebDriverUtils;
import org.openqa.selenium.*;

import static com.Amazon.utils.WebDriverUtils.findSingleElement;
import static com.Amazon.utils.WebDriverUtils.waitForElementToBeClickableAndClick;

public class CheckOutPage {
    private By checkouHeader = By.cssSelector("div#nav-checkout-title");
    private By cashOnDeliveryRadio = By.xpath("//div[@class='a-box pmts-instrument-box'] " +
            "[.//span[contains(text(), 'COD')]]" +
            " //input");

    private By useThisPayment = By.cssSelector("span#checkout-primary-continue-button-id input");
    private By changeAddress = By.cssSelector("a[aria-label='Change delivery address']");
    private By addNewAddressButton = By.cssSelector("a#add-new-address-desktop-sasp-tango-link");

    private By checkOutOrderTotalDiv = By.xpath("//div[@class='order-summary-grid']" +
            " [.//span[contains(text(), 'Order total')]] " +
            "//div[@class='order-summary-line-definition']");

    private By primaryAddress = By.cssSelector("span#deliver-to-address-text");


    public boolean isCheckoutHeaderVisible() {
        try {
            WebElement element = findSingleElement(checkouHeader);
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Checkout header is not visible: " + e.getMessage());
            return false;
        }
    }

    public void selectCOD() {
        try {
            WebElement codRadio = findSingleElement(cashOnDeliveryRadio);

            if (codRadio == null) {
                throw new NoSuchElementException("Cash on Delivery option not found on the page.");
            }

            if (!codRadio.isEnabled()) {
                throw new ElementNotInteractableException("Cash on Delivery option is present but disabled.");
            }

            waitForElementToBeClickableAndClick(cashOnDeliveryRadio, 20);
            waitForElementToBeClickableAndClick(useThisPayment, 20);
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ElementNotInteractableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean isPrimaryAddressEmpty() {
        try {
            String primaryAddressText = findSingleElement(primaryAddress).getText();
            if (primaryAddressText.isEmpty()) {
                return true;
            } else
                return false;
        } catch (TimeoutException T) {
            T.getMessage();
        }
        return false;
    }

    public void checkExistingAddressAndCreateNewAddress() {
        if (!isPrimaryAddressEmpty()) {
            waitForElementToBeClickableAndClick(changeAddress, 20);
            waitForElementToBeClickableAndClick(addNewAddressButton, 20);
        }
    }

    public double getCheckOutTotalPrice() {
        return WebDriverUtils.parsePrice(findSingleElement(checkOutOrderTotalDiv).getText());
    }
}

