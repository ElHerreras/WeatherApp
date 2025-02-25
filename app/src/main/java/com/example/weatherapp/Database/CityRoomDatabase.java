package com.example.weatherapp.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.weatherapp.CityDAO.CityDAO;
import com.example.weatherapp.Model.Ciudad;
import com.example.weatherapp.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Ciudad.class}, version = 1, exportSchema = false)
public abstract class CityRoomDatabase extends RoomDatabase {

    public abstract CityDAO cityDao();

    private static volatile CityRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CityRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CityRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CityRoomDatabase.class, "cities")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        //Sobreescribimos el metodo onCreate que se llama cuando la bbdd se crea por primera vez
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriterExecutor.execute(() -> {
                CityDAO dao = INSTANCE.cityDao();
                dao.deleteAll();


                Ciudad city1 = new Ciudad("Madrid", 10.5, R.drawable.nube2);
                Ciudad city2 = new Ciudad("Murcia", 20.2, R.drawable.sol_sonriendo);
                Ciudad city3 = new Ciudad("Santiago", 8.0, R.drawable.trueno);
                Ciudad city4 = new Ciudad("Extremadura", 12.7, R.drawable.nube);

                dao.insert(city1);
                dao.insert(city2);
                dao.insert(city3);
                dao.insert(city4);
            });
        }
    };
}
