package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherapp.Model.Ciudad;
import com.example.weatherapp.RecyclerView.CityListAdapter;
import com.example.weatherapp.ViewModel.CityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private CityViewModel mCityViewModel;
    public static final int NEW_CITY_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CityListAdapter adapter = new CityListAdapter(new CityListAdapter.CityDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCityViewModel = new ViewModelProvider(this).get(CityViewModel.class);

        mCityViewModel.getAllCities().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewActivity.class);
            startActivityForResult(intent, NEW_CITY_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CITY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String nombreCiudad = data.getStringExtra(NewActivity.EXTRA_REPLY_CITY_NAME);
            double temperatura = data.getDoubleExtra(NewActivity.EXTRA_REPLY_TEMPERATURE, 0.0);
            int iconResourceId = data.getIntExtra(NewActivity.EXTRA_REPLY_ICON, R.drawable.clima_defecto);


            assert nombreCiudad != null;

            Ciudad city = new Ciudad(nombreCiudad, temperatura, iconResourceId);
            mCityViewModel.insert(city);
        } else {

            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_SHORT).show();
        }
    }
}
