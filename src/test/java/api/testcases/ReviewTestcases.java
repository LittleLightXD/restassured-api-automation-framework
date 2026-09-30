package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.dataproviders.ReviewDataProvider;
import api.endpoints.ReviewEndpoints;
import api.payload.ReviewPayload;
import api.utils.TestData;
import io.restassured.response.Response;

public class ReviewTestcases {

    @Test(priority = 1,description = "Create product review",dataProvider = "reviewData",dataProviderClass = ReviewDataProvider.class)
    public void createProductReviewTest(
            String productId,
            String dateTime,
            String description,
            String heading,
            String rating,
            String shopperName) {

        ReviewPayload payload = new ReviewPayload();

        payload.setDateTime(dateTime);
        payload.setDescription(description);
        payload.setHeading(heading);
        payload.setRating(Integer.parseInt(rating));
        payload.setShopperId(Integer.parseInt(TestData.shopperId));
        payload.setShopperName(shopperName);

        Response response = ReviewEndpoints.createReview(productId, payload);

        Assert.assertEquals(response.getStatusCode(),201,"Review should be created successfully");

        TestData.reviewId = response.jsonPath().getString("data.reviewId");

        Assert.assertNotNull(TestData.reviewId,"Review ID should not be null");
    }

    @Test(priority = 2, description = "Update product review")
    public void updateReviewTest() {

        String productId = TestData.productIds.get("product10");
        String shopperId = TestData.shopperId;

        ReviewPayload payload = new ReviewPayload();
        payload.setShopperId(Integer.parseInt(TestData.shopperId));
        payload.setShopperName("Yetti");
        payload.setHeading("Waste product and waste merchant");
        payload.setDescription("Bro i dont want fight, I asked for an item and you sent me waste");
        payload.setRating(1);

        Response response = ReviewEndpoints.updateReview(shopperId, productId, payload);

        Assert.assertEquals(response.getStatusCode(), 200, "Review should be updated successfully");
    }

}