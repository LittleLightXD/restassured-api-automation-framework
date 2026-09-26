package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import api.utils.TestData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductEndpoints {


    public static Response createProduct(String merchantId, ProductPayload payload, String productKey) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("merchantId", merchantId)
                .body(payload)
                .when()
                .post(Routes.PRODUCT)
                .then()
                .extract()
                .response();

                 if (response.getStatusCode() == 201) {
                    TestData.productIds.put(productKey,response.jsonPath().getString("data.productId")
        );
    }
        return response;
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

}
