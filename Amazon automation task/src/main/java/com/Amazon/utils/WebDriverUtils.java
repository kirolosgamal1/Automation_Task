package com.Amazon.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.lang.System.err;

public class WebDriverUtils {
    private static WebDriver driver;
    private static Properties properties;
    public static final int timeout = 20;

    static {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {

        driver = new ChromeDriver();
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void maximizeScreen() {
        driver.manage().window().maximize();
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static WebElement findSingleElement(By locator) {
        waitForElementLocated(locator, 20);
        return driver.findElement(locator);
    }

    public static void refreshPage() {
        driver.navigate().refresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait until the page is fully loaded
        js.executeScript("return document.readyState").equals("complete");
    }

    public static List<WebElement> findMultipleElements(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            err.println("Exception while waiting for elements: " + e.getMessage());
            return Collections.emptyList();
        }

        // Return the list of elements
        return driver.findElements(locator);
    }

    public static void waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void waitForElementClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeClickableAndClick(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            clickOnElement(locator);
        } catch (ElementClickInterceptedException e) {
            waitForElementClickable(locator, timeoutInSeconds);
            jsClick(locator);
        }
    }

    public static void typing(By locator, String text) {
        waitForElementVisible(locator, 5);
        findSingleElement(locator).sendKeys(text);
    }

    //for webElement
    public static void waitForWebElementToBeClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementLocated(By element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void scrollToElement(By locator) {
        WebElement element = findSingleElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void jsClick(By locator) {
        WebElement element = findSingleElement(locator);
        if (isElementAttached(element)) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } else {
            throw new StaleElementReferenceException("Element is stale - cannot click");
        }
    }

    public static void jsClick(WebElement element) {
        if (isElementAttached(element)) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } else {
            throw new StaleElementReferenceException("Element is stale - cannot click");
        }
    }

    private static boolean isElementAttached(WebElement element) {
        try {
            element.isEnabled(); 
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public static void clickOnElement(By element) {

        waitForElementVisible(element, timeout);
        waitForElementClickable(element, timeout);
        try {
            findSingleElement(element).click();
        } catch (StaleElementReferenceException e) {
            waitForElementClickable(element, timeout);
            jsClick(element);
        }
    }

    public static double parsePrice(String priceText) {
        String cleanedPrice = priceText.replaceAll("[^0-9.,]", ""); // Remove all non-numeric characters except commas and dots
        cleanedPrice = cleanedPrice.replace(",", "");
        return Double.parseDouble(cleanedPrice);
    }
}













