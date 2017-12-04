<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aeremeev
  Date: 01.12.2017
  Time: 16:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Repository</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<title>DAO Repository</title>
<style>
    <%@ include file="css/index.css"%>
</style>
<script><%@include file="js/scripts.js"%></script>
</head>
<body>
<c:if test="${role == 1}">
    <form class="form-horizontal" id="create">
        <fieldset>
            <legend>Create new user</legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="name">Name</label>
                <div class="col-md-4">
                    <input id="name" name="name" type="text" placeholder="Name" class="form-control input-md" required="">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password" name="password" type="password" placeholder="Password" class="form-control input-md" required="">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="address">Address</label>
                <div class="col-md-4">
                    <input id="address" name="address" type="text" placeholder="Address" class="form-control input-md" required="">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Role</label>
                <div class="col-md-4">
                    <input id="role" name="role" type="text" placeholder="admin, mandator, user" class="form-control input-md" required="">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Music</label>
                <div class="col-md-4">
                    <input id="musicType" name="musicType" type="text" placeholder="Pop, Rock, Rap... etc" class="form-control input-md" required="">
                </div>
            </div>

        </fieldset>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="create-btn"></label>
            <div class="col-md-4">
                <button id="create-btn" name="singlebutton" class="btn btn-primary" type="button">Create user</button>
            </div>
        </div>
    </form>


    <form class="form-horizontal">
        <fieldset>
            <legend>Repository</legend>

            <div class="form-group">
                <label class="col-md-6  control-label" for="Address_field">Address</label>
                <div class="col-md-6">
                    <input id="Address_field" name="Address_field" type="text" placeholder="Please enter user address" class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label" for="Musictype_field">Music Type</label>
                <div class="col-md-6">
                    <input id="Musictype_field" name="Musictype_field" type="text" placeholder="Please enter user music type" class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label" for="Role_field">Role</label>
                <div class="col-md-6">
                    <input id="Role_field" name="Role_field" type="text" placeholder="Please enter user role" class="form-control input-md">
                </div>
            </div>

        </fieldset>
        <!-- Button -->
        <div class="form-group">
            <div class="col-md-8">
                <button id="addr-btn" name="singlebutton" class="btn btn-primary" type="button">Find by address</button>
                <button id="musictype-btn" name="singlebutton" type="button" class="btn btn-primary">Find by music</button>
                <button id="role-btn" name="singlebutton" type="button" class="btn btn-primary">Find by role</button>
                <button id="role-all-btn" name="singlebutton" type="button" class="btn btn-primary">All users sorted by roles</button>
            </div>
        </div>
        <div class="row">
            <table align="left" class="table-bordered table-hover table-condensed" id = "info">
                <thead>
                <tr>
                    <th align="center">User name </th>
                    <th align="center">Address</th>
                    <th align="center">Music styles</th>
                    <th align="center">Role</th>
                </tr>
                </thead>
                <tbody id = "insert">
                </tbody>
            </table>
        </div>
    </form>


    </br>
    <div class="btn-group">
        <a href="${pageContext.servletContext.contextPath}/logout" class="btn btn-primary btn-xs pull-right">Logout</a>
    </div>

</c:if>
<c:if test="${role == 2}">
    <form class="form-horizontal">
        <fieldset>
            <legend>Repository</legend>

            <div class="form-group">
                <label class="col-md-6  control-label" for="Address_field">Address</label>
                <div class="col-md-6">
                    <input id="Address_field" name="Address_field" type="text" placeholder="Please enter user address" class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label" for="Musictype_field">Music Type</label>
                <div class="col-md-6">
                    <input id="Musictype_field" name="Musictype_field" type="text" placeholder="Please enter user music type" class="form-control input-md">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label" for="Role_field">Role</label>
                <div class="col-md-6">
                    <input id="Role_field" name="Role_field" type="text" placeholder="Please enter user role" class="form-control input-md">
                </div>
            </div>

        </fieldset>
        <!-- Button -->
        <div class="form-group">
            <div class="col-md-8">
                <button id="addr-btn" name="singlebutton" class="btn btn-primary" type="button">Find by address</button>
                <button id="musictype-btn" name="singlebutton" type="button" class="btn btn-primary">Find by music</button>
                <button id="role-btn" name="singlebutton" type="button" class="btn btn-primary">Find by role</button>
                <button id="role-all-btn" name="singlebutton" type="button" class="btn btn-primary">All users sorted by roles</button>
            </div>
        </div>
        <div class="row">
            <table align="left" class="table-bordered table-hover table-condensed" id = "info">
                <thead>
                <tr>
                    <th align="center">User name </th>
                    <th align="center">Address</th>
                    <th align="center">Music styles</th>
                    <th align="center">Role</th>
                </tr>
                </thead>
                <tbody id = "insert">
                </tbody>
            </table>
        </div>
    </form>


    </br>
    <div class="btn-group">
        <a href="${pageContext.servletContext.contextPath}/logout" class="btn btn-primary btn-xs pull-right">Logout</a>
    </div>

</c:if>
<c:if test="${role > 2}">
    <h2>Welcome to DAO example! There're no operations for ordinary users! Please logout!</h2>
    <div align="left">
        <a href="${pageContext.servletContext.contextPath}/logout" class="btn btn-primary btn-xs pull-right">Logout</a>
    </div>
</c:if>
</body>
</html>
