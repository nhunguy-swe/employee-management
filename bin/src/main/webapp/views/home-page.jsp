<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<%@ include file="header.jsp" %>

<%-- Vùng chứa nội dung quan trọng để AJAX đổ dữ liệu vào --%>
<div id="main-content">
    <div class="container text-center" style="margin-top: 100px;">
        <h1>Welcome to Employee Management System</h1>
    </div>
</div>

<%@ include file="footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    // Tự động lấy tên project (quan-ly-du-an) thay vì ghi cứng
    var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="<c:url value='/src/js/home-page.js' />"></script>
<%-- Cần nhúng add-employee.js ở đây để xử lý sự kiện nút bấm sau khi load form --%>
<script src="<c:url value='/src/js/add-employee.js' />"></script>
</body>
</html>