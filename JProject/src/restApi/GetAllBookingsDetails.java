package restApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllBookingsDetails {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @Test
    public void getAllBookingDetails() {
        // Set the base URI
        RestAssured.baseURI = BASE_URL;

        // Send GET request to /booking to fetch all bookings (only booking ids will be returned)
        Response response = RestAssured
                .given()
                .get("/booking");

        // Log the response
        System.out.println("Response: " + response.asString());

        // Assertions: Check if the status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch all bookings!");

        // Extract the list of booking IDs
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertNotNull(bookingIds, "Booking IDs are null!");

        // Log the number of bookings fetched
        System.out.println("Total Bookings: " + bookingIds.size());

        // Loop through each booking ID and fetch its details
        for (Integer bookingId : bookingIds) {
            // Fetch individual booking details by ID
            Response bookingDetailsResponse = RestAssured
                    .given()
                    .get("/booking/" + bookingId);

            // Assertions: Check if each booking details request returns a successful status code
            Assert.assertEquals(bookingDetailsResponse.getStatusCode(), 200, "Failed to fetch booking details for ID: " + bookingId);

            // Extract and print details of the booking
            String firstName = bookingDetailsResponse.jsonPath().getString("firstname");
            String lastName = bookingDetailsResponse.jsonPath().getString("lastname");
            int totalPrice = bookingDetailsResponse.jsonPath().getInt("totalprice");
            boolean depositPaid = bookingDetailsResponse.jsonPath().getBoolean("depositpaid");
            String checkinDate = bookingDetailsResponse.jsonPath().getString("bookingdates.checkin");
            String checkoutDate = bookingDetailsResponse.jsonPath().getString("bookingdates.checkout");
            String additionalNeeds = bookingDetailsResponse.jsonPath().getString("additionalneeds");

            // Print the booking details to the console
            System.out.println("Booking ID: " + bookingId);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Total Price: " + totalPrice);
            System.out.println("Deposit Paid: " + depositPaid);
            System.out.println("Checkin Date: " + checkinDate);
            System.out.println("Checkout Date: " + checkoutDate);
            System.out.println("Additional Needs: " + additionalNeeds);
            System.out.println("-----------------------------------------------------");
        }
    }
}
