package com.example.coronavirustracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Wave;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv_totalCases, tv_activeCases, tv_recovered, tv_criticalCases, tv_todayCases, tv_todayDeaths, tv_affectedCountries, tv_totalDeaths;
    SpinKitView spinKitView;
    ScrollView scrollView;
    PieChart pieChart;
    Button btn_track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_activeCases = findViewById(R.id.tv_activeCases);
        tv_affectedCountries = findViewById(R.id.tv_affectedCountries);
        tv_criticalCases = findViewById(R.id.tv_critical);
        tv_totalCases = findViewById(R.id.tv_totalCases);
        tv_recovered = findViewById(R.id.tv_recoveredCases);
        tv_todayCases = findViewById(R.id.tv_todayCases);
        tv_todayDeaths = findViewById(R.id.tv_todayDeaths);
        tv_totalDeaths = findViewById(R.id.tv_totalDeaths);
        pieChart = findViewById(R.id.piechart);
        scrollView = findViewById(R.id.scrollView_scrollStats);
        spinKitView = findViewById(R.id.spin_kit);
        btn_track = findViewById(R.id.btn_track);

        fetchData();


        btn_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AffectedCountries.class));
            }
        });

    }

    private void fetchData() {
        String url = "https://disease.sh/v3/covid-19/all/";

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tv_totalCases.setText(jsonObject.getString("cases"));
                    tv_activeCases.setText(jsonObject.getString("active"));
                    tv_recovered.setText(jsonObject.getString("recovered"));
                    tv_affectedCountries.setText(jsonObject.getString("affectedCountries"));
                    tv_todayCases.setText(jsonObject.getString("todayCases"));
                    tv_todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tv_totalDeaths.setText(jsonObject.getString("deaths"));
                    tv_criticalCases.setText(jsonObject.getString("critical"));

                    pieChart.addPieSlice(new PieModel("Total Cases",
                            Integer.parseInt(tv_totalCases.getText().toString()),
                            Color.parseColor("#FFA726")));

                    pieChart.addPieSlice(new PieModel("Recovered",
                            Integer.parseInt(tv_recovered.getText().toString()),
                            Color.parseColor("#66BB6A")));

                    pieChart.addPieSlice(new PieModel("Active Cases",
                                                Integer.parseInt(tv_activeCases.getText().toString()),
                                                Color.parseColor("#29B6F6")));

                    pieChart.addPieSlice(new PieModel("Deaths",
                            Integer.parseInt(tv_totalDeaths.getText().toString()),
                            Color.parseColor("#EF5350")));
                    pieChart.startAnimation();


                    progressBar.setVisibility(View.INVISIBLE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.INVISIBLE);
                    scrollView.setVisibility(View.INVISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        requestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_slot, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_bookSlot:
                Intent intent = new Intent(MainActivity.this, BookVaccineActivity.class);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}