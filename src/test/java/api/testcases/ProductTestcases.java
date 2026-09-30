package api.testcases;

import api.endpoints.ProductEndpoints;
import api.payload.*;
import api.testdata.ProductTestData;
import api.utils.TestData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ProductTestcases {

  @Test(priority = 1, description = "Create Multiple Products")
    public void createProductsTest() {
        int count = 1;

        List<ProductPayload> products = ProductTestData.getProducts();

        Response response =ProductEndpoints.createProducts(TestData.merchantId,products);

        Assert.assertEquals(response.getStatusCode(),201,"Products creation failed");

        List<Map<String, Object>> createdProducts =response.jsonPath().getList("data");

        Assert.assertNotNull(createdProducts,"Created products should not be null");

        for (Map<String, Object> product : createdProducts) {

            String productId = String.valueOf(product.get("productId"));
            String key = "product" + count;

            TestData.productIds.put(key, productId);
            count++;
        }
    }

    @Test(priority = 2, description = "Update product")
        public void updateProductTest() {

            String productId = TestData.productIds.get("product1");

            ProductUpdatePayload payload = new ProductUpdatePayload();
            payload.setBrand("Razer");
            payload.setCategory("Gaming Accessories");
            payload.setCreatedDateTime("2025-06-01T11:45:00.000Z");
            payload.setDescription("High-performance wireless gaming mouse with customizable RGB lighting and 20,000 DPI optical sensor.");
            payload.setMerchantId(Integer.parseInt(TestData.merchantId));
            payload.setName("Razer Viper Ultimate");
            payload.setOffer(15);
            payload.setPrice(1499.99);
            payload.setProductId(Integer.parseInt(productId));
            payload.setProductImageURLs(List.of("https://assets2.razerzone.com/images/pnx.assets/91d6b6a2b9ebc7c8f4f5d38e1c7ac21a/razer-viper-ultimate-gallery-1.jpg"));
            payload.setQuantity(100);
            payload.setRating(4.7);
            payload.setReviews(List.of());
            payload.setSearchTags(List.of("razer", "gaming", "mouse", "accessory", "RGB"));
            payload.setStatus("ACTIVE");
            payload.setThumbnailURL("https://assets2.razerzone.com/images/pnx.assets/91d6b6a2b9ebc7c8f4f5d38e1c7ac21a/razer-viper-ultimate-gallery-1.jpg");
            payload.setTitle("Razer Viper Ultimate Wireless Mouse");
            payload.setType("Electronics");
            payload.setZoneId(TestData.zoneId);

            Response response = ProductEndpoints.updateProduct(productId, payload);

            Assert.assertEquals(response.getStatusCode(),201,"Product update failed");
        }

    @Test(priority = 3, description = "Get product by ID")
    public void getProductByIdTest() {

        String productId = TestData.productIds.get("product1");
        Response response = ProductEndpoints.getProductById(productId);

        Assert.assertEquals(response.getStatusCode(), 200, "Product should be fetched successfully");
        Assert.assertEquals(response.jsonPath().getString("data.productId"), productId, "Product ID should match");
    }

    @Test(priority = 4, description = "Delete product")
    public void deleteProductTest() {

        String productId = TestData.productIds.get("product6");
        Response response = ProductEndpoints.deleteProduct(productId);
        
        Assert.assertEquals(response.getStatusCode(), 200, "Product should be deleted successfully");
    }

     @Test(priority = 5, description = "Get all alpha products")
    public void getAllAlphaProductsTest() {

        Response response = ProductEndpoints.getAllProducts();

        Assert.assertEquals(response.getStatusCode(), 200, "Alpha products should be fetched successfully");
    }
}