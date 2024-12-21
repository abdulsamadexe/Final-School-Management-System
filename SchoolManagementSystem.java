// import java.util.List;

public class SchoolManagementSystem {
    public static void main(String[] args) {
        University university = new University();
        university.loadData("data.txt");

        for (int i = 0; i < university.getStudents().size(); i++) {
            System.out.println(university.getStudents().get(i).getName());
        }

        university.saveData("data.txt");
        }
    }
