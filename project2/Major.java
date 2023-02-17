package project2;

/**
 enum variable that associates a major with its school and class code.
 @param tempMajor String that represents the student's major.
 @param tempSchool String that represents the school that major is in.
 @param tempClassCode String that represents the class code of that major.
 @author Michael Burton
 */
public enum Major {


    CS("CS", "SAS","01:198"),
    MATH("MATH", "SAS", "01:640"),
    EE("EE","SOE", "14:332"),
    ITI("ITI","SC&I", "04:547"),
    BAIT("BAIT", "RBS","33:136");


    final String major;
    final String school;
    final String classCode;


    private Major(String tempMajor, String tempSchool, String tempClassCode) {
        major = tempMajor;
        school = tempSchool;
        classCode = tempClassCode;
    }

    /**
     converts a string into an enum class.
     @param input String that will be converted to major
     @return Major that input string is associated with
     */
    public static Major stringToMajor(String input) {
        Major output;
        switch (input.toUpperCase()) {
            case "CS":
                output = Major.CS;
                break;
            case "MATH":
                output = Major.MATH;
                break;
            case "EE":
                output = Major.EE;
                break;
            case "ITI":
                output = Major.ITI;
                break;
            case "BAIT":
                output = Major.BAIT;
                break;
            default:
                System.out.println(input.toUpperCase() + " is not a major");
                output = null;
        }
        return output;
    }

    /**
     Getter method for school major is in.
     @return the school the major is in as a string.
     */
    public String getSchool() {
        return school;
    }

    /**
     Getter method for major variable.
     @return a major as a string.
     */
    public String getMajor() {
        return major;
    }

    /**
     Getter method for the class code variable.
     @return the class code the major is under as a string.
     */
    public String getClassCode() {
        return classCode;
    }
}

