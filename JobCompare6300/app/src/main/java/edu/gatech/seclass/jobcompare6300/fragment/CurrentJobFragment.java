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
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.job.JobViewModel;


public class CurrentJobFragment extends Fragment {

    Button save, cancel;
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


    public CurrentJobFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_current_job, container, false);
        save = (Button) view.findViewById(R.id.save_button);
        cancel = (Button) view.findViewById(R.id.cancel_button);
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

        Job current =  retrieveOriginalCurrentValue();

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



        cancel.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_currentJobFragment_to_menuFragment));

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                if (handleNullInput(editTextArray)) {
                } else {
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

                    boolean isCurrentJob = true;
                    Job current = null;
                    try {
                        current = mJobViewModel.findCurrent();
                    } catch (ExecutionException e) {
                    } catch (InterruptedException e) {
                    }

                    if (current == null) {
                        current = new Job(title, company, city, state, costOfLiving, salary, bonus,
                                benefits, stipend, fund, isCurrentJob);
                        current.setScore(BigDecimal.valueOf(0));


                        if (!current.validateBenefits()) {
                            benefitsEditText.setError("0 to 100 inclusively");
                        }
                        if(!current.validateFund()){
                            fundEditText.setError("$0 to $18000 inclusively");
                        }
                        if(!current.validateCOL()){
                            colEditText.setError("COL should be greater than 0");
                        }
                        if(current.validateBenefits() &&current.validateFund() && current.validateCOL()) {
                            mJobViewModel.insert(current);
                            Navigation.findNavController(view).navigate(R.id.action_currentJobFragment_to_menuFragment);

                        }
                    } else {
                        current.setTitle(title);
                        current.setCompany(company);
                        current.setCity(city);
                        current.setState(state);
                        current.setCostOfLiving(costOfLiving);
                        current.setSalary(salary);
                        current.setBonus(bonus);
                        current.setBenefits(benefits);
                        current.setStipend(stipend);
                        current.setFund(fund);
                        current.setCurrentJob(isCurrentJob);
                        current.setScore(BigDecimal.valueOf(0));
                        current.setSelected(false);

                        if (!current.validateBenefits()) {
                            benefitsEditText.setError("0 to 100 inclusively");
                        }
                        if(!current.validateCOL()){
                            colEditText.setError("COL should be greater than 0");
                        }
                        if(!current.validateFund()){
                            fundEditText.setError("$0 to $18000 inclusively");
                        }
                        if(current.validateBenefits() && current.validateFund() && current.validateCOL()) {
                            mJobViewModel.update(current);
                            Navigation.findNavController(view).navigate(R.id.action_currentJobFragment_to_menuFragment);

                        }
                    }
                }
            }
        });
        return view;
    }

    private Job retrieveOriginalCurrentValue() {
        Job originalCurrent = null;
        try {
            originalCurrent = mJobViewModel.findCurrent();
        } catch (Exception e) {
            System.out.println("no current job yet");
        }
        if (originalCurrent != null) {
            titleEditText.setText(originalCurrent.getTitle());
            companyEditText.setText(originalCurrent.getCompany());
            cityEditText.setText(originalCurrent.getCity());
            stateEditText.setText(originalCurrent.getState());
            colEditText.setText(Integer.toString(originalCurrent.getCostOfLiving()));
            salaryEditText.setText(originalCurrent.getSalary().toString());
            bonusEditText.setText(originalCurrent.getBonus().toString());
            stipendEditText.setText(originalCurrent.getStipend().toString());
            benefitsEditText.setText(Integer.toString(originalCurrent.getBenefits()));
            fundEditText.setText(originalCurrent.getFund().toString());
        }
        return originalCurrent;
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
}
