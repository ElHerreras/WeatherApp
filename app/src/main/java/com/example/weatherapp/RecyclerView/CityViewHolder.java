package com.example.weatherapp.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

public class CityViewHolder  extends RecyclerView.ViewHolder {

    private final TextView cityNameItemView;
    private final TextView temperatureItemView;
    private final ImageView iconItemView;

    private CityViewHolder (View itemView) {
        super(itemView);
        cityNameItemView = itemView.findViewById(R.id.cityName);
        temperatureItemView = itemView.findViewById(R.id.temperature);
        iconItemView = itemView.findViewById(R.id.icon);
    }

    @SuppressLint("DefaultLocale")
    public void bind(String cityName, double temperature, int iconResourceId) {
        cityNameItemView.setText(cityName);
        temperatureItemView.setText(String.format("%.1f°C", temperature));
        iconItemView.setImageResource(iconResourceId);
    }

    static CityViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CityViewHolder(view);
    }
}
