package Budgeting_Functionalities;

public class Goal {
    private int goalID;
    private String goalName;
    private float targetAmount;
    private float savedAmount;
    private String targetDate;

    // Default constructor required for Jackson deserialization
    public Goal() {}
    public Goal(int goalID, String goalName, float targetAmount, float savedAmount, String targetDate) {
        this.goalID = goalID;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.targetDate = targetDate;
    }
    public int getGoalID() {
        return goalID;
    }
    public String getGoalName() {
        return goalName;
    }

    public float getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(float targetAmount) {
        this.targetAmount = targetAmount;
    }
    public String getTargetDate(String targetDate) {
        return targetDate;
    }
}