package com.onedt.volleyutils;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

public class MainActivity extends Activity {
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_content = (TextView) findViewById(R.id.tv_content);
        VolleyUtils.getRequest(this, "http:www.baidu.com", "", new VolleyListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                tv_content.setText(response);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }


}
