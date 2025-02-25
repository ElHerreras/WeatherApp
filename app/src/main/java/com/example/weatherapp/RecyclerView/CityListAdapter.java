package com.example.weatherapp.RecyclerView;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.weatherapp.Model.Ciudad;

public class CityListAdapter extends ListAdapter<Ciudad, CityViewHolder> {

    public CityListAdapter(@NonNull DiffUtil.ItemCallback<Ciudad> diffCallback) {
        super(diffCallback);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CityViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        Ciudad current = getItem(position);
        holder.bind(current.getNombreCiudad(), current.getTemperatura(), current.getIconResourceId());
    }

    public static class CityDiff extends DiffUtil.ItemCallback<Ciudad> {

        @Override
        public boolean areItemsTheSame(@NonNull Ciudad oldItem, @NonNull Ciudad newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ciudad oldItem, @NonNull Ciudad newItem) {
            return oldItem.getNombreCiudad().equals(newItem.getNombreCiudad());
        }
    }
}
