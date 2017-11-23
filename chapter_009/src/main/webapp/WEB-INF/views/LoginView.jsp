<%--
  Created by IntelliJ IDEA.
  User: ereme
  Date: 20.11.2017
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/singin" method="post">
    Login: <input type="text" name="login"></br>
    Password: <input type="password" name="password"></br>
    <input type="submit">
</form>

</body>
</html>
