package edu.gatech.seclass.jobcompare6300.setting;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SettingDao {

    @Query("SELECT * From setting_table LIMIT 1")
    Setting getSetting();

    @Insert
    public void insert(Setting setting);

    @Update
    public void update(Setting setting);
}
