package dao;

import entities.Department;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    /**
     * Lấy tất cả danh sách phòng ban
     */
    public List<Department> findAllDepartment() throws ClassNotFoundException, SQLException {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM Department";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Department dept = new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                );
                list.add(dept);
            }
        }
        return list;
    }

    /**
     * Tìm kiếm phòng ban theo ID
     * Hữu ích khi bạn chỉ có department_id từ bảng Employee và muốn lấy object Department tương ứng
     */
    public Department findById(int id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Department WHERE department_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Department(
                            rs.getInt("department_id"),
                            rs.getString("department_name")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Thêm mới một phòng ban
     */
    public boolean addDepartment(String deptName) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Department (department_name) VALUES (?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, deptName);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Cập nhật thông tin phòng ban
     */
    public boolean updateDepartment(Department dept) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Department SET department_name = ? WHERE department_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dept.getDepartmentName());
            ps.setInt(2, dept.getDepartmentId());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Xóa phòng ban
     * Lưu ý: Nếu có nhân viên thuộc phòng ban này, SQL sẽ báo lỗi khóa ngoại (Foreign Key)
     */
    public boolean deleteDepartment(int id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Department WHERE department_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}