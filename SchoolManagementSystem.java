import java.util.List;

public class SchoolManagementSystem {
    public static void main(String[] args) {
        University university = new University();
        university.loadData("data.txt");

        Student student1 = new Student("S1001", "Alice", "abc@gmail.com", "2000-01-01", "123 Main St");
        Student student2 = new Student("S1002", "Bob", "dsa", "2000-01-02", "456 Main St");
        Student student3 = new Student("S1003", "Charlie", "sdf", "2000-01-03", "789 Main St");

        Teacher teacher1 = new Teacher("T1001", "David", "@gmailc.com", "1980-01-01", "123 Main St");
        Teacher teacher2 = new Teacher("T1002", "Eve", "sdf", "1980-01-02", "456 Main St");

        Course course1 = new Course("C1001", "Math", teacher1, 3);
        Course course2 = new Course("C1002", "Science", teacher2, 4);

        university.addStudent(student3);
        university.addStudent(student2);
        university.addStudent(student1);

        university.addTeacher(teacher2);
        university.addTeacher(teacher1);
        
        university.addCourse(course2);
        university.addCourse(course1);


        teacher1.assignCourse(course1);
        teacher2.assignCourse(course2);

        course1.addStudent(student1);
        course1.addStudent(student2);

        course2.addStudent(student1);
        course2.assignGrade(student1, 45);

        AdministrativeStaff staff1 = new AdministrativeStaff("A1001", "Frank", "abcder@gmail.com", "1970-01-01", "123 Main St", "Manager");

        AdministrativeStaff staff2 = new AdministrativeStaff("A1002", "Grace", "456@gmail.com", "1970-01-02", "456 Main St", "Supervisor");

        university.addAdminstaff(staff2);
        university.addAdminstaff(staff1);

        System.out.println(university.getCourses().get(1).calculateAverageGrade());
        

        university.saveData("data.txt");
        }
    }
