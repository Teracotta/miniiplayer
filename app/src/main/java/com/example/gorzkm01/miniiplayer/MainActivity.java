package com.example.gorzkm01.miniiplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gorzkm01.miniiplayer.highlightsCallbackInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements highlightsCallbackInterface {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<String> results = new ArrayList();
    private String JSONurl = "http://ibl.api.bbci.co.uk/ibl/v1/categories?rights=mobile";
    private RequestQueue requestQueue;
    public final static String EXTRA_MESSAGE = "CATEGORY_TITLE";
    public static final String TAG = "first_tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        requestQueue = Volley.newRequestQueue(this);

        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        JsonObjectRequest obreq = new JsonObjectRequest(JSONurl, null,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray categoriesArray = response.getJSONArray("categories");
                            String id, title;
                            for(int i = 0; i < categoriesArray.length(); i++) {
                                id = categoriesArray.getJSONObject(i).getString("id");
                                title = categoriesArray.getJSONObject(i).getString("title");

                                results.add(title);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        requestQueue.add(obreq);



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new MyAdapter(results, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void callbackCall(String title) {
        Intent intent = new Intent(this, DisplayCategoriesActivity.class);
        intent.putExtra(EXTRA_MESSAGE, title);
        startActivity(intent);
    }
}
