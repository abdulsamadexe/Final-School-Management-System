import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class page {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JLabel headerLabel;
    private University university;

    public page(University university) {
        this.university = university;
        frame = new JFrame("Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600); 
        frame.setLocationRelativeTo(null); 

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout); 

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#F0EBD8"));
        headerLabel = new JLabel("Add Course");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30)); 
        headerLabel.setForeground(Color.decode("#1D2D44"));
        headerPanel.add(headerLabel);

        frame.add(headerPanel, BorderLayout.NORTH);

        mainPanel.add(createFormPanel(new String[]{"Course ID(C001)","Course Title",  "Course Credits"}, new String[]{"courseId","string", "int"},"AddCourse",new String[]{"i.e, C001","i.e, Data Structures","i.e, 4"}), "AddCourse");
        mainPanel.add(createFormPanel(new String[]{"Teacher ID", "Course ID"}, new String[]{"teacherId", "courseId"}, "AssignTeacherToCourse", new String[]{"i.e, T001","i.e, C001"}), "AssignTeacherToCourse");
        mainPanel.add(createFormPanel(new String[]{"Student ID", "Course ID"}, new String[]{"studentId", "courseId"}, "EnrollStudentInCourse", new String[]{"i.e, S001","i.e, C001"}), "EnrollStudentInCourse");
        mainPanel.add(createFormPanel(new String[]{"Student ID", "Course ID"}, new String[]{"studentId", "courseId"}, "RemoveStudentFromCourse", new String[]{"i.e, S001","i.e, C001"}), "RemoveStudentFromCourse");
        mainPanel.add(createFormPanel(new String[]{"Course ID"}, new String[]{"courseId"}, "CalculateAverageGrade", new String[]{"i.e, C001"}), "CalculateAverageGrade");
        mainPanel.add(createFormPanel(new String[]{"Teacher ID", "Teacher Name", "Email", "DOB", "Specialization"}, new String[]{"teacherId", "string", "email", "dob", "string"}, "AddTeacher", new String[]{"i.e, T001","i.e, Ali Hassan","i.e, example@gmail.com","i.e, 01-01-1990","i.e, Data Structures"}), "AddTeacher");
        mainPanel.add(createFormPanel(new String[]{"Teacher ID", "Course ID"}, new String[]{"teacherId", "courseId"}, "AssignCourseToTeacher", new String[]{"i.e, T001","i.e, C001"}), "AssignCourseToTeacher");
        mainPanel.add(createFormPanel(new String[]{"Student ID", "Student Name", "Email", "DOB", "Address"}, new String[]{"studentId", "string", "email", "dob", "alnum"}, "AddStudent", new String[]{"i.e, S001","i.e, Ali Hassan","i.e, example@gmail.com","i.e, 11-01-2000","i.e, 123 Main St"}), "AddStudent");
        mainPanel.add(createFormPanel(new String[]{"Student ID","Course ID", "Grade"}, new String[]{"studentId","courseId", "int"}, "AssignGradeToStudent", new String[]{"i.e, S001","i.e, C001","i.e, 90"}), "AssignGradeToStudent");
        mainPanel.add(createFormPanel(new String[]{"Student Name"}, new String[]{"string"}, "SearchStudentByName", new String[]{"i.e, Ali Hassan"}), "SearchStudentByName");
        mainPanel.add(createFormPanel(new String[]{"Course Credits"}, new String[]{"int"}, "SearchCourseByCredits", new String[]{"i.e, 4"}), "SearchCourseByCredits");
        mainPanel.add(createReportPanel(), "GenerateReport");
        frame.add(mainPanel, BorderLayout.CENTER);

        createMenuBar(frame);

        frame.setVisible(true);
    }

    private JPanel createFormPanel(String[] labels, String[] types,String func,String[] format) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#F0EBD8")); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10); 

        Font labelFont = new Font("Arial", Font.PLAIN, 20);

        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(labelFont);
            label.setForeground(Color.decode("#0D1321")); 
            JTextField textField = new JTextField(15); 
            textField.setPreferredSize(new Dimension(150, 30)); 
            textFields[i] = textField;
            // set text of textfield to the format
            textField.setFont(new Font("Arial", Font.PLAIN, 20));
            textField.setForeground(Color.GRAY);
            textField.setText(format[i]);
            final int index = i;
            textField.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (textField.getText().equals(format[index])) {
                        textField.setText("");
                        textField.setForeground(Color.BLACK);
                    }
                }            
                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (textField.getText().isEmpty()) {
                        textField.setForeground(Color.GRAY);
                        textField.setText(format[index]);
                    }
                }
            });


            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(textField, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20)); 
        submitButton.setBackground(Color.decode("#1D2D44")); 
        submitButton.setForeground(Color.WHITE); 
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(submitButton, gbc);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;
                StringBuilder inputData = new StringBuilder();
                for (int i = 0; i < labels.length; i++) {
                    String input = textFields[i].getText().trim();
                    if (input.isEmpty() || input.equals(format[i])) { // Check for placeholder
                        JOptionPane.showMessageDialog(frame, labels[i] + " cannot be empty. Please type according to the format shown", "Input Error", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                        break;
                    }
                    
                    if (!validateInput(input, types[i])) {
                        JOptionPane.showMessageDialog(frame, "Invalid input for " + labels[i], "Input Error", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                        break;
                    }
                    if (types[i].equals("int")) {
                        try {
                            Integer.parseInt(input);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Invalid number format for " + labels[i], "Input Error", JOptionPane.ERROR_MESSAGE);
                            valid = false;
                            break;
                        }
                    }
                    inputData.append(labels[i]).append(": ").append(input).append("\n");
                }
                if (valid) {
                    System.out.println(inputData.toString());
                    if (func.equals("AddCourse")) {
                        int a = university.addCourse(new Course(textFields[0].getText(), textFields[1].getText().replace(" ", "_"), Integer.parseInt(textFields[2].getText())));
                        if(a == -1){
                            JOptionPane.showMessageDialog(frame, "Course already exists", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (func.equals("AddTeacher")) {
                        int a = university.addTeacher(new Teacher(textFields[0].getText(), textFields[1].getText().replace(" ", "_"), textFields[2].getText(), textFields[3].getText(), textFields[4].getText().replace(" ", "_")));
                        if(a == -1){
                            JOptionPane.showMessageDialog(frame, "Teacher already exists", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (func.equals("AddStudent")) {
                        int a = university.addStudent(new Student(textFields[0].getText(), textFields[1].getText().replace(" ", "_"), textFields[2].getText(), textFields[3].getText(), textFields[4].getText().replace(" ", "_")));
                        if(a == -1){
                            JOptionPane.showMessageDialog(frame, "Student already exists", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } 

                    else if (  func.equals("AssignTeacherToCourse") || func.equals("AssignCourseToTeacher")) 
                      {  int a = university.assignTeacherToCourse(textFields[0].getText(), textFields[1].getText());
                        if(a == -1){
                            JOptionPane.showMessageDialog(frame, "Teacher or Course does not exist", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(a == 1){
                            JOptionPane.showMessageDialog(frame, "Teacher is already assigned to the course", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    
                    }
                   else if (func.equals("EnrollStudentInCourse")) {
                      int a = university.enrollStudentInCourse(textFields[0].getText(), textFields[1].getText());
                      if(a == -1){
                        JOptionPane.showMessageDialog(frame, "Student or Course does not exist", "Input Error", JOptionPane.ERROR_MESSAGE);
                      }
                      else if(a == 1){
                        JOptionPane.showMessageDialog(frame, "Student is already enrolled in the course", "Input Error", JOptionPane.ERROR_MESSAGE);
                      }
                    }

                    else if (func.equals("RemoveStudentFromCourse")) {
                        int a =university.removeStudentFromCourse(textFields[0].getText(), textFields[1].getText());
                        if(a == -1){
                            JOptionPane.showMessageDialog(frame, "Student or Course does not exist", "Input Error", JOptionPane.ERROR_MESSAGE);
                          }
                        else if(a == 1){
                            JOptionPane.showMessageDialog(frame, "Student is not enrolled in the course", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    // else if (func.equals("SearchStudentByName")) {
                        // String studentName = textFields[0].getText().replace(" ", "_");
                        // java.util.List<String> students = university.searchStudentByName(studentName);
                        // if (students.isEmpty()) {
                            // JOptionPane.showMessageDialog(frame, "No students found with the name " + studentName, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        // } else {
                        //     StringBuilder result = new StringBuilder("Students found:\n");
                        //     for (String student : students) {
                        //         result.append(student).append("\n");
                        //     }
                            // JOptionPane.showMessageDialog(frame, result.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        // }
                    // }

                    // else if (func.equals("SearchCourseByCredits")) {
                        // int credits = Integer.parseInt(textFields[0].getText());
                        // java.util.List<String> courses = university.searchCourseByCredits(credits);
                        // if (courses.isEmpty()) {
                        //     JOptionPane.showMessageDialog(frame, "No courses found with " + credits + " credits", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        // } else {
                        //     StringBuilder result = new StringBuilder("Courses found:\n");
                        //     for (String course : courses) {
                        //         result.append(course).append("\n");
                        //     }
                        //     JOptionPane.showMessageDialog(frame, result.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        // }
                    // }

                    //  else if (func.equals("AssignGradeToStudent")) {
                    //     university.assignGradeToStudent(textFields[0].getText(),textFields[1].getText(), Integer.parseInt(textFields[2].getText()));
                    // }
                    // else if (func.equals("CalculateAverageGrade")) {
                    //     university.calculateAverageGrade(textFields[0].getText());
                    // }
                university.saveData(null);
                //empty the textfields
                for (int i = 0; i < labels.length; i++) {
                    textFields[i].setText(format[i]); // Set default placeholder text
                    textFields[i].setForeground(Color.GRAY); // Ensure placeholder text is shown in gray
                }
                
            }
        }
        });

        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        
        panel.setBackground(Color.decode("#F0EBD8"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);

        JButton studentsReportButton = new JButton("Students Report");
        JButton coursesReportButton = new JButton("Courses Report");
        JButton teachersReportButton = new JButton("Teachers Report");

        studentsReportButton.setFont(new Font("Arial", Font.BOLD, 20));
        studentsReportButton.setFocusPainted(false);
        studentsReportButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        coursesReportButton.setFont(new Font("Arial", Font.BOLD, 20));
        coursesReportButton.setFocusPainted(false);
        coursesReportButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        teachersReportButton.setFont(new Font("Arial", Font.BOLD, 20));
        teachersReportButton.setFocusPainted(false);
        teachersReportButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(studentsReportButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(coursesReportButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(teachersReportButton, gbc);

        studentsReportButton.addActionListener(_ -> showStudentsReport());
        coursesReportButton.addActionListener(_ -> showCoursesReport());
        teachersReportButton.addActionListener(_ -> showTeachersReport());

        return panel;
    }

    private void showStudentsReport() {
        JFrame reportFrame = new JFrame("Students Report");
        reportFrame.setSize(400, 400);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setLayout(new BorderLayout());
    
        JLabel heading = new JLabel("Students Report", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBorder(null); // Remove border
        reportFrame.add(heading, BorderLayout.NORTH);
    
        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(Color.decode("#F0EBD8"));
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        // Assuming university.getStudentsReport() returns a List<String> of student details
        // for (String student : university.getStudentsReport()) {
        //     JLabel studentLabel = new JLabel(student);
        //     studentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        //     reportPanel.add(studentLabel);
        // }
    
        JScrollPane scrollPane = new JScrollPane(reportPanel);
        reportFrame.add(scrollPane, BorderLayout.CENTER);
    
        reportFrame.getContentPane().setBackground(Color.decode("#F0EBD8")); // Set background color
        reportFrame.setVisible(true);
    }
    
    private void showCoursesReport() {
        JFrame reportFrame = new JFrame("Courses Report");
        reportFrame.setSize(400, 400);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setLayout(new BorderLayout());
    
        JLabel heading = new JLabel("Courses Report", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBorder(null); // Remove border
        reportFrame.add(heading, BorderLayout.NORTH);
    
        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(Color.decode("#F0EBD8"));
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        // Assuming university.getCoursesReport() returns a List<String> of course details
        // for (String course : university.getCoursesReport()) {
        //     JLabel courseLabel = new JLabel(course);
        //     courseLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        //     reportPanel.add(courseLabel);
        // }
    
        JScrollPane scrollPane = new JScrollPane(reportPanel);
        reportFrame.add(scrollPane, BorderLayout.CENTER);
    
        reportFrame.getContentPane().setBackground(Color.decode("#F0EBD8")); // Set background color
        reportFrame.setVisible(true);
    }
    
    private void showTeachersReport() {
        JFrame reportFrame = new JFrame("Teachers Report");
        reportFrame.setSize(400, 400);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setLayout(new BorderLayout());
    
        JLabel heading = new JLabel("Teachers Report", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBorder(null); // Remove border
        reportFrame.add(heading, BorderLayout.NORTH);
    
        JPanel reportPanel = new JPanel();
        reportPanel.setBackground(Color.decode("#F0EBD8"));
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        // Assuming university.getTeachersReport() returns a List<String> of teacher details
        // for (String teacher : university.getTeachersReport()) {
        //     JLabel teacherLabel = new JLabel(teacher);
        //     teacherLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        //     reportPanel.add(teacherLabel);
        // }
    
        // JScrollPane scrollPane = new JScrollPane(reportPanel);
        // reportFrame.add(scrollPane, BorderLayout.CENTER);
    
        reportFrame.getContentPane().setBackground(Color.decode("#F0EBD8")); // Set background color
        reportFrame.setVisible(true);
    }
    

    private boolean validateInput(String input, String type) {
        if (type.equals("string") || type.equals("alnum")) {
            input = input.replace(" ", "_"); // Replace spaces with underscores for string and alnum only
        }
        switch (type) {
            case "courseId":
                return input.matches("^C\\d+");  
            case "studentId":
                return input.matches("^S\\d+"); 
            case "teacherId":
                return input.matches("^T\\d+"); 
            case "email":
                return input.matches(".*@gmail.com$");
            case "dob":
                return input.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$");
            case "int":
                return input.matches("\\d+"); 
            case "string":
                return input.matches("[a-zA-Z_]+");
            case "alnum":
                return input.matches("[a-zA-Z0-9_]+");
            default:
                return false;
        }
    }

    private void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu courseMenu = new JMenu("Course");
        courseMenu.setFont(new Font("Arial", Font.PLAIN, 20)); 
        JMenuItem addCourse = new JMenuItem("Add Course");
        JMenuItem assignTeacherToCourse = new JMenuItem("Assign Teacher to Course");
        JMenuItem enrollStudentInCourse = new JMenuItem("Enroll Student in Course");
        JMenuItem removeStudentFromCourse = new JMenuItem("Remove Student from Course");
        JMenuItem calculateAverageGrade = new JMenuItem("Calculate Average Grade");
        JMenuItem searchCourseByCredits = new JMenuItem("Search Course by Credits");

        addCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        assignTeacherToCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        enrollStudentInCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        removeStudentFromCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        calculateAverageGrade.setFont(new Font("Arial", Font.PLAIN, 20)); 
        searchCourseByCredits.setFont(new Font("Arial", Font.PLAIN, 20));

        courseMenu.add(addCourse);
        courseMenu.add(assignTeacherToCourse);
        courseMenu.add(enrollStudentInCourse);
        courseMenu.add(removeStudentFromCourse);
        courseMenu.add(calculateAverageGrade);
        courseMenu.add(searchCourseByCredits);

        JMenu teacherMenu = new JMenu("Teacher");
        teacherMenu.setFont(new Font("Arial", Font.PLAIN, 20)); 
        JMenuItem addTeacher = new JMenuItem("Add Teacher");
        JMenuItem assignCourseToTeacher = new JMenuItem("Assign Course to Teacher");

        addTeacher.setFont(new Font("Arial", Font.PLAIN, 20)); 
        assignCourseToTeacher.setFont(new Font("Arial", Font.PLAIN, 20)); 

        teacherMenu.add(addTeacher);
        teacherMenu.add(assignCourseToTeacher);

        JMenu studentMenu = new JMenu("Student");
        studentMenu.setFont(new Font("Arial", Font.PLAIN, 20)); 
        JMenuItem addStudent = new JMenuItem("Add Student");
        JMenuItem assignGradeToStudent = new JMenuItem("Assign Grade to Student");
        JMenuItem searchStudentByName = new JMenuItem("Search Student by Name");

        addStudent.setFont(new Font("Arial", Font.PLAIN, 20)); 
        assignGradeToStudent.setFont(new Font("Arial", Font.PLAIN, 20)); 
        searchStudentByName.setFont(new Font("Arial", Font.PLAIN, 20));

        studentMenu.add(addStudent);
        studentMenu.add(assignGradeToStudent);
        studentMenu.add(searchStudentByName);

        menuBar.add(courseMenu);
        menuBar.add(teacherMenu);
        menuBar.add(studentMenu);

        JMenu staff = new JMenu("Administrative Staff");
        staff.setFont(new Font("Arial", Font.PLAIN, 20));
        JMenuItem addStaff = new JMenuItem("Generate Report");
        addStaff.setFont(new Font("Arial", Font.PLAIN, 20));
        staff.add(addStaff);
        menuBar.add(staff);

        frame.setJMenuBar(menuBar);

        addCourse.addActionListener(_ -> showPanel("AddCourse", "Add Course"));
        assignTeacherToCourse.addActionListener(_ -> showPanel("AssignTeacherToCourse", "Assign Teacher to Course"));
        enrollStudentInCourse.addActionListener(_ -> showPanel("EnrollStudentInCourse", "Enroll Student in Course"));
        removeStudentFromCourse.addActionListener(_ -> showPanel("RemoveStudentFromCourse", "Remove Student from Course"));
        calculateAverageGrade.addActionListener(_ -> showPanel("CalculateAverageGrade", "Calculate Average Grade"));
        addTeacher.addActionListener(_ -> showPanel("AddTeacher", "Add Teacher"));
        assignCourseToTeacher.addActionListener(_ -> showPanel("AssignCourseToTeacher", "Assign Course to Teacher"));
        addStudent.addActionListener(_ -> showPanel("AddStudent", "Add Student"));
        assignGradeToStudent.addActionListener(_ -> showPanel("AssignGradeToStudent", "Assign Grade to Student"));
        addStaff.addActionListener(_ -> showPanel("GenerateReport", "Generate Report"));
        searchStudentByName.addActionListener(_ -> showPanel("SearchStudentByName", "Search Student by Name"));
        searchCourseByCredits.addActionListener(_ -> showPanel("SearchCourseByCredits", "Search Course by Credits"));
    }

    private void showPanel(String panelName, String labelText) {
        cardLayout.show(mainPanel, panelName);
        headerLabel.setText(labelText);
    }

    public static void main(String[] args) {
        University university = new University();
        university.loadData(null);
        new page(university);
    }
}