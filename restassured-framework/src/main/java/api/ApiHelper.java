package api;

import api.requests.ApiRequest;
import api.responses.ApiResponse;
import models.User;

public class ApiHelper {

    private ApiRequest apiRequest;

    public ApiHelper() {
        apiRequest = new ApiRequest();
    }

    public ApiResponse createUser(User user) {
        return new ApiResponse(apiRequest.post(utils.Config.USERS_ENDPOINT, user));
    }

    public ApiResponse getUserById(int userId) {
        return new ApiResponse(apiRequest.get(utils.Config.USERS_ENDPOINT + "/" + userId));
    }

    public ApiResponse updateUser(int userId, User user) {
        return new ApiResponse(apiRequest.put(utils.Config.USERS_ENDPOINT + "/" + userId, user));
    }
}