package com.example.volleyjsondemo.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleyjsondemo.R;
import com.example.volleyjsondemo.adapter.FlatDataAdapter;
import com.example.volleyjsondemo.pojo.Flat;
import com.example.volleyjsondemo.pojo.FlatResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvFlatData;
    //String url="http://123.201.36.137:9192/vijoapp/api/flat/search/format/json";
    String url="http://123.201.36.137:9192/vijoapp/api/visitortype/list/format/json";
    public FlatResponse flatResponse;
    public EditText etSearch;
    public static String TAG="MainActivity";
    FlatDataAdapter flatDataAdapter;
    public ArrayList<Flat> flatArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFlatData=findViewById(R.id.rv_flat);
        etSearch=findViewById(R.id.et_search);
        flatArrayList=new ArrayList<>();
        rvFlatData.setLayoutManager(new LinearLayoutManager(this));


        loadData();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(MainActivity.this, ""+s.toString(), Toast.LENGTH_SHORT).show();
                    filter(s.toString());
            }
        });
    }

    private void loadData() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                Log.e(TAG, "onResponse: "+response );
                flatResponse=gson.fromJson(response,FlatResponse.class);

                if(flatResponse.StatusCode.equals("1")){
                        flatDataAdapter=new FlatDataAdapter(MainActivity.this,flatResponse.getData().getVisitor_type());
                        rvFlatData.setAdapter(flatDataAdapter);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Issue"+error, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onErrorResponse: "+error );

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void filter(String text){
        ArrayList<Flat> filteredList=new ArrayList<>();
        for(Flat item:flatResponse.getData().getVisitor_type() ){
            if(item.getVisitor_type().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        Log.e(TAG, "filter: size"+filteredList.size() );
        Log.e(TAG, "filter: List"+filteredList.toString() );
        flatDataAdapter.updateData(MainActivity.this,filteredList);

    }
}
