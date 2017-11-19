<%@ page import="ru.job4j.servlet.User" %>
<%@ page import="ru.job4j.servlet.UserStorage" %>
<%@ page import="java.sql.Timestamp" %><%--
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
    <a href="<%=request.getContextPath()%>/create.jsp">Add New User</a>
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
        <% for (User user : UserStorage.getInstance().getUsers()) {%>
        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=new Timestamp(user.getCreateDate())%></td>
            <td><a href=<%=request.getContextPath()%>/update.jsp?login=<%=user.getLogin()%>>edit</a></td>
            <td><a href=<%=request.getContextPath()%>/delete?login=<%=user.getLogin()%>>delete</a></td>
        </tr>
        <% } %>
    </table>

</body>
</html>
