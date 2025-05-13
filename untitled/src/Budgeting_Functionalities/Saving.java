package Budgeting_Functionalities;


public class Saving {
    private int savingID;
    private float totalSavings;


    // Default constructor required for Jackson deserialization
    public Saving() {
    }
    public Saving(int savingID, float totalSavings) {
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
