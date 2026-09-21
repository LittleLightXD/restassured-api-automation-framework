package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataproviders.AdminDataProvider;
import api.endpoints.AdminEndpoints;
import api.payload.AdminPayload;
import api.payload.LoginAdminPayload;
import api.utils.FakeDataGenerator;
import api.utils.TestData;
import io.restassured.response.Response;


public class Admintestcases {

    private static final Logger logger = LogManager.getLogger(Admintestcases.class);

    @Test(priority = 1, description = "Create admin with valid data")
    public void createAdminWithValidDataTest() {

        TestData.adminPassword = FakeDataGenerator.getPassword();
        TestData.adminEmail = FakeDataGenerator.getUniqueEmail();

        AdminPayload payload = new AdminPayload();
        payload.setCity("Bangalore");
        payload.setCountry("India");
        payload.setDob("1990-01-01");
        payload.setEmail(TestData.adminEmail);
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setGender("MALE");
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setPassword(TestData.adminPassword);
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setRole("ADMIN");
        payload.setState("Karnataka");
        payload.setStatus("ACTIVE");
        payload.setZoneId("ALPHA");


        Response response = AdminEndpoints.createAdmin(payload);

        TestData.adminId = response.jsonPath().getString("data.userId");

        logger.info("Admin created successfully with ID: {}", TestData.adminId);
        logger.info("Expected status : 201, Actual status: {}", response.getStatusCode());
        
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertNotNull(TestData.adminId, "Admin ID should not be null");

    }

    @Test(priority = 2, description = "Login as admin", dependsOnMethods = "createAdminWithValidDataTest")
    public void adminLoginTest() {

        LoginAdminPayload payload = new LoginAdminPayload();
        payload.setEmail(TestData.adminEmail);
        payload.setPassword(TestData.adminPassword);
        payload.setRole("ADMIN");

        Response response = AdminEndpoints.loginAdmin(payload);
        
        logger.info("Expected status : 200, Actual status: {}", response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(),200,"Admin login should be successful");
        Assert.assertNotNull(TestData.adminToken,"Admin token should not be null");
        Assert.assertNotNull(TestData.adminId,"Admin user ID should not be null");
    }

    @Test(priority = 3, description = "Get admin by valid ID")
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


        Response response = AdminEndpoints.getAdminById(TestData.adminId);
        
        logger.info("Expected status : 200, Actual status: {}", response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("data.userId"),TestData.adminId,"Admin ID should match");

    }

    @Test(priority = 4, description = "Update admin details")
    public void updateAdminTest() {

        AdminPayload updatePayload = new AdminPayload();
        updatePayload.setFirstName("Updated");
        updatePayload.setLastName("Name");
        updatePayload.setCity("Bangalore");
        updatePayload.setCountry("India");
        updatePayload.setState("Karnataka");

        Response response = AdminEndpoints.updateAdmin(TestData.adminId, updatePayload);
        
        logger.info("Expected status : 200, Actual status: {}", response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

    }


    @Test(priority = 5, dataProvider = "validAdminData", dataProviderClass = AdminDataProvider.class,
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
        payload.setDob("1990-01-01");
        payload.setGender("MALE");
        payload.setState("Karnataka");
        payload.setZoneId("ALPHA");

        Response response = AdminEndpoints.createAdmin(payload);
        
        logger.info("Expected status : 201, Actual status: {}", response.getStatusCode());

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
        
        logger.info("Expected status : 400, Actual status: {}", response.getStatusCode());

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
        logger.info("Expected status : 400, Actual status: {}", response.getStatusCode());


        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 422,
                "Expected error status code");

    }

    @Test(priority = 8, description = "Duplicate email validation")
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
        payload1.setState("Karnataka");

        Response response1 = AdminEndpoints.createAdmin(payload1);
        Assert.assertEquals(response1.getStatusCode(), 201, "First admin should be created");
        
        logger.info("Expected status : 201, Actual status: {}", response1.getStatusCode());


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
        payload2.setState("Karnataka");

        Response response2 = AdminEndpoints.createAdmin(payload2);

        logger.info("Expected status : 409, Actual status: {}", response2.getStatusCode());

        Assert.assertTrue(response2.getStatusCode() == 409 || response2.getStatusCode() == 400,
                "Duplicate email should be rejected");

    }

}
