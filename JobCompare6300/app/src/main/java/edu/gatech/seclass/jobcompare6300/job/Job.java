package edu.gatech.seclass.jobcompare6300.job;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.math.BigDecimal;

@Entity(tableName = "job_table")
public class Job implements Comparable<Job> {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "company")
    private String company;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "state")
    private String state;

    @ColumnInfo(name = "cost_of_living")
    private int costOfLiving;

    @ColumnInfo(name = "salary")
    private BigDecimal salary;

    @ColumnInfo(name = "bonus")
    private BigDecimal bonus;

    @ColumnInfo(name = "benefits")
    private int benefits;

    @ColumnInfo(name = "stipend")
    private BigDecimal stipend;

    @ColumnInfo(name = "fund")
    private BigDecimal fund;

    @ColumnInfo(name = "is_current_job")
    private boolean isCurrentJob;

    @ColumnInfo(name = "score")
    private BigDecimal score;

    @ColumnInfo(name = "is_selected")
    private boolean isSelected;

    public Job(String title, String company, String city,
               String state, int costOfLiving, BigDecimal salary,
               BigDecimal bonus, int benefits, BigDecimal stipend,
               BigDecimal fund, boolean isCurrentJob) {
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.costOfLiving = costOfLiving;
        this.salary = salary;
        this.bonus = bonus;
        this.benefits = benefits;
        this.stipend = stipend;
        this.fund = fund;
        this.isCurrentJob = isCurrentJob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(int costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public int getBenefits() {
        return benefits;
    }

    public void setBenefits(int benefits) {
        this.benefits = benefits;
    }

    public BigDecimal getStipend() {
        return stipend;
    }

    public void setStipend(BigDecimal stipend) {
        this.stipend = stipend;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    //need to be implemented with GUI
    public boolean validateBenefits() {
        if(getBenefits()>-1 && getBenefits() <101)
            return true;
        else return false;
    }

    public boolean validateFund() {
        if(getFund().doubleValue()>=0 && getFund().doubleValue()<=18000)
            return true;
        else return false;
    }

    public boolean validateCOL(){
        if(getCostOfLiving()>0){
            return true;
        }
        else return false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean Selected) {
        isSelected = Selected;
    }

    @Override
    public int compareTo(Job o) {
        return this.getScore().compareTo(o.getScore());
    }
}

