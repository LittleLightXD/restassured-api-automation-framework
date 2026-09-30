package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CartOrderEndpoints {

        public static Response addToCart(String shopperId, CartPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .pathParam("shopperId", shopperId)
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER_CART)
                .then()
                .extract()
                .response();
        }

    public static Response createOrder(String shopperId, OrderPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_ORDER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
        }
        
    public static Response updateOrderStatus(String shopperId,String orderId,String status) {
        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .pathParam("shopperId", shopperId)
                .pathParam("orderId", orderId)
                .queryParam("status", status)
                .when()
                .patch(Routes.UPDATE_ORDER);
        }
}

