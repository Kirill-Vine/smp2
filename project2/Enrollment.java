package project2;

public class Enrollment {
    private int size = 1;
    private EnrollStudent[] enrollStudents = new EnrollStudent[size];

    public void add(EnrollStudent enrollStudent) {
        EnrollStudent[] newarr = new EnrollStudent[size + 1];
        for (int i = 0; i < size; i++) {
            if(enrollStudents[i] != null) {
                newarr[i] = enrollStudents[i];
            } else {
                enrollStudents[i] = enrollStudent;
                return;
            }
        }
        newarr[size-1] = enrollStudent;
        size+=1;
        enrollStudents = newarr;
    } //add to the end of array

    //move the last one in the array to replace the deleting index position
    public void remove(EnrollStudent enrollStudent) {
        for(int i = 0; i < enrollStudents.length; i++) {
            if(enrollStudents[i] != null && enrollStudents[i].equals(enrollStudent)) {
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

    public boolean contains(EnrollStudent enrollStudent) {
        for(int i = 0; i < enrollStudents.length; i++) {
            if(enrollStudents[i] != null && enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for(int i = 0; i < enrollStudents.length; i++) {
            if(enrollStudents[i] != null) {
                System.out.println(enrollStudents[i].toString());
            } else {
                System.out.println("is empty");
            }
        }
    } //print the array as is without sorting
    public void printAllTuition(Roster roster) {
        for(EnrollStudent enrollStudent: enrollStudents) {
            for(Student student : roster.getRoster()) {
                if(student != null && enrollStudent != null && student.getProfile().equals(enrollStudent.getProfile())) {
                    System.out.println(student.toString() + " " + student.tuitionDue(enrollStudent.getCredits()));
                }
            }
        }
    }

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