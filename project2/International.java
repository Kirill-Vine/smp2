package project2;

/**
 * Class represents an International student.
 * @param profile Profile of the student.
 * @param major major of the student.
 * @param creditsCompleted number of credits completed by the student.
 * @param studyAbroad states whether student is studying abroad.
 * @author Kiril Vinogradav
 * @author Michael Burton
 */
public class International extends NonResident {
    public final static int INSURANCE = 2650;
    private boolean isStudyingAbroad;
    public International (Profile profile, Major major, int creditsCompleted,boolean studyAbroad) {
        super(profile,major,creditsCompleted);
        isStudyingAbroad = studyAbroad;
    }
    public International (Profile p, boolean studyAbroad) {
        super(p);
        isStudyingAbroad = studyAbroad;
    }

    /**
     * returns isStudyingAbroad variable
     * @return value of isStudyingAbroad variable.
     */
    public boolean getAbroad() {
        return isStudyingAbroad;
    }

    /**
     * Calculate The amount the student owes in tuition.
     * @param creditsEnrolled Amount of credits the student is enrolled in.
     * @return The tuition the student owes based on the credits enrolled, as a double.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double output = 0;

        if(!isStudyingAbroad) {
            output += super.FULL_TIME + Student.UNIVERSITY_FEE + INSURANCE;
            if(creditsEnrolled > Student.SOFT_CAP) {
                output+=super.PART_TIME_RATE*(creditsEnrolled-Student.SOFT_CAP);
            }
        } else {
            output+=INSURANCE + Student.UNIVERSITY_FEE;

        }
        return output;
    }


    /**
     * test whether the student has a valid amount of credits.
     * @param creditsEnrolled number of credits the student has.
     * @return true if student is above credit minimum, and below credit maximum.
     */
    @Override
    public boolean isValid(int creditsEnrolled) {
        if(isStudyingAbroad) {
            if(creditsEnrolled < CREDITS_MIN || creditsEnrolled >Student.FULL_TIME) {
                return false;
            } else {
                return true;
            }
        } else {
            if (creditsEnrolled < Student.FULL_TIME || creditsEnrolled > CREDITS_MAX) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * test whether 2 International objects are equivalent.
     * @param o Object to be tested against International class.
     * @return true if both International classes have equivalent profiles.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof International) {
            International student = (International) o;
            if (this.getProfile().equals(student.getProfile())) {
                return true;
            }
        }
        return false;
    }

    /**
     * converts International object as a string
     * @return string that represents International object.
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
        output+="(international";
        if(isStudyingAbroad) {
            output+= " studying abroad";
        }
        output+=")";
        return output;
    }
}