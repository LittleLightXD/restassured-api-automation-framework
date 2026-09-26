package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import api.utils.TestData;
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

            if (response.getStatusCode() == 201) {
            String userId = response.jsonPath().getString("data.userId");
            TestData.merchantId = userId;
        }
        return response;
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


        public static Response loginMerchant(LoginMerchantPayload loginPayload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(loginPayload)
                .when()
                .post(Routes.LOGIN)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() == 200) {
            String token = response.jsonPath().getString("data.jwtToken");
            TestData.merchantToken = token;
        }

        return response;
    }
}
