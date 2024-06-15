package edu.gatech.seclass.jobcompare6300;


//import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import java.math.BigDecimal;

import edu.gatech.seclass.jobcompare6300.database.Database;
import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.setting.Setting;
import edu.gatech.seclass.jobcompare6300.utility.Calculator;


@RunWith(AndroidJUnit4.class)
public class AppTests {

    public static class Jobsclass {
        Job compareJob;
        Job testJob;
    }

    public static class SettingClass {
        Setting originalSetting;
        Setting compareSetting;
        Setting testSetting;
    }

    public Jobsclass setUIJob(String action, boolean current, int version){

        String test_title = "TestTitle";
        String test_company;
        if (version == 0) {
            test_company = "TestCompany";
        }
        else {
            test_company = "TestCompany2";
        }
        String test_city = "TestCity";
        String test_state = "TestState";
        int test_col = 50;
        int test_benefits = 20;
        BigDecimal test_salary = BigDecimal.valueOf(1000);
        BigDecimal test_bonus = BigDecimal.valueOf(100);
        BigDecimal test_stipend = BigDecimal.valueOf(100);
        BigDecimal test_fund = BigDecimal.valueOf(100);
        boolean test_current_job = current;

        Job compareJob = new Job(test_title, test_company, test_city,
                test_state, test_col, test_salary,
                test_bonus, test_benefits, test_stipend,
                test_fund, test_current_job);

        if (current) {
            onView(withId(R.id.currentJobButton)).perform(click());
        }
        else{
            onView(withId(R.id.jobOfferButton)).perform(click());

        }
        onView(withId(R.id.titleEditText)).perform(clearText(), replaceText(test_title));
        onView(withId(R.id.companyEditText)).perform(clearText(), replaceText(test_company));
        onView(withId(R.id.cityEditText)).perform(clearText(), replaceText(test_city));
        onView(withId(R.id.stateEditText)).perform(clearText(), replaceText(test_state));
        onView(withId(R.id.colEditText)).perform(clearText(), replaceText(String.valueOf(test_col)));
        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText(String.valueOf(test_salary)));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText(String.valueOf(test_bonus)));
        onView(withId(R.id.benefitsEditText)).perform(clearText(), replaceText(String.valueOf(test_benefits)));
        onView(withId(R.id.stipendEditText)).perform(clearText(), replaceText(String.valueOf(test_stipend)));
        onView(withId(R.id.funEditText)).perform(clearText(), replaceText(String.valueOf(test_fund)));

        if (action == "Save") {
            onView(withId(R.id.save_button)).perform(click());
        }
        else if (action == "Cancel") {
            onView(withId(R.id.cancel_button)).perform(click());
        }

        Job testJob;

        if (version == 0) {
            testJob = Database.getInstance(getApplicationContext()).JobDao().getTestID();
        }
        else {
            testJob = Database.getInstance(getApplicationContext()).JobDao().getTestID2();
        }
        //compareJob.setId(testJob.getId());
        //compareJob.setScore(testJob.getScore());
        Jobsclass returnJobs = new Jobsclass();
        returnJobs.compareJob = compareJob;
        returnJobs.testJob = testJob;

        return returnJobs;
    }

    int test_col = 50;
    int test_benefits = 20;
    BigDecimal test_salary = BigDecimal.valueOf(1000);
    BigDecimal test_bonus = BigDecimal.valueOf(100);
    BigDecimal test_stipend = BigDecimal.valueOf(100);
    BigDecimal test_fund = BigDecimal.valueOf(100);
    boolean test_current_job = true;

    public double alternateCalcScore(BigDecimal test_salary, BigDecimal test_bonus, BigDecimal test_stipend,
        BigDecimal test_fund, int test_col, String salary_weight, String bonus_weight, String benefits_weight,
        String stipend_weight, String fund_weight)
    {
        double expectedScore_double = (test_salary.doubleValue() *(100/ (double) test_col) * ((Double.parseDouble(salary_weight)) + Double.parseDouble(benefits_weight)* ((double)test_benefits/100))) +
                (test_bonus.doubleValue() * Double.parseDouble(bonus_weight))*(100/ (double) test_col) +
                test_stipend.doubleValue()* Double.parseDouble(stipend_weight) +
                test_fund.doubleValue() * Double.parseDouble(fund_weight);

        expectedScore_double = expectedScore_double/(Double.parseDouble(salary_weight) +
                Double.parseDouble(bonus_weight) + Double.parseDouble(benefits_weight)+
                Double.parseDouble(stipend_weight)+ Double.parseDouble(fund_weight));

        return expectedScore_double;
    }


    public SettingClass SettingsSave(String salary_weight, String bonus_weight, String benefits_weight, String stipend_weight, String fund_weight) {


        Setting originalSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        Setting compareSetting = new Setting(Integer.parseInt(salary_weight),
                Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight),
                Integer.parseInt(stipend_weight),
                Integer.parseInt(fund_weight));

        onView(withId(R.id.compareSettingsButton)).perform(click());

        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText(salary_weight));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText(bonus_weight));
        onView(withId(R.id.benefitsEditText)).perform(clearText(), replaceText(benefits_weight));
        onView(withId(R.id.stipendEditText)).perform(clearText(), replaceText(stipend_weight));
        onView(withId(R.id.fundEditText)).perform(clearText(), replaceText(fund_weight));

        onView(withId(R.id.save_button)).perform(click());
        Setting testSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        SettingClass returnSettings = new SettingClass();
        returnSettings.originalSetting = originalSetting;
        returnSettings.compareSetting = compareSetting;
        returnSettings.testSetting = testSetting;

        return returnSettings;
    }


    @Rule
    public ActivityScenarioRule<MainActivity> testActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void clearTables(){
        // Not really a test - but a method to clear the initial state of the app
        Database.getInstance(getApplicationContext()).clearAllTables();
    }

    @Test
    public void checkCurrentJobSave() {

        // Test case 1. Check that job is saved to database

        Jobsclass UIJobs = new Jobsclass();

        UIJobs = setUIJob("Save", true, 0);
        Job compareJob = UIJobs.compareJob;
        Job testJob = UIJobs.testJob;

        Database.getInstance(getApplicationContext()).JobDao().deleteJob(testJob);

        assertEquals(testJob.getTitle(), compareJob.getTitle());
        assertEquals(testJob.getCompany(), compareJob.getCompany());
        assertEquals(testJob.getCity(), compareJob.getCity());
        assertEquals(testJob.getState(), compareJob.getState());
        assertEquals(testJob.getCostOfLiving(), compareJob.getCostOfLiving());
        assertEquals(testJob.getBenefits(), compareJob.getBenefits());
        assertEquals(testJob.getSalary(), compareJob.getSalary());
        assertEquals(testJob.getBonus(), compareJob.getBonus());
        assertEquals(testJob.getStipend(), compareJob.getStipend());
        assertEquals(testJob.getFund(), compareJob.getFund());
        assertEquals(testJob.isCurrentJob(), compareJob.isCurrentJob());
    }


    @Test
    public void checkCurrentJobCancel() {
        // Test case 2: Check that cancel does not save to database

        //JobDatabase.getInstance(getApplicationContext()).clearAllTables();
        Jobsclass UIJobs = new Jobsclass();

        UIJobs = setUIJob("Cancel", true, 0);
        Job compareJob = UIJobs.compareJob;
        Job testJob = UIJobs.testJob;

        assertNull(testJob);
    }

    @Test
    public void checkCurrentJobSavewithoutinputs() {

        // Test case 3. Check that current job save with empty fields gives validation error
        // and is not saved into database

        onView(withId(R.id.currentJobButton)).perform(click());
        try {onView(withId(R.id.save_button)).perform(click());}
        catch (Exception e) {
            fail();
        }

    }

    @Test
    public void checkNewJobSave() {
        // Test case 4: Check that new job saves to database

        //JobDatabase.getInstance(getApplicationContext()).clearAllTables();
        Jobsclass UIJobs = new Jobsclass();

        UIJobs = setUIJob("Save", false, 0);
        Job compareJob = UIJobs.compareJob;
        Job testJob = UIJobs.testJob;

        Database.getInstance(getApplicationContext()).JobDao().deleteJob(testJob);

        assertEquals(testJob.getTitle(), compareJob.getTitle());
        assertEquals(testJob.getCompany(), compareJob.getCompany());
        assertEquals(testJob.getCity(), compareJob.getCity());
        assertEquals(testJob.getState(), compareJob.getState());
        assertEquals(testJob.getCostOfLiving(), compareJob.getCostOfLiving());
        assertEquals(testJob.getBenefits(), compareJob.getBenefits());
        assertEquals(testJob.getSalary(), compareJob.getSalary());
        assertEquals(testJob.getBonus(), compareJob.getBonus());
        assertEquals(testJob.getStipend(), compareJob.getStipend());
        assertEquals(testJob.getFund(), compareJob.getFund());
        assertEquals(testJob.isCurrentJob(), compareJob.isCurrentJob());
    }

    @Test
    public void checkNewJobCancel() {
        // Test case 5: Check that cancel for new Job does not save to database

        //JobDatabase.getInstance(getApplicationContext()).clearAllTables();
        Jobsclass UIJobs = new Jobsclass();

        UIJobs = setUIJob("Cancel", false, 0 );
        Job compareJob = UIJobs.compareJob;
        Job testJob = UIJobs.testJob;

        assertNull(testJob);
    }

    @Test
    public void NewJobSavewithoutinputs() {

        // Test case 6. Check that new job save with empty fields gives validation error
        // and is not saved into database

        onView(withId(R.id.jobOfferButton)).perform(click());
        try {onView(withId(R.id.save_button)).perform(click());}
        catch (Exception e) {
            fail();
        }

    }

    @Test
    public void NewJobSaveEnterAnotherwithoutinputs() {

        // Test case 7. Check that current job save with empty fields gives validation error when
        // "Enter Another" button is clicked.
        // and is not saved into database

        onView(withId(R.id.jobOfferButton)).perform(click());
        try {onView(withId(R.id.another_button)).perform(click());}
        catch (Exception e) {
            fail();
        }

    }

    @Test
    public void NewJobSaveEnterSaveandComparewithoutinputs() {

        // Test case 8. Check that current job save with empty fields gives validation error when
        // "Save and Compare" button is clicked.
        // and is not saved into database

        onView(withId(R.id.jobOfferButton)).perform(click());
        try {onView(withId(R.id.save_and_compare_button)).perform(click());}
        catch (Exception e) {
            fail();
        }

    }

    @Test
    public void CheckCurrentJobSavedVisible() {

        // Test case 9. Check that the current job save is visible when clicked again

        Jobsclass UIJobs = new Jobsclass();

        // Save the details of current job.
        UIJobs = setUIJob("Save", true, 0);
        Job compareJob = UIJobs.compareJob;
        Job testJob = UIJobs.testJob;

        // Click current job again
        onView(withId(R.id.currentJobButton)).perform(click());

        Database.getInstance(getApplicationContext()).JobDao().deleteJob(testJob);

        onView(withId(R.id.titleEditText)).check(matches(withText(compareJob.getTitle())));
        onView(withId(R.id.companyEditText)).check(matches(withText(compareJob.getCompany())));
        onView(withId(R.id.cityEditText)).check(matches(withText(compareJob.getCity())));
        onView(withId(R.id.stateEditText)).check(matches(withText(compareJob.getState())));
        onView(withId(R.id.colEditText)).check(matches(withText(String.valueOf(compareJob.getCostOfLiving()))));
        onView(withId(R.id.salaryEditText)).check(matches(withText(compareJob.getSalary().toString())));
        onView(withId(R.id.bonusEditText)).check(matches(withText(compareJob.getBonus().toString())));
        onView(withId(R.id.benefitsEditText)).check(matches(withText(String.valueOf(compareJob.getBenefits()))));
        onView(withId(R.id.stipendEditText)).check(matches(withText(compareJob.getStipend().toString())));
        onView(withId(R.id.funEditText)).check(matches(withText(compareJob.getFund().toString())));


    }


    @Test
    public void CheckCurrentJobSavedEditable() {

        // Test case 10. Check that the current job saved is editable and saved correctly

        Jobsclass UIJobs = new Jobsclass();

        // Save the details of current job.
        UIJobs = setUIJob("Save", true, 0);
        Job compareJob = UIJobs.compareJob;
        Job testJob = UIJobs.testJob;

        UIJobs = setUIJob("Save", true, 1);
        compareJob = UIJobs.compareJob;
        testJob = UIJobs.testJob;

        Database.getInstance(getApplicationContext()).JobDao().deleteJob(testJob);

        assertEquals(testJob.getTitle(), compareJob.getTitle());
        assertEquals(testJob.getCompany(), compareJob.getCompany());
        assertEquals(testJob.getCity(), compareJob.getCity());
        assertEquals(testJob.getState(), compareJob.getState());
        assertEquals(testJob.getCostOfLiving(), compareJob.getCostOfLiving());
        assertEquals(testJob.getBenefits(), compareJob.getBenefits());
        assertEquals(testJob.getSalary(), compareJob.getSalary());
        assertEquals(testJob.getBonus(), compareJob.getBonus());
        assertEquals(testJob.getStipend(), compareJob.getStipend());
        assertEquals(testJob.getFund(), compareJob.getFund());
        assertEquals(testJob.isCurrentJob(), compareJob.isCurrentJob());
    }

    @Test
    public void CheckCompareSettingsSavewithoutInputs() {

        // Test Case 11. Test saving compare settings without any input.

        onView(withId(R.id.compareSettingsButton)).perform(click());
        try {onView(withId(R.id.save_button)).perform(click());}
        catch (Exception e) {
            fail();
        }

    }

    @Test
    public void CheckCompareSettingsDefaultEqual() {

        // Test Case 12. Check default compare settings are equal

        Setting originalSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        assertTrue(originalSetting.getSalaryWeight() > 0);
        assertEquals(originalSetting.getSalaryWeight(), originalSetting.getBonusWeight());
        assertEquals(originalSetting.getSalaryWeight(), originalSetting.getBenefitsWeight());
        assertEquals(originalSetting.getSalaryWeight(), originalSetting.getStipendWeight());
        assertEquals(originalSetting.getSalaryWeight(), originalSetting.getFundWeight());

    }

    @Test
    public void CheckCompareSettingsSave() {

        // Test Case 13. Save settings and check against database

        String salary_weight = "30";
        String bonus_weight = "10";
        String benefits_weight = "10";
        String stipend_weight = "10";
        String fund_weight = "50";

        Setting originalSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();


        Setting compareSetting = new Setting(Integer.parseInt(salary_weight),
                Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight),
                Integer.parseInt(stipend_weight),
                Integer.parseInt(fund_weight));

        onView(withId(R.id.compareSettingsButton)).perform(click());

        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText(salary_weight));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText(bonus_weight));
        onView(withId(R.id.benefitsEditText)).perform(clearText(), replaceText(benefits_weight));
        onView(withId(R.id.stipendEditText)).perform(clearText(), replaceText(stipend_weight));
        onView(withId(R.id.fundEditText)).perform(clearText(), replaceText(fund_weight));

        onView(withId(R.id.save_button)).perform(click());

        Setting testSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        assertEquals(testSetting.getSalaryWeight(), compareSetting.getSalaryWeight());
        assertEquals(testSetting.getBonusWeight(), compareSetting.getBonusWeight());
        assertEquals(testSetting.getBenefitsWeight(), compareSetting.getBenefitsWeight());
        assertEquals(testSetting.getStipendWeight(), compareSetting.getStipendWeight());
        assertEquals(testSetting.getFundWeight(), compareSetting.getFundWeight());

        // Replace with pre-test setting
        Database.getInstance(getApplicationContext()).SettingDao().update(originalSetting);

    }

    @Test
    public void CheckCompareSettingsNegativeSave() {

        // Test Case 14. Check that negative settings are not stored in database, and
        // maintains the settings

        String salary_weight = "-30";
        String bonus_weight = "-10";
        String benefits_weight = "-10";
        String stipend_weight = "10";
        String fund_weight = "-20";


        Setting originalSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        Setting validSetting = new Setting(20, 20, 20,
                20,
                20);
        validSetting.setId(originalSetting.getId());

        Database.getInstance(getApplicationContext()).SettingDao().update(validSetting);


        Setting compareSetting = new Setting(Integer.parseInt(salary_weight),
                Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight),
                Integer.parseInt(stipend_weight),
                Integer.parseInt(fund_weight));

        onView(withId(R.id.compareSettingsButton)).perform(click());

        onView(withId(R.id.salaryEditText)).perform(clearText(), typeText(salary_weight));
        onView(withId(R.id.bonusEditText)).perform(clearText(), typeText(bonus_weight));
        onView(withId(R.id.benefitsEditText)).perform(clearText(), typeText(benefits_weight));
        onView(withId(R.id.stipendEditText)).perform(clearText(), typeText(stipend_weight));
        onView(withId(R.id.fundEditText)).perform(clearText(), typeText(fund_weight));

        onView(withId(R.id.save_button)).perform(click());

        Setting testSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        // Replace with pre-test setting
        Database.getInstance(getApplicationContext()).SettingDao().update(originalSetting);

        assertTrue(testSetting.getSalaryWeight() > 0);
        assertTrue(testSetting.getBonusWeight() >= 0);
        assertTrue(testSetting.getBenefitsWeight() >= 0);
        assertTrue(testSetting.getStipendWeight() >= 0);
        assertTrue(testSetting.getFundWeight() >= 0);

        Database.getInstance(getApplicationContext()).SettingDao().update(originalSetting);

    }

    @Test
    public void CheckCompareSettingsZeroSave() {

        // Test Case 15. Check that all zero settings are not stored in database, and
        // maintains the settings

        String salary_weight = "0";
        String bonus_weight = "0";
        String benefits_weight = "0";
        String stipend_weight = "0";
        String fund_weight = "0";


        Setting originalSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        Setting validSetting = new Setting(20, 20, 20,
                20,
                20);
        validSetting.setId(originalSetting.getId());

        Database.getInstance(getApplicationContext()).SettingDao().update(validSetting);


        Setting compareSetting = new Setting(Integer.parseInt(salary_weight),
                Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight),
                Integer.parseInt(stipend_weight),
                Integer.parseInt(fund_weight));

        onView(withId(R.id.compareSettingsButton)).perform(click());

        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText(salary_weight));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText(bonus_weight));
        onView(withId(R.id.benefitsEditText)).perform(clearText(), replaceText(benefits_weight));
        onView(withId(R.id.stipendEditText)).perform(clearText(), replaceText(stipend_weight));
        onView(withId(R.id.fundEditText)).perform(clearText(), replaceText(fund_weight));

        onView(withId(R.id.save_button)).perform(click());

        Setting testSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();

        // Replace with pre-test setting
        Database.getInstance(getApplicationContext()).SettingDao().update(originalSetting);

        assertEquals(testSetting.getSalaryWeight(), validSetting.getSalaryWeight());
        assertEquals(testSetting.getBonusWeight(), validSetting.getBonusWeight());
        assertEquals(testSetting.getBenefitsWeight(), validSetting.getBenefitsWeight());
        assertEquals(testSetting.getStipendWeight(), validSetting.getStipendWeight());
        assertEquals(testSetting.getFundWeight(), validSetting.getFundWeight());

    }

    @Test
    public void CheckCompareCalculation1() {

        // Test Case 16. Check calcualation of adjusted bonus

        String salary_weight = "20";
        String bonus_weight = "20";
        String benefits_weight = "20";
        String stipend_weight = "20";
        String fund_weight = "20";


        String test_title = "TestTitle";
        String test_company = "TestCompany";
        String test_city = "TestCity";
        String test_state = "TestState";
        int test_col = 50;
        int test_benefits = 20;
        BigDecimal test_salary = BigDecimal.valueOf(1000);
        BigDecimal test_bonus = BigDecimal.valueOf(100);
        BigDecimal test_stipend = BigDecimal.valueOf(100);
        BigDecimal test_fund = BigDecimal.valueOf(100);
        boolean test_current_job = true;

        Job testJob = new Job(test_title, test_company, test_city,
                test_state, test_col, test_salary,
                test_bonus, test_benefits, test_stipend,
                test_fund, test_current_job);

        Setting validSetting = new Setting(20, 20, 20,
                20,
                20);

        BigDecimal adjustedBonus = Calculator.AdjustBonusByCOL(testJob);
        assertEquals(200, adjustedBonus.doubleValue(), 0.01);

    }

    @Test
    public void CheckCompareCalculation2() {

        // Test Case 17. Check that all zero settings are not stored in database, and
        // maintains the settings

        String salary_weight = "20";
        String bonus_weight = "20";
        String benefits_weight = "20";
        String stipend_weight = "20";
        String fund_weight = "20";


        String test_title = "TestTitle";
        String test_company = "TestCompany";
        String test_city = "TestCity";
        String test_state = "TestState";
        int test_col = 50;
        int test_benefits = 20;
        BigDecimal test_salary = BigDecimal.valueOf(1000);
        BigDecimal test_bonus = BigDecimal.valueOf(100);
        BigDecimal test_stipend = BigDecimal.valueOf(100);
        BigDecimal test_fund = BigDecimal.valueOf(100);
        boolean test_current_job = true;

        Job testJob = new Job(test_title, test_company, test_city,
                test_state, test_col, test_salary,
                test_bonus, test_benefits, test_stipend,
                test_fund, test_current_job);

        Setting validSetting = new Setting(20, 20, 20,
                20,
                20);

        BigDecimal adjustedSalary = Calculator.AdjustSalaryByCOL(testJob);
        assertEquals(2000, adjustedSalary.doubleValue(), 0.01);
    }

    @Test
    public void CheckCompareCalculation3() {

        // Test Case 18. Check that all zero settings are not stored in database, and
        // maintains the settings

        String test_title = "TestTitle";
        String test_company = "TestCompany";
        String test_city = "TestCity";
        String test_state = "TestState";
        int test_col = 50;
        int test_benefits = 20;
        BigDecimal test_salary = BigDecimal.valueOf(1000);
        BigDecimal test_bonus = BigDecimal.valueOf(100);
        BigDecimal test_stipend = BigDecimal.valueOf(100);
        BigDecimal test_fund = BigDecimal.valueOf(100);
        boolean test_current_job = true;

        Job testJob = new Job(test_title, test_company, test_city,
                test_state, test_col, test_salary,
                test_bonus, test_benefits, test_stipend,
                test_fund, test_current_job);


        String salary_weight = "20";
        String bonus_weight = "0";
        String benefits_weight = "0";
        String stipend_weight = "0";
        String fund_weight = "0";

        Setting validSetting = new Setting(Integer.parseInt(salary_weight), Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight), Integer.parseInt(stipend_weight), Integer.parseInt(fund_weight));

        BigDecimal adjustedScore = Calculator.CalculateScore(testJob, validSetting);
        double expectedScore_double = alternateCalcScore(test_salary, test_bonus, test_stipend,
                test_fund, test_col, salary_weight, bonus_weight, benefits_weight,
                stipend_weight, fund_weight);
        assertEquals("Salary weight not ok", expectedScore_double, adjustedScore.doubleValue(), 0.01);

        salary_weight = "0";
        bonus_weight = "20";
        benefits_weight = "0";
        stipend_weight = "0";
        fund_weight = "0";

        validSetting = new Setting(Integer.parseInt(salary_weight), Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight), Integer.parseInt(stipend_weight), Integer.parseInt(fund_weight));

        adjustedScore = Calculator.CalculateScore(testJob, validSetting);
        expectedScore_double = alternateCalcScore(test_salary, test_bonus, test_stipend,
                test_fund, test_col, salary_weight, bonus_weight, benefits_weight,
                stipend_weight, fund_weight);
        assertEquals("Bonus weight not ok", expectedScore_double, adjustedScore.doubleValue(), 0.01);

        salary_weight = "0";
        bonus_weight = "0";
        benefits_weight = "20";
        stipend_weight = "0";
        fund_weight = "0";

        validSetting = new Setting(Integer.parseInt(salary_weight), Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight), Integer.parseInt(stipend_weight), Integer.parseInt(fund_weight));

        adjustedScore = Calculator.CalculateScore(testJob, validSetting);
        expectedScore_double = alternateCalcScore(test_salary, test_bonus, test_stipend,
                test_fund, test_col, salary_weight, bonus_weight, benefits_weight,
                stipend_weight, fund_weight);
        assertEquals("Benefits weight not ok", expectedScore_double, adjustedScore.doubleValue(), 0.01);

        salary_weight = "0";
        bonus_weight = "0";
        benefits_weight = "0";
        stipend_weight = "20";
        fund_weight = "0";

        validSetting = new Setting(Integer.parseInt(salary_weight), Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight), Integer.parseInt(stipend_weight), Integer.parseInt(fund_weight));

        adjustedScore = Calculator.CalculateScore(testJob, validSetting);
        expectedScore_double = alternateCalcScore(test_salary, test_bonus, test_stipend,
                test_fund, test_col, salary_weight, bonus_weight, benefits_weight,
                stipend_weight, fund_weight);
        assertEquals("Stipend weight not ok", expectedScore_double, adjustedScore.doubleValue(), 0.01);


        salary_weight = "0";
        bonus_weight = "0";
        benefits_weight = "0";
        stipend_weight = "0";
        fund_weight = "20";

        validSetting = new Setting(Integer.parseInt(salary_weight), Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight), Integer.parseInt(stipend_weight), Integer.parseInt(fund_weight));

        adjustedScore = Calculator.CalculateScore(testJob, validSetting);
        expectedScore_double = alternateCalcScore(test_salary, test_bonus, test_stipend,
                test_fund, test_col, salary_weight, bonus_weight, benefits_weight,
                stipend_weight, fund_weight);
        assertEquals("Fund weight not ok", expectedScore_double, adjustedScore.doubleValue(), 0.01);

        salary_weight = "20";
        bonus_weight = "20";
        benefits_weight = "20";
        stipend_weight = "20";
        fund_weight = "20";

        validSetting = new Setting(Integer.parseInt(salary_weight), Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight), Integer.parseInt(stipend_weight), Integer.parseInt(fund_weight));

        adjustedScore = Calculator.CalculateScore(testJob, validSetting);
        expectedScore_double = alternateCalcScore(test_salary, test_bonus, test_stipend,
                test_fund, test_col, salary_weight, bonus_weight, benefits_weight,
                stipend_weight, fund_weight);
        assertEquals("Combined weight not ok", expectedScore_double, adjustedScore.doubleValue(), 0.01);

        salary_weight = "10";
        bonus_weight = "15";
        benefits_weight = "20";
        stipend_weight = "25";
        fund_weight = "30";

        validSetting = new Setting(Integer.parseInt(salary_weight), Integer.parseInt(bonus_weight),
                Integer.parseInt(benefits_weight), Integer.parseInt(stipend_weight), Integer.parseInt(fund_weight));

        adjustedScore = Calculator.CalculateScore(testJob, validSetting);
        expectedScore_double = alternateCalcScore(test_salary, test_bonus, test_stipend,
                test_fund, test_col, salary_weight, bonus_weight, benefits_weight,
                stipend_weight, fund_weight);
        assertEquals("Combined weight2 not ok", expectedScore_double, adjustedScore.doubleValue(), 0.01);

    }

    @Test
    public void CheckCompareGUI() {

        // Test case 19. Check that the current job saved is editable and saved correctly

        Jobsclass UIJobs = new Jobsclass();

        // Save the details of current job.
        UIJobs = setUIJob("Save", true, 0);
        Job compareJobCurrent = UIJobs.compareJob;
        Job testJobCurrent = UIJobs.testJob;

        UIJobs = setUIJob("Save", false, 1);
        Job compareJobOffer = UIJobs.compareJob;
        Job testJobOffer = UIJobs.testJob;

        Setting originalSetting = Database.getInstance(getApplicationContext()).SettingDao().getSetting();


        SettingClass newSetting = SettingsSave("10", "15", "20", "25", "30");

        onView(withId(R.id.compareOffersButton)).perform(click());

        //onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollToPosition(0)).perform(click());
        //onView(withId(R.id.checkBox)).perform(click());
        //onView(withId(R.id.checkBox)).perform(click());



        //onView(withId(R.id.company1)).check(matches(withText(compareJobCurrent.getCompany())));
        //onView(withId(R.id.company2)).check(matches(withText(compareJobOffer.getCompany())));

        Database.getInstance(getApplicationContext()).JobDao().deleteJob(testJobCurrent);
        Database.getInstance(getApplicationContext()).JobDao().deleteJob(testJobOffer);
        Database.getInstance(getApplicationContext()).SettingDao().update(originalSetting);

    }
}
