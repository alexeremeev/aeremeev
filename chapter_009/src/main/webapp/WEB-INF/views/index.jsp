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
    <title>Users</title>
</head>
<body>
<c:if test="${admin == true}">
<a href="${pageContext.servletContext.contextPath}/create">Add New User</a>
<h1>Users List</h1>
<table border='1' width='100%'>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>CreateDate</th>
        <th>Role</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="dateObject" class="java.util.Date" />
        <jsp:setProperty name="dateObject" property="time" value="${user.createDate}"/>
    <tr>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><fmt:formatDate value="${dateObject}" pattern="dd/MM/yyyy HH:mm"/></td>
        <td><c:out value="${user.role.name}"></c:out></td>
        <td><a href=${pageContext.servletContext.contextPath}/update?login=<c:out value="${user.login}"></c:out>>edit</a></td>
        <td><a href=${pageContext.servletContext.contextPath}/delete?login=<c:out value="${user.login}"></c:out>>delete</a></td>
    </tr>

    </c:forEach>
</table>
    <br/>
    <br/>
    <a href="${pageContext.servletContext.contextPath}/createRole">Add New Role</a>
    <h1>Roles List</h1>
    <table border='1' width='100%'>
        <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Admin Permissions</th>
        <th>Edit</th>
        <th>Delete</th>
        </tr>
            <c:forEach items="${roles}" var="role">
        <tr>
            <td><c:out value="${role.id}"></c:out></td>
            <td><c:out value="${role.name}"></c:out></td>
            <td><c:out value="${role.admin}"></c:out></td>
            <td><a href=${pageContext.servletContext.contextPath}/updateRole?id=<c:out value="${role.id}"></c:out>>edit</a></td>
            <td><a href=${pageContext.servletContext.contextPath}/deleteRole?id=<c:out value="${role.id}"></c:out>>delete</a></td>
        <tr>
        </c:forEach>
    </table>

    </c:if>
    <c:if test="${admin == false}">
    <h1>Your Data</h1>
    <table border='1' width='100%'>
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>CreateDate</th>
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <jsp:useBean id="date" class="java.util.Date" />
        <jsp:setProperty name="date" property="time" value="${notAdmin.createDate}"/>
        <tr>
            <td><c:out value="${notAdmin.name}"></c:out></td>
            <td><c:out value="${notAdmin.login}"></c:out></td>
            <td><c:out value="${notAdmin.email}"></c:out></td>
            <td><fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm"/></td>
            <td><c:out value="${notAdmin.role.name}"></c:out></td>
            <td><a href=${pageContext.servletContext.contextPath}/update>edit</a></td>
            <td>Not Available</td>
        </tr>
        </c:if>
    </table>
<form>
    <input type="button" value="Sing Out" onclick="window.location.href='${pageContext.servletContext.contextPath}/singout'" />
</form>

</body>
</html>
