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
    <h1>Add New User</h1>
    <form action='<%=request.getContextPath()%>/create' method='post'>
        <table>
        <tr><td>Name:</td><td><input type='name' name='name'/></td></tr>
        <tr><td>Login:</td><td><input type='login' name='login'/></td></tr>
        <tr><td>Email:</td><td><input type='email' name='email'/></td></tr>
        <tr><td><input type='submit' value='Save User'/></td></tr>
        </table>
    </form>
    <br/>
    <a href=<%=request.getContextPath()%>/index.jsp>View All Users</a>
</body>
</html>
