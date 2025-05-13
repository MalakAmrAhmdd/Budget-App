package Budgeting_Functionalities;

import User_Management.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages saving goals for a user, allowing addition of new saving goals.
 */
public class Saving_GoalManager {
    private User user;

    /**
     * Constructs a Saving_GoalManager for the specified user.
     * @param user The user whose saving goals are managed.
     */
    public Saving_GoalManager(User user) {
        this.user = user;
    }

    /**
     * Adds a new saving goal for the user, similar to how expenses are added.
     * @param goalName The name of the saving goal.
     * @param targetAmount The target amount to save.
     * @param deadline The deadline for achieving the saving goal.
     * @return true if the saving goal was added successfully, false otherwise.
     */
    public boolean addSavingGoal(String goalName, float targetAmount, LocalDate deadline) {
        // Validate input
        if (goalName == null || goalName.trim().isEmpty()) {
            System.out.println("Goal name cannot be empty.");
            return false;
        }
        if (targetAmount <= 0) {
            System.out.println("Target amount must be positive.");
            return false;
        }
        if (deadline == null || deadline.isBefore(LocalDate.now())) {
            System.out.println("Deadline must be a future date.");
            return false;
        }

        List<Saving_Goal> goals = user.getSavingGoals();
        if (goals == null) {
            goals = new ArrayList<>();
            user.setSavingGoals(goals);
        }
        int newId = goals.size() + 1;
        Saving_Goal savingGoal = new Saving_Goal(newId, goalName, targetAmount, 0, deadline);
        goals.add(savingGoal);

        user.updateUserInFile();

        System.out.println("Saving goal added successfully. Your saving goals have been updated.");
        return true;
    }
}
