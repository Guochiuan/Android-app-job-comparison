package edu.gatech.seclass.jobcompare6300.job;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;
import java.util.concurrent.ExecutionException;
import edu.gatech.seclass.jobcompare6300.database.Database;

public class JobRepository {

    private JobDao jobDao;
    private LiveData<List<Job>> allJobs;

    JobRepository(Application application) {
        Database db = Database.getInstance(application);
        jobDao = db.JobDao();
        allJobs = jobDao.getAllJobs();
    }


    public Job findCurrent() throws ExecutionException, InterruptedException {
        return new findCurrentAsyncTask(jobDao).execute().get();
    }

    public List<Job> getList() throws ExecutionException, InterruptedException {
        return new getListAsyncTask(jobDao).execute().get();
    }

    public List<Job> getTwoSelected() throws ExecutionException, InterruptedException {
        return new getTwoSelectedAsyncTask(jobDao).execute().get();
    }

    public void update(Job job) {
        new updateJobAsyncTask(jobDao).execute(job);
    }

    public void insert(Job job) {
        new insertAsyncTask(jobDao).execute(job);
    }

    private static class insertAsyncTask extends AsyncTask<Job, Void, Void> {

        private JobDao mAsyncTaskDao;

        insertAsyncTask(JobDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Job... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateJobAsyncTask extends AsyncTask<Job, Void, Void> {
        private JobDao mAsyncTaskDao;

        updateJobAsyncTask(JobDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Job... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class findCurrentAsyncTask extends AsyncTask<Void, Void, Job> {
        private JobDao mAsyncTaskDao;

        findCurrentAsyncTask(JobDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Job doInBackground(Void... voids) {
            Job current = mAsyncTaskDao.findCurrent();
            return current;
        }

        @Override
        protected void onPostExecute(Job job) {
            super.onPostExecute(job);
        }
    }


    private static class getTwoSelectedAsyncTask extends AsyncTask<Void, Void, List<Job>> {
        private JobDao mAsyncTaskDao;

        getTwoSelectedAsyncTask(JobDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected List<Job> doInBackground(Void... voids) {
            List<Job> jobs = mAsyncTaskDao.getTwoSelected();
            return jobs;
        }

        @Override
        protected void onPostExecute(List<Job> jobs) {
            super.onPostExecute(jobs);
        }
    }

    private static class getListAsyncTask extends AsyncTask<Void, Void, List<Job>> {
        private JobDao mAsyncTaskDao;

        getListAsyncTask(JobDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Job> doInBackground(Void... voids) {
            List<Job> jobs = mAsyncTaskDao.getList();
            return jobs;
        }

        @Override
        protected void onPostExecute(List<Job> jobs) {
            super.onPostExecute(jobs);
        }
    }
}
