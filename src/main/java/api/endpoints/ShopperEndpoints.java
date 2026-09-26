package api.endpoints;

import api.payload.*;
import api.payload.ShopperPayload;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import api.utils.TestData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ShopperEndpoints {


    public static Response createShopper(ShopperPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER)
                .then()
                .extract()
                .response();

                if (response.getStatusCode() == 201) {
                String userId = response.jsonPath().getString("data.userId");
                TestData.shopperId = userId;
        }
        return response; 
    }


    public static Response getShopperById(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("shopperId", shopperId)
                .when()
                .get(Routes.GET_SHOPPER)
                .then()
                .extract()
                .response();
    }

    public static Response forgotPassword(String email) {

        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .header("role", "SHOPPER")
                .header("email", email)
                .when()
                .post(Routes.FORGOT_PASSWORD)
                .then()
                .extract()
                .response();
        }

    public static Response loginShopper(LoginShopperPayload loginPayload) {

        Response response = given()
            .spec(ReusableRequestSpec.buildRequestSpec())
            .body(loginPayload)
            .when()
            .post(Routes.LOGIN)
            .then()
            .extract()
            .response();

        if (response.getStatusCode() == 200) {
                String token =response.jsonPath().getString("data.jwtToken");
                TestData.shopperToken = token;
                }
        return response;
        }

    public static Response verifyAccount(String token, String password) {

        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("token", token)
                .header("password", password)
                .header("Authorization", "Bearer " + TestData.shopperToken)
                .when()
                .post(Routes.VERIFY_ACCOUNT)
                .then()
                .extract()
                .response();
        }


    public static Response addShopperAddress(String shopperId, ShopperPayload.AddressDetails addressDetails) {
        Response response = given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .pathParam("shopperId", shopperId)
                .body(addressDetails)
                .when()
                .post(Routes.POST_SHOPPER_ADDRESS)
                .then()
                .extract()
                .response();

                if (response.getStatusCode() == 200) {
                String addressId =response.jsonPath().getString("data.addressId");
                TestData.addressId = addressId;
                }
        return response;
        }



    public static Response getShopperAddress(String shopperId, String addressId) {
        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .pathParam("shopperId", shopperId)
                .pathParam("addressId", addressId)
                .when()
                .get(Routes.GET_SHOPPER_ADDRESS)
                .then()
                .extract()
                .response();
    }


    public static Response updateShopperAddress(
        String shopperId,
        String addressId,
        ShopperPayload.AddressDetails addressDetails) {

        return given()
            .spec(ReusableRequestSpec.buildShopperRequestSpec())
            .pathParam("shopperId", shopperId)
            .pathParam("addressId", addressId)
            .body(addressDetails)
            .when()
            .put(Routes.GET_SHOPPER_ADDRESS)
            .then()
            .extract()
            .response();
        }

    public static Response deleteShopperAddress(String shopperId, String addressId) {
        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .pathParam("shopperId", shopperId)
                .pathParam("addressId", addressId)
                .when()
                .delete(Routes.GET_SHOPPER_ADDRESS)
                .then()
                .extract()
                .response();
    }

    public static Response getAllAddresses(String shopperId) {

        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .pathParam("shopperId", shopperId)
                .when()
                .get(Routes.GET_ALL_ADDRESSES)
                .then()
                .extract()
                .response();
        }
}