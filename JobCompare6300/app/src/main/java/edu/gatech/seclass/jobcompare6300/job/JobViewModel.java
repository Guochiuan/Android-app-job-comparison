package edu.gatech.seclass.jobcompare6300.job;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import java.util.List;
import java.util.concurrent.ExecutionException;
public class JobViewModel extends AndroidViewModel {


    private JobRepository jobRepository;


    public JobViewModel(Application application) throws ExecutionException, InterruptedException {
        super(application);
        jobRepository = new JobRepository(application);
    }

    public Job findCurrent() throws ExecutionException, InterruptedException {
        return jobRepository.findCurrent();
    }

    public List<Job> getList() throws ExecutionException, InterruptedException {
        return jobRepository.getList();
    }

    public List<Job> getTwoSelected() throws ExecutionException, InterruptedException {
        return jobRepository.getTwoSelected();
    }

    public void insert(Job job) {
        jobRepository.insert(job);
    }

    public void update(Job job) {
        jobRepository.update(job);
    }

}
