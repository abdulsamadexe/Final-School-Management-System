import java.io.*;
import java.util.*;
public class University {
    private static int totalStudents = 0;
    private static int totalTeachers = 0;
    private static int totalCourses = 0;
    private static int totaladministrativestaff = 0;

    private Repository<Student> students;
    private Repository<Teacher> teachers;
    private Repository<Course> courses;
    private Repository<AdministrativeStaff> adminstaff;

    public University() {
        students = new Repository<>();
        teachers = new Repository<>();
        courses = new Repository<>();
        adminstaff = new Repository<>();
    }
    public static int getTotalStudents() {
        return totalStudents;
    }

    public static int getTotalTeachers() {
        return totalTeachers;
    }

    public static int getTotalCourses() {
        return totalCourses;
    }

    public static int gettotalAdministrativeStaff() {
        return totaladministrativestaff;
    }

    public List<Student> getStudents() {
        return students.getAll();
    }

    public List<Teacher> getTeachers() {
        return teachers.getAll();
    }

    public List<Course> getCourses() {
        return courses.getAll();
    }

    public List<AdministrativeStaff> getAllAdministrativeStaff() {
        return adminstaff.getAll();
    }


    public int addStudent(Student student) {
        List<Student> st=students.getAll();
        for(Student stud:st){
            if(stud.getStudentID().equals(student.getStudentID())){
                //already exists 
                return -1;
            }
        }
        students.add(student);
        totalStudents++;
        return 0;
        
    }

    public int addAdminstaff(AdministrativeStaff s) {
        List<AdministrativeStaff> adm=adminstaff.getAll();

        for(AdministrativeStaff ad:adm){
            if(ad.getStaffID().equals(s.getStaffID())){
                return -1;
            }
        }

        adminstaff.add(s);
        totaladministrativestaff++;
        return 0;
    }

    public int addTeacher(Teacher teacher) {
            List<Teacher> t=teachers.getAll();
            for(Teacher teach:t){
                if(teach.getTeacherID().equals(teacher.getTeacherID())){
                    //already exists
                    return -1;
                }
            }
            teachers.add(teacher);
            totalTeachers++;
            return 0;
        }
    

    public int addCourse(Course course) {
        List<Course> crs=courses.getAll();
        for(Course cr:crs){
            if(cr.getCourseID().equals(course.getCourseID())){
                // -1 if the course already exists in the list
                return -1;
            }
        }
        courses.add(course);
        totalCourses++;
        return 0;
        
    }

    public void displaySystemStats() {
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Total Teachers: " + totalTeachers);
        System.out.println("Total Courses: " + totalCourses);
    }

    public List<Student> searchStudentByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students.getAll()) {
            // Replace underscores with spaces in the student's name
            String studentName = student.getName().replace("_", " ");
            
            // Check if the student's name (with spaces instead of underscores) contains the search term
            if (studentName.toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Course> displayStudentCourses(String studentID) {
        Student student = searchStudentByID(studentID);
        if (student == null) {
            return null;
        }
        return student.displayCourses();
    }

    public List<Course> displayTeacherCourses(String teacherID) {
        Teacher teacher = searchTeacherByID(teacherID);
        if (teacher == null) {
            return null;
        }
        return teacher.displayCourses();
    }
    
    // public String generateReport(List<Person> people) {
        
    // }
    // generatereport func for following code
    // public String generateReport(List<Person> people) {
    //     StringBuilder report = new StringBuilder();
    //     for (Person person : people) {
    //         report.append(person.displayDetails()).append("\n");
    //     }
    //     report.append("Total number of people: ").append(people.size());
    //     return report.toString();
    // }
    public String generateReport(List<Person> people) {
        AdministrativeStaff admin = new AdministrativeStaff("","","","","","");
        return admin.generateReport(people);
    }
    // public String generateReport(List<Person> people) {
    //     StringBuilder report = new StringBuilder();
    //     for (Person person : people) {
    //         report.append(person.displayDetails()).append("\n");
    //     }
    //     report.append("Total number of people: ").append(people.size());
    //     return report.toString();
    // }

    public String displaydetails(Course course) {
        return course.displayDetails();
    }

    public Teacher searchTeacherByID(String teacherID) {
        for (Teacher teacher : teachers.getAll()) {
            if (teacher.getTeacherID().equals(teacherID)) {
                return teacher;
            }
        }
        return null;
    }

    public Course searchCourseByID(String courseID) {
        for (Course course : courses.getAll()) {
            if (course.getCourseID().equals(courseID)) {
                return course;
            }
        }
        return null;
    }

    public Student searchStudentByID(String studentID) {
        for (Student student : students.getAll()) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }   

    public AdministrativeStaff searchAdminStaffByID(String staffID) {
        for (AdministrativeStaff staff : adminstaff.getAll()) {
            if (staff.getStaffID().equals(staffID)) {
                return staff;
            }
        }
        return null;
        }

        public int assignTeacherToCourse(String teacherID, String courseID) {
        // Find the teacher and course by their IDs
        Teacher teacher = searchTeacherByID(teacherID);
        Course course = searchCourseByID(courseID);

        // Return -1 if either the teacher or course is not found
        if (teacher == null || course == null) {
            return -1; // Teacher or course does not exist
        }

        // Check if the course already has an assigned teacher
        if (course.getAssignedTeacher() != null) {
            return 1; // Course already has an assigned teacher
        }

        // Assign the teacher to the course
        course.setAssignedTeacher(teacher);
        return 0; // Assignment successful
        }

        public int enrollStudentInCourse(String studentID, String courseID) {
        // Find the student and course by their IDs
        Student student = searchStudentByID(studentID);
        Course course = searchCourseByID(courseID);

        // Return -1 if either the student or course is not found
        if (student == null || course == null) {
            return -1; // Student or course does not exist
        }

        // Check if the student is already enrolled in the course
        for (Student s : course.getEnrolledStudents()) {
            if (s.getStudentID().equals(studentID)) {
            return 1; // Student already enrolled
            }
        }

        // Enroll the student in the course
        course.addStudent(student); // Assuming addStudent handles the addition
        return 0; // Enrollment successful
        }

        public int assignGradeToStudent(String studentID, String courseID, int grade) {
        // Find the student and course by their IDs
        Student student = searchStudentByID(studentID);
        Course course = searchCourseByID(courseID);

        // Return -1 if either the student or course is not found
        if (student == null || course == null) {
            return -1; // Student or course does not exist
        }

        // Check if the student is enrolled in the course
        boolean isEnrolled = false;
        for (Student s : course.getEnrolledStudents()) {
            if (s.getStudentID().equals(studentID)) {
            isEnrolled = true;
            break;
            }
        }

        if (!isEnrolled) {
            return 1; // Student not enrolled in the course
        }

        // Assign the grade to the student
        course.assignGrade(student, grade);
        return 0; // Grade assignment successful
        }

        public int removeStudentFromCourse(String studentID, String courseID) {
        // Find the student and course by their IDs
        Student student = searchStudentByID(studentID);
        Course course = searchCourseByID(courseID);
    
        // Return -1 if either the student or course is not found
        if (student == null || course == null) {
            return -1; // Student or course does not exist
        }
    
        // Check if the student is enrolled in the course
        boolean isEnrolled = false;
        for (Student s : course.getEnrolledStudents()) {
            if (s.getStudentID().equals(studentID)) {
                isEnrolled = true;
                break;
            }
        }
    
        if (!isEnrolled) {
            return 1; // Student not enrolled in the course
        }
    
        // Remove the student from the course
        course.removeStudent(student); // Assuming removeStudent handles the removal
        return 0; // Removal successful
    }

    //calculate the average grade of a course
    public double calculateAverageGrade(String courseID) {
        Course course = searchCourseByID(courseID);
        if(course==null){
            return -1;
        } // Return -1 if the course is not found
        List<Student> students = course.getEnrolledStudents();
        List<Integer> grades = course.getGrades();
        
        if(students.size()==0){
            return 1;
        } // Return 1 if no students are enrolled in the course
       
        int a = -1;
        for (int i = 0; i < students.size(); i++) {
            if (grades.get(i) != -1) {
                a = 0;
            }
            if(a != 0) {
                return 2;
            }
        }
        // for (int i = 0; i < students.size(); i++) {
        //     if (grades.get(i) != -1) {
        //         total += grades.get(i);
        //         count++;
        //     }
        // }
        // return total / count;
        return course.calculateAverageGrade();
    }
    public double calculateMedianGrade(String courseID) {
        Course course = searchCourseByID(courseID);
        if(course==null){
            return -1;
        } // Return -1 if the course is not found
        List<Student> students = course.getEnrolledStudents();
        List<Integer> grades = course.getGrades();
        
        if(students.size()==0){
            return 1;
        } // Return 1 if no students are enrolled in the course
       
        int a = -1;
        for (int i = 0; i < students.size(); i++) {
            if (grades.get(i) != -1) {
                a = 0;
            }
            if(a != 0) {
                return 2;
            }
        }
    
        // Sort the valid grades
        // Collections.sort(validGrades);
        // int size = validGrades.size();
    
        // // Calculate the median
        // if (size % 2 == 0) {
        //     // If the number of valid grades is even, return the average of the two middle values
        //     return (validGrades.get(size / 2 - 1) + validGrades.get(size / 2)) / 2.0;
        // } else {
        //     // If the number of valid grades is odd, return the middle value
        //     return validGrades.get(size / 2);
        // }
        return course.calculateMedianGrade();
    }
    

    public List<Course> filterCoursesByTeacher(String teacherID) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses.getAll()) {
            Teacher teacher = course.getAssignedTeacher();
            if (teacher != null && teacher.getTeacherID().equals(teacherID)) {
                result.add(course);
            }
        }
        return result;
    }

    

    public List<Course> filterCoursesByCredits(int minCredits) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses.getAll()) {
            if (course.getCredits() >= minCredits) {
                result.add(course);
            }
        }
        return result;
    }

    public void loadData(String filename) {
        try (Scanner scanner = new Scanner(new File("Course_data.txt"))) {
            Course currentCourse = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" ");
                if (parts[0].startsWith("C")) {
                    String courseID = parts[0];
                    String title = parts[1];
                    int credits = Integer.parseInt(parts[2]);
                    currentCourse = new Course(courseID, title, credits);
                    addCourse(currentCourse);
                } else if (parts[0].startsWith("T")) {
                    String teacherID = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    String dateOfBirth = parts[3];
                    String specialization = parts[4];
                    Teacher teacher = new Teacher(teacherID, name, email, dateOfBirth, specialization);
                    if (currentCourse != null) {
                        currentCourse.setAssignedTeacher(teacher);
                    }
                 } else if (parts[0].equals("null")) {
                        currentCourse.setAssignedTeacher();
                    } 
                   else if (parts[0].startsWith("S")) {
                    String studentID = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    String dateOfBirth = parts[3];
                    String address = parts[4];
                    Student student = new Student(studentID, name, email, dateOfBirth, address);
                    if (currentCourse != null) {
                        currentCourse.addStudent(student);
                    }
                }else if(parts[0].equals("Grades:")){
                    for(int i=1;i<parts.length;i++){
                        currentCourse.assignGrade(currentCourse.getEnrolledStudents().get(i-1), Integer.parseInt(parts[i]));
                    }
            }
        }
     } catch (FileNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(new File("Student_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" ");
                String studentID = parts[0];
                String name = parts[1];
                String email = parts[2];
                String dateOfBirth = parts[3];
                String address = parts[4];
                Student student = new Student(studentID, name, email, dateOfBirth, address);
                addStudent(student);

                while (scanner.hasNextLine()) {
                    String courseLine = scanner.nextLine().trim();
                    if (courseLine.isEmpty()) {
                        break;
                    }
                    String[] courseParts = courseLine.split(" ");
                    String courseID = courseParts[0];
                    String title = courseParts[1];
                    int credits = Integer.parseInt(courseParts[2]);
                    Course course = null;
                    for (Course c : courses.getAll()) {
                        if (c.getCourseID().equals(courseID)) {
                            course = c;
                            break;
                        }
                    }
                    if (course == null) {
                        course = new Course(courseID, title, credits);
                        addCourse(course);
                    }
                    student.enrollInCourse(course);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(new File("Teacher_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" ");
                String teacherID = parts[0];
                String name = parts[1];
                String email = parts[2];
                String dateOfBirth = parts[3];
                String specialization = parts[4];
                Teacher teacher = new Teacher(teacherID, name, email, dateOfBirth, specialization);
                addTeacher(teacher);
                while (scanner.hasNextLine()) {
                    String courseLine = scanner.nextLine().trim();
                    if (courseLine.isEmpty()) {
                        break;
                    }
                    String[] courseParts = courseLine.split(" ");
                    String courseID = courseParts[0];
                    String title = courseParts[1];
                    int credits = Integer.parseInt(courseParts[2]);
                    Course course = null;
                    for (Course c : courses.getAll()) {
                        if (c.getCourseID().equals(courseID)) {
                            course = c;
                            break;
                        }
                    }
                    if (course == null) {
                        course = new Course(courseID, title, credits);
                        addCourse(course);
                    }
                    teacher.assignCourse(course);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        try (Scanner sc = new Scanner(new File("University_data.txt"))) {
            totalStudents = Integer.parseInt(sc.nextLine());
            totalTeachers = Integer.parseInt(sc.nextLine());
            totalCourses = Integer.parseInt(sc.nextLine());
            totaladministrativestaff = Integer.parseInt(sc.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from file: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(new File("Administrative_staff.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" ");
                String staffID = parts[0];
                String name = parts[1];
                String email = parts[2];
                String dateOfBirth = parts[3];
                String role = parts[4];
                String department = parts[5];
                AdministrativeStaff staff = new AdministrativeStaff(staffID, name, email, dateOfBirth, role, department);
                addAdminstaff(staff);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void saveData(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Course_data.txt", false));
            for (Course course : courses.getAll()) {
                writer.print(course.getCourseID() + " ");
                writer.print(course.getTitle() + " ");
                writer.println(course.getCredits() + "");
                Teacher assignedTeacher = course.getAssignedTeacher();
                if (assignedTeacher != null) {
                    writer.print(assignedTeacher.getTeacherID() + " ");
                    writer.print(assignedTeacher.getName() + " ");
                    writer.print(assignedTeacher.getEmail() + " ");
                    writer.print(assignedTeacher.getDateOfBirth() + " ");
                    writer.println(assignedTeacher.getSpecialization() + " ");
                } else {
                    writer.println("null");
                }
                List<Student> enrolledStudents = course.getEnrolledStudents();
                for (Student student : enrolledStudents) {
                    writer.print(student.getStudentID() + " ");
                    writer.print(student.getName() + " ");
                    writer.print(student.getEmail() + " ");
                    writer.print(student.getDateOfBirth() + " ");
                    writer.println(student.getAddress() + " ");
                }
                List<Integer> grades = course.getGrades();
                writer.print("Grades: ");
                for (Integer grade : grades) {
                    writer.print(grade + " ");
                }
                
                
                writer.println("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Teacher_data.txt", false));
            for (Teacher teacher : teachers.getAll()) {
                writer.print(teacher.getTeacherID() + " ");
                writer.print(teacher.getName() + " ");
                writer.print(teacher.getEmail() + " ");
                writer.print(teacher.getDateOfBirth() + " ");
                writer.println(teacher.getSpecialization() + " ");

                List<Course> coursesTaught = teacher.getCoursesTaught();
                for (Course course : coursesTaught) {
                    writer.print(course.getCourseID() + " ");
                    writer.print(course.getTitle() + " ");
                    writer.println(course.getCredits() + " ");
                }
                writer.println();

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Student_data.txt", false));
            for (Student student : students.getAll()) {
            writer.print(student.getStudentID() + " ");
            writer.print(student.getName() + " ");
            writer.print(student.getEmail() + " ");
            writer.print(student.getDateOfBirth() + " ");
            writer.println(student.getAddress() + " ");
            List<Course> enrolledCourses = student.getEnrolledCourses();
            for (Course course : enrolledCourses) {
                writer.print(course.getCourseID() + " ");
                writer.print(course.getTitle() + " ");
                writer.println(course.getCredits() + " ");
            }
            writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("University_data.txt", false));
            writer.println(totalStudents);
            writer.println(totalTeachers);
            writer.println(totalCourses);
            writer.println(totaladministrativestaff);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Administrative_staff.txt", false));
            for (AdministrativeStaff staff : adminstaff.getAll()) {
                writer.print(staff.getStaffID() + " ");
                writer.print(staff.getName() + " ");
                writer.print(staff.getEmail() + " ");
                writer.print(staff.getDateOfBirth() + " ");
                writer.print(staff.getRole() + " ");
                writer.println(staff.getDepartment() + " ");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
