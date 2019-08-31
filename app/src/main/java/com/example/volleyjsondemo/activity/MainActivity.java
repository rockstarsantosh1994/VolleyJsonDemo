package com.example.volleyjsondemo.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleyjsondemo.R;
import com.example.volleyjsondemo.adapter.FlatDataAdapter;
import com.example.volleyjsondemo.pojo.FlatResponse;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvFlatData;
    String url="http://123.201.36.137:9192/vijoapp/api/flat/search/format/json";
    public FlatResponse flatResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFlatData=findViewById(R.id.rv_flat);
        rvFlatData.setLayoutManager(new LinearLayoutManager(this));


        loadData();
    }

    private void loadData() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();

                flatResponse=gson.fromJson(response,FlatResponse.class);

                if(flatResponse.StatusCode.equals("1")){
                        rvFlatData.setAdapter(new FlatDataAdapter(MainActivity.this,flatResponse.getData().getFlat()));
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Issue"+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
