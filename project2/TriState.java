package project2;

/**
 * Class represents an International student.
 * @param profile Profile of the student.
 * @param major major of the student.
 * @param creditsCompleted number of credits completed by the student.
 * @param stateString states whether student is a state.
 * @author Kirill Vine
 * @author Michael Burton
 */
public class TriState extends NonResident {
    private String state;
    public TriState (Profile profile, Major major, int creditsCompleted,String stateString) {
        super(profile,major,creditsCompleted);
        state = stateString;
    }
    public TriState (Profile p, String stateString) {
        super(p);
        state = stateString;
    }

    /**
     * return the state variable.
     * @return state variable.
     */
    public String getState() {
        return state;
    }

    /**
     * Calculate The amount the student owes in tuition.
     * @param creditsEnrolled Amount of credits the student is enrolled in.
     * @return The tuition the student owes based on the credits enrolled, as a double.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double output = 0;
        if(creditsEnrolled >= Student.FULL_TIME) {
            output+= super.FULL_TIME;
            output+= super.UNIVERSITY_FEE;
            if(creditsEnrolled > super.SOFT_CAP) {
                output+=PART_TIME_RATE*(creditsEnrolled- super.SOFT_CAP);
            }
            switch(state.toUpperCase()) {
                case "NY":
                    output-=4000;
                    break;
                case "CT":
                    output-=5000;
                    break;
            }
        } else {
            output+= (creditsEnrolled * super.PART_TIME_RATE);
            output+= Student.PART_TIME_FEE;
        }

        if(output < 0) {
            output=  0;
        }
        return output;
    }

    /**
     * determine whether 2 TriState objects are eqiuvalent.
     * @param o Object to be tested against TriState class.
     * @return whether the 2 TriState have equivalent profiles.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof TriState) {
            TriState student = (TriState) o;
            if (this.getProfile().equals(student.getProfile())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convert TriState student to a string.
     * @return TriState student as a string.
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
        output+="(non-resident) (Tri-State: " + state + ")";
        return output;
    }
}