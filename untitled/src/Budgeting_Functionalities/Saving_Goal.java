package Budgeting_Functionalities;

import java.time.LocalDate;

public class Saving_Goal {
    private int goalID;
    private String name;
    private float targetAmount;
    private LocalDate deadline;
    private float totalSavings;

    // Default constructor required for Jackson deserialization
    public Saving_Goal() {}

    public Saving_Goal(int goalID, String name, float targetAmount, float totalSavings, LocalDate deadline) {
        this.goalID = goalID;
        this.name = name;
        this.targetAmount = targetAmount;
        this.totalSavings = totalSavings;
        this.deadline = deadline;
    }

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(float targetAmount) {
        this.targetAmount = targetAmount;
    }

    public float getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(float totalSavings) {
        this.totalSavings = totalSavings;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
