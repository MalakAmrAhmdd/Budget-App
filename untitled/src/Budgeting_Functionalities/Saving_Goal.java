package Budgeting_Functionalities;


public class Saving_Goal {
    private int savingID;
    private float totalSavings;


    // Default constructor required for Jackson deserialization
    public Saving_Goal() {
    }
    public Saving_Goal(int savingID, float totalSavings) {
        this.savingID = savingID;
        this.totalSavings = totalSavings;
    }
    public int getSavingID() {
        return savingID;
    }
    public float getTotalSavings() {
        return totalSavings;
    }

}
