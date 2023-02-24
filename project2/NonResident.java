package project2;

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
        return output;
    }


    public boolean isResident() {
        return false;
    }

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
}