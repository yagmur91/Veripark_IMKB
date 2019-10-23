package com.veripark.services;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.veripark.services.Model.StockListResponseModel;


public class StockServices {
    public StockListResponseModel getStockList(String auth, String encriptedPeriod){
        try{
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"period\": \""+ encriptedPeriod +"\"\n}\n");
            Request request = new Request.Builder()
                    .url("https://mobilechallange.veripark.com/api/stocks/list")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-VP-Authorization", auth)
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
            ObjectMapper mapper = new ObjectMapper();

            StockListResponseModel responseBody = mapper.readValue(response.body().string(), StockListResponseModel.class);
            return responseBody;
        }
        catch (Exception ex){
            Log.println(Log.ERROR, "StockList service", ex.getMessage());
        }
        return null;
    }
}
