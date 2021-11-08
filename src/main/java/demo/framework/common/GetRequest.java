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
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.RequestLoggingFilter.logRequestTo;

public class GetRequest {

    public static Response sendGetRequest(Headers headers, String url, ContentType contentType, String accept){

        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);

        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(headers)
                .accept(accept)
                .filter(logRequestTo(captor))
                .get(url);

        ExtentTestManager.getTest().log(Status.INFO, writer.toString());
        ExtentTestManager.getTest().log(Status.INFO, "Response status : " + response.statusCode());
        return response;
    }

    public static Response getWithQueryParams(Headers headers, HashMap<String,String> param, String uri) {
        return given()
                .params(param)
                .headers(headers)
                .log()
                .all()
                .get(uri);
    }

    public static Response sendGetWithOutRequest(String url){

        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);
        //RestAssured.useRelaxedHTTPSValidation();

        //RestAssured.authentication = basic("foo", "bar");
        //RestAssured.baseURI = url;

       // RestAssured.proxy = host("zscaler").withPort(10068);
        //RestAssured.proxy = host("86.00").withPort(10068);

        RestAssured.useRelaxedHTTPSValidation();
        //RestAssured.given().when().get().then().statusCode(200);
        Response response = RestAssured.given().get(url);

        // Response response = RestAssured.given()
        //      .get(url);
        //Response response = RestAssured.given().when().get(url);


        ExtentTestManager.getTest().log(Status.INFO, writer.toString());
        ExtentTestManager.getTest().log(Status.INFO, "Response status : " + response.statusCode());
        return response;
    }
}