package dao;

import entities.Employee;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    /**
     * Thêm mới nhân viên
     */
    public void addEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Employee (employee_name, gender, date_of_birth, department_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getEmployeeName());
            ps.setByte(2, emp.getGender());
            ps.setDate(3, emp.getDateOfBirth());
            ps.setInt(4, emp.getDepartmentId());

            ps.executeUpdate();
        }
    }

    /**
     * Lấy danh sách tất cả nhân viên
     */
    public List<Employee> findAllEmployee() throws ClassNotFoundException, SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setEmployeeName(rs.getString("employee_name"));
                emp.setGender(rs.getByte("gender"));
                emp.setDateOfBirth(rs.getDate("date_of_birth"));
                emp.setDepartmentId(rs.getInt("department_id"));
                list.add(emp);
            }
        }
        return list;
    }

    /**
     * Xóa nhân viên theo ID
     */
    public void deleteEmployee(int id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Employee WHERE employee_id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Tìm kiếm nhân viên theo ID (Dùng cho chức năng Edit)
     */
    public Employee findById(int id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Employee WHERE employee_id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    emp.setEmployeeName(rs.getString("employee_name"));
                    emp.setGender(rs.getByte("gender"));
                    emp.setDateOfBirth(rs.getDate("date_of_birth"));
                    emp.setDepartmentId(rs.getInt("department_id"));
                    return emp;
                }
            }
        }
        return null;
    }

    /**
     * Cập nhật thông tin nhân viên
     */
    public void updateEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Employee SET employee_name = ?, gender = ?, date_of_birth = ?, department_id = ? "
                + "WHERE employee_id = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getEmployeeName());
            ps.setByte(2, emp.getGender());
            ps.setDate(3, emp.getDateOfBirth());
            ps.setInt(4, emp.getDepartmentId());
            ps.setInt(5, emp.getEmployeeId());

            ps.executeUpdate();
        }
    }
}