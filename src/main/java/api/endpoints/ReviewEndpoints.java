package api.endpoints;

import api.payload.ReviewPayload;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReviewEndpoints {

    public static Response createReview(String productId, ReviewPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .queryParam("productId", productId)
                .body(payload)
                .when()
                .post(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }

    public static Response getProductReviews(String productId) {

        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .queryParam("productId", productId)
                .when()
                .get(Routes.GET_REVIEW);
    }

    public static Response updateReview(String shopperId, String productId, ReviewPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildShopperRequestSpec())
                .pathParam("shopperId", shopperId)
                .queryParam("productId", productId)
                .body(payload)
                .when()
                .put(Routes.UPDATE_REVIEW)
                .then()
                .extract()
                .response();
    }
}