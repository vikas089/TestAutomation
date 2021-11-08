package demo.framework.common;


import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class RequestHeaders {

    public static Headers set_Rest_Headers(){
        Header h1= new Header("userId", "");
        Header h2 = new Header("password", "");
        Header h3 = new Header("applicationCode",  "");
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        list.add(h3);
        Headers header = new Headers(list);
        return header;
    }
    public static Headers set_Blank_Headers(){
        List<Header> list = new ArrayList<Header>();
        Headers header = new Headers(list);
        return header;
    }

}

