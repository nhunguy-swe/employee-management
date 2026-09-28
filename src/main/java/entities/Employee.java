package entities;

import java.sql.Date;

public class Employee {
    private int employeeId;
    private String employeeName;
    private byte gender;
    private Date dateOfBirth;
    private int departmentId;

    public Employee() {}

    public Employee(int employeeId, String employeeName, byte gender, Date dateOfBirth, int departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.departmentId = departmentId;
    }

    // Getter and Setter
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee [id=" + employeeId + ", name=" + employeeName + ", gender=" + gender + "]";
    }
}
