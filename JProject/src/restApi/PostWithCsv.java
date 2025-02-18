package restApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostWithCsv {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @Test
    public void createBookingFromCSV() throws IOException {
        // Set the base URI
        RestAssured.baseURI = BASE_URL;

        // Read CSV file
        try (FileReader reader = new FileReader("src/Test/Resources/booking.csv");
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader()))
        
        
        {

            for (CSVRecord record : csvParser) {
                // Prepare JSON payload from CSV record
                Map<String, Object> bookingPayload = new HashMap<>();
                bookingPayload.put("firstname", record.get("firstname"));
                bookingPayload.put("lastname", record.get("lastname"));
                bookingPayload.put("totalprice", Integer.parseInt(record.get("totalprice")));
                bookingPayload.put("depositpaid", Boolean.parseBoolean(record.get("depositpaid")));

                Map<String, String> bookingDates = new HashMap<>();
                bookingDates.put("checkin", record.get("checkin"));
                bookingDates.put("checkout", record.get("checkout"));
                bookingPayload.put("bookingdates", bookingDates);
                bookingPayload.put("additionalneeds", record.get("additionalneeds"));

                // Make POST request
                Response response = RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(bookingPayload)
                        .post("/booking");

                // Print response
                System.out.println("Response Status Code: " + response.statusCode());
                System.out.println("Response Body: " + response.prettyPrint());
            }
        }
    }
}
