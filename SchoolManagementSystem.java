// import java.util.List;

public class SchoolManagementSystem {
    public static void main(String[] args) {
        University university = new University();
        university.loadData("data.txt");

        university.assignGradeToStudent("S1001", "C1001", 60);
        

        university.saveData("data.txt");
        }
    }
