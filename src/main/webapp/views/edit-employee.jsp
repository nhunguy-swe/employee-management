<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="icon" href="data:,"> </head>
<body class="bg-light">

<%@ include file="header.jsp" %>

<div class="container bg-white p-4 mt-5 shadow-sm">
    <h2 class="mb-4">Update Employee Information</h2>

    <form action="${pageContext.request.contextPath}/edit-employee" method="POST">
        <input type="hidden" name="employeeId" value="${employee.employeeId}">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" name="employeeName" value="${employee.employeeName}" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2">Gender:</label>
            <div class="col-sm-6">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" value="1" ${employee.gender == 1 ? 'checked' : ''}>
                    <label class="form-check-label">Male</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" value="0" ${employee.gender == 0 ? 'checked' : ''}>
                    <label class="form-check-label">Female</label>
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Date of birth:</label>
            <div class="col-sm-6">
                <input type="date" class="form-control" name="dateOfBirth" value="${employee.dateOfBirth}" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Department:</label>
            <div class="col-sm-6">
                <select class="form-control" name="deptId" required>
                    <c:forEach items="${listOfDepartment}" var="dept">
                        <option value="${dept.departmentId}" ${employee.departmentId == dept.departmentId ? 'selected' : ''}>
                                ${dept.departmentName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group row mt-4">
            <div class="col-sm-2"></div>
            <div class="col-sm-6">
                <button type="submit" class="btn btn-primary px-5">Update Employee</button>
                <a href="list-employees" class="btn btn-secondary ml-2">Back to List</a>
            </div>
        </div>
    </form>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>