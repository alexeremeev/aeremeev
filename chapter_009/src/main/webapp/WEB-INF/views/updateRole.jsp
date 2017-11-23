<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: ereme
  Date: 23.11.2017
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Role</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/updateRole' method='post'>
        <table>
            <tr><td></td><td><input type='hidden' name='id' value='<c:out value="${role.id}"></c:out>'/></td></tr>
            <tr><td>Name:</td><td><input type='text' name='name' value='<c:out value="${role.name}"></c:out>'/></td></tr>
            <tr><td><input type="checkbox" name="admin" value="1">Admin Permissions</td></tr>
            <tr><td><input type='submit' value='Save Changes'/></td></tr>
        </table>
</form>
</body>
</html>
