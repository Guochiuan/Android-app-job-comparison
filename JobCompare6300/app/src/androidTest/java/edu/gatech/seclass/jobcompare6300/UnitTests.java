package edu.gatech.seclass.jobcompare6300;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.setting.Setting;

@RunWith(AndroidJUnit4.class)
public class UnitTests {

    private String title;
    private String company;
    private String city;
    private String state;
    private int costOfLiving;
    private BigDecimal salary;
    private BigDecimal bonus;
    private int benefits = 1;
    private BigDecimal stipend;
    private BigDecimal fund;
    private boolean isCurrentJob;
    private Job job;

    @Before
    public void setupJob() {
        title = "testTitle";
        company = "testCompany";
        city = "testCity";
        state = "testState";
        costOfLiving = 1;
        salary = new BigDecimal("10.50");
        bonus = new BigDecimal("5.25");
        benefits = 1;
        stipend = new BigDecimal("7.75");
        fund = new BigDecimal("1.11");
        isCurrentJob = true;

        job = new Job(
                title,
                company,
                city,
                state,
                costOfLiving,
                salary,
                bonus,
                benefits,
                stipend,
                fund,
                isCurrentJob);
    }

    @Test
    public void testJobGetters() {
        assertEquals(title, job.getTitle());
        assertEquals(company, job.getCompany());
        assertEquals(city, job.getCity());
        assertEquals(state, job.getState());
        assertEquals(costOfLiving, job.getCostOfLiving());
        assertEquals(salary, job.getSalary());
        assertEquals(bonus, job.getBonus());
        assertEquals(benefits, job.getBenefits());
        assertEquals(stipend, job.getStipend());
        assertEquals(fund, job.getFund());
        assertTrue(job.isCurrentJob());
    }

    @Test
    public void testJobSetters() {
        job.setTitle("newTitle");
        job.setCompany("newCompany");
        job.setCity("newCity");
        job.setState("newState");
        job.setCostOfLiving(0);
        job.setSalary(new BigDecimal("0.00"));
        job.setBonus(new BigDecimal("0.00"));
        job.setBenefits(0);
        job.setStipend(new BigDecimal("0.00"));
        job.setFund(new BigDecimal("0.00"));
        job.setCurrentJob(false);

        assertEquals("newTitle", job.getTitle());
        assertEquals("newCompany", job.getCompany());
        assertEquals("newCity", job.getCity());
        assertEquals("newState", job.getState());
        assertEquals(0, job.getCostOfLiving());
        assertEquals(new BigDecimal("0.00"), job.getSalary());
        assertEquals(new BigDecimal("0.00"), job.getBonus());
        assertEquals(0, job.getBenefits());
        assertEquals(new BigDecimal("0.00"), job.getStipend());
        assertEquals(new BigDecimal("0.00"), job.getFund());
        assertFalse(job.isCurrentJob());
    }

    @Test
    public void testValidateBenefits() {
        job.setBenefits(0);
        assertTrue(job.validateBenefits());

        job.setBenefits(50);
        assertTrue(job.validateBenefits());

        job.setBenefits(100);
        assertTrue(job.validateBenefits());

        job.setBenefits(-1);
        assertFalse(job.validateBenefits());

        job.setBenefits(101);
        assertFalse(job.validateBenefits());
    }

    @Test
    public void testValidateFund() {
        job.setFund(new BigDecimal("0.00"));
        assertTrue(job.validateFund());

        job.setFund(new BigDecimal("9000.00"));
        assertTrue(job.validateFund());

        job.setFund(new BigDecimal("18000.00"));
        assertTrue(job.validateFund());

        job.setFund(new BigDecimal("-0.01"));
        assertFalse(job.validateFund());

        job.setFund(new BigDecimal("18000.01"));
        assertFalse(job.validateFund());
    }

    @Test
    public void testValidateCol() {
        job.setCostOfLiving(-1);
        assertFalse(job.validateCOL());

        job.setCostOfLiving(0);
        assertFalse(job.validateCOL());

        job.setCostOfLiving(1);
        assertTrue(job.validateCOL());
    }

    @Test
    public void testSettingGetters() {
        Setting setting = new Setting(1, 1, 1, 1, 1);

        assertEquals(1, setting.getSalaryWeight());
        assertEquals(1, setting.getBonusWeight());
        assertEquals(1, setting.getBenefitsWeight());
        assertEquals(1, setting.getStipendWeight());
        assertEquals(1, setting.getFundWeight());
    }

    @Test
    public void testSettingSetters() {
        Setting setting = new Setting(1, 1, 1, 1, 1);

        setting.setSalaryWeight(2);
        setting.setBonusWeight(2);
        setting.setBenefitsWeight(2);
        setting.setStipendWeight(2);
        setting.setFundWeight(2);

        assertEquals(2, setting.getSalaryWeight());
        assertEquals(2, setting.getBonusWeight());
        assertEquals(2, setting.getBenefitsWeight());
        assertEquals(2, setting.getStipendWeight());
        assertEquals(2, setting.getFundWeight());
    }
}
