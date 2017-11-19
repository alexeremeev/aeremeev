<%@ page import="ru.job4j.servlet.User" %>
<%@ page import="ru.job4j.servlet.UserStorage" %><%--
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
<form action='<%=request.getContextPath()%>/update' method='post'>
    <%String login = request.getParameter("login");%>
    <%User user = UserStorage.getInstance().findByLogin(login);%>
    <table>
        <tr><td></td><td><input type='hidden' name='login' value='<%=user.getLogin()%>'/></td></tr>
        <tr><td>Name:</td><td><input type='name' name='name' value='<%=user.getName()%>'/></td></tr>
        <tr><td>Email:</td><td><input type='email' name='email' value='<%=user.getEmail()%>'/></td></tr>
        <tr><td><input type='submit' value='Save Changes'/></td></tr>
        </table>
    </form>
</body>
</html>
