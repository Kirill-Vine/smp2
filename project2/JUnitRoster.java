package project2;
/**
 * Test add() and remove() methods, in the Roster class with 4 test cases: true or false for adding International and true or false for
 * adding Tristate.
 * @author Kirill Vine
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class JUnitRoster {

    @org.junit.Test
    public void addInternationalTrue() {
        Roster roster = new Roster();
        Student international = new International(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, false);
        assertTrue(roster.add(international));
    }
    @org.junit.Test
    public void addInternationalFalse() {
        Roster roster = new Roster();
        Student international = new International(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, false);
        roster.add(international);
        assertFalse(roster.add(international));
    }
    @org.junit.Test
    public void addTriStateTrue() {
        Roster roster = new Roster();
        Student tristate = new TriState(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, "New York");
        assertTrue(roster.add(tristate));
    }
    @org.junit.Test
    public void addTriStateFalse() {
        Roster roster = new Roster();
        Student tristate = new TriState(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, "New York");
        roster.add(tristate);
        assertFalse(roster.add(tristate));
    }
    @org.junit.Test
    public void removeInternationalTrue() {
        Roster roster = new Roster();
        Student international = new International(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, false);
        roster.add(international);
        assertTrue(roster.remove(international));
    }
    @org.junit.Test
    public void removeInternationalFalse() {
        Roster roster = new Roster();
        Student international = new International(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, false);
        assertFalse(roster.remove(international));
    }
    @org.junit.Test
    public void removeTriStateTrue() {
        Roster roster = new Roster();
        Student tristate = new TriState(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, "New York");
        roster.add(tristate);
        assertTrue(roster.remove(tristate));
    }
    @org.junit.Test
    public void removeTriStateFalse() {
        Roster roster = new Roster();
        Student tristate = new TriState(new Profile("l","f",new Date("12/31/2000")),Major.CS, 12, "New York");
        assertFalse(roster.remove(tristate));
    }
}