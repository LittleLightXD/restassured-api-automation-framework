package api.testcases;

import api.endpoints.AdminEndpoints;
import api.payload.AdminPayload;
import api.utils.FakeDataGenerator;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Admintestcases {



    @Test(priority = 1, description = "Create admin with valid data")
    public void createAdminWithValidDataTest() {


        AdminPayload payload = new AdminPayload();
        payload.setCity("Bangalore");
        payload.setCountry("India");
        payload.setDob("1990-01-01");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setGender("MALE");
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setState("Karnataka");
        payload.setStatus("ACTIVE");
        payload.setZoneId("ALPHA");


        Response response = AdminEndpoints.createAdmin(payload);


        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertNotNull(response.jsonPath().getString("adminId"), "Admin ID should not be null");

    }

    @Test(priority = 2, description = "Get admin by valid ID")
    public void getAdminByIdTest() {


        AdminPayload payload = new AdminPayload();
        payload.setCity("Mumbai");
        payload.setCountry("India");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName("Test");
        payload.setLastName("User");
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response createResponse = AdminEndpoints.createAdmin(payload);
        String adminId = createResponse.jsonPath().getString("adminId");


        Response response = AdminEndpoints.getAdminById(adminId);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("adminId"), adminId, "Admin ID should match");

    }

    @Test(priority = 3, description = "Update admin details")
    public void updateAdminTest() {


        AdminPayload payload = new AdminPayload();
        payload.setCity("Delhi");
        payload.setCountry("India");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName("Original");
        payload.setLastName("Name");
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response createResponse = AdminEndpoints.createAdmin(payload);
        String adminId = createResponse.jsonPath().getString("adminId");


        AdminPayload updatePayload = new AdminPayload();
        updatePayload.setFirstName("Updated");
        updatePayload.setLastName("Name");

        Response response = AdminEndpoints.updateAdmin(adminId, updatePayload);


        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

    }


    @Test(priority = 5, dataProvider = "validAdminData",
            description = "Create admin with multiple valid datasets")
    public void createAdminDataDrivenTest(String firstName, String lastName, String email, String phone) {

        AdminPayload payload = new AdminPayload();
        payload.setCity("TestCity");
        payload.setCountry("India");
        payload.setEmail(email);
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(phone);
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response response = AdminEndpoints.createAdmin(payload);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
    }



    @Test(priority = 6, description = "Create admin with invalid email format")
    public void createAdminWithInvalidEmailTest() {

        AdminPayload payload = new AdminPayload();
        payload.setCity("TestCity");
        payload.setCountry("India");
        payload.setEmail("invalid-email");
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setStatus("ACTIVE");

        Response response = AdminEndpoints.createAdmin(payload);


        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400 for invalid email");

    }

    @Test(priority = 7, description = "Create admin with missing required fields")
    public void createAdminWithMissingFieldsTest() {

        AdminPayload payload = new AdminPayload();
        payload.setCountry("India");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());

        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");

        Response response = AdminEndpoints.createAdmin(payload);


        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 422,
                "Expected error status code");

    }

    @Test(priority = 8, description = "Get admin with invalid ID")
    public void getAdminWithInvalidIdTest() {

        Response response = AdminEndpoints.getAdminById("invalid-admin-id-12345");


        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404");

    }

    @Test(priority = 9, description = "Duplicate email validation")
    public void duplicateEmailTest() {

        String duplicateEmail = FakeDataGenerator.getUniqueEmail();


        AdminPayload payload1 = new AdminPayload();
        payload1.setCity("City1");
        payload1.setCountry("India");
        payload1.setEmail(duplicateEmail);
        payload1.setFirstName("Admin1");
        payload1.setLastName("User1");
        payload1.setPassword(FakeDataGenerator.getPassword());
        payload1.setPhone(FakeDataGenerator.getPhoneNumber());
        payload1.setRole("ADMIN");
        payload1.setStatus("ACTIVE");

        Response response1 = AdminEndpoints.createAdmin(payload1);
        Assert.assertEquals(response1.getStatusCode(), 201, "First admin should be created");


        AdminPayload payload2 = new AdminPayload();
        payload2.setCity("City2");
        payload2.setCountry("India");
        payload2.setEmail(duplicateEmail);
        payload2.setFirstName("Admin2");
        payload2.setLastName("User2");
        payload2.setPassword(FakeDataGenerator.getPassword());
        payload2.setPhone(FakeDataGenerator.getPhoneNumber());
        payload2.setRole("ADMIN");
        payload2.setStatus("ACTIVE");

        Response response2 = AdminEndpoints.createAdmin(payload2);


        Assert.assertTrue(response2.getStatusCode() == 409 || response2.getStatusCode() == 400,
                "Duplicate email should be rejected");

    }

    @Test(priority = 10, description = "Unauthorized access without token")
    public void unauthorizedAccessTest() {

        String adminId = "test-admin-id";



        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("adminId", adminId)
                .when()
                .get(api.endpoints.Routes.GET_ADMIN);

        Assert.assertEquals(response.getStatusCode(), 401,
                "An unauthenticated request should return 401");


    }
}
