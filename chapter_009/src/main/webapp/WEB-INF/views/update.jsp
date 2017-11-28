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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <script>
        <%@include file="js/countryJson.js"%>
    </script>
    <title>Edit User</title>
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
<c:if test="${admin == true}">
    <form class="form-horizontal" action='${pageContext.servletContext.contextPath}/update' method='post' onsubmit="return verifyForm();">
        <fieldset>
            <legend>Edit user</legend>
            <input id="login" name="login" type="hidden" value="<c:out value="${user.login}"></c:out>" class="form-control input-md" required="">
            <div class="form-group">
                <label class="col-md-4 control-label" for="name">Name</label>
                <div class="col-md-4">
                    <input id="name" name="name" type="text" value='<c:out value="${user.name}"></c:out>' class="form-control input-md" required="">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="email">@mail@</label>
                <div class="col-md-6">
                    <input id="email" name="email" type="email" value='<c:out value="${user.email}"></c:out>' class="form-control input-md" required="">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password" name="password" type="password" value='<c:out value="${user.password}"></c:out>' class="form-control input-md" required="">
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
</c:if>
<c:if test="${admin == false}">
    <form class="form-horizontal" action='${pageContext.servletContext.contextPath}/update' method='post' onsubmit="return verifyForm();">
        <fieldset>
            <legend>Edit user</legend>
            <input id="login" name="login" type="hidden" value="<c:out value="${user.login}"></c:out>" class="form-control input-md" required="">
            <div class="form-group">
                <label class="col-md-4 control-label" for="name">Name</label>
                <div class="col-md-4">
                    <input id="name" name="name" type="text" value='<c:out value="${user.name}"></c:out>' class="form-control input-md" required="">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="email">@mail@</label>
                <div class="col-md-6">
                    <input id="email" name="email" type="email" value='<c:out value="${user.email}"></c:out>' class="form-control input-md" required="">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password</label>
                <div class="col-md-4">
                    <input id="password" name="password" type="password" value='<c:out value="${user.password}"></c:out>' class="form-control input-md" required="">
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
</c:if>
</body>
</html>
