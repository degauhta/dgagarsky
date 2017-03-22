package ru.job4j;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

/**
 * CustomersCounter class.
 *
 * @author Denis
 * @since 19.03.2017
 */
class CustomersCounter {
    /**
     * Customers time.
     */
    private Map<MyTime, String> customersTime = new TreeMap<>();

    /**
     * Overlaps time.
     */
    private Map<String, Integer> overlapsTime = new HashMap<>();

    /**
     * Max customers.
     */
    private int maxCustomers;

    /**
     * Customer in.
     */
    private static final String CUSTOMER_IN = "in";

    /**
     * Customer out.
     */
    private static final String CUSTOMER_OUT = "out";

    /**
     * Add new customer.
     *
     * @param timeStart  start time in seconds
     * @param timeFinish end time in second
     */
    void addCustomer(int timeStart, int timeFinish) {
        this.customersTime.put(new MyTime(timeStart), CUSTOMER_IN);
        this.customersTime.put(new MyTime(timeFinish), CUSTOMER_OUT);
    }

    /**
     * Print overlaps time.
     */
    void printMostCustomersTime() {
        if (this.customersTime.size() > 2) {
            checkOverlaps();
            if (this.overlapsTime.size() > 0) {
                for (Map.Entry<String, Integer> entry : this.overlapsTime.entrySet()) {
                    if (entry.getValue() == this.maxCustomers) {
                        System.out.println(entry.getKey());
                    }
                }
            }
        }
    }

    /**
     * Check customers time for overlaps.
     */
    private void checkOverlaps() {
        int count = 0;
        int prev = 0;
        for (Map.Entry<MyTime, String> entry : this.customersTime.entrySet()) {
            if (CUSTOMER_IN.equals(entry.getValue())) {
                count++;
            } else if (prev < entry.getKey().getSeconds()) {
                this.overlapsTime.put(String.format("%s customers from %s till %s", count,
                        convertSeconds(prev),
                        convertSeconds(entry.getKey().getSeconds())), count);
                this.maxCustomers = Math.max(this.maxCustomers, count);
                count--;
            }
            prev = entry.getKey().getSeconds();
        }
    }

    /**
     * Convert seconds to string representation.
     *
     * @param convertTime seconds
     * @return string representation
     */
    private String convertSeconds(int convertTime) {
        int hours = convertTime / 3600;
        int minutes = (convertTime % 3600) / 60;
        int seconds = convertTime % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * MyTime class.
     */
    private class MyTime implements Comparable<MyTime> {
        /**
         * MyTime in seconds.
         */
        private int seconds;

        /**
         * Default constructor.
         *
         * @param seconds seconds
         */
        MyTime(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public String toString() {
            return String.valueOf(this.seconds);
        }

        @Override
        public int compareTo(MyTime o) {
            return (this.seconds < o.seconds) ? -1 : 1;
        }

        /**
         * Get seconds.
         *
         * @return seconds
         */
        int getSeconds() {
            return seconds;
        }
    }
}