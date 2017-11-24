<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: aeremeev
  Date: 18.11.2017
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New User</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<h1>Add New User</h1>
<form action='${pageContext.servletContext.contextPath}/create' method='post'>
    <table>
        <tr><td>Name:</td><td><input type='name' name='name'/></td></tr>
        <tr><td>Login:</td><td><input type='login' name='login'/></td></tr>
        <tr><td>Email:</td><td><input type='email' name='email'/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
        <tr><td>Permissions:</td><td><select name="role" size="1">
            <c:forEach items = "${roles}" var = "role">
                <option value = "${role.id}"><c:out value="${role.name}"></c:out></option>
            </c:forEach>
        </select></td></tr>
        <tr><td><input type='submit' value='Save User'/></td></tr>
    </table>
</form>
<br/>
<a href="${pageContext.servletContext.contextPath}/">View All Users</a>
</body>
</html>
