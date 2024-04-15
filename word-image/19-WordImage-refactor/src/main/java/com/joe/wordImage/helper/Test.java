package com.joe.wordImage.helper;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

    public static void main(String[] args) {
        JSONObject jo = new JSONObject();

        jo.put("firstName", "John");

        jo.put("lastName", "Doe");

        JSONArray ja = new JSONArray();

        ja.put(jo);

        System.out.println(ja);

        JSONObject mainObj = new JSONObject();

        mainObj.put("employees", ja);

        System.out.println(mainObj);

    }
}
