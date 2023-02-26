package project2;
import java.io.File;
import java.io.FileNotFoundException;
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
    final static int READ_FILE_LENGTH = 2;


    /**
     * Create Date object from a list of strings.
     * @param inputString string to be converted into date
     * @return date object if string is valid, null if not.
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

    /**
     * converts a string into an int
     * @param inputString string to be turned into credits int.
     * @return valid credits amount if string is valid, and -1 if not.
     */
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

    /**
     * convert string into Resident student
     * @param inputStringList String array to be converted to Resident.
     * @return Resident object if string array is valid, null if not.
     */
    private static Resident setStudentResident(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE) {
            int credits = setCredits(inputStringList[5]);
            Date dob = TuitionManager.setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            return new Resident(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits);
        } else {
            System.out.println("Missing data in line");
        }
        return null;
    }

    /**
     * convert string into NonResident student
     * @param inputStringList String array to be converted to NonResident.
     * @return NonResident object if string array is valid, null if not.
     */
    private static NonResident setStudentNonResident(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE) {
            int credits = setCredits(inputStringList[5]);
            Date dob = TuitionManager.setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            return new NonResident(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits);
        } else {
            System.out.println("Missing data in line");
        }

        return null;
    }

    /**
     * convert string into International student
     * @param inputStringList String array to be converted to International.
     * @return International object if string array is valid, null if not.
     */
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
        } else {
            System.out.println("Missing data in line");
        }
        return null;
    }

    /**
     * convert string into TriState student
     * @param inputStringList String array to be converted to TriState.
     * @return TriState object if string array is valid, null if not.
     */
    private static TriState setStudentTriState(String[] inputStringList) {
        if (inputStringList.length == ADD_COMMAND_SIZE+1) {
            int credits = setCredits(inputStringList[5]);
            Date dob = TuitionManager.setDate(inputStringList[3]);
            Major tempMajor = Major.stringToMajor(inputStringList[4]);
            if (tempMajor == null || credits < 0 || dob == null) {
                return null;
            }
            if(!inputStringList[6].toUpperCase().equals("NY") && !inputStringList[6].toUpperCase().equals("CT")
                    && !inputStringList[6].toUpperCase().equals("NJ")) {
                System.out.println(inputStringList[6] + " is not in the TriState Area");
                return null;
            }
            return new TriState(new Profile(inputStringList[2], inputStringList[1], dob), tempMajor, credits,inputStringList[6]);
        } else {
            System.out.println("Missing data in line");
        }
        return null;
    }

    /**
     * convert string into Resident student using only profile information
     * @param inputStringList String array that represents a profile.
     * @return Resident object if string array is valid, null if not.
     */
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

    /**
     * tests whether string inputList is valid before creating an enrollStudent.
     * @param enrollment Enrollment class that holds enrollment roster.
     * @param roster roster of all current students.
     * @param inputStringList string array that represents student being enrolled.
     */
    private void testStudentRosterBeforeEnrollment(Enrollment enrollment, Roster roster, String[] inputStringList) {
        if(inputStringList.length == CHANGE_MAJOR_COMMAND_SIZE) {
            Student student = setStudentProfile(inputStringList);

            if(roster.contains(student)){
                enrollment.add(setEnrollStudent(inputStringList,roster));
            } else {
                System.out.println(student.getProfile().toString() + " is not in roster");
            }
        } else {
            System.out.println("Not enough data in command");
        }
    }

    /**
     * creates a new subtree of all commands that begin with A
     * commands that begin with A are commands that add students to the roster.
     * Used to decrease line number in run method.
     * @param inputStringList command to be interpreted
     * @param roster roster that students will be added to if valid.
     */
    private void addHelper(String[] inputStringList, Roster roster) {
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
        if(student != null && !roster.contains(student)) {
            System.out.println(student.getProfile().toString() + " was added to roster");
            roster.add(student);
        } else if (student!= null && roster.contains(student)) {
            System.out.println(student.getProfile() + " is already in roster");
        }
    }

    /**
     * creates a new subtree of all commands that begin with P
     * commands that begin with A are commands that print the students in the roster.
     * Used to decrease line number in run method.
     * @param inputStringList command to be interpreted
     * @param roster roster that students will be printed if valid command.
     */
    private void printHelper(String[] inputStringList,Roster roster,Enrollment enrollment) {
        if(inputStringList[0].length() == 1) {
            roster.print();
        } else if(inputStringList[0].charAt(1) == 'S') {
            roster.printByStanding();
        } else if(inputStringList[0].charAt(1) == 'C') {
            roster.printBySchoolMajor();
        } else if(inputStringList[0].charAt(1) == 'E') {
            enrollment.print();
        } else if(inputStringList[0].charAt(1) == 'T') {
            enrollment.printAllTuition(roster);
        }
    }

    /**
     * convert string array into EnrollStudent student using only profile information
     * @param inputStringList String array that represents a profile.
     * @return EnrollStudent object if string array is valid, null if not.
     */
    private EnrollStudent setEnrollStudentProfile(String[] inputStringList, Roster roster) {
        if(inputStringList.length == REMOVE_COMMAND_SIZE){
            Date dob = setDate(inputStringList[3]);
            if (dob == null) {
                return null;
            }
            Profile tempProfile = new Profile(inputStringList[2], inputStringList[1], dob);
            for(int i = 0; i < roster.getRoster().length;i++) {
                if(roster.getRoster()[i] != null && roster.getRoster()[i].getProfile().equals(tempProfile)) {
                    return new EnrollStudent(tempProfile);
                }
            }
            System.out.println(tempProfile.toString() + " is not in roster");
            return null;
        }else {
            System.out.println("missing data in command");
            return null;
        }
    }

    /**
     * convert string array into EnrollStudent
     * @param inputStringList String array that represents an EnrollStudent.
     * @return EnrollStudent object if string array is valid, null if not.
     */
    private EnrollStudent setEnrollStudent(String[] inputStringList,Roster roster) {
        if(inputStringList.length == CHANGE_MAJOR_COMMAND_SIZE){
            int credits = setCredits(inputStringList[4]);
            Date dob = setDate(inputStringList[3]);
            if (dob == null || credits < 0) {
                return null;
            }
            Profile tempProfile = new Profile(inputStringList[2], inputStringList[1], dob);
            for(int i = 0; i < roster.getRoster().length;i++) {
                if(roster.getRoster()[i] != null && roster.getRoster()[i].getProfile().equals(tempProfile)) {
                    if(roster.getRoster()[i].isValid(credits)) {
                        return new EnrollStudent(tempProfile, credits);
                    } else {
                        System.out.println(credits+ ": Credit amount invalid");
                        return null;
                    }
                }
            }
            System.out.println(tempProfile.toString() + " is not in roster");
            return null;
        }else {
            System.out.println("missing data in command");
            return null;
        }
    }

    /**
     *Adds students to a roster determined by an input file.
     * @param path path that leads to file to be read.
     * @param roster roster manipulated by the file.
     */
    private void readFile(String path, Roster roster) {
        File file = new File(path);
        Scanner sc;
         try {
            sc = new Scanner(file);
        } catch(FileNotFoundException ex){
            System.out.println("file does not exist");
            return;
        }
        while (sc.hasNextLine()) {
            Student student = null;
            String fileInput = sc.nextLine();
            String[] fileInputList = fileInput.split(",");
            switch(fileInputList[0]) {
                case "T":
                    student = TuitionManager.setStudentTriState(fileInputList);
                    break;
                case"R":
                    student = TuitionManager.setStudentResident(fileInputList);
                    break;
                case"N":
                    student = TuitionManager.setStudentNonResident(fileInputList);
                    break;
                case"I":
                    student = TuitionManager.setStudentInternational(fileInputList);
                    break;
            }
            if(!roster.contains(student)) {
                roster.add(student);
            }
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
            if(input.equals("")) {
                continue;
            }
            String[] inputStringList = input.split("\\s+");
            if(inputStringList[0].equals("LS") && inputStringList.length ==READ_FILE_LENGTH) {
                readFile(inputStringList[1],roster);
            } else if(inputStringList[0].charAt(0) == 'A') {
                addHelper(inputStringList, roster);
            } else if(inputStringList[0].equals("R")) {
                roster.remove(TuitionManager.setStudentProfile(inputStringList));
            } else if(inputStringList[0].charAt(0) == 'P') {
                printHelper(inputStringList,roster,enrollment);
            } else if (inputStringList[0].equals("L")) {
                roster.printAllStudentsInSchool(inputStringList[1]);
            }else if (inputStringList[0].equals("E")) {
                testStudentRosterBeforeEnrollment(enrollment,roster,inputStringList);
            } else if(inputStringList[0].equals("D")) {
                enrollment.remove(setEnrollStudentProfile(inputStringList,roster));
            } else if(inputStringList[0].equals("S") && inputStringList.length == CHANGE_MAJOR_COMMAND_SIZE) {
                roster.addScholarship(enrollment, TuitionManager.setStudentProfile(inputStringList),inputStringList[4]);
            }else if (inputStringList[0].equals("C")) {
                roster.changeMajor(TuitionManager.setStudentProfile(inputStringList), inputStringList[4]);
            } else if (inputStringList[0].equals("Q")) {
                System.out.println("Closing Roster...");
                exited = true;
            } else if(inputStringList[0].equals("SE")) {
                enrollment.endSemester(roster);
            } else {
                System.out.println(inputStringList[0] + " is an invalid command");
            }
        }
    }
}