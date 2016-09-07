package com.example.gorzkm01.miniiplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayCategoriesActivity extends AppCompatActivity implements episodesCallbackInterface {
    private RecyclerView categoriesRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<String> results = new ArrayList();
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_categories_activity);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // TextView textView = new TextView(this);
        // textView.setText(message);

        // TextView layout = (TextView) findViewById(R.id.highlight_title);
        // Log.v("message here", message);
        setTitle(message);



        String JSONurl = "http://ibl.api.bbci.co.uk/ibl/v1/categories/" + message + "/highlights?lang=en&rights=mobile&availability=available";
        Log.v("JSONURL", JSONurl);
        // layout.addView(textView);

        categoriesRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_categories_view);

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
                            JSONObject categoryHighlights = response.getJSONObject("category_highlights");
                            JSONArray highlightsElements = categoryHighlights.getJSONArray("elements");
                            String id, title, subtitle;
                            for(int i = 0; i < highlightsElements.length(); i++) {
                                id = highlightsElements.getJSONObject(i).getString("id");
                                title = highlightsElements.getJSONObject(i).getString("title");
                                // subtitle = highlightsElements.getJSONObject(i).getString("subtitle");

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
        categoriesRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        categoriesRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new HighlightsAdapter(results, this);
        categoriesRecyclerView.setAdapter(mAdapter);

    }

    public void callbackCall(String title) {
        Log.d("DisplayCategoriesA", "DisplayCategoriesA");
        // openCategoryWindow();
        // Intent intent = new Intent(this, DisplayCategoriesActivity.class);
        // TextView categoryTitle = (TextView) findViewById(R.id.info_text);
        // String message = categoryTitle.getText().toString();

        // intent.putExtra(EXTRA_MESSAGE, title);
        // startActivity(intent);
    }
}
