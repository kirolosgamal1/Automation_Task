package api.responses;

import io.restassured.response.Response;
import models.User;
import models.UserDataContainer;
import models.UserUpdateResponse;

public class ApiResponse {

    private Response response;

    public ApiResponse(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getBodyAsString() {
        return response.getBody().asString();
    }

    // deserialization
    public <T> T getBodyAs(Class<T> clazz) {
        return response.getBody().as(clazz);
    }

    // Get nested user data from the response {data contains all the fields}
    public User getUserData() {
        UserDataContainer userDataContainer = response.getBody().as(UserDataContainer.class);
        return userDataContainer.getData();
    }

    // updatedAt
    public UserUpdateResponse getUpdatedUserData() {
        return response.getBody().as(UserUpdateResponse.class);
    }

}