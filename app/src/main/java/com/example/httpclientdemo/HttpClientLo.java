package com.example.httpclientdemo;

import org.json.JSONObject;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/10 at 14:46
 */
public interface HttpClientLo {
    void success(JSONObject jsonObject);
    void error(String msg);
}
