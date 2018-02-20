<%--
  Created by IntelliJ IDEA.
  User: aeremeev
  Date: 18.02.2018
  Time: 15:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="styleCss" />
    <spring:url value="/resources/js/create.js" var="createJs" />
    <spring:url value="/resources/img/car-icon.png" var="ico"/>
    <title>Продажа машин</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${styleCss}">
    <link rel="shortcut icon" href="${ico}" type="image/png">
    <script src="${createJs}" type="text/javascript"></script>
</head>
<body>
<h2 id="titleOrder" align="center">Новое объявление</h2>
<br>
<div class="container">
    <form>
        <div class="form-group">
            <label for="name">Название</label>
            <input type="text" class="form-control" id="name">
        </div>

        <div class="form-group">
            <label for="model">Марка</label>
            <select id="model" class="selectpicker form-control" data-width="35%">

            </select>
        </div>

        <div class="form-group">
            <label for="body">Кузов</label>
            <select id="body" class="selectpicker form-control" data-width="35%">

            </select>
        </div>

        <div class="form-group">
            <label for="transmission">Трансмиссия</label>
            <select id="transmission" class="selectpicker form-control" data-width="35%">

            </select>
        </div>

        <div class="form-group">
            <label for="engine">Мощность л/с</label>
            <select id="engine" class="selectpicker form-control" data-width="35%">

            </select>
        </div>

        <div class="form-group" >
            <label for="gearbox" >Коробка</label>
            <select id="gearbox" class="selectpicker form-control" data-width="35%">

            </select>
        </div>

        <div class="form-group">
            <label for="price">Цена</label>
            <input type="number" class="form-control" id="price" placeholder="Введите цену">
        </div>
        <div class="form-group">
            <label for="mileage">Пробег км</label>
            <input type="number" class="form-control" id="mileage" placeholder="Введите пробег">
        </div>

        <div class="form-group">
            <label for="release">Год выпуска</label>
            <input type="date" class="form-control" id="release" placeholder="Введите год выпуска">
        </div>

        <div>
            <label for="sold">Продано</label>
            <input type="checkbox" id="sold">
        </div>

        <button type="button" class="btn btn-warning" id="add-order-btn">Выставить на продажу</button>
    </form>
</div>
<br>
<h4 align="center">Добавьте фотографии</h4>
<div class="container">
    <form class="form-inline" enctype="multipart/form-data">
        <div class="form-group">
            <input type="file" accept="image/jpeg"  multiple id="file-image">
            <input type="button" value="Добавить изображение" id="file-btn-load">
        </div>
    </form>
</div>
<div class="container">
    <button onclick="goBack()">Назад</button>
</div>
</body>
</html>
