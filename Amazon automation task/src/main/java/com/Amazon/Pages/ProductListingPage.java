package com.Amazon.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.Amazon.utils.WebDriverUtils.*;

public class ProductListingPage {

    private By priceElement = By.xpath("//div[contains(@class, 'puisg-col-inner')]   " +
            "  [.//span[contains(@class, 'a-price-whole') and number(translate(., ',', '')) < 15000]]   " +
            "  [.//button[contains(@aria-label, 'Add to cart')]]  " +
            "   //span[contains(@class, 'a-price-whole')]"); //filtering based on 15k then retrieve only the spans containing corrresponding add to cart
    private By addToCartButton = By.xpath("//div[contains(@class, 'puisg-col-inner')]  " +
            "   [.//span[contains(@class, 'a-price-whole') and number(translate(., ',', '')) < 15000]]   " +
            "  //button[contains(@aria-label, 'Add to cart')]");

    private By elementNameProductListing = By.xpath("//div[contains(@class, 'puisg-col-inner')]   [.//span[contains(@class, 'a-price-whole') and number(translate(., ',', '')) < 15000]]   [.//button[contains(@aria-label, 'Add to cart') and contains(@id, 'a-autoid')]] //a[contains(@class, 'a-link-normal')] //h2");

    private By resutlsHeader = By.cssSelector("span[data-component-id='25']");

    private By nextBtn = By.xpath("//a[text()='Next']");

    protected List<WebElement> addToCartButtons;
    protected List<WebElement> priceElements;
    protected List<WebElement> elementNamesProductListing;
    protected List<String> elementNamesAsStringsProductListing = new ArrayList<String>();
    public double totalPrice;


    public void clickAddToCartButtons() {
        for (WebElement button : addToCartButtons) {
            waitForWebElementToBeClickable(button, 10);
            button.click();
            System.out.println("Clicked 'Add to cart' button.");
            // Pause for 2 seconds between clicks
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
        }
    }

    public double calculateTotalPrice() {
        priceElements = findMultipleElements(priceElement);
        totalPrice = 0.0;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            double price = parsePrice(priceText); // Parse
            totalPrice += price; // Add to the total
            System.out.println("Price: " + price);
        }

        return totalPrice;
    }

    public void clickAddAndCalculatePrice() {
        while (true) {
            try {
                addToCartButtons = findMultipleElements(addToCartButton);
                if (!addToCartButtons.isEmpty()) {
                    clickAddToCartButtons();
                    totalPrice = calculateTotalPrice();
                    System.out.println("Total Price: " + totalPrice);

                    elementNamesAsStringsProductListing = extractTextFromListOfElements();
                    System.out.println("Extracted Texts: " + elementNamesAsStringsProductListing);
                    break;
                } else {
                    System.out.println("No 'Add to Cart' buttons found. Clicking 'Next' button.");
                    clickOnElement(nextBtn);

                    // Wait for the next page to load
                    Thread.sleep(3000);
                }
            } catch (NoSuchElementException e) {
                System.out.println("No elements found. Clicking 'Next' button.");
                clickOnElement(nextBtn);

            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }

    public int getNumberOfAddCartClicked() {
        return addToCartButtons.size();
    }

    public List<String> extractTextFromListOfElements() {
        elementNamesProductListing = findMultipleElements(elementNameProductListing);

        for (WebElement element : elementNamesProductListing) {
            elementNamesAsStringsProductListing.add(element.getText().trim());
        }
        return elementNamesAsStringsProductListing;
    }
}
