package api.testcases;

import api.endpoints.ProductEndpoints;
import api.payload.ProductPayload;
import api.utils.FakeDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.utils.TestData;

public class ProductTestcases {




    @Test(priority = 1, description = "Create product with valid data")
    public void createProductWithValidDataTest() {

        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setDescription("High quality test product");
        payload.setPrice(299.99);
        payload.setDiscountPrice(199.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");
        payload.setSubcategory("MOBILE_PHONES");
        payload.setBrand("TestBrand");
        payload.setMerchantId("merchant-123456");

        Response response = ProductEndpoints.createProduct(TestData.merchantId,payload);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("productId"));

    }

    @Test(priority = 2, description = "Get product by ID")
    public void getProductByIdTest() {

        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setDescription("High quality test product");
        payload.setPrice(299.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        String productId = ProductEndpoints.createProductAndGetId(payload);
        Response response = ProductEndpoints.getProductById(productId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("productId"), productId);

    }

    @Test(priority = 3, description = "Get all products")
    public void getAllProductsTest() {

        Response response = ProductEndpoints.getAllProducts(1, 10, "productName");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 4, description = "Get products by merchant")
    public void getProductsByMerchantTest() {

        Response response = ProductEndpoints.getProductsByMerchant("merchant-123456");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 5, description = "Update product details")
    public void updateProductTest() {

        ProductPayload createPayload = new ProductPayload();
        createPayload.setProductName("Test Product " + System.currentTimeMillis());
        createPayload.setDescription("Initial description");
        createPayload.setPrice(299.99);
        createPayload.setQuantity(100);
        createPayload.setCategory("ELECTRONICS");

        String productId = ProductEndpoints.createProductAndGetId(createPayload);

        ProductPayload updatePayload = new ProductPayload();
        updatePayload.setProductName("Updated Product Name");
        updatePayload.setDescription("Updated description");
        updatePayload.setPrice(249.99);

        Response response = ProductEndpoints.updateProduct(productId, updatePayload);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 6, description = "Search products")
    public void searchProductsTest() {

        Response response = ProductEndpoints.searchProducts("Electronics");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 7, description = "Filter products by category")
    public void filterProductsByCategoryTest() {

        Response response = ProductEndpoints.filterProductsByCategory("ELECTRONICS");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 8, description = "Filter products by price range")
    public void filterProductsByPriceTest() {

        Response response = ProductEndpoints.filterProductsByPrice(100.0, 500.0);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 9, description = "Get in-stock products")
    public void getInStockProductsTest() {

        Response response = ProductEndpoints.getInStockProducts();

        Assert.assertEquals(response.getStatusCode(), 200);

    }



    @Test(priority = 10, dataProvider = "validProductData",
            description = "Create product with data provider")
    public void createProductDataDrivenTest(String productName, Double price, Integer quantity, String category) {

        ProductPayload payload = new ProductPayload();
        payload.setProductName(productName);
        payload.setPrice(price);
        payload.setQuantity(quantity);
        payload.setCategory(category);

        Response response = ProductEndpoints.createProduct(TestData.merchantId, payload);

        Assert.assertEquals(response.getStatusCode(), 201);

    }



    @Test(priority = 20, description = "Create product with missing product name")
    public void createProductWithMissingNameTest() {

        ProductPayload payload = new ProductPayload();
        payload.setDescription("Product without name");
        payload.setPrice(299.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        Response response = ProductEndpoints.createProduct(TestData.merchantId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 21, description = "Create product with invalid price")
    public void createProductWithInvalidPriceTest() {

        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setPrice(-50.0);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        Response response = ProductEndpoints.createProduct(TestData.merchantId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(priority = 22, description = "Create product with invalid quantity")
    public void createProductWithInvalidQuantityTest() {

        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setPrice(299.99);
        payload.setQuantity(-10);
        payload.setCategory("ELECTRONICS");

        Response response = ProductEndpoints.createProduct(TestData.merchantId, payload);

        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test(priority = 23, description = "Get product with invalid ID")
    public void getProductWithInvalidIdTest() {

        Response response = ProductEndpoints.getProductById("invalid-product-id");

        Assert.assertEquals(response.getStatusCode(), 404);

    }

    @Test(priority = 24, description = "Update product that does not exist")
    public void updateNonExistentProductTest() {

        ProductPayload payload = new ProductPayload();
        payload.setProductName("Updated Product");
        payload.setPrice(199.99);

        Response response = ProductEndpoints.updateProduct("non-existent-id", payload);

        Assert.assertEquals(response.getStatusCode(), 404);

    }

    @Test(priority = 25, description = "Delete product")
    public void deleteProductTest() {

        ProductPayload payload = new ProductPayload();
        payload.setProductName("Test Product " + System.currentTimeMillis());
        payload.setPrice(299.99);
        payload.setQuantity(100);
        payload.setCategory("ELECTRONICS");

        String productId = ProductEndpoints.createProductAndGetId(payload);
        Response response = ProductEndpoints.deleteProduct(productId);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 26, description = "Filter products by brand")
    public void filterProductsByBrandTest() {

        Response response = ProductEndpoints.filterProductsByBrand("Samsung");

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 27, description = "Filter products by rating")
    public void filterProductsByRatingTest() {

        Response response = ProductEndpoints.filterProductsByRating(4.0);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 28, description = "Get out of stock products")
    public void getOutOfStockProductsTest() {

        Response response = ProductEndpoints.getOutOfStockProducts();

        Assert.assertEquals(response.getStatusCode(), 200);

    }
}

