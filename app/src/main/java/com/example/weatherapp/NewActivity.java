package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_CITY_NAME = "com.example.weatherapp.REPLY_CITY_NAME";
    public static final String EXTRA_REPLY_TEMPERATURE = "com.example.weatherapp.REPLY_TEMPERATURE";
    public static final String EXTRA_REPLY_ICON = "com.example.weatherapp.REPLY_ICON";

    private EditText mEditCityNameView;
    private EditText mEditTemperatureView;
    private Spinner mWeatherIconSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);

        mEditCityNameView = findViewById(R.id.cityName);
        mEditTemperatureView = findViewById(R.id.temperature);
        mWeatherIconSpinner = findViewById(R.id.spinner_weather_icon);

        setupSpinner();

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditCityNameView.getText()) ||
                    TextUtils.isEmpty(mEditTemperatureView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String cityName = mEditCityNameView.getText().toString();
                double temperature = Double.parseDouble(mEditTemperatureView.getText().toString());
                int iconResourceId = getSelectedIconResourceId();

                replyIntent.putExtra(EXTRA_REPLY_CITY_NAME, cityName);
                replyIntent.putExtra(EXTRA_REPLY_TEMPERATURE, temperature);
                replyIntent.putExtra(EXTRA_REPLY_ICON, iconResourceId);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weather_icons, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWeatherIconSpinner.setAdapter(adapter);
    }

    private int getSelectedIconResourceId() {
        String selectedIcon = mWeatherIconSpinner.getSelectedItem().toString();
        switch (selectedIcon) {
            case "Soleado":
                return R.drawable.sol_sonriendo;
            case "Nublado":
                return R.drawable.nube2;
            case "Lluvioso":
                return R.drawable.nube;
            case "Tormenta":
                return R.drawable.trueno;
            default:
                return R.drawable.clima_defecto;
        }
    }
}



