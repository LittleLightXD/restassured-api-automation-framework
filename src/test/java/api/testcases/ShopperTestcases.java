package api.testcases;

import api.dataproviders.AddressDataProvider;
import api.endpoints.ShopperEndpoints;
import api.payload.*;
import api.utils.FakeDataGenerator;
import api.utils.TestData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopperTestcases {


   @Test(priority = 1, description = "Create shopper")
    public void createShopperTest() {
        
    TestData.shopperPassword = FakeDataGenerator.getPassword();
    TestData.shopperEmail = FakeDataGenerator.getUniqueEmail();

    ShopperPayload payload = new ShopperPayload();

    payload.setCity("Mumbai");
    payload.setCountry("India");
    payload.setEmail(TestData.shopperEmail);
    payload.setFirstName(FakeDataGenerator.getFirstName());
    payload.setGender("MALE");
    payload.setLastName(FakeDataGenerator.getLastName());
    payload.setPassword(TestData.shopperPassword);
    payload.setPhone(FakeDataGenerator.getPhoneNumber());
    payload.setState("Maharashtra");
    payload.setZoneId(TestData.zoneId);

    Response response = ShopperEndpoints.createShopper(payload);
    TestData.shopperId = response.jsonPath().getString("data.userId");

    Assert.assertEquals(response.getStatusCode(), 201, "Shopper should be created successfully");
    Assert.assertNotNull(TestData.shopperId, "Shopper ID should not be null");
    }

   @Test(priority = 2, description = "Shopper login")
    public void loginShopperTest() {

    LoginShopperPayload loginPayload = new LoginShopperPayload();

    loginPayload.setEmail(TestData.shopperEmail);
    loginPayload.setPassword(TestData.shopperPassword);
    loginPayload.setRole("SHOPPER");

    Response response = ShopperEndpoints.loginShopper(loginPayload);
    TestData.shopperToken = response.jsonPath().getString("data.jwtToken");

    Assert.assertEquals(response.getStatusCode(), 200, "Shopper should login successfully");
    Assert.assertNotNull(TestData.shopperToken, "Shopper token should not be null");
    Assert.assertNotNull(TestData.shopperId, "Shopper ID should not be null");
    }

   @Test(priority = 3, description = "Get shopper by ID")
    public void getShopperByIdTest() {

        Response response = ShopperEndpoints.getShopperById(TestData.shopperId);

        Assert.assertEquals(response.getStatusCode(), 200, "Shopper should be fetched successfully");
        Assert.assertEquals(response.jsonPath().getString("data.userId"), TestData.shopperId, "Shopper ID should match");
    }

    @Test(priority = 4, dataProvider = "addressData", dataProviderClass = AddressDataProvider.class, description = "Add multiple shopper addresses")
    public void addShopperAddressTest(String city, String type, String country, String buildingInfo, String streetInfo, String landmark, String state, String pincode, String name, String phone) {

        ShopperPayload.AddressDetails addressDetails = new ShopperPayload.AddressDetails();

        addressDetails.setCity(city);
        addressDetails.setType(type);
        addressDetails.setCountry(country);
        addressDetails.setBuildingInfo(buildingInfo);
        addressDetails.setStreetInfo(streetInfo);
        addressDetails.setLandmark(landmark);
        addressDetails.setState(state);
        addressDetails.setPincode(pincode);
        addressDetails.setName(name);
        addressDetails.setPhone(phone);

        Response response = ShopperEndpoints.addShopperAddress(TestData.shopperId, addressDetails,city);

        if (response.getStatusCode() == 201) {
        String addressId = response.jsonPath().getString("data.addressId");
        TestData.addressIds.put(pincode, addressId);
        }

        Assert.assertEquals(response.getStatusCode(), 201, city + " address should be added successfully");
    }

    @Test(priority = 5, description = "Get shopper address") 
    public void getShopperAddressTest() { 
        
        String addressId = TestData.addressIds.get("432104"); 
        
        Response response = ShopperEndpoints.getShopperAddress(TestData.shopperId, addressId); 

        Assert.assertEquals(response.getStatusCode(), 200, "Shopper address should be fetched successfully"); 
    }

    @Test(priority = 6, description = "Update shopper address")
    public void updateShopperAddressTest() {

    String addressId = TestData.addressIds.get("432102"); 

    ShopperPayload.AddressDetails addressDetails = new ShopperPayload.AddressDetails();

    addressDetails.setCity("Diamond Harbour");
    addressDetails.setType("Home");
    addressDetails.setCountry("India");
    addressDetails.setBuildingInfo("iuytrew");
    addressDetails.setStreetInfo("Pune");
    addressDetails.setLandmark("lkjhgfdsa");
    addressDetails.setState("Gujrat");
    addressDetails.setPincode("432102");
    addressDetails.setName("8GYxvJBz5Z");
    addressDetails.setPhone("9737280329");

    Response response = ShopperEndpoints.updateShopperAddress(TestData.shopperId, addressId, addressDetails);

    Assert.assertEquals(response.getStatusCode(), 200, "Shopper address should be updated successfully");
    }

    @Test(priority = 7, description = "Delete shopper address")
    public void deleteShopperAddressTest() {

        Response response = ShopperEndpoints.deleteShopperAddress(TestData.shopperId, TestData.addressIds.get("432103"));

        Assert.assertEquals(response.getStatusCode(), 204, "Shopper address should be deleted successfully");
    }

    @Test(priority = 8, description = "Get all shopper addresses")
    public void getAllShopperAddressesTest() {

        Response response = ShopperEndpoints.getAllAddresses(TestData.shopperId);

        Assert.assertEquals(response.getStatusCode(), 200, "Shopper addresses should be fetched successfully");
    }

}