package servlet;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entities.Department;
import entities.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DateUtils;
import utils.Log4J;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Servlet xử lý chức năng thêm mới nhân viên.
 * Được ánh xạ với URL pattern /add-employee.
 */
@WebServlet("/add-employee")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DepartmentDao departmentDao = new DepartmentDao();
    private EmployeeDao employeeDao = new EmployeeDao();

    /**
     * Phương thức doGet: Hiển thị form thêm mới nhân viên.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách phòng ban từ DB để hiển thị lên dropdown
            List<Department> listOfDepartment = departmentDao.findAllDepartment();
            request.setAttribute("listOfDepartment", listOfDepartment);

            // Khởi tạo object rỗng để tránh lỗi khi JSP truy cập thuộc tính null
            Employee emptyEmp = new Employee();
            emptyEmp.setGender((byte) 1); // Mặc định là Male
            request.setAttribute("employee", emptyEmp);

            // Trả về trang giao diện add-employee.jsp
            request.getRequestDispatcher("/views/add-employee.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            Log4J.getLogger().error("Error loading department list: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Phương thức doPost: Xử lý dữ liệu khi người dùng nhấn nút "Add Employee".
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Thu thập dữ liệu từ request parameters
        String name = request.getParameter("employeeName");
        String genderStr = request.getParameter("gender");
        String dobStr = request.getParameter("dateOfBirth");
        String deptIdStr = request.getParameter("deptId");

        try {
            // 2. Chuyển đổi và kiểm tra dữ liệu
            int deptId = Integer.parseInt(deptIdStr);
            byte gender = Byte.parseByte(genderStr);

            // Chuyển đổi chuỗi ngày tháng sang java.sql.Date
            java.util.Date utilDate = DateUtils.convertStringToDate(dobStr);
            java.sql.Date sqlDate = (utilDate != null) ? new java.sql.Date(utilDate.getTime()) : null;

            // 3. Tạo đối tượng Employee và gán giá trị
            Employee employee = new Employee();
            employee.setEmployeeName(name);
            employee.setGender(gender);
            employee.setDateOfBirth(sqlDate);
            employee.setDepartmentId(deptId);

            // 4. Gọi DAO để lưu vào CSDL
            employeeDao.addEmployee(employee);

            // 5. Chuyển hướng (Redirect) sang trang danh sách để cập nhật kết quả
            // Sử dụng Redirect thay vì Forward để tránh lỗi lặp lại dữ liệu khi user nhấn F5
            response.sendRedirect(request.getContextPath() + "/list-employees");

        } catch (ParseException | NumberFormatException e) {
            Log4J.getLogger().error("Data conversion error: " + e.getMessage());
            request.setAttribute("message", "Invalid data format. Please check again.");
            doGet(request, response); // Quay lại trang nhập liệu
        } catch (ClassNotFoundException | SQLException e) {
            Log4J.getLogger().error("Database error occurred: " + e.getMessage());
            request.setAttribute("message", "Database connection error.");
            doGet(request, response);
        }
    }
}