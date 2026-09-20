package api.endpoints;

import api.payload.ProductPayload;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductEndpoints {


    public static Response createProduct(String merchantId, ProductPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("merchantId", merchantId)
                .body(payload)
                .when()
                .post(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static String createProductAndGetId(ProductPayload payload) {
        throw new UnsupportedOperationException("A merchant id is required by the Postman collection.");
    }


    public static Response getProductById(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.SINGLE_PRODUCT.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response getAllProducts(Integer pageNumber, Integer pageSize, String sortBy) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .queryParam("sortBy", sortBy)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response getProductsByMerchant(String merchantId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.MERCHANT_PRODUCT.replace("{merchantId}", merchantId))
                .then()
                .extract()
                .response();
    }


    public static Response getAllProductsAlphabetically() {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.ALL_PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response updateProduct(String productId, ProductPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("productId", productId)
                .body(payload)
                .when()
                .put(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response deleteProduct(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .delete(Routes.SINGLE_PRODUCT.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }


    public static Response searchProducts(String searchTerm) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("searchTerm", searchTerm)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response filterProductsByCategory(String category) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("category", category)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response filterProductsByPrice(Double minPrice, Double maxPrice) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("minPrice", minPrice)
                .queryParam("maxPrice", maxPrice)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response filterProductsByBrand(String brand) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("brand", brand)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response filterProductsByRating(Double minRating) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("minRating", minRating)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response getInStockProducts() {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("inStock", true)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response getOutOfStockProducts() {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("inStock", false)
                .when()
                .get(Routes.PRODUCT)
                .then()
                .extract()
                .response();
    }
}
