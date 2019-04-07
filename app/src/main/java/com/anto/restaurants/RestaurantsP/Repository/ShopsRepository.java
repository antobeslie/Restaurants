package com.anto.restaurants.RestaurantsP.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.anto.restaurants.RoomDatabase;

import java.util.List;

public class ShopsRepository {


    ShopsDao mPlacesDao;
    List<ShopsData> mAllPlaces;

    public ShopsRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mPlacesDao = db.shopsDao();
        mAllPlaces = mPlacesDao.getShopsData();

    }

    List<ShopsData> getShopsData() {

        mAllPlaces = mPlacesDao.getShopsData();
        return mAllPlaces;

    }


    public List<ShopsData> getShopsSearch(String s) {

        mAllPlaces = mPlacesDao.getShopsDataSearch(s);
        return mAllPlaces;

    }

    public void insert(ShopsData place) {


        new ShopsRepository.insertAsyncTask(mPlacesDao).execute(place);
    }

    public void deleteAll() {

        new ShopsRepository.deleteAsyncTask(mPlacesDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<ShopsData, Void, Void> {

        private ShopsDao mAsyncTaskDao;

        insertAsyncTask(ShopsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ShopsData... params) {

            System.out.println("Inserting...");
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private ShopsDao mAsyncTaskDao;

        deleteAsyncTask(ShopsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... params) {

            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

}
