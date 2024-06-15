package edu.gatech.seclass.jobcompare6300.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import java.util.List;
import java.util.concurrent.ExecutionException;
import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.job.JobViewModel;
import edu.gatech.seclass.jobcompare6300.setting.Setting;
import edu.gatech.seclass.jobcompare6300.setting.SettingViewModel;

public class MenuFragment extends Fragment {

    private SettingViewModel mSettingViewModel;
    private JobViewModel mJobViewModel;
    private Setting mSetting;
    private boolean comparable = false;
    Button currentJob, offerInput, setting, offersList;

    public MenuFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        mJobViewModel = new ViewModelProvider(this).get(JobViewModel.class);

        try {
            mSetting = mSettingViewModel.getSetting();
        } catch (ExecutionException e) {
        } catch (InterruptedException e) {
        }
        if (mSetting == null) {
            mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
            Setting setting = new Setting(1, 1, 1, 1, 1);
            mSettingViewModel.insert(setting);
        }
        List<Job> jobs = null;
        try {
            jobs = mJobViewModel.getList();
        } catch (ExecutionException e) {

        } catch (InterruptedException e) {

        }
        if (jobs != null)
            if (jobs.size() > 1)
                comparable = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        currentJob = (Button) view.findViewById(R.id.currentJobButton);
        offerInput = (Button) view.findViewById(R.id.jobOfferButton);
        setting = (Button) view.findViewById(R.id.compareSettingsButton);
        offersList = (Button) view.findViewById(R.id.compareOffersButton);

        if(!comparable)
            offersList.setEnabled(false);

        currentJob.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_currentJobFragment));

        offerInput.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_offersInputFragment));

        setting.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_settingFragment));

        offersList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (comparable) {
                    Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_offersListFragment);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Enter at least 2 offers to compare or 1 offer with current job", Toast.LENGTH_LONG).show();

                }

            }
        });


        return view;
    }


}