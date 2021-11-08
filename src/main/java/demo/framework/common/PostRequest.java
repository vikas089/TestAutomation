package demo.framework.common;

import com.aventstack.extentreports.Status;
import demo.utils.reports.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.RequestLoggingFilter.logRequestTo;

public class PostRequest {

    public static Response sendPostRequest(Headers headers, String url, String body, ContentType contentType, String accept)  {

        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);

        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(headers)
                .accept(accept)
                .body(body)
                .filter(logRequestTo(captor)).log().all()
                .post(url);
        try {
            response.wait(30000);
        } catch (Exception exception) {

        }

        ExtentTestManager.getTest().log(Status.INFO, writer.toString());
        ExtentTestManager.getTest().log(Status.INFO, "Response status : " + response.statusCode());
        return response;
    }

    public static Response sendPostRequestWithoutContentType(String url) {

        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);

        Response response = RestAssured
                .given()
                .post(url);

        ExtentTestManager.getTest().log(Status.INFO, writer.toString());
        ExtentTestManager.getTest().log(Status.INFO, "Response status : " + response.statusCode());
        return response;
    }

    public static Response sendPostRequestWithRelaxedHTTPValidation(Headers headers, String url, String body, ContentType contentType, String accept) {

        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);

        RestAssured.useRelaxedHTTPSValidation();
        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(headers)
                .accept(accept)
                .body(body)
                .filter(logRequestTo(captor))
                .post(url);

        ExtentTestManager.getTest().log(Status.INFO, writer.toString());
        ExtentTestManager.getTest().log(Status.INFO, "Response status : " + response.statusCode());
        return response;
    }

    public static Response soapCallResponse(String endPointURL, String soapAction, String soapBody) {
        RestAssured.baseURI = endPointURL;
        return given()
                .headers("SOAPAction", soapAction, "Content-Type", "text/xml")
                .and()
                .body(soapBody)
                .when()
                .post()
                .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();
    }

}