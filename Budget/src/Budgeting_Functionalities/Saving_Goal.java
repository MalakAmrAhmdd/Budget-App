package Budgeting_Functionalities;

import java.time.LocalDate;

/**
 * Represents a saving goal with a target amount, deadline, and total savings.
 */
public class Saving_Goal {
    private int goalID;
    private String name;
    private float targetAmount;
    private LocalDate deadline;
    private float totalSavings;

    /**
     * Default constructor required for Jackson deserialization.
     */
    public Saving_Goal() {}

    /**
     * Constructs a Saving_Goal with the specified parameters.
     * @param goalID The unique identifier for the saving goal.
     * @param name The name of the saving goal.
     * @param targetAmount The target amount to save.
     * @param totalSavings The current total savings towards the goal.
     * @param deadline The deadline for achieving the saving goal.
     */
    public Saving_Goal(int goalID, String name, float targetAmount, float totalSavings, LocalDate deadline) {
        this.goalID = goalID;
        this.name = name;
        this.targetAmount = targetAmount;
        this.totalSavings = totalSavings;
        this.deadline = deadline;
    }

    /**
     * Gets the goal ID.
     * @return The goal ID.
     */
    public int getGoalID() {
        return goalID;
    }

    /**
     * Sets the goal ID.
     * @param goalID The goal ID.
     */
    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    /**
     * Gets the name of the saving goal.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the saving goal.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the target amount to save.
     * @return The target amount.
     */
    public float getTargetAmount() {
        return targetAmount;
    }

    /**
     * Sets the target amount to save.
     * @param targetAmount The target amount.
     */
    public void setTargetAmount(float targetAmount) {
        this.targetAmount = targetAmount;
    }

    /**
     * Gets the total savings towards the goal.
     * @return The total savings.
     */
    public float getTotalSavings() {
        return totalSavings;
    }

    /**
     * Sets the total savings towards the goal.
     * @param totalSavings The total savings.
     */
    public void setTotalSavings(float totalSavings) {
        this.totalSavings = totalSavings;
    }

    /**
     * Gets the deadline for the saving goal.
     * @return The deadline.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline for the saving goal.
     * @param deadline The deadline.
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
