package api.testcases;

import api.dataproviders.ProductDataProvider;
import api.endpoints.ProductEndpoints;
import api.payload.ProductPayload;
import api.payload.ProductUpdatePayload;
import api.utils.TestData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

public class ProductTestcases {


    @Test(
            priority = 1,
            dataProvider = "productData",
            dataProviderClass = ProductDataProvider.class,
            description = "Create products from Excel"
    )
    public void createProductTest(
            String productKey,
            ProductPayload payload) {

        Response response =
                ProductEndpoints.createProduct(
                        TestData.merchantId,
                        payload,
                        productKey
                );

        Assert.assertEquals(
                response.getStatusCode(),
                201,
                productKey + " should be created successfully"
        );

        Assert.assertNotNull(
                TestData.productIds.get(productKey),
                productKey + " ID should not be null"
        );
    }


    @Test(
            priority = 2,
            description = "Get products by zone"
    )
    public void getProductsByZoneTest() {

        Response response =
                ProductEndpoints.getProductsByZone(
                        TestData.zoneId
                );

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Products should be fetched successfully by zone"
        );
    }


    @Test(
            priority = 3,
            description = "Update product"
    )
    public void updateProductTest() {

        String productId =
                TestData.productIds.get("product1");

        ProductUpdatePayload payload =
                new ProductUpdatePayload();

        payload.setBrand("Razer");
        payload.setCategory("Gaming Accessories");
        payload.setCreatedDateTime("2025-06-01T11:45:00.000Z");
        payload.setDescription(
                "High-performance wireless gaming mouse with customizable RGB lighting and 20,000 DPI optical sensor."
        );
        payload.setMerchantId(
                Integer.parseInt(TestData.merchantId)
        );
        payload.setName("Razer Viper Ultimate");
        payload.setOffer(15);
        payload.setPrice(1499.99);
        payload.setProductId(
                Integer.parseInt(productId)
        );

        payload.setProductImageURLs(
                Collections.singletonList(
                        "https://assets2.razerzone.com/images/pnx.assets/91d6b6a2b9ebc7c8f4f5d38e1c7ac21a/razer-viper-ultimate-gallery-1.jpg"
                )
        );

        payload.setQuantity(100);
        payload.setRating(4.7);
        payload.setReviews(Collections.emptyList());

        payload.setSearchTags(
                Arrays.asList(
                        "razer",
                        "gaming",
                        "mouse",
                        "accessory",
                        "RGB"
                )
        );

        payload.setStatus("ACTIVE");

        payload.setThumbnailURL(
                "https://assets2.razerzone.com/images/pnx.assets/91d6b6a2b9ebc7c8f4f5d38e1c7ac21a/razer-viper-ultimate-gallery-1.jpg"
        );

        payload.setTitle(
                "Razer Viper Ultimate Wireless Mouse"
        );

        payload.setType("Electronics");
        payload.setZoneId(TestData.zoneId);

        Response response =
                ProductEndpoints.updateProduct(
                        productId,
                        payload
                );

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Product should be updated successfully"
        );
    }


    @Test(
            priority = 4,
            description = "Get product by ID"
    )
    public void getProductByIdTest() {

        String productId =
                TestData.productIds.get("product1");

        Response response =
                ProductEndpoints.getProductById(productId);

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Product should be fetched successfully"
        );

        Assert.assertEquals(
                response.jsonPath().getString("data.productId"),
                productId,
                "Product ID should match"
        );
    }


    @Test(
            priority = 5,
            description = "Get products by merchant"
    )
    public void getProductsByMerchantTest() {

        Response response =
                ProductEndpoints.getProductsByMerchant(
                        TestData.merchantId
                );

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Merchant products should be fetched successfully"
        );
    }


    @Test(
            priority = 6,
            description = "Get all alpha products"
    )
    public void getAllAlphaProductsTest() {

        Response response =
                ProductEndpoints.getAllProducts();

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Alpha products should be fetched successfully"
        );
    }


    @Test(
            priority = 7,
            description = "Delete product"
    )
    public void deleteProductTest() {

        String productId =
                TestData.productIds.get("product1");

        Response response =
                ProductEndpoints.deleteProduct(productId);

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Product should be deleted successfully"
        );
    }
}