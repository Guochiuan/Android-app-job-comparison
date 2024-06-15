package edu.gatech.seclass.jobcompare6300.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import edu.gatech.seclass.jobcompare6300.job.Job;
import edu.gatech.seclass.jobcompare6300.setting.Setting;

public class Calculator {


    public static BigDecimal AdjustSalaryByCOL(Job job) {
        int col = job.getCostOfLiving();
        BigDecimal salary = job.getSalary();
        BigDecimal adjustedSalary = salary.multiply(BigDecimal.valueOf(100.00)).divide(BigDecimal.valueOf(col), 2, RoundingMode.HALF_UP);
        return adjustedSalary;
    }

    public static BigDecimal AdjustBonusByCOL(Job job) {
        int col = job.getCostOfLiving();
        BigDecimal bonus = job.getBonus();
        BigDecimal adjustedBonus = bonus.multiply(BigDecimal.valueOf(100.00)).divide(BigDecimal.valueOf(col), 2, RoundingMode.HALF_UP);
        return adjustedBonus;
    }


    public static BigDecimal CalculateScore(Job job, Setting setting) {
        int salaryWeight = setting.getSalaryWeight();
        int bonusWeight = setting.getBonusWeight();
        int benefitsWeight = setting.getBenefitsWeight();
        int stipendWeight = setting.getStipendWeight();
        int fundWeight = setting.getFundWeight();

        double totalWeight = salaryWeight + bonusWeight + benefitsWeight + stipendWeight + fundWeight;

        BigDecimal salary = AdjustSalaryByCOL(job);
        BigDecimal bonus = AdjustBonusByCOL(job);
        BigDecimal benefits = salary.multiply(BigDecimal.valueOf(job.getBenefits()).divide(BigDecimal.valueOf(100)));
        BigDecimal stipend = job.getStipend();
        BigDecimal fund = job.getFund();

        BigDecimal score = (BigDecimal.valueOf(salaryWeight / totalWeight).multiply(salary))
                .add(BigDecimal.valueOf(bonusWeight / totalWeight).multiply(bonus))
                .add(BigDecimal.valueOf(benefitsWeight / totalWeight).multiply(benefits))
                .add(BigDecimal.valueOf(stipendWeight / totalWeight).multiply(stipend))
                .add(BigDecimal.valueOf(fundWeight / totalWeight).multiply(fund));
        score = score.setScale(2, BigDecimal.ROUND_HALF_UP);
        return score;
    }


}
