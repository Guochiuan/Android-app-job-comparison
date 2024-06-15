package edu.gatech.seclass.jobcompare6300.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.job.JobViewModel;


public class OffersInputFragment extends Fragment {

    Button save, cancel, another, saveAndCompare;
    private EditText titleEditText;
    private EditText companyEditText;
    private EditText cityEditText;
    private EditText stateEditText;
    private EditText colEditText;
    private EditText salaryEditText;
    private EditText bonusEditText;
    private EditText stipendEditText;
    private EditText benefitsEditText;
    private EditText fundEditText;
    private JobViewModel mJobViewModel;
    private Job current;
    private Job job;

    public OffersInputFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_offers_input, container, false);
        save = (Button) view.findViewById(R.id.save_button);
        cancel = (Button) view.findViewById(R.id.cancel_button);
        another = (Button) view.findViewById(R.id.another_button);
        saveAndCompare = (Button) view.findViewById(R.id.save_and_compare_button);

        titleEditText = view.findViewById(R.id.titleEditText);
        companyEditText = view.findViewById(R.id.companyEditText);
        cityEditText = view.findViewById(R.id.cityEditText);
        stateEditText = view.findViewById(R.id.stateEditText);
        colEditText = view.findViewById(R.id.colEditText);
        salaryEditText = view.findViewById(R.id.salaryEditText);
        bonusEditText = view.findViewById(R.id.bonusEditText);
        stipendEditText = view.findViewById(R.id.stipendEditText);
        benefitsEditText = view.findViewById(R.id.benefitsEditText);
        fundEditText = view.findViewById(R.id.funEditText);

        current = null;
        try {
            current = mJobViewModel.findCurrent();
        } catch (ExecutionException e) {

        } catch (InterruptedException e) {

        }

        if(current == null){
            saveAndCompare.setEnabled(false);
        }


        EditText[] editTextArray = new EditText[10];

        editTextArray[0] = titleEditText;
        editTextArray[1]= companyEditText;
        editTextArray[2]=cityEditText;
        editTextArray[3]=stateEditText;
        editTextArray[4]=colEditText;
        editTextArray[5]= salaryEditText;
        editTextArray[6]=bonusEditText;
        editTextArray[7]=benefitsEditText;
        editTextArray[8]=stipendEditText;
        editTextArray[9]=fundEditText;


        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                boolean error = handleNullInput(editTextArray);
                if(!error){

                    Job job = createJobWithInput();

                    if (!job.validateBenefits()) {
                        benefitsEditText.setError("0 to 100 inclusively");
                    }
                    if(!job.validateFund()){
                        fundEditText.setError("$0 to $18000 inclusively");
                    }
                    if(!job.validateCOL()){
                        colEditText.setError("COL should be greater than 0");
                    }

                    if(job.validateBenefits() && job.validateFund() && job.validateCOL()){
                        mJobViewModel.insert(job);
                        Navigation.findNavController(view).navigate(R.id.action_offersInputFragment_to_menuFragment);
                    }

                }

            }
        });

        cancel.setOnClickListener(

                Navigation.createNavigateOnClickListener(R.id.action_offersInputFragment_to_menuFragment));

        another.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                    boolean error = handleNullInput(editTextArray);
                    if(!error){

                    Job job = createJobWithInput();

                    if(!job.validateCOL()){
                        colEditText.setError("COL should be greater than 0");
                    }

                    if (!job.validateBenefits()) {
                        benefitsEditText.setError("0 to 100 inclusively");
                    }
                    if(!job.validateFund()){
                        fundEditText.setError("$0 to $18000 inclusively");
                    }
                    if(job.validateBenefits() && job.validateFund() && job.validateCOL()) {
                        mJobViewModel.insert(job);
                        resetInputValue();
                    }
                }

            }
        });


        saveAndCompare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                boolean error = handleNullInput(editTextArray);
                if(!error){

                Job job = createJobWithInput();

                if(!job.validateCOL()){
                    colEditText.setError("COL should be greater than 0");
                }

                if (!job.validateBenefits()) {
                    benefitsEditText.setError("0 to 100 inclusively");
                }
                if(!job.validateFund()){
                    fundEditText.setError("$0 to $18000 inclusively");
                }
                if(job.validateBenefits() && job.validateFund() && job.validateCOL()) {

                    if (current == null) {
                        mJobViewModel.insert(job);
                        Toast.makeText(getActivity().getApplicationContext(), "Saved but you haven't entered the current job.", Toast.LENGTH_LONG).show();
                        resetInputValue();
                    } else {
                        job.setSelected(true);
                        current.setSelected(true);
                        mJobViewModel.insert(job);
                        mJobViewModel.update(current);

                        Navigation.findNavController(view).navigate(R.id.action_offersInputFragment_to_offersCompareFragment);
                    }
                }}}
        });
        return view;
    }

    private Job createJobWithInput() {
        String title = titleEditText.getText().toString();
        String company = companyEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String state = stateEditText.getText().toString();
        int costOfLiving = Integer.parseInt(colEditText.getText().toString());
        BigDecimal salary = new BigDecimal(salaryEditText.getText().toString());
        BigDecimal bonus = new BigDecimal(bonusEditText.getText().toString());
        int benefits = Integer.parseInt(benefitsEditText.getText().toString());
        BigDecimal stipend = new BigDecimal(stipendEditText.getText().toString());
        BigDecimal fund = new BigDecimal(fundEditText.getText().toString());

        boolean isCurrentJob = false;
        BigDecimal score = new BigDecimal("0");

        job = new Job(title, company, city, state, costOfLiving, salary, bonus, benefits, stipend, fund, isCurrentJob);
        job.setScore(score);
        return job;
    }

    private void resetInputValue(){
        titleEditText.setText("");
        companyEditText.setText("");
        cityEditText.setText("");
        stateEditText.setText("");
        colEditText.setText("");
        salaryEditText.setText("");
        bonusEditText.setText("");
        benefitsEditText.setText("");
        stipendEditText.setText("");
        fundEditText.setText("");

    }

    public boolean handleNullInput(EditText[] array) {
        boolean error = false;
        for (int i = 0; i < array.length; i++) {
            if (TextUtils.isEmpty(array[i].getText().toString())) {
                array[i].setError("Your input cannot be blank.");
                error = true;
            }
        }
        return error;
    }
}