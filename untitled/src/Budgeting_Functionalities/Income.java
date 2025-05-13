package Budgeting_Functionalities;

import User_Management.User;
import java.time.LocalDate;


public class Income {
    private User user;
    private int incomeID;
    private String source;
    private float amount;
    private LocalDate date;

    // Default constructor required for Jackson deserialization
    public Income() {}

    public Income(int incomeID, String source, float amount, LocalDate date) {
        this.incomeID = incomeID;
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    public int getIncomeID() {
        return incomeID;
    }

     public String getSource() {
        return source;
     }

     public float getAmount() {
        return amount;
     }

     public LocalDate getDate() {
        return date;
     }


}
