package com.example.coronavirustracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.Wave;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {
    EditText et_searchET;
    ListView lv_searchLV;
    SpinKitView spinKitView;
    ProgressBar progressBar;
    public static List<CountryModel> countryModelList = new ArrayList<>();
    CountryModel countryModel;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);
        et_searchET = findViewById(R.id.et_search);
        lv_searchLV = (ListView) findViewById(R.id.lv_trackCountriesListView);
        progressBar = findViewById(R.id.spin_kitSearchActivity);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fetchData();

        lv_searchLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), DetailActivity.class).putExtra("position", position));
            }
        });
        et_searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myAdapter.getFilter().filter(s);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url = "https://disease.sh/v3/covid-19/countries/";

        ProgressBar progressBar = findViewById(R.id.spin_kitSearchActivity);
        ThreeBounce threeBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(threeBounce);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i = 0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String countryName = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String todayDeaths = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String active = jsonObject.getString("active");
                        String critical = jsonObject.getString("critical");
                        String tests = jsonObject.getString("tests");

                        String totalDeaths = jsonObject.getString("deaths");

                        JSONObject countryInfoJsonObject = jsonObject.getJSONObject("countryInfo");
                        String flagURL = countryInfoJsonObject.getString("flag");

                        countryModel = new CountryModel(flagURL, countryName, cases, todayCases,todayDeaths,recovered, active, critical, tests, totalDeaths);
                        countryModelList.add(countryModel);
                    }

                    myAdapter = new MyAdapter(AffectedCountries.this, countryModelList);
                    lv_searchLV.setAdapter(myAdapter);
                    progressBar.setVisibility(View.INVISIBLE);


                } catch (JSONException e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AffectedCountries.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        requestQueue.add(request);
    }
}