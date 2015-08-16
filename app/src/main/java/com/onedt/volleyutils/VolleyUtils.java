package com.onedt.volleyutils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by cxh on 2015/8/9.
 */
public class VolleyUtils {
    private static RequestQueue queue = Volley.newRequestQueue(MyApplication.getApp().getApplicationContext());

    /**
     * get请求方式，通过StringRequest进行请求
     * @param context
     * @param url
     * @param tag
     * @param listener
     */
    public static void getRequest(Context context, String url, String tag, final VolleyListener listener){
        queue.cancelAll(tag);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        });
        request.setTag(tag);
        queue.add(request);
    }

    /**
     *  post请求方式，通过key-value方式来提交，使用StringRequest请求
     * @param context
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void postRequest(Context context, String url, String tag, final Map<String, String> params, final VolleyListener listener){
        queue.cancelAll(tag);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        request.setTag(tag);
        queue.add(request);
    }

    /**
     * post请求方式，通过json方式来提交，使用JsonObjectRequest请求
     * @param context
     * @param url
     * @param tag
     * @param params
     * @param listener
     */
    public static void postJsonRequest(Context context, String url, String tag, Map<String, String> params, final VolleyListener listener) {
        queue.cancelAll(tag);
        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        });
        request.setTag(tag);
        queue.add(request);
    }

    public interface VolleyListener{
        void onSuccess(String response);
        void onFailed(VolleyError error);
    }


}
