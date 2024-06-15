package edu.gatech.seclass.jobcompare6300.job;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface JobDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Job job);

    @Update
    void update(Job... job);

    @Delete
    void deleteJob(Job job);

    @Query("SELECT * FROM job_table ORDER BY score DESC")
    LiveData<List<Job>> getAllJobs();

    @Query("SELECT * FROM job_table WHERE is_current_job = 1 LIMIT 1")
    Job findCurrent();

    @Query("SELECT * FROM job_table WHERE is_selected = 1 LIMIT 2")
    List<Job> getTwoSelected();

    @Query("SELECT * FROM job_table ORDER BY score DESC")
    List<Job> getList();

    @Query("SELECT * FROM job_table WHERE company = 'TestCompany' LIMIT 1")
    Job getTestID();

    @Query("SELECT * FROM job_table WHERE company = 'TestCompany2' LIMIT 1")
    Job getTestID2();


}
