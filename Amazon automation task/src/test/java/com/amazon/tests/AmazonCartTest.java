package com.amazon.tests;

import com.Amazon.Pages.*;
import com.Amazon.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.Amazon.utils.WebDriverUtils.refreshPage;
import static com.Amazon.utils.WebDriverUtils.waitForElementToBeClickableAndClick;


public class AmazonCartTest {
    private HomePage homePage;
    private LoginPage loginPage;
    private DepartmentPage departmentPage;
    private FiltersPage filtersPage;
    private ProductListingPage productListingPage;
    private CartPage cartPage;
    private AddressPage addressPage;
    private CheckOutPage checkoutPage;
    private double pricesExpectedFromProductListing;
    private int clickedAddToCart;
    private int cartCount;
    private double totalPriceOnCartPage;
    private WebDriver driver = WebDriverUtils.getDriver();

    @BeforeClass
    public void setUp() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        departmentPage = new DepartmentPage();
        filtersPage = new FiltersPage();
        productListingPage = new ProductListingPage();
        cartPage = new CartPage();
        checkoutPage = new CheckOutPage();
        addressPage = new AddressPage();
        WebDriverUtils.maximizeScreen();

    }

    @Test
    public void testCartWorkflow() {

        homePage = navigateToHomePage();
        homePage.changeLangIntoEnglish();
        refreshPage();
        homePage.clickOnSignIn();
        loginPage.login(
                WebDriverUtils.getProperty("username"),
                WebDriverUtils.getProperty("password")
        );
        navigateToHomePage();
        if (cartPage.getCartItemCount() != 0) {

            cartPage.getCartItemCount();
            goToCart();
            cartPage.emptyCart();
        }

        departmentPage.navigateToAllVideoGames();
        filtersPage.applyFiltersAndSort();

        // Add Products < 15,000 EGP
        productListingPage.clickAddAndCalculatePrice();
        pricesExpectedFromProductListing = productListingPage.totalPrice;

        clickedAddToCart = productListingPage.getNumberOfAddCartClicked();
        goToCart();
        cartCount = cartPage.getCartItemCount();
        totalPriceOnCartPage = cartPage.getTotalAmount();

        Assert.assertEquals(cartCount, clickedAddToCart, "Error! Clicked add to cart button is not eqaul to items count on cart page");
        Assert.assertEquals(totalPriceOnCartPage, pricesExpectedFromProductListing, "Error! Total price on cart page doesn't match the sum of prices from products page");

        // Step 6: Proceed to Checkout
        cartPage.proceedToCheckout();
        addressPage.declinePrime();
        if (checkoutPage.isCheckoutHeaderVisible()) { //check if secure checkout header is visible
            if (!checkoutPage.isPrimaryAddressEmpty()) {

                checkoutPage.checkExistingAddressAndCreateNewAddress();
            }
            try {
                addressPage.parseAndFillJsonData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Verify Total Amount
        //won't check on delivery fees as first order is always free shipping so can be ignored for now
        Assert.assertEquals(checkoutPage.getCheckOutTotalPrice(), totalPriceOnCartPage);


    }

    @AfterClass
    public void tearDown() {
        WebDriverUtils.quitDriver();
    }

    public HomePage navigateToHomePage() {
        WebDriverUtils.navigateTo(WebDriverUtils.getProperty("baseUrl"));
        return new HomePage();
    }

    public void goToCart() {
        waitForElementToBeClickableAndClick(homePage.cartButton, 20);
        refreshPage();
    }

}