package api.requests;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiRequest {

    private RequestSpecification request;

    public ApiRequest() {

        request = RestAssured.given()
                .log().all()
                .baseUri(utils.Config.BASE_URL)
                .contentType(ContentType.JSON)
                .config(RestAssured.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", 5000))); // Connection timeout to handle slow responses/servers
    }

    public Response get(String endpoint) {
        return request.get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        return request.body(body).post(endpoint);
    }

    public Response put(String endpoint, Object body) {
        return request.body(body).put(endpoint);
    }

}