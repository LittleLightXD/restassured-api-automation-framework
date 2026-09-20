package api.endpoints;

import api.payload.WalletPayload;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class WalletEndpoints {


    public static Response getWalletBalance(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get("/wallet/" + shopperId + "/balance")
                .then()
                .extract()
                .response();
    }


    public static Response addFundsToWallet(String shopperId, Double amount) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"amount\": " + amount + ", \"transactionType\": \"ADD_FUNDS\"}")
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }


    public static Response deductFundsFromWallet(String shopperId, Double amount, String reason) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"amount\": " + amount + ", \"transactionType\": \"DEBIT\", \"reason\": \"" + reason + "\"}")
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }


    public static Response processWalletTransaction(String shopperId, WalletPayload.WalletTransaction transaction) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(transaction)
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }


    public static Response getWalletTransactionHistory(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get("/wallet/" + shopperId + "/transactions")
                .then()
                .extract()
                .response();
    }


    public static Response refundToWallet(String shopperId, Double amount, String orderId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"amount\": " + amount + ", \"transactionType\": \"REFUND\", \"orderId\": \"" + orderId + "\"}")
                .when()
                .post("/wallet/" + shopperId + "/transaction")
                .then()
                .extract()
                .response();
    }


    public static Response getWalletDetails(String shopperId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get("/wallet/" + shopperId)
                .then()
                .extract()
                .response();
    }


    public static Response withdrawFundsFromWallet(String shopperId, Double amount) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"amount\": " + amount + ", \"transactionType\": \"WITHDRAWAL\"}")
                .when()
                .post("/wallet/" + shopperId + "/withdraw")
                .then()
                .extract()
                .response();
    }
}

