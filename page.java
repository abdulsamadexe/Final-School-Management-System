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

        mainPanel.add(createFormPanel(new String[]{"Course ID","Course Title",  "Course Credits"}, new String[]{"courseId","string", "int"},"AddCourse"), "AddCourse");
        mainPanel.add(createFormPanel(new String[]{"Teacher ID", "Course ID"}, new String[]{"teacherId", "courseId"}, "AssignTeacherToCourse"), "AssignTeacherToCourse");
        mainPanel.add(createFormPanel(new String[]{"Student ID", "Course ID"}, new String[]{"studentId", "courseId"}, "EnrollStudentInCourse"), "EnrollStudentInCourse");
        mainPanel.add(createFormPanel(new String[]{"Student ID", "Course ID"}, new String[]{"studentId", "courseId"}, "RemoveStudentFromCourse"), "RemoveStudentFromCourse");
        mainPanel.add(createFormPanel(new String[]{"Course ID"}, new String[]{"courseId"}, "CalculateAverageGrade"), "CalculateAverageGrade");
        mainPanel.add(createFormPanel(new String[]{"Teacher ID", "Teacher Name", "Email", "DOB", "Specialization"}, new String[]{"teacherId", "string", "email", "dob", "string"}, "AddTeacher"), "AddTeacher");
        mainPanel.add(createFormPanel(new String[]{"Teacher ID", "Course ID"}, new String[]{"teacherId", "courseId"}, "AssignCourseToTeacher"), "AssignCourseToTeacher");
        mainPanel.add(createFormPanel(new String[]{"Student ID", "Student Name", "Email", "DOB", "Address"}, new String[]{"studentId", "string", "email", "dob", "string"}, "AddStudent"), "AddStudent");
        mainPanel.add(createFormPanel(new String[]{"Student ID","Course ID", "Grade"}, new String[]{"studentId","courseId", "int"}, "AssignGradeToStudent"), "AssignGradeToStudent");
        mainPanel.add(createReportPanel(), "GenerateReport");
        frame.add(mainPanel, BorderLayout.CENTER);

        createMenuBar(frame);

        frame.setVisible(true);
    }

    private JPanel createFormPanel(String[] labels, String[] types,String func) {
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
        panel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;
                StringBuilder inputData = new StringBuilder();
                for (int i = 0; i < labels.length; i++) {
                    String input = textFields[i].getText().trim();
                    if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, labels[i] + " cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
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
                        university.addCourse(new Course(textFields[0].getText(), textFields[1].getText(), Integer.parseInt(textFields[2].getText())));
                    } else if (func.equals("AddTeacher")) {
                        university.addTeacher(new Teacher(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText()));
                    } else if (func.equals("AddStudent")) {
                        university.addStudent(new Student(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), textFields[4].getText()));
                    } 

                    else if (  func.equals("AssignTeacherToCourse") || func.equals("AssignCourseToTeacher")) 
                      {  university.assignTeacherToCourse(textFields[0].getText(), textFields[1].getText());
                    }
                   else if (func.equals("EnrollStudentInCourse")) {
                      university.enrollStudentInCourse(textFields[0].getText(), textFields[1].getText());
                    }

                    // else if (func.equals("EnrollStudentInCourse") {
                    //     university.removeStudentFromCourse(textFields[0].getText(), textFields[1].getText());
                    // }
                    // else if (func.equals("RemoveStudentFromCourse")) {
                    //     university.calculateAverageGrade(textFields[0].getText());
                    // }
                    //  else if (func.equals("AssignGradeToStudent")) {
                    //     university.assignGradeToStudent(textFields[0].getText(),textFields[1].getText(), Integer.parseInt(textFields[2].getText()));
                    // }
                university.saveData(null);
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
        coursesReportButton.setFont(new Font("Arial", Font.BOLD, 20));
        teachersReportButton.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(studentsReportButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(coursesReportButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(teachersReportButton, gbc);

        studentsReportButton.addActionListener(e -> showStudentsReport());
        coursesReportButton.addActionListener(e -> showCoursesReport());
        teachersReportButton.addActionListener(e -> showTeachersReport());

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
                return input.matches("[a-zA-Z ]+"); 
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

        addCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        assignTeacherToCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        enrollStudentInCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        removeStudentFromCourse.setFont(new Font("Arial", Font.PLAIN, 20)); 
        calculateAverageGrade.setFont(new Font("Arial", Font.PLAIN, 20)); 

        courseMenu.add(addCourse);
        courseMenu.add(assignTeacherToCourse);
        courseMenu.add(enrollStudentInCourse);
        courseMenu.add(removeStudentFromCourse);
        courseMenu.add(calculateAverageGrade);

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

        addStudent.setFont(new Font("Arial", Font.PLAIN, 20)); 
        assignGradeToStudent.setFont(new Font("Arial", Font.PLAIN, 20)); 

        studentMenu.add(addStudent);
        studentMenu.add(assignGradeToStudent);

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

        addCourse.addActionListener(e -> showPanel("AddCourse", "Add Course"));
        assignTeacherToCourse.addActionListener(e -> showPanel("AssignTeacherToCourse", "Assign Teacher to Course"));
        enrollStudentInCourse.addActionListener(e -> showPanel("EnrollStudentInCourse", "Enroll Student in Course"));
        removeStudentFromCourse.addActionListener(e -> showPanel("RemoveStudentFromCourse", "Remove Student from Course"));
        calculateAverageGrade.addActionListener(e -> showPanel("CalculateAverageGrade", "Calculate Average Grade"));
        addTeacher.addActionListener(e -> showPanel("AddTeacher", "Add Teacher"));
        assignCourseToTeacher.addActionListener(e -> showPanel("AssignCourseToTeacher", "Assign Course to Teacher"));
        addStudent.addActionListener(e -> showPanel("AddStudent", "Add Student"));
        assignGradeToStudent.addActionListener(e -> showPanel("AssignGradeToStudent", "Assign Grade to Student"));
        addStaff.addActionListener(e -> showPanel("GenerateReport", "Generate Report"));
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
