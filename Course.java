import java.util.*;
// import java.io.*;
public class Course {
    private String courseID;
    private String title;
    private int credits;
    private Teacher assignedTeacher;
    private List<Student> enrolledStudents;
    private List<Integer> grades;

    public Course(String courseID, String title,Teacher teacher, int credits) {
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
        this.assignedTeacher = teacher;
        this.enrolledStudents = new ArrayList<>(100);
        grades = new ArrayList<>(enrolledStudents.size());
        for (int i = 0; i < enrolledStudents.size(); i++) {
            grades.add(null);
        }
    }
    public Course(String courseID, String title, int credits) {
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
        this.assignedTeacher = null;
        this.enrolledStudents = new ArrayList<>(100);
        grades = new ArrayList<>(enrolledStudents.size());
        for (int i = 0; i < enrolledStudents.size(); i++) {
            grades.add(null);
         }
    }

    public String getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public Teacher getAssignedTeacher() {
        return assignedTeacher;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setAssignedTeacher(Teacher teacher) {
        this.assignedTeacher = teacher;
        teacher.assignCourse(this);
    }

    public void setAssignedTeacher() {
        this.assignedTeacher = null;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
        grades.add(-1);
        System.out.println("Student " + student.getStudentID() + " added to " + title);
        student.enrollInCourse(this);
    }

    public void removeStudent(Student student) {
        for(int i=0;i<enrolledStudents.size();i++){
            if(enrolledStudents.get(i).getStudentID().equals(student.getStudentID())){
                enrolledStudents.remove(i);
                grades.remove(i);
                System.out.println("Student " + student.getStudentID() + " removed from " + title);
                return;
            }
        }
    }

    
    public void assignGrade(Student s, Integer grade) {
    for (int i = 0; i < enrolledStudents.size(); i++) {
        if (enrolledStudents.get(i).getStudentID().equals(s.getStudentID())) {
            grades.set(i, grade);
            System.out.println("Grade " + grade + " assigned to " + s.getStudentID() + " in " + title);
            return;
        }
    }
}


    public double calculateAverageGrade() {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i) != -1) {
                sum += grades.get(i);
                count++;
            }
        }
        return sum / count;
    }
    //toString method
    public String displayDetails()
     {
        String str = "Course ID: " + courseID + ", Title: " + title + ", Credits: " + credits + ", Teacher: ";
        if (assignedTeacher != null) {
            str += assignedTeacher.getName();
        } else {
            str += "None";
        }
        str += ", Enrolled Students: ";
        if (enrolledStudents.size() == 0) {
            str += "None";
        } else {
            for (int i = 0; i < enrolledStudents.size(); i++) {
                str+= enrolledStudents.get(i).getStudentID();
                str += enrolledStudents.get(i).getName();

                if (i != enrolledStudents.size() - 1) {
                    str += ", ";
                }
            }
        }

        return str;
    }
    
}