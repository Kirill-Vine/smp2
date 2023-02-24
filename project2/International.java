package project2;

public class International extends NonResident {
    private boolean isStudyingAbroad;
    public International (Profile profile, Major major, int creditsCompleted,boolean studyAbroad) {
        super(profile,major,creditsCompleted);
        isStudyingAbroad = studyAbroad;
    }
    public International (Profile p, boolean studyAbroad) {
        super(p);
        isStudyingAbroad = studyAbroad;
    }

    @Override
    public double tuitionDue(int creditsEnrolled) {
        double output = 0;
        if(isStudyingAbroad) {
            if(creditsEnrolled < super.FULL_TIME) {
                output+=super.UNIVERSITY_FEE;
            } else {
                output+=super.PART_TIME_FEE;
            }
        } else {
            if (creditsEnrolled < super.FULL_TIME) {
                output += FULL_TIME;
                output += super.UNIVERSITY_FEE;
                if (creditsEnrolled > super.SOFT_CAP) {
                    output += PART_TIME_RATE * (super.SOFT_CAP - creditsEnrolled);
                }
            } else {
                output += (creditsEnrolled * PART_TIME_RATE);
                output += super.PART_TIME_FEE;
            }
        }
        return output;
    }

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
}