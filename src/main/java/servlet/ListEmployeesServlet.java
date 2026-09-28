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
import utils.Log4J;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list-employees")
public class ListEmployeesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao = new EmployeeDao();
    private DepartmentDao departmentDao = new DepartmentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy tham số action để biết người dùng muốn làm gì
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        try {
            // Xử lý XÓA nếu action là 'delete'
            if ("delete".equals(action) && idStr != null) {
                int id = Integer.parseInt(idStr);
                employeeDao.deleteEmployee(id); // Gọi hàm xóa trong DAO
                // Sau khi xóa thành công, tiếp tục chạy xuống dưới để lấy lại danh sách mới
            }

            // Lấy dữ liệu hiển thị (cho cả trường hợp xem danh sách và sau khi xóa)
            List<Employee> listOfEmployee = employeeDao.findAllEmployee();
            List<Department> listOfDepartment = departmentDao.findAllDepartment();

            request.setAttribute("listOfEmployee", listOfEmployee);
            request.setAttribute("listOfDepartment", listOfDepartment);

            request.getRequestDispatcher("/views/list-employees.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            Log4J.getLogger().error("Error in ListEmployeesServlet: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}