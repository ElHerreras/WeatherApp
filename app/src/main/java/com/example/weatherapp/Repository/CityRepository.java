package com.example.weatherapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.CityDAO.CityDAO;
import com.example.weatherapp.Database.CityRoomDatabase;
import com.example.weatherapp.Model.Ciudad;

import java.util.List;

public class CityRepository {

    private CityDAO mCityDao;
    private LiveData<List<Ciudad>> mAllCities;

    public CityRepository(Application application) {
        CityRoomDatabase db = CityRoomDatabase.getDatabase(application);
        mCityDao = db.cityDao();
        mAllCities = mCityDao.getAlphabetizedCities();
    }

    public LiveData<List<Ciudad>> getAllCities() {
        return mAllCities;
    }

    public void insert(Ciudad city) {
        CityRoomDatabase.databaseWriterExecutor.execute(() -> {
            mCityDao.insert(city);
        });
    }
}
