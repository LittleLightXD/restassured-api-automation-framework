package api.endpoints;

import api.payload.*;
import api.specs.ReusableRequestSpec;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import api.utils.TestData;

public class AdminEndpoints {


    public static Response createAdmin(AdminPayload payload) {

        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(payload)
                .when()
                .post(Routes.CREATE_ADMIN)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response getAdminById(String adminId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAdminRequestSpec())
                .pathParam("adminId", adminId)
                .when()
                .get(Routes.GET_ADMIN)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response updateAdmin(String adminId, AdminPayload payload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildAdminRequestSpec())
                .pathParam("adminId", adminId)
                .body(payload)
                .when()
                .put(Routes.UPDATE_ADMIN)
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response deleteAdmin(String adminId) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .pathParam("adminId", adminId)
                .when()
                .delete(Routes.GET_ADMIN) // same endpoint as Get Admin
                .then()
                .extract()
                .response();

        return response;
    }


    public static Response loginAdmin(LoginAdminPayload loginPayload) {
        Response response = given()
                .spec(ReusableRequestSpec.buildRequestSpec())
                .body(loginPayload)
                .when()
                .post(Routes.LOGIN)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() == 200) {
            String token = response.jsonPath().getString("data.jwtToken");
            String userId = response.jsonPath().getString("data.userId");
            TestData.adminToken = token;
            TestData.adminId = userId;
        }

        return response;
    }
}
