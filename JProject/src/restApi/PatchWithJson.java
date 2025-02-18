package restApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PatchWithJson {

    private static final String BASE_URI = "https://restful-booker.herokuapp.com";
    private static final String AUTH_ENDPOINT = "/auth";
    private static final String BOOKING_ENDPOINT = "/booking";

    @Test
    public void testUpdateBookingWithJSON() {
        // Set the base URI
        RestAssured.baseURI = BASE_URI;

        // Create an auth token
        String token = createAuthToken();

        // Booking ID to update (replace with a valid booking ID)
        int bookingId = 1625;

        // Path to JSON file containing the updated payload
        File jsonPayload = new File("src/test/resources/updateBookingPayload1.json");

        // Send PUT request to update the booking
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(jsonPayload)
                .patch(BOOKING_ENDPOINT + "/" + bookingId);

        // Print response for debugging
        System.out.println("Response Body:");
        System.out.println(response.asString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200.");

        // Validate some updated fields in the response
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Ronit", "Firstname does not match!");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "Doe", "Lastname does not match!");
        Assert.assertEquals(response.jsonPath().getString("additionalneeds"), "Breakfast", "Breakfasr does not match!");
    }

    private String createAuthToken() {
        // Payload for authentication
        String authPayload = "{ \"username\": \"admin\", \"password\": \"password123\" }";

        // Send POST request to /auth to generate a token
        Response authResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .post(AUTH_ENDPOINT);

        Assert.assertEquals(authResponse.getStatusCode(), 200, "Failed to generate auth token!");
        return authResponse.jsonPath().getString("token");
    }
}
