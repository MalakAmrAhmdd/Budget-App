package Budgeting_Functionalities;

public class Goal {
    private String name;
    private double targetAmount;
    private double currentAmount;

    public Goal(String name, double targetAmount) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = 0;
    }

    public void trackProgress(double amount) {
        this.currentAmount += amount;
    }
}