package Budgeting_Functionalities;

    /**
     * Represents a budget category with a specific amount, limit, and date range.
     */
    public class Budget {
        private int budgetID;
        private String category;
        private float amount;
        private float limit;
        private String startDate;
        private String endDate;

        /**
         * Default constructor required for Jackson deserialization.
         */
        public Budget() {}

        /**
         * Constructs a new Budget with the specified details.
         *
         * @param budgetID   The unique ID of the budget.
         * @param category   The category of the budget.
         * @param amount     The allocated amount for the budget.
         * @param startDate  The start date of the budget.
         * @param endDate    The end date of the budget.
         */
        public Budget(int budgetID, String category, float amount, String startDate, String endDate) {
            this.budgetID = budgetID;
            this.category = category;
            this.amount = amount;
            this.limit = amount;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        /**
         * Gets the unique ID of the budget.
         *
         * @return The budget ID.
         */
        public int getBudgetID() {
            return budgetID;
        }

        /**
         * Gets the category of the budget.
         *
         * @return The budget category.
         */
        public String getCategory() {
            return category;
        }

        /**
         * Sets the category of the budget.
         *
         * @param category The new category of the budget.
         */
        public void setCategory(String category) {
            this.category = category;
        }

        /**
         * Gets the allocated amount for the budget.
         *
         * @return The budget amount.
         */
        public float getAmount() {
            return amount;
        }

        /**
         * Sets the allocated amount for the budget.
         *
         * @param amount The new amount for the budget.
         */
        public void setAmount(float amount) {
            this.amount = amount;
        }

        /**
         * Gets the limit of the budget.
         *
         * @return The budget limit.
         */
        public float getLimit() {
            return limit;
        }

        /**
         * Gets the start date of the budget.
         *
         * @return The start date of the budget.
         */
        public String getStartDate() {
            return startDate;
        }

        /**
         * Gets the end date of the budget.
         *
         * @return The end date of the budget.
         */
        public String getEndDate() {
            return endDate;
        }
    }