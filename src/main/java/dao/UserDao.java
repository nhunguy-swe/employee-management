package dao;

import entities.User;
import utils.DBUtils;

import java.sql.*;

public class UserDao {

    public boolean registerUser(User user) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Users (first_name, last_name, email, user_name, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getPassword());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean login(User user) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Users WHERE user_name = ? AND password = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Nếu tìm thấy bản ghi, cập nhật thông tin đầy đủ cho đối tượng user
                    user.setUserId(rs.getInt("user_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    return true;
                }
            }
        }
        return false;
    }
}
