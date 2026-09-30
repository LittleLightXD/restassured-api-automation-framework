package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;
import java.util.*;

import static io.restassured.RestAssured.given;

public class ProductEndpoints {


    public static Response createProducts(String merchantId,List<ProductPayload> payloads) {

        return given()
            .spec(ReusableRequestSpec.buildMerchantRequestSpec())
            .queryParam("merchantId", merchantId)
            .body(payloads)
            .when()
            .post(Routes.PRODUCT)
            .then()
            .extract()
            .response();
    }


    public static Response getProductById(String productId) {
        return given()
                .spec(ReusableRequestSpec.buildAdminRequestSpec())
                .when()
                .get(Routes.SINGLE_PRODUCT.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

    public static Response getAllProducts() {
        return given()
                .spec(ReusableRequestSpec.buildAdminRequestSpec())
                .when()
                .get(Routes.ALL_PRODUCT)
                .then()
                .extract()
                .response();
    }


    public static Response updateProduct(String productId, ProductUpdatePayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildMerchantRequestSpec())
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
                .spec(ReusableRequestSpec.buildMerchantRequestSpec())
                .when()
                .delete(Routes.SINGLE_PRODUCT.replace("{productId}", productId))
                .then()
                .extract()
                .response();
    }

}
