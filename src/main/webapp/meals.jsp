<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 29.11.2022
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table {
            width: 600px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        td, th {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
        }
        th {
            background: #b0e0e6; /* Цвет фона */
        }

        .exceed {
            color: red;
        }
        .normal {
            color: green;
        }
    </style>
</head>
<body>

<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h3><a href="meals?action=create">Add meal</a> </h3>
<table>
    <tr>
        <th>Id</th>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${mealTos}" var="mealTo">
        <tr ${mealTo.excess? 'class="exceed"': 'class="normal"'} >
            <td>${mealTo.id}</td>
            <td>${fn:formatDateTime(mealTo.dateTime)}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?action=update&id=${mealTo.id}"/>Update</td>
            <td><a href="meals?action=delete&id=${mealTo.id}"/>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
