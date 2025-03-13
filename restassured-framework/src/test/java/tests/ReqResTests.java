package tests;

import api.ApiHelper;
import api.responses.ApiResponse;
import models.User;
import models.UserUpdateResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ReqResTests {

    @Test(priority = 1)
    public void testCreateUser() {
        ApiHelper apiHelper = new ApiHelper();
        SoftAssert softAssert = new SoftAssert();

        // Create a new user
        User newUser = new User("John", "Software Engineer");
        ApiResponse createResponse = apiHelper.createUser(newUser);
        softAssert.assertEquals(createResponse.getStatusCode(), 201, "User creation failed");

        // Deserialize the response into a User object
        User createdUser = createResponse.getBodyAs(User.class);

        softAssert.assertEquals(createdUser.getFirstName(), newUser.getFirstName(), "First name does not match");
        softAssert.assertEquals(createdUser.getJob(), newUser.getJob(), "Job does not match");

        softAssert.assertNotNull(createdUser.getCreatedAt(), "createdAt should not be null");

        System.out.println("Created User: " + createdUser);

        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testGetUserById() {
        ApiHelper apiHelper = new ApiHelper();
        int userId = 2;

        ApiResponse response = apiHelper.getUserById(userId);
        Assert.assertEquals(response.getStatusCode(), 200, "User retrieval failed");

        System.out.println("Retrieved User: " + response.getBodyAsString());
    }

    @Test(priority = 3)
    public void testUpdateUser() {
        ApiHelper apiHelper = new ApiHelper();
        SoftAssert softAssert = new SoftAssert();

        int existingUserID = 2;
        ApiResponse getExistingUserResponse = apiHelper.getUserById(existingUserID);
        System.out.println("Existing User: " + getExistingUserResponse.getBodyAsString());

        User updatedUser = new User("Kirolos", "SDET");
        ApiResponse updateResponse = apiHelper.updateUser(existingUserID, updatedUser);

        softAssert.assertEquals(updateResponse.getStatusCode(), 200, "User update failed");

        UserUpdateResponse updatedUserResponse = updateResponse.getUpdatedUserData();
        //  updatedUserResponse.setId(existingUserID);

        softAssert.assertEquals(updatedUserResponse.getId(), existingUserID, "User ID should not change");   //will fail cause reqres.in doesn't retain data
        softAssert.assertEquals(updatedUserResponse.getFirstName(), updatedUser.getFirstName(), "First name does not match");
        softAssert.assertEquals(updatedUserResponse.getJob(), updatedUser.getJob(), "Job does not match");

        softAssert.assertNotNull(updatedUserResponse.getUpdatedAt(), "updatedAt should not be null");

        System.out.println("Updated User: " + updatedUserResponse);

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testRetrieveUser_NotFound() {
        ApiHelper apiHelper = new ApiHelper();
        SoftAssert softAssert = new SoftAssert();

        int invalidUserId = 9999;
        ApiResponse response = apiHelper.getUserById(invalidUserId);

        softAssert.assertEquals(response.getStatusCode(), 404, "Expected 404 for invalid user ID");

        softAssert.assertAll();
    }


}

