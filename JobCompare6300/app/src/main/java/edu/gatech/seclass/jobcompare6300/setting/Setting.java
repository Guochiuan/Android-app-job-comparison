package edu.gatech.seclass.jobcompare6300.setting;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "setting_table")
public class Setting {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "salary_weight")
    private int salaryWeight = 1;

    @ColumnInfo(name = "bonus_weight")
    private int bonusWeight = 1;

    @ColumnInfo(name = "benefits_weight")
    private int benefitsWeight = 1;

    @ColumnInfo(name = "stipend_weight")
    private int stipendWeight = 1;

    @ColumnInfo(name = "fund_weight")
    private int fundWeight = 1;

    public Setting(int salaryWeight, int bonusWeight, int benefitsWeight, int stipendWeight, int fundWeight) {
        this.salaryWeight = salaryWeight;
        this.bonusWeight = bonusWeight;
        this.benefitsWeight = benefitsWeight;
        this.stipendWeight = stipendWeight;
        this.fundWeight = fundWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalaryWeight() {
        return salaryWeight;
    }

    public void setSalaryWeight(int salaryWeight) {
        this.salaryWeight = salaryWeight;
    }

    public int getBonusWeight() {
        return bonusWeight;
    }

    public void setBonusWeight(int bonusWeight) {
        this.bonusWeight = bonusWeight;
    }

    public int getBenefitsWeight() {
        return benefitsWeight;
    }

    public void setBenefitsWeight(int benefitsWeight) {
        this.benefitsWeight = benefitsWeight;
    }

    public int getStipendWeight() {
        return stipendWeight;
    }

    public void setStipendWeight(int stipendWeight) {
        this.stipendWeight = stipendWeight;
    }

    public int getFundWeight() {
        return fundWeight;
    }

    public void setFundWeight(int fundWeight) {
        this.fundWeight = fundWeight;
    }

}
