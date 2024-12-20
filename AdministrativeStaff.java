import java.util.*;
public class AdministrativeStaff extends Person implements Reportable {
    private String staffID;
    private String role;
    private String department;

    public AdministrativeStaff(String staffID, String name, String email, String dateOfBirth, String role, String department) {
        super(name, email, dateOfBirth);
        this.staffID = staffID;
        this.role = role;
        this.department = department;
    }

    public String getStaffID(){
        return staffID;
    }

    public String getRole(){
        return role;
    }

    public String getDepartment(){
        return department;
    }

    public String generateReport(){
        System.out.println("Generating report");
        return "Report generated";
    }
    public String generateReport(List<Person> people) {
        StringBuilder report = new StringBuilder();
        for (Person person : people) {
            report.append(person.displayDetails()).append("\n");
        }
        return report.toString();
    }

    @Override
    public String displayDetails() {
        return "Staff: " + staffID + ", Name: " + name + ", Role: " + role + ", Department: " + department;
    }
    @Override
    public void exportToFile(){
        System.out.println("Exporting to file");

    }
    
   public boolean isSame(AdministrativeStaff ad){
       return this.staffID.equals(ad.getStaffID()) && this.role.equals(ad.getRole()) && this.department.equals(ad.getDepartment()) && this.dateOfBirth.equals(ad.getDateOfBirth()) && this.email.equals(ad.getEmail());
   }

    
}
