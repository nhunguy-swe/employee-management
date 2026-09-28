<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký thành viên</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/src/css/login.css' />">
    <style>
        .error-msg { color: red; font-size: 0.9em; margin-bottom: 10px; text-align: center; font-weight: bold; }
        .required { color: red; }
    </style>
</head>
<body>
<div class="login-container">
    <h2>REGISTER</h2>

    <p id="error" class="error-msg"></p>

    <c:if test="${not empty message}">
        <p class="error-msg">${message}</p>
    </c:if>

    <form action="<c:url value='/register' />" method="post"
          onsubmit="return validateRegister()" name="frm-register">

        <div class="form-group">
            <label for="firstName">First Name <span class="required">*</span></label>
            <input type="text" id="firstName" name="firstName" placeholder="Enter first name" required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name <span class="required">*</span></label>
            <input type="text" id="lastName" name="lastName" placeholder="Enter last name" required>
        </div>

        <div class="form-group">
            <label for="email">Email <span class="required">*</span></label>
            <input type="email" id="email" name="email" placeholder="Enter email" required>
        </div>

        <div class="form-group">
            <label for="userName">User Name <span class="required">*</span></label>
            <input type="text" id="userName" name="userName" placeholder="Enter user name" required>
        </div>

        <div class="form-group">
            <label for="password">Password <span class="required">*</span></label>
            <input type="password" id="password" name="password" placeholder="Enter password" required>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirm Password <span class="required">*</span></label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-login">Register</button>
            <a href="<c:url value='/login' />" class="link-register">Back to Login</a>
        </div>
    </form>
</div>

<script src="<c:url value='/src/js/register-user.js' />"></script>
</body>
</html>