package api.endpoints;

import api.payload.BankCardPayload;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BankCardEndpoints {


    public static Response createBankAccount(BankCardPayload payload) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.POST_SHOPPER_BANK_ACCOUNT)
                .then()
                .extract()
                .response();
    }


    public static String createBankAccountAndGetId(BankCardPayload payload) {
        Response response = createBankAccount(payload);
        return response.jsonPath().getString("bankAccountId");
    }


    public static Response getBankAccountById(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId)
                .then()
                .extract()
                .response();
    }


    public static Response getAllBankAccounts() {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .get(Routes.POST_SHOPPER_BANK_ACCOUNT)
                .then()
                .extract()
                .response();
    }


    public static Response verifyBankAccount(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .post(Routes.LOGIN_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId + "/verify")
                .then()
                .extract()
                .response();
    }


    public static Response updateBankAccountBalance(String bankAccountId, Double newBalance) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"balance\": " + newBalance + "}")
                .when()
                .put(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId)
                .then()
                .extract()
                .response();
    }


    public static Response createBankTransaction(String bankAccountId, BankCardPayload.TransactionDetails transaction) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(transaction)
                .when()
                .post(Routes.SHOPPER_BANK_CARDS_TRANSACTION)
                .then()
                .extract()
                .response();
    }


    public static Response getBankAccountTransactions(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .queryParam("bankAccountId", bankAccountId)
                .when()
                .get(Routes.SHOPPER_BANK_CARDS_TRANSACTION)
                .then()
                .extract()
                .response();
    }


    public static Response deleteBankAccount(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .when()
                .delete(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId)
                .then()
                .extract()
                .response();
    }


    public static Response setBankAccountAsDefault(String bankAccountId) {
        return given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body("{\"isDefault\": true}")
                .when()
                .put(Routes.POST_SHOPPER_BANK_ACCOUNT + "/" + bankAccountId + "/default")
                .then()
                .extract()
                .response();
    }
}

