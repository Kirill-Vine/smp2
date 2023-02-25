package project2;

public class Resident extends Student {

    final static double FULL_TIME = 12536;
    final static double PART_TIME_RATE = 404;
    final static int SCHOLARSHIP_MAX = 10000;

    //12 is fulltime
    //16 pays extra
    private int scholarship;
    public Resident(Profile profile, Major major, int creditsCompleted) {
        super(profile,major,creditsCompleted);
        scholarship = 0;
    }

    public Resident(Profile p) {
        super(p);
        scholarship = 0;
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
        output-=scholarship;
        return output;
    }

    public void awardScholarship(int s) {
        scholarship += s;
        if(scholarship > SCHOLARSHIP_MAX) {
            scholarship = SCHOLARSHIP_MAX;
        }
    }

    public boolean isResident() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Resident) {
            Resident student = (Resident) o;
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
        output+="(resident)";
        return output;
    }
}