<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 30.11.2022
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>${param.action == create? 'Add meal':'Edit meal'}</h2>
<jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo" scope="request"/>
<form action="meals" method="post">
    <input type="hidden" name="id" value="${mealTo.id}">
    <label for="dateTime">Enter dateTime</label>
    <input type="datetime-local" id="dateTime" name="dateTime" value="${mealTo.dateTime}">
    <br>
    <label for="description">Enter description</label>
    <input type="text" id="description" name="description" value="${mealTo.description}">
    <br>
    <label for="calories">Enter calories</label>
    <input type="text" id="calories" name="calories" value="${mealTo.calories}">
    <br>
    <input type="submit" value="Submit">
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
