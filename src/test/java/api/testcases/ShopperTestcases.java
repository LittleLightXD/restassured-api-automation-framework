package api.testcases;

import api.endpoints.ShopperEndpoints;
import api.payload.ShopperPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopperTestcases {




    @Test(priority = 1, description = "Create shopper with valid data")
    public void createShopperWithValidDataTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setGender("MALE");
        payload.setDateOfBirth("1990-01-15");

        Response response = ShopperEndpoints.createShopper(payload);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("shopperId"));

    }



    @Test(priority = 10, dataProvider = "validShopperData",
            description = "Create shopper with data provider")
    public void createShopperDataDrivenTest(String firstName, String lastName, String email, String phone) {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setEmail(email);
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(phone);

        Response response = ShopperEndpoints.createShopper(payload);

        Assert.assertEquals(response.getStatusCode(), 201);

    }



    @Test(priority = 20, description = "Create shopper with invalid email")
    public void createShopperWithInvalidEmailTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail("invalid-email-format");
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        Response response = ShopperEndpoints.createShopper(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 21, description = "Create shopper with missing required fields")
    public void createShopperWithMissingFieldsTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());


        Response response = ShopperEndpoints.createShopper(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 22, description = "Get shopper with invalid ID")
    public void getShopperWithInvalidIdTest() {

        Response response = ShopperEndpoints.getShopperById("invalid-shopper-id");

        Assert.assertEquals(response.getStatusCode(), 404);

    }

    @Test(priority = 23, description = "Create shopper with password mismatch")
    public void passwordMismatchTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword("DifferentPassword123!");
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        Response response = ShopperEndpoints.createShopper(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 24, description = "Create shopper with invalid phone")
    public void createShopperWithInvalidPhoneTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone("123");

        Response response = ShopperEndpoints.createShopper(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 25, description = "Duplicate email registration")
    public void duplicateShopperEmailTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        String email = FakeDataGenerator.getUniqueEmail();
        payload.setEmail(email);
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());


        ShopperEndpoints.createShopper(payload);


        Response response = ShopperEndpoints.createShopper(payload);

        Assert.assertEquals(response.getStatusCode(), 409);

    }


    @Test(priority = 27, description = "Unauthorized access to shopper endpoint")
    public void unauthorizedAccessTest() {

        Response response = ShopperEndpoints.getShopperById("some-id");

        Assert.assertEquals(response.getStatusCode(), 401);
    }
}

