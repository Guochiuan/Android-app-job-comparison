package edu.gatech.seclass.jobcompare6300.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.job.JobViewModel;
import edu.gatech.seclass.jobcompare6300.setting.Setting;
import edu.gatech.seclass.jobcompare6300.setting.SettingViewModel;
import edu.gatech.seclass.jobcompare6300.utility.Calculator;

public class OffersListFragment extends Fragment {

    private JobViewModel mJobViewModel;
    private SettingViewModel mSettingViewModel;
    private Setting mSetting;
    private List<Job> mJobs;

    Button offersCompare, home;

    public OffersListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers_list, container, false);
        mJobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
        mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        try {
            mSetting = mSettingViewModel.getSetting();
        } catch (ExecutionException e) {
        } catch (InterruptedException e) {
        }
        try {
            mJobs = mJobViewModel.getList();
        } catch (ExecutionException e) {
        } catch (InterruptedException e) {
        }
        for (int i = 0; i < mJobs.size(); i++) {
            BigDecimal score = Calculator.CalculateScore(mJobs.get(i), mSetting);
            mJobs.get(i).setScore(score);
            mJobs.get(i).setSelected(false);
            mJobViewModel.update(mJobs.get(i));
        }

        ArrayList<Job> sorted_jobs = new ArrayList<>();
        for (int i = 0; i < mJobs.size(); i++) {
            sorted_jobs.add(mJobs.get(i));
        }
        Collections.sort(sorted_jobs, Collections.reverseOrder());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final JobListAdapter adapter = new JobListAdapter(getActivity().getApplicationContext(), sorted_jobs);

        offersCompare = (Button) view.findViewById(R.id.offers_compare_button);
        home = (Button) view.findViewById(R.id.home_button);

        offersCompare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int totalSelected = 0;
                List<Job> updatedJobs;
                updatedJobs = adapter.getJobs();

                for (int i = 0; i < updatedJobs.size(); i++) {
                    if (updatedJobs.get(i).isSelected()) {
                        totalSelected++;
                    }
                }

                if (totalSelected != 2) {
                    for (int i = 0; i < updatedJobs.size(); i++) {
                        mJobViewModel.update(updatedJobs.get(i));
//                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity().getApplicationContext(), "Please select exactly TWO offers (including the current job if present) to compare.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    updatedJobs = adapter.getJobs();
                    for (int i = 0; i < updatedJobs.size(); i++) {
                        mJobViewModel.update(updatedJobs.get(i));
                    }
                    Navigation.findNavController(view).navigate(R.id.action_offersListFragment_to_offersCompareFragment);
                }
            }
        });

        home.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                        R.id.action_offersListFragment_to_menuFragment));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        return view;
    }
}