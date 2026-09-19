package api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ReusableRequestSpec {


  public static RequestSpecification buildRequestSpec() {

    RequestSpecBuilder builder = new RequestSpecBuilder();

    builder.setBaseUri("https://www.shoppersstack.com/shopping")
           .setContentType(ContentType.JSON)
           .setAccept(ContentType.JSON);

    return builder.build();
}


    public static RequestSpecification buildAuthenticatedRequestSpec(String token) {
        RequestSpecification spec = buildRequestSpec();
        return spec.header("Authorization", "Bearer " + token);
    }


    private static void addLogging(RequestSpecBuilder builder) {
        try {
            PrintStream requestLog = new PrintStream(new FileOutputStream("./test-output/request-logs.txt", true));
            PrintStream responseLog = new PrintStream(new FileOutputStream("./test-output/response-logs.txt", true));

            builder.addFilter(new RequestLoggingFilter(requestLog));
            builder.addFilter(new ResponseLoggingFilter(responseLog));
        } catch (IOException e) {
            System.out.println("Error setting up logging: " + e.getMessage());
        }
    }


    public static RequestSpecification buildRequestSpecWithBaseURI(String baseURI) {

    RequestSpecBuilder builder = new RequestSpecBuilder();

    builder.setBaseUri(baseURI)
           .setContentType(ContentType.JSON)
           .setAccept(ContentType.JSON);

    addLogging(builder);

        return builder.build();
    }


    public static RequestSpecification buildRequestSpecWithHeaders(String... headers) {
        RequestSpecification spec = buildRequestSpec();

        if (headers.length % 2 == 0) {
            for (int i = 0; i < headers.length; i += 2) {
                spec = spec.header(headers[i], headers[i + 1]);
            }
        }

        return spec;
    }
}

