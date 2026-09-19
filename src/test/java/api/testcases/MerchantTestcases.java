package api.testcases;

import api.endpoints.MerchantEndpoints;
import api.payload.MerchantPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MerchantTestcases {



    @Test(priority = 1, description = "Create merchant with valid data - Status 201")
    public void createMerchantWithValidDataTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setBusinessEmail(FakeDataGenerator.getUniqueEmail());
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setBusinessPhone(FakeDataGenerator.getPhoneNumber());
        payload.setBusinessType("RETAIL");
        payload.setGstNumber("18AABCU1234H1Z0");

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        Assert.assertNotNull(response.jsonPath().getString("merchantId"), "Merchant ID should not be null");

    }

    @Test(priority = 2, description = "Create merchant with company and address details")
    public void createMerchantWithEmbeddedDetailsTest() {

        MerchantPayload.CompanyDetails companyDetails = new MerchantPayload.CompanyDetails();
        companyDetails.setCompanyName(FakeDataGenerator.getCompanyName());
        companyDetails.setBusinessType("WHOLESALE");
        companyDetails.setGst("18AABCU1234H1Z0");

        MerchantPayload.AddressDetails addressDetails = new MerchantPayload.AddressDetails();
        addressDetails.setAddress(FakeDataGenerator.getAddress());
        addressDetails.setCity(FakeDataGenerator.getCity());
        addressDetails.setState("Karnataka");
        addressDetails.setCountry(FakeDataGenerator.getCountry());
        addressDetails.setZipCode(FakeDataGenerator.getPostalCode());

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setCompanyDetails(companyDetails);
        payload.setAddressDetails(addressDetails);

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201");
    }

    @Test(priority = 3, description = "Get merchant by valid ID")
    public void getMerchantByIdTest() {


        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");


        Response response = MerchantEndpoints.getMerchantById(merchantId);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("merchantId"), merchantId);

    }

    @Test(priority = 4, description = "Update merchant - API Chaining")
    public void updateMerchantTest() {


        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName("Original Name");
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");


        MerchantPayload updatePayload = new MerchantPayload();
        updatePayload.setBusinessName("Updated Name");

        Response response = MerchantEndpoints.updateMerchant(merchantId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }

    @Test(priority = 5, description = "Update merchant status")
    public void updateMerchantStatusTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");


        Response response = MerchantEndpoints.updateMerchantStatus(merchantId, "ACTIVE");

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }



    @Test(priority = 10, dataProvider = "validMerchantData",
           description = "Create merchant with multiple datasets")
    public void createMerchantDataDrivenTest(String businessName, String email, String phone, String businessType) {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(businessName);
        payload.setEmail(email);
        payload.setPhone(phone);
        payload.setPassword(FakeDataGenerator.getStrongPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setBusinessType(businessType);

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 201);
    }



    @Test(priority = 20, description = "Duplicate email validation - Status 409")
    public void duplicateMerchantEmailTest() {

        String duplicateEmail = FakeDataGenerator.getUniqueEmail();


        MerchantPayload payload1 = new MerchantPayload();
        payload1.setBusinessName(FakeDataGenerator.getCompanyName());
        payload1.setEmail(duplicateEmail);
        payload1.setPhone(FakeDataGenerator.getPhoneNumber());
        payload1.setPassword(FakeDataGenerator.getStrongPassword());
        payload1.setConfirmPassword(payload1.getPassword());

        Response response1 = MerchantEndpoints.createMerchant(payload1);
        Assert.assertEquals(response1.getStatusCode(), 201);


        MerchantPayload payload2 = new MerchantPayload();
        payload2.setBusinessName(FakeDataGenerator.getCompanyName());
        payload2.setEmail(duplicateEmail);
        payload2.setPhone(FakeDataGenerator.getPhoneNumber());
        payload2.setPassword(FakeDataGenerator.getStrongPassword());
        payload2.setConfirmPassword(payload2.getPassword());

        Response response2 = MerchantEndpoints.createMerchant(payload2);

        Assert.assertEquals(response2.getStatusCode(), 409, "Expected status code 409 for duplicate email");
    }

    @Test(priority = 21, description = "Missing required fields - Status 400")
    public void createMerchantWithMissingFieldsTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());

        payload.setPassword(FakeDataGenerator.getPassword());

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 422,
                "Expected error status code");
    }

    @Test(priority = 22, description = "Invalid email format - Status 400")
    public void createMerchantWithInvalidEmailTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail("invalid-email-format");
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
    }

    @Test(priority = 23, description = "Invalid phone format - Status 400")
    public void createMerchantWithInvalidPhoneTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone("123");
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
    }

    @Test(priority = 24, description = "Invalid merchant ID - Status 404")
    public void getMerchantWithInvalidIdTest() {

        Response response = MerchantEndpoints.getMerchantById("invalid-merchant-id-99999");

        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404");
    }

    @Test(priority = 25, description = "Password and confirm password mismatch - Status 400")
    public void passwordMismatchTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword("Password@123");
        payload.setConfirmPassword("DifferentPassword@123");

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
    }

    @Test(priority = 26, description = "Invalid GST number - Status 400")
    public void invalidGstNumberTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());
        payload.setGstNumber("INVALID");

        Response response = MerchantEndpoints.createMerchant(payload);

        Assert.assertEquals(response.getStatusCode(), 400, "Expected status code 400");
    }

    @Test(priority = 27, description = "Delete merchant - Status 200/204")
    public void deleteMerchantTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setBusinessName(FakeDataGenerator.getCompanyName());
        payload.setEmail(FakeDataGenerator.getUniqueEmail());
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setPassword(FakeDataGenerator.getPassword());
        payload.setConfirmPassword(payload.getPassword());

        Response createResponse = MerchantEndpoints.createMerchant(payload);
        String merchantId = createResponse.jsonPath().getString("merchantId");

        Response response = MerchantEndpoints.deleteMerchant(merchantId);

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204,
                "Expected 200 or 204");
    }

    @Test(priority = 28, description = "Access control - unauthorized request without token")
    public void unauthorizedAccessTest() {
    }
}

