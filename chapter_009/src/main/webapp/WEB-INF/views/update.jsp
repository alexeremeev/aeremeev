<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: aereremev
  Date: 18.11.2017
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/update' method='post'>

    <table>
        <tr><td></td><td><input type='hidden' name='login' value='<c:out value="${user.login}"></c:out>'/></td></tr>
        <tr><td>Name:</td><td><input type='name' name='name' value='<c:out value="${user.name}"></c:out>'/></td></tr>
        <tr><td>Email:</td><td><input type='email' name='email' value='<c:out value="${user.email}"></c:out>'/></td></tr>
        <tr><td><input type='submit' value='Save Changes'/></td></tr>
        </table>
    </form>
</body>
</html>