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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <script>
        <%@include file="js/countryJson.js"%>
    </script>
    <script>
        <%@include file="js/verifyForm.js"%>
    </script>
    <title>Create New User</title>
</head>
<style>
    <%@ include file="css/edit.css"%>
</style>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<form class="form-horizontal" id="create" action='${pageContext.servletContext.contextPath}/create' method='post' onsubmit="verifyForm();">
    <fieldset>
        <legend>Add New User</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="name">Name</label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" placeholder="Name" class="form-control input-md" required="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="login">Login</label>
            <div class="col-md-4">
                <input id="login" name="login" type="text" placeholder="Login" class="form-control input-md" required="">
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="email">@mail@</label>
            <div class="col-md-4">
                <input id="email" name="email" type="email" placeholder="@" class="form-control input-md" required="">
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="password" class="form-control input-md" required="">
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="role">Role</label>
            <div class="col-md-4">
                <select id="role" name="role" class="form-control">
                    <c:forEach items = "${roles}" var = "role">
                        <option value = "${role.id}"><c:out value="${role.name}"></c:out></option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="country">Country</label>
            <div class="col-md-4">
                <select id="country" name="country" class="form-control">
                    <option>Select Country</option>
                    <c:forEach items="${countries}" var="country">
                        <option value="${country.key}">${country.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="city">City</label>
            <div class="col-md-4">
                <select id="city" name="city" class="form-control">
                    <option>Select City</option>
                </select>
            </div>
        </div>



    </fieldset>
    <!-- Button -->
    <div class="form-group">
        <label class="col-md-4 control-label" for="singlebutton"></label>
        <div class="col-md-4">
            <button id="singlebutton" name="singlebutton" class="btn btn-primary" type="submit">Save user</button>
        </div>
    </div>
    <!-- Button -->
    <div class="form-group">
        <label class="col-md-4 control-label" for="singlebutton"></label>
        <div class="col-md-1">
            <a href="${pageContext.servletContext.contextPath}/" class="btn btn-link" role="button">View users and roles</a>
        </div>
    </div>
</form>

</body>
</html>
