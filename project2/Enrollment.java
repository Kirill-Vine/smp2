package project2;
import java.text.DecimalFormat;

/**
 * Class made to control the enrollment of students.
 * Uses an array of @EnrollStudent objects to keep manage the data.
 *
 * @author Michael Burton
 * @author Kirill Vinogradov
 */
public class Enrollment {

    private int size = 1;
    private EnrollStudent[] enrollStudents = new EnrollStudent[size];


    /**
     *adds an EnrollStudent to the enrollment roster.
     * @param enrollStudent student to be added to enrollment roster.
     */
    public void add(EnrollStudent enrollStudent) {
        if(enrollStudent == null) {
            return;
        }
        EnrollStudent[] newarr = new EnrollStudent[size + 1];
        for (int i = 0; i < size; i++) {
            if(enrollStudents[i]!=null) {
                if(enrollStudents[i].equals(enrollStudent)){
                    System.out.println("student replaced " + enrollStudent.toString());
                    enrollStudents[i] = enrollStudent;
                    return;
                }
                newarr[i] = enrollStudents[i];
            }
        }
        System.out.println("student enrolled " + enrollStudent.toString());
        newarr[newarr.length-1] = enrollStudent;
        size+=1;
        enrollStudents = newarr;
    } //add to the end of array

    /**
     * Remove a student from the roster if he is present.
     * @param enrollStudent student to be removed from the roster.
     */
    public void remove(EnrollStudent enrollStudent) {
        if(enrollStudent == null) {
            return;
        }
        if(!contains(enrollStudent)) {
            System.out.println(enrollStudent.getProfile() + " is not in enrollment roster");
            return;
        }
        for(int i = 0; i < enrollStudents.length; i++) {
            if(enrollStudents[i] != null && enrollStudents[i].equals(enrollStudent)) {
                System.out.println(enrollStudent.getProfile() + " removed from enrollment");
                enrollStudents[i] = null;
                break;
            }
        }
        //move array down so null value is always at the end
        for(int i = 1; i < enrollStudents.length; i++) {
            if(enrollStudents[i] != null && enrollStudents[i-1] == null) {
                enrollStudents[i-1] = enrollStudents[i];
                enrollStudents[i] = null;
            }
        }
    }

    /**
     * Test whether the enrollment roster contains a student.
     * @param enrollStudent test whether this student is in the enrollment roster.
     * @return true if the enrollStudent is in the roster, and false if otherwise.
     */
    public boolean contains(EnrollStudent enrollStudent) {
        for(int i = 0; i < enrollStudents.length; i++) {
            if(enrollStudents[i] != null && enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test whether the enrollment roster is empty.
     * @return true if the roster contains no students, false if otherwise.
     */
    public boolean isEmpty() {
        for(int i = 0; i < enrollStudents.length; i++) {
            if(enrollStudents[i]!= null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Print every student currently enrolled in the enrollment roster.
     */
    public void print() {
        if(!isEmpty()) {
            System.out.println("** Enrollment **");
            for (int i = 0; i < enrollStudents.length; i++) {
                if (enrollStudents[i] != null) {
                    System.out.println(enrollStudents[i].toString());
                }
            }
            System.out.println("** end of enrollment **");
        } else {
            System.out.println("Enrollment is empty");
        }
    }

    /**
     * Print every student enrolled in the enrollment roster as well as their tuition due.
     * @param roster roster to print the students and tuition of.
     */
    public void printAllTuition(Roster roster) {
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        String output;
        System.out.println("** Tuition due **");
        for(EnrollStudent enrollStudent: enrollStudents) {
            for(Student student : roster.getRoster()) {
                if(student != null && enrollStudent != null && student.getProfile().equals(enrollStudent.getProfile())) {
                    output = student.getProfile().toString();
                    if(student instanceof Resident) {
                        output += " (Resident)";
                    } else if (student instanceof TriState) {
                        TriState triState = (TriState) student;
                        output += "(Tri-State " + triState.getState().toUpperCase() + ")";

                    } else if(student instanceof International) {
                        International international = (International) student;
                        output+="(International student ";
                        if(international.getAbroad()) {
                            output+="studying abroad)";
                        } else {
                            output+=")";
                        }
                    }else if(student instanceof NonResident) {
                        output += " (Non-Resident)";
                    }
                    output+= " credits enrolled: " + enrollStudent.getCredits()+" tuitionDue: $"
                                    + df.format(student.tuitionDue(enrollStudent.getCredits()));
                    System.out.println(output);
                }
            }
        }
        System.out.println("** tuition due end **");
    }

    /**
     * Returns the current enrollment roster
     * @return the current enrollment roster
     */
    public EnrollStudent[] getEnrollment() {
        return enrollStudents;
    }
    /**
     * Add credits to each student enrolled in the enrollment roster as the semester ends.
     * @param roster roster of students to have credits addded.
     */
    public void endSemester(Roster roster) {
        for(EnrollStudent enrollStudent: enrollStudents) {
            for(Student student : roster.getRoster()) {
                if(student != null && enrollStudent != null && student.getProfile().equals(enrollStudent.getProfile())) {
                    student.addCredits(enrollStudent.getCredits());
                    remove(enrollStudent);
                    break;
                }
            }
        }
    }
    public static void main(String[] args){
        Enrollment er = new Enrollment();
        EnrollStudent studd = new EnrollStudent(new Profile("l", "f", new Date("1/1/2000")),1);
        er.add(studd);
        er.remove(studd);
        er.print();
    }
}