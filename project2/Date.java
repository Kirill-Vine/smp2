package project2;

import java.util.Calendar;

/**
 * Represents a day of the year on a calendar day.
 *  @param String date
 * A string written in the syntax "mm/dd/yyyy"
 *
 * @author Michael Burton
 * @author Kirill Vine
 *
 */

public class Date implements Comparable<Date> {


    private int year;
    private int month;
    private int day;


    //create an object with today’s date (see Calendar class)
    public Date() {
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }


    //take mm/dd/yyyy and create a Date object
    public Date(String date) {
        String[] dateArray = date.split("/", 0);
        try {
            this.month = Integer.parseInt(dateArray[0]);
            this.day = Integer.parseInt(dateArray[1]);
            this.year = Integer.parseInt(dateArray[2]);
        } catch (NumberFormatException nfe) {
            System.out.println("date must be numbers");
            this.year = -1;
            this.month = -1;
            this.day = -1;
        }
    }

    /**
     Tests if the Date class's year is a leap year.
     @return true if the year is a leap year, false if otherwise.
     */
    private boolean isLeap() {
        final int QUADRENNIAL = 4;
        final int CENTENNIAL = 100;
        final int QUATERCENTENNIAL = 400;
        if (this.year % QUATERCENTENNIAL == 0) {
            return true;
        }
        if (this.year % CENTENNIAL == 0) {
            return false;
        }
        if (this.year % QUADRENNIAL == 0) {
            return true;
        }
        return false;
    }

    /**
     Converts an integer to the month enum it should correspond to.
     @param input integer that corresponds to a month
     @return Month that has the same monthNumber as int input
     */
    public Month intToMonth(int input) {
        if (input == 2) {
            if (isLeap()) {
                return Month.FEBRUARY_LEAP;
            } else {
                return Month.FEBRUARY;
            }
        } else {
            for (Month month : Month.values()) {
                if (input == month.getMonthNumber()) {
                    return month;
                }
            }
        }
        return null;
    }

    /**
     * Determines whether not the current Date class represents a valid calendar date.
     @return true if the date is a valid calendar date, false if otherwise
     */
    public boolean isValid() {
        final int POSITIVE_NUMBERS = 0;
        final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
        if (this.month <= POSITIVE_NUMBERS || this.day <= POSITIVE_NUMBERS ||
                this.month > Month.DECEMBER.getMonthNumber() || this.year > CURRENT_YEAR ||
                this.month < Month.JANUARY.getMonthNumber()) {
            return false;
        }
        Month currentMonth = intToMonth(this.month);
        if (this.day > currentMonth.getDays()) {
            return false;
        }
        return true;
    }

    /**
     Getter method for year
     @return year integer variable
     */
    public int getYear() {
        return year;
    }

    /**
     Getter method for month
     @return month integer variable
     */
    public int getMonth() {
        return month;
    }
    /**
     Getter method for day
     @return day integer variable
     */
    public int getDay() {
        return day;
    }

    /**
     returns Date class as a string
     @return Date class as a string writtten as mm/dd/yyyy
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     method tests whether 2 date classes are equivalent
     @param Object takes an object class to compare to the Date class
     @return true if the object is a date class that matches the current Date class's month, day, and year, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Date) {
            Date date = (Date) o;
            if (date.month == this.month && date.day == this.day && date.year == this.year) {
                return true;
            }
        }
        return false;
    }

    /**
     Finds the difference between 2 date classes in terms of years
     @param Date date class that is to be compared to current class
     @return returns difference between the 2 dates in terms of years apart.
     */
    @Override
    public int compareTo(Date d) {
        final int ROUND_DOWN = 1;
        if (this.month == d.month && this.year == d.year && this.day == d.day) {
            return 0;
        } else if (this.year == d.year) {
            if (this.month < d.month || (this.month == d.month && this.day < d.day)) {
                return -1;
            }
            return 1;
        }
        if (this.month < d.month || (this.month == d.month && this.day < d.day)) {
            return (this.year - d.year) - ROUND_DOWN;
        }
        return this.year - d.year;
    }

    public static void main(String args[]) {
        String[] testStringDate = {"1/31/2003",
                "1/32/2003",
                "2/29/2004",
                "2/32/2004",
                "2/28/2003",
                "2/29/2003",
                "2/28/2003",
                "2/29/2003",
                "4/31/2003",
                "4/32/2003",
                "5/30/2003",
                "5/32/2003",
                "6/30/2003",
                "6/32/2003",
                "7/31/2003",
                "7/32/2003",
                "8/31/2003",
                "8/32/2003",
                "9/30/2003",
                "9/32/2003",
                "10/31/2003",
                "10/32/2003",
                "11/30/2003",
                "11/32/2003",
                "12/31/2003",
                "12/32/2003",
                "13/1/2003",
                "-1/31/2003",
                "1/0/2003",
                "1/1/2222",
                "3/31/2003",
                "3/32/2003"
        };
        Date test;
        for (int i = 0; i < testStringDate.length; i++) {
            test = new Date(testStringDate[i]);
            System.out.println("input: " + testStringDate[i] + " output: " + test.isValid());
        }
    }
}