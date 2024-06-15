package edu.gatech.seclass.jobcompare6300.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;
import edu.gatech.seclass.jobcompare6300.R;
import edu.gatech.seclass.jobcompare6300.setting.Setting;
import edu.gatech.seclass.jobcompare6300.setting.SettingViewModel;

public class SettingFragment extends Fragment {

    Button saveButton, returnButton;

    private EditText salaryEditText;
    private EditText bonusEditText;
    private EditText benefitsEditText;
    private EditText stipendEditText;
    private EditText fundEditText;

    private Setting setting;
    private SettingViewModel mSettingViewModel;

    public SettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        saveButton = (Button) view.findViewById(R.id.save_button);
        returnButton = (Button) view.findViewById(R.id.return_button);

        salaryEditText = view.findViewById(R.id.salaryEditText);
        bonusEditText = view.findViewById(R.id.bonusEditText);
        benefitsEditText = view.findViewById(R.id.benefitsEditText);
        stipendEditText = view.findViewById(R.id.stipendEditText);
        fundEditText = view.findViewById(R.id.fundEditText);

        setting = retrieveOriginalSettingValue();


        returnButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_settingFragment_to_menuFragment));

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (false) {
                    //edit later
                } else {
                    int salary = Integer.parseInt(salaryEditText.getText().toString());
                    int bonus = Integer.parseInt(bonusEditText.getText().toString());
                    int benefits = Integer.parseInt(benefitsEditText.getText().toString());
                    int stipend = Integer.parseInt(stipendEditText.getText().toString());
                    int fund = Integer.parseInt(fundEditText.getText().toString());

                    if(salary == 0 && bonus == 0 && benefits ==0 && stipend ==0 && fund == 0){
                        Toast.makeText(getActivity().getApplicationContext(), "Cannot set every weight to be 0", Toast.LENGTH_LONG).show();

                    }
                    else{
                        if (setting == null) {
                            setting = new Setting(salary, bonus, benefits, stipend, fund);
                            mSettingViewModel.insert(setting);
                        } else {
                            setting.setSalaryWeight(salary);
                            setting.setBonusWeight(bonus);
                            setting.setBenefitsWeight(benefits);
                            setting.setStipendWeight(stipend);
                            setting.setFundWeight(fund);

                            mSettingViewModel.update(setting);
                        }
                        Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_menuFragment);
                    }

                }
            }
        });

        return view;
    }

    private Setting retrieveOriginalSettingValue(){
        Setting originalSetting = null;
        try {
            originalSetting = mSettingViewModel.getSetting();
        } catch (ExecutionException e) {
        } catch (InterruptedException e) {
        }

        if (originalSetting != null) {
        salaryEditText.setText(Integer.toString(originalSetting.getSalaryWeight()));
        bonusEditText.setText(Integer.toString(originalSetting.getBonusWeight()));
        benefitsEditText.setText(Integer.toString(originalSetting.getBenefitsWeight()));
        stipendEditText.setText(Integer.toString(originalSetting.getStipendWeight()));
        fundEditText.setText(Integer.toString(originalSetting.getFundWeight()));
        }
        return originalSetting;
    }
}