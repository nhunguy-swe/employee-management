<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/src/css/login.css' />">
</head>
<body>
<div class="login-container">
    <h2>LOGIN</h2>

    <p id="error" style="color: red; text-align: center;">
        ${loginFail != null ? loginFail : ""}
    </p>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="userName">User Name <span class="required">*</span></label>
            <input type="text" id="userName" name="userName" placeholder="Enter user name" required>
        </div>

        <div class="form-group">
            <label for="password">Password <span class="required">*</span></label>
            <input type="password" id="password" name="password" placeholder="Enter password" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-login">Login</button>
            <a href="<c:url value='/register' />" class="link-register">Click here to Register</a>
        </div>
    </form>
</div>
</body>
</html>
