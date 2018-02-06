package com.walmartlabstest.babypink;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class OpeningActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner mCountriesSpinner;
    Spinner mStatesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        mCountriesSpinner = findViewById(R.id.country_spinner);
        mStatesSpinner = findViewById(R.id.state_spinner);

        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item,
                getResources().getStringArray(R.array.countries));
        mCountriesSpinner.setAdapter(countriesAdapter);
        mCountriesSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> statesAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item,
                getResources().getStringArray(R.array.empty_states));
        mStatesSpinner.setAdapter(statesAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        @ArrayRes int statesRes = R.array.empty_states;
        switch (position) {
            case 1:
                statesRes = R.array.us_states;
                break;
            case 2:
                statesRes = R.array.india_states;
                break;
            case 3:
                statesRes = R.array.mexico_states;
        }
        ArrayAdapter<String> statesAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item,
                getResources().getStringArray(statesRes));
        mStatesSpinner.setAdapter(statesAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ArrayAdapter<String> statesAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item,
                getResources().getStringArray(R.array.empty_states));
        mStatesSpinner.setAdapter(statesAdapter);
    }

    public void submit(View view) {
        int countriesSelectedPos = mCountriesSpinner.getSelectedItemPosition();
        int statesSelectedPos = mStatesSpinner.getSelectedItemPosition();
        if (countriesSelectedPos == 0 || statesSelectedPos == 0) {
            Toast.makeText(this, R.string.initial_submit_error, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
        }
    }
}
