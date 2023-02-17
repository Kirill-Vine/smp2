package project2;

public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;
    public EnrollStudent(Profile p, int ce){
        profile = p;
        creditsEnrolled = ce;
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