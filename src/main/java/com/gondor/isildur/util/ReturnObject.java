package com.gondor.isildur.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class ReturnObject extends JSONObject {

    public ReturnObject(int resultCode, String resultMsg) {
        this.put("resultCode", resultCode);
        this.put("resultMsg", resultMsg);
    }

    public <E> ReturnObject(E data) {
        this.put("resultCode", 0);
        this.put("resultMsg", "Success");
        this.put("data", data);
    }

    public <E> ReturnObject(int resultCode, String resultMsg, E data) {
        this.put("resultCode", resultCode);
        this.put("resultMsg", resultMsg);
        this.put("data", data);
    }

    public void send(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void successRespond(HttpServletResponse response) {
        respond(response, 0, "Success");
    }

    public static <E> void successRespond(HttpServletResponse response, E data) {
        respond(response, 0, "Success", data);
    }

    public static void respond(HttpServletResponse response, int resultCode, String resultMsg) {
        ReturnObject res = new ReturnObject(resultCode, resultMsg);
        res.send(response);
    }

    public static <E> void respond(HttpServletResponse response, int resultCode, String resultMsg, E data) {
        ReturnObject res = new ReturnObject(resultCode, resultMsg, data);
        res.send(response);
    }
}
