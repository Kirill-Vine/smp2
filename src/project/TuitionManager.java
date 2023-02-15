package project;

import java.util.Scanner;
/**
 This class controls the UI and determines which Roster method to run given various commands.
 @author Michael Burton
 @author Kirill Vine
 */
public class TuitionManager {
    final static int ADD_COMMAND_SIZE = 6;
    final static int REMOVE_COMMAND_SIZE = 4;
    final static int PRINT_COMMAND_SIZE = 1;
    final static int CHANGE_MAJOR_COMMAND_SIZE = 5;
    final static int MIN_AGE = 16;

    /**
     Convert an array of strings into a student object
     @param inputStringList an array of strings that consists of either [command, first name, last name, date of birth, major, credits]
     or [command, first name, last name, date of birth].
     @return the Student class that is created with the inputStringList, and null if the inputString is invalid.
     */
    private static Student setStudent(String[] inputStringList) {
        if (inputStringList[0].equals("A") && inputStringList.length == ADD_COMMAND_SIZE) {
            Major tempMajor;
            int credits = 0;
            Date today = new Date();
            Date dob = new Date(inputStringList[3]);
            tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null) {
                return null;
            }
            try {
                credits = Integer.parseInt(inputStringList[5]);
            } catch (NumberFormatException nfe) {
                System.out.println("credits must be an integer");
                return null;
            }
            if (credits < 0) {
                System.out.println("credits must be a positive number");
                return null;
            }
            if (!dob.isValid()) {
                System.out.println("DOB Invalid: " + inputStringList[3] + " is not a calendar date!");
                return null;
            }
            System.out.println("compareTo " + today.compareTo(dob));
            if (today.compareTo(dob) < MIN_AGE) {

                System.out.println("DOB Invalid: " + inputStringList[3] + " is younger than 16");
                return null;
            }
            return new Student(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits);
        } else if (!inputStringList[0].equals("A") && inputStringList.length == REMOVE_COMMAND_SIZE
                || inputStringList.length == CHANGE_MAJOR_COMMAND_SIZE) {
            Date dob = new Date(inputStringList[3]);
            Profile currentStudentProfile = new Profile(inputStringList[2], inputStringList[1], dob);
            return new Student(currentStudentProfile);
        } else {
            System.out.println("improper command");
            return null;
        }
    }

    /**
     Run the UI for the student roster.
     */
    public void run() {
        Roster roster = new Roster();
        boolean exited = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Roster Manager running...");
        while (!exited) {
            String input = sc.nextLine();
            String[] inputStringList = input.split("\\s+");
            switch (inputStringList[0]) {
                case "A": // add newStudent
                    roster.add(TuitionManager.setStudent(inputStringList));
                    break;
                case "R": //remove
                    roster.remove(TuitionManager.setStudent(inputStringList));
                    break;
                case "P":
                    roster.print();
                    break;
                case "PS":
                    roster.printByStanding();
                    break;
                case "PC":
                    roster.printBySchoolMajor();
                    break;
                case "L":
                    roster.printAllStudentsInSchool(inputStringList[1]);
                    break;
                case "C":
                    roster.changeMajor(TuitionManager.setStudent(inputStringList), inputStringList[4]);
                    break;
                case "Q":
                    System.out.println("Closing Roster...");
                    exited = true;
                    break;
                default:
                    System.out.println(inputStringList[0] + " is an invalid command");
                    continue;
            }
        }
    }
}