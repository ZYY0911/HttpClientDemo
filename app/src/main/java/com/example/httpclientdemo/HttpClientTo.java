package com.example.httpclientdemo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/10 at 14:37
 */
public class HttpClientTo extends Thread {
    private String Url = "http://118.190.26.201:8080/traffic/";
    private boolean isLoop = false;
    private int time;
    private JSONObject jsonObject = new JSONObject();
    private HttpClientLo httpClientLo;

    public HttpClientTo setHttpClientLo(HttpClientLo httpClientLo) {
        this.httpClientLo = httpClientLo;
        return this;
    }

    public HttpClientTo setUrl(String url) {
        Url += url;
        return this;
    }

    public HttpClientTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public HttpClientTo setTime(int time) {
        this.time = time;
        return this;
    }

    public HttpClientTo setJsonObject(String k, Object v) {
        try {
            jsonObject.put(k, v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            try {
                URL url = new URL(Url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestProperty("Content-type",
                        "application/json;charset=utf-8");
                connection.connect();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        connection.getOutputStream(), "utf-8"));
                writer.write(jsonObject.toString());
                writer.close();
                int requestCode = connection.getResponseCode();
                if (requestCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    String result = new Scanner(inputStream).useDelimiter("\\Z").next();
                    httpClientLo.success(new JSONObject(result));
                } else {
                    InputStream inputStream = connection.getErrorStream();
                    httpClientLo.error(new Scanner(inputStream).useDelimiter("\\Z").next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);
    }
}
