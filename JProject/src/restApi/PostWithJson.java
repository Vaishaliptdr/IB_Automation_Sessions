package restApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PostWithJson {

    @Test
    public void testCreateBookingWithJSONPayload() {
        // Base URI for Restful Booker API
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Path to JSON file containing the payload
        File jsonPayload = new File("src/test/resources/bookingPayload.json");

        // Send POST request to create a new booking
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .post("/booking");

        // Print response for debugging
        System.out.println("Response Body:");
        System.out.println(response.asString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200.");

        // Verify the response contains the same details as in the JSON file
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "Kiara", "Firstname does not match!");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Josh", "Lastname does not match!");
        Assert.assertEquals(response.jsonPath().getInt("booking.totalprice"), 1250, "Total price does not match!");
        Assert.assertTrue(response.jsonPath().getBoolean("booking.depositpaid"), "Deposit paid status does not match!");

        System.out.println("Booking created successfully with ID: " + response.jsonPath().getInt("bookingid"));
    }
}
