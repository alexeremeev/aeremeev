<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserStorage" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: aeremeev
  Date: 17.11.2017
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/" method="post">
    Login: <input type="text" name="login"></br>
    Email: <input type="text" name="email"></br>
    <input type="submit">
</form>

<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th> login </th>
        <th> email </th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.email}"></c:out> </td>

    </tr>
    </c:forEach>
</table>

</body>
</html>
