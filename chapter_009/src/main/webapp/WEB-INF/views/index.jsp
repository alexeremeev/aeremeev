<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%--
  Created by IntelliJ IDEA.
  User: aeremeev
  Date: 18.11.2017
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <title>Users</title>
    <style>
        <%@ include file="css/index.css"%>
    </style>
</head>
<body>
<c:if test="${admin == true}">
    <div align="center">
        <h1>Users</h1>
    </div>
    <div class="container">
        <div class="row col-md-16 col-md-offset-1 custyle">
            <table class="table table-striped custab">
                <thead>
                <a href="${pageContext.servletContext.contextPath}/create" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new user</a>
                <tr>
                    <th>Name</th>
                    <th>Login</th>
                    <th>@mail@</th>
                    <th>Registered</th>
                    <th>Role</th>
                    <th>Country</th>
                    <th>City</th>
                    <th>Action</th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <jsp:useBean id="dateObject" class="java.util.Date" />
                    <jsp:setProperty name="dateObject" property="time" value="${user.createDate}"/>
                    <tr>
                        <td><c:out value="${user.name}"></c:out></td>
                        <td><c:out value="${user.login}"></c:out></td>
                        <td><c:out value="${user.email}"></c:out></td>
                        <td><fmt:formatDate value="${dateObject}" pattern="dd/MM/yyyy HH:mm"/></td>
                        <td><c:out value="${user.role.name}"></c:out></td>
                        <td><c:out value="${user.address.country}"></c:out></td>
                        <td><c:out value="${user.address.city}"></c:out></td>
                        <td><a class='btn btn-info btn-xs' href="${pageContext.servletContext.contextPath}/update?login=<c:out value="${user.login}"></c:out>">
                            <span class="glyphicon glyphicon-edit"></span> Edit</a> <a href="${pageContext.servletContext.contextPath}/delete?login=<c:out value="${user.login}"></c:out>"
                            class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <br/>
    <br/>
    <div align="center">
        <h1>Roles</h1>
    </div>
    <div class="container">
        <div class="row col-md-16 col-md-offset-1 custyle">
            <table class="table table-striped custab">
                <thead>
                <a href="${pageContext.servletContext.contextPath}/createRole" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new role</a>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Admin Permissions</th>
                    <th>Action</th>
                </tr>
                </thead>
                <c:forEach items="${roles}" var="role">
                    <tr>
                        <td><c:out value="${role.id}"></c:out></td>
                        <td><c:out value="${role.name}"></c:out></td>
                        <td><c:out value="${role.admin}"></c:out></td>
                        <td><a class='btn btn-info btn-xs' href="${pageContext.servletContext.contextPath}/updateRole?id=<c:out value="${role.id}"></c:out>">
                            <span class="glyphicon glyphicon-edit"></span> Edit</a> <a href="${pageContext.servletContext.contextPath}/deleteRole?id=<c:out value="${role.id}"></c:out>"
                            class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    </c:if>
    <c:if test="${admin == false}">
        <div align="center">
            <h1>Your Data</h1>
        </div>
        <div class="container">
            <div class="row col-md-16 col-md-offset-1 custyle">
                <table class="table table-striped custab">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Login</th>
                        <th>@mail@</th>
                        <th>Registered</th>
                        <th>Role</th>
                        <th>Country</th>
                        <th>City</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <jsp:useBean id="date" class="java.util.Date" />
                    <jsp:setProperty name="date" property="time" value="${notAdmin.createDate}"/>
                        <tr>
                            <td><c:out value="${notAdmin.name}"></c:out></td>
                            <td><c:out value="${notAdmin.login}"></c:out></td>
                            <td><c:out value="${notAdmin.email}"></c:out></td>
                            <td><fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><c:out value="${notAdmin.role.name}"></c:out></td>
                            <td><c:out value="${notAdmin.address.country}"></c:out></td>
                            <td><c:out value="${notAdmin.address.city}"></c:out></td>
                            <td><a class='btn btn-info btn-xs' href="${pageContext.servletContext.contextPath}/update">
                                <span class="glyphicon glyphicon-edit"></span> Edit</a></td>
                        </tr>
                </table>
            </div>
        </div>
        </c:if>
<div align="center">
    <a href="${pageContext.servletContext.contextPath}/singout" class="btn btn-primary btn-xs pull-right">Sing Out</a>
</div>
</body>
</html>
