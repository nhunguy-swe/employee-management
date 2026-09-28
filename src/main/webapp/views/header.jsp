<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value='/home' />">
            <i class="fa fa-home"></i> Home Page
        </a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/add-employee' />">Add an Employee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/list-employees' />">List Employees</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <span class="nav-link" style="color: yellow">
                        <i class="fa fa-user-circle-o"></i> ${userLogin.userName}
                    </span>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/logout' />">
                        <i class="fa fa-user-times"></i> Logout
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>