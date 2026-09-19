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

    @Test(priority = 2, description = "Get shopper by ID")
    public void getShopperByIdTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.getShopperById(shopperId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("shopperId"), shopperId);

    }

    @Test(priority = 3, description = "Update shopper profile")
    public void updateShopperTest() {

        ShopperPayload createPayload = new ShopperPayload();
        createPayload.setFirstName(FakeDataGenerator.getFirstName());
        createPayload.setLastName(FakeDataGenerator.getLastName());
        createPayload.setEmail(FakeDataGenerator.getUniqueEmail());
        createPayload.setPassword(FakeDataGenerator.getStrongPassword());
        createPayload.setConfirmPassword(createPayload.getPassword());
        createPayload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(createPayload);

        ShopperPayload updatePayload = new ShopperPayload();
        updatePayload.setFirstName("UpdatedFirstName");
        updatePayload.setLastName("UpdatedLastName");
        updatePayload.setPhone(FakeDataGenerator.getPhoneNumber());

        Response response = ShopperEndpoints.updateShopper(shopperId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 4, description = "Add address to shopper account")
    public void addShopperAddressTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);

        ShopperPayload.AddressDetails addressDetails = new ShopperPayload.AddressDetails();
        addressDetails.setAddress(FakeDataGenerator.getAddress());
        addressDetails.setCity(FakeDataGenerator.getCity());
        addressDetails.setState("TestState");
        addressDetails.setCountry(FakeDataGenerator.getCountry());
        addressDetails.setZipCode(FakeDataGenerator.getPostalCode());
        addressDetails.setAddressType("HOME");

        Response response = ShopperEndpoints.addShopperAddress(shopperId, addressDetails);

        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test(priority = 5, description = "Add items to wishlist")
    public void addToWishlistTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.addToWishlist(shopperId, "product-123456");

        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test(priority = 6, description = "Add items to cart")
    public void addToCartTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.addToCart(shopperId, "product-123456", 2);

        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test(priority = 7, description = "Get shopper cart")
    public void getCartTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.getCart(shopperId);

        Assert.assertEquals(response.getStatusCode(), 200);

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

    @Test(priority = 26, description = "Delete shopper account")
    public void deleteShopperTest() {

        ShopperPayload payload = new ShopperPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());

        String shopperId = ShopperEndpoints.createShopperAndGetId(payload);
        Response response = ShopperEndpoints.deleteShopper(shopperId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 27, description = "Unauthorized access to shopper endpoint")
    public void unauthorizedAccessTest() {

        Response response = ShopperEndpoints.getShopperById("some-id");

        Assert.assertEquals(response.getStatusCode(), 401);
    }
}

