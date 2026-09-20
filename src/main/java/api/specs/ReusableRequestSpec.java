package api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import api.utils.TestData;

public class ReusableRequestSpec {

    public static RequestSpecification buildRequestSpec() {

        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri("https://www.shoppersstack.com/shopping")
               .setContentType(ContentType.JSON)
               .setAccept(ContentType.JSON);

        return builder.build().relaxedHTTPSValidation();
    }

    public static RequestSpecification buildAdminRequestSpec() {

        return buildRequestSpec()
                .header("Authorization", "Bearer " + TestData.adminToken);
    }

    public static RequestSpecification buildMerchantRequestSpec() {

        return buildRequestSpec()
                .header("Authorization", "Bearer " + TestData.merchantToken);
    }

    public static RequestSpecification buildShopperRequestSpec() {

        return buildRequestSpec()
                .header("Authorization", "Bearer " + TestData.shopperToken);
    }
}