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
    //Use wrapper classes for processing numeric data,calculateMedianGrade(): Calculates and returns the median grade for students in a
// course.
public double calculateMedianGrade() {
    List<Integer> sortedGrades = new ArrayList<>(grades);
    Collections.sort(sortedGrades);
    int size = sortedGrades.size();

    if (size % 2 == 0) {
        // For even-sized list, return the average of the two middle numbers (ensure floating-point division)
        return (sortedGrades.get(size / 2 - 1) + sortedGrades.get(size / 2)) / 2.0;
    } else {
        // For odd-sized list, return the middle element
        return sortedGrades.get(size / 2);
    }
}

    //toString method
    public String toString() {
        return title;  // You can modify this to show more information if needed
    }
    
}