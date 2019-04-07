package com.anto.restaurants.Home;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.anto.restaurants.RoomDatabase;

import java.util.List;

public class PlacesRepository {

    PlacesDao mPlacesDao;
    LiveData<List<PlacesData>> mAllPlaces;


    PlacesRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mPlacesDao = db.placesDao();
        mAllPlaces = mPlacesDao.getAllPlaces();

    }

    LiveData<List<PlacesData>> getAllPlaces() {


        mAllPlaces = mPlacesDao.getAllPlaces();
        return mAllPlaces;

    }

    public void insert(PlacesData place) {
        new insertAsyncTask(mPlacesDao).execute(place);
    }

    public void deleteAll() {

        new deleteAsyncTask(mPlacesDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<PlacesData, Void, Void> {

        private PlacesDao mAsyncTaskDao;

        insertAsyncTask(PlacesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlacesData... params) {

            System.out.println("Inserting Place...");
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private PlacesDao mAsyncTaskDao;

        deleteAsyncTask(PlacesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... params) {

            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

}
