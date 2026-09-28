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
import java.util.List;

@WebServlet("/edit-employee")
public class EditEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao = new EmployeeDao();
    private DepartmentDao departmentDao = new DepartmentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Lấy ID từ tham số URL
            int id = Integer.parseInt(request.getParameter("id"));

            // 2. Lấy thông tin nhân viên và danh sách phòng ban
            Employee employee = employeeDao.findById(id);
            List<Department> listOfDepartment = departmentDao.findAllDepartment();

            // 3. Đẩy dữ liệu sang trang JSP
            request.setAttribute("employee", employee);
            request.setAttribute("listOfDepartment", listOfDepartment);
            request.getRequestDispatcher("/views/edit-employee.jsp").forward(request, response);

        } catch (Exception e) {
            Log4J.getLogger().error("Error loading edit form: " + e.getMessage());
            response.sendRedirect("list-employees");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Thu thập dữ liệu từ form
            int id = Integer.parseInt(request.getParameter("employeeId"));
            String name = request.getParameter("employeeName");
            byte gender = Byte.parseByte(request.getParameter("gender"));
            String dobStr = request.getParameter("dateOfBirth");
            int deptId = Integer.parseInt(request.getParameter("deptId"));

            // 2. Tạo đối tượng Employee và cập nhật
            Employee emp = new Employee();
            emp.setEmployeeId(id);
            emp.setEmployeeName(name);
            emp.setGender(gender);
            emp.setDepartmentId(deptId);

            java.util.Date utilDate = DateUtils.convertStringToDate(dobStr);
            emp.setDateOfBirth(new java.sql.Date(utilDate.getTime()));

            employeeDao.updateEmployee(emp);

            // 3. Quay lại trang danh sách sau khi sửa thành công
            response.sendRedirect(request.getContextPath() + "/list-employees");

        } catch (Exception e) {
            Log4J.getLogger().error("Error updating employee: " + e.getMessage());
            doGet(request, response);
        }
    }
}