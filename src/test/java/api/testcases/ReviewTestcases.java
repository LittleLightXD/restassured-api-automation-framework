package api.testcases;

import api.endpoints.ReviewEndpoints;
import api.payload.ReviewPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReviewTestcases {




    @Test(priority = 1, description = "Create product review with valid data")
    public void createReviewWithValidDataTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setTitle("Excellent Product!");
        payload.setComment("Great quality and fast delivery. Highly recommended!");
        payload.setIsVerifiedPurchase(true);

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("reviewId"));

    }

    @Test(priority = 2, description = "Get all reviews for product")
    public void getProductReviewsTest() {

        Response response = ReviewEndpoints.getProductReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3, description = "Get reviews by rating")
    public void getReviewsByRatingTest() {

        Response response = ReviewEndpoints.getReviewsByRating("product-123456", 5);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 4, description = "Get verified purchase reviews")
    public void getVerifiedPurchaseReviewsTest() {

        Response response = ReviewEndpoints.getVerifiedPurchaseReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 5, description = "Update review")
    public void updateReviewTest() {

        ReviewPayload createPayload = new ReviewPayload();
        createPayload.setProductId("product-123456");
        createPayload.setShopperId("shopper-123456");
        createPayload.setRating(5);
        createPayload.setComment("Initial comment");

        String reviewId = ReviewEndpoints.createReviewAndGetId(createPayload);

        ReviewPayload updatePayload = new ReviewPayload();
        updatePayload.setComment("Updated comment with more details");
        updatePayload.setRating(4);

        Response response = ReviewEndpoints.updateReview(reviewId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 6, description = "Get most helpful reviews")
    public void getMostHelpfulReviewsTest() {

        Response response = ReviewEndpoints.getMostHelpfulReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 7, description = "Get most recent reviews")
    public void getMostRecentReviewsTest() {

        Response response = ReviewEndpoints.getMostRecentReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 8, description = "Get rating distribution")
    public void getRatingDistributionTest() {

        Response response = ReviewEndpoints.getRatingDistribution("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

    }



    @Test(priority = 10, dataProvider = "validReviewData",
            description = "Create review with data provider")
    public void createReviewDataDrivenTest(String productId, String shopperId, Integer rating, String comment) {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId(productId);
        payload.setShopperId(shopperId);
        payload.setRating(rating);
        payload.setComment(comment);

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 201);

    }



    @Test(priority = 20, description = "Create review with invalid rating (too high)")
    public void createReviewWithInvalidRatingTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(10);
        payload.setComment("Invalid rating");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 21, description = "Create review with missing product ID")
    public void createReviewWithMissingProductIdTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Missing product ID");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 22, description = "Create review with empty comment")
    public void createReviewWithEmptyCommentTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(priority = 23, description = "Create review with negative rating")
    public void createReviewWithNegativeRatingTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(-1);
        payload.setComment("Negative rating");

        Response response = ReviewEndpoints.createReview(payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 24, description = "Update non-existent review")
    public void updateNonExistentReviewTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setComment("Updated comment");

        Response response = ReviewEndpoints.updateReview("invalid-review-id", payload);

        Assert.assertEquals(response.getStatusCode(), 404);

    }

    @Test(priority = 25, description = "Delete review")
    public void deleteReviewTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Review to delete");

        String reviewId = ReviewEndpoints.createReviewAndGetId(payload);
        Response response = ReviewEndpoints.deleteReview(reviewId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 26, description = "Mark review as helpful")
    public void markReviewHelpfulTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Helpful review");

        String reviewId = ReviewEndpoints.createReviewAndGetId(payload);
        Response response = ReviewEndpoints.markReviewHelpful(reviewId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 27, description = "Get reviews with media (images/videos)")
    public void getMediaReviewsTest() {

        Response response = ReviewEndpoints.getMediaReviews("product-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 28, description = "Report inappropriate review")
    public void reportReviewTest() {

        ReviewPayload payload = new ReviewPayload();
        payload.setProductId("product-123456");
        payload.setShopperId("shopper-123456");
        payload.setRating(5);
        payload.setComment("Review to report");

        String reviewId = ReviewEndpoints.createReviewAndGetId(payload);
        Response response = ReviewEndpoints.reportReview(reviewId, "OFFENSIVE_LANGUAGE");

        Assert.assertEquals(response.getStatusCode(), 200);

    }
}

