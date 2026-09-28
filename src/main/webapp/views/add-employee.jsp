<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add an Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body { background-color: white; }
        .main-container { margin-top: 50px; padding-bottom: 100px; }
        .form-title { font-size: 36px; font-weight: 500; margin-bottom: 5px; }
        .success-msg { color: blue; margin-bottom: 20px; font-size: 18px; }
        label { font-weight: normal; color: #333; }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<%-- Container bọc ngoài để file JS có thể tìm và thay thế nội dung --%>
<div class="container mt-4">
    <h1 style="font-size: 32px; font-weight: 500;">Add an Employee</h1>

    <%-- Form gửi dữ liệu trực tiếp đến Servlet bằng phương thức POST --%>
    <form action="${pageContext.request.contextPath}/add-employee" method="POST">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name:</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="employeeName" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2">Gender:</label>
            <div class="col-sm-7">
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="gender" value="1" checked> Male
                    </label>
                </div>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="gender" value="0"> Female
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Date of birth:</label>
            <div class="col-sm-7">
                <input type="date" class="form-control" name="dateOfBirth" required>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Department:</label>
            <div class="col-sm-7">
                <select class="form-control" name="deptId" required>
                    <option value="">-- Select Department --</option>
                    <c:forEach items="${listOfDepartment}" var="department">
                        <option value="${department.departmentId}">${department.departmentName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-2"></div>
            <div class="col-sm-7">
                <%-- Nút submit sẽ tự động gửi form mà không cần JavaScript --%>
                <button type="submit" class="btn btn-primary px-4">Add Employee</button>
            </div>
        </div>
    </form>
</div>

<%@ include file="footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    // Khai báo contextPath để tệp JS bên ngoài sử dụng đúng URL
    var contextPath = "${pageContext.request.contextPath}";
</script>

</body>
</html>