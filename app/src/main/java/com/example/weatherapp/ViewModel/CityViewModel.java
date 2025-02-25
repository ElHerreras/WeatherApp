package com.example.weatherapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherapp.Model.Ciudad;
import com.example.weatherapp.Repository.CityRepository;

import java.util.List;

//Heredamos de AndroidViewModel
public class CityViewModel extends AndroidViewModel {

    //Instanciamos el repositorio para manejar las operaciones de datos
    private final CityRepository cityRepository;

    //Creamos una lista que contendra una lista conservable de elementos City
    private final LiveData<List<Ciudad>> cAllCities;

    //Definimos el constructor de la clase e inicializamos la clase padre con la aplicación
    public CityViewModel (Application application) {
        super(application);
        //Inicializamos la variable del repositorio instanciando el repositorio y pasandole la aplicacion
        cityRepository = new CityRepository(application);
        //Devolvemos un LiveData con todas las ciudades
        cAllCities = cityRepository.getAllCities();
    }

    //Esto permite a otras clases observar los cambios en la lista de ciudades
    public LiveData<List<Ciudad>> getAllCities() { return cAllCities; }

    //Método que toma un objeto City como parámentro y lo inserta en el repositorio
    public void insert (Ciudad city) { cityRepository.insert(city); }
}
