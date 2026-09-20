package api.endpoints;

import api.payload.ShopperPayload;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ShopperEndpoints {


    public static Response createShopper(ShopperPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER)
                .then()
                .extract()
                .response();
    }


    public static String createShopperAndGetId(ShopperPayload payload) {
        Response response = createShopper(payload);
        return response.jsonPath().getString("shopperId");
    }


    public static Response getShopperById(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.GET_SHOPPER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response updateShopper(String shopperId, ShopperPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .put(Routes.UPDATE_SHOPPER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response deleteShopper(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .delete(Routes.UPDATE_SHOPPER.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response addShopperAddress(String shopperId, ShopperPayload.AddressDetails addressDetails) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(addressDetails)
                .when()
                .post(Routes.POST_SHOPPER_ADDRESS.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response getShopperAddress(String shopperId, String addressId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.GET_SHOPPER_ADDRESS.replace("{shopperId}", shopperId)
                        .replace("{addressId}", addressId))
                .then()
                .extract()
                .response();
    }


    public static Response updateShopperAddress(String shopperId, String addressId, ShopperPayload.AddressDetails addressDetails) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(addressDetails)
                .when()
                .put(Routes.GET_SHOPPER_ADDRESS.replace("{shopperId}", shopperId)
                        .replace("{addressId}", addressId))
                .then()
                .extract()
                .response();
    }


    public static Response deleteShopperAddress(String shopperId, String addressId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .delete(Routes.GET_SHOPPER_ADDRESS.replace("{shopperId}", shopperId)
                        .replace("{addressId}", addressId))
                .then()
                .extract()
                .response();
    }


    public static Response addBankAccount(ShopperPayload.BankAccountDetails bankDetails) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(bankDetails)
                .when()
                .post(Routes.POST_SHOPPER_BANK_ACCOUNT)
                .then()
                .extract()
                .response();
    }


    public static Response addToWishlist(String shopperId, String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("productId", productId)
                .when()
                .post(Routes.POST_SHOPPER_WISHLIST.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response getWishlist(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.GET_SHOPPER_WISHLIST.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response removeFromWishlist(String shopperId, String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .delete(Routes.DELETE_SHOPPER_WISHLIST.replace("{shopperId}", shopperId)
                        .replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response addToCart(String shopperId, String productId, Integer quantity) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("productId", productId)
                .queryParam("quantity", quantity)
                .when()
                .post(Routes.POST_SHOPPER_CART.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response getCart(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.GET_SHOPPER_CART.replace("{shopperId}", shopperId))
                .then()
                .extract()
                .response();
    }


    public static Response updateCartItem(String shopperId, String productId, Integer quantity) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("quantity", quantity)
                .when()
                .put(Routes.UPDATE_SHOPPER_CART.replace("{shopperId}", shopperId)
                        .replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response removeFromCart(String shopperId, String itemId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .delete(Routes.DELETE_SHOPPER_CART.replace("{shopperId}", shopperId)
                        .replace("{itemId}", itemId))
                .then()
                .extract()
                .response();
    }
}

