package edu.gatech.seclass.jobcompare6300.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.job.JobViewModel;
import edu.gatech.seclass.jobcompare6300.utility.Calculator;


public class OffersCompareFragment extends Fragment {

    Button newCompare, returnToHome;

    private JobViewModel mJobViewModel;
    private List<Job> selectedJobs;
    private TextView title1;
    private TextView title2;
    private TextView company1;
    private TextView company2;
    private TextView location1;
    private TextView location2;
    private TextView salary1;
    private TextView salary2;
    private TextView bonus1;
    private TextView bonus2;
    private TextView benefits1;
    private TextView benefits2;
    private TextView stipend1;
    private TextView stipend2;
    private TextView fund1;
    private TextView fund2;

    public OffersCompareFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJobViewModel = new ViewModelProvider(this).get(JobViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_offers_compare, container, false);

        title1 = view.findViewById(R.id.title1);
        title2 = view.findViewById(R.id.title2);
        company1 = view.findViewById(R.id.company1);
        company2 = view.findViewById(R.id.company2);
        location1 = view.findViewById(R.id.location1);
        location2 = view.findViewById(R.id.location2);
        salary1 = view.findViewById(R.id.salary1);
        salary2 = view.findViewById(R.id.salary2);
        bonus1 = view.findViewById(R.id.bonus1);
        bonus2 = view.findViewById(R.id.bonus2);
        benefits1 = view.findViewById(R.id.benefits1);
        benefits2 = view.findViewById(R.id.benefits2);
        stipend1 = view.findViewById(R.id.stipend1);
        stipend2 = view.findViewById(R.id.stipend2);
        fund1 = view.findViewById(R.id.fund1);
        fund2 = view.findViewById(R.id.fund2);


        selectedJobs = null;
        try {
            selectedJobs = mJobViewModel.getTwoSelected();
        } catch (ExecutionException e) {

        } catch (InterruptedException e) {

        }

        title1.setText(selectedJobs.get(0).getTitle());
        title2.setText(selectedJobs.get(1).getTitle());
        company1.setText(selectedJobs.get(0).getCompany());
        company2.setText(selectedJobs.get(1).getCompany());
        location1.setText(selectedJobs.get(0).getCity() + ", " + selectedJobs.get(0).getState());
        location2.setText(selectedJobs.get(1).getCity() + ", " + selectedJobs.get(1).getState());
        salary1.setText(Calculator.AdjustSalaryByCOL(selectedJobs.get(0)).toString());
        salary2.setText(Calculator.AdjustSalaryByCOL(selectedJobs.get(1)).toString());
        bonus1.setText(Calculator.AdjustBonusByCOL(selectedJobs.get(0)).toString());
        bonus2.setText(Calculator.AdjustBonusByCOL(selectedJobs.get(1)).toString());
        benefits1.setText(selectedJobs.get(0).getBenefits() + "%");
        benefits2.setText(selectedJobs.get(1).getBenefits() + "%");
        stipend1.setText(selectedJobs.get(0).getStipend().toString());
        stipend2.setText(selectedJobs.get(1).getStipend().toString());
        fund1.setText(selectedJobs.get(0).getFund().toString());
        fund2.setText(selectedJobs.get(1).getFund().toString());

        newCompare = (Button) view.findViewById(R.id.new_compare_button);
        returnToHome = (Button) view.findViewById(R.id.return_to_home_button);

        returnToHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                {
                    resetSelection();
                    Navigation.findNavController(view).navigate(R.id.action_offersCompareFragment_to_menuFragment);
                }
            }
        });

        newCompare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                {
                    resetSelection();
                    Navigation.findNavController(view).navigate(R.id.action_offersCompareFragment_to_offersListFragment);
                }
            }
        });
        return view;
    }

    public void resetSelection() {
        selectedJobs = null;
        try {
            selectedJobs = mJobViewModel.getTwoSelected();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < selectedJobs.size(); i++) {
            selectedJobs.get(i).setSelected(false);
            mJobViewModel.update(selectedJobs.get(i));
        }
    }
}