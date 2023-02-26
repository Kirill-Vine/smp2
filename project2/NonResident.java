package project2;

/**
 *a class that represents a student that is a non-resident.
 *  * @param profile profile of the student.
 *  * @param major major of the student.
 *  * @param creditsCompleted the amount of credits completed by the student.
 *  * @author Michael Burton
 *  * @author Kiril Vinogradov
 *  */
public class NonResident extends Student {
    final static double FULL_TIME = 29737;
    final static double PART_TIME_RATE = 966;

    //12 is fulltime
    //16 pays extra
    public NonResident(Profile profile, Major major, int creditsCompleted) {
        super(profile,major,creditsCompleted);
    }

    public NonResident(Profile p) {
        super(p);
    }

    /**
     * Calculate The amount the student owes in tuition.
     * @param creditsEnrolled Amount of credits the student is enrolled in.
     * @return The tuition the student owes based on the credits enrolled, as a double.
     */
    public double tuitionDue(int creditsEnrolled) {
        double output = 0;
        if(creditsEnrolled >= super.FULL_TIME) {
            output+= FULL_TIME;
            output+= super.PART_TIME_FEE;
            if(creditsEnrolled > super.SOFT_CAP) {
                output+=PART_TIME_RATE*(creditsEnrolled-super.SOFT_CAP);
            }
        } else {
            output+= (creditsEnrolled * PART_TIME_RATE);
            output+= super.PART_TIME_FEE;
        }
        return output;
    }

    /**
     * returns whether student is a resident.
     * @return false, all non-resident students are not residents.
     */
    public boolean isResident() {
        return false;
    }

    /**
     * determine whether 2 NonResident are equivalent.
     * @param o Object to be tested against NonResident class.
     * @return whether the 2 NonResident classes have equivalent profiles.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof NonResident) {
            NonResident student = (NonResident) o;
            if (this.getProfile().equals(student.getProfile())) {
                return true;
            }
        }
        return false;
    }

    /**
     * converts NonResident to a string
     * @return NonResident in the form of a string.
     */
    @Override
    public String toString() {
        final int FRESHMAN = 30;
        final int SOPHOMORE = 60;
        final int JUNIOR = 90;
        final int SENIOR = 120;
        String output;
        output = getProfile().toString() + " (" + getMajor().getClassCode() + " " + getMajor().getMajor() + ") " + getMajor().getSchool()
                + " creditsCompleted: " + getCredits();
        if (getCredits() <  FRESHMAN) {
            output += "(Freshman)";
        } else if (getCredits() < SOPHOMORE) {
            output += "(Sophomore)";
        } else if (getCredits() < JUNIOR) {
            output += "(Junior)";
        } else {
            output += "(Senior)";
        }
        output+="(nonresident)";
        return output;
    }
}