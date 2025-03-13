package com.Amazon.Pages;

import org.json.JSONObject;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.Amazon.utils.WebDriverUtils.*;


public class AddressPage {

    // Locators
    private By addressPopUp = By.cssSelector("div#a-popover-content-3");
    //    private  By addNewAdress = By.id("add-new-address-popover-link");
//    private  By addAddressButton = By.id("add-new-address-desktop-sasp-tango-link");
    private By fullNameField = By.cssSelector("input#address-ui-widgets-enterAddressFullName");
    private By phoneNumberField = By.cssSelector("input#address-ui-widgets-enterAddressPhoneNumber");
    private By streetNameField = By.cssSelector("input#address-ui-widgets-enterAddressLine1");
    private By buildingNameField = By.cssSelector("input#address-ui-widgets-enter-building-name-or-number");
    private By cityField = By.id("address-ui-widgets-enterAddressCity");
    private By districtField = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    private By nearestLandMarkField = By.id("address-ui-widgets-landmark");
    private By defaultAddressCheckbox = By.id("address-ui-widgets-use-as-my-default");
    private By useThisAddressBtn = By.cssSelector("span#checkout-primary-continue-button-id input[data-csa-c-slot-id='address-ui-widgets-continue-address-btn-bottom']");
    private By declinePrimeBtn = By.cssSelector("div#no-thanks-container a#prime-declineCTA");


    public void setFullName(String fullName) {
        waitForElementLocated(fullNameField, 20);
        findSingleElement(fullNameField).sendKeys(fullName);
    }

    public void setPhoneNumber(String phoneNumber) {
        waitForElementLocated(phoneNumberField, 20);
        findSingleElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void setStreetName(String streetName) {
        waitForElementLocated(streetNameField, 20);
        findSingleElement(streetNameField).sendKeys(streetName);
    }

    public void setBuildingName(String buildingName) {
        waitForElementLocated(buildingNameField, 20);
        findSingleElement(buildingNameField).sendKeys(buildingName);
    }

    public void setCity(String city) {
        waitForElementLocated(cityField, 20);
        findSingleElement(cityField).sendKeys(city);
    }

    public void setDistrict(String district) {
        waitForElementLocated(districtField, 20);
        findSingleElement(districtField).sendKeys(district);
    }

    public void setNearestLandMark(String nearestLandMark) {
        waitForElementLocated(nearestLandMarkField, 20);
        findSingleElement(nearestLandMarkField).sendKeys(nearestLandMark);
    }

    public void parseAndFillJsonData() throws IOException {
        try {
            // Read the entire file content >>> string
            String content = new String(Files.readAllBytes(Paths.get("src/test/java/Test Data/AddressDetails.json")));
            JSONObject jsonObject = new JSONObject(content);

            setFullName(jsonObject.getString("fullName"));
            setPhoneNumber(jsonObject.getString("phoneNumber"));
            setStreetName(jsonObject.getString("streetName"));
            setBuildingName(jsonObject.getString("buildingName"));
            setCity(jsonObject.getString("city"));
            setDistrict(jsonObject.getString("district"));
            setNearestLandMark(jsonObject.getString("nearestLandMark"));

            clickDefaultAddressCheckbox();
            waitForElementToBeClickableAndClick(useThisAddressBtn, 20);

        } catch (IOException e) {
            System.err.println("Error parsing JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void declinePrime() {
        try {
            if (findSingleElement(declinePrimeBtn).isDisplayed()) {
                waitForElementToBeClickableAndClick(declinePrimeBtn, 30);
            }
        } catch (Exception e) {
            System.out.println("Decline Prime Page didn't show up" + e.getMessage());
        }
    }

    public void clickDefaultAddressCheckbox() {
        try {
            if (findSingleElement(defaultAddressCheckbox).isEnabled() && findSingleElement(defaultAddressCheckbox) != null) {
                waitForElementToBeClickableAndClick(defaultAddressCheckbox, 20);
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Checkbox not enabled!");
        }
    }
}
