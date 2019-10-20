package com.veripark.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class HandShakePost {

    public  HandShakePost()
    {
        sendPost();
    }



   private void sendPost() {

        try {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{\n\t\"deviceId\": \"abcd-123-ff987654\",\n\t\"systemVersion\": \"12.2\",\n\t\"platformName\": \"iOS\",\n\t\"deviceModel\": \"iPhone XS Max\",\n\t\"manifacturer\": \"Apple\"\n}\n");
            Request request = new Request.Builder()
                    .url("https://mobilechallange.veripark.com/api/handshake/start")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
            ObjectMapper mapper = new ObjectMapper();

            HandShakeObject responseBody = mapper.readValue(response.body().string(), HandShakeObject.class);

        }
        catch (Exception ex){
            Exception a  =ex;
        }
    }
}
