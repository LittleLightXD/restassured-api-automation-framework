package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import api.utils.TestData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class WishlistCartEndpoint {


    public static Response addToWishlist(String shopperId, String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("productId", productId)
                .queryParam("shopperId", shopperId)
                .when()
                .post(Routes.POST_SHOPPER_WISHLIST)
                .then()
                .extract()
                .response();
    }


    public static Response getWishlist(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("shopperId", shopperId)
                .when()
                .get(Routes.GET_SHOPPER_WISHLIST)
                .then()
                .extract()
                .response();
    }


    public static Response removeFromWishlist(String shopperId, String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("productId", productId)
                .queryParam("shopperId", shopperId)
                .when()
                .delete(Routes.DELETE_SHOPPER_WISHLIST)
                .then()
                .extract()
                .response();
    }


    public static Response addToCart(String shopperId, String productId, Integer quantity) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("productId", productId)
                .queryParam("quantity", quantity)
                .queryParam("shopperId", shopperId)
                .when()
                .post(Routes.POST_SHOPPER_CART)
                .then()
                .extract()
                .response();
    }


    public static Response getCart(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("shopperId", shopperId)
                .when()
                .get(Routes.GET_SHOPPER_CART)
                .then()
                .extract()
                .response();
    }


    public static Response updateCartItem(String shopperId, String productId, Integer quantity) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("productId", productId)
                .queryParam("quantity", quantity)
                .queryParam("shopperId", shopperId)
                .when()
                .put(Routes.UPDATE_SHOPPER_CART)
                .then()
                .extract()
                .response();
    }


    public static Response removeFromCart(String shopperId, String itemId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("shopperId", shopperId)
                .queryParam("itemId", itemId)
                .when()
                .delete(Routes.DELETE_SHOPPER_CART)
                .then()
                .extract()
                .response();
    }
}

