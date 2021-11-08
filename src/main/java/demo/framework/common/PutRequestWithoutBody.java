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

import static io.restassured.filter.log.RequestLoggingFilter.logRequestTo;

public class PutRequestWithoutBody {

    public static Response sendPutRequestWithoutBody(Headers headers, String url, ContentType contentType, String accept){

        StringWriter writer = new StringWriter();
        PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);

        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(headers)
                .accept(accept)
                .filter(logRequestTo(captor))
                .put(url);

        ExtentTestManager.getTest().log(Status.INFO, writer.toString());
        ExtentTestManager.getTest().log(Status.INFO, "Response status : " + response.statusCode());
        return response;
    }
}

