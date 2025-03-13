package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Specific model for the update user
@JsonIgnoreProperties(ignoreUnknown = true)  // createdAt is returned with null when updating the user so ignoring it
public class UserUpdateResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("job")
    private String job;

    @JsonProperty("updatedAt")
    private String updatedAt;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserUpdateResponse{id=" + id  + "', firstName='" + firstName + "', job='" + job + "', updatedAt='" + updatedAt + "'}";
    }
}