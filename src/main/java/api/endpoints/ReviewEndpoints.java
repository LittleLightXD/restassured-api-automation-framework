package api.endpoints;

import api.payload.ReviewPayload;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReviewEndpoints {


    public static Response createReview(ReviewPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_REVIEW)
                .then()
                .extract()
                .response();
    }


    public static String createReviewAndGetId(ReviewPayload payload) {
        Response response = createReview(payload);
        return response.jsonPath().getString("reviewId");
    }


    public static Response getProductReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response getReviewsByRating(String productId, Integer rating) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("rating", rating)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response getVerifiedPurchaseReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("isVerifiedPurchase", true)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response getMediaReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("hasMedia", true)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response updateReview(String reviewId, ReviewPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }


    public static Response deleteReview(String reviewId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .delete(Routes.DELETE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }


    public static Response markReviewHelpful(String reviewId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"helpful\": true}")
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }


    public static Response markReviewUnhelpful(String reviewId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"unhelpful\": true}")
                .when()
                .put(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId))
                .then()
                .extract()
                .response();
    }


    public static Response getMostHelpfulReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("sortBy", "helpful")
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response getMostRecentReviews(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("sortBy", "recent")
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response getRatingDistribution(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("getRatingDistribution", true)
                .when()
                .get(Routes.GET_REVIEW.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response reportReview(String reviewId, String reason) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"reason\": \"" + reason + "\"}")
                .when()
                .post(Routes.UPDATE_REVIEW.replace("{reviewId}", reviewId) + "/report")
                .then()
                .extract()
                .response();
    }
}

