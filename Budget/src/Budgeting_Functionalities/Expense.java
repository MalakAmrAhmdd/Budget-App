package Budgeting_Functionalities;

    import java.time.LocalDate;

    /**
     * Represents an expense with a specific category, amount, and date.
     */
    public class Expense {
        private int expenseID;
        private String category;
        private float amount;
        private float limit;
        private LocalDate date;

        /**
         * Default constructor required for Jackson deserialization.
         */
        public Expense() {}

        /**
         * Constructs a new Expense with the specified details.
         *
         * @param expenseID The unique ID of the expense.
         * @param category  The category of the expense.
         * @param amount    The amount of the expense.
         * @param date      The date of the expense.
         * @param limit     The limit associated with the expense.
         */
        public Expense(int expenseID, String category, float amount, LocalDate date, float limit) {
            this.expenseID = expenseID;
            this.category = category;
            this.amount = amount;
            this.date = date;
            this.limit = limit;
        }

        /**
         * Gets the unique ID of the expense.
         *
         * @return The expense ID.
         */
        public int getExpenseID() {
            return expenseID;
        }

        /**
         * Gets the category of the expense.
         *
         * @return The expense category.
         */
        public String getCategory() {
            return category;
        }

        /**
         * Gets the amount of the expense.
         *
         * @return The expense amount.
         */
        public float getAmount() {
            return amount;
        }

        /**
         * Gets the limit associated with the expense.
         *
         * @return The expense limit.
         */
        public float getLimit() {
            return limit;
        }

        /**
         * Gets the date of the expense.
         *
         * @return The expense date.
         */
        public LocalDate getDate() {
            return date;
        }
    }