package project2;
/**
 * Test tuitionDue() method in the International class with 2 test cases: is or is not study abroad.
 * @author Kirill Vine
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class JUnitInternational {

    @org.junit.Test
    public void tuitionDue_isStudyAbroad(){
        International international = new International(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, false);
        assertEquals(35655,international.tuitionDue(12),0);
    }
    @org.junit.Test
    public void tuitionDue_isNotStudyAbroad(){
        International international = new International(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, true);
        assertEquals(5918,international.tuitionDue(12),0);
    }
}