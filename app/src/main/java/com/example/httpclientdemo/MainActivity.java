package com.example.httpclientdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpClientTo httpClientTo = new HttpClientTo();
        httpClientTo.setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setHttpClientLo(new HttpClientLo() {
                    @Override
                    public void success(JSONObject jsonObject) {
                        Log.i("aaa", "success: "+jsonObject.toString());
                    }

                    @Override
                    public void error(String msg) {
                        Log.i("aaa", "error: "+ msg);

                    }
                }).start();

    }


}