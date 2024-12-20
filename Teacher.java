import java.util.*;
// import java.io.*;
public class Teacher extends Person implements Reportable {
    private String teacherID;
    private String specialization;
    private List<Course> coursesTaught;

    public Teacher(String teacherID, String name, String email, String dateOfBirth, String specialization) {
        super(name, email, dateOfBirth);
        this.teacherID = teacherID;
        this.specialization = specialization;
        this.coursesTaught = new ArrayList<>();
    }

    public String getTeacherID() {
        return teacherID;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public void assignCourse(Course course) {
        coursesTaught.add(course);
        System.out.println("Teacher " + teacherID + " assigned to teach " + course.getTitle());
    }

    public void displayCourses() {
        System.out.println("Courses taught by " + name + ":");
        for (Course course : coursesTaught) {
            System.out.println(course.getTitle() + " (" + course.getCourseID() + ")");
        }
    }
    @Override
    public void exportToFile() {
        
    }

    @Override
    public String displayDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Teacher: ").append(teacherID)
               .append(", Name: ").append(name)
               .append(", Specialization: ").append(specialization)
               .append(", Email: ").append(email)
               .append(", Date of Birth: ").append(dateOfBirth)
               .append(", Courses Taught: ");
        for (Course course : coursesTaught) {
            details.append(course.getTitle()).append(" (").append(course.getCourseID()).append("), ");
        }
        if (!coursesTaught.isEmpty()) {
            details.setLength(details.length() - 2);
        }
        return details.toString();
    }

    @Override
    public String generateReport() {
        return "Teacher Report: " + displayDetails();
    }

    
    public boolean issameteacher(Teacher obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Teacher other = (Teacher) obj;
        return teacherID.equals(other.getTeacherID()) && name.equals(other.getName()) && email.equals(other.getEmail()) && dateOfBirth.equals(other.getDateOfBirth()) && specialization.equals(other.getSpecialization());
    }
}