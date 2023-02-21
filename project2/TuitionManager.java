package project2;

import java.util.Scanner;
/**
 This class controls the UI and determines which Roster method to run given various commands.
 @author Michael Burton
 @author Kirill Vine
 */
public class TuitionManager {
    final static int ADD_COMMAND_SIZE = 6;
    final static int REMOVE_COMMAND_SIZE = 4;
    final static int CHANGE_MAJOR_COMMAND_SIZE = 5;
    final static int MIN_AGE = 16;

    /**
     Convert an array of strings into a student object
     @param inputStringList an array of strings that consists of either [command, first name, last name, date of birth, major, credits]
     or [command, first name, last name, date of birth].
     @return the Student class that is created with the inputStringList, and null if the inputString is invalid.
     */

    private static Date setDate(String inputString) {
        Date dob = new Date(inputString);
        Date today = new Date();
        if (!dob.isValid()) {
            System.out.println("DOB Invalid: " + inputString + " is not a calendar date!");
            return null;
        }
        if (today.compareTo(dob) < MIN_AGE) {

            System.out.println("DOB Invalid: " + inputString + " is younger than 16");
            return null;
        }
        return dob;
    }

    private static int setCredits(String inputString) {
        int credits;
        try {
            credits = Integer.parseInt(inputString);
        } catch (NumberFormatException nfe) {
            System.out.println("credits must be an integer");
            return -1;
        }
        if (credits < 0) {
            System.out.println("credits must be a positive number");
            return -1;
        }
        return credits;
    }

    private static Resident setStudentResident(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE) {
            int credits = setCredits(inputStringList[5]);
            Date dob = TuitionManager.setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                System.out.println("major or credits or dob is null");
                return null;
            }
            return new Resident(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits);
        }
        return null;
    }
    private static NonResident setStudentNonResident(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE) {
            int credits = setCredits(inputStringList[5]);
            Date dob = TuitionManager.setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            return new NonResident(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits);
        }
        return null;
    }

    private static International setStudentInternational(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE+1) {
            int credits = setCredits(inputStringList[5]);
            Date dob = TuitionManager.setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            boolean abroad = Boolean.parseBoolean(inputStringList[6]);
            return new International(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits,abroad);
        }
        return null;
    }
    private static TriState setStudentTriState(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE+1) {
            int credits = setCredits(inputStringList[5]);
            Date dob = TuitionManager.setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            if(!inputStringList[6].equals("NY") && !inputStringList[6].equals("CT") && !inputStringList[6].equals("NJ")) {
                System.out.println(inputStringList[6] + " is not in the TriState Area");
                return null;
            }
            return new TriState(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits,inputStringList[6]);
        }
        return null;
    }

    private static Student setStudentProfile(String[] inputStringList) {
        if (inputStringList.length == REMOVE_COMMAND_SIZE
                || inputStringList.length == CHANGE_MAJOR_COMMAND_SIZE) {
            Date dob = new Date(inputStringList[3]);
            Profile currentStudentProfile = new Profile(inputStringList[2], inputStringList[1], dob);
           return new Resident(currentStudentProfile);
        } else {
            System.out.println("improper command format");
            return null;
        }
    }

    void addHelper(String[] inputStringList, Roster roster) {
        Student student = null;
        if(inputStringList[0].length() == 2) {
            if (inputStringList[0].charAt(1) == 'R') {
                student = TuitionManager.setStudentResident(inputStringList);
            } else if (inputStringList[0].charAt(1) == 'N') {
                student = TuitionManager.setStudentNonResident(inputStringList);
            } else if (inputStringList[0].charAt(1) == 'T') {
                student = TuitionManager.setStudentTriState(inputStringList);
            } else if (inputStringList[0].charAt(1) == 'I') {
                student = TuitionManager.setStudentInternational(inputStringList);
            }
        } else {
            System.out.println("improper command format");
            return;
        }
        if(student != null) {
            roster.add(student);
        }
    }
    void printHelper(String[] inputStringList,Roster roster) {
        if(inputStringList[0].length() == 1) {
            roster.print();
        } else if(inputStringList[0].charAt(1) == 'S') {
            roster.printByStanding();
        } else if(inputStringList[0].charAt(1) == 'C') {
            roster.printBySchoolMajor();
        } else if(inputStringList[0].charAt(1) == 'E') {

        } else if(inputStringList[0].charAt(1) == 'T') {
        }
    }

    public EnrollStudent setEnrollStudent(String[] inputStringList) {
        if (inputStringList.length == CHANGE_MAJOR_COMMAND_SIZE) {
            int credits = setCredits(inputStringList[4]);
            Date dob = setDate(inputStringList[3]);
            if (dob == null || credits < 0) {
                return null;
            }
            return new EnrollStudent(new Profile(inputStringList[1], inputStringList[2], dob), credits);
        }else {
            return null;
        }
    }


    /**
     Run the UI for the student roster.
     */
    public void run() {
        Roster roster = new Roster();
        Enrollment enrollment = new Enrollment();
        boolean exited = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Roster Manager running...");
        while (!exited) {
            String input = sc.nextLine();
            String[] inputStringList = input.split("\\s+");
            if(inputStringList[0].charAt(0) == 'A') {
                addHelper(inputStringList, roster);
            } else if(inputStringList[0].equals("R")) {
                roster.remove(TuitionManager.setStudentProfile(inputStringList));
            } else if(inputStringList[0].charAt(0) == 'P') {
                printHelper(inputStringList, roster);
            } else if (inputStringList[0].equals("L")) {
                roster.printAllStudentsInSchool(inputStringList[1]);
            }else if (inputStringList[0].equals("E")) {
                enrollment.add(setEnrollStudent(inputStringList));
            } else if(inputStringList[0].equals("D")) {
                enrollment.remove(setEnrollStudent(inputStringList));
            } else if(inputStringList[0].equals("S")) {

            }else if (inputStringList[0].equals("C")) {
                roster.changeMajor(TuitionManager.setStudentProfile(inputStringList), inputStringList[4]);
            } else if (inputStringList[0].equals("Q")) {
                System.out.println("Closing Roster...");
                exited = true;
            } else if(inputStringList[0].equals("SE")) {
            } else {
                System.out.println(inputStringList[0] + " is an invalid command");
            }
        }
    }
}