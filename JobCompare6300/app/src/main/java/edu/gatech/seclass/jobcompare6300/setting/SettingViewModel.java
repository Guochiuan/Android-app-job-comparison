package edu.gatech.seclass.jobcompare6300.setting;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import java.util.concurrent.ExecutionException;

public class SettingViewModel extends AndroidViewModel {

    private final SettingRepository settingRepository;

    public SettingViewModel(Application application) {
        super(application);
        settingRepository = new SettingRepository(application);
    }

    public Setting getSetting() throws ExecutionException, InterruptedException {
        return settingRepository.getSetting();
    }

    public void insert(Setting setting) {
        settingRepository.insert(setting);
    }
    public void update(Setting setting) {
        settingRepository.update(setting);
    }

}
