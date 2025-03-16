package com.Amazon.Pages;

import com.Amazon.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.Amazon.utils.WebDriverUtils.*;


public class CartPage {

    private By cartCount = By.cssSelector("a#nav-cart");
    private By proceedToCheckoutButton = By.cssSelector("input[data-feature-id='proceed-to-checkout-action']");
    private By totalAmount = By.cssSelector("span#sc-subtotal-amount-activecart");
    private By deleteButton = By.cssSelector("input[value='Delete']");
    private By deselectAllButton = By.cssSelector("a#deselect-all");
    private By selectAllButton = By.cssSelector("a#select-all");

    public HomePage emptyCart() {
        //To empty the cart prior to begin testing
        List<WebElement> deleteButtons = WebDriverUtils.findMultipleElements(deleteButton);
        for (WebElement button : deleteButtons) {
            WebDriverUtils.refreshPage();
            button = findSingleElement(deleteButton);
            waitForWebElementToBeClickable(button, 50);
            jsClick(button);
            System.out.println("Clicked 'Delete' button, refreshing the page to prevent Staleness issues");
        }
        return new HomePage();
    }

    public int getCartItemCount() {
        String text = findSingleElement(cartCount).getText();
        // Remove all non-digit characters, leaving only the digits
        String numericPart = text.replaceAll("\\D", "");
        return Integer.parseInt(numericPart);
    }

    public void proceedToCheckout() {
        findSingleElement(proceedToCheckoutButton).click();
    }

    public double getTotalAmount() {
        return parsePrice(findSingleElement(totalAmount).getText());
    }
}