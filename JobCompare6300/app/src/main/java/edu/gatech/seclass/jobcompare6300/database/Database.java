package edu.gatech.seclass.jobcompare6300.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.job.JobDao;
import edu.gatech.seclass.jobcompare6300.setting.Setting;
import edu.gatech.seclass.jobcompare6300.setting.SettingDao;
import edu.gatech.seclass.jobcompare6300.utility.Converter;


@androidx.room.Database(entities = {Job.class, Setting.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public abstract JobDao JobDao();
    public abstract SettingDao SettingDao();

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, Database.class, "db_001").build();
        }
        return instance;
    }


}




