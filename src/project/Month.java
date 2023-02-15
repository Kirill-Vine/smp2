package project;

/**
 Enum class used to associate months with the amount of days they have.
 @param monthNumber integer representing which month of the year that month is.
 @param numberOfDays number of days that are in that month.
 @author Michael Burton

 */
public enum Month {
    JANUARY(1,31),
    FEBRUARY_LEAP(2, 29),
    FEBRUARY(2,28),
    MARCH(3,31),
    APRIL(4,30),
    MAY(5,31),
    JUNE(6,30),
    JULY(7,31),
    AUGUST(8,31),
    SEPTEMBER(9,30),
    OCTOBER(10,31),
    NOVEMBER(11,30),
    DECEMBER(12,31);

    final int days;
    final int month;

    private Month(int monthNumber, int numberOfDays) {
        days = numberOfDays;
        month = monthNumber;
    }

    /**
     Getter method for month number.
     @return number month of the year.
     */
    public int getMonthNumber() {
        return month;
    }

    /**
     Getter method for days variable.
     @return number of days in that month.
     */
    public int getDays() {
        return days;
    }

}