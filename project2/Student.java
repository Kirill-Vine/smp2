package project2;


/**
 Class that records a student's profile, major and credits completed.
 @param p Profile object that has the first name, last name and date of birth of the student.
 @param m Major of the student.
 @param cc int that represents the number of credits the student completed.
 @author Michael Burton
 @author Kirill Vine
 */
public abstract class Student implements Comparable<Student> {
    final static int FULL_TIME = 12;
    final static int SOFT_CAP = 16;
    final static double UNIVERSITY_FEE = 3268;
    final static double PART_TIME_FEE = .8*UNIVERSITY_FEE;
    private Profile profile;
    private Major major;
    private int creditsCompleted;

    Student(Profile p, Major m, int cc) {
        profile = p;
        major = m;
        creditsCompleted = cc;
    }
    Student (Profile p) {
        profile = p;
        major = major.CS;
        creditsCompleted = 0;
    }

    public boolean isValid(int creditsEnrolled){
        if(creditsEnrolled >= 3 && creditsEnrolled <=24) {
            return true;
        } else {
            return false;
        }

    } //polymorphism
    public abstract double tuitionDue(int creditsEnrolled); //polymorphism
    public abstract boolean isResident(); //polymorphism

    /**
     Converts Student to a string representation of the student.
     @return A string that represents the student.
     */
    @Override
    public String toString() {
        final int FRESHMAN = 30;
        final int SOPHOMORE = 60;
        final int JUNIOR = 90;
        final int SENIOR = 120;
        String output;
        output = profile.toString() + " (" + major.getClassCode() + " " + major.getMajor() + " " + major.getSchool()
                + " creditsCompleted: " + creditsCompleted;
        if (creditsCompleted <  FRESHMAN) {
            output += "(Freshman)";
        } else if (creditsCompleted < SOPHOMORE) {
            output += "(Sophomore)";
        } else if (creditsCompleted < JUNIOR) {
            output += "(Junior)";
        } else {
            output += "(Senior)";
        }
        return output;
    }

    /**
     Determines whether Student and an object is equivalent.
     @param o Object to be tested against student class.
     @return true if the object and the student class are equivalent, false if otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student student = (Student) o;
            if (this.profile.equals(student.profile)) {
                return true;
            }
        }
        return false;
    }

    /**
     compares two Students together.
     @return the compareTo of each student's profile class.
     */
    @Override
    public int compareTo(Student student) {
        if (student.equals(this)) {
            return 0;
        } else {
            return student.profile.compareTo(this.profile);
        }
    }

    /**
     Getter method for the profile variable in Student.
     @return student's profile variable.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     Getter method for the major variable in Student.
     @return the student's major
     */
    public Major getMajor() {
        return major;
    }

    /**Getter method for the creditsCompleted variable in Student.
     @return the number of credits the student has completed.
     */
    public int getCredits() {
        return creditsCompleted;
    }

    //setter methods
    public void setMajor(Major m) {
        major = m;
    }

}

