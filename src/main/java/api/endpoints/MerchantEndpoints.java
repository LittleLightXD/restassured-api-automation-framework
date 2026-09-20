package api.endpoints;

import api.payload.MerchantPayload;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class MerchantEndpoints {


    public static Response createMerchant(MerchantPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static String createMerchantAndGetId(MerchantPayload payload) {
        Response response = createMerchant(payload);

        if (response.getStatusCode() == 201) {
            String merchantId = response.jsonPath().getString("merchantId");
            return merchantId;
        }
        return null;
    }


    public static Response getMerchantById(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("merchantId", merchantId)
                .when()
                .get(Routes.GET_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response getAllMerchants() {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response updateMerchant(String merchantId, MerchantPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("merchantId", merchantId)
                .body(payload)
                .when()
                .put(Routes.UPDATE_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response deleteMerchant(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("merchantId", merchantId)
                .when()
                .delete(Routes.UPDATE_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response updateMerchantStatus(String merchantId, String status) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("merchantId", merchantId)
                .queryParam("status", status)
                .when()
                .patch(Routes.UPDATE_MERCHANT_STATUS)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response getMerchantWithCompanyDetails(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("merchantId", merchantId)
                .when()
                .get(Routes.GET_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response getMerchantWithAddressDetails(String merchantId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("merchantId", merchantId)
                .when()
                .get(Routes.GET_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response searchMerchants(String searchTerm) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("search", searchTerm)
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response getMerchantsByStatus(String status) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("status", status)
                .when()
                .get(Routes.GET_MERCHANT_ZONEID)
                .then()
                .extract()
                .response();

        return response;
    }
}
