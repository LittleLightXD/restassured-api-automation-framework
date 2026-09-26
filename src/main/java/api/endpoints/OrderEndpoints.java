package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import api.utils.TestData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderEndpoints {


    public static Response createOrder(String shopperId, OrderPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response getOrderById(String shopperId, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.UPDATE_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response updateOrder(String shopperId, String orderId, OrderPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .put(Routes.UPDATE_ORDER.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }


    public static Response generateInvoicePDF(String shopperId, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .accept("application/pdf")
                .when()
                .get(Routes.GET_ORDER_INVOICE.replace("{shopperId}", shopperId)
                        .replace("{orderId}", orderId))
                .then()
                .extract()
                .response();
    }
}

