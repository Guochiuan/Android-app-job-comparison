package edu.gatech.seclass.jobcompare6300.setting;

import android.app.Application;
import android.os.AsyncTask;
import java.util.concurrent.ExecutionException;
import edu.gatech.seclass.jobcompare6300.database.Database;

public class SettingRepository {

    private SettingDao settingDao;

    SettingRepository(Application application) {
        Database db = Database.getInstance(application);
        settingDao = db.SettingDao();
    }

    public void update(Setting setting) {
        new updateSettingAsyncTask(settingDao).execute(setting);
    }

    public void insert(Setting setting) {
        new insertAsyncTask(settingDao).execute(setting);
    }

    public Setting getSetting() throws ExecutionException, InterruptedException {
        return new SettingRepository.getSettingAsyncTask(settingDao).execute().get();
    }

    private static class insertAsyncTask extends AsyncTask<Setting, Void, Void> {

        private SettingDao mAsyncTaskDao;

        insertAsyncTask(SettingDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Setting... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateSettingAsyncTask extends AsyncTask<Setting, Void, Void> {
        private SettingDao mAsyncTaskDao;

        updateSettingAsyncTask(SettingDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Setting... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class getSettingAsyncTask extends AsyncTask<Void, Void, Setting> {
        private SettingDao mAsyncTaskDao;

        getSettingAsyncTask(SettingDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Setting doInBackground(Void... voids) {
            Setting setting = mAsyncTaskDao.getSetting();
            return setting;
        }

        @Override
        protected void onPostExecute(Setting setting) {
            super.onPostExecute(setting);
        }
    }


}
