package project2;
/**
 * Test isValid() method in the Date class with 7 test cases (5 invalid, 2 valid) and code from the testbed main()
 * in Project 1.
 * @author Kirill Vine
 */
import static org.junit.Assert.*;

public class JUnitDate {

    @org.junit.Test
    public void isValid_moreThan32DaysInAMonth() {
        Date date = new Date("1/32/2003");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void isValid_nonLeapYear_29DaysInFebruary() {
        Date date = new Date("2/29/2003");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void isValid_31DaysInA30DayMonth(){
        Date date = new Date("4/31/2003");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void isValid_negativeIntegerInMonth(){
        Date date = new Date("-1/31/2003");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void isValid_IntegerHigherThan12InMonth(){
        Date date = new Date("13/31/2003");
        assertFalse(date.isValid());
    }
    @org.junit.Test
    public void isValid_leapYear29DaysInFebruary(){
        Date date = new Date("2/29/2016");
        assertTrue(date.isValid());
    }
    @org.junit.Test
    public void isValid_31DaysInDecember(){
        Date date = new Date("12/31/2000");
        assertTrue(date.isValid());
    }
}