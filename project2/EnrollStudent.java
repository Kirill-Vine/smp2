package project2;

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

    public Profile getProfile() {
        return profile;
    }
    public int getCredits() {
        return creditsEnrolled;
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) o;
            return enrollStudent.profile.equals(this.profile);
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return profile.toString() + " credits enrolled: " + creditsEnrolled;
    }
}