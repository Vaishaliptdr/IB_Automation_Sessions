package restApi;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class BearerTokenExample {

    private static final String BASE_URL = "https://gorest.co.in/public/v2";
    private static final String ACCESS_TOKEN = "Bearer 0921069c3f8ef9421d9b2408ca48d4a734ef5ae3d28c8744b87d79632d826e51";

    // Helper method for logging and returning response
    private static Response logAndReturnResponse(Response response) {
        response.then().log().all();
        return response;
    }

    // POST Request: Create a User
    public static void createUser() {
        String payload = "{\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"email\": \"johndoe" + System.currentTimeMillis() + "@example.com\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        Response response = given()
                .header("Authorization", ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(BASE_URL + "/users");

        logAndReturnResponse(response);
    }

    // GET Request: Fetch User Details by ID
    public static void getUserById(int userId) {
        Response response = given()
                .header("Authorization", ACCESS_TOKEN)
                .when()
                .get(BASE_URL + "/users/" + userId);

        logAndReturnResponse(response);
    }

    // PUT Request: Update User Information
    public static void updateUser(int userId) {
        String payload = "{\n" +
                "    \"name\": \"John Updated\",\n" +
                "    \"email\": \"johnupdated" + System.currentTimeMillis() + "@example.com\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";

        Response response = given()
                .header("Authorization", ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put(BASE_URL + "/users/" + userId);

        logAndReturnResponse(response);
    }

    // PATCH Request: Partially Update User Information
    public static void partiallyUpdateUser(int userId) {
        String payload = "{\n" +
                "    \"status\": \"active\"\n" +
                "}";

        Response response = given()
                .header("Authorization", ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .patch(BASE_URL + "/users/" + userId);

        logAndReturnResponse(response);
    }

    // DELETE Request: Delete User by ID
    public static void deleteUser(int userId) {
        Response response = given()
                .header("Authorization", ACCESS_TOKEN)
                .when()
                .delete(BASE_URL + "/users/" + userId);

        logAndReturnResponse(response);
    }

    public static void main(String[] args) {
        // Example workflow
        System.out.println("Creating a new user...");
        createUser();

        System.out.println("Fetching user details...");
        int userId = 7566977; // Replace with actual ID or fetch from createUser response
        getUserById(userId);

        System.out.println("Updating user information...");
        updateUser(userId);

        System.out.println("Partially updating user information...");
        partiallyUpdateUser(userId);

        System.out.println("Deleting user...");
        deleteUser(userId);
    }
}

