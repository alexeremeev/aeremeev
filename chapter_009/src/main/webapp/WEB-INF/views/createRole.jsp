<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: ereme
  Date: 23.11.2017
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Role</title>
</head>
<body>
<h1>Add New Role</h1>
<form action='${pageContext.servletContext.contextPath}/createRole' method='post'>
    <table>
        <tr><td>Name:</td><td><input type='text' name='name'/></td></tr>
        <tr><td><input type="checkbox" name="admin" value="1">Admin Permissions</td></tr>
        <tr><td><input type='submit' value='Save Role'/></td></tr>
    </table>
</form>
<br/>
<a href="${pageContext.servletContext.contextPath}/">View All Users and Roles</a>
</body>
</html>
