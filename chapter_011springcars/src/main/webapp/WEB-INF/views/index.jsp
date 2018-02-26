<%--
  Created by IntelliJ IDEA.
  User: aeremeev
  Date: 17.02.2018
  Time: 14:20
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="styleCss" />
    <spring:url value="/resources/js/main.js" var="mainJs" />
    <spring:url value="/resources/img/car-icon.png" var="ico"/>
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
    <title>Продажа машин</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${styleCss}">
    <link rel="shortcut icon" href="${ico}" type="image/png">
    <script src="${mainJs}" type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <h4>Авторизация</h4>
            <form id="authorisation">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input id="username" type="text" class="form-control" name="username" placeholder="Login" required autofocus>
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input id="password" type="password" class="form-control" name="password" placeholder="Password" required>
                </div>
                <br>
                <button type="button" class="btn btn-link" id="auth-btn">Войти</button>
                <button type="button" class="btn btn-link" id="add-user-btn">Регистрация</button>
            </form>

            <div id="info_success_auth" hidden>
                <h3><span class="label label-info">Вход выполнен</span></h3>
                <br>
                <%--&lt;%&ndash;<a href="<spring:url value= "/create"/>">&ndash;%&gt;--%>
                    <button type="button" class="btn btn-link" id="move-order-page" hidden>Добавить объявление</button>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
            </div>
        </div>

        <div class="col-sm-9">
            <h4 align="center">Список объявлений</h4>
            <br>

            <div align="center">
                <h5 align="center" >Фильтр объявлений</h5>
                <br>
                <form class="form-inline">
                    <div class="form-group">
                        Марка:
                        <input type="text" class="form-control"  placeholder="Марка" id="model">
                    </div>
                    <div class="form-group">
                        С фото:
                        <input type="checkbox" class="form-control" id ="photo">
                    </div>
                    <div class="form-group">
                        За последние сутки:
                        <input type="checkbox" class="form-control" id ="orderDate">
                    </div>

                    <button type="button" class="btn btn-default btn-filter" id="flt-ord-btn" onclick="filterTable()">Поиск</button>
                </form>

            </div>
            <br><br>
            <div class="container">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Продано</th>
                        <th>Автомобиль</th>
                        <th>Цена</th>
                        <th>Пробег</th>
                        <th>Год</th>
                        <th>Фото</th>
                        <th>Редактировать</th>
                    </tr>
                    </thead>
                    <tbody id="table-body">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modalWindow">
    <form>
        <button type="button" class="btn btn-default btn-close-modal">закрыть</button>
    </form>

    <div class="container">
        <br>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">

            <div class="carousel-inner" role="listbox" id="carousel-body">

            </div>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</div>

</body>
</html>
