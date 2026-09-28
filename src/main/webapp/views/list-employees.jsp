<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>List Employees</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>List of Employees</h2>
        <a href="${pageContext.request.contextPath}/add-employee" class="btn btn-primary">
            <i class="fa fa-plus"></i> Add New Employee
        </a>
    </div>

    <table id="employeeTable" class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Employee Name</th>
            <th>Gender</th>
            <th>Date of birth</th>
            <th>Department Name</th>
            <th style="width: 150px;">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listOfEmployee}" var="employee">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.employeeName}</td>
                <td>${employee.gender == 1 ? 'Male' : 'Female'}</td>
                <td>${employee.dateOfBirth}</td>
                <td>
                    <c:forEach items="${listOfDepartment}" var="department">
                        <c:if test="${employee.departmentId == department.departmentId}">
                            ${department.departmentName}
                        </c:if>
                    </c:forEach>
                </td>
                <td class="text-center">

                        <%-- Nút Sửa --%>
                    <a href="${pageContext.request.contextPath}/edit-employee?id=${employee.employeeId}" class="btn btn-warning btn-sm">
                        <i class="fa fa-edit"></i> Edit
                    </a>

                        <%-- Nút Xóa (Gửi action=delete về cùng Servlet này) --%>
                    <a href="list-employees?action=delete&id=${employee.employeeId}"
                       class="btn btn-danger btn-sm"
                       title="Delete"
                       onclick="return confirm('Are you sure you want to delete employee: ${employee.employeeName}?');">
                        <i class="fa fa-trash"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>

<script>
    $(document).ready(function() {
        $('#employeeTable').DataTable({
            "pagingType": "full_numbers",
            "pageLength": 5,
            "lengthMenu": [5, 10, 20, 50],
            "language": {
                "search": "Filter records:",
            }
        });
    });
</script>

<%@ include file="footer.jsp" %>
</body>
</html>