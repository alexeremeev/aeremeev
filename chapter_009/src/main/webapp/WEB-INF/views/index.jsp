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
    <a href="${pageContext.servletContext.contextPath}/create">Add New User</a>
    <h1>Users List</h1>
    <table border='1' width='100%'>
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>CreateDate</th>
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
            <td><a href=${pageContext.servletContext.contextPath}/update?login=<c:out value="${user.login}"></c:out>>edit</a></td>
            <td><a href=${pageContext.servletContext.contextPath}/delete?login=<c:out value="${user.login}"></c:out>>delete</a></td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
