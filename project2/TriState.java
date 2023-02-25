package project2;

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
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double output = 0;
        if(creditsEnrolled < super.FULL_TIME) {
            output+= FULL_TIME;
            output+= super.UNIVERSITY_FEE;
            if(creditsEnrolled > super.SOFT_CAP) {
                output+=PART_TIME_RATE*(super.SOFT_CAP -creditsEnrolled);
            }
        } else {
            output+= (creditsEnrolled * PART_TIME_RATE);
            output+= super.PART_TIME_FEE;
        }
        switch(state.toUpperCase()) {
            case "NY":
                output-=4000;
                break;
            case "CT":
                output-=5000;
                break;
        }
        if(output < 0) {
            output=  0;
        }
        return output;
    }
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