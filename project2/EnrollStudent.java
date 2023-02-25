package project2;

/**
 * Class that represents an enrolled student.
 * @param p Profile of the enrolled student.
 * @param ce number of credits enrolled students is taking.
 * @author Michael Burton
 * @author Kiril Vinogradov
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;
    public EnrollStudent(Profile p, int ce){
        profile = p;
        creditsEnrolled = ce;
    }
    public EnrollStudent(Profile p) {
        profile = p;
        creditsEnrolled = 3;
    }

    /**
     * return the profile variable
     * @return Profile object of EnrollStudent.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * return the credits variable
     * @return int variable credits of EnrollStudent.
     */
    public int getCredits() {
        return creditsEnrolled;
    }

    /**
     * Tests whether 2 EnrollStudent objects equal one another
     * @param o Object whose equality is tested.
     * @return true if the 2 objects have equal profile variables, false if otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) o;
            return enrollStudent.profile.equals(this.profile);
        } else {
            return false;
        }
    }

    /**
     * Convert EnrollStudent object into a string.
     * String displays profile and creditsEnrolled.
     * @return String representing the EnrollStudent object.
     */
    @Override
    public String toString() {
        return profile.toString() + " credits enrolled: " + creditsEnrolled;
    }
}