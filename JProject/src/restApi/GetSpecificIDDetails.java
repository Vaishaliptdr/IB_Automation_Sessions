package restApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSpecificIDDetails {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @Test
    public void getBookingDetailsById() {
    	int id=17059;
        // Set the base URI
        RestAssured.baseURI = BASE_URL;

        // Send GET request to fetch details of booking with ID 1000
        Response response = RestAssured
        		
                .given()
                .get("/booking/"+id);

        // Log the response
        System.out.println("Response: " + response.asString());

        // Assertions: Check if the status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch booking details for ID 1000!");

        // Extract booking details from the response
        String firstName = response.jsonPath().getString("firstname");
        String lastName = response.jsonPath().getString("lastname");
        int totalPrice = response.jsonPath().getInt("totalprice");
        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        String checkinDate = response.jsonPath().getString("bookingdates.checkin");
        String checkoutDate = response.jsonPath().getString("bookingdates.checkout");
        String additionalNeeds = response.jsonPath().getString("additionalneeds");

        // Print the booking details to the console
        System.out.println("Booking ID: 1000");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Deposit Paid: " + depositPaid);
        System.out.println("Checkin Date: " + checkinDate);
        System.out.println("Checkout Date: " + checkoutDate);
        System.out.println("Additional Needs: " + additionalNeeds);
    }
}

