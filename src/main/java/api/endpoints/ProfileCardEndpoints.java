package api.endpoints;

import api.payload.*;
import api.routes.Routes;
import api.specs.ReusableRequestSpec;
import api.utils.TestData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProfileCardEndpoints {


    public static Response saveProfileCard(ProfileCardPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER_BANK_CARD)
                .then()
                .extract()
                .response();

                if (response.getStatusCode() == 200) {
            String profileCardId = response.jsonPath().getString("data.cardId");
            TestData.merchantToken = profileCardId;
        }

        return response;
    }

public static Response getBankCardByShopperId(String shopperId, String cardType) {

    return given()
            .spec(ReusableRequestSpec.buildShopperRequestSpec())
            .pathParam("shopperId", shopperId)
            .queryParam("type", cardType)
            .when()
            .get(Routes.POST_SHOPPER_BANK_CARD + "/{shopperId}")
            .then()
            .extract()
            .response();
    }

public static Response deleteBankCard() {

    return given()
            .spec(ReusableRequestSpec.buildShopperRequestSpec())
            .when()
            .delete(Routes.POST_SHOPPER_BANK_CARD)
            .then()
            .extract()
            .response();
    }

public static Response getWalletTransactions(String shopperId) {
    return given()
            .spec(ReusableRequestSpec.buildRequestSpec())
            .when()
            .get(Routes.GET_SHOPPER + "/wallets")
            .then()
            .extract()
            .response();
    }
     
}

