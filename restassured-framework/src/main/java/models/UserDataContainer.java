package models;

import com.fasterxml.jackson.annotation.JsonProperty;

//separate class including the data property that contains all the fields
public class UserDataContainer {

    @JsonProperty("data")
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserResponse{data=" + data + "}";
    }
}