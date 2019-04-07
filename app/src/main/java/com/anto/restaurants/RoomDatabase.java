package com.anto.restaurants;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.anto.restaurants.Home.PlacesDao;
import com.anto.restaurants.Home.PlacesData;
import com.anto.restaurants.RestaurantsP.Repository.ShopsDao;
import com.anto.restaurants.RestaurantsP.Repository.ShopsData;

@Database(entities = {PlacesData.class, ShopsData.class}, version = 1, exportSchema = false)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {

    public abstract PlacesDao placesDao();
    public abstract ShopsDao shopsDao();



    public static RoomDatabase INSTANCE;

    public static RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "rest_database")
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlacesDao mDao;
        private final ShopsDao shopsDao;


        PopulateDbAsync(RoomDatabase db) {
            mDao = db.placesDao();
            shopsDao = db.shopsDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            shopsDao.deleteAll();
            return null;
        }
    }

}
