package restApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestfulBookerApiTest {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private String token;
    private int bookingId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;

        // Create an auth token for further requests
        Map<String, String> authPayload = new HashMap<>();
        authPayload.put("username", "admin");
        authPayload.put("password", "password123");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .post("/auth");

        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token generation failed!");
    }

    @Test(priority = 1)
    public void testCreateBooking() {
        // Payload for booking
        Map<String, Object> bookingPayload = new HashMap<>();
        bookingPayload.put("firstname", "John");
        bookingPayload.put("lastname", "Doe");
        bookingPayload.put("totalprice", 123);
        bookingPayload.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2024-12-01");
        bookingDates.put("checkout", "2024-12-05");
        bookingPayload.put("bookingdates", bookingDates);
        bookingPayload.put("additionalneeds", "Breakfast");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(bookingPayload)
                .post("/booking");

        Assert.assertEquals(response.statusCode(), 200);
        bookingId = response.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId > 0, "Booking ID not generated!");
    }

    @Test(priority = 2)
    public void testGetBooking() {
        Response response = RestAssured
                .given()
                .get("/booking/" + bookingId);
        System.out.println("Newly created booking: "+bookingId);
        
        //To print booking details 
        Map<String, Object> bookingDetails = response.jsonPath().getMap("$");
        System.out.println("Details for Booking ID " + bookingId + ": " + bookingDetails);
        
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "John");
    }

    @Test(priority = 3)
    public void testUpdateBooking() {
        // Payload for updating the booking
        Map<String, Object> updatedPayload = new HashMap<>();
        updatedPayload.put("firstname", "Jane");
        updatedPayload.put("lastname", "Doe");
        updatedPayload.put("totalprice", 200);
        updatedPayload.put("depositpaid", false);

        Map<String, String> updatedDates = new HashMap<>();
        updatedDates.put("checkin", "2024-12-10");
        updatedDates.put("checkout", "2024-12-15");
        updatedPayload.put("bookingdates", updatedDates);
        updatedPayload.put("additionalneeds", "Lunch");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedPayload)
                .put("/booking/" + bookingId);
        
      //To print Updated booking details 
        Map<String, Object> bookingDetails = response.jsonPath().getMap("$");
        System.out.println("Details for Booking ID " + bookingId + ": " + bookingDetails);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Jane");
    }

    @Test(priority = 4)
    public void testPartialUpdateBooking() {
        // Payload for partial update
        Map<String, Object> partialPayload = new HashMap<>();
        partialPayload.put("firstname", "Johnny");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(partialPayload)
                .patch("/booking/" + bookingId);
        
      //To print Updated booking details 
        Map<String, Object> bookingDetails = response.jsonPath().getMap("$");
        System.out.println("Details for Booking ID " + bookingId + ": " + bookingDetails);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstname"), "Johnny");
    }

    @Test(priority = 5)
    public void testDeleteBooking() {
        Response response = RestAssured
                .given()
                .header("Cookie", "token=" + token)
                .delete("/booking/" + bookingId);

        Assert.assertEquals(response.statusCode(), 201);

        // Confirm the booking is deleted
        Response getResponse = RestAssured
                .given()
                .get("/booking/" + bookingId);
        Assert.assertEquals(getResponse.statusCode(), 404);
    }
}