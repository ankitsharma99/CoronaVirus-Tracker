package com.example.coronavirustracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private  int positionCountry;

    TextView tv_totalCases, tv_activeCase, tv_recovered, tv_critical, tv_todayCases, tv_todayDeaths, tv_totalDeaths, tv_testsDone, tv_country;
    ImageView flagURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);
        getSupportActionBar().setTitle("Details of " + AffectedCountries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        tv_country = findViewById(R.id.tv_countryNameDetail);
        tv_totalCases = findViewById(R.id.tv_totalCases);
        tv_activeCase = findViewById(R.id.tv_activeCases);
        tv_recovered = findViewById(R.id.tv_recoveredCases);
        tv_critical = findViewById(R.id.tv_critical);
        tv_todayCases = findViewById(R.id.tv_todayCases);
        tv_todayDeaths = findViewById(R.id.tv_todayDeaths);
        tv_totalDeaths = findViewById(R.id.tv_totalDeaths);
        tv_testsDone = findViewById(R.id.tv_testsDone);
        flagURL = findViewById(R.id.imageView2);


        tv_country.setText(AffectedCountries.countryModelList.get(positionCountry).getCountry());
        tv_totalCases.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
        tv_activeCase.setText(AffectedCountries.countryModelList.get(positionCountry).getActiveCases());
        tv_recovered.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
        tv_critical.setText(AffectedCountries.countryModelList.get(positionCountry).getCriticalCases());
        tv_todayCases.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayCases());
        tv_todayDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayDeath());
        tv_totalDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getTotalDeaths());
        tv_testsDone.setText(AffectedCountries.countryModelList.get(positionCountry).getTestsDone());
        Glide.with(DetailActivity.this).load(AffectedCountries.countryModelList.get(positionCountry).getFlagURL()).into(flagURL);




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}