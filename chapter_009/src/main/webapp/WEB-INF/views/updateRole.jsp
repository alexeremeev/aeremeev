<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: ereme
  Date: 23.11.2017
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <title>Edit Role</title>
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
<form class="form-horizontal" action='${pageContext.servletContext.contextPath}/updateRole' method='post'>
    <fieldset>
        <legend>Edit role</legend>
        <input id="id" name="id" type="hidden" value="<c:out value="${role.id}"></c:out>" class="form-control input-md" required="">
        <div class="form-group">
            <label class="col-md-4 control-label" for="name">Name</label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" value="<c:out value="${role.name}"></c:out>"  class="form-control input-md" required="">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="adminPermissons">Admin Permissions</label>
            <div class="col-md-4">
                <input id="adminPermissons" type="checkbox" name="admin" value="1">
            </div>
        </div>

    </fieldset>
    <!-- Button -->
    <div class="form-group">
        <label class="col-md-4 control-label" for="singlebutton"></label>
        <div class="col-md-4">
            <button id="singlebutton" name="singlebutton" class="btn btn-primary" type="submit">Save changes</button>
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
