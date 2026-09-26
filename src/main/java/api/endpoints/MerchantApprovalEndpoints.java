package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import api.utils.TestData;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class MerchantApprovalEndpoints {


    public static Response updateMerchantStatus(String merchantId, String status) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAdminRequestSpec())
                .queryParam("status", status)
                .when()
                .patch(Routes.UPDATE_MERCHANT_STATUS)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response getAllMerchants(String ZoneId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("zoneId", ZoneId)
                .when()
                .get(Routes.CREATE_MERCHANT)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response getMerchantsByStatus(String status, String zoneId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAdminRequestSpec())
                .queryParam("status", status)
                .queryParam("zoneId", zoneId)
                .when()
                .get(Routes.GET_MERCHANT_ZONEID)
                .then()
                .extract()
                .response();

        return response;
    }
}
