package project;

import java.util.Scanner;

/**
 Class that contains a student's first name, last name and Date of Birth
 @param lname last name of the student as a String
 @param fname first name of the student as a String
 @param dob Date of Birth of the student as a Date class
 @author Michael Burton
 @author Kirill Vine
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    public Profile(String l, String f, Date d) {
        this.lname = l;
        this.fname = f;
        dob = d;
    }

    /**
     Converts profile into a string.
     @return String that contains student's last name, first name, and date of birth.
     */
    @Override
    public String toString() {
        return "" + fname + " " + lname + " " + dob.toString();
    }

    /**
     Tests whether 2 profiles are equivalent
     @param o An object class to be compared to the Profile class
     @return true if the student has the same first name, last name and date of birth
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Profile) {
            Profile profile = (Profile) o;
            if (profile.lname.toUpperCase().equals(this.lname.toUpperCase()) && profile.fname.toUpperCase().equals(this.fname.toUpperCase()) && profile.dob.equals(this.dob)) {
                return true;
            }
        }
        return false;
    }

    /**
     @param profile Profile class that is to be compared with
     @returns a positive number if the second profile is worth more lexographically, or if its date of birth is earlier, a negative if the opposite.
     and 0 if both profiles are equivalent.
     */
    @Override
    public int compareTo(Profile profile) {
        int output;
        output = this.lname.compareTo(profile.lname);
        if(output == 0) {
            output = this.fname.compareTo(profile.fname);
            if(output == 0) {
                output = this.dob.compareTo(profile.dob);
            }
        }
        return output;
    }
}