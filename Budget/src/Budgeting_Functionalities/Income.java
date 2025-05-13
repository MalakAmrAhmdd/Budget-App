package Budgeting_Functionalities;

    import java.time.LocalDate;

    /**
     * Represents an income source with a specific amount and date.
     */
    public class Income {
        private int incomeID;
        private String source;
        private float amount;
        private LocalDate date;

        /**
         * Default constructor required for Jackson deserialization.
         */
        public Income() {}

        /**
         * Constructs a new Income with the specified details.
         *
         * @param incomeID The unique ID of the income.
         * @param source   The source of the income.
         * @param amount   The amount of the income.
         * @param date     The date of the income.
         */
        public Income(int incomeID, String source, float amount, LocalDate date) {
            this.incomeID = incomeID;
            this.source = source;
            this.amount = amount;
            this.date = date;
        }

        /**
         * Gets the unique ID of the income.
         *
         * @return The income ID.
         */
        public int getIncomeID() {
            return incomeID;
        }

        /**
         * Gets the source of the income.
         *
         * @return The income source.
         */
        public String getSource() {
            return source;
        }

        /**
         * Gets the amount of the income.
         *
         * @return The income amount.
         */
        public float getAmount() {
            return amount;
        }

        /**
         * Gets the date of the income.
         *
         * @return The income date.
         */
        public LocalDate getDate() {
            return date;
        }
    }