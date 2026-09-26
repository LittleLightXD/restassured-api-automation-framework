package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.endpoints.MerchantApprovalEndpoints;
import api.endpoints.MerchantEndpoints;
import api.payload.LoginMerchantPayload;
import api.payload.MerchantPayload;
import api.specs.ReusableRequestSpec;
import api.utils.FakeDataGenerator;
import api.utils.TestData;
import io.restassured.response.Response;

public class MerchantTestcases {
    
    private static final Logger logger = LogManager.getLogger(Admintestcases.class);

    @Test(priority = 1, description = "Create merchant with valid data")
    public void createMerchantTest() {

        TestData.merchantPassword = FakeDataGenerator.getPassword();
        TestData.merchantEmail = FakeDataGenerator.getUniqueEmail();

        MerchantPayload payload = new MerchantPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setGender("male");
        payload.setEmail(TestData.merchantEmail);
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setCommission("30");
        payload.setProductLimit(10);
        payload.setPassword(TestData.merchantPassword);
        payload.setZoneId("BANGALORE" + TestData.adminId);
        payload.setCity("Bardhaman");
        payload.setState("West Bengal");
        payload.setCountry("India");

        MerchantPayload.CompanyDetails company = new MerchantPayload.CompanyDetails();
        company.setName("uytr");
        company.setPhone(FakeDataGenerator.getPhoneNumber());
        company.setEmail(FakeDataGenerator.getUniqueEmail());
        company.setWebAddress("ytrew.com");
        company.setGstn("gfdsdfdsd4433");
        company.setRegisterNumber(FakeDataGenerator.getUUID());
        
        MerchantPayload.AddressDetails address = new MerchantPayload.AddressDetails();
        address.setBuildingInfo("qwerty");
        address.setLandmark("uytrew");
        address.setCountry("India");
        address.setState("West Bengal");
        address.setCity("Bardhaman");
        address.setType("Books");
        address.setPincode("713101");
        address.setStreetInfo("Katwa Bardhaman Road");

        company.setAddress(address);
        payload.setCompany(company);


        Response response = MerchantEndpoints.createMerchant(payload);

        TestData.merchantId = response.jsonPath().getString("data.userId");
        logger.info("Expected status : 201, Actual status: {}", response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 201, "Merchant should be created successfully");
        Assert.assertNotNull(TestData.merchantId, "Merchant ID should not be null");
    }

    
    @Test(priority = 2, description = "Login merchant", dependsOnMethods = "createMerchantTest")
    public void loginMerchantTest() {

        LoginMerchantPayload loginPayload =
                new LoginMerchantPayload();

        loginPayload.setEmail(TestData.merchantEmail);
        loginPayload.setPassword(TestData.merchantPassword);
        loginPayload.setRole("MERCHANT");

        Response response = MerchantEndpoints.loginMerchant(loginPayload);
        TestData.merchantToken = response.jsonPath().getString("data.userId");

        Assert.assertEquals(response.getStatusCode(),200, "Merchant login should be successful");

    }

    @Test(priority = 3, description = "Get first merchant")
    public void getMerchantByIdTest() {

        Response response = MerchantEndpoints.getMerchantById(TestData.merchantId);

        Assert.assertEquals(response.getStatusCode(),200, "Merchant should be fetched successfully");

        Assert.assertEquals(response.jsonPath().getString("data.userId"),TestData.merchantId,"Merchant ID should match");
    }

    @Test(priority = 4, description = "Create merchant to reject")
    public void createMerchantToRejectTest() {

        MerchantPayload payload = new MerchantPayload();
        payload.setFirstName(FakeDataGenerator.getFirstName());
        payload.setLastName(FakeDataGenerator.getLastName());
        payload.setGender("male");
        payload.setEmail(TestData.merchantEmail);
        payload.setPhone(FakeDataGenerator.getPhoneNumber());
        payload.setCommission("30");
        payload.setProductLimit(10);
        payload.setPassword(TestData.merchantPassword);
        payload.setZoneId("BANGALORE" + TestData.adminId);
        payload.setCity("Bardhaman");
        payload.setState("West Bengal");
        payload.setCountry("India");

        MerchantPayload.CompanyDetails company = new MerchantPayload.CompanyDetails();
        company.setName("uytr");
        company.setPhone(FakeDataGenerator.getPhoneNumber());
        company.setEmail(FakeDataGenerator.getUniqueEmail());
        company.setWebAddress("ytrew.com");
        company.setGstn("gfdsdfdsd4433");
        company.setRegisterNumber(FakeDataGenerator.getUUID());
        
        MerchantPayload.AddressDetails address = new MerchantPayload.AddressDetails();
        address.setBuildingInfo("qwerty");
        address.setLandmark("uytrew");
        address.setCountry("India");
        address.setState("West Bengal");
        address.setCity("Bardhaman");
        address.setType("Books");
        address.setPincode("713101");
        address.setStreetInfo("Katwa Bardhaman Road");

        company.setAddress(address);
        payload.setCompany(company);


        Response response = MerchantEndpoints.createMerchant(payload);

        TestData.merchantReject = response.jsonPath().getString("data.userId");
    }

    @Test(priority = 5, description = "Reject merchant")
    public void blockMerchantTest() {

        Response response =MerchantApprovalEndpoints.updateMerchantStatus(TestData.merchantId,"BLOCKED");

        Assert.assertEquals(response.getStatusCode(),200,"Merchant should be blocked successfully");
    }

    @Test(priority = 6, description = "Approve merchant")
    public void approveMerchantTest() {

        Response response = MerchantApprovalEndpoints.updateMerchantStatus(TestData.merchantId, "APPROVED");

        Assert.assertEquals(response.getStatusCode(), 200, "Merchant should be approved successfully");
    }

    @Test(priority = 7, description = "Get merchants by zone")
    public void getMerchantsByZoneIdTest() {

        Response response = MerchantApprovalEndpoints.getAllMerchants(TestData.zoneId);

        Assert.assertEquals(response.getStatusCode(), 200, "Merchants should be fetched successfully");
    }

    @Test(priority = 8, description = "Get approved merchants by zone")
    public void getApprovedMerchantsByZoneTest() {

        Response response = MerchantApprovalEndpoints.getMerchantsByStatus("APPROVED", TestData.zoneId);

        Assert.assertEquals(response.getStatusCode(), 200, "Approved merchants should be fetched successfully");
    }

    @Test(priority = 9, description = "Get active merchants by zone")
    public void getActiveMerchantsTest() {
        
        Response response = MerchantApprovalEndpoints.getMerchantsByStatus("ACTIVE", TestData.zoneId);
        
        Assert.assertEquals(response.getStatusCode(), 200, "Active merchants should be fetched successfully");
    }

    @Test(priority = 10, description = "Get blocked merchants by zone")
    public void getBlockedMerchantsTest() {

        Response response = MerchantApprovalEndpoints.getMerchantsByStatus("BLOCKED", TestData.zoneId);

        Assert.assertEquals(response.getStatusCode(), 200, "Blocked merchants should be fetched successfully");
    }
}