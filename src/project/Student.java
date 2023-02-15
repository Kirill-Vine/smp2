package project;


/**
 Class that records a student's profile, major and credits completed.
 @param p Profile object that has the first name, last name and date of birth of the student.
 @param m Major of the student.
 @param cc int that represents the number of credits the student completed.
 @author Michael Burton
 @author Kirill Vine
 */
public class Student implements Comparable<Student> {
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

    public static void main(String args[]) {
        String[][] testStringStudent  = {
                {"John Smith 1/1/2002", "John Smith 1/1/2002"},
                { "John Smith 1/1/2002", "Alex Jones 1/1/2002" },
                {"Alex Jones 1/1/2002", "John Smith 1/1/2002"},
                { "John Smith 1/1/2002", "John Smith 1/2/2002"},
                {"John Smith 1/2/2002", "John Smith 1/1/2002"}
        };
        for (int i = 0; i < testStringStudent.length; i++) {
            String[] studentAStringList = testStringStudent[i][0].split("\\s+");
            String[] studentBStringList = testStringStudent[i][1].split("\\s");
            Student studentA = new Student(
                    new Profile(studentAStringList[1], studentAStringList[0], new Date(studentAStringList[2])));
            Student studentB = new Student(
                    new Profile(studentBStringList[1], studentBStringList[0], new Date(studentBStringList[2])));
            System.out.println(testStringStudent[i][0] + " compareTo " + testStringStudent[i][1] + ": "
                    + studentA.compareTo(studentB));
        }
    }
}

