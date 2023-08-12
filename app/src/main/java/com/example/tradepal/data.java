package com.example.tradepal;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class data{

    private String ticker;
    public data (String s){
        ticker = s;
    }

    public String getDataRS() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://realstonks.p.rapidapi.com/" + ticker)
                .get()
                .addHeader("X-RapidAPI-Key", "d001be903bmsh72c76a45143a83fp1d2e0djsn66706bb1fe21")
                .addHeader("X-RapidAPI-Host", "realstonks.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response.toString();
    }

    public String getDataYF() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "symbol=" + ticker);
        Request request = new Request.Builder()
                .url("https://yfinance-stock-market-data.p.rapidapi.com/stock-info")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("X-RapidAPI-Key", "d001be903bmsh72c76a45143a83fp1d2e0djsn66706bb1fe21")
                .addHeader("X-RapidAPI-Host", "yfinance-stock-market-data.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response.toString();
    }
}
