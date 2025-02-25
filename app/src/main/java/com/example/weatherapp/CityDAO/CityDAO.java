package com.example.weatherapp.CityDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weatherapp.Model.Ciudad;

import java.util.List;

@Dao
public interface CityDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Ciudad city);

    @Query("SELECT * FROM Ciudad ORDER BY nombreCiudad ASC")
    LiveData<List<Ciudad>> getAlphabetizedCities();

    @Query("DELETE FROM Ciudad")
    void deleteAll();
}
