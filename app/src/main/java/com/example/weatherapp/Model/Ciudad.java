package com.example.weatherapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Ciudad")
public class Ciudad {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nombreCiudad")
    private String nombreCiudad;
    @ColumnInfo(name = "temperatura")
    private double temperatura;
    @ColumnInfo(name = "clima")
    private int iconResourceId;

    public Ciudad(@NonNull String nombreCiudad, double temperatura, int iconResourceId) {
        this.nombreCiudad = nombreCiudad;
        this.temperatura = temperatura;
        this.iconResourceId = iconResourceId;
    }

    @NonNull
    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

}
