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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

</head>
<style>
    <%@ include file="css/login.css"%>
</style>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<div class="container">
    <h1 class="welcome text-center">Welcome to <br> User Storage</h1>
    <div class="card card-container">
        <h2 class='login_title text-center'>Login</h2>
        <hr>

        <form class="form-signin" action="${pageContext.servletContext.contextPath}/singin" method="post">
            <span city="reauth-login" class="reauth-login"></span>
            <p class="input_title">Login</p>
            <input type="text" city="inputLogin" class="login_box" name="login" placeholder="Login" required autofocus>
            <p class="input_title">Password</p>
            <input type="password" city="inputPassword" class="login_box" name="password" placeholder="Password" required>
            <button class="btn btn-lg btn-primary" type="submit">Login</button>
        </form><!-- /form -->
    </div><!-- /card-container -->
</div><!-- /container -->

</body>
</html>
