package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.gatech.seclass.jobcompare6300.job.JobViewModel;
import edu.gatech.seclass.jobcompare6300.setting.SettingViewModel;

public class MainActivity extends AppCompatActivity {

    private SettingViewModel mSettingViewModel;
    private JobViewModel mJobViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}